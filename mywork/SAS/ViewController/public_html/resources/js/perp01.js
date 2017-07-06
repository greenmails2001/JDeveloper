var Perp01Script = new Object();
Perp01Script.selectedModule = null;
Perp01Script.ausId = null;
Perp01Script.packageCode = null;

// Nguoi dung lua chon click vao ung dung .
Perp01Script.selectApplication = function (evt) {
    Perp01Script.appCode = evt.getSource().getProperty("appCode");   
}


Perp01Script.testJInit = function() {
    var url = 'http://192.168.0.123/forms/frmservlet';
    window.open(url, 'win' + Math.random());
}


/**
 * http://192.168.0.123/forms/frmservlet?config=vinhdv_PERP
 *    &otherparams=p_root_app_name=FIN_MAIN+p_aus_id=69+p_gus_id+108+p_initial_module=FIN11
 */
Perp01Script.openModule = function (evt) {
    if (Perp01Script.selectedModule === null) {
        alert('Please select Module to Open');
        return;
    }
    var url = 'http://192.168.0.123/forms/frmservlet?config=' + Perp01Script.sasUserName 
            + "_PERP" + '&otherparams=p_root_app_name=' + Perp01Script.packageCode 
            + '+p_aus_id=' + Perp01Script.ausId 
            + '+p_gus_id=' + Perp01Script.gusId 
            + '+p_initial_module=' + Perp01Script.selectedModule;
    window.open(url, 'win' + Math.random());
}

Perp01Script.dbclickOpenModule= function(evt) {
    Perp01Script.moduleSelection(evt);
    Perp01Script.openModule(evt);
}

Perp01Script.moduleSelection = function (evt) {
    Perp01Script.selectedModule = evt.getSource().getProperty("moduleId");
    Perp01Script.ausId = evt.getSource().getProperty("ausId");
    Perp01Script.sasUserName = evt.getSource().getProperty("sasUserName");   
    Perp01Script.packageCode= evt.getSource().getProperty("packageCode");  
    Perp01Script.gusId=evt.getSource().getProperty("gusId");  
}

// Setting Bank Account .
Perp01Script.settingBankAccount = function (evt) {
    var url = evt.getSource().getProperty('bankFormURL');
    window.open(url, 'win' + Math.random());
}
