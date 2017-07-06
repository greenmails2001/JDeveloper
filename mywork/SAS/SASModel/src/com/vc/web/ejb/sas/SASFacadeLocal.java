package com.vc.web.ejb.sas;

import com.vc.web.PerpSystemException;
import com.vc.web.PerpSystemInfo;
import com.vc.web.ejb.perp.entities.SystemParas;
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

import java.util.List;

import javax.ejb.Local;

@Local
public interface SASFacadeLocal {
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<Provinces> queryProvincesFindAll();

    List<Provinces> queryProvincesFindAllByRange(int firstResult, 
                                                 int maxResults);

    Number queryProvincesFindAllSize();

    void removeProvinces(Provinces provinces);

    ServicePackages queryServicePackagesFindBySpaId(Long spaId);

    ServicePackages queryServicePackagesFindByPackageCode(String packageCode);

    List<ServicePackages> queryServicePackagesFindAll();

    List<ServicePackages> queryServicePackagesFindAllByRange(int firstResult, 
                                                             int maxResults);

    Number queryServicePackagesFindAllSize();

    void removeServicePackages(ServicePackages servicePackages);

    List<Roles> queryRolesFindAll();

    List<Roles> queryRolesFindAllByRange(int firstResult, int maxResults);

    Number queryRolesFindAllSize();

    void removeRoles(Roles roles);

    List<Countries> queryCountriesFindAll();

    List<Countries> queryCountriesFindAllByRange(int firstResult, 
                                                 int maxResults);

    Number queryCountriesFindAllSize();

    void removeCountries(Countries countries);

    Subscriptions querySubscriptionsFindByActivatedCode(String activatedCode);

    List<Subscriptions> querySubscriptionsFindAll();

    List<Subscriptions> querySubscriptionsFindAllByRange(int firstResult, 
                                                         int maxResults);

    Number querySubscriptionsFindAllSize();

    void removeSubscriptions(Subscriptions subscriptions);

    List<Accounts> queryAccountsFindAll();

    Accounts queryAccountsFindByUserName(String userName);

    Accounts queryAccountsFindByUserNamePassowrd(String userName, 
                                                 String password);

    List<Accounts> queryAccountsFindAllByRange(int firstResult, 
                                               int maxResults);

    Number queryAccountsFindAllSize();

    void removeAccounts(Accounts accounts);

    List<Industries> queryIndustriesFindAll();

    List<Industries> queryIndustriesFindAllByRange(int firstResult, 
                                                   int maxResults);

    Number queryIndustriesFindAllSize();

    void removeIndustries(Industries industries);

    Long getNextValueAccId();

    List<ServicePackages> queryServicePackagesFindToOrder(Long accId);

    List<Subscriptions> querySubscriptionsFindByAccId(Long accId);

    Accounts queryAccountsFindByAccId(Long accId);

    List<SecurityQuestions> querySecurityQuestionsFindAll();

    /** ---------------------------------------------------------------------- */

    PerpSystemInfo getPerpSystemInfo(Long sasAccId) throws PerpSystemException;

    public String generateActivatedCode();

    public Accounts queryAccountsFindByEmail(String email);

    public Subscriptions createDefaultSubscriptions(Long accId);

    public Subscriptions querySubscriptionsFindBySubId(Long subId);

    public Subscriptions createSubscriptions(Long accId, Long spaId);

    public void sendEmailCreatedAccount(String email, String fullName, 
                                        String userName, String password, 
                                        String url);

    public void sendEmailCreateSub(Long accId, Long subId);

    public void sendEmailForgotPassword(String email);

    public List<Accounts> queryAccountsFindCustome(Long indId, Long couId, 
                                                   Long proId, 
                                                   Integer empCount);

    List<HelpDeskRelations> queryHelpDeskRelationsFindAll();

    List<HelpDeskRelations> queryHelpDeskRelationsFindAllByRange(int firstResult, 
                                                                 int maxResults);

    Number queryHelpDeskRelationsFindAllSize();

    void removeHelpDeskRelations(HelpDeskRelations helpDeskRelations);

    List<HelpDesks> queryHelpDesksFindAll();

    public List<HelpDesks> queryHelpDesksFindByCode2(String helpCode);
    
