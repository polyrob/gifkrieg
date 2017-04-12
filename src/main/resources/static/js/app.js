angular.module('gifkrieg', ['ngRoute'])

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


var getDisplayDateTimeForEpoch = function (epoch) {
    return new Date(epoch).toDateString();
};
