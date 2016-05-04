angular.module('starter.MyPostController', ['ngResource'])

.controller('MyPostCtrl',['$scope','$parse','$resource','$ionicPopup','$state','$stateParams', function($scope,$parse,$resource,$ionicPopup,$state,$stateParams ) {
console.log("mypost controller...");

console.log($stateParams);
  if(localStorage.id != undefined){
    $scope.userId = localStorage.id;
  }

  searchField= function(){

    var posts = $resource(baseUrl()+'post', null, {
      'get': {method: 'GET',isArray:true}
    });
    posts.get({"userId": $scope.userId})
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
                    '<img src="'+item.imagePath+'" alt="" class="circle"/>'+
                     '<a href="#/app/card/'+item.id+'"<span class="title">'+item.title+'</span>'+
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
