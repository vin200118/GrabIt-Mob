angular.module('starter.searchController', ['ngResource'])

.controller('SearchCtrl',['$scope','$parse','$resource','$ionicPopup','$state','$stateParams', function($scope,$parse,$resource,$ionicPopup,$state,$stateParams ) {
console.log("search controller...");
$('#categoryId').val("0");
$('#subcategoryId').val("0");
$('#search').val("");
console.log($stateParams);

  $('#categoryId').on('change',function() {
  console.log("selected value print here..."+$('#categoryId').val());
  var categoryId = $('#categoryId').val();
  var subCategory = $resource(baseUrl()+'category/:categoryId', {categoryId: categoryId}, {
  'get': {method: 'GET',isArray:true}
  });

  subCategory.get()
  .$promise.then(function (response) {
  console.log(JSON.stringify(response));
      var items=JSON.parse(JSON.stringify(response));
      if(items.length== 0){
        $('#subcategoryId')
      .find('option')
      .remove()
      .end()
      .append('<option value="" disabled selected>Choose your option</option>');
      }
      $.each(items, function (i, item) {
            $('#subcategoryId').append($('<option>', {
                value: item.id,
                text : item.name
            }));
      });

          $('select').material_select();
  }, function (error) {

  console.log(error);
  });
  });

  var category = $resource(baseUrl()+'category', null, {
    'get': {method: 'GET',isArray:true}
  });

  category.get()
      .$promise.then(function (response) {
      console.log(JSON.stringify(response));
          var items=JSON.parse(JSON.stringify(response));
          $.each(items, function (i, item) {
                $('#categoryId').append($('<option>', {
                    value: item.id,
                    text : item.name
                }));
          });
              $('#categoryId').val($stateParams.categoryId);
              $("#categoryId").trigger("change");
              $('select').material_select();

    }, function (error) {

      console.log(error);
    });

  searchField= function(){

    var posts = $resource(baseUrl()+'post', null, {
      'get': {method: 'GET',isArray:true}
    });
    posts.get({"search":$('#search').val(),"categoryId":$('#categoryId').val(),"subCategoryId":$('#subcategoryId').val()})
        .$promise.then(function (response) {
        console.log(JSON.stringify(response));
            var items=JSON.parse(JSON.stringify(response));
            $('#postList').find('li').remove();
            $.each(items, function (i, item) {
              var condition;
              if(item.condition == "U"){
                condition = "Used"
              }else if(item.condition == "N"){
                condition = "New";
              }

                  $('#postList').append($('<li class="collection-item avatar">'+
                    '<img src="/img/splendar.jpg" alt="" class="circle"/>'+
                     '<a href="#/card/'+item.id+'"<span class="title">'+item.title+'</span>'+
                   '<p>Price :'+item.price+'/-</p>'+
                   '<p>Condition:'+condition+'</p></a></li>'));
            });


      }, function (error) {

        console.log(error);
      });


  }


  searchField();
  $('#search').on('change',function() {
      searchField();
  });
  $('#categoryId').on('change',function() {
      searchField();
  });
  $('#subcategoryId').on('change',function() {
      searchField();
  });

}]).controller('SearchCardCtrl',['$scope','$parse','$resource','$ionicPopup','$state','$stateParams', function($scope,$parse,$resource,$ionicPopup,$state,$stateParams ) {

$('#cardImg').attr("src","/img/maruti.jpg");

console.log("in card contrl..")

    var card = $resource(baseUrl()+'post/:id', {id: $stateParams.id}, {
    'get': {method: 'GET'}
    });

    card.get()
    .$promise.then(function (response) {
    console.log(JSON.stringify(response));
        var data=JSON.parse(JSON.stringify(response));
        $scope.data= data;
        if(data.condition == "U"){
            data.condition="Used";
        }else if(data.condition == "N"){
              data.condition="New"
        }

    }, function (error) {

      console.log(error);
    });
}])
