/**
 * Created by Jordi on 21/01/2017.
 */
angular.module('avpc', [])
    .controller('service', function($scope, $http ,$location) {
        $http.get('http://localhost:8080/servei/' + $location.search().serveiId).
        then(function(response) {
            $scope.serveiData = response.data;
        });
    });