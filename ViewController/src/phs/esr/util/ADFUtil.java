package nhs.esr.util;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import javax.naming.Context;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.sql.DataSource;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCDataControl;

import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;

public class ADFUtil {
    public ADFUtil() {
        super();
    }

    /**
     * This utility method use to DataControl object
     * @param dataControl
     * @return Object
     */
    public static Object getDataControl(String dataControl) {
        Object obj = null;
        BindingContext bindingContext = BindingContext.getCurrent();
        if (bindingContext != null) {
            DCDataControl dc = bindingContext.findDataControl(dataControl);
            obj = dc.getDataProvider();
        }
        return obj;
    }


    public static void createRow(ViewObjectImpl viewObjectImpl) {
        Row newRow = viewObjectImpl.createRow();
        newRow.setNewRowState(Row.STATUS_INITIALIZED);
        //vo.insertRowAtRangeIndex(vo.getRangeSize() - 1, newRow);
        viewObjectImpl.insertRow(newRow);
        System.out.println(viewObjectImpl.getCurrentRowIndex());
    }

    public static void setCriteriaCondition(ViewObject viewObject,
                                            String whereClauseVar,
                                            String value) {
        ViewCriteria vc = viewObject.createViewCriteria();
        vc.resetCriteria();
        ViewCriteriaRow vcr1 = vc.createViewCriteriaRow();
        vcr1.setAttribute(whereClauseVar, "=" + value);
        vc.add(vcr1);
        viewObject.applyViewCriteria(vc);
    }
    
    public static void setCriteriaConditionGeneric(ViewObject viewObject,
                                            String whereClauseVar,
                                            Object value) {
        ViewCriteria vc = viewObject.createViewCriteria();
        vc.resetCriteria();
        ViewCriteriaRow vcr1 = vc.createViewCriteriaRow();
        vcr1.setAttribute(whereClauseVar, value);
        vc.add(vcr1);
        viewObject.applyViewCriteria(vc);
    }


    //Get DB Connection from Datasource
    public static Connection getConnection() {
        Context context;
        Connection con = null;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("jdbc/ADF_EBS");
            con = ds.getConnection();
        } catch (NamingException e) {
            System.out.println("+++++++ NamingException : "+e.getMessage());
        } catch (SQLException e) {
            System.out.println("+++++++ SQLException : "+e.getMessage());
        }
        return con;
    }
    
    //Get DB Connection from Datasource
    public static Connection getStaticConnection() throws SQLException {
        Connection con = null;
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.85.2:1561:EDEV";
        String username = "apps";
        String password = "master5sno0ker";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("++++++++++++ Exception  : "+e.getMessage());
        }
        con = DriverManager.getConnection(url, username, password);        
        return con;
    }
    public static String showMessage(String message,javax.faces.application.FacesMessage.Severity severity) {
        FacesMessage facesMessage = new FacesMessage(message);
        facesMessage.setSeverity(severity);
        FacesContext.getCurrentInstance().addMessage(null,facesMessage);
        return null;
    }
}
