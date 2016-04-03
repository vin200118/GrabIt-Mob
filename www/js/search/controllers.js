angular.module('starter.searchController', ['ngResource'])

.controller('SearchCtrl',['$scope','$parse','$resource','$ionicPopup','$state','$stateParams', function($scope,$parse,$resource,$ionicPopup,$state,$stateParams ) {
console.log("search controller...");
console.log($stateParams);

            var category = $resource('http://192.168.26.1:8080/GrabIt/category', null, {
              'get': {method: 'GET',isArray:true}
            });
            category.get()
                .$promise.then(function (response) {
                console.log(JSON.stringify(response));
                    $scope.categories=JSON.parse(JSON.stringify(response));
              }, function (error) {

                console.log(error);
              });

          var subCategory = $resource('http://192.168.26.1:8080/GrabIt/category/:categoryId', {categoryId: $stateParams.categoryId}, {
            'get': {method: 'GET',isArray:true}
          });

          subCategory.get()
            .$promise.then(function (response) {
            console.log(JSON.stringify(response));
                $scope.subCategories=JSON.parse(JSON.stringify(response));
          }, function (error) {

            console.log(error);
          });
  }]);
