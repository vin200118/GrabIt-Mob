
var showMessage = function(message, miliseconds){
  var $toastContent = $(message);
  if(miliseconds== undefined){
    miliseconds =2000;
  }
    Materialize.toast($toastContent,miliseconds);
}
