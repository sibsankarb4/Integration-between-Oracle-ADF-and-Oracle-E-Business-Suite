package nhs.esr.apiCall;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import nhs.esr.util.ADFUtil;

public class ValidateContractTypeAPICall {
    public ValidateContractTypeAPICall() {
        super();
    }
    
    /**
     * Validate Contract API call
     */
         
     public Map validateContractType(Map validateContractTypeMap) {
         Map validateContractTypeReturnMap = new HashMap();
         CallableStatement cs = null;
         Connection conn = null;
         try {
            // conn = ADFUtil.getStaticConnection();
            conn = ADFUtil.getConnection();
             String statement = "xx_asg_contract_type_pkg.asg_contract_type_hook_ap(?,?,?,?,?,?,?,?,?)";
             cs = conn.prepareCall("begin " + statement + " ;end;");
             Set keys = validateContractTypeMap.keySet();
             Iterator itr = keys.iterator();
             String key = null;
             Object value = null;
             resetValidateContractTypeOutParams(cs);
             registerValidateContractTypeOutParams(cs);   
             cs.setObject("p_user_id", 13558);
             cs.setObject("p_resp_id", 50552);
             cs.setObject("p_resp_appl_id", 800);
             while (itr.hasNext()) {
                 key = (String)itr.next();
                 value = validateContractTypeMap.get(key);
                 System.out.println("++++ Validate Contract input : Key : " +
                                    key + ", Value : " + value);
                 if ("p_effective_date".equals(key) && value != null) {
                     cs.setObject("p_effective_date", value);
                 }
                 if ("p_payroll_id".equals(key) && value != null) {
                     cs.setObject("p_payroll_id", value);
                 }
                 if ("p_assignment_id".equals(key) && value != null) {
                     cs.setObject("p_assignment_id", value);
                 }
                 if ("p_grade_id".equals(key) && value != null) {
                     cs.setObject("p_grade_id", value);
                 }
                 if ("p_position_id".equals(key) && value != null) {
                     cs.setObject("p_position_id", value);
                 }                            
             }

             cs.execute();
             if (cs.getObject("p_output")!= null){
                 validateContractTypeReturnMap.put("p_error", cs.getObject("p_output"));                
                 }          

         } catch (SQLException e) {
             validateContractTypeReturnMap.put("p_error", e.getMessage());
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
         return validateContractTypeReturnMap;
     }


    public void resetValidateContractTypeOutParams(CallableStatement cs){
            try {
                cs.setObject("p_grade_id", null);
                cs.setObject("p_payroll_id", null);
                cs.setObject("p_position_id", null);
            } catch (SQLException e) {
                e.printStackTrace();                
        }
    }
    public void registerValidateContractTypeOutParams(CallableStatement cs) {
        try {
            cs.registerOutParameter("p_output", Types.VARCHAR);        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    

}
