package nhs.esr.apiCall;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

import nhs.esr.util.ADFUtil;


public class UpdateAssignmentAPICall {
    public UpdateAssignmentAPICall() {
        super();
    }

    public static void main(String[] args) {
        Map updateAssignMap = new HashMap();
        updateAssignMap.put("p_effective_date","27-MAR-16"); //String
        updateAssignMap.put("p_datetrack_update_mode", "CORRECTION");
        updateAssignMap.put("p_assignment_id", 1034);
        updateAssignMap.put("p_grade_id", 2375);
        updateAssignMap.put("p_position_id", 83338);
        updateAssignMap.put("p_job_id", 353);
        updateAssignMap.put("p_payroll_id", 236);
        updateAssignMap.put("p_location_id", 150);
        updateAssignMap.put("p_organization_id", 106);
        updateAssignMap.put("p_pay_basis_id", 62);
        updateAssignMap.put("p_supervisor_id", 1032);
        updateAssignMap.put("p_assignment_number", "2000951");
        updateAssignMap.put("p_change_reason", "HC");
        updateAssignMap.put("p_assignment_status_type_id", 1);
        updateAssignMap.put("p_date_probation_end", "17-JUN-16");
        updateAssignMap.put("p_frequency", "W");
        updateAssignMap.put("p_manager_flag", "Y");
        updateAssignMap.put("p_normal_hours", 7);
        updateAssignMap.put("p_supervisor_assignment_id", 1033);
        try {
            Map updateAssignMapAssignReturnMap =
                (new UpdateAssignmentAPICall()).callUpdateAssignmentAPI(updateAssignMap);
            Set keys = updateAssignMapAssignReturnMap.keySet();
            Iterator itr = keys.iterator();
            String key = null;
            Object value = null;
            while (itr.hasNext()) {
                key = (String)itr.next();
                value = updateAssignMapAssignReturnMap.get(key);
                if ("p_error".equalsIgnoreCase(key)) {
                    System.out.println("+++++++Error: " +
                                       updateAssignMapAssignReturnMap.get("p_error"));
                } else {
                    System.out.println("+++Key++++" + key +
                                       "++++++++Value++++++++++" +
                                       updateAssignMapAssignReturnMap.get(key));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Update Assignment API call
     */
    public Map callUpdateAssignmentAPI(Map updateAssignmentMap) {
        Map updateAssignmentReturnMap = new HashMap();
        CallableStatement cs = null;
        Connection con = null;
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.85.2:1561:EDEV";
        String username = "apps";
        String password = "master5sno0ker";
        try {
           // con = DriverManager.getConnection(url, username, password);
           con = ADFUtil.getConnection();
            System.out.println(".+++callUpdateAssignmentAPI+++++ con ++++++"+con);
            String statement =
                "xx_hremployee_pkg.update_assignment_prc(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            cs = con.prepareCall("begin " + statement + " ;end;");
            Set keys = updateAssignmentMap.keySet();
            Iterator itr = keys.iterator();
            String key = null;
            Object value = null;
            resetUpdateAssignmentInParams(cs);
            registerUpdateAssignmentOutParams(cs);
            while (itr.hasNext()) {
                key = (String)itr.next();
                value = updateAssignmentMap.get(key);
//                System.out.println(".+++callUpdateAssignmentAPI+++++ key ++++++"+key);
//                System.out.println(".+++callUpdateAssignmentAPI+++++ value ++++++"+value);

                if ("p_effective_date".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_effective_date", value);
                }
                if ("p_datetrack_update_mode".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_datetrack_update_mode", value);
                }
                if ("p_assignment_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_assignment_id", value);
                }
                if ("p_grade_id".equalsIgnoreCase(key) && (value != null)) {
                    cs.setObject("p_grade_id", value);
                }
                if ("p_position_id".equalsIgnoreCase(key) && (value != null)) {
                    cs.setObject("p_position_id", value);
                }
                if ("p_job_id".equalsIgnoreCase(key) && (value != null)) {
                    cs.setObject("p_job_id", value);
                }
                if ("p_payroll_id".equalsIgnoreCase(key) && (value != null)) {
                    cs.setObject("p_payroll_id", value);
                }
                if ("p_location_id".equalsIgnoreCase(key) && (value != null)) {
                    cs.setObject("p_location_id", value);
                }
                if ("p_organization_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_organization_id", value);
                }
                if ("p_pay_basis_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_pay_basis_id", value);
                }                
                if ("p_segment1".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment1", value);
                }
                if ("p_segment2".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment2", value);
                }
                if ("p_segment3".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment3", value);
                }
                if ("p_segment4".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment4", value);
                }
                if ("p_segment5".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment5", value);
                }
                if ("p_segment6".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment6", value);
                }
                if ("p_segment7".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment7", value);
                }
                if ("p_segment8".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment8", value);
                }
                if ("p_segment9".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment9", value);
                }
                if ("p_segment10".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment10", value);
                }
                if ("p_segment11".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment11", value);
                }
                if ("p_segment12".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment12", value);
                }
                if ("p_segment13".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment13", value);
                }
                if ("p_segment14".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment14", value);
                }
                if ("p_segment15".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment15", value);
                }
                if ("p_segment16".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment16", value);
                }
                if ("p_segment17".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment17", value);
                }
                if ("p_segment18".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment18", value);
                }
                if ("p_segment19".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment19", value);
                }
                if ("p_segment20".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment20", value);
                }
                if ("p_segment21".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment21", value);
                }
                if ("p_segment22".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment22", value);
                }
                if ("p_segment23".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment23", value);
                }
                if ("p_segment24".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment24", value);
                }
                if ("p_segment25".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment25", value);
                }
                if ("p_segment26".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment26", value);
                }
                if ("p_segment27".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment27", value);
                }
                if ("p_segment28".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment28", value);
                }
                if ("p_segment29".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment29", value);
                }
                if ("p_segment30".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_segment30", value);
                }                              
                if ("p_supervisor_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_supervisor_id", value);
                }
                if ("p_assignment_number".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_assignment_number", value);
                }
                if ("p_change_reason".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_change_reason", value);
                }
                if ("p_assignment_status_type_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_assignment_status_type_id", value);
                }
                if ("p_comments".equalsIgnoreCase(key) && (value != null)) {
                    cs.setObject("p_comments", value);
                }
                if ("p_date_probation_end".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_date_probation_end", value);
                }
                if ("p_default_code_comb_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_default_code_comb_id", value);
                }
                if ("p_frequency".equalsIgnoreCase(key) && (value != null)) {
                    cs.setObject("p_frequency", value);
                }
                if ("p_internal_address_line".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_internal_address_line", value);
                }
                if ("p_manager_flag".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_manager_flag", value);
                }
                if ("p_normal_hours".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_normal_hours", value);
                }
                if ("p_probation_period".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_probation_period", value);
                }
                if ("p_probation_unit".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_probation_unit", value);
                }
                if ("p_projected_assignment_end".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_projected_assignment_end", value);
                }
                if ("p_time_normal_finish".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_time_normal_finish", value);
                }
                if ("p_time_normal_start".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_time_normal_start", value);
                }
                if ("p_bargaining_unit_code".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_bargaining_unit_code", value);
                }
                if ("p_labour_union_member_flag".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_labour_union_member_flag", value);
                }
                if ("p_hourly_salaried_code".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_hourly_salaried_code", value);
                }
                if ("p_ass_attribute_category".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute_category", value);
                }
                if ("p_ass_attribute1".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute1", value);
                }
                if ("p_ass_attribute2".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute2", value);
                }
                if ("p_ass_attribute3".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute3", value);
                }
                if ("p_ass_attribute4".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute4", value);
                }
                if ("p_ass_attribute5".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute5", value);
                }
                if ("p_ass_attribute6".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute6", value);
                }
                if ("p_ass_attribute7".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute7", value);
                }
                if ("p_ass_attribute8".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute8", value);
                }
                if ("p_ass_attribute9".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute9", value);
                }
                if ("p_ass_attribute10".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute10", value);
                }
                if ("p_ass_attribute11".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute11", value);
                }
                if ("p_ass_attribute12".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute12", value);
                }
                if ("p_ass_attribute13".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute13", value);
                }
                if ("p_ass_attribute14".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute14", value);
                }
                if ("p_ass_attribute15".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute15", value);
                }
                if ("p_ass_attribute16".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute16", value);
                }
                if ("p_ass_attribute17".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute17", value);
                }
                if ("p_ass_attribute18".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute18", value);
                }
                if ("p_ass_attribute19".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute19", value);
                }
                if ("p_ass_attribute20".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute20", value);
                }
                if ("p_ass_attribute21".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute21", value);
                }
                if ("p_ass_attribute22".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute22", value);
                }
                if ("p_ass_attribute23".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute23", value);
                }
                if ("p_ass_attribute24".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute24", value);
                }
                if ("p_ass_attribute25".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute25", value);
                }
                if ("p_ass_attribute26".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute26", value);
                }
                if ("p_ass_attribute27".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute27", value);
                }
                if ("p_ass_attribute28".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute28", value);
                }
                if ("p_ass_attribute29".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute29", value);
                }
                if ("p_ass_attribute30".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_ass_attribute30", value);
                }                        
                if ("p_title".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_title", value);
                }
                if ("p_notice_period".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_notice_period", value);
                }
                if ("p_notice_period_uom".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_notice_period_uom", value);
                }
                if ("p_employee_category".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_employee_category", value);
                }

                if ("p_employment_category".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_employment_category", value);
                }
                if ("p_work_at_home".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_work_at_home", value);
                }                
                if ("p_supervisor_assignment_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_supervisor_assignment_id", value);
                }
                if ("p_special_ceiling_step_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_special_ceiling_step_id", value);
                }
                if ("p_people_group_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_people_group_id", value);
                }
                if ("p_soft_coding_keyflex_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_soft_coding_keyflex_id", value);
                }
                if ("p_cagr_grade_def_id".equalsIgnoreCase(key) &&
                    (value != null)) {
                    cs.setObject("p_cagr_grade_def_id", value);
                }
            }
            cs.execute();
            if (cs.getObject("p_error") != null) {
                updateAssignmentReturnMap.put("p_error",
                                              cs.getObject("p_error"));
            } else {
                if (cs.getObject("p_special_ceiling_step_id") != null)
                    updateAssignmentReturnMap.put("p_special_ceiling_step_id",
                                                  cs.getObject("p_special_ceiling_step_id"));
                if (cs.getObject("p_people_group_id") != null)
                    updateAssignmentReturnMap.put("p_people_group_id",
                                                  cs.getObject("p_people_group_id"));
                if (cs.getObject("p_soft_coding_keyflex_id") != null)
                    updateAssignmentReturnMap.put("p_soft_coding_keyflex_id",
                                                  cs.getObject("p_soft_coding_keyflex_id"));
                if (cs.getObject("p_group_name") != null)
                    updateAssignmentReturnMap.put("p_group_name",
                                                  cs.getObject("p_group_name"));
                if (cs.getObject("p_effective_start_date") != null)
                    updateAssignmentReturnMap.put("p_effective_start_date",
                                                  cs.getObject("p_effective_start_date"));
                if (cs.getObject("p_effective_end_date") != null)
                    updateAssignmentReturnMap.put("p_effective_end_date",
                                                  cs.getObject("p_effective_end_date"));
                if (cs.getObject("p_org_now_no_manager_warning") != null)
                    updateAssignmentReturnMap.put("p_org_now_no_manager_warning",
                                                  cs.getObject("p_org_now_no_manager_warning"));
                if (cs.getObject("p_other_manager_warning") != null)
                    updateAssignmentReturnMap.put("p_other_manager_warning",
                                                  cs.getObject("p_other_manager_warning"));
                if (cs.getObject("p_spp_delete_warning") != null)
                    updateAssignmentReturnMap.put("p_spp_delete_warning",
                                                  cs.getObject("p_spp_delete_warning"));
                if (cs.getObject("p_entries_changed_warning") != null)
                    updateAssignmentReturnMap.put("p_entries_changed_warning",
                                                  cs.getObject("p_entries_changed_warning"));
                if (cs.getObject("p_tax_district_changed_warning") != null)
                    updateAssignmentReturnMap.put("p_tax_district_changed_warning",
                                                  cs.getObject("p_tax_district_changed_warning"));
                if (cs.getObject("p_concatenated_segments") != null)
                    updateAssignmentReturnMap.put("p_concatenated_segments",
                                                  cs.getObject("p_concatenated_segments"));
                if (cs.getObject("p_cagr_grade_def_id") != null)
                    updateAssignmentReturnMap.put("p_cagr_grade_def_id",
                                                  cs.getObject("p_cagr_grade_def_id"));
                if (cs.getObject("p_cagr_concatenated_segments") != null)
                    updateAssignmentReturnMap.put("p_cagr_concatenated_segments",
                                                  cs.getObject("p_cagr_concatenated_segments"));
                if (cs.getObject("p_comment_id") != null)
                    updateAssignmentReturnMap.put("p_comment_id",
                                                  cs.getObject("p_comment_id"));
                if (cs.getObject("p_no_managers_warning") != null)
                    updateAssignmentReturnMap.put("p_no_managers_warning",
                                                  cs.getObject("p_no_managers_warning"));
            }

        } catch (SQLException e) {
            updateAssignmentReturnMap.put("p_error", e.getMessage());
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
        return updateAssignmentReturnMap;
    }

    /**
     *Reset updateAssignMap Assignment API Params
     */
    public void resetUpdateAssignmentInParams(CallableStatement cs) {

        try {
//            cs.setObject("p_user_id", 13590);
//            cs.setObject("p_resp_id", 50552);
//            cs.setObject("p_application_id", 800);
//            cs.setObject("p_org_id", 62);
//            
//            System.out.println(".....currentUserId....."+Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId"))));
//            System.out.println(".....responsibilityId....."+Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("responsibilityId"))));
//            System.out.println("......applicationId....."+Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("applicationId"))));
//            System.out.println(".......orgId......."+Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId"))));

            cs.setObject("p_user_id", Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId"))));
            cs.setObject("p_resp_id", Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("responsibilityId"))));
            cs.setObject("p_application_id", Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("applicationId"))));
            cs.setObject("p_org_id",  Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId"))));
            
            cs.setObject("p_datetrack_update_mode", null);
            // cs.setObject("p_called_from_mass_update", false);
            cs.setObject("p_grade_id", null);
            cs.setObject("p_position_id", null);
            cs.setObject("p_job_id", null);
            cs.setObject("p_payroll_id", null);
            cs.setObject("p_location_id", null);
            cs.setObject("p_organization_id", null);
            cs.setObject("p_pay_basis_id", null);
            cs.setObject("p_segment1", 121);
            cs.setObject("p_segment2", null);
            cs.setObject("p_segment3", null);
            cs.setObject("p_segment4", null);
            cs.setObject("p_segment5", null);
            cs.setObject("p_segment6", null);
            cs.setObject("p_segment7", null);
            cs.setObject("p_segment8", null);
            cs.setObject("p_segment9", null);
            cs.setObject("p_segment10", null);
            cs.setObject("p_segment11", null);
            cs.setObject("p_segment12", null);
            cs.setObject("p_segment13", null);
            cs.setObject("p_segment14", null);
            cs.setObject("p_segment15", null);
            cs.setObject("p_segment16", null);
            cs.setObject("p_segment17", null);
            cs.setObject("p_segment18", null);
            cs.setObject("p_segment19", null);
            cs.setObject("p_segment20", null);
            cs.setObject("p_segment21", null);
            cs.setObject("p_segment22", null);
            cs.setObject("p_segment23", null);
            cs.setObject("p_segment24", null);
            cs.setObject("p_segment25", null);
            cs.setObject("p_segment26", null);
            cs.setObject("p_segment27", null);
            cs.setObject("p_segment28", null);
            cs.setObject("p_segment29", null);
            cs.setObject("p_segment30", null);
            cs.setObject("p_supervisor_id", null);
            cs.setObject("p_assignment_number", null);
            cs.setObject("p_change_reason", null);
            cs.setObject("p_assignment_status_type_id", null);
            cs.setObject("p_comments", null);
            cs.setObject("p_date_probation_end", null);
            cs.setObject("p_default_code_comb_id", null);
            cs.setObject("p_frequency", null);
            cs.setObject("p_internal_address_line", null);
            cs.setObject("p_manager_flag", "N");
            cs.setObject("p_normal_hours", null);
            cs.setObject("p_probation_period", null);
            cs.setObject("p_probation_unit", null);
            cs.setObject("p_projected_assignment_end", null);
            cs.setObject("p_time_normal_finish", null);
            cs.setObject("p_time_normal_start", null);
            cs.setObject("p_bargaining_unit_code", null);
            cs.setObject("p_labour_union_member_flag", "N");
            cs.setObject("p_hourly_salaried_code", null);
            cs.setObject("p_ass_attribute_category", "E");
            cs.setObject("p_ass_attribute1", null);
            cs.setObject("p_ass_attribute2", null);
            cs.setObject("p_ass_attribute3", "1");
            cs.setObject("p_ass_attribute4", null);
            cs.setObject("p_ass_attribute5", null);
            cs.setObject("p_ass_attribute6", "N");
            cs.setObject("p_ass_attribute7", "N");
            cs.setObject("p_ass_attribute8", "N");
            cs.setObject("p_ass_attribute9", null);
            cs.setObject("p_ass_attribute10", null);
            cs.setObject("p_ass_attribute11", null);
            cs.setObject("p_ass_attribute12", null);
            cs.setObject("p_ass_attribute13", null);
            cs.setObject("p_ass_attribute14", null);
            cs.setObject("p_ass_attribute15", null);
            cs.setObject("p_ass_attribute16", null);
            cs.setObject("p_ass_attribute17", null);
            cs.setObject("p_ass_attribute18", null);
            cs.setObject("p_ass_attribute19", "N");
            cs.setObject("p_ass_attribute20", null);
            cs.setObject("p_ass_attribute21", null);
            cs.setObject("p_ass_attribute22", "None");
            cs.setObject("p_ass_attribute23", null);
            cs.setObject("p_ass_attribute24", null);
            cs.setObject("p_ass_attribute25", "N");
            cs.setObject("p_ass_attribute26", null);
            cs.setObject("p_ass_attribute27", null);
            cs.setObject("p_ass_attribute28", null);
            cs.setObject("p_ass_attribute29", null);
            cs.setObject("p_ass_attribute30", null);
            cs.setObject("p_title", null);
            cs.setObject("p_notice_period", null);
            cs.setObject("p_notice_period_uom", null);
            cs.setObject("p_employee_category", null);
            cs.setObject("p_employment_category", null);
            cs.setObject("p_work_at_home", null);            
            cs.setObject("p_supervisor_assignment_id", null);            
            //cs.setObject("p_special_ceiling_step_id", null);
            //cs.setObject("p_people_group_id", null);
            //cs.setObject("p_soft_coding_keyflex_id", null);   
            //cs.setObject("p_cagr_grade_def_id", null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Register out params for updateAssignMap
     */
    public void registerUpdateAssignmentOutParams(CallableStatement cs) {
        try {
            cs.registerOutParameter("p_special_ceiling_step_id", Types.NUMERIC);
            cs.registerOutParameter("p_people_group_id", Types.NUMERIC);
            cs.registerOutParameter("p_soft_coding_keyflex_id", Types.NUMERIC);
            cs.registerOutParameter("p_group_name", Types.VARCHAR);
            cs.registerOutParameter("p_effective_start_date", Types.VARCHAR);
            cs.registerOutParameter("p_effective_end_date", Types.VARCHAR);
            cs.registerOutParameter("p_org_now_no_manager_warning", Types.VARCHAR);
            cs.registerOutParameter("p_other_manager_warning", Types.VARCHAR);
            cs.registerOutParameter("p_spp_delete_warning", Types.VARCHAR);
            cs.registerOutParameter("p_entries_changed_warning", Types.VARCHAR);
            cs.registerOutParameter("p_tax_district_changed_warning", Types.VARCHAR);
            cs.registerOutParameter("p_concatenated_segments", Types.VARCHAR);
            cs.registerOutParameter("p_cagr_grade_def_id", Types.NUMERIC);
            cs.registerOutParameter("p_cagr_concatenated_segments", Types.VARCHAR);
            cs.registerOutParameter("p_comment_id", Types.NUMERIC);
            cs.registerOutParameter("p_no_managers_warning", Types.VARCHAR);
            cs.registerOutParameter("p_error", Types.VARCHAR);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
