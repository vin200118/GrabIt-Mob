angular.module('starter.postController', ['ngResource','starter.postService','ionic', 'ngCordova'])
  .controller('PostCtrl',['$scope','$resource','$ionicPopup','PostService','$state','$stateParams','$rootScope','$cordovaCamera','$window', function($scope, $resource,$ionicPopup,PostService,$state ,$stateParams,$rootScope,$cordovaCamera,$window) {
    $scope.data = {};

    if(sessionStorage.getItem("data") != undefined){
      console.log(sessionStorage.getItem("data"));
      $scope.data = JSON.parse(sessionStorage.getItem("data"));
      $scope.data.image=$scope.data.imagePath;
      convertStingToDate($scope.data.contactNumber);
      //$("#dateOfPurchase").val($scope.data.dateOfPurchase);
      $scope.newDate =new Date($scope.data.dateOfPurchase);
      $("#selectCategory").val($scope.data.categoryId);
      changeCategory($scope.data.categoryId);
      $("#subCategory").val($scope.data.subCategoryId);
      $("#categoryLabel").addClass("dropdownCss");
      $("#subcategoryLabel").addClass("dropdownCss");
      var $input = $('.datepicker').pickadate();
      var picker = $input.pickadate('picker');
      picker.set('select', $scope.newDate)
      $('label').addClass('active');
      $("#submitPostBtn").text("Update Post");
    }
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
    if(localStorage.id != undefined){
      $scope.data.userId = localStorage.id;
    }
    console.log("in post controller");
    $scope.submitPost = function(){
      $scope.data.dateOfPurchase=$("#dateOfPurchase").val();
     $scope.data.categoryId=$("#selectCategory").val();
     $scope.data.subCategoryId=$("#subCategory").val();
      contactEmail=$("#contactEmail").val();
      PostService.validatePost($scope.data).success(function(data) {
        var methodName = 'POST'
        if($scope.data.id != undefined){
          if($scope.data.image != null && $scope.data.image !="" && $scope.data.image != undefined && $scope.data.image.indexOf("data:image/jpeg;base64") == -1){
            $scope.data.image="";
          }
          methodName = 'PUT'
        }
      console.log("submitPost..."+JSON.stringify($scope.data));
        var Post = $resource(baseUrl()+'post',null,{
          'save' : { method:methodName}
        });

        var response = Post.save($scope.data)
          .$promise.then(function(response) {
            if(response != undefined && response.statusCode == "201"){
              console.log("post created...");
              showMessage('<span>'+response.message+'</span>');
              $window.location.href="#/home";
              //  $state.go("search");
            }else if(response != undefined && response.statusCode == "200"){
              console.log("post updated...");
              showMessage('<span>'+response.message+'</span>');
              $window.location.href="#/app/mypost/"+$scope.data.userId;
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
      changeCategory();
    });

    $scope.takePhoto = function () {
      var options = {
        quality: 100,
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
        $scope.data.image = "data:image/jpeg;base64," + imageData;
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
        $scope.data.image = "data:image/jpeg;base64," + imageData;

      }, function (err) {
        alert(err);
        // An error occured. Show a message to the user
      });
    }

   function changeCategory(categoryId){
      console.log("selected value print here..."+$('#selectCategory').val());
     var selectedCategoryId=null;
     if(categoryId == undefined) {
       selectedCategoryId = $('#selectCategory').val();
     }else{
       selectedCategoryId = categoryId
     }

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
    }

    function convertStingToDate(phoneNumber){
      $scope.data.contactNumber=parseInt(phoneNumber);
    }

  }]);
