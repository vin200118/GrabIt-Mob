angular.module('starter.postService', [])
.service('PostService', function($q) {
    return {
        validatePost: function(data) {
            var deferred = $q.defer();
            var promise = deferred.promise;

           if (data.categoryId == null) {
                  deferred.reject('Category should be selected');
            } else if(data.subCategoryId == null) {
                    deferred.reject('SubCategory should be selected');
            }else if(data.title == null && data.title == "" ) {
                    deferred.reject('Title should not be empty');
            }else if(data.price == null) {
                    deferred.reject('Price should not be empty');
            }else if(data.description == null) {
                    deferred.reject('Description should not be empty');
            }else if(data.dateOfPurchase == null || data.dateOfPurchase == "") {
                    deferred.reject('Date of purchase should not be empty');
            }else if(data.contactName == null) {
                    deferred.reject('contactName should not be empty');
            }else if(data.contactNumber == null) {
                    deferred.reject('contactNumber should not be empty');
            }else{
                deferred.resolve('Welcome');
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
