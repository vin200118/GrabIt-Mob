angular.module('starter.homeController', ['ngResource'])

.controller('HomeCtrl',['$scope','$resource','$ionicPopup','$state','$ionicSideMenuDelegate', function($scope, $resource,$ionicPopup,$state,$ionicSideMenuDelegate ) {
    $scope.data = {};


    $scope.loginn = function() {

      console.log("vinayak");

    }

  }]);
