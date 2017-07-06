package com.vc.web.ejb.perp;

import com.vc.web.PerpSystemException;
import com.vc.web.PerpSystemInfo;
import com.vc.web.SpaOun;
import com.vc.web.ejb.perp.entities.AppUsers;
import com.vc.web.ejb.perp.entities.OrganizationUnits;
import com.vc.web.ejb.perp.entities.SystemParas;
import com.vc.web.perp.GusInfo;
import com.vc.web.perp.ModuleInfo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

//TODO
//@Stateless(name = "DynamicPERPFacade")
public class DynamicPERPFacadeBean2 /* implements DynamicPERPFacade, 
                                              DynamicPERPFacadeLocal */ {
    /**
     * String : activatedCode 
     * PerpSystemInfo : 
     */
    private static Map<String, PerpSystemInfo> MAP_ACTIVE = 
        new TreeMap<String, PerpSystemInfo>();

    /** Danh sach cac Persistence chua duoc su dung */
    private static List<String> PERSISTENCES = new ArrayList<String>();
    static {
        for (int i = 1; i < 100; i++) {
            PERSISTENCES.add("PERPPersistence" + i);
        }
    }
    
    // PerpSystemInfo and Persistence Name .
    private static Map<PerpSystemInfo, String> MAP_PSI = 
        new TreeMap<PerpSystemInfo, String>();
    // PerpSystemInfo and EntityManager .
    private static Map<PerpSystemInfo, EntityManager> MAP_EM = 
         new TreeMap<PerpSystemInfo, EntityManager>();

    // ----------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------

    public DynamicPERPFacadeBean2() {

    }
    // ----------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------
    // DANH SACH CAC HAM PRIVATE SU DUNG TRONG CLASS .
    // ----------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------

    private EntityManager getEntityManager(PerpSystemInfo perpSystemInfo) {
        EntityManager em= MAP_EM.get(perpSystemInfo) ;
        if(em!=null) { 
            return em;
        }
        String activatedCode = perpSystemInfo.getActivatedCode();
        PerpSystemInfo psi = MAP_ACTIVE.get(activatedCode);
        if (psi != null && !psi.equals(perpSystemInfo)) {
            System.out.println("ActivatedCode:" + activatedCode + 
                               " -- Has change DB Info ....");
            System.out.println(" Old :" + psi.toString());
            System.out.println(" New :" + perpSystemInfo.toString());
            MAP_ACTIVE.put(activatedCode, perpSystemInfo);
        }
        String persName = MAP_PSI.get(perpSystemInfo);
        if (persName == null) {
            persName = PERSISTENCES.remove(0);
            MAP_PSI.put(perpSystemInfo, persName);
        }
        System.out.println("PERSISTENCE NAME :" + persName);
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("toplink.jdbc.driver", "oracle.jdbc.OracleDriver");
        properties.put("toplink.jdbc.url", 
                       perpSystemInfo.getConnectionString());
        properties.put("toplink.jdbc.user", perpSystemInfo.getDbUser());
        properties.put("toplink.jdbc.password", perpSystemInfo.getDbPwd());

        EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory(persName, properties);
        em = emf.createEntityManager();
        MAP_EM.put(perpSystemInfo,em);
        System.out.println("ENTITY MANAGER :" + em);
        return em;
    }

    private String getEncrytPassword(EntityManager em, String userName, 
                                     String password) {
        Query query = 
            em.createNativeQuery("Select APP_SECURE.encrypt_password(?,?)  from dual");
        query.setParameter(1, userName);
        query.setParameter(2, password);
        List<String> list = (List<String>)query.getSingleResult();
        String incPass = list.get(0);
        return incPass;

    }
    // ----------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------

    /** <code>select o from AppUsers o</code> */
    public List<AppUsers> queryAppUsersFindAll(PerpSystemInfo psi) {
        EntityManager em = this.getEntityManager(psi);
        return em.createNamedQuery("AppUsers.findAll").getResultList();
    }

    /** <code>select o from AppUsers o where o.userName= :userName and o.password=:password</code> */
    public AppUsers queryAppUsersFindByUserNameEncrytPassword(PerpSystemInfo psi, 
                                                              String userName, 
                                                              String password) {
        System.out.println("\n\n@info01:" + psi.toString());
        EntityManager em = this.getEntityManager(psi);
        String encrytPassword = this.getEncrytPassword(em, userName, password);
        Query query = 
            em.createNamedQuery("AppUsers.findByUserNameEncrytPassword");
        query.setParameter("userName", userName);
        query.setParameter("password", encrytPassword);
        List<AppUsers> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    private OrganizationUnits queryOrganizationUnitsFindRoot(EntityManager em) {
        Query query = 
            em.createNativeQuery("Select * from Organization_Units o where o.OUN_ID_CHILD_OF is null", 
                                 OrganizationUnits.class);
        List<OrganizationUnits> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<SpaOun> getSpaOunList(PerpSystemInfo psi) throws PerpSystemException {
        EntityManager em = this.getEntityManager(psi);
        String sql = 
            " Select * from system_paras spa " + " where spa.code in ('CO_NAME','CO_LOGO','CO_ADDRESS','CO_TAX_NO','CO_TAXFORM','CO_TEL_NO','CO_FAX_NO') " + 
            " order by spa.oun_id desc";
        Query query = em.createNativeQuery(sql, SystemParas.class);
        List<SystemParas> list1 = query.getResultList();
        List<SpaOun> list = new ArrayList<SpaOun>();
        SpaOun spaOun = null;
        Long ounId = null;
        for (SystemParas spa : list1) {
            OrganizationUnits oun = spa.getOrganizationUnits();
            String spaCode = spa.getCode();
            String value = spa.getValue();
            String desc = spa.getDescription();
            if (oun == null && spaOun == null) {

                spaOun = new SpaOun();
                OrganizationUnits rootOun = 
                    this.queryOrganizationUnitsFindRoot(em);
                if (rootOun != null) {
                    spaOun.setOunOunId(rootOun.getOunId());
                    spaOun.setOunOunNumber(rootOun.getOunNumber());
                    spaOun.setOunOunName(rootOun.getName());
                }
                list.add(spaOun);
            } else if (oun != null && oun.getOunId() != ounId) {
                spaOun = new SpaOun();
                spaOun.setOunOunId(oun.getOunId());
                spaOun.setOunOunNumber(oun.getOunNumber());
                spaOun.setOunOunName(oun.getName());
                list.add(spaOun);
                ounId = oun.getOunId();
            }

            spaOun.setOunId(oun == null ? null : oun.getOunId());

            if (SpaOun.KEY_CO_ADDRESS.equals(spaCode)) {
                spaOun.setSpaIdCoAddress(spa.getSpaId());
                spaOun.setCoAddress(value);
                spaOun.setCoAddressDesc(desc);
            } else if (SpaOun.KEY_CO_FAX_NO.equals(spaCode)) {
                spaOun.setSpaIdFaxNo(spa.getSpaId());
                spaOun.setCoFaxNo(value);
                spaOun.setCoFaxNoDesc(desc);
            } else if (SpaOun.KEY_CO_LOGO.equals(spaCode)) {
                spaOun.setSpaIdCoLogo(spa.getSpaId());
                spaOun.setCoLogo(value);
                spaOun.setCoLogoDesc(desc);
            } else if (SpaOun.KEY_CO_NAME.equals(spaCode)) {
                spaOun.setSpaIdCoName(spa.getSpaId());
                spaOun.setCoName(value);
                spaOun.setCoNameDesc(desc);
            } else if (SpaOun.KEY_CO_TAX_FORM.equals(spaCode)) {
                spaOun.setSpaIdTaxForm(spa.getSpaId());
                spaOun.setCoTaxForm(value);
                spaOun.setCoTaxFormDesc(desc);
            } else if (SpaOun.KEY_CO_TAX_NO.equals(spaCode)) {
                spaOun.setSpaIdTaxNo(spa.getSpaId());
                spaOun.setCoTaxNo(value);
                spaOun.setCoTaxNoDesc(desc);
            } else if (SpaOun.KEY_CO_TEL_NO.equals(spaCode)) {
                spaOun.setSpaIdTelNo(spa.getSpaId());
                spaOun.setCoTelNo(value);
                spaOun.setCoTelNoDesc(desc);
            }
        }
        return list;
    }


    public void mergeEntity(PerpSystemInfo psi, 
                            Object obj) throws PerpSystemException {
        EntityManager em = this.getEntityManager(psi);
        em.merge(obj);
    }

    public void mergeSystemPara(PerpSystemInfo psi, Long spaId, 
                                String value) throws PerpSystemException {
        EntityManager em = this.getEntityManager(psi);
        SystemParas spa = em.find(SystemParas.class, spaId);
        if (spa != null) {
            spa.setValue(value);
            em.merge(spa);
        }
    }

    private SystemParas createSystemParas(EntityManager em, String code, 
                                          String value, String description) {
        SystemParas spa = new SystemParas();
        spa.setCode(code);
        spa.setDescription(description);
        spa.setValue(value);
        em.persist(spa);
        return spa;
    }

    public void saveSpaOun(PerpSystemInfo psi, 
                           SpaOun spaOun) throws PerpSystemException {
        EntityManager em = this.getEntityManager(psi);
        SystemParas spa = null;
        if (spaOun.getSpaIdCoAddress() != null) {
          
            spa = em.find(SystemParas.class, spaOun.getSpaIdCoAddress());
            if (spa != null) {
                spa.setValue(spaOun.getCoAddress());
                em.merge(spa);
                System.out.println("Merge Spa " + spa);
            }
        }

        if (spaOun.getSpaIdCoLogo() != null) {
           
            spa = em.find(SystemParas.class, spaOun.getSpaIdCoLogo());
            if (spa != null) {
                spa.setValue(spaOun.getCoLogo());
                em.merge(spa);
                System.out.println("Merge Spa " + spa);
            }
        }

        if (spaOun.getSpaIdCoName() != null) {
           
            spa = em.find(SystemParas.class, spaOun.getSpaIdCoName());
            if (spa != null) {
                spa.setValue(spaOun.getCoName());
                em.merge(spa);
                System.out.println("Merge Spa " + spa);
            }
        }

        if (spaOun.getSpaIdFaxNo() != null) {
           
            spa = em.find(SystemParas.class, spaOun.getSpaIdFaxNo());
            if (spa != null) {
                spa.setValue(spaOun.getCoFaxNo());
                em.merge(spa);
                System.out.println("Merge Spa " + spa);
            }
        }
        if (spaOun.getSpaIdTaxForm() != null) {
            
            spa = em.find(SystemParas.class, spaOun.getSpaIdTaxForm());
            if (spa != null) {
                spa.setValue(spaOun.getCoTaxForm());
                em.merge(spa);
                System.out.println("Merge Spa " + spa);
            }
        }
        if (spaOun.getSpaIdTaxNo() != null) {
          
            spa = em.find(SystemParas.class, spaOun.getSpaIdTaxNo());
            if (spa != null) {
                spa.setValue(spaOun.getCoTaxNo());
                em.merge(spa);
                System.out.println("Merge Spa " + spa);
            }
        }
        if (spaOun.getSpaIdTelNo() != null) {
     
            spa = em.find(SystemParas.class, spaOun.getSpaIdTelNo());
            if (spa != null) {
                spa.setValue(spaOun.getCoTelNo());
                em.merge(spa);
                System.out.println("Merge Spa " + spa);
            }
        }
        // Save thong tin vao bang Orgainization_Units 
        if (spaOun.getOunOunId() != null) {
            OrganizationUnits oun = 
                em.find(OrganizationUnits.class, spaOun.getOunOunId());
            if (oun != null) {
                oun.setName(spaOun.getOunOunName());
                em.merge(oun);
                System.out.println("Merge Oun " + oun);
            }

        }
    }

    public void setupAccPerior(PerpSystemInfo psi, Timestamp startUseDate, 
                               Timestamp startDate, 
                               Timestamp endDate) throws PerpSystemException {
        EntityManager em = this.getEntityManager(psi);
        String sql = "execute Setup_Acc_Perior(?,?,?)";
        Query query = em.createNamedQuery(sql);
        query.setParameter(1, startUseDate);
        query.setParameter(2, startDate);
        query.setParameter(3, endDate);
        query.executeUpdate();
    }

    public List<SystemParas> querySystemParasFindByUserPrivilegeChangable(PerpSystemInfo psi) {
        EntityManager em = this.getEntityManager(psi);
        return em.createNamedQuery("SystemParas.findByUserPrivilegeChangable").getResultList();
    }

    public List<ModuleInfo> getModuleInfoList(PerpSystemInfo psi, 
                                              String packageCode, Long gusId, 
                                              Long ausId) throws PerpSystemException {
        String sql = "Select m.Men_Id\n" +
            "      ,m.Men_Id_Child_Of\n" +
            "      ,m.Menu_Name\n" +
            "      ,m.Menu_Lable\n" +
            "      ,m.Menu_Description\n" +
            "      ,Wbs_Level\n" +
            "      ,m.Menu_Number\n" +
            "      ,m.App_Code\n" +
            " From   Menus m order by doi(m.menu_number, m.wbs_level)";
        /*String sql = "Select m.Men_Id\n" +
            "      ,m.Men_Id_Child_Of\n" +
            "      ,m.Menu_Name\n" +
            "      ,m.Menu_Lable\n" +
            "      ,m.Menu_Description\n" +
            "      ,Wbs_Level\n" +
            "      ,m.Menu_Number\n" +
            "      ,m.App_Code\n" +
            " From   Menus m\n" +
            " Where  m.Men_Id In\n" +
            "       (Select Men.Men_Id\n" +
            "        From   Menus            Men\n" +
            "              ,Responsibilities Res\n" +
            "        Where  Res.Gus_Id In (Select Uro.Gus_Id\n" +
            "                              From   App_Users  Aus\n" +
            "                                    ,User_Roles Uro\n" +
            "                              Where  Uro.Aus_Id = Aus.Aus_Id\n" +
            "                              And    Uro.Gus_Id = " + gusId +
            "                              And    Aus.Aus_Id = " + ausId +
            "        And    Res.Men_Id = Men.Men_Id)\n" +
            " Connect By Prior m.Men_Id = m.Men_Id_Child_Of\n" +
            " Start  With m.Men_Id =\n" +
            "            (Select Men_Id From Menus Where Menu_Name ='" +
            packageCode + "') " + " Order  By m.Men_Id_Child_Of Desc";//*/
        EntityManager em = this.getEntityManager(psi);
        Query query = em.createNativeQuery(sql);
        List<List<Object>> list = query.getResultList();
        TreeMap<Long, ModuleInfo> map = new TreeMap<Long, ModuleInfo>();
        ModuleInfo root = null;
        //----------------------
        int i = 0;
        for (List<Object> row : list) {
            // System.out.println("Row :"+row.get(0));
            Long menId = ((BigDecimal)row.get(0)).longValue();
            BigDecimal parent = (BigDecimal)row.get(1);
            Long menIdChildOf2 = parent == null ? null : parent.longValue();
            String menuName = (String)row.get(2);
            String menuLabel = (String)row.get(3);
            String menuDescription = (String)row.get(4);
            ModuleInfo module = 
                new ModuleInfo(menId, menIdChildOf2, menuName, menuLabel, 
                               menuDescription,gusId);
            if (i++ == 0) {
                root = module;
            }
            map.put(menId, module);
            if (menIdChildOf2 != null) {
                ModuleInfo parentMi = map.get(menIdChildOf2);
                if (parentMi != null) {
                    parentMi.addChildModule(module);
                }
            }
        }
        if (root != null) {
            return root.getChildModules();
        }
        return new ArrayList<ModuleInfo>();
    }

    public List<GusInfo> getGusInfoList(PerpSystemInfo psi, 
                                        Long ausId) throws PerpSystemException {
        String sql = 
            "SELECT gus.gus_id, uro.aus_id, gus.name, gus.description\n" +
            "  FROM user_roles uro, group_users gus\n" +
            " WHERE uro.gus_id = gus.gus_id\n" +
            "   AND uro.aus_id = " + ausId;
        EntityManager em = this.getEntityManager(psi);
        Query query = em.createNativeQuery(sql);
        List<List<Object>> list = query.getResultList();
        List<GusInfo> gusList = new ArrayList<GusInfo>();
        GusInfo gusInfo = null;
        for (List<Object> row : list) {
            Long gusId = ((BigDecimal)row.get(0)).longValue();
            String gusName = (String)row.get(2);
            gusInfo = new GusInfo();
            gusInfo.setAusId(ausId);
            gusInfo.setGusName(gusName);
            gusInfo.setGusId(gusId);
            gusList.add(gusInfo);
        }
        return gusList;
    }
}
