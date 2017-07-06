package com.vc.web.ejb.perp;

import com.vc.web.ejb.perp.entities.AppUsers;
import com.vc.web.ejb.perp.entities.CostCenters;
import com.vc.web.ejb.perp.entities.Employees;
import com.vc.web.ejb.perp.entities.Groups;
import com.vc.web.ejb.perp.entities.OrganizationUnits;
import com.vc.web.ejb.perp.entities.SystemParas;

import com.vc.web.perp.AppInfo;

import com.vc.web.perp.ModuleInfo;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.TreeMap;
import java.util.Vector;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(mappedName = "ProjSystem-Model-PERPFacadeBean")
@Remote
@Local
public class PERPFacadeBean implements PERPFacade, PERPFacadeLocal {
    @PersistenceContext(unitName = "PERPPersistence")
    private EntityManager em;

    public PERPFacadeBean() {
    }

    public Object mergeEntity(Object entity) {
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    /** <code>select o from SystemParas o</code> */
    public List<SystemParas> querySystemParasFindAll() {
        return em.createNamedQuery("SystemParas.findAll").getResultList();
    }

    /** <code>select o from SystemParas o</code> */
    public List<SystemParas> querySystemParasFindAllByRange(int firstResult, 
                                                            int maxResults) {
        Query query = em.createNamedQuery("SystemParas.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select o from SystemParas o where o.userPrivilege=:userPrivilege</code> */
    public List<SystemParas> querySystemParasFindByUserPrivilege(Object userPrivilege) {
        return em.createNamedQuery("SystemParas.findByUserPrivilege").setParameter("userPrivilege", 
                                                                                   userPrivilege).getResultList();
    }

    /** <code>select o from SystemParas o where o.userPrivilege=:userPrivilege</code> */
    public List<SystemParas> querySystemParasFindByUserPrivilegeByRange(Object userPrivilege, 
                                                                        int firstResult, 
                                                                        int maxResults) {
        Query query = 
            em.createNamedQuery("SystemParas.findByUserPrivilege").setParameter("userPrivilege", 
                                                                                userPrivilege);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select o from SystemParas o where o.userPrivilege in ('U','I')</code> */
    public List<SystemParas> querySystemParasFindByUserPrivilegeChangable() {
        return em.createNamedQuery("SystemParas.findByUserPrivilegeChangable").getResultList();
    }

    /** <code>select o from SystemParas o where o.userPrivilege in ('U','I')</code> */
    public List<SystemParas> querySystemParasFindByUserPrivilegeChangableByRange(int firstResult, 
                                                                                 int maxResults) {
        Query query = 
            em.createNamedQuery("SystemParas.findByUserPrivilegeChangable");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from SystemParas o</code> */
    public Number querySystemParasFindAllSize() {
        return (Number)em.createNamedQuery("SystemParas.findAll.size").getSingleResult();
    }

    public void removeSystemParas(SystemParas systemParas) {
        systemParas = em.find(SystemParas.class, systemParas.getSpaId());
        em.remove(systemParas);
    }


    /** <code>select o from AppUsers o where o.userName= :userName and o.password=:password</code> */
    public AppUsers queryAppUsersFindByUserNameEncrytPassword(String userName, 
                                                              String encrytPassword) {
        Query query = 
            em.createNamedQuery("AppUsers.findByUserNameEncrytPassword");
        query.setParameter("userName", userName);
        query.setParameter("password", encrytPassword);
        List<AppUsers> list = query.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public AppUsers queryAppUsersFindByUserNamePassword(String userName, 
                                                        String password) {
        String encrytPassword = this.getEncrytPassword(userName, password);
        return this.queryAppUsersFindByUserNameEncrytPassword(userName, 
                                                              encrytPassword);
    }

    public String getEncrytPassword(String userName, String password) {
        Query query = 
            em.createNativeQuery("Select APP_SECURE.encrypt_password(?,?)  from dual");
        query.setParameter(1, userName);
        query.setParameter(2, password);
        List<String> list = (List<String>)query.getSingleResult();
        String incPass = list.get(0);
        return incPass;
    }


    /** <code>select o from AppUsers o</code> */
    public List<AppUsers> queryAppUsersFindAll() {
        return em.createNamedQuery("AppUsers.findAll").getResultList();
    }

    /** <code>select o from AppUsers o</code> */
    public List<AppUsers> queryAppUsersFindAllByRange(int firstResult, 
                                                      int maxResults) {
        Query query = em.createNamedQuery("AppUsers.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from AppUsers o</code> */
    public Number queryAppUsersFindAllSize() {
        return (Number)em.createNamedQuery("AppUsers.findAll.size").getSingleResult();
    }

    public void removeAppUsers(AppUsers appUsers) {
        appUsers = em.find(AppUsers.class, appUsers.getAusId());
        em.remove(appUsers);
    }

    /** <code>select o from CostCenters o</code> */
    public List<CostCenters> queryCostCentersFindAll() {
        return em.createNamedQuery("CostCenters.findAll").getResultList();
    }

    /** <code>select o from CostCenters o</code> */
    public List<CostCenters> queryCostCentersFindAllByRange(int firstResult, 
                                                            int maxResults) {
        Query query = em.createNamedQuery("CostCenters.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from CostCenters o</code> */
    public Number queryCostCentersFindAllSize() {
        return (Number)em.createNamedQuery("CostCenters.findAll.size").getSingleResult();
    }

    public void removeCostCenters(CostCenters costCenters) {
        costCenters = em.find(CostCenters.class, costCenters.getCceId());
        em.remove(costCenters);
    }

    /** <code>select o from Employees o</code> */
    public List<Employees> queryEmployeesFindAll() {
        return em.createNamedQuery("Employees.findAll").getResultList();
    }

    /** <code>select o from Employees o</code> */
    public List<Employees> queryEmployeesFindAllByRange(int firstResult, 
                                                        int maxResults) {
        Query query = em.createNamedQuery("Employees.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from Employees o</code> */
    public Number queryEmployeesFindAllSize() {
        return (Number)em.createNamedQuery("Employees.findAll.size").getSingleResult();
    }

    public void removeEmployees(Employees employees) {
        employees = em.find(Employees.class, employees.getEmpId());
        em.remove(employees);
    }

    /** <code>select o from OrganizationUnits o</code> */
    public List<OrganizationUnits> queryOrganizationUnitsFindAll() {
        return em.createNamedQuery("OrganizationUnits.findAll").getResultList();
    }

    /** <code>select o from OrganizationUnits o</code> */
    public List<OrganizationUnits> queryOrganizationUnitsFindAllByRange(int firstResult, 
                                                                        int maxResults) {
        Query query = em.createNamedQuery("OrganizationUnits.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from OrganizationUnits o</code> */
    public Number queryOrganizationUnitsFindAllSize() {
        return (Number)em.createNamedQuery("OrganizationUnits.findAll.size").getSingleResult();
    }

    public void removeOrganizationUnits(OrganizationUnits organizationUnits) {
        organizationUnits = 
                em.find(OrganizationUnits.class, organizationUnits.getOunId());
        em.remove(organizationUnits);
    }

    /** <code>select o from Groups o</code> */
    public List<Groups> queryGroupsFindAll() {
        return em.createNamedQuery("Groups.findAll").getResultList();
    }

    /** <code>select o from Groups o</code> */
    public List<Groups> queryGroupsFindAllByRange(int firstResult, 
                                                  int maxResults) {
        Query query = em.createNamedQuery("Groups.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }


    public void setupAccPeriod(Date beginUsing, int firstDay) {

    }


    /** <code>select count(o) from Groups o</code> */
    public Number queryGroupsFindAllSize() {
        return (Number)em.createNamedQuery("Groups.findAll.size").getSingleResult();
    }

    public void removeGroups(Groups groups) {
        groups = em.find(Groups.class, groups.getGroId());
        em.remove(groups);
    }


    public List<AppInfo> getAppInfoList(Long ausId) {
        /*
        String sql =
            "Select Distinct App.App_Code ,App.App_Name " +
            " From   User_Roles       Uro " + "      ,App_Users        Aus " +
            "      ,Group_Users      Gus " + "      ,Responsibilities Res " +
            "      ,Menus            Men " + "      ,Applications     App " +
            " Where  Uro.Aus_Id = Aus.Aus_Id " +
            " And    Gus.Gus_Id = Uro.Gus_Id " +
            " And    Res.Gus_Id = Gus.Gus_Id " +
            " And    (Res.Inserted = 'Y' Or Res.Updated = 'Y' Or Res.Deleted = 'Y' Or " +
            "      Res.Queried = 'Y') " + " And    Men.Men_Id = Res.Men_Id " +
            " And    Men.App_Code = App.App_Code " +
            " And    Men.Men_Id_Child_Of Is Null " +
            " And    Aus.Aus_Id = :ausId";
        //*/
        String sql = "Select App.App_Code ,app.App_Name from Applications app";
        Query query = em.createNativeQuery(sql);
        //query.setParameter("ausId", ausId);
        //query.setParameter("ausId", new Long(1));
        List<Vector<Object>> list = query.getResultList();

        List<AppInfo> retList = new ArrayList<AppInfo>();
        for (Vector<Object> row : list) {
            AppInfo app = new AppInfo("" + row.get(0), "" + row.get(1));
            retList.add(app);
        }

        return retList;
    }

    public List<ModuleInfo> getModuleInfoList(Long ausId, String appCode) {
        String sql = "Select Men.Men_Id\n" +
            "      ,Men.Men_Id_Child_Of\n" +
            "      ,Men.Menu_Name\n" +
            "      ,Men.Menu_Lable\n" +
            "      ,Men.Menu_Description\n" +
            "      ,wbs_level,\n" +
            "      men.menu_number\n" +
            " From   Menus Men\n" +
            " where men.app_code = '" + appCode + "'\n" +
            " Connect By Prior   Men.Men_Id  = Men.Men_Id_Child_Of\n" +
            " Start  With Men.Wbs_Level = 1\n" +
            " order by doi(men.menu_number, men.wbs_level)";
        Query query = em.createNativeQuery(sql);
        List<List<Object>> list = query.getResultList();
        TreeMap<Long, ModuleInfo> map = new TreeMap<Long, ModuleInfo>();
        ModuleInfo root = null;
        //----------------------
        int i = 0;
        for (List<Object> row : list) {
            Long menId = ((BigDecimal)row.get(0)).longValue();
            BigDecimal parent = (BigDecimal)row.get(1);
            Long menIdChildOf2 = parent == null ? null : parent.longValue();
            String menuName = (String)row.get(2);
            String menuLabel = (String)row.get(3);
            String menuDescription = (String)row.get(4);
            ModuleInfo module = 
                new ModuleInfo(menId, menIdChildOf2, menuName, menuLabel, 
                               menuDescription,null);
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
}
