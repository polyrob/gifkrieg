angular.module('hello', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home',
		controllerAs: 'controller'
	}).when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation',
		controllerAs: 'controller'
	}).when('/leaderboard', {
        templateUrl : 'leaderboard.html',
        controller : 'leaderboard',
        controllerAs: 'controller'
    }).otherwise('/');

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('navigation',

		function($rootScope, $http, $location, $route) {

            $rootScope.errors = [];
            $rootScope.hasError = false;


            $rootScope.closeAlert = function (index) {
                $scope.errors.splice(index, 1);
            }

			var self = this;

			self.tab = function(route) {
				return $route.current && route === $route.current.controller;
			};

			var authenticate = function(credentials, callback) {

				var headers = credentials ? {
					authorization : "Basic "
							+ btoa(credentials.username + ":"
									+ credentials.password)
				} : {};
				console.log(headers);
                console.log("Doing get in authenticate()")
				$http.get('user', {
					headers : headers
				}).then(function(response) {
				    console.log(response);
					if (response.data.name) {
						$rootScope.authenticated = true;
						$rootScope.username = response.data.name;
						$rootScope.authorities = response.data.authorities;
					} else {
						$rootScope.authenticated = false;
					}
					callback && callback($rootScope.authenticated);
				}, function() {
					$rootScope.authenticated = false;
					callback && callback(false);
				});

			}

			authenticate();

			self.credentials = {};
			self.login = function() {
				authenticate(self.credentials, function(authenticated) {
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

			self.logout = function() {
				$http.post('logout', {}).finally(function() {
					$rootScope.authenticated = false;
					$rootScope.username = null;
					$rootScope.authorities = null;
					$location.path("/");
				});
			}


    }).controller('home', function($http) {
	var self = this;
//	$http.get('/resource/').then(function(response) {
//		self.greeting = response.data;
//	})
}).controller('leaderboard', function($http) {
    var self = this;
    console.log('leaderboard controller...');
	$http.get('/api/leaderboard').then(function(response) {
		self.data = response.data;
	})
});
