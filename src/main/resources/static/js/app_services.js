angular.module('gifkrieg')

    .factory("UserService", function ($rootScope, $http) {
        var self = this;
        var userState;
        var userGifs;

        return {
            fromUserDetails: function (data) {
                $rootScope.authenticated = true;
                $rootScope.username = data.username;
                $rootScope.authorities = data.authorities;
                $rootScope.userId = data.userId;
                return;
            },
            logout: function () {
                $rootScope.authenticated = false;
                $rootScope.username = null;
                $rootScope.authorities = null;
                $rootScope.userId = null;
            },

            getUserState: function() {
                console.log("UserService.getUserGifs");
                if ( !userState ) {
                    userState = $http.get('/api/user').then(function (response) {
                        console.log(response);
                        $rootScope.credits = response.data.credits;
                        return response.data;
                    });
                  return userState;
                }
                return userState;
            },
            invalidateUserState: function() {
                userState = null;
            },

            getUserGifs: function () {
                console.log("UserService.getUserGifs");
                if ( !userGifs ) {
                    userGifs = $http.get('/api/user/gifs').then(function (response) {
                        console.log(response);
                        return response.data;
                    });
                  return userGifs;
                }
                return userGifs;
            },
            invalidateUserGifs: function () {
                userGifs = null;
            },
        };
    })

    .factory('challengeService', function ($http) {
        var promise;
        var current;
        var myService = {
            async: function() {
                if ( !promise ) {
                  // $http returns a promise, which has a then function, which also returns a promise
                    promise = $http.get('/pub/challenge').then(function (response) {
                    // The then function here is an opportunity to modify the response
                        console.log(response);
                        // The return value gets picked up by the then in the controller.
                        current = response.data.current;
                        return response.data;
                    });
                  // Return the promise to the controller
                  return promise;
                }
                return promise;
            },
            invalidateChallenges: function() {
                promise = null;
            },
            currentChallenge: function() {
                return current;
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
        })

    .factory('gifSubmissionService', function ($http) {
        var promise;
        var myService = {
            async: function(challenge, gif) {
              // $http returns a promise, which has a then function, which also returns a promise
                var promise = $http.post('/api/submission/' + challenge.id, {'gifId': gif.id}).then(function (response) {
                    // The then function here is an opportunity to modify the response
                    console.log(response);
                    // The return value gets picked up by the then in the controller.
                    return response.data;
              });
              // Return the promise to the controller
              return promise;

            }
        };
        return myService;
    })

    .factory('votingService', function ($http) {
            var promise;
            var myService = {
                getSubmissionsForVoting: function(challenge) {
                    var promise = $http.get('/api/challenge/' + challenge.id).then(function (response) {
                        console.log(response);
                        return response.data;
                    });
                    return promise;
                },
                castVote : function(challengeId, gif) {
                    var promise = $http.post('/api/challenge/' + challengeId, {'gifId': gif.id}).then(function (response) {
                        console.log(response);
                        return response.data;
                    });
                    return promise
                }
            };
            return myService;
        });
