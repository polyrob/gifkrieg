angular.module('gifkrieg')

    .controller('ModalInstanceCtrl', function ($scope, $modalInstance, data) {

      $scope.data = data;
//      $scope.selected = {
//        item: $scope.items[0]
//      };

      $scope.ok = function () {
        $modalInstance.close($scope.selected.item);
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    })


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
            $http.post('logout', {}).then(function () {
                console.log("Doing logout.");
                UserService.logout();
                UserService.invalidateUserGifs();
                UserService.invalidateUserState();
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


    }).controller('challengeController', function ($scope, $rootScope, challengeService, UserService) {
        challengeService.async().then(function (data) {
            $scope.data = data;
        });

        if ($rootScope.authenticated) {
            UserService.getUserState().then(function (data) {
                $scope.hasSubmittedCurrent = data.hasSubmittedCurrent;
                $scope.hasVotedCurrent = data.hasVotedCurrent;
            });
        }


    }).controller('leaderboardController', function ($scope, $modal, leaderboardService) {
        leaderboardService.async().then(function (data) {
            $scope.data = data;
        });


        var modalInstance = $modal.open({
              templateUrl: 'myModalContent.html',
              controller: 'ModalInstanceCtrl',
              size: 'sm',
              resolve: {
                data: function () {
                  //return $scope.items;
                  return {title: "My Title",
                          content: ['a','b','c']}
                }
              }
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
                    challengeService.invalidateChallenges();

                    UserService.invalidateUserGifs();
                    $location.path("/");


                },
                function errorCallback(response) {
                    console.log("Failure with gif submission." + response.data);
                    alert(response.data);
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
                    //UserService.invalidateUserState();
                    challengeService.invalidateChallenges();
                    $location.path("/");
                    if (response.credits) {
                        $rootScope.credits += response.credits;
                        $.bootstrapGrowl(response.credits + ' credits received for voting!',{
                                    type: 'success',
                                    delay: 4000,
                                });
                    }
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

    }).controller('notify', function ($scope) {
         UserService.getUserGifs().then(function (data) {
              $scope.gifdeck = data;
         });
    });
