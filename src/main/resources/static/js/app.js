angular.module('hello', ['ngRoute']).config(function ($routeProvider, $httpProvider, $locationProvider) {

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
        }).when('/submitGif', {
            templateUrl: 'submitGif.html'
            //        controller : 'leaderboard',
            //        controllerAs: 'controller'
        }).when('/mydeck', {
            templateUrl: 'usergifs.html',
            controller: 'usergif',
            controllerAs: 'controller'
        }).otherwise('/');

        //TODO see what's up with getting rid of #hashtag
        //$locationProvider.html5Mode(true);

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';


    }).factory("UserService", function ($rootScope) {
        var self = this;

        return {
            fromUserDetails: function (data) {
                $rootScope.authenticated = true;
                $rootScope.username = data.username;
                $rootScope.authorities = data.authorities;
                $rootScope.userId = data.userId;
                $rootScope.gifdeck = data.userGifs;
                $rootScope.hasSubmittedCurrent = data.hasSubmittedCurrent;
                $rootScope.hasVotedCurrent = data.hasVotedCurrent;
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

    .service('challengeService', function ($http) {
        var data = {};

        // think about caching with  $http.get('/pub/challenge', {cache: true})
        $http.get('/pub/challenge').then(function (response) {
            data.current = response.data.current;
            data.past = response.data.past;
            data.voting = response.data.voting;
            data.votingSubmissions = response.data.votingSubmissions;
            data.currentSubmissions = response.data.currentSubmissions;
            data.completedVotes = response.data.completedVotes;

            data.current.startTime = getDisplayDateTimeForEpoch(data.current.startTime);
            data.current.endTime = getDisplayDateTimeForEpoch(data.current.endTime);
            data.past.forEach(function (challenge) {
                challenge.startTime = getDisplayDateTimeForEpoch(challenge.startTime);
                challenge.endTime = getDisplayDateTimeForEpoch(challenge.endTime);
            });
        })


        return {
            getChallenge: function () {
                return data;
            },
            setProperty: function (value) {
                property = value;
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



    }).controller('home', function (challengeService) {
        var self = this;
        self.data = challengeService.getChallenge();
        // think about caching with  $http.get('/pub/challenge', {cache: true})


    }).controller('leaderboard', function ($http, challengeService) {
        var self = this;
        self.data = challengeService.getChallenge();
        $http.get('/pub/leaderboard').then(function (response) {
            self.data = response.data;
        })
    }).controller('submitGif', function ($rootScope, $scope, $http, challengeService) {
        var self = this;
//        self.data = challengeService.getChallenge();

        $scope.submitGif = function (gifId) {
            console.log("submitting gif: " + gifId);
//            var data = { params: {"gifId":gifId};
            $http.post('/api/submission/' + challengeService.getChallenge().current.id, {'gifId': gifId})
                .then(function successCallback(response) {
                     console.log("Success! Gif submitted.");
                     $rootScope.hasSubmittedCurrent = true;
                   }, function errorCallback(response) {
                   console.log("Failure with gif submission." + response);
                     // called asynchronously if an error occurs
                     // or server returns response with an error status.
                   });
        }


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
