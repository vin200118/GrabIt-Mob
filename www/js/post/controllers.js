angular.module('starter.postController', ['ngResource','starter.postService','ionic', 'ngCordova'])

.controller('PostCtrl',['$scope','$resource','$ionicPopup','PostService','$state','$stateParams','$rootScope','$cordovaCamera', function($scope, $resource,$ionicPopup,PostService,$state ,$stateParams,$rootScope,$cordovaCamera) {
    $scope.data = {};
   $.cloudinary.config({ cloud_name: 'vinayak118', api_key: '278713757411919'});

   $('.upload_field').unsigned_cloudinary_upload("zcudy0uz",
  { cloud_name: 'demo', tags: 'browser_uploads' },
  { multiple: true }
).bind('cloudinarydone', function(e, data) {

  $('.thumbnails').append($.cloudinary.image(data.result.public_id,
    { format: 'jpg', width: 150, height: 100,
      crop: 'thumb', gravity: 'face', effect: 'saturation:50' } ))}

).bind('cloudinaryprogress', function(e, data) {

  $('.progress_bar').css('width',
    Math.round((data.loaded * 100.0) / data.total) + '%');

});

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
            var Post = $resource(baseUrl()+'post',null,{
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
      var category = $resource(baseUrl()+'category', null, {
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
        var subCategory = $resource(baseUrl()+'category/:categoryId', {categoryId: selectedCategoryId}, {
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

      $scope.takePhoto = function () {
                 var options = {
                   quality: 75,
                   destinationType: Camera.DestinationType.DATA_URL,
                   sourceType: Camera.PictureSourceType.CAMERA,
                   allowEdit: true,
                   encodingType: Camera.EncodingType.JPEG,
                   targetWidth: 300,
                   targetHeight: 300,
                   popoverOptions: CameraPopoverOptions,
                   saveToPhotoAlbum: false
               };

                   $cordovaCamera.getPicture(options).then(function (imageData) {

                        $scope.imgURI = "data:image/jpeg;base64," + imageData;
                   }, function (err) {
                     alert(err);
                       // An error occured. Show a message to the user
                   });
               }

               $scope.choosePhoto = function () {
                 var options = {
                   quality: 75,
                   destinationType: Camera.DestinationType.DATA_URL,
                   sourceType: Camera.PictureSourceType.PHOTOLIBRARY,
                   allowEdit: true,
                   encodingType: Camera.EncodingType.JPEG,
                   targetWidth: 300,
                   targetHeight: 300,
                   popoverOptions: CameraPopoverOptions,
                   saveToPhotoAlbum: false
               };

                   $cordovaCamera.getPicture(options).then(function (imageData) {
                     $scope.imgURI = "data:image/jpeg;base64," + imageData;

                   }, function (err) {
                     alert(err);
                       // An error occured. Show a message to the user
                   });
               }

}]);
