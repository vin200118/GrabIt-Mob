angular.module('starter.controllers', ['ngResource','starter.services'])

.controller('LoginCtrl',['$scope','$resource','$ionicPopup','LoginService','$state','$stateParams','$rootScope','$window', function($scope, $resource,$ionicPopup,LoginService,$state ,$stateParams,$rootScope,$window) {
    $scope.data = {};

  if(localStorage.id != undefined){
    $state.go("app.home");
  }

  $rootScope.$on('$locationChangeSuccess', function () {
    console.log('$locationChangeSuccess changed!', new Date());

    if(localStorage.id == undefined){
      $state.go("login");
    }

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
                  localStorage.setItem("id",response.details.id);
                  console.log(localStorage.length);
                  console.log(localStorage.id)
                      $state.go("app.home");
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

}]).controller('SideBarCtrl',['$scope','$parse','$resource','$ionicPopup','$state','$stateParams','$window', function($scope,$parse,$resource,$ionicPopup,$state,$stateParams,$window ) {

  console.log("side bar controller...");
  $scope.logout = function() {
    localStorage.removeItem("id");
    $state.go("login");
  };

  $scope.mypost= function() {

    $state.go("app.mypost");
  };


}])

