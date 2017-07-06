var PvObject = new Object();
PvObject.menuName = null;
PvObject.ausId = null;
PvObject.packageCode = null;
PvObject.subscriptionCode = null;// Xem bang Subscriptions cot Activated_Code . (Can doi thanh Subscription_Code)
PvObject.appHost = null;
PvObject.appPort = null;

PvObject.testJInit = function() {
    var url = 'http://192.168.0.123/forms/frmservlet';
    window.open(url, 'win' + Math.random());
}


/**
 * http://192.168.0.123/forms/frmservlet?config=vinhdv_PERP
 *    &otherparams=p_root_app_name=FIN_MAIN+p_aus_id=69+p_gus_id+108+p_initial_module=FIN11
 */
PvObject.openModule = function (evt) {
    if (PvObject.menuName === null) {
        alert('Please select Module to Open');
        return;
    }
    var url = 'http://'+PvObject.appHost+':'+PvObject.appPort+'/forms/frmservlet?config=' 
            + PvObject.subscriptionCode 
            + "_PERP" + '&otherparams=p_root_app_name=' + PvObject.packageCode 
            + '+p_aus_id=' + PvObject.ausId 
            + '+p_gus_id=' + PvObject.gusId 
            + '+p_initial_module=' + PvObject.menuName;
    //alert(url);        
    window.open(url, 'win' + Math.random());
}

PvObject.dbclickOpenModule= function(evt) {	
    PvObject.moduleSelection(evt);
    PvObject.openModule(evt);
}

PvObject.moduleSelection = function (evt) {
    PvObject.menuName = evt.getSource().getProperty("menuName");
    PvObject.ausId = evt.getSource().getProperty("ausId");
    PvObject.subscriptionCode = evt.getSource().getProperty("subscriptionCode");   
    PvObject.packageCode= evt.getSource().getProperty("packageCode");  
    PvObject.gusId=evt.getSource().getProperty("gusId"); 
    PvObject.appHost = evt.getSource().getProperty("appHost"); 
    PvObject.appPort =evt.getSource().getProperty("appPort");  
    /*alert('PvObject.menuName='+PvObject.menuName
       +' PvObject.ausId='+PvObject.ausId
       +' PvObject.appHost='+PvObject.appHost
       +' PvObject.appPort='+ PvObject.appPort);//*/
}

