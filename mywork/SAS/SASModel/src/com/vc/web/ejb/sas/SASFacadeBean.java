package com.vc.web.ejb.sas;

import com.vc.web.PerpSystemErrorCode;
import com.vc.web.PerpSystemException;
import com.vc.web.PerpSystemInfo;
import com.vc.web.ejb.sas.entities.Accounts;
import com.vc.web.ejb.sas.entities.Countries;
import com.vc.web.ejb.sas.entities.ForumCategories;
import com.vc.web.ejb.sas.entities.ForumMembers;
import com.vc.web.ejb.sas.entities.ForumMessages;
import com.vc.web.ejb.sas.entities.ForumOwners;
import com.vc.web.ejb.sas.entities.Forums;
import com.vc.web.ejb.sas.entities.HelpDeskRelations;
import com.vc.web.ejb.sas.entities.HelpDesks;
import com.vc.web.ejb.sas.entities.Industries;
import com.vc.web.ejb.sas.entities.News;
import com.vc.web.ejb.sas.entities.NewsBookmarks;
import com.vc.web.ejb.sas.entities.NewsCategories;
import com.vc.web.ejb.sas.entities.NewsRelations;
import com.vc.web.ejb.sas.entities.Provinces;
import com.vc.web.ejb.sas.entities.Roles;
import com.vc.web.ejb.sas.entities.SecurityQuestions;
import com.vc.web.ejb.sas.entities.ServicePackages;
import com.vc.web.ejb.sas.entities.Subscriptions;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless(mappedName = "SAS-PERPModel-SASFacadeBean")
@Remote
@Local
public class SASFacadeBean implements SASFacade, SASFacadeLocal {
    @PersistenceContext(unitName = "SASPersistence")
    private EntityManager em;

    public SASFacadeBean() {
    }

    public Object mergeEntity(Object entity) {
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        try {
            em.persist(entity);
        } catch (Exception e) {
            em.remove(entity);
        }
        return entity;
    }

    /** <code>select o from Provinces o</code> */
    public List<Provinces> queryProvincesFindAll() {
        return em.createNamedQuery("Provinces.findAll").getResultList();
    }

