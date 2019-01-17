package nhs.esr.apiCall;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import nhs.esr.util.ADFUtil;

public class SetPrimaryAssignmentAPICall {
    public SetPrimaryAssignmentAPICall() {
        super();
    }
    public static void main(String[] args) {
        Map setPrimaryAssignmentMap = new HashMap();
        setPrimaryAssignmentMap.put("p_effective_date",new java.sql.Date(new Date().getTime())); 
        setPrimaryAssignmentMap.put("p_person_id", 15312);
        setPrimaryAssignmentMap.put("p_assignment_id", 15315);
        setPrimaryAssignmentMap.put("p_object_version_number", 10);
        try {
            Map setPrimaryAssignmentMapAssignReturnMap =
                (new SetPrimaryAssignmentAPICall()).callMakePrimaryAssignmentAPI(setPrimaryAssignmentMap);
            Set keys = setPrimaryAssignmentMapAssignReturnMap.keySet();
            Iterator itr = keys.iterator();
            String key = null;
            Object value = null;
            while (itr.hasNext()) {
                key = (String)itr.next();
                value = setPrimaryAssignmentMapAssignReturnMap.get(key);
                if ("p_error".equalsIgnoreCase(key)) {
                    System.out.println("+++++++Error: " +
                                       setPrimaryAssignmentMapAssignReturnMap.get("p_error"));
                } else {
                    System.out.println("+++Key++++" + key +
                                       "++++++++Value++++++++++" +
                                       setPrimaryAssignmentMapAssignReturnMap.get(key));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * Create SetPrimaryAssignment API call
     */
        
    public Map callMakePrimaryAssignmentAPI(Map nonPrimaryAssignmentDetails) {

        Map primaryAssignmentReturnMap = new HashMap();
        CallableStatement cs = null;
        Connection conn = null;
        try {
            conn = ADFUtil.getConnection();
          String statement = "HR_ASSIGNMENT_API.set_new_primary_asg(?,?,?,?,?,?)";
            cs = conn.prepareCall("begin " + statement + " ;end;");
            Set keys = nonPrimaryAssignmentDetails.keySet();
            Iterator itr = keys.iterator();
            String key = null;
            Object value = null;
            registerPrimaryAssignmentOutParams(cs);
           
            while (itr.hasNext()) {
                key = (String)itr.next();
                value = nonPrimaryAssignmentDetails.get(key);
                System.out.println("++++ Make Primary Assignment input : Key : " +
                                   key + ", Value : " + value);
                if ("p_effective_date".equals(key) && value != null) {
                    cs.setObject("p_effective_date", value);
                }
                if ("p_person_id".equals(key) && value != null) {
                    cs.setObject("p_person_id", value);
                }
                if ("p_assignment_id".equals(key) && value != null) {
                    cs.setObject("p_assignment_id", value);
                }
                if ("p_object_version_number".equals(key) && value != null) {
                    cs.setObject("p_object_version_number", value);
                }
                            
            }

            //Apps Initialize p_validate
            /*cs.setObject("p_user_id",
                         Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId"))));
            cs.setObject("p_resp_id",
                         Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("responsibilityId"))));
            cs.setObject("p_application_id",
                         Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("applicationId"))));
            cs.setObject("p_org_id",
                         Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId"))));
            */
          appsInitialization(13558 , 50552, 800, 62);
            cs.execute();
            if (cs.getObject("p_object_version_number") != null) {
                primaryAssignmentReturnMap.put("p_object_version_number",
                                               cs.getObject("p_object_version_number"));
            }
            if (cs.getObject("p_effective_start_date") != null) {
                primaryAssignmentReturnMap.put("p_effective_start_date",
                                               cs.getObject("p_effective_start_date"));
            }
            if (cs.getObject("p_effective_end_date") != null) {
                primaryAssignmentReturnMap.put("p_effective_end_date",
                                               cs.getObject("p_effective_end_date"));
            }

            for (Object returnKey : primaryAssignmentReturnMap.keySet()) {
                System.out.println("++++++ Return map key : " +
                                   returnKey.toString() + ", Value : " +
                                   primaryAssignmentReturnMap.get(returnKey));
            }


        } catch (SQLException e) {
            primaryAssignmentReturnMap.put("p_error", e.getMessage());
            e.printStackTrace();
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return primaryAssignmentReturnMap;
    }


    private void registerPrimaryAssignmentOutParams(CallableStatement cs) {
        try {
            cs.registerOutParameter("p_object_version_number", Types.NUMERIC);
            cs.registerOutParameter("p_effective_start_date", Types.DATE);
            cs.registerOutParameter("p_effective_end_date", Types.DATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Apps Initialization
     */
    public String appsInitialization(int p_user_id, int p_resp_id,
                                     int p_application_id, int p_org_id) {
        CallableStatement cs = null;
        Connection con = null;
        String returnAppsInitialization = null;
        try {
            //con = ADFUtil.getStaticConnection();
            con = ADFUtil.getConnection();
            String statement =
                "begin ? := xx_nhs_person_dtls_pkg.create_context_fnc(?,?,?,?); end;";
            //String statement = "{ ? = call  xx_nhs_person_dtls_pkg.create_context_fnc( ?, ?, ?, ?) }";
            cs = con.prepareCall(statement);
            cs.registerOutParameter(1, Types.VARCHAR);
            /*cs.setObject("p_user_id",
                             Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId"))));
                cs.setObject("p_resp_id",
                             Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("responsibilityId"))));
                cs.setObject("p_application_id",
                             Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("applicationId"))));
                cs.setObject("p_org_id",
                             Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId"))));*/
            cs.setObject(2, p_user_id);
            cs.setObject(3, p_resp_id);
            cs.setObject(4, p_application_id);
            cs.setObject(5, p_org_id);
            cs.executeUpdate();
            returnAppsInitialization = (String)cs.getObject(1);

            //System.out.println("++++++++ Return from apps initialization : " +
            //                   returnAppsInitialization);
        } catch (Exception e) {
            System.out.println("Exception while apps initialization : " +
                               e.getMessage());
            e.printStackTrace();
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnAppsInitialization;
    }

}
