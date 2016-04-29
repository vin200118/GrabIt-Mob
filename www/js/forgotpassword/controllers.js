angular.module('starter.forgotPasswordController', ['ngResource','starter.emailServices'])

.controller('ForgotPasswordCtrl',['$scope','$parse','$resource','$ionicPopup','EmailService','$state', function($scope,$parse, $resource,$ionicPopup,EmailService,$state ) {
  $scope.data = {};
  $scope.errorMessage = false;
  $scope.messageText = "";
  var serverMessage = $parse('myForm.email.$error.serverMessage');
  $scope.change = function(){
    $scope.myForm.$setValidity("email", true, $scope.myForm);
    serverMessage.assign($scope, "");
  }


    $scope.forgotPassword = function() {

      EmailService.validateEmail($scope.data.email).success(function (data) {

        var User1 = $resource(baseUrl()+'email/forgot-password?toAddress=:toAddress', {toAddress: $scope.data.email}, {
          'get': {method: 'GET'}
        });

        var serverMessage = $parse('myForm.email.$error.serverMessage');
        User1.get()
          .$promise.then(function (response) {
          console.log(response.message);
          if(response.statusCode == "200"){
           var params = { 'message': response.message};
            $state.go("login",params);
          }else{
            $scope.myForm.$setValidity("email", false, $scope.myForm);
            serverMessage.assign($scope, response.message);


          }

        }, function (error) {

          console.log(error);
        });
      }).error(function (data) {
        var alertPopup = $ionicPopup.alert({
          title: 'Login failed!',
          template: 'Please check your credentials!'
        });
      });
    }

  }]);