    /** <code>select o from Provinces o</code> */
    public List<Provinces> queryProvincesFindAllByRange(int firstResult, 
                                                        int maxResults) {
        Query query = em.createNamedQuery("Provinces.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from Provinces o</code> */
    public Number queryProvincesFindAllSize() {
        return (Number)em.createNamedQuery("Provinces.findAll.size").getSingleResult();
    }

    public void removeProvinces(Provinces provinces) {
        provinces = em.find(Provinces.class, provinces.getProId());
        em.remove(provinces);
    }

    public ServicePackages queryServicePackagesFindBySpaId(Long spaId) {
        return em.find(ServicePackages.class, spaId);
    }


    /** <code>select o from ServicePackages o</code> */
    public List<ServicePackages> queryServicePackagesFindAll() {
        return em.createNamedQuery("ServicePackages.findAll").getResultList();
    }

    public ServicePackages queryServicePackagesFindByPackageCode(String packageCode) {
        Query query = em.createNamedQuery("ServicePackages.findByPackageCode");
        query.setParameter("packageCode", packageCode);
        List<ServicePackages> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /** <code>select o from ServicePackages o</code> */
    public List<ServicePackages> queryServicePackagesFindAllByRange(int firstResult, 
                                                                    int maxResults) {
        Query query = em.createNamedQuery("ServicePackages.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select o from ServicePackages o</code> */
    public List<ServicePackages> queryServicePackagesFindToOrder(Long accId) {
        Query query = em.createNamedQuery("ServicePackages.findToOrder");
        // query.setParameter("accId",accId);
        return query.getResultList();
    }

    public Accounts queryAccountsFindByUserName(String userName) {
        Query query = em.createNamedQuery("Accounts.findByUserName");
        query.setParameter("userName", userName);
        List<Accounts> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public Accounts queryAccountsFindByUserNamePassowrd(String userName, 
                                                        String password) {
        Query query = em.createNamedQuery("Accounts.findByUserNamePassowrd");
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        List<Accounts> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }


    /** <code>select count(o) from ServicePackages o</code> */
    public Number queryServicePackagesFindAllSize() {
        return (Number)em.createNamedQuery("ServicePackages.findAll.size").getSingleResult();
    }

    public void removeServicePackages(ServicePackages servicePackages) {
        servicePackages = 
                em.find(ServicePackages.class, servicePackages.getSpaId());
        em.remove(servicePackages);
    }

    /** <code>select o from Roles o</code> */
    public List<Roles> queryRolesFindAll() {
        return em.createNamedQuery("Roles.findAll").getResultList();
    }

    /** <code>select o from Roles o</code> */
    public List<Roles> queryRolesFindAllByRange(int firstResult, 
                                                int maxResults) {
        Query query = em.createNamedQuery("Roles.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from Roles o</code> */
    public Number queryRolesFindAllSize() {
        return (Number)em.createNamedQuery("Roles.findAll.size").getSingleResult();
    }

    public void removeRoles(Roles roles) {
        roles = em.find(Roles.class, roles.getRolId());
        em.remove(roles);
    }

    /** <code>select o from Countries o</code> */
    public List<Countries> queryCountriesFindAll() {
        return em.createNamedQuery("Countries.findAll").getResultList();
    }

    /** <code>select o from Countries o</code> */
    public List<Subscriptions> querySubscriptionsFindByAccId(Long accId) {
        Query query = em.createNamedQuery("Subscriptions.findByAccId");
        query.setParameter("accId", accId);
        return query.getResultList();
    }


    /** <code>select o from Countries o</code> */
    public List<Countries> queryCountriesFindAllByRange(int firstResult, 
                                                        int maxResults) {
        Query query = em.createNamedQuery("Countries.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from Countries o</code> */
    public Number queryCountriesFindAllSize() {
        return (Number)em.createNamedQuery("Countries.findAll.size").getSingleResult();
    }

    public void removeCountries(Countries countries) {
        countries = em.find(Countries.class, countries.getCouId());
        em.remove(countries);
    }


    public Subscriptions querySubscriptionsFindByActivatedCode(String activatedCode) {
        Query query = em.createNamedQuery("Subscriptions.findByActivatedCode");
        query.setParameter("activatedCode", activatedCode);
        List<Subscriptions> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /** <code>select o from Subscriptions o</code> */
    public List<Subscriptions> querySubscriptionsFindAll() {
        return em.createNamedQuery("Subscriptions.findAll").getResultList();
    }

    public Accounts queryAccountsFindByAccId(Long accId) {
        return em.find(Accounts.class, accId);
    }

    /** <code>select o from Subscriptions o</code> */
    public List<Subscriptions> querySubscriptionsFindAllByRange(int firstResult, 
                                                                int maxResults) {
        Query query = em.createNamedQuery("Subscriptions.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from Subscriptions o</code> */
    public Number querySubscriptionsFindAllSize() {
        return (Number)em.createNamedQuery("Subscriptions.findAll.size").getSingleResult();
    }

    public void removeSubscriptions(Subscriptions subscriptions) {
        subscriptions = em.find(Subscriptions.class, subscriptions.getSubId());
        em.remove(subscriptions);
    }

    /** <code>select o from Accounts o</code> */
    public List<Accounts> queryAccountsFindAll() {
        return em.createNamedQuery("Accounts.findAll").getResultList();
    }

    /** <code>select o from Accounts o</code> */
    public List<Accounts> queryAccountsFindAllByRange(int firstResult, 
                                                      int maxResults) {
        Query query = em.createNamedQuery("Accounts.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from Accounts o</code> */
    public Number queryAccountsFindAllSize() {
        return (Number)em.createNamedQuery("Accounts.findAll.size").getSingleResult();
    }

    public void removeAccounts(Accounts accounts) {
        accounts = em.find(Accounts.class, accounts.getAccId());
        em.remove(accounts);
    }

    /** <code>select o from Industries o</code> */
    public List<Industries> queryIndustriesFindAll() {
        return em.createNamedQuery("Industries.findAll").getResultList();
    }

    /** <code>select o from Industries o</code> */
    public List<Industries> queryIndustriesFindAllByRange(int firstResult, 
                                                          int maxResults) {
        Query query = em.createNamedQuery("Industries.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from Industries o</code> */
    public Number queryIndustriesFindAllSize() {
        return (Number)em.createNamedQuery("Industries.findAll.size").getSingleResult();
    }

    public void removeIndustries(Industries industries) {
        industries = em.find(Industries.class, industries.getIndId());
        em.remove(industries);
    }

    private Long getNextValue(String seqName) {
        Query query = 
            em.createNativeQuery("Select " + seqName + ".nextval from dual");
        List<Object> v = (List<Object>)query.getSingleResult();
        Object obj = v.get(0);
        if (obj instanceof BigDecimal) {
            return ((BigDecimal)obj).longValue();
        }
        return (Long)obj;
    }

    public Long getNextValueAccId() {
        return this.getNextValue("Acc_Seq");
    }


    /**
     * Dynamic PERP DB Info .
     * @param subId
     * @return
     */
    public PerpSystemInfo getPerpSystemInfo(Long subId) throws PerpSystemException {
        Subscriptions sub = em.find(Subscriptions.class, subId);
        if (sub == null) {
            throw new PerpSystemException(PerpSystemErrorCode.SUBSCRIPTION_NOT_FOUND);
        }
        String sql = 
            "Select Env_Pkg.Env_Id Env_Id " + "      ,Env_Pkg.Sub_Id Sub_Id " + 
            "      ,Env_Pkg.Db_Host Db_Host " + 
            "      ,Env_Pkg.Db_Port Db_Port " + "      ,Env_Pkg.Sid Sid " + 
            "      ,Env_Pkg.Db_User Db_User " + 
            "      ,Env_Pkg.Db_Pwd Db_Pwd " + 
            "      ,Env_Pkg.App_Host App_Host " + 
            "      ,Env_Pkg.App_Port App_Port " + 
            "      ,Env_Pkg.Setup_Date Setup_Date " + 
            "      ,Env_Pkg.Removed_Date Removed_Date " + 
            "      ,Env_Pkg.Current_Num_Of_App_User Current_Num_Of_App_User " + 
            "      ,Env_Pkg.Description Description " + 
            "      ,'activatedCode' Activated_Code " + "From   Dual " + 
            "Where  Env_Pkg.Get_Env(" + subId + ") = 'Y'";
        Query query = em.createNativeQuery(sql);
        List<Object> list = null;
        try {
            list = (List<Object>)query.getSingleResult();
        } catch (NoResultException e) {
            throw new PerpSystemException(PerpSystemErrorCode.PERP_SYSTEM_NOT_FOUND);
        }
        if (list == null) {
            throw new PerpSystemException(PerpSystemErrorCode.PERP_SYSTEM_NOT_FOUND);
        }

        BigDecimal value = null;
        PerpSystemInfo psi = new PerpSystemInfo();
        value = (BigDecimal)list.get(0);
        psi.setEnvId(value.longValue());
        value = (BigDecimal)list.get(1);
        psi.setSubId(value.longValue());
        psi.setDbHost((String)list.get(2));
        value = (BigDecimal)list.get(3);
        psi.setDbPort(value == null ? 1521 : value.intValue());
        psi.setSid((String)list.get(4));
        psi.setDbUser((String)list.get(5));
        psi.setDbPwd((String)list.get(6));
        psi.setAppHost((String)list.get(7));
        value = (BigDecimal)list.get(8);
        psi.setAppPort(value == null ? 7778 : value.intValue());
        psi.setSetupDate((Timestamp)list.get(9));
        psi.setRemovedDate((Timestamp)list.get(10));
        value = (BigDecimal)list.get(11);
        psi.setCurrentNumOfAppUser(value == null ? 0 : value.longValue());
        psi.setDescription((String)list.get(12));
        psi.setActivatedCode((String)list.get(13));
        psi.setActivatedCode(sub.getActivatedCode());
        return psi; //*/       
    }

    public String generateActivatedCode() {
        Query query = em.createNativeQuery("Select sub_seq.nextval from dual");
        List<Object> list = (List<Object>)query.getSingleResult();
        Object obj = list.get(0);
        BigDecimal value = (BigDecimal)obj;
        return "PerpActive" + value.longValue();
    }

    /** <code>select o from Accounts o where o.email=:email</code> */
    public Accounts queryAccountsFindByEmail(String email) {
        if (email == null) {
            return null;
        }
        Query query = 
            em.createNamedQuery("Accounts.findByEmail").setParameter("email", 
                                                                     email);
        List<Accounts> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 
     * @param accId
     * @return
     */
    public Subscriptions createDefaultSubscriptions(Long accId) {
        Subscriptions sub = new Subscriptions();
        String activatedCode = this.generateActivatedCode();
        Accounts acc = em.find(Accounts.class, accId);
        sub.setAccounts(acc);
        sub.setActivatedCode(activatedCode);
        sub.setStartDate(new Timestamp(System.currentTimeMillis()));
        sub.setSubscribeDate(new Timestamp(System.currentTimeMillis()));
        sub.setQuantity(new Double(1));
        em.persist(sub);
        return sub;
    }

    /** <code>select o from SecurityQuestions o</code> */
    public List<SecurityQuestions> querySecurityQuestionsFindAll() {
        Query query = em.createNamedQuery("SecurityQuestions.findAll");
        return query.getResultList();
    }

    public Subscriptions querySubscriptionsFindBySubId(Long subId) {
        return em.find(Subscriptions.class, subId);
    }

    public void sendEmailCreatedAccount(String email, String fullName, 
                                        String userName, String password, 
                                        String url) {
        Query query = 
            em.createNativeQuery("Begin Demo_Mail.mail(?,?,?,?); End;");
        String message = "Chao ban " + fullName + "\n" +
            "Ban da dang ky thanh cong vao he thong PERP On-Demand" + 
            "Tai khoan giao dich tren " + url + "\n\n" +
            "Tai khoan " + userName + "\n" +
            "Password " + password + "\n";

        query.setParameter(1, "hatv@pythis.com");
        query.setParameter(2, email);
        query.setParameter(3, 
                           "Ban da dang ky thanh cong he thong PERP On-Demand");
        query.setParameter(4, message);
        query.executeUpdate();
    }

    public void sendEmailCreateSub(Long accId, Long subId) {
        Accounts acc = em.find(Accounts.class, accId);
        if (acc == null) {
            return;
        }
        Subscriptions sub = em.find(Subscriptions.class, subId);
        if (sub == null) {
            return;
        }
        ServicePackages spa = sub.getServicePackages();
        String packageCode = spa.getPackageCode();
        String packageName = spa.getPackageName();

        Query query = 
            em.createNativeQuery("Begin Demo_Mail.mail(?,?,?,?); End;");
        String message = "Chao ban " + acc.getFullName() + "\n" +
            "Ban da dang ky su dung san pham PERP On-Demand " + packageCode + 
            " - " + packageName + "\n" +
            "Xin ban cho doi chung toi xac minh cac thong tin va tao co so du lieu cho ung dung cua ban \n " + 
            "Chung toi se lien lac voi ban trong khoang thoi gian som nhat\n\n" +
            "PERP On-Demand Admin";

        query.setParameter(1, "hatv@pythis.com");
        query.setParameter(2, acc.getEmail());
        query.setParameter(3, "From Sale PERP Ondeman");
        query.setParameter(4, message);
        query.executeUpdate();
    }

    /**
    public Subscriptions querySubscriptionFindByAccIdSpaId(Long accId,
                                                           Long spaId) {
        Query query = em.createNamedQuery("Subscriptions.findByAccIdSpaId");
        query.setParameter("accId", accId);
        query.setParameter("spaId", spaId);
        List<Subscriptions> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }//*/

    public Subscriptions createSubscriptions(Long accId, Long spaId) {
        Accounts acc = em.find(Accounts.class, accId);
        ServicePackages spa = em.find(ServicePackages.class, spaId);
        if (acc == null || spa == null) {
            return null;
        }
        Subscriptions sub = new Subscriptions();
        sub.setAccounts(acc);
        sub.setServicePackages(spa);
        sub.setQuantity(new Double(1));
        sub.setStartDate(new Timestamp(System.currentTimeMillis()));
        sub.setSubscribeDate(new Timestamp(System.currentTimeMillis()));
        sub.setActivatedCode(this.generateActivatedCode());
        this.persistEntity(sub);
        return sub;
    }

    public void sendEmailForgotPassword(String email) {
        Accounts acc = this.queryAccountsFindByEmail(email);
        if (acc == null) {
            return;
        }
        Query query = 
            em.createNativeQuery("Begin Demo_Mail.mail(?,?,?,?); End;");
        String message = "Chao ban " + acc.getFullName() + "\n" +
            "Tai khoan su dung cua ban :" + acc.getUserName() + "\n" +
            "Mat khau :" + acc.getPassword();

        query.setParameter(1, "hatv@pythis.com");
        query.setParameter(2, acc.getEmail());
        query.setParameter(3, "Password PERP Ondeman");
        query.setParameter(4, message);
    }

    public List<Accounts> queryAccountsFindCustome(Long indId, Long couId, 
                                                   Long proId, 
                                                   Integer empCount) {
        System.out.println("Chay den day :" + indId + ":" + couId + ":" + 
                           proId + ":" + empCount);
        return this.queryAccountsFindAll();
    }

    //*/

    /** <code>select o from HelpDeskRelations o</code> */
    public List<HelpDeskRelations> queryHelpDeskRelationsFindAll() {
        return em.createNamedQuery("HelpDeskRelations.findAll").getResultList();
    }

    /** <code>select o from HelpDeskRelations o</code> */
    public List<HelpDeskRelations> queryHelpDeskRelationsFindAllByRange(int firstResult, 
                                                                        int maxResults) {
        Query query = em.createNamedQuery("HelpDeskRelations.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from HelpDeskRelations o</code> */
    public Number queryHelpDeskRelationsFindAllSize() {
        return (Number)em.createNamedQuery("HelpDeskRelations.findAll.size").getSingleResult();
    }

    public void removeHelpDeskRelations(HelpDeskRelations helpDeskRelations) {
        helpDeskRelations = 
                em.find(HelpDeskRelations.class, helpDeskRelations.getHdrId());
        em.remove(helpDeskRelations);
    }

    /** <code>select o from HelpDesks o</code> */
    public List<HelpDesks> queryHelpDesksFindAll() {
        return em.createNamedQuery("HelpDesks.findAll").getResultList();
    }

    public List<HelpDesks> queryHelpDesksFindByCode2(String helpCode) {

        Query query = em.createNamedQuery("HelpDesks.findByCode");
        query.setParameter("helpCode", helpCode);
        List<HelpDesks> list = query.getResultList();
        return list;
    }

    public List<HelpDesks> queryHelpDesksFindAllRoot() {
        Query query = em.createNamedQuery("HelpDesks.findAllRoot");
        List<HelpDesks> list = query.getResultList();
        return list;
    }

    public List<HelpDesks> queryHelpDesksFindAllRootByCode(String helpCode) {
        Query query = em.createNamedQuery("HelpDesks.findAllRoot");
        List<HelpDesks> list = query.getResultList();
        return list;
    }

    public HelpDesks createDefaultChildHelpDesks(HelpDesks parent) {
        if (parent == null) {
            return null;
        }
        Long parentId = parent.getHelpId();
        HelpDesks p = em.find(HelpDesks.class, parentId);
        if (p == null) {
            return null;
        }
        HelpDesks child = new HelpDesks();
        child.setTitle("New title here ... ");
        child.setContent("New content here ..");
        // Ghi chu : helpCode tu dong duoc Trigger tao ra ...
        p.addHelpDesks(child);
        this.persistEntity(child);
        return child;
    }

    public HelpDesks queryHelpDesksFindByCode(String helpCode) {
        Query query = em.createNamedQuery("HelpDesks.findByCode");
        query.setParameter("helpCode", helpCode);
        List<HelpDesks> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /** <code>select o from HelpDesks o</code> */
    public List<HelpDesks> queryHelpDesksFindAllByRange(int firstResult, 
                                                        int maxResults) {
        Query query = em.createNamedQuery("HelpDesks.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from HelpDesks o</code> */
    public Number queryHelpDesksFindAllSize() {
        return (Number)em.createNamedQuery("HelpDesks.findAll.size").getSingleResult();
    }

    public void removeHelpDesks(HelpDesks helpDesks) {
        helpDesks = em.find(HelpDesks.class, helpDesks.getHelpId());
        em.remove(helpDesks);
    }

    /** <code>select o from NewsCategories o</code> */
    public NewsCategories queryNewsCategoriesFindByNcaId(Long ncaId) {
        if (ncaId == null) {
            return null;
        }
        return em.find(NewsCategories.class, ncaId);
    }

    public List<NewsCategories> queryNewsCategoriesFindByNcaIdParent(Long ncaIdParent) {
        return em.createNamedQuery("NewsCategories.findByNcaIdParent").setParameter("ncaIdParent", 
                                                                                    ncaIdParent).getResultList();
    }

    public List<NewsCategories> queryNewsCategoriesFindByCategoryCodeParent(String categoryCode) {
        return em.createNamedQuery("NewsCategories.findByCategoryCodeParent").setParameter("categoryCode", 
                                                                                           categoryCode).getResultList();
    }

    /** <code>select o from NewsCategories o</code> */
    public List<NewsCategories> queryNewsCategoriesFindAll() {
        return em.createNamedQuery("NewsCategories.findAll").getResultList();
    }

    public List<News> queryNewsFindByCategoryCodeAndBookmarkCode(String categoryCode, 
                                                                 String bookmarkCode) {
        Query query = 
            em.createNamedQuery("News.findByCategoryCodeAndBookmarkCode");
        query.setParameter("categoryCode", categoryCode);
        query.setParameter("bookmarkCode", bookmarkCode);
        return query.getResultList();
    }


    public List<NewsCategories> queryNewsCategoriesFindAllRoot() {
        return em.createNamedQuery("NewsCategories.findAllRoot").getResultList();
    }

    /** <code>select o from NewsCategories o</code> */
    public List<NewsCategories> queryNewsCategoriesFindAllByRange(int firstResult, 
                                                                  int maxResults) {
        Query query = em.createNamedQuery("NewsCategories.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from NewsCategories o</code> */
    public Number queryNewsCategoriesFindAllSize() {
        return (Number)em.createNamedQuery("NewsCategories.findAll.size").getSingleResult();
    }

    public void removeNewsCategories(NewsCategories newsCategories) {
        newsCategories = 
                em.find(NewsCategories.class, newsCategories.getNcaId());
        em.remove(newsCategories);
    }

    /** <code>select o from News o</code> */
    public List<News> queryNewsFindAll() {
        return em.createNamedQuery("News.findAll").getResultList();
    }

    public News queryNewsFindByNewsId(Long newsId) {
        List<News> list = 
            em.createNamedQuery("News.findByNewsId").setParameter("newsId", 
                                                                  newsId).getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<News> queryNewsFindByNcaId(Long ncaId) {
        return em.createNamedQuery("News.findByNcaId").setParameter("ncaId", 
                                                                    ncaId).getResultList();
    }

    public List<News> queryNewsFindByNcaId(Long ncaId, int resultCount) {
        Query query = 
            em.createNamedQuery("News.findByNcaId").setParameter("ncaId", 
                                                                 ncaId);
        query = query.setFirstResult(0).setMaxResults(resultCount);
        return query.getResultList();
    }

    public List<News> queryNewsFindByNcaIdOrderByPriorityLevel(Long ncaId) {
        return em.createNamedQuery("News.findByNcaIdOrderByPriorityLevel").setParameter("ncaId", 
                                                                                        ncaId).getResultList();
    }

    public News queryNewsFindFirstByNcaIdOrderByPriorityLevel(Long ncaId) {
        // Query query= em.createNativeQuery("Select * from News n where n.nca_Id=:ncaId and rownum=1 order by n.priority_Level desc",News.class);
        List<News> list = queryNewsFindByNcaIdOrderByPriorityLevel(ncaId);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    /** <code>select o from News o</code> */
    public List<News> queryNewsFindAllByRange(int firstResult, 
                                              int maxResults) {
        Query query = em.createNamedQuery("News.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from News o</code> */
    public Number queryNewsFindAllSize() {
        return (Number)em.createNamedQuery("News.findAll.size").getSingleResult();
    }

    public void removeNews(News news) {
        news = em.find(News.class, news.getNewsId());
        em.remove(news);
    }

    /** <code>select o from NewsRelations o</code> */
    public List<NewsRelations> queryNewsRelationsFindAll() {
        return em.createNamedQuery("NewsRelations.findAll").getResultList();
    }

    /** <code>select o from NewsRelations o</code> */
    public List<NewsRelations> queryNewsRelationsFindAllByRange(int firstResult, 
                                                                int maxResults) {
        Query query = em.createNamedQuery("NewsRelations.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from NewsRelations o</code> */
    public Number queryNewsRelationsFindAllSize() {
        return (Number)em.createNamedQuery("NewsRelations.findAll.size").getSingleResult();
    }

    public void removeNewsRelations(NewsRelations newsRelations) {
        newsRelations = em.find(NewsRelations.class, newsRelations.getNreId());
        em.remove(newsRelations);
    }

    /** <code>select o from Forums o</code> */
    public List<Forums> queryForumsFindAll() {
        return em.createNamedQuery("Forums.findAll").getResultList();
    }

    public List<Forums> queryForumsFindByOwnerCode(String forumOwnerCode) {
        return em.createNamedQuery("Forums.findByOwnerCode").setParameter("forumOwnerCode", 
                                                                          forumOwnerCode).getResultList();
    }


    /** <code>select o from Forums o</code> */
    public List<Forums> queryForumsFindAllByRange(int firstResult, 
                                                  int maxResults) {
        Query query = em.createNamedQuery("Forums.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from Forums o</code> */
    public Number queryForumsFindAllSize() {
        return (Number)em.createNamedQuery("Forums.findAll.size").getSingleResult();
    }

    public void removeForums(Forums forums) {
        forums = em.find(Forums.class, forums.getForumId());
        em.remove(forums);
    }

    /** <code>select o from ForumOwners o</code> */
    public List<ForumOwners> queryForumOwnersFindAll() {
        return em.createNamedQuery("ForumOwners.findAll").getResultList();
    }

    /** <code>select o from ForumOwners o</code> */
    public List<ForumOwners> queryForumOwnersFindAllByRange(int firstResult, 
                                                            int maxResults) {
        Query query = em.createNamedQuery("ForumOwners.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from ForumOwners o</code> */
    public Number queryForumOwnersFindAllSize() {
        return (Number)em.createNamedQuery("ForumOwners.findAll.size").getSingleResult();
    }

    public void removeForumOwners(ForumOwners forumOwners) {
        forumOwners = 
                em.find(ForumOwners.class, forumOwners.getForumOwnerCode());
        em.remove(forumOwners);
    }

    /** <code>select o from ForumCategories o</code> */
    public List<ForumCategories> queryForumCategoriesFindAll() {
        return em.createNamedQuery("ForumCategories.findAll").getResultList();
    }

    /** <code>select o from ForumCategories o</code> */
    public List<ForumCategories> queryForumCategoriesFindAllByRange(int firstResult, 
                                                                    int maxResults) {
        Query query = em.createNamedQuery("ForumCategories.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from ForumCategories o</code> */
    public Number queryForumCategoriesFindAllSize() {
        return (Number)em.createNamedQuery("ForumCategories.findAll.size").getSingleResult();
    }

    public void removeForumCategories(ForumCategories forumCategories) {
        forumCategories = 
                em.find(ForumCategories.class, forumCategories.getFcaId());
        em.remove(forumCategories);
    }

    /** <code>select o from ForumMessages o</code> */
    public List<ForumMessages> queryForumMessagesFindAll() {
        return em.createNamedQuery("ForumMessages.findAll").getResultList();
    }

    public List<ForumMessages> queryForumMessagesFindAllRootByFcaId(Long fcaId) {
        return em.createNamedQuery("ForumMessages.findAllRootByFcaId").setParameter("fcaId", 
                                                                                    fcaId).getResultList();
    }

    /** <code>select o from ForumMessages o</code> */
    public List<ForumMessages> queryForumMessagesFindAllByRange(int firstResult, 
                                                                int maxResults) {
        Query query = em.createNamedQuery("ForumMessages.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from ForumMessages o</code> */
    public Number queryForumMessagesFindAllSize() {
        return (Number)em.createNamedQuery("ForumMessages.findAll.size").getSingleResult();
    }

    public void removeForumMessages(ForumMessages forumMessages) {
        forumMessages = 
                em.find(ForumMessages.class, forumMessages.getMessageId());
        em.remove(forumMessages);
    }

    /** <code>select o from ForumMembers o</code> */
    public List<ForumMembers> queryForumMembersFindAll() {
        return em.createNamedQuery("ForumMembers.findAll").getResultList();
    }

    /** <code>select o from ForumMembers o</code> */
    public List<ForumMembers> queryForumMembersFindAllByRange(int firstResult, 
                                                              int maxResults) {
        Query query = em.createNamedQuery("ForumMembers.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from ForumMembers o</code> */
    public Number queryForumMembersFindAllSize() {
        return (Number)em.createNamedQuery("ForumMembers.findAll.size").getSingleResult();
    }

    public void removeForumMembers(ForumMembers forumMembers) {
        forumMembers = em.find(ForumMembers.class, forumMembers.getMemberId());
        em.remove(forumMembers);
    }

    /** <code>select o from NewsBookmarks o</code> */
    public List<NewsBookmarks> queryNewsBookmarksFindAll() {
        return em.createNamedQuery("NewsBookmarks.findAll").getResultList();
    }

    /** <code>select o from NewsBookmarks o</code> */
    public List<NewsBookmarks> queryNewsBookmarksFindAllByRange(int firstResult, 
                                                                int maxResults) {
        Query query = em.createNamedQuery("NewsBookmarks.findAll");
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    /** <code>select count(o) from NewsBookmarks o</code> */
    public Number queryNewsBookmarksFindAllSize() {
        return (Number)em.createNamedQuery("NewsBookmarks.findAll.size").getSingleResult();
    }

    public void removeNewsBookmarks(NewsBookmarks newsBookmarks) {
        newsBookmarks = 
                em.find(NewsBookmarks.class, newsBookmarks.getBookmarkCode());
        em.remove(newsBookmarks);
    }
}
