
InitialSetting= new Object();

InitialSetting.showLogin = function(event)
{
 
  var loginPopup = event.getSource().findComponent("loginPopup");
  
  loginPopup.show({align:"end_after", alignId:"loginButton"});
  
  event.cancel();
}