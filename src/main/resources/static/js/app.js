angular.module('hello', ['ngRoute']).config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'home.html',
        controller: 'home',
        controllerAs: 'controller'
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
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';


}).controller('navigation', function ($rootScope, $scope, $route, $http, $location) {
    console.log("Navigation controller...");
    var self = this;

    $rootScope.errors = [];
    $rootScope.hasError = false;
    
    $rootScope.userGifs = [{"url": "http://www.reactiongifs.com/wp-content/uploads/2013/11/trre.gif"},{"url": "http://www.reactiongifs.com/r/somg.gif"}];


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
        console.log(headers);
        console.log("Doing get in authenticate()")
        $http.get('auth/user', {
            headers: headers
        }).then(function (response) {
            console.log(response);
            if (response.data.name) {
                $rootScope.authenticated = true;
                $rootScope.username = response.data.name;
                $rootScope.authorities = response.data.authorities;
                $rootScope.userId = response.data.principal.userId;
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
            $rootScope.authenticated = false;
            $rootScope.username = null;
            $rootScope.authorities = null;
            $location.path("/");
        });
    }

}).controller('register', function ($rootScope, $scope, $http, $location) {

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
                    $rootScope.username = data.principal.username;
                    $rootScope.authorities = data.principal.authorities;
                    $location.path("/");
                }
            });
    };



}).controller('home', function ($http) {
    var self = this;
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
    console.log('leaderboard controller...');
    $http.get('/pub/leaderboard').then(function (response) {
        self.data = response.data;
    })
});

var getDisplayDateTimeForEpoch = function (epoch) {
    return new Date(epoch).toDateString();
};
