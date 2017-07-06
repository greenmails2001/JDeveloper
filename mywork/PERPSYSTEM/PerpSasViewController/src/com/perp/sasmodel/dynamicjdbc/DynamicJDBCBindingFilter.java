package com.perp.sasmodel.dynamicjdbc;

import java.io.IOException;
import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;

import oracle.adf.model.servlet.ADFBindingFilter;

import oracle.jbo.DMLException;
import oracle.jbo.common.JBOClass;
import oracle.jbo.common.ampool.ApplicationPoolException;


/**
 * This class extends the ADFBindingFilter to:
 *
 *    (1) Intercept dynamic credentials username/password information
 *        (easily extendible to include datasource/jdbcURL info, too)
 *        and set that information on the HTTP session where the
 *        DynamicJDBCSessionCookieFactory can access it in order to pass
 *        it to the constructor of the DynamicJDBCEnvInfoProvider that it
 *        creates and sets on the DynamicHttpSessionCookieImpl class.
 *
 *    (2) Track whether a user is logged in or not with an HTTP Session
 *        attribute, and if not logged in forward to the login page
 *        (whose physical name is retrieved as a web.xml context parameter
 *        named "RedirectToLogin"
 *
 *    (3) Catch failed login attempts and redirect the user to the login
 *        page after setting a flag that indicates that a login failed.
 *
 * This class will be used at runtime when you modify the web.xml of a
 * given application to use this DynamicJDBCBindingFilter instead of the
 * default ADFBindingFilter. Study the web.xml file for each "viewcontroller"
 * project in this workspace for an example.
 *
 *   <filter>
 *     <filter-name>ADFBindingFilter</filter-name>
 *     <filter-class>test.DynamicJDBCBindingFilter</filter-class>
 *   </filter>
 */
public class DynamicJDBCBindingFilter extends ADFBindingFilter implements Serializable {
    private static final String NON_NULL_VALUE = "x";
    private static final String LOGIN_PAGE_REDIRECT_PARAM = "RedirectToLogin";
    private static final String FACES_URL_PATTERN_PARAM = "FacesURLPattern";
    private static final String FAILED_ATTR = "failed";
    private static final String FORM_PREFIX = "form:";
    //private static final String USERNAME_PARAM = "username";
    //private static final String PASSWORD_PARAM = "password";
    //private static final String JDBCURL_PARAM = "jdbcurl";
    private static final String IS_LOGIN_PAGE_PARAM = "_loginpage";
    private static final String APP_POOL_ERR_MSG = "JBO-30003";
    private static final String DBCONN_ERR_MSG = "JBO-26061";
    private String loginPageRedirectName = null;
    private String facesURLPattern = null;

    public DynamicJDBCBindingFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException,
                                                   ServletException {    
        HttpServletRequest svrRequest = (HttpServletRequest)request;
        HttpSession session = svrRequest.getSession(true);
        if (PerpSasLoginHelper.envInfoFound(session)) {   
            //Neu loi tai day co nghia la Username/Pass vao DB Perp ko chinh xac.
            super.doFilter(request, response, chain);
        } else {
            String requestUri = svrRequest.getRequestURI();

            if (requestUri.endsWith(this.loginPageRedirectName())) {                
                super.doFilter(request, response, chain);              
            } else if (requestUri.endsWith(".js") ||
                       requestUri.endsWith(".css")) {
                super.doFilter(request, response, chain);
            } else {
                System.out.println("FILTER ==> Env Not Found");
                forwardToLoginPage(request, response);
            }
        }
    }  

    private void handleFailedLoginAttempt(HttpServletRequest request,
                                          ServletResponse response) throws ServletException,
                                                                           IOException {
        try {
            response.resetBuffer();
            signalFailedLoginAttempt(request);
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            redirectToLoginPageOnLogonError(request, response);
        }
    }

