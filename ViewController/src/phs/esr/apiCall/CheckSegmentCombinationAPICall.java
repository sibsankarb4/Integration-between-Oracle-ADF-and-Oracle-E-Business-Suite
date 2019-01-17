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

public class CheckSegmentCombinationAPICall {
    public CheckSegmentCombinationAPICall() {
        super();
    }
    
    public Map callCheckSegmenCombinationtAPI(Map checkSegmentCombinationMap){
        Map checkSegmentCombinationReturnMap = new HashMap();
        CallableStatement cs = null;        
        Connection conn = null;
        try {
            //conn = ADFUtil.getStaticConnection();
            conn = ADFUtil.getConnection();
            String statement = "xx_hremployee_pkg.check_segment_combination(?,?,?,?,?,?,?)";
            cs = conn.prepareCall("begin " + statement + " ;end;");
            Set keys = checkSegmentCombinationMap.keySet();
            Iterator itr = keys.iterator();
            String key = null;
            Object value = null;
            registerCheckSegmentCombinationOutParams(cs);
                while (itr.hasNext()) {
                    key = (String)itr.next();
                    value = checkSegmentCombinationMap.get(key);
                    System.out.println("++++ Check Segment Combination : Key : " +
                                       key + ", Value : " + value);
                    if ("p_paypoint_location".equals(key) && value != null) {
                        cs.setObject("p_paypoint_location", value);
                    }
                    if ("p_expense_user_type".equals(key) && value != null) {
                        cs.setObject("p_expense_user_type", value);
                    }
                    if ("p_time_and_attendance".equals(key) && value != null) {
                        cs.setObject("p_time_and_attendance", value);
                    }
                    if ("p_data_entry_group".equals(key) && value != null) {
                        cs.setObject("p_data_entry_group", value);
                    }                            
                }    
                cs.execute();
                if (cs.getObject("p_ccid")!= null){
                    checkSegmentCombinationReturnMap.put("p_ccid", cs.getObject("p_ccid"));                
                    }          
                if (cs.getObject("p_concat_segments_out")!= null){
                    checkSegmentCombinationReturnMap.put("p_concat_segments_out", cs.getObject("p_concat_segments_out"));                
                    }          
                if (cs.getObject("p_error")!= null){
                    checkSegmentCombinationReturnMap.put("p_error", cs.getObject("p_error"));                
                    }
                
            } catch (SQLException e) {
                checkSegmentCombinationReturnMap.put("p_error", e.getMessage());
                e.printStackTrace();
        }
        return checkSegmentCombinationReturnMap;
    }
    public void registerCheckSegmentCombinationOutParams(CallableStatement cs){
        try {
            cs.registerOutParameter("p_ccid", Types.NUMERIC);
            cs.registerOutParameter("p_concat_segments_out", Types.VARCHAR);                            
            cs.registerOutParameter("p_error", Types.VARCHAR);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
    }
}
