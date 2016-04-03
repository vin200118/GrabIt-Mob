angular.module('starter.postController', ['ngResource','starter.postService'])

.controller('PostCtrl',['$scope','$resource','$ionicPopup','PostService','$state','$stateParams','$rootScope', function($scope, $resource,$ionicPopup,PostService,$state ,$stateParams,$rootScope) {
    $scope.data = {};
$('select').material_select();
$('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15, // Creates a dropdown of 15 years to control year
    closeOnSelect: true,
    format: 'dd-mm-yyyy',
  formatSubmit: 'yyyy/mm/dd'
  });
console.log("in post controller");

      var category = $resource('http://192.168.26.1:8080/GrabIt/category', null, {
        'get': {method: 'GET',isArray:true}
      });
      category.get()
          .$promise.then(function (response) {
          console.log(JSON.stringify(response));
              var items=JSON.parse(JSON.stringify(response));
              $.each(items, function (i, item) {
                    $('#selectCategory').append($('<option>', {
                        value: item.id,
                        text : item.name
                    }));
              });

                  $('select').material_select();
        }, function (error) {

          console.log(error);
        });

        $('#selectCategory').on('change',function() {
        console.log("selected value print here..."+$('#selectCategory').val());
        var selectedCategoryId = $('#selectCategory').val();
        var subCategory = $resource('http://192.168.26.1:8080/GrabIt/category/:categoryId', {categoryId: selectedCategoryId}, {
        'get': {method: 'GET',isArray:true}
        });

        subCategory.get()
        .$promise.then(function (response) {
        console.log(JSON.stringify(response));
            var items=JSON.parse(JSON.stringify(response));
            if(items.length== 0){
              $('#subCategory')
            .find('option')
            .remove()
            .end()
            .append('<option value="" disabled selected>Choose your option</option>');
            }
            $.each(items, function (i, item) {
                  $('#subCategory').append($('<option>', {
                      value: item.id,
                      text : item.name
                  }));
            });

                $('select').material_select();
        }, function (error) {

        console.log(error);
        });
      });

}]);
