angular.module('starter.homeController', ['ngResource'])

.controller('HomeCtrl',['$scope','$resource','$ionicPopup','$state', function($scope, $resource,$ionicPopup,$state ) {
    $scope.data = {};
    $scope.loginn = function() {

      console.log("vinayak");

    }

  }]);