    List<HelpDesks> queryHelpDesksFindAllRoot();
    
    List<HelpDesks> queryHelpDesksFindAllRootByCode(String helpCode);

    public HelpDesks createDefaultChildHelpDesks(HelpDesks parent);

    public HelpDesks queryHelpDesksFindByCode(String helpCode);

    List<HelpDesks> queryHelpDesksFindAllByRange(int firstResult, 
                                                 int maxResults);

    Number queryHelpDesksFindAllSize();

    void removeHelpDesks(HelpDesks helpDesks);

    NewsCategories queryNewsCategoriesFindByNcaId(Long ncaId) ;
    
    List<NewsCategories> queryNewsCategoriesFindByNcaIdParent(Long ncaIdParent) ;
    
    List<NewsCategories> queryNewsCategoriesFindByCategoryCodeParent(String categoryCode) ;
    
    List<NewsCategories> queryNewsCategoriesFindAll();
    
    List<News> queryNewsFindByCategoryCodeAndBookmarkCode(String categoryCode, 
                                                                     String bookmarkCode);
    
    List<NewsCategories> queryNewsCategoriesFindAllRoot();

    List<NewsCategories> queryNewsCategoriesFindAllByRange(int firstResult, 
                                                           int maxResults);

    Number queryNewsCategoriesFindAllSize();

    void removeNewsCategories(NewsCategories newsCategories);

    List<News> queryNewsFindAll();
    
    News queryNewsFindByNewsId(Long newsId);
    
    List<News> queryNewsFindByNcaId(Long ncaId);
    
    List<News> queryNewsFindByNcaId(Long ncaId, int resultCount);
    
    List<News> queryNewsFindByNcaIdOrderByPriorityLevel(Long ncaId) ;
    
    News queryNewsFindFirstByNcaIdOrderByPriorityLevel(Long ncaId) ;

    List<News> queryNewsFindAllByRange(int firstResult, int maxResults);

    Number queryNewsFindAllSize();

    void removeNews(News news);

    List<NewsRelations> queryNewsRelationsFindAll();

    List<NewsRelations> queryNewsRelationsFindAllByRange(int firstResult, 
                                                         int maxResults);

    Number queryNewsRelationsFindAllSize();

    void removeNewsRelations(NewsRelations newsRelations);

    List<Forums> queryForumsFindAll();
    
    List<Forums> queryForumsFindByOwnerCode(String forumOwnerCode);

    List<Forums> queryForumsFindAllByRange(int firstResult, int maxResults);

    Number queryForumsFindAllSize();

    void removeForums(Forums forums);

    List<ForumOwners> queryForumOwnersFindAll();

    List<ForumOwners> queryForumOwnersFindAllByRange(int firstResult, 
                                                     int maxResults);

    Number queryForumOwnersFindAllSize();

    void removeForumOwners(ForumOwners forumOwners);

    List<ForumCategories> queryForumCategoriesFindAll();

    List<ForumCategories> queryForumCategoriesFindAllByRange(int firstResult, 
                                                             int maxResults);

    Number queryForumCategoriesFindAllSize();

    void removeForumCategories(ForumCategories forumCategories);

    List<ForumMessages> queryForumMessagesFindAll();
    
    List<ForumMessages> queryForumMessagesFindAllRootByFcaId(Long fcaId) ;

    List<ForumMessages> queryForumMessagesFindAllByRange(int firstResult, 
                                                         int maxResults);

    Number queryForumMessagesFindAllSize();

    void removeForumMessages(ForumMessages forumMessages);

    List<ForumMembers> queryForumMembersFindAll();

    List<ForumMembers> queryForumMembersFindAllByRange(int firstResult, 
                                                       int maxResults);

    Number queryForumMembersFindAllSize();

    void removeForumMembers(ForumMembers forumMembers);

    List<NewsBookmarks> queryNewsBookmarksFindAll();

    List<NewsBookmarks> queryNewsBookmarksFindAllByRange(int firstResult, 
                                                         int maxResults);

    Number queryNewsBookmarksFindAllSize();

    void removeNewsBookmarks(NewsBookmarks newsBookmarks);
}