    /**
     * Encapsulate detecting if the exception thrown
     * represents a failed login attempt.
     */
    private boolean isFailedLoginException(Exception e) {
        /*
     * In 9.0.5.2, our eager AM acquisition will
     * cause the application pool exception to appear
     * without being wrapped.
     */
        if (e instanceof ApplicationPoolException ||
            e instanceof BlankUserNameOrPassword) {
            return true;
        } else if (e instanceof DMLException) {
            Object[] details = ((DMLException)e).getDetails();
            if (details != null && details.length > 0) {
                if (details[0] instanceof SQLException) {
                    SQLException s = (SQLException)details[0];
                    if (DynamicJDBCLoginHelper.isFailedDBConnectErrorCode(s)) {
                        return true;
                    }
                }
            }
        } else if (e instanceof ServletException) {
            Throwable rootCause = ((ServletException)e).getRootCause();
            /*
       * In 10.1.2 Struts, our lazy AM acquisition causes
       * the failed database login to be wrapped in a
       * ServletException that contains the ApplicationPoolException
       */
            if (rootCause instanceof ApplicationPoolException) {
                return true;
            }
            /*
       * In 10.1.2 JSP Model 1, our lazy AM acquisition causes
       * the failed database login to be wrapped in a
       * ServletException that contains a JspTagException.
       * The JspTagException does not wrap the original error,
       * but only encapsulate the original errors getMessage() value.
       */
            if (rootCause instanceof JspTagException) {
                String errMsg = ((JspTagException)rootCause).getMessage();
                if (errMsg != null &&
                    (errMsg.contains(APP_POOL_ERR_MSG) || errMsg.contains(DBCONN_ERR_MSG))) {
                    return true;
                }
            }
            if (rootCause instanceof DMLException) {
                Object[] details = ((DMLException)rootCause).getDetails();
                if (details != null && details.length > 0) {
                    if (details[0] instanceof SQLException) {
                        SQLException s = (SQLException)details[0];
                        if (DynamicJDBCLoginHelper.isFailedDBConnectErrorCode(s)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Forwards control to the login page.
     */
    private void forwardToLoginPage(ServletRequest request,
                                    ServletResponse response) throws ServletException,
                                                                     IOException {
        HttpServletResponse responseHttp = (HttpServletResponse)response;
        responseHttp.sendRedirect(loginPageRedirectName());
        markResponseCompleteIfUsingJSF();
    }

    private void redirectToLoginPage(ServletRequest request,
                                     ServletResponse response) throws ServletException,
                                                                      IOException {
        HttpServletResponse responseHttp = (HttpServletResponse)response;
        responseHttp.sendRedirect(loginPageRedirectName());
        markResponseCompleteIfUsingJSF();
    }

    private void redirectToLoginPageOnLogonError(ServletRequest request,
                                                 ServletResponse response) throws ServletException,
                                                                                  IOException {
        HttpServletResponse responseHttp = (HttpServletResponse)response;
        responseHttp.sendRedirect(loginPageRedirectName((HttpServletRequest)request) +
                                  "?failed=true");
        markResponseCompleteIfUsingJSF();
    }

    /**
     * Signal a failed login attempt
     */
    private void signalFailedLoginAttempt(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        DynamicJDBCLoginHelper.logOut(session);
        request.setAttribute(FAILED_ATTR, NON_NULL_VALUE);
        session.invalidate();
    }
    /*
     * If we are running in a Faces environment, invoke
     * the FacesContext.responseComplete() method after
     * the session invalidate. We use Java reflection
     * so that our code can still work in a Non-Faces
     * environment, too.
     */

    private void markResponseCompleteIfUsingJSF() {
        try {
            Class c = JBOClass.forName("javax.faces.context.FacesContext");
            Method m = c.getMethod("getCurrentInstance", null);
            Object obj = m.invoke(null, null);
            if (obj != null) {
                m = c.getMethod("responseComplete", null);
                m.invoke(obj, null);
            }
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            // Ignore, we're not running in a faces context.
        }
    }


    /**
     * Returns true if the current request is a login request.
     */
    private boolean isLoginRequest(HttpServletRequest request) {
        return getRequestParamWithOptionalFormPrefix(IS_LOGIN_PAGE_PARAM,
                                                     request) != null &&
            request.getMethod().equalsIgnoreCase("POST");
    }

    private String loginPageRedirectName() {
        return loginPageRedirectName;
    }

    private String loginPageRedirectName(HttpServletRequest request) {
        if (facesURLPattern != null) {
            if (request.getRequestURI().indexOf(facesURLPattern) == -1) {
                return facesURLPattern + loginPageRedirectName;
            }
        }
        return loginPageRedirectName;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        loginPageRedirectName =
                filterConfig.getServletContext().getInitParameter(LOGIN_PAGE_REDIRECT_PARAM);
        facesURLPattern =
                filterConfig.getServletContext().getInitParameter(FACES_URL_PATTERN_PARAM);
        //System.out.println("loginPageRedirectName :" + loginPageRedirectName);
        //System.out.println("facesURLPattern :" + facesURLPattern);
    }

    private String getRequestParamWithOptionalFormPrefix(String paramName,
                                                         HttpServletRequest req) {
        Object val = req.getParameter(paramName);
        if (val == null) {
            val = req.getParameter(FORM_PREFIX + paramName);
        }
        String ret = (String)val;
        if (ret != null && ret.length() == 0) {
            ret = null;
        }
        return ret;
    }
}
