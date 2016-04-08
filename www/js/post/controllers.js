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
  $scope.data.type = 'S';
  $scope.data.condition = 'U';
console.log("in post controller");
      $scope.submitPost = function(){
        $scope.data.dateOfPurchase=$("#dateOfPurchase").val();
        $scope.data.categoryId=$("#selectCategory").val();
        $scope.data.subCategoryId=$("#subCategory").val();
      PostService.validatePost($scope.data).success(function(data) {
            console.log("submitPost..."+JSON.stringify($scope.data));
            var Post = $resource('http://192.168.26.1:8080/GrabIt/post',null,{
                'save' : { method:'POST'}
              });

              var response = Post.save($scope.data)
              .$promise.then(function(response) {
                if(response != undefined && response.statusCode == "201"){
                      console.log("post created");
                      showMessage('<span>'+response.message+'</span>');
                      //  $state.go("search");
                }
            }, function(error) {

                console.log(error);
            });


        }).error(function(error) {
          showMessage('<span>'+error+'</span>');
       });

      }
      var category = $resource('http://192.168.26.1:8080/GrabIt/category', null, {
        'get': {method: 'GET',isArray:true}
      });
  $scope.validateFields = function(data){
      if(data.selectCategory == null){
          showMessage('<span>category is empty</span>',5000);

          return false;
      }
  }
      $scope.conditionChange = function(value){

        $scope.data.condition = value;
        console.log(value);
      }

      $scope.typeChange = function(value){

        $scope.data.type = value;
        console.log(value);
      }
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
