var LoginController = function(scope, window, login) {
    scope.login = function () {
        login.login(scope.username, scope.password)
            .success(scope.onLoginSuccess)
            .error(scope.onLoginError);
    };

    scope.onLoginSuccess = function () {
        scope.error = false;
        window.location.href = "./";
    };

    scope.onLoginError = function (error) {
        scope.error = true;
        window.alert("error");
    };
};

LoginController.$inject = ["$scope", "$window", "login"];

module.exports = LoginController;