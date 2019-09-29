var app = angular.module('wiki', [ 'ngSanitize', 'ngRoute' ])
var git_hub_oauth = "https://github.com/login/oauth/authorize";
var client_id = "b314bcc6a0549cf9acbe";
var client_secret;
var client_scopes = "repo";
var auth_code;

var gitController = app.controller('wiki_git', function($scope, $http,
		$location) {
	$scope.title = "Wiki";

	getClientSecret();
	getAuthCode();
	getAccessToken();

	function getClientSecret() {
		if (sessionStorage.getItem("client_secret") == null) {
			var secret = prompt("Enter client secret");
			if (secret != null) {
				sessionStorage.setItem("client_secret", secret)
			}
			client_secret = sessionStorage.getItem("client_secret");
		}
	}

	function getAuthCode() {
		var error = window.location.href.match(/[&\?]error=([^&]+)/);
		if (error) {
			alert("Error getting auth code: " + error[1])
		}
		code = window.location.href.match(/[&\?]code=([\w\/\-]+)/);
		if (code) {
			auth_code = code[1]
		} else {
			auth_url = git_hub_oauth + "?client_id=" + client_id + "&scopes="
					+ client_scopes;
			window.location = auth_url;
		}
	}

	function getAccessToken() {
		if (auth_code != null) {

			data = JSON.stringify({
				"client_id" : client_id,
				"client_secret" : client_secret,
				"code" : auth_code
			});

			$http.post('https://github.com/login/oauth/access_token', data, {
				headers: { 'Content-Type': 'text/plain' }
				
			})
					.then(function successCallback(response) {
						alert("Got auth token " + response.data)
					}, function errorCallback(response) {
						alert("Error obtaining token " + response.statusText)
					});
		}
	}
});