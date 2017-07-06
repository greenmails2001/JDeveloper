var Sas01Script = new Object();
Sas01Script.selectedModule = null;
Sas01Script.ausId = null;
Sas01Script.packageCode = null;

// Nguoi dung lua chon click vao ung dung .
Sas01Script.selectApplication = function (evt) {
    Sas01Script.appCode = evt.getSource().getProperty("appCode");   
}


SasO1Script.testJInit = function() {
    var url = 'http://192.168.0.123/forms/frmservlet';
    window.open(url, 'win' + Math.random());
}

/**
 * http://192.168.0.123/forms/frmservlet?config=vinhdv_PERP
 *    &otherparams=p_root_app_name=FIN_MAIN+p_aus_id=69+p_gus_id+108+p_initial_module=FIN11
 */
Sas01Script.openModule = function (evt) {
    if (Sas01Script.selectedModule === null) {
        alert('Please select Module to Open');
        return;
    }
    var url = 'http://192.168.0.123/forms/frmservlet?config=' + Sas01Script.sasUserName 
            + "_PERP" + '&otherparams=p_root_app_name=' + Sas01Script.packageCode 
            + '+p_aus_id=' + Sas01Script.ausId 
            + '+p_gus_id=' + Sas01Script.gusId 
            + '+p_initial_module=' + Sas01Script.selectedModule;
    window.open(url, 'win' + Math.random());
}

Sas01Script.dbclickOpenModule= function(evt) {
    Sas01Script.moduleSelection(evt);
    Sas01Script.openModule(evt);
}

Sas01Script.moduleSelection = function (evt) {
    Sas01Script.selectedModule = evt.getSource().getProperty("moduleId");
    Sas01Script.ausId = evt.getSource().getProperty("ausId");
    Sas01Script.sasUserName = evt.getSource().getProperty("sasUserName");   
    Sas01Script.packageCode= evt.getSource().getProperty("packageCode");  
    Sas01Script.gusId=evt.getSource().getProperty("gusId");  
}

// Setting Bank Account .
Sas01Script.settingBankAccount = function (evt) {
    var url = evt.getSource().getProperty('bankFormURL');
    window.open(url, 'win' + Math.random());
}
