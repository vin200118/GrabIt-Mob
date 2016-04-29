angular.module('starter.controllers', ['ngResource','starter.services'])

.controller('LoginCtrl',['$scope','$resource','$ionicPopup','LoginService','$state','$stateParams','$rootScope', function($scope, $resource,$ionicPopup,LoginService,$state ,$stateParams,$rootScope) {
    $scope.data = {};


  $rootScope.$on('$locationChangeSuccess', function () {
    console.log('$locationChangeSuccess changed!', new Date());

    var message = $stateParams.message;
    if(message != null){
      showMessage('<span>'+message+'</span>',5000);
    }
    $stateParams.message=null;
  });


    $scope.login = function() {

       LoginService.loginUser($scope.data.username, $scope.data.password).success(function(data) {
            var User = $resource(baseUrl()+'user/login',null,{
                'save' : { method:'POST'}
              });

              var response = User.save($scope.data)
              .$promise.then(function(response) {
                if(response != undefined && response.statusCode == "200"){
                      $state.go("home");
                }else if(response != undefined && response.statusCode == "401"){
                  showMessage('<span>Please check your credentials!</span>');
                }
            }, function(error) {

                console.log(error);
            });
          console.log("welcome");
       }).error(function(data) {
         showMessage('<span>Username and Password both are required!</span>');

       });

       var success = function(response) {
  console.log('user was saved');
};

var failure = function(error) {
  console.log('user was NOT saved');
};

    }

  }])
