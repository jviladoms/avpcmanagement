/**
 * Created by Jordi on 21/01/2017.
 */
angular.module('avpc', [])
    .controller('members', function($scope, $http) {
        $http.get('http://localhost:8080/members').
        then(function(response) {
            $scope.membersarray = response.data;
        });
    }).config(function($locationProvider) { $locationProvider.html5Mode(true); });

