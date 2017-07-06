package com.vc.web;

import com.sun.xml.bind.v2.util.DataSourceSource;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Timestamp;

import javax.naming.Context;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.persistence.Column;

import javax.sql.DataSource;

import oracle.jdbc.driver.OracleDriver;


public class PerpSystemInfo implements Serializable, 
                                       Comparable<PerpSystemInfo> {
    @Column(name = "DB_HOST")
    private String dbHost = "localhost"; // "192.168.0.123";
    @Column(name = "DB_PORT")
    private Integer dbPort = 1521;
    @Column(name = "SID")
    private String sid = "db10g";
    @Column(name = "DB_USER")
    private String dbUser = "pdc1";
    @Column(name = "DB_PWD")
    private String dbPwd = "pdc1";
    //--------------------
    @Column(name = "ENV_ID")
    private Long envId;
    @Column(name = "SUB_ID")
    private Long subId;
    @Column(name = "ACTIVATED_CODE")
    private String activatedCode = "activated1";
    @Column(name = "APP_HOST")
    private String appHost;
    @Column(name = "SETUP_DATE")
    private Timestamp setupDate;
    @Column(name = "REMOVED_DATE")
    private Timestamp removedDate;
    @Column(name = "CURRENT_NUM_OF_APP_USER")
    private Long currentNumOfAppUser;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "APP_PORT")
    private Integer appPort;

    private String dsJndi;

    public PerpSystemInfo() {
    }

    public String getConnectionString() {
        return "jdbc:oracle:thin:@" + dbHost + ":" + dbPort + ":" + sid;
    }


    public void setActivatedCode(String activatedCode) {
        this.activatedCode = activatedCode;
    }

    public String getActivatedCode() {
        return activatedCode;
    }


    public int compareTo(PerpSystemInfo o) {
        if (o == null) {
            return 1;
        }
        int c = this.compare(this.dbHost, o.getDbHost());
        if (c != 0) {
            return c;
        }
        c = this.dbPort - o.getDbPort();
        if (c != 0) {
            return c;
        }
        c = this.compare(this.sid, o.getSid());
        if (c != 0) {
            return c;
        }
        c = this.compare(this.dbUser, o.getDbUser());
        if (c != 0) {
            return c;
        }
        c = this.compare(this.dbPwd, o.getDbPwd());
        if (c != 0) {
            return c;
        }
        return 0;
    }

    private int compare(String s1, String s2) {
        if (s1 != null) {
            return s1.compareTo(s2);
        } else if (s2 != null) {
            return -s2.compareTo(s1);
        }
        return 0;
    }

    public String toString() {
        return "[url,host,port,databaseName,userName,password]=[" + 
            this.getConnectionString() + "," + this.dbHost + "," + 
            this.dbPort + "," + this.sid + "," + this.dbUser + "," + 
            this.dbPwd + "]";
    }


    public void setSetupDate(Timestamp setupDate) {
        this.setupDate = setupDate;
    }

    public Timestamp getSetupDate() {
        return setupDate;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAppPort(Integer appPort) {
        this.appPort = appPort;
    }

    public Integer getAppPort() {
        return appPort;
    }

    public void setAppHost(String appHost) {
        this.appHost = appHost;
    }

    public String getAppHost() {
        return appHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPort(Integer dbPort) {
        this.dbPort = dbPort;
    }

    public Integer getDbPort() {
        return dbPort;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Long getSubId() {
        return subId;
    }

    public void setEnvId(Long envId) {
        this.envId = envId;
    }

    public Long getEnvId() {
        return envId;
    }

    public void setRemovedDate(Timestamp removedDate) {
        this.removedDate = removedDate;
    }

    public Timestamp getRemovedDate() {
        return removedDate;
    }

    public void setCurrentNumOfAppUser(Long currentNumOfAppUser) {
        this.currentNumOfAppUser = currentNumOfAppUser;
    }

    public Long getCurrentNumOfAppUser() {
        return currentNumOfAppUser;
    }

    public void setDsJndi(String dsJndi) {
        this.dsJndi = dsJndi;
    }

    public String getDsJndi() {
        return dsJndi;
    }

    private Connection getConnection(PerpSystemInfo psi) throws PerpSystemException {
        Context context = null;
        try {
            context = new InitialContext();
        } catch (NamingException e) {
            throw new PerpSystemException(PerpSystemErrorCode.DS_NOT_FOUND, 
                                          e.getMessage());
        }
        DataSource ds = null;
        try {
            ds = (DataSource)context.lookup(psi.getDsJndi());
        } catch (NamingException e) {
            throw new PerpSystemException(PerpSystemErrorCode.DS_NOT_FOUND);
        }
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new PerpSystemException(PerpSystemErrorCode.NOT_GET_DS_CONNECTION);
        }
    }
}
