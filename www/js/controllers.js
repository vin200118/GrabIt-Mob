angular.module('starter.controllers', ['ngResource','starter.services'])

.controller('LoginCtrl',['$scope','$resource','$ionicPopup','LoginService','$state', function($scope, $resource,$ionicPopup,LoginService,$state ) {
    $scope.data = {};
    $scope.login = function() {

      LoginService.loginUser($scope.data.username, $scope.data.password).success(function(data) {
            var User = $resource('http://192.168.2.144:8080/GrabIt/user/login',null,{
                'save' : { method:'POST'}
              });
              var response = User.save($scope.data)
              .$promise.then(function(response) {
                if(response != undefined && response.errorCode == "200"){
                      $state.go("home");
                }else if(response != undefined && response.errorCode == "401"){
                  var alertPopup = $ionicPopup.alert({
                      title: 'Login failed!',
                      template: 'Please check your credentials!'
                  });
                }
            }, function(error) {

                console.log(error);
            });

            //  if(response != undefined && response.errorCode == "200"){
            //     $state.go("/home");
            //  }
              console.log("welcome");
       }).error(function(data) {
           var alertPopup = $ionicPopup.alert({
               title: 'Login failed!',
               template: 'Please check your credentials!'
           });
       });
       var success = function(response) {
  console.log('user was saved');
};

var failure = function(error) {
  console.log('user was NOT saved');
};

    }

    }])
    .controller('HomeCtrl',['$scope','$resource','$ionicPopup','$state', function($scope, $resource,$ionicPopup,$state ) {


     }])
