package com.vc.web.ejb.perp;

import com.vc.web.ejb.perp.entities.AppUsers;
import com.vc.web.ejb.perp.entities.CostCenters;
import com.vc.web.ejb.perp.entities.Employees;
import com.vc.web.ejb.perp.entities.Groups;
import com.vc.web.ejb.perp.entities.OrganizationUnits;
import com.vc.web.ejb.perp.entities.SystemParas;

import com.vc.web.perp.AppInfo;
import com.vc.web.perp.ModuleInfo;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface PERPFacade {
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<SystemParas> querySystemParasFindAll();

    List<SystemParas> querySystemParasFindAllByRange(int firstResult, 
                                                     int maxResults);

    List<SystemParas> querySystemParasFindByUserPrivilege(Object userPrivilege);

    List<SystemParas> querySystemParasFindByUserPrivilegeByRange(Object userPrivilege, 
                                                                 int firstResult, 
                                                                 int maxResults);

    List<SystemParas> querySystemParasFindByUserPrivilegeChangable();

    List<SystemParas> querySystemParasFindByUserPrivilegeChangableByRange(int firstResult, 
                                                                          int maxResults);

    Number querySystemParasFindAllSize();

    void removeSystemParas(SystemParas systemParas);

    List<AppUsers> queryAppUsersFindAll();

    List<AppUsers> queryAppUsersFindAllByRange(int firstResult, 
                                               int maxResults);

    Number queryAppUsersFindAllSize();

    AppUsers queryAppUsersFindByUserNameEncrytPassword(String userName, 
                                                       String encrytPassword);

    AppUsers queryAppUsersFindByUserNamePassword(String userName, 
                                                 String password);

    String getEncrytPassword(String userName, String password);

    void removeAppUsers(AppUsers appUsers);

    List<CostCenters> queryCostCentersFindAll();

    List<CostCenters> queryCostCentersFindAllByRange(int firstResult, 
                                                     int maxResults);

    Number queryCostCentersFindAllSize();

    void removeCostCenters(CostCenters costCenters);

    List<Employees> queryEmployeesFindAll();

    List<Employees> queryEmployeesFindAllByRange(int firstResult, 
                                                 int maxResults);

    Number queryEmployeesFindAllSize();

    void removeEmployees(Employees employees);

    List<OrganizationUnits> queryOrganizationUnitsFindAll();

    List<OrganizationUnits> queryOrganizationUnitsFindAllByRange(int firstResult, 
                                                                 int maxResults);

    Number queryOrganizationUnitsFindAllSize();

    void removeOrganizationUnits(OrganizationUnits organizationUnits);

    List<Groups> queryGroupsFindAll();

    List<Groups> queryGroupsFindAllByRange(int firstResult, int maxResults);

    Number queryGroupsFindAllSize();

    void removeGroups(Groups groups);

    public void setupAccPeriod(Date beginUsing, int firstDay);

    public List<ModuleInfo> getModuleInfoList(Long ausId, String appCode);

    public List<AppInfo> getAppInfoList(Long ausId);
}
