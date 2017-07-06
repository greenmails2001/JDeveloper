package com.vc.web.util;

import com.vc.web.ejb.perp.DynamicPERPFacadeLocal;
import com.vc.web.ejb.sas.SASFacadeLocal;

import javax.faces.FacesException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 * @author vha 13-8-2009 .
 */
public class EJBUtil {
    private EJBUtil() {
    }

    public static Context getContext() throws NamingException {
        return new InitialContext();
    }

    public static SASFacadeLocal lockupSASFacade() {
        Context context = null;
        try {
            context = getContext();
            return (SASFacadeLocal)context.lookup("ejb/local/SASFacade");
        } catch (NamingException e) {
            throw new FacesException(e.getMessage());
        }
    }

    public static DynamicPERPFacadeLocal lockupDynamicPERPFacade()  {
        Context context=null;
        try {
            context = getContext();
            return (DynamicPERPFacadeLocal)context.lookup("ejb/local/DynamicPERPFacade");
        } catch (NamingException e) {
            throw new FacesException(e.getMessage());
        }        
    }
}
