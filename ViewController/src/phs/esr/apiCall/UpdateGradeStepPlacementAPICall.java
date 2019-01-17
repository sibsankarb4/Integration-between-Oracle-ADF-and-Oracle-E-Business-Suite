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

public class UpdateGradeStepPlacementAPICall {
    public UpdateGradeStepPlacementAPICall() {
        super();
    }
    public static void main(String[] args) {
        Map updateGradeStepMap = new HashMap();
        updateGradeStepMap.put("p_effective_date",new java.sql.Date(new Date().getTime())); 
        updateGradeStepMap.put("p_datetrack_mode", "CORRECTION");
        updateGradeStepMap.put("p_placement_id", 462);
        updateGradeStepMap.put("p_object_version_number", 2);
        updateGradeStepMap.put("p_step_id", 3113);        
        try {
            Map updateGradeStepMapAssignReturnMap =
                (new UpdateGradeStepPlacementAPICall()).callUpdateGradeStepPlacementAPI(updateGradeStepMap);
            Set keys = updateGradeStepMapAssignReturnMap.keySet();
            Iterator itr = keys.iterator();
            String key = null;
            Object value = null;
            while (itr.hasNext()) {
                key = (String)itr.next();
                value = updateGradeStepMapAssignReturnMap.get(key);
                if ("p_error".equalsIgnoreCase(key)) {
                    System.out.println("+++++++Error: " +
                                       updateGradeStepMapAssignReturnMap.get("p_error"));
                } else {
                    System.out.println("+++Key++++" + key +
                                       "++++++++Value++++++++++" +
                                       updateGradeStepMapAssignReturnMap.get(key));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * Update GradeStepPlacement API call
     */
    public Map callUpdateGradeStepPlacementAPI(Map updateGradeStepPlacementMap) {
        Map updateGradeStepPlacementReturnMap = new HashMap();
        CallableStatement cs = null;
        Connection con = null;
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.85.2:1561:EDEV";
        String username = "apps";
        String password = "master5sno0ker";
        try {
           // con = DriverManager.getConnection(url, username, password);
           con = ADFUtil.getConnection();
            String statement =
                "hr_sp_placement_api.update_spp(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            cs = con.prepareCall("begin " + statement + " ;end;");
            Set keys = updateGradeStepPlacementMap.keySet();
            Iterator itr = keys.iterator();
            String key = null;
            Object value = null;
            resetUpdateGradeStepPlacementInParams(cs);
            registerUpdateGradeStepPlacementOutParams(cs);
            while (itr.hasNext()) {
                key = (String)itr.next();
                value = updateGradeStepPlacementMap.get(key);
                if ("p_effective_date".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_effective_date", value);
                }
                if ("p_datetrack_mode".equalsIgnoreCase(key) && (value != null)) {
                    cs.setObject("p_datetrack_mode", value);
                }
                if ("p_placement_id".equalsIgnoreCase(key) && (value != null)) {
                    cs.setObject("p_placement_id", value);
                }
                if ("p_object_version_number".equalsIgnoreCase(key) && (value != null)) {
                    cs.setObject("p_object_version_number", value);
                }                
                if ("p_step_id".equalsIgnoreCase(key) && (value != null)) {
                    cs.setObject("p_step_id", value);
                }
                if ("p_auto_increment_flag".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_auto_increment_flag", value);
                }
                if ("p_reason".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_reason", value);
                }
                if ("p_request_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_request_id", value);
                }                
                if ("p_program_application_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_program_application_id", value);
                }
                if ("p_program_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_program_id", value);
                }
                if ("p_program_update_date".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_program_update_date", value);
                }
                if ("p_increment_number".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_increment_number", value);
                }
                if ("p_information1".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information1", value);
                }
                if ("p_information2".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information2", value);
                }
                if ("p_information3".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information3", value);
                }
                if ("p_information4".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information4", value);
                }
                if ("p_information5".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information5", value);
                }
                if ("p_information6".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information6", value);
                }
                if ("p_information7".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information7", value);
                }
                if ("p_information8".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information8", value);
                }
                if ("p_information9".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information9", value);
                }
                if ("p_information10".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information10", value);
                }
                if ("p_information11".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information11", value);
                }
                if ("p_information12".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information12", value);
                }
                if ("p_information13".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information13", value);
                }
                if ("p_information14".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information14", value);
                }
                if ("p_information15".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information15", value);
                }
                if ("p_information16".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information16", value);
                }
                if ("p_information17".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information17", value);
                }
                if ("p_information18".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information18", value);
                }
                if ("p_information19".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information19", value);
                }
                if ("p_information20".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information20", value);
                }
                if ("p_information21".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information21", value);
                }
                if ("p_information22".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information22", value);
                }
                if ("p_information23".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information23", value);
                }
                if ("p_information24".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information24", value);
                }
                if ("p_information25".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information25", value);
                }
                if ("p_information26".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information26", value);
                }
                if ("p_information27".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information27", value);
                }
                if ("p_information28".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information28", value);
                }
                if ("p_information29".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information29", value);
                }
                if ("p_information30".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information30", value);
                }
                if ("p_information_category".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_information_category", value);
                }
            }
            cs.execute();
                if (cs.getObject("p_effective_start_date") != null)
                    updateGradeStepPlacementReturnMap.put("p_effective_start_date",
                                                  cs.getObject("p_effective_start_date"));
                if (cs.getObject("p_effective_end_date") != null)
                    updateGradeStepPlacementReturnMap.put("p_effective_end_date",
                                                  cs.getObject("p_effective_end_date"));  

                if (cs.getObject("p_object_version_number") != null)
                    updateGradeStepPlacementReturnMap.put("p_object_version_number",
                                                cs.getObject("p_object_version_number"));

        } catch (SQLException e) {
            updateGradeStepPlacementReturnMap.put("p_error", e.getMessage());
            e.printStackTrace();
        } finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return updateGradeStepPlacementReturnMap;
    }

    /**
     *Reset GradeStepPlacement In Params
     */
    public void resetUpdateGradeStepPlacementInParams(CallableStatement cs) {

        try {
            cs.setObject("p_datetrack_mode", "CORRECTION");
            cs.setObject("p_auto_increment_flag", "N");
            cs.setObject("p_reason", null);
            cs.setObject("p_request_id", null);
            cs.setObject("p_program_application_id", null);
            cs.setObject("p_program_id", null);
            cs.setObject("p_program_update_date", null);  
            cs.setObject("p_increment_number", null);
            cs.setObject("p_information_category", "GB");
            cs.setObject("p_information1", null);
            cs.setObject("p_information2", null);
            cs.setObject("p_information3", null);
            cs.setObject("p_information4", null);
            cs.setObject("p_information5", null);
            cs.setObject("p_information6", null);
            cs.setObject("p_information7", null);
            cs.setObject("p_information8", null);
            cs.setObject("p_information9", null);
            cs.setObject("p_information10", null);
            cs.setObject("p_information11", null);
            cs.setObject("p_information12", null);
            cs.setObject("p_information13", null);
            cs.setObject("p_information14", null);
            cs.setObject("p_information15", null);
            cs.setObject("p_information16", null);
            cs.setObject("p_information17", null);
            cs.setObject("p_information18", null);
            cs.setObject("p_information19", null);
            cs.setObject("p_information20", null);
            cs.setObject("p_information21", null);
            cs.setObject("p_information22", null);
            cs.setObject("p_information23", null);
            cs.setObject("p_information24", null);
            cs.setObject("p_information25", null);
            cs.setObject("p_information26", null);
            cs.setObject("p_information27", null);
            cs.setObject("p_information28", null);
            cs.setObject("p_information29", null);
            cs.setObject("p_information30", null);            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Register GradeStepPlacement out params
     */
    public void registerUpdateGradeStepPlacementOutParams(CallableStatement cs) {
        try {
            cs.registerOutParameter("p_effective_start_date", Types.DATE);
            cs.registerOutParameter("p_effective_end_date", Types.DATE);
            cs.registerOutParameter("p_object_version_number", Types.NUMERIC);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
