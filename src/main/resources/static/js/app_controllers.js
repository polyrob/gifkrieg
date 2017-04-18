angular.module('gifkrieg')
    .controller('navigation', function ($rootScope, $scope, $route, $http, $location, UserService) {
        var self = this;

        $rootScope.errors = [];
        $rootScope.hasError = false;

        self.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };

        var authenticate = function (credentials, callback) {
            var headers = credentials ? {
                authorization: "Basic " +
                    btoa(credentials.username + ":" +
                        credentials.password)
            } : {};
            console.log("Doing get in authenticate()");
            $http.get('auth/user', {
                headers: headers
            }).then(function (response) {
                if (response.data) {
                    $rootScope.authenticated = true;
                    UserService.fromUserDetails(response.data);
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback($rootScope.authenticated);
            }, function () {
                $rootScope.authenticated = false;
                callback && callback(false);
            });

        };

        authenticate();

        self.credentials = {};
        self.login = function () {
            authenticate(self.credentials, function (authenticated) {
                if (authenticated) {
                    console.log("Login succeeded");
                    $location.path("/");
                    self.error = false;
                    $rootScope.authenticated = true;
                } else {
                    console.log("Login failed");
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
        };

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
                        $location.path("/mydeck");
                    }
                });
        };


    }).controller('challengeController', function ($scope, challengeService, UserService) {
        challengeService.async().then(function (data) {
            $scope.data = data;
        });

        UserService.getUserState().then(function (data) {
            $scope.hasSubmittedCurrent = data.hasSubmittedCurrent;
            $scope.hasVotedCurrent = data.hasVotedCurrent;
        });


    }).controller('leaderboardController', function ($scope, leaderboardService) {
        leaderboardService.async().then(function (data) {
            $scope.data = data;
        });


    }).controller('submitGifController', function ($rootScope, $scope, $location, gifSubmissionService, challengeService, UserService) {
        UserService.getUserGifs().then(function (data) {
            $scope.gifdeck = data;
        });

        $scope.submitGif = function (gif) {
            console.log("submitting gif: " + gif.id);

            gifSubmissionService.async(challengeService.currentChallenge(), gif).then(
                function successCallback(response) {
                    console.log("Success! Gif submitted.");
//                    $rootScope.hasSubmittedCurrent = true;
                    UserService.invalidateUserState();
                    invalidateChallenges.invalidateChallenges();

                    UserService.invalidateUserGifs();
                    $location.path("/");


                },
                function errorCallback(response) {
                    console.log("Failure with gif submission." + response);
                    alert("Something went wrong with your submission");
                })
        };


    }).controller('votingController', function ($rootScope, $scope, $location, votingService, challengeService, UserService) {
        var challengeId;
        challengeService.async().then(function (data) {
            challengeId = data.voting.id
            $scope.voting = data.voting;
            votingService.getSubmissionsForVoting(data.voting).then(function (entries) {
                $scope.data = entries;
            });
        });

        $scope.castVote = function (submission) {
            votingService.castVote(challengeId, submission.gif).then(
                function successCallback(response) {
                    console.log("Success! Vote submitted.");
                    $rootScope.hasVotedCurrent = true;
                    UserService.invalidateUserState();
                    invalidateChallenges.invalidateChallenges();
                    $location.path("/");
                },
                function errorCallback(response) {
                    console.log("Failure with gif submission." + response);
                    alert("Something went wrong with your submission");
                })
        }

    }).controller('usergif', function ($scope, UserService) {
        UserService.getUserGifs().then(function (data) {
            $scope.gifdeck = data;
        });
    });
