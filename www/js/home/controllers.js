angular.module('starter.homeController', ['ngResource'])

.controller('HomeCtrl',['$scope','$resource','$ionicPopup','$state','$ionicSideMenuDelegate','$window', function($scope, $resource,$ionicPopup,$state,$ionicSideMenuDelegate ,$window) {
    $scope.data = {};
alert("home :"+localStorage.id);
  if(localStorage.id == undefined){
    $state.go("login");
  }
    $scope.loginn = function() {

      console.log("vinayak");

    }

  }]);
