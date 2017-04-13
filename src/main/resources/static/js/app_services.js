angular.module('gifkrieg')

    .factory("UserService", function ($rootScope) {
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

    .factory('challengeService', function ($http) {
        var promise;
        var myService = {
            async: function() {
                if ( !promise ) {
                  // $http returns a promise, which has a then function, which also returns a promise
                    promise = $http.get('/pub/challenge').then(function (response) {
                    // The then function here is an opportunity to modify the response
                    console.log(response);
                    // The return value gets picked up by the then in the controller.
                    return response.data;
                  });
                  // Return the promise to the controller
                  return promise;
                }
                return promise;
            }
        };
        return myService;
    })

    .factory('leaderboardService', function ($http) {
            var promise;
            var myService = {
                async: function() {
                    if ( !promise ) {
                      // $http returns a promise, which has a then function, which also returns a promise
                        promise = $http.get('/pub/leaderboard').then(function (response) {
                        // The then function here is an opportunity to modify the response
                        console.log(response);
                        // The return value gets picked up by the then in the controller.
                        return response.data;
                      });
                      // Return the promise to the controller
                      return promise;
                    }
                    return promise;
                }
            };
            return myService;
        });

