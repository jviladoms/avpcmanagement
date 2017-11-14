/**
 * Created by Jordi on 21/01/2017.
 */
angular.module('avpc', [])
    .controller('services', function($scope, $http) {
        $http.get('http://localhost:8080/service').
        then(function(response) {
            $scope.servicearray = response.data;
        });
    }).config(function($locationProvider) { $locationProvider.html5Mode(true); });

