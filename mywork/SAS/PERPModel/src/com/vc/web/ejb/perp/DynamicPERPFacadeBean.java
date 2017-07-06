package com.vc.web.ejb.perp;

import com.vc.web.PerpSystemErrorCode;
import com.vc.web.PerpSystemException;
import com.vc.web.PerpSystemInfo;
import com.vc.web.SpaOun;
import com.vc.web.ejb.perp.entities.AppUsers;
import com.vc.web.ejb.perp.entities.OrganizationUnits;
import com.vc.web.ejb.perp.entities.SystemParas;
import com.vc.web.perp.GusInfo;
import com.vc.web.perp.ModuleInfo;

import com.vc.web.perp.OunInfo;
import com.vc.web.perp.OunShortInfo;

import com.vc.web.perp.Spa4Oun;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import oracle.jdbc.driver.OracleDriver;


@Stateless(mappedName = "ProjSystem-Model-DynamicPERPFacadeBean")
@Remote
@Local
public class DynamicPERPFacadeBean implements DynamicPERPFacade, 
                                              DynamicPERPFacadeLocal {

    public DynamicPERPFacadeBean() {

    }


    private Connection getConnection(PerpSystemInfo psi) throws PerpSystemException {
        try {
            Class.forName(OracleDriver.class.getName());
            return DriverManager.getConnection(psi.getConnectionString(), 
                                               psi.getDbUser(), 
                                               psi.getDbPwd());
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION);
        } catch (ClassNotFoundException e) {
            throw new PerpSystemException(PerpSystemErrorCode.CLASS_NOT_FOUND);
        }
    }
    // ----------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------

    /** <code>select o from AppUsers o</code> */
    public List<AppUsers> queryAppUsersFindAll(PerpSystemInfo psi) {
        throw new UnsupportedOperationException("Khong ho tro queryAppUser findAll");
    }

    private void close(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            // TODO
        }
    }

    private void close(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
            // TODO
        }
    }

    private void close(Statement stm) {
        try {
            stm.close();
        } catch (Exception e) {
            // TODO
        }
    }

    private String getEncrytPassword(Connection conn, String userName, 
                                     String password) throws PerpSystemException{
        String sql = ("Select APP_SECURE.encrypt_password(?,?)  from dual");
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            this.close(rs);
            this.close(pstm);
        }
        return null;

    }

    public AppUsers queryAppUsersFindByUserNamePassword(PerpSystemInfo psi, 
                                                        String userName, 
                                                        String password) throws PerpSystemException {
        Connection conn = this.getConnection(psi);
        String encrytPass = this.getEncrytPassword(conn, userName, password);
        AppUsers aus = 
            this.queryAppUsersFindByUserNameEncrytPassword(conn, userName, 
                                                           encrytPass);
        this.close(conn);
        return aus;
    }

    private AppUsers queryAppUsersFindByUserNameEncrytPassword(Connection conn, 
                                                               String userName, 
                                                               String password) throws PerpSystemException {

        String sql = "Select Aus_Id " + //1
            "      ,User_Name " + "      ,Password " + "      ,App_Name " + 
            "      ,Create_Date " + "      ,Created_By " + //6
            "      ,Modify_Date " + "      ,Modified_By " + "      ,Emp_Id " + 
            "      ,Pro_Admin " + //10
            "      ,Ki_Admin " + "      ,Timecheck_Admin " + 
            "      ,Forum_Admin " + "      ,Chart_Admin " + //7
            "      ,Status " + 
            "From   App_Users aus where aus.user_name=? and aus.password=?";

        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            pstm.setString(2, password);
            rs = pstm.executeQuery();

            if (rs.next()) {

                AppUsers aus = new AppUsers();
                aus.setAusId(rs.getLong(1));
                aus.setUserName(rs.getString(2));
                aus.setPassword(rs.getString(3));
                aus.setAppName(rs.getString(4));
                aus.setCreateDate(rs.getTimestamp(5));
                aus.setCreatedBy(rs.getString(6));
                aus.setModifyDate(rs.getTimestamp(7));
                aus.setModifiedBy(rs.getString(8));
                aus.setEmployees(null);

                return aus;
            }

        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            this.close(rs);
            this.close(pstm);
        }
        return null;
    }
    // Khong close Connection .

    private OrganizationUnits queryOrganizationUnitsFindRoot(Connection conn)  throws PerpSystemException{
        String sql = 
            "Select oun.oun_id,oun.name ,oun.name from organization_units oun where oun.oun_id_child_of is null";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                System.out.println("TIM THAY ROOT OUN ");
                OrganizationUnits oun = new OrganizationUnits();
                oun.setOunId(rs.getLong(1));
                oun.setOunNumber(rs.getString(2));
                oun.setName(rs.getString(3));
                return oun;
            } else {
                System.out.println("KO TIM THAY ROOT OUN ");
            }
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            close(rs);
            close(pstm);
        }
        return null;
    }

    public List<SpaOun> getSpaOunList(PerpSystemInfo psi) throws PerpSystemException {

        String sql = "Select Spa.Spa_Id\n" +
            "      ,Spa.Code\n" +
            "      ,Spa.Value\n" +
            "      ,Spa.Description\n" +
            "      ,Decode(Spa.Oun_Id,Null,-1,Spa.Oun_Id)\n" +
            "      ,Oun.Oun_Number\n" +
            "      ,Oun.Name\n" +
            "From   System_Paras       Spa\n" +
            "      ,Organization_Units Oun\n" +
            "Where  Spa.Oun_Id = Oun.Oun_Id(+)\n" +
            "And    Spa.Code In ('CO_NAME', 'CO_LOGO', 'CO_ADDRESS', 'CO_TAX_NO',\n" +
            "        'CO_TAXFORM', 'CO_TEL_NO', 'CO_FAX_NO')\n" +
            "Order  By Spa.Oun_Id desc";
        Connection con = this.getConnection(psi);
        ResultSet rs = null;
        PreparedStatement pstm = null;
        SystemParas spa1 = new SystemParas();
        List<SystemParas> list1 = new ArrayList<SystemParas>();
        try {
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                spa1 = new SystemParas();
                spa1.setSpaId(rs.getLong(1));
                spa1.setCode(rs.getString(2));
                spa1.setValue(rs.getString(3));
                spa1.setDescription(rs.getString(4));
                Long ounId = rs.getLong(5);
                if (ounId != null && 
                    ounId != -1) { // Tai sao SQL getLong null --> 0 ??
                    OrganizationUnits oun = new OrganizationUnits();
                    oun.setOunId(ounId);
                    oun.setOunNumber(rs.getString(6));
                    oun.setName(rs.getString(7));
                    spa1.setOrganizationUnits(oun);

                } else {
                    spa1.setOrganizationUnits(null);
                }
                list1.add(spa1);
            }
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        }

        List<SpaOun> list = new ArrayList<SpaOun>();
        SpaOun spaOun = null;
        Long ounId = null;
        int i = 0;
        for (SystemParas spa : list1) {
            OrganizationUnits oun = spa.getOrganizationUnits();
            String spaCode = spa.getCode();
            String value = spa.getValue();
            String desc = spa.getDescription();
            if (oun == null && spaOun == null) {
                System.out.println("Root :" + i++);
                spaOun = new SpaOun();
                OrganizationUnits rootOun = 
                    this.queryOrganizationUnitsFindRoot(con);
                if (rootOun != null) {
                    spaOun.setOunOunId(rootOun.getOunId());
                    spaOun.setOunOunNumber(rootOun.getOunNumber());
                    spaOun.setOunOunName(rootOun.getName());
                }
                spaOun.setRootOun(true);
                list.add(spaOun);
            } else if (oun != null && oun.getOunId() != ounId) {
                System.out.println("Not Root :" + i++);
                spaOun = new SpaOun();
                spaOun.setOunOunId(oun.getOunId());
                spaOun.setOunOunNumber(oun.getOunNumber());
                spaOun.setOunOunName(oun.getName());
                spaOun.setRootOun(false);
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
        this.close(rs);
        this.close(pstm);
        this.close(con);
        return list;
    }


    public void mergeEntity(PerpSystemInfo psi, 
                            Object obj) throws PerpSystemException {
        //TODO Khong su dung trong truong hop 
        throw new PerpSystemException(PerpSystemErrorCode.UNKNOWN);
    }

    public void mergeSystemPara(PerpSystemInfo psi, Long spaId, 
                                String value) throws PerpSystemException {
        Connection conn = this.getConnection(psi);

        String sql = 
            "Update System_Paras spa set spa.value=? where spa.spa_id=?";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, value);
            pstm.setLong(2, spaId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            this.close(pstm);
            this.close(conn);
        }
    }
    // Private Method .

    private SystemParas createSystemParas(EntityManager em, String code, 
                                          String value, String description) {
        SystemParas spa = new SystemParas();
        spa.setCode(code);
        spa.setDescription(description);
        spa.setValue(value);
        em.persist(spa);
        return spa;
    }
    // Private Method .

    private void saveSystemPara(Connection conn, Long spaId, String value)  throws PerpSystemException{
        String sql = 
            "Update System_Paras spa set spa.value=? where spa.spa_id=?";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, value);
            pstm.setLong(2, spaId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            close(pstm);
        }
    }
    // Private Method .

    private void saveOrganizationUnits(Connection conn, Long ounId, 
                                       String name)  throws PerpSystemException{
        String sql = 
            "Update Organization_Units oun set oun.name=? where oun.oun_id=?";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setLong(2, ounId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            close(pstm);
        }
    }

    // TODO Delete ham nay di ...

    public void saveSpaOun(PerpSystemInfo psi, 
                           SpaOun spaOun) throws PerpSystemException {
        Connection conn = this.getConnection(psi);
        SystemParas spa = null;
        if (spaOun.getSpaIdCoAddress() != null) {
            this.saveSystemPara(conn, spaOun.getSpaIdCoAddress(), 
                                spaOun.getCoAddress());
        }

        if (spaOun.getSpaIdCoLogo() != null) {
            this.saveSystemPara(conn, spaOun.getSpaIdCoLogo(), 
                                spaOun.getCoLogo());
        }

        if (spaOun.getSpaIdCoName() != null) {
            this.saveSystemPara(conn, spaOun.getSpaIdCoName(), 
                                spaOun.getCoName());
        }

        if (spaOun.getSpaIdFaxNo() != null) {
            this.saveSystemPara(conn, spaOun.getSpaIdFaxNo(), 
                                spaOun.getCoFaxNo());
        }
        if (spaOun.getSpaIdTaxForm() != null) {
            this.saveSystemPara(conn, spaOun.getSpaIdTaxForm(), 
                                spaOun.getCoTaxForm());
        }
        if (spaOun.getSpaIdTaxNo() != null) {
            this.saveSystemPara(conn, spaOun.getSpaIdTaxNo(), 
                                spaOun.getCoTaxNo());
        }
        if (spaOun.getSpaIdTelNo() != null) {
            this.saveSystemPara(conn, spaOun.getSpaIdTelNo(), 
                                spaOun.getCoTelNo());
        }
        // Save thong tin vao bang Orgainization_Units 
        if (spaOun.getOunOunId() != null) {
            this.saveOrganizationUnits(conn, spaOun.getOunOunId(), 
                                       spaOun.getOunOunName());
        }
        this.close(conn);
    }

    public void setupAccPerior(PerpSystemInfo psi, Timestamp startUseDate, 
                               Timestamp startDate, 
                               Timestamp endDate) throws PerpSystemException {
        Connection conn = this.getConnection(psi);

        String sql = "{?= call Setup_Acc_Perior(?,?,?)}";
        CallableStatement pstm = null;
        try {
            pstm = conn.prepareCall(sql);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            close(pstm);
            close(conn);
        }

    }

    public List<SystemParas> querySystemParasFindByUserPrivilegeChangable(PerpSystemInfo psi) throws PerpSystemException {
        Connection conn = this.getConnection(psi);
        String sql = "Select Spa.Spa_Id\n" +
            "      ,Spa.Value\n" +
            "      ,Spa.Description\n" +
            "      ,Spa.Code\n" +
            "      ,Spa.User_Privilege\n" +
            "      ,Spa.Oun_Id\n" +
            "      ,Oun.Oun_Number\n" +
            "      ,Oun.Name\n" +
            "From   System_Paras       Spa\n" +
            "      ,Organization_Units Oun\n" +
            "Where  spa.user_Privilege in ('U','I') and Oun.Oun_Id(+) = Spa.Oun_Id";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<SystemParas> list = new ArrayList<SystemParas>();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            SystemParas spa = null;
            while (rs.next()) {
                spa = new SystemParas();
                spa.setSpaId(rs.getLong(1));
                spa.setValue(rs.getString(2));
                spa.setDescription(rs.getString(3));
                spa.setCode(rs.getString(4));
                spa.setUserPrivilege(rs.getString(5));
                Long ounId = rs.getLong(6);
                if (ounId != null) {
                    OrganizationUnits oun = new OrganizationUnits();
                    oun.setOunId(ounId);
                    oun.setOunNumber(rs.getString(7));
                    oun.setName(rs.getString(8));
                    spa.setOrganizationUnits(oun);
                }
                list.add(spa);
            }
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        }
        this.close(conn);
        return list;
    }

    public List<OunShortInfo> getOunShortInfoList(PerpSystemInfo psi) throws PerpSystemException {
        String sql = "Select Oun.Oun_Id\n" +
            "      ,Oun.Oun_Id_Child_Of\n" +
            "      ,Oun.Oun_Number\n" +
            "      ,Oun.Name\n" +
            "From   Organization_Units Oun\n" +
            "Connect By Prior Oun.Oun_Id = Oun.Oun_Id_Child_Of\n" +
            "Start  With Oun.Oun_Id_Child_Of Is Null";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<OunShortInfo> list = new ArrayList<OunShortInfo>();
        Map<Long, OunShortInfo> map = new HashMap<Long, OunShortInfo>();
        OunShortInfo root = null;
        try {
            conn = this.getConnection(psi);
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            int i = 0;
            while (rs.next()) {
                Long ounId = rs.getLong(1);
                Long parentId = rs.getLong(2);
                //TODO : ()
                parentId = parentId == 0 ? null : parentId;
                String ounNumber = rs.getString(3);
                String ounName = rs.getString(4);
                OunShortInfo child = 
                    new OunShortInfo(ounId, parentId, ounNumber, ounName);
                map.put(ounId, child);
                if (i++ == 0) {
                    root = child;
                } else {
                    OunShortInfo parent = map.get(parentId);
                    parent.addChild(child);
                }
            }

        } catch (Exception e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            this.close(rs);
            this.close(pstm);
            this.close(conn);
        }
        if (root != null) {
            list.add(root);
        }
        return list;
    }


    public List<OunInfo> getOunInfoList(PerpSystemInfo psi) throws PerpSystemException {
        String sql = 
            "With Country_Table as ( " + "   Select C1.Rv_Low_Value Country_Code " + 
            "         ,C1.Rv_Meaning   Country_Name " + 
            "   From   Cg_Ref_Codes C1 " + 
            "   Where  C1.Rv_Domain = 'COUNTRY' " + "), " + 
            "Province_Table as ( " + 
            "  Select C1.Rv_Low_Value Province_Code " + 
            "        ,C1.Rv_Meaning   Province_Name " + 
            "  From   Cg_Ref_Codes C1 " + 
            "  Where  C1.Rv_Domain = 'PROVINCE' " + "), " + 
            "Org_Type_Table as ( " + 
            "  Select C1.Rv_Low_Value oun_Type_Code " + 
            "        ,C1.Rv_Meaning   oun_Type_Name " + 
            "  From   Cg_Ref_Codes C1 " + 
            "  Where  C1.Rv_Domain = 'ORG TYPE' " + ") " + 
            "Select Oun.Oun_Id " + "      ,Oun.Oun_Id_Child_Of " + 
            "      ,Oun.Oun_Number " + "      ,Oun.Name Oun_Name " + 
            "      ,c.Country_Code " + "      ,c.Country_Name " + 
            "      ,p.Province_Code " + "      ,p.Province_Name " + 
            "      ,o.Oun_Type_Code " + "      ,o.Oun_Type_Name " + 
            "      ,Oun.Address " + "      ,Oun.Effective_Date_From " + 
            "      ,Oun.Effective_Date_To " + "      ,Oun.Description " + 
            "From   Organization_Units Oun " + "      ,Country_Table      c " + 
            "      ,Province_Table     p " + "      ,Org_Type_Table     o " + 
            "Where  p.Province_Code(+) = Oun.Province " + 
            "And    c.Country_Code(+) = Oun.Country " + 
            "And    o.Oun_Type_Code(+) = Oun.Oun_Type " + 
            "Connect By Prior Oun.Oun_Id = Oun.Oun_Id_Child_Of " + 
            "Start  With Oun.Oun_Id_Child_Of Is Null ";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<OunInfo> list = new ArrayList<OunInfo>();
        Map<Long, OunInfo> map = new HashMap<Long, OunInfo>();
        OunInfo root = null;
        try {
            conn = this.getConnection(psi);
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            int i = 0;
            while (rs.next()) {
                Long ounId = rs.getLong(1);
                Long parentId = rs.getLong(2);
                //TODO : ()
                parentId = parentId == 0 ? null : parentId;
                String ounNumber = rs.getString(3);
                String ounName = rs.getString(4);
                String countryCode = rs.getString(5);
                String countryName = rs.getString(6);
                String provinceCode = rs.getString(7);
                String provinceName = rs.getString(8);
                String ounTypeCode = rs.getString(9);
                String ounTypeName = rs.getString(10);
                String address = rs.getString(11);
                Timestamp effectiveDateFrom = rs.getTimestamp(12);
                Timestamp effectiveDateTo = rs.getTimestamp(13);
                String description = rs.getString(14);
                OunInfo child = 
                    new OunInfo(ounId, parentId, ounNumber, ounName, 
                                countryCode, countryName, provinceCode, 
                                provinceName, ounTypeCode, ounTypeName, 
                                address, effectiveDateFrom, effectiveDateTo, 
                                description);
                map.put(ounId, child);
                if (i++ == 0) {
                    root = child;
                } else {
                    OunInfo parent = map.get(parentId);
                    parent.addChild(child);
                }
            }

        } catch (Exception e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            this.close(rs);
            this.close(pstm);
            this.close(conn);
        }
        if (root != null) {
            list.add(root);
        }
        return list;
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
            "  FROM MENUS M\n" +
            " WHERE M.Men_id IN\n" +
            "       (SELECT MEN.MEN_ID\n" +
            "          FROM MENUS MEN, RESPONSIBILITIES RES\n" +
            "         WHERE RES.GUS_ID IN (SELECT Uro.GUS_ID\n" +
            "                                FROM APP_USERS AUS, USER_ROLES URO\n" +
            "                               WHERE URO.Aus_id = AUS.Aus_id\n" +
            "                                 AND Uro.Gus_Id = " + gusId + 
            "                                 AND AUS.Aus_id = " + ausId + 
            ")\n" +
            "           AND RES.Men_id = MEN.Men_id)\n" +
            "CONNECT BY PRIOR M.Men_id = M.Men_id_Child_of\n" +
            " START WITH M.Men_id = ( Select x.Men_Id From Menus x Where x.Menu_Name ='" + 
            packageCode + "')" + 
            " ORDER BY TO_NUM(Menu_number, 'MENU NUMBER')";
        System.out.println("SQL :"+sql);
        Connection conn = this.getConnection(psi);
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            int i = 0;
            TreeMap<Long, ModuleInfo> map = new TreeMap<Long, ModuleInfo>();
            ModuleInfo root = null;
            while (rs.next()) {
                Long menId = rs.getLong(1);
                Long menIdChildOf2 = rs.getLong(2);
                String menuName = rs.getString(3);
                String menuLabel = rs.getString(4);
                String menuDescription = rs.getString(5);
                ModuleInfo module = 
                    new ModuleInfo(menId, menIdChildOf2, menuName, menuLabel, 
                                   menuDescription, gusId);
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
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            this.close(rs);
            this.close(pstm);
            this.close(conn);
        }
        return new ArrayList<ModuleInfo>();
    }

    public List<GusInfo> getGusInfoList(PerpSystemInfo psi, 
                                        Long ausId) throws PerpSystemException {
        String sql = 
            "SELECT gus.gus_id, uro.aus_id, gus.name, gus.description\n" +
            "  FROM user_roles uro, group_users gus\n" +
            " WHERE uro.gus_id = gus.gus_id\n" +
            "   AND uro.aus_id = ?";
        Connection conn = this.getConnection(psi);
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<GusInfo> gusList = new ArrayList<GusInfo>();
        GusInfo gusInfo = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, ausId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Long gusId = rs.getLong(1);
                String gusName = rs.getString(3);
                gusInfo = new GusInfo();
                gusInfo.setAusId(ausId);
                gusInfo.setGusName(gusName);
                gusInfo.setGusId(gusId);
                gusList.add(gusInfo);
            }
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            this.close(rs);
            this.close(pstm);
            this.close(conn);
        }
        return gusList;
    }

    public List<ModuleInfo> getModuleInfoListSearchResult(PerpSystemInfo psi, 
                                                          Long ausId, 
                                                          Long gusId, 
                                                          String searchText) throws PerpSystemException {
        String sql = "Select Men.Men_Id\n" +
            "      ,Men.Men_Id_Child_Of\n" +
            "      ,Men.Menu_Name\n" +
            "      ,Men.Menu_Lable\n" +
            "      ,Men.Menu_Description\n" +
            "      ,Men.Wbs_Level\n" +
            "      ,Men.Menu_Number\n" +
            "      ,Men.App_Code\n" +
            "From   Menus Men\n" +
            "Where  (Lower(Men.Menu_Lable) Like '%" + searchText + "%' Or\n" +
            "       Lower(Men.Menu_Name) Like '%" + searchText + "%')\n" +
            "And    Men.Is_Parent = 'N'\n" +
            "Connect By Prior Men.Men_Id = Men.Men_Id_Child_Of\n" +
            "Start  With Men.Men_Id =\n" +
            "            (Select Men_Id\n" +
            "             From   Menus\n" +
            "             Where  Menu_Name = (Select Gus.App_Code\n" +
            "                                 From   Group_Users Gus\n" +
            "                                       ,User_Roles  Uro\n" +
            "                                 Where  Gus.Gus_Id = " + gusId + 
            "                                 And    Gus.Gus_Id = Uro.Gus_Id\n" +
            "                                 And    Uro.Aus_Id = " + ausId + 
            "))\n" +
            "Order  By Men.Menu_Name";

        Connection conn = this.getConnection(psi);
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ModuleInfo> list = new ArrayList<ModuleInfo>();
        ModuleInfo m = null;
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                m = new ModuleInfo();
                m.setModuleId(rs.getLong(1));
                m.setModuleIdChildOf(rs.getLong(2));
                m.setModuleName(rs.getString(3));
                m.setModuleLabel(rs.getString(4));
                m.setModuleDescription(rs.getString(5));
                m.setGusId(gusId);
                list.add(m);
            }

        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            this.close(rs);
            this.close(pstm);
            this.close(conn);
        }
        return list;
    }


    private void saveSystemPara(Connection conn, Long ounId, boolean root, 
                                String code, 
                                String value) throws SQLException {
        PreparedStatement pstm = null;
        try {
            if (value != null) {
                String usql = 
                    "Update System_Paras spa set spa.value=? where spa.code=? " + 
                    (root ? " And spa.Oun_Id is null " : 
                     " And spa.Oun_Id=" + ounId);
                pstm = conn.prepareStatement(usql);
                pstm.setString(1, value);
                pstm.setString(2, code);
                int count = pstm.executeUpdate();
                if (count == 0) { // thuc hien insert ...
                    String isql = 
                        "Insert into System_Paras (Spa_Id,Oun_Id,Code,Value,Description) values (Spa_Seq.nextval,?,?,?,?)";
                    pstm = conn.prepareStatement(isql);
                    pstm.setLong(1, root ? null : ounId);
                    pstm.setString(2, code);
                    pstm.setString(3, value);
                    pstm.setString(4, null);
                    pstm.executeUpdate();
                }
            } else if (!root) {
                // Chi xoa neu khong phai root ...
                String dsql = 
                    "Delete from System_Paras spa where Spa.Code=? And Spa.Oun_Id=?";
                pstm = conn.prepareStatement(dsql);
                pstm.setString(1, code);
                pstm.setLong(2, ounId);
                pstm.executeUpdate();
            }
        } finally {
            this.close(pstm);
        }
    }


    public void saveSpa4Oun(PerpSystemInfo psi, 
                            Spa4Oun spa4Oun) throws PerpSystemException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = this.getConnection(psi);
            this.saveSystemPara(conn, spa4Oun.getOunId(), spa4Oun.isRoot(), 
                                Spa4Oun.CO_ADDRESS, spa4Oun.getCoAddress());
            this.saveSystemPara(conn, spa4Oun.getOunId(), spa4Oun.isRoot(), 
                                Spa4Oun.CO_LOGO, spa4Oun.getCoLogo());
            this.saveSystemPara(conn, spa4Oun.getOunId(), spa4Oun.isRoot(), 
                                Spa4Oun.CO_NAME, spa4Oun.getCoName());
            this.saveSystemPara(conn, spa4Oun.getOunId(), spa4Oun.isRoot(), 
                                Spa4Oun.FAX_NO, spa4Oun.getCoFaxNo());
            this.saveSystemPara(conn, spa4Oun.getOunId(), spa4Oun.isRoot(), 
                                Spa4Oun.TAX_FORM, spa4Oun.getCoTaxForm());
            this.saveSystemPara(conn, spa4Oun.getOunId(), spa4Oun.isRoot(), 
                                Spa4Oun.TAX_NO, spa4Oun.getCoTaxNo());
            this.saveSystemPara(conn, spa4Oun.getOunId(), spa4Oun.isRoot(), 
                                Spa4Oun.TEL_NO, spa4Oun.getCoTelNo());
        } catch (Exception e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        }
    }

    public Spa4Oun getSpa4Oun(PerpSystemInfo psi, Long ounId, 
                              boolean root) throws PerpSystemException {
        String sql = 
            "Select Spa.Code ,Spa.Value From  System_Paras Spa Where Spa.Code In " + 
            " ('CO_NAME', 'CO_LOGO', 'CO_ADDRESS', 'TAX_NO', 'TAX_FORM','TEL_NO', 'FAX_NO') ";
        if (!root) {
            sql += " And Spa.Oun_Id=" + ounId;
        } else {
            sql += " And spa.Oun_id Is null";
        }
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Spa4Oun spa4Oun = new Spa4Oun();
        try {
            conn = this.getConnection(psi);
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            spa4Oun.setOunId(ounId);
            spa4Oun.setRoot(root);
            while (rs.next()) {
                String code = rs.getString(1);
                String value = rs.getString(2);
                if (code.equals(Spa4Oun.CO_NAME)) {
                    spa4Oun.setCoName(value);
                } else if (code.equals(Spa4Oun.CO_LOGO)) {
                    spa4Oun.setCoLogo(value);
                } else if (code.equals(Spa4Oun.CO_ADDRESS)) {
                    spa4Oun.setCoAddress(value);
                } else if (code.equals(Spa4Oun.TAX_NO)) {
                    spa4Oun.setCoTaxNo(value);
                } else if (code.equals(Spa4Oun.TAX_FORM)) {
                    spa4Oun.setCoTaxForm(value);
                } else if (code.equals(Spa4Oun.TEL_NO)) {
                    spa4Oun.setCoTelNo(value);
                } else if (code.equals(Spa4Oun.FAX_NO)) {
                    spa4Oun.setCoFaxNo(value);
                }
            }
        } catch (Exception e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            this.close(rs);
            this.close(pstm);
            this.close(conn);
        }
        return spa4Oun;
    }

    public void removeOrganizationUnit(PerpSystemInfo psi, 
                                       OunInfo ounInfo) throws PerpSystemException {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            if (ounInfo != null && ounInfo.getOunId() != null) {
                conn = this.getConnection(psi);
                String sql = 
                    "Delete from Organization_Units oun where oun.oun_Id=?";
                pstm = conn.prepareStatement(sql);
                pstm.setLong(1, ounInfo.getOunId());
                pstm.executeUpdate();
            }
        } catch (Exception e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            this.close(pstm);
            this.close(conn);
        }
    }

    public void createDefaultChildOranizationUnit(PerpSystemInfo psi, 
                                                  Long parentOunId, 
                                                  String userName) throws PerpSystemException {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {

            String sql = 
                "Insert Into Organization_Units  " + "   (Oun_Id  " + "   ,Oun_Number  " + 
                "   ,Oun_Type  " + "   ,Name  " + "   ,External_Flag  " + 
                "   ,Ops_Flag  " + "   ,Country  " + "   ,Oun_Id_Child_Of  " + 
                "   ,Province  " + "   ,Address  " + 
                "   ,Effective_Date_From  " + "   ,Effective_Date_To  " + 
                "   ,Description  " + "   ,Create_Date  " + 
                "   ,Created_By  " + "   ,Modify_Date  " + 
                "   ,Modified_By  " + "   ,Emp_Id  " + "   ,Telephone  " + 
                "   ,Cce_Id  " + "   ,Hq_Flag)  " + "Values  " + 
                "   (Oun_Seq.Nextval  " + "   ,to_char(Oun_Seq.Currval)   " + 
                "   ,'CN'  " + "   ,'Chi nhanh ... '   " + "   ,'N'   " + 
                "   ,'N'   " + "   ,'VN'   " + "   ,?   " + "   ,Null   " + 
                "   ,Null   " + "   ,Sysdate   " + "   ,Null   " + 
                "   ,Null  " + "   ,Sysdate   " + "   ,?  " + "   ,Null  " + 
                "   ,Null  " + "   ,Null  " + "   ,Null  " + "   ,Null  " + 
                "   ,'N'  " + "    )  ";
          
            conn = this.getConnection(psi);
            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, parentOunId);
            pstm.setString(2, userName);
            pstm.executeUpdate();
        } catch (Exception e) {
            throw new PerpSystemException(PerpSystemErrorCode.SQL_EXCEPTION, 
                                          e.getMessage());
        } finally {
            this.close(pstm);
            this.close(conn);
        }
    }
}
