package esr.nhs.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class CoreFormNavigationBean {
    public CoreFormNavigationBean() {
    }
    
    //getting resp id, appliocation id, person id, effective date from session
    int respId =  Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("responsibilityId")));
    int applId = Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("applicationId")));
    int assignmentId =Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("assignmentId"));
    java.sql.Date effectiveDate = new java.sql.Date(((java.util.Date)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("p_effective_date")).getTime());
    

    public void costingHRVersion(ActionEvent actionEvent) {
        
        String sqlQueryforUrl = "SELECT fnd_run_function.get_run_function_url  (p_function_id =>fnd_function.get_function_id('PERWSEAC'),p_resp_appl_id=>?,p_resp_id=>?," +
                                "p_security_group_id=> fnd_global.security_group_id,p_parameters=>'NODE_ID='|| TO_CHAR (?,'fm9999999999999')|| ' SESSION_DATE_FROM='|| TO_char (? +10,'YYYY/MM/DD')," + 
                             "p_lang_code=>'US') as URL FROM DUAL" ;

        Connection connection = null;
        PreparedStatement statement = null;
        try {
           // connection = ADFUtil.getConnection();
           connection = getConnection();
            statement = connection.prepareStatement(sqlQueryforUrl);
            statement.setInt(1, applId);
            statement.setInt(2, respId);
            statement.setInt(3, assignmentId);
            statement.setDate(4, effectiveDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String url = (String)rs.getObject("URL");
                System.out.println("....url...."+url);
                if (url != null && url.trim().length() > 0) {

                    FacesContext facesContext =
                        FacesContext.getCurrentInstance();
                    ExtendedRenderKitService service =
                        Service.getRenderKitService(facesContext,
                                                    ExtendedRenderKitService.class);
                    service.addScript(facesContext,
                                      "window.open('" + url + "')");

                }
                break; //as we are expecting only one row
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
      
    }
    

    public void elementEntriesHRVersion(ActionEvent actionEvent) {
        
        String sqlQueryforUrl = "SELECT fnd_run_function.get_run_function_url  (p_function_id =>fnd_function.get_function_id('PAYWSMEE'),p_resp_appl_id=>?,p_resp_id=>?," +
                                "p_security_group_id=> fnd_global.security_group_id,p_parameters=>'NODE_ID='|| TO_CHAR (?,'fm9999999999999')|| ' SESSION_DATE_FROM='|| TO_char (? +10,'YYYY/MM/DD')," + 
                             "p_lang_code=>'US') as URL FROM DUAL" ;

        Connection connection = null;
        PreparedStatement statement = null;
        try {
           // connection = ADFUtil.getConnection();
           connection = getConnection();
            statement = connection.prepareStatement(sqlQueryforUrl);
            statement.setInt(1, applId);
            statement.setInt(2, respId);
            statement.setInt(3, assignmentId);
            statement.setDate(4, effectiveDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String url = (String)rs.getObject("URL");
                System.out.println("....url...."+url);
                if (url != null && url.trim().length() > 0) {

                    FacesContext facesContext =
                        FacesContext.getCurrentInstance();
                    ExtendedRenderKitService service =
                        Service.getRenderKitService(facesContext,
                                                    ExtendedRenderKitService.class);
                    service.addScript(facesContext,
                                      "window.open('" + url + "')");

                }
                break; //as we are expecting only one row
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
       
    }


    public void elementEntriesPayrollVersion(ActionEvent actionEvent) {
        String sqlQueryforUrl = "SELECT fnd_run_function.get_run_function_url  (p_function_id =>fnd_function.get_function_id('PAYWSMEE'),p_resp_appl_id=>?,p_resp_id=>?," +
                                "p_security_group_id=> fnd_global.security_group_id,p_parameters=>'NODE_ID='|| TO_CHAR (?,'fm9999999999999')|| ' SESSION_DATE_FROM='|| TO_char (? +10,'YYYY/MM/DD')," + 
                             "p_lang_code=>'US') as URL FROM DUAL" ;

        Connection connection = null;
        PreparedStatement statement = null;
        try {
           // connection = ADFUtil.getConnection();
           connection = getConnection();
            statement = connection.prepareStatement(sqlQueryforUrl);
            statement.setInt(1, applId);
            statement.setInt(2, respId);
            statement.setInt(3, assignmentId);
            statement.setDate(4, effectiveDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String url = (String)rs.getObject("URL");
                System.out.println("....url...."+url);
                if (url != null && url.trim().length() > 0) {

                    FacesContext facesContext =
                        FacesContext.getCurrentInstance();
                    ExtendedRenderKitService service =
                        Service.getRenderKitService(facesContext,
                                                    ExtendedRenderKitService.class);
                    service.addScript(facesContext,
                                      "window.open('" + url + "')");

                }
                break; //as we are expecting only one row
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void taxInformationPayrollVersion(ActionEvent actionEvent) {
        
        String sqlQueryforUrl = "SELECT fnd_run_function.get_run_function_url  (p_function_id =>fnd_function.get_function_id('PAYGBTAX'),p_resp_appl_id=>?,p_resp_id=>?," +
                                "p_security_group_id=> fnd_global.security_group_id,p_parameters=>'NODE_ID='|| TO_CHAR (?,'fm9999999999999')|| ' SESSION_DATE_FROM='|| TO_char (? +10,'YYYY/MM/DD')," + 
                             "p_lang_code=>'US') as URL FROM DUAL" ;

        Connection connection = null;
        PreparedStatement statement = null;
        try {
           // connection = ADFUtil.getConnection();
           connection = getConnection();
            statement = connection.prepareStatement(sqlQueryforUrl);
            statement.setInt(1, applId);
            statement.setInt(2, respId);
            statement.setInt(3, assignmentId);
            statement.setDate(4, effectiveDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String url = (String)rs.getObject("URL");
                System.out.println("....url...."+url);
                if (url != null && url.trim().length() > 0) {

                    FacesContext facesContext =
                        FacesContext.getCurrentInstance();
                    ExtendedRenderKitService service =
                        Service.getRenderKitService(facesContext,
                                                    ExtendedRenderKitService.class);
                    service.addScript(facesContext,
                                      "window.open('" + url + "')");

                }
                break; //as we are expecting only one row
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void payMethodPayrollVersion(ActionEvent actionEvent) {
        String sqlQueryforUrl = "SELECT fnd_run_function.get_run_function_url  (p_function_id =>fnd_function.get_function_id('PAYWSEMP'),p_resp_appl_id=>?,p_resp_id=>?," +
                                "p_security_group_id=> fnd_global.security_group_id,p_parameters=>'NODE_ID='|| TO_CHAR (?,'fm9999999999999')|| ' SESSION_DATE_FROM='|| TO_char (? +10,'YYYY/MM/DD')," + 
                             "p_lang_code=>'US') as URL FROM DUAL" ;

        Connection connection = null;
        PreparedStatement statement = null;
        try {
           // connection = ADFUtil.getConnection();
           connection = getConnection();
            statement = connection.prepareStatement(sqlQueryforUrl);
            statement.setInt(1, applId);
            statement.setInt(2, respId);
            statement.setInt(3, assignmentId);
            statement.setDate(4, effectiveDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String url = (String)rs.getObject("URL");
                System.out.println("....url...."+url);
                if (url != null && url.trim().length() > 0) {

                    FacesContext facesContext =
                        FacesContext.getCurrentInstance();
                    ExtendedRenderKitService service =
                        Service.getRenderKitService(facesContext,
                                                    ExtendedRenderKitService.class);
                    service.addScript(facesContext,
                                      "window.open('" + url + "')");

                }
                break; //as we are expecting only one row
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void quickPayPayrollVersion(ActionEvent actionEvent) {

        String sqlQueryforUrl = "SELECT fnd_run_function.get_run_function_url  (p_function_id =>fnd_function.get_function_id('PAYWSRQP'),p_resp_appl_id=>?,p_resp_id=>?," +
                                "p_security_group_id=> fnd_global.security_group_id,p_parameters=>'NODE_ID='|| TO_CHAR (?,'fm9999999999999')|| ' SESSION_DATE_FROM='|| TO_char (? +10,'YYYY/MM/DD')," + 
                             "p_lang_code=>'US') as URL FROM DUAL" ;

        Connection connection = null;
        PreparedStatement statement = null;
        try {
           // connection = ADFUtil.getConnection();
           connection = getConnection();
            statement = connection.prepareStatement(sqlQueryforUrl);
            statement.setInt(1, applId);
            statement.setInt(2, respId);
            statement.setInt(3, assignmentId);
            statement.setDate(4, effectiveDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String url = (String)rs.getObject("URL");
                System.out.println("....url...."+url);
                if (url != null && url.trim().length() > 0) {

                    FacesContext facesContext =
                        FacesContext.getCurrentInstance();
                    ExtendedRenderKitService service =
                        Service.getRenderKitService(facesContext,
                                                    ExtendedRenderKitService.class);
                    service.addScript(facesContext,
                                      "window.open('" + url + "')");

                }
                break; //as we are expecting only one row
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static Connection getConnection() {
        Context context;
        Connection con = null;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("jdbc/ADF_EBS");
            con = ds.getConnection();
        } catch (NamingException e) {
            System.out.println("+++++++ NamingException : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("+++++++ SQLException : " + e.getMessage());
        }
        return con;
    }
    

}