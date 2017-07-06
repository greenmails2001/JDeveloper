var PvObject = new Object();
PvObject.selectedModule = null;
PvObject.ausId = null;
PvObject.packageCode = null;

// Nguoi dung lua chon click vao ung dung .
PvObject.selectApplication = function (evt) {
    PvObject.appCode = evt.getSource().getProperty("appCode");   
}

PvObject.testJInit = function() {
    var url = 'http://192.168.0.123/forms/frmservlet';
    window.open(url, 'win' + Math.random());
}


/**
 * http://192.168.0.123/forms/frmservlet?config=vinhdv_PERP
 *    &otherparams=p_root_app_name=FIN_MAIN+p_aus_id=69+p_gus_id+108+p_initial_module=FIN11
 */
PvObject.openModule = function (evt) {
    if (PvObject.selectedModule === null) {
        alert('Please select Module to Open');
        return;
    }
    var url = 'http://192.168.0.123/forms/frmservlet?config=' + PvObject.sasUserName 
            + "_PERP" + '&otherparams=p_root_app_name=' + PvObject.packageCode 
            + '+p_aus_id=' + PvObject.ausId 
            + '+p_gus_id=' + PvObject.gusId 
            + '+p_initial_module=' + PvObject.selectedModule;
    window.open(url, 'win' + Math.random());
}

PvObject.dbclickOpenModule= function(evt) {
    PvObject.moduleSelection(evt);
    PvObject.openModule(evt);
}

PvObject.moduleSelection = function (evt) {
    PvObject.selectedModule = evt.getSource().getProperty("moduleId");
    PvObject.ausId = evt.getSource().getProperty("ausId");
    PvObject.sasUserName = evt.getSource().getProperty("sasUserName");   
    PvObject.packageCode= evt.getSource().getProperty("packageCode");  
    PvObject.gusId=evt.getSource().getProperty("gusId");  
}

// Setting Bank Account .
PvObject.settingBankAccount = function (evt) {
    var url = evt.getSource().getProperty('bankFormURL');
    window.open(url, 'win' + Math.random());
}
