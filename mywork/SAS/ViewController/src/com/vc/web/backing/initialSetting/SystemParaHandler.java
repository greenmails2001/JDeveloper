package com.vc.web.backing.initialSetting;

import com.vc.web.PerpSystemInfo;
import com.vc.web.ejb.perp.DynamicPERPFacadeLocal;
import com.vc.web.ejb.perp.entities.SystemParas;
import com.vc.web.util.EJBUtil;
import com.vc.web.util.FacesUtil;
import com.vc.web.util.ViewUtil;


public class SystemParaHandler {
    public SystemParaHandler() {
    }


    public String saveEditSystemParaAction() {
        SystemParas spa = 
            (SystemParas)FacesUtil.resolveExpression("#{bindings.querySystemParasFindByUserPrivilegeChangableIterator.currentRow.dataProvider}");
        if (spa != null) {
            DynamicPERPFacadeLocal perpFacade;
            PerpSystemInfo psi = 
                ViewUtil.getPerpSessionLogin().getAppUserInfo().getPerpSystemInfo();
            try {
                perpFacade = EJBUtil.lockupDynamicPERPFacade();
                perpFacade.mergeSystemPara(psi, spa.getSpaId(), 
                                           spa.getValue());
            } catch (Exception e) {
                FacesUtil.addError("Error", e.getMessage());
            }
        }
        return null;
    }
}
