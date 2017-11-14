/**
 * Created by Jordi on 21/01/2017.
 */
angular.module('avpc', [])
    .controller('member', function($scope, $http ,$location) {
        $http.get('http://localhost:8080/members/' + $location.search().memberId).
        then(function(response) {
            $scope.memberData = response.data;
        });
    });