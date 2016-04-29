angular.module('starter.homeController', ['ngResource'])

.controller('HomeCtrl',['$scope','$resource','$ionicPopup','$state', function($scope, $resource,$ionicPopup,$state ) {
    $scope.data = {};
    $(".button-collapse").sideNav();
    $scope.loginn = function() {

      console.log("vinayak");

    }

  }]);
