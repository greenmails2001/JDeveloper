package com.vc.web.ejb.perp;

import com.vc.web.PerpSystemException;
import com.vc.web.PerpSystemInfo;
import com.vc.web.SpaOun;

import com.vc.web.ejb.perp.entities.AppUsers;

import com.vc.web.ejb.perp.entities.SystemParas;

import com.vc.web.perp.GusInfo;
import com.vc.web.perp.ModuleInfo;

import com.vc.web.perp.OunInfo;
import com.vc.web.perp.OunShortInfo;

import com.vc.web.perp.Spa4Oun;

import java.sql.Timestamp;

import java.util.List;

import javax.ejb.Local;

import javax.persistence.EntityManager;

@Local
public interface DynamicPERPFacadeLocal {
    public List<AppUsers> queryAppUsersFindAll(PerpSystemInfo psi);


    public AppUsers queryAppUsersFindByUserNamePassword(PerpSystemInfo psi, 
                                                        String userName, 
                                                        String password) throws PerpSystemException;

    public List<SpaOun> getSpaOunList(PerpSystemInfo psi) throws PerpSystemException;

    public void saveSpaOun(PerpSystemInfo psi, 
                           SpaOun spaOun) throws PerpSystemException;

    public void setupAccPerior(PerpSystemInfo psi, Timestamp startUseDate, 
                               Timestamp startDate, 
                               Timestamp endDate) throws PerpSystemException;

    public void mergeEntity(PerpSystemInfo psi, 
                            Object obj) throws PerpSystemException;

    public void mergeSystemPara(PerpSystemInfo psi, Long spaId, 
                                String value) throws PerpSystemException;

    public List<SystemParas> querySystemParasFindByUserPrivilegeChangable(PerpSystemInfo psi) throws PerpSystemException ;

    public List<ModuleInfo> getModuleInfoList(PerpSystemInfo psi, 
                                              String packageCode, Long gusId, 
                                              Long ausId) throws PerpSystemException;

    public List<GusInfo> getGusInfoList(PerpSystemInfo psi, 
                                        Long ausId) throws PerpSystemException;

    public List<ModuleInfo> getModuleInfoListSearchResult(PerpSystemInfo psi, 
                                                          Long ausId, 
                                                          Long gusId, 
                                                          String searchText) throws PerpSystemException;

    public Spa4Oun getSpa4Oun(PerpSystemInfo psi, Long ounId, 
                              boolean root) throws PerpSystemException;

    public void saveSpa4Oun(PerpSystemInfo psi, 
                            Spa4Oun spa4Oun) throws PerpSystemException;

    public List<OunShortInfo> getOunShortInfoList(PerpSystemInfo psi) throws PerpSystemException;
    
    public List<OunInfo> getOunInfoList(PerpSystemInfo psi) throws PerpSystemException;
    
    public void removeOrganizationUnit(PerpSystemInfo psi, 
                                       OunInfo ounInfo) throws PerpSystemException;
    
    public void createDefaultChildOranizationUnit(PerpSystemInfo psi, 
                                                  Long parentOunId, 
                                                  String userName) throws PerpSystemException ;
}
