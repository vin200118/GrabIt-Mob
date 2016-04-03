angular.module('starter.emailServices', [])
.service('EmailService', function($q) {
    return {
        validateEmail: function(email) {
            var deferred = $q.defer();
            var promise = deferred.promise;

            if (email != '') {
                deferred.resolve('Welcome ' + name + '!');
            } else {
                deferred.reject('Email Id is invalid.');
            }
            promise.success = function(fn) {
                promise.then(fn);
                return promise;
            }
            promise.error = function(fn) {
                promise.then(null, fn);
                return promise;
            }
            return promise;
        }
    }
})
