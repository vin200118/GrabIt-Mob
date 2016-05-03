
var showMessage = function(message, miliseconds){
  var $toastContent = $(message);
  if(miliseconds== undefined){
    miliseconds =2000;
  }
    Materialize.toast($toastContent,miliseconds);
}


var baseUrl = function(){
 return 'http://192.168.26.1:8080/GrabIt/';
  //return 'https://osi-grabit.herokuapp.com/';
}
