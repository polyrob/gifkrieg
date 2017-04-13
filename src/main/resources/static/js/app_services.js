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
        });

//
//        var data = {};
//
//        // think about caching with  $http.get('/pub/challenge', {cache: true})
//        $http.get('/pub/challenge').then(function (response) {
//            data.current = response.data.current;
//            data.past = response.data.past;
//            data.voting = response.data.voting;
//            data.votingSubmissions = response.data.votingSubmissions;
//            data.currentSubmissions = response.data.currentSubmissions;
//            data.completedVotes = response.data.completedVotes;
//
//            data.current.startTime = getDisplayDateTimeForEpoch(data.current.startTime);
//            data.current.endTime = getDisplayDateTimeForEpoch(data.current.endTime);
//            data.past.forEach(function (challenge) {
//                challenge.startTime = getDisplayDateTimeForEpoch(challenge.startTime);
//                challenge.endTime = getDisplayDateTimeForEpoch(challenge.endTime);
//            });
//        })
//
//
//        return {
//            getChallenge: function () {
//                return data;
//            },
//            setProperty: function (value) {
//                property = value;
//            }
//        };
