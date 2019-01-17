package nhs.esr.ebiz.security;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import oracle.adf.share.ADFContext;

import oracle.apps.fnd.ext.common.EBiz;


public class EBizUtil {
    private static final Logger logger =   Logger.getLogger(EBizUtil.class.getName());
    private static EBiz INSTANCE = null;
    static {
        Connection connection = null;
        try {
            connection = ConnectionProvider.getConnection();
            // DO NOT hard code applServerID for a real application
            // Get applServerID as CONTEXT-PARAM from web.xml or      elsewhere
            ServletContext servletcxt =
                (ServletContext)ADFContext.getCurrent().getEnvironment().getContext();
            String applServerId =
                servletcxt.getInitParameter("APPL_SERVER_ID");
            System.out.println("...applServerId..+++ " + applServerId);
            INSTANCE = new EBiz(connection, applServerId);
        } catch (SQLException e) {
            logger.log(Level.SEVERE,
                       "SQLException while creating EBiz instance -->", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                       "Exception while creating EBiz instance -->", e);
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
            }
        }
    }

    public static EBiz getEBizInstance() {
           return INSTANCE;
    }
}
