angular.module('hello', ['ngRoute']).config(function ($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl: 'home.html'
//            controller: 'home',
//            controllerAs: 'controller'
        }).when('/login', {
            templateUrl: 'login.html',
            controller: 'navigation',
            controllerAs: 'controller'
        }).when('/register', {
            templateUrl: 'register.html',
            controller: 'register',
            controllerAs: 'controller'
        }).when('/leaderboard', {
            templateUrl: 'leaderboard.html'
            //        controller : 'leaderboard',
            //        controllerAs: 'controller'
        }).when('/mydeck', {
            templateUrl: 'usergifs.html',
            controller: 'usergif',
            controllerAs: 'controller'
        }).otherwise('/');

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';


    }).factory("UserService", function ($rootScope) {

        return {
            fromUserDetails: function (data) {
                $rootScope.authenticated = true;
                $rootScope.username = data.username;
                $rootScope.authorities = data.authorities;
                $rootScope.userId = data.userId;
                $rootScope.gifdeck = data.userGifs;
                return;
            },
            logout: function () {
                $rootScope.authenticated = false;
                $rootScope.username = null;
                $rootScope.authorities = null;
                $rootScope.userId = null;
                $rootScope.gifdeck = null;
            }
        };
    })

    .controller('navigation', function ($rootScope, $scope, $route, $http, $location, UserService) {
        var self = this;

        $rootScope.errors = [];
        $rootScope.hasError = false;

        $rootScope.closeAlert = function (index) {
            $scope.errors.splice(index, 1);
        }

        self.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };

        var authenticate = function (credentials, callback) {

            var headers = credentials ? {
                authorization: "Basic " +
                    btoa(credentials.username + ":" +
                        credentials.password)
            } : {};
            console.log("Doing get in authenticate()")
            $http.get('auth/user', {
                headers: headers
            }).then(function (response) {
                if (response.data) {
                    $rootScope.authenticated = true;
                    UserService.fromUserDetails(response.data);
//                    $rootScope.username = response.data.username;
//                    $rootScope.authorities = response.data.authorities;
//                    $rootScope.userId = response.data.userId;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback($rootScope.authenticated);
            }, function () {
                $rootScope.authenticated = false;
                callback && callback(false);
            });

        }

        authenticate();

        self.credentials = {};
        self.login = function () {
            authenticate(self.credentials, function (authenticated) {
                if (authenticated) {
                    console.log("Login succeeded")
                    $location.path("/");
                    self.error = false;
                    $rootScope.authenticated = true;
                } else {
                    console.log("Login failed")
                    $location.path("/login");
                    self.error = true;
                    $rootScope.errorMessage = 'Logon failed. Please check your username/password.';
                    $rootScope.authenticated = false;
                }
            })
        };

        self.logout = function () {
            $http.post('logout', {}).finally(function () {
                UserService.logout();
                $location.path("/");
            });
        }

    }).controller('register', function ($rootScope, $scope, $http, $location, UserService) {

        // create a blank object to handle form data.
        $scope.user = {};
        // calling our submit function.
        $scope.submitForm = function () {
            // Posting data to php file
            $http({
                    method: 'POST',
                    url: '/auth/register',
                    data: $scope.user, //forms user object
                    headers: {
                        //                    'Content-Type': 'application/x-www-form-urlencoded'
                    }
                })
                .success(function (data) {
                    if (data.errors) {
                        // Showing errors.
                        $scope.errorUsername = data.errors.username;
                        $scope.errorEmail = data.errors.email;
                        $scope.errorPassword = data.errors.password;
                        $scope.errorPasswordConfirm = data.errors.passwordConfirm;
                    } else {
                        $scope.message = data.message;
                        $rootScope.authenticated = true;
                        UserService.fromUserDetails(data)
//                        $rootScope.username = data.principal.username;
//                        $rootScope.authorities = data.principal.authorities;
                        $location.path("/");
                    }
                });
        };



    }).controller('home', function ($http) {
        var self = this;
        // think about caching with  $http.get('/pub/challenge', {cache: true})
        $http.get('/pub/challenge').then(function (response) {
            self.current = response.data.current;
            self.past = response.data.past;

            self.current.startTime = getDisplayDateTimeForEpoch(self.current.startTime);
            self.current.endTime = getDisplayDateTimeForEpoch(self.current.endTime);
            self.past.forEach(function (challenge) {
                challenge.startTime = getDisplayDateTimeForEpoch(challenge.startTime);
                challenge.endTime = getDisplayDateTimeForEpoch(challenge.endTime);
            });
        })

    }).controller('leaderboard', function ($http) {
        var self = this;
        $http.get('/pub/leaderboard').then(function (response) {
            self.data = response.data;
        })
    }).controller('usergif', function ($http, $rootScope) {
        var self = this;
//        $http.get('/api/user/' + $rootScope.userId).then(function (response) {
//            self.data = response.data;
//            console.log(response.data);
//        })
    });

var getDisplayDateTimeForEpoch = function (epoch) {
    return new Date(epoch).toDateString();
};
