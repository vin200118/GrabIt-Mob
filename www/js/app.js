// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('starter', ['ionic','starter.controllers','starter.homeController','starter.forgotPasswordController','starter.services','starter.emailServices','starter.searchController','starter.postController','starter.postService'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    if(window.cordova && window.cordova.plugins.Keyboard) {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);

      // Don't remove this line unless you know what you are doing. It stops the viewport
      // from snapping when text inputs are focused. Ionic handles this internally for
      // a much nicer keyboard experience.
      cordova.plugins.Keyboard.disableScroll(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
})
.config(function($stateProvider, $urlRouterProvider,$httpProvider) {
  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js

  $stateProvider
    .state('app', {
      url: '/app',
      abstract: true,
      templateUrl: 'templates/menu.html',
      controller: 'SideBarCtrl'
    })

  .state('login', {
       url: '/login',

       templateUrl: 'templates/login.html',
       controller: 'LoginCtrl',
       params:{'message': null}
   })

   .state('app.home', {
        url: '/home',
     views: {
       'menuContent': {
         templateUrl: 'templates/home.html',
         controller: 'HomeCtrl'
       }
     }
    })

    .state('forgotPassword', {
      url: '/forgot-password',
      templateUrl: 'templates/forgot_password.html',
      controller: 'ForgotPasswordCtrl'

    })

    .state('app.post', {
      url: '/add-post',
      views: {
        'menuContent': {
          templateUrl: 'templates/add_post.html',
          controller: 'PostCtrl'
        }
      }

    })

    .state('app.search', {
         url: '/search/:categoryId',
      views: {
        'menuContent': {
          templateUrl: 'templates/search.html',
          controller: 'SearchCtrl'
        }
      }
     })

     .state('app.card', {
          url: '/card/:id',
       views: {
         'menuContent': {
           templateUrl: 'templates/card.html',
           controller: 'SearchCardCtrl'
         }
       }
      })


   // if none of the above states are matched, use this as the fallback
   $urlRouterProvider.otherwise('/login');

 });
