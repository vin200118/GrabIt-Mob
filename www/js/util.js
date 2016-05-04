
var showMessage = function(message, miliseconds){
  var $toastContent = $(message);
  if(miliseconds== undefined){
    miliseconds =2000;
  }
    Materialize.toast($toastContent,miliseconds);
}


var baseUrl = function(){
 return 'http://192.168.2.144:8080/GrabIt/';
  //return 'https://osi-grabit.herokuapp.com/';
}
