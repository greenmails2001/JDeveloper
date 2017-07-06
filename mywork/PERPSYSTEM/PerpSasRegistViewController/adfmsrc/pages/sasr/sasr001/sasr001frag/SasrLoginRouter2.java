package pages.sasr.sasr001.sasr001frag;

import com.perp.beans.PerpSasrAccountChecking;
import com.perp.beans.SasrSessionLoginObject;
import com.perp.sasregistviewcontroller.utils.SasrFacesUtils;

public class SasrLoginRouter2 {
    public SasrLoginRouter2() {
    }

    /**
     * return 'create' hoac 'active' .
     * @return
     */
    public String getCaseFlow() {
        SasrSessionLoginObject sasrObject =
            SasrFacesUtils.getSasrSessionLoginObject();
        PerpSasrAccountChecking checkingObject =
            sasrObject.getPerpSasrAccountChecking();
        if (checkingObject == null) {
            return "create";
        }       
        return "active";
    }
}
