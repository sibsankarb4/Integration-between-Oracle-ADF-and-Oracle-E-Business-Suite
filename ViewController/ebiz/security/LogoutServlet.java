package nhs.esr.ebiz.security;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import oracle.apps.fnd.ext.common.AppsRequestWrapper;
import oracle.apps.fnd.ext.common.AppsSessionHelper;
import oracle.apps.fnd.ext.common.Session;

public class LogoutServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        System.out.println("...entering into LogoutServlet.......");
        AppsRequestWrapper wrappedRequest = null;
        Connection EBSconn = null;
        try {
            EBSconn = ConnectionProvider.getConnection();
            wrappedRequest =
                    new AppsRequestWrapper(request, response, EBSconn, EBizUtil.getEBizInstance());


            Session session = wrappedRequest.getAppsSession();

            if (session != null) {
                AppsSessionHelper helper =
                    new AppsSessionHelper(wrappedRequest.getEbizInstance());
                helper.destroyAppsSession(wrappedRequest.getAppsSession(),
                                          wrappedRequest, response);
            }

            request.getSession(true).invalidate();

            String agent = null;
            agent = wrappedRequest.getEbizInstance().getAppsServletAgent();
            response.sendRedirect(agent + "AppsLocalLogin.jsp");
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (EBSconn != null) {
                try {
                    EBSconn.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }

            if (wrappedRequest != null) {
                try {
                    wrappedRequest.getConnection().close();
                } catch (SQLException e3) {
                    e3.getMessage();
                    wrappedRequest = null;
                }
            }
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        System.out.println("...entering into LogoutServlet.......");
        doGet(request, response);
    }
}
