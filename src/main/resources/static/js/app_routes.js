angular.module('gifkrieg')
    .config(function ($routeProvider, $httpProvider, $locationProvider) {

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
            }).when('/submitGif', {
                templateUrl: 'submitGif.html'
            }).when('/vote', {
                templateUrl: 'vote.html'
            }).when('/mydeck', {
                templateUrl: 'usergifs.html',
                controller: 'usergif',
                controllerAs: 'controller'
            }).otherwise('/');

            //TODO see what's up with getting rid of #hashtag
            //$locationProvider.html5Mode(true);

            $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        })