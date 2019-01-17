package nhs.esr.apiCall;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import java.sql.Types;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.Set;

import javax.faces.context.FacesContext;

import nhs.esr.util.ADFUtil;

public class CreateAssignmentAPICall {
    public CreateAssignmentAPICall() {
        super();
    }
    /**
     * Create Assignment API call
     */
    public Map callCreateAssignmentAPI(Map createAssignmentMap) {
            Map createAssignmentReturnMap = new HashMap();
            CallableStatement cs = null;
            Connection con = null;
            try {
               // con = ADFUtil.getStaticConnection();
               con = ADFUtil.getConnection();
                String statement =
                    "xx_hremployee_pkg.create_secondary_emp_asg(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                cs = con.prepareCall("begin " + statement + " ;end;");
                Set keys = createAssignmentMap.keySet();
                Iterator itr = keys.iterator();
                String key = null;
                Object value = null;
                resetCreateAssignmentInParams(cs);
                registerCreateAssignmentOutParams(cs);
//                cs.setObject("p_user_id", 13627);
//                cs.setObject("p_resp_id", 50552);
//                cs.setObject("p_application_id", 800);
//                cs.setObject("p_org_id", 62);
               // cs.setObject("p_effective_date",new Date(new java.util.Date().getTime()));
               cs.setObject("p_user_id", Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId"))));
               cs.setObject("p_resp_id", Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("responsibilityId"))));
               cs.setObject("p_application_id", Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("applicationId"))));
               cs.setObject("p_org_id",  Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId"))));
                
                while (itr.hasNext()) {
                    key = (String)itr.next();
                    value = createAssignmentMap.get(key);
                    if ("p_business_group_id".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_business_group_id", value);
                    }
                    if ("p_effective_date".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_effective_date", value);
                    }
                    if ("p_person_id".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_person_id", value);
                    }
                   if ("p_organization_id".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_organization_id", value);
                    }
                    if ("p_grade_id".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_grade_id", value);
                    }
                    if ("p_position_id".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_position_id", value);
                    }
                    if ("p_job_id".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_job_id", value);
                    }
                    if ("p_assignment_status_type_id".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_assignment_status_type_id", value);
                    }
                    if ("p_people_group_id".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_people_group_id", value);
                    }
                    if ("p_payroll_id".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_payroll_id", value);
                    }
                    if ("p_location_id".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_location_id", value);
                    }
                    if ("p_supervisor_id".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_supervisor_id", value);
                    }
                    if ("p_special_ceiling_step_id".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_special_ceiling_step_id", value);
                    }
                    if ("p_pay_basis_id".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_pay_basis_id", value);
                    }
                    if ("p_assignment_number".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_assignment_number", value);
                    }
                    if ("p_change_reason".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_change_reason", value);
                    }
                    if ("p_comments".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_comments", value);
                    }
                    if ("p_date_probation_end".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_date_probation_end", value);
                    }
                    if ("p_default_code_comb_id".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_default_code_comb_id", value);
                    }
                    if ("p_employment_category".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_employment_category", value);
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
                    if ("p_normal_hours".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_normal_hours", value);
                    }
                    if ("p_perf_review_period".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_perf_review_period", value);
                    }
                    if ("p_perf_review_period_frequency".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_perf_review_period_frequency", value);
                    }
                    if ("p_probation_period".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_probation_period", value);
                    }
                    if ("p_probation_unit".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_probation_unit", value);
                    }
                    if ("p_sal_review_period".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_sal_review_period", value);
                    }
                    if ("p_sal_review_period_frequency".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_sal_review_period_frequency", value);
                    }
                    if ("p_set_of_books_id".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_set_of_books_id", value);
                    }
                    if ("p_source_type".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_source_type", value);
                    }
                    if ("p_time_normal_finish".equalsIgnoreCase(key) && (value != null)) {
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
                    if ("p_labour_union_member_flag".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_labour_union_member_flag", value);
                    }
                    if ("p_hourly_salaried_code".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_hourly_salaried_code", value);
                    }
                    if ("p_ass_attribute_category".equalsIgnoreCase(key) &&
                        (value != null)) {
                        cs.setObject("p_ass_attribute_category", value);
                    }
                    if ("p_ass_attribute1".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute1", value);
                    }
                    if ("p_ass_attribute2".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute2", value);
                    }
                    if ("p_ass_attribute3".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute3", value);
                    }
                    if ("p_ass_attribut4".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute4", value);
                    }
                    if ("p_ass_attribute5".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute5", value);
                    }
                    if ("p_ass_attribute6".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute6", value);
                    }
                    if ("p_ass_attribute7".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute7", value);
                    }
                    if ("p_ass_attribute8".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute8", value);
                    }
                    if ("p_ass_attribute9".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute9", value);
                    }
                    if ("p_ass_attribute10".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute10", value);
                    }
                    if ("p_ass_attribute11".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute11", value);
                    }
                    if ("p_ass_attribute12".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute12", value);
                    }
                    if ("p_ass_attribute13".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute13", value);
                    }
                    if ("p_ass_attribute14".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute14", value);
                    }
                    if ("p_ass_attribute15".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute15", value);
                    }
                    if ("p_ass_attribute16".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute16", value);
                    }
                    if ("p_ass_attribute17".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute17", value);
                    }
                    if ("p_ass_attribute18".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute18", value);
                    }
                    if ("p_ass_attribute19".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute19", value);
                    }
                    if ("p_ass_attribute20".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute20", value);
                    }
                    if ("p_ass_attribute21".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute21", value);
                    }
                    if ("p_ass_attribute22".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute22", value);
                    }
                    if ("p_ass_attribute23".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute23", value);
                    }
                    if ("p_ass_attribute24".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute24", value);
                    }
                    if ("p_ass_attribute25".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute25", value);
                    }
                    if ("p_ass_attribute26".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute26", value);
                    }
                    if ("p_ass_attribute27".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute27", value);
                    }
                    if ("p_ass_attribute28".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute28", value);
                    }
                    if ("p_ass_attribute29".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute29", value);
                    }
                    if ("p_ass_attribute30".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_ass_attribute30", value);
                    }
                    if ("p_title".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_title", value);
                    }
                    
                    
                    
                    
                    
                    
                    
                    if ("p_notice_period".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_notice_period", value);
                    }
                    if ("p_notice_period_uom".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_notice_period_uom", value);
                    }
                    if ("p_employee_category".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_employee_category", value);
                    }
                    if ("p_work_at_home".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_work_at_home", value);
                    }
                    if ("p_job_post_source_name".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_job_post_source_name", value);
                    }
                    if ("p_grade_ladder_pgm_id".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_grade_ladder_pgm_id", value);
                    }
                    if ("p_supervisor_assignment_id".equalsIgnoreCase(key) && (value != null)) {
                        cs.setObject("p_supervisor_assignment_id", value);
                    }
                }
                cs.execute();
                if (cs.getObject("p_error") != null){
                    createAssignmentReturnMap.put("p_error", cs.getObject("p_error"));
                }
                else{                
                    if (cs.getObject("p_assignment_number") != null)
                        createAssignmentReturnMap.put("p_assignment_number",
                                               cs.getObject("p_assignment_number"));                
                    if (cs.getObject("p_assignment_id") != null)
                        createAssignmentReturnMap.put("p_assignment_id",
                                               cs.getObject("p_assignment_id"));
                    if (cs.getObject("p_object_version_number") != null)
                        createAssignmentReturnMap.put("p_object_version_number",
                                               cs.getObject("p_object_version_number"));
                    if (cs.getObject("p_group_name") != null)
                        createAssignmentReturnMap.put("p_group_name",
                                               cs.getObject("p_group_name"));
                    if (cs.getObject("p_concatenated_segments") != null)
                        createAssignmentReturnMap.put("p_concatenated_segments",
                                               cs.getObject("p_concatenated_segments"));
                    if (cs.getObject("p_cagr_grade_def_id") != null)
                        createAssignmentReturnMap.put("p_cagr_grade_def_id",
                                               cs.getObject("p_cagr_grade_def_id"));
                    if (cs.getObject("p_cagr_concatenated_segments") != null)
                        createAssignmentReturnMap.put("p_cagr_concatenated_segments",
                                               cs.getObject("p_cagr_concatenated_segments"));
                    if (cs.getObject("p_soft_coding_keyflex_id") != null)
                        createAssignmentReturnMap.put("p_soft_coding_keyflex_id",
                                               cs.getObject("p_soft_coding_keyflex_id"));
                    if (cs.getObject("p_people_group_id") != null)
                        createAssignmentReturnMap.put("p_people_group_id",
                                               cs.getObject("p_people_group_id"));
                    if (cs.getObject("p_effective_start_date") != null)
                        createAssignmentReturnMap.put("p_effective_start_date",
                                               cs.getObject("p_effective_start_date"));
                    if (cs.getObject("p_effective_end_date") != null)
                        createAssignmentReturnMap.put("p_effective_end_date",
                                               cs.getObject("p_effective_end_date"));
                    if (cs.getObject("p_assignment_sequence") != null)
                        createAssignmentReturnMap.put("p_assignment_sequence",
                                               cs.getObject("p_assignment_sequence"));
                    if (cs.getObject("p_comment_id") != null)
                        createAssignmentReturnMap.put("p_comment_id",
                                               cs.getObject("p_comment_id"));
                    if (cs.getObject("p_other_manager_warning") != null)
                        createAssignmentReturnMap.put("p_other_manager_warning",
                                               cs.getObject("p_other_manager_warning"));
                    if (cs.getObject("p_hourly_salaried_warning") != null)
                        createAssignmentReturnMap.put("p_hourly_salaried_warning",
                                               cs.getObject("p_hourly_salaried_warning"));
                    if (cs.getObject("p_gsp_post_process_warning") != null)
                        createAssignmentReturnMap.put("p_gsp_post_process_warning",
                                               cs.getObject("p_gsp_post_process_warning"));
                }

            } catch (SQLException e) {
                createAssignmentReturnMap.put("p_error", e.getMessage());
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
            return createAssignmentReturnMap;
        }
    
    /**
         *Reset Create Assignment API Params
         */
        public void resetCreateAssignmentInParams(CallableStatement cs) {

            try {
                cs.setObject("p_grade_id", null);
                cs.setObject("p_position_id", null);
                cs.setObject("p_job_id", null);
                cs.setObject("p_payroll_id", null);
                cs.setObject("p_location_id", null);
                cs.setObject("p_supervisor_id", null);
                cs.setObject("p_special_ceiling_step_id", null);
                cs.setObject("p_pay_basis_id", null);
                cs.setObject("p_change_reason", null);
                cs.setObject("p_comments", null);
                cs.setObject("p_date_probation_end", null);
                cs.setObject("p_default_code_comb_id", null);
                cs.setObject("p_employment_category", null);
                cs.setObject("p_frequency", null);
                cs.setObject("p_internal_address_line", null);
                cs.setObject("p_manager_flag", "N");
                cs.setObject("p_normal_hours", null);
                cs.setObject("p_perf_review_period", null);
                cs.setObject("p_perf_review_period_frequency", null);
                cs.setObject("p_probation_period", null);
                cs.setObject("p_probation_unit", null);
                cs.setObject("p_sal_review_period", null);
                cs.setObject("p_sal_review_period_frequency", null);
                cs.setObject("p_set_of_books_id", null);
                cs.setObject("p_source_type", null);
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
               cs.setObject("p_work_at_home", null);
               cs.setObject("p_job_post_source_name", null);
               cs.setObject("p_grade_ladder_pgm_id", null);
               cs.setObject("p_supervisor_assignment_id", null);
                
           } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        /**
         * Register out params for create Assignment
         */
        public void registerCreateAssignmentOutParams(CallableStatement cs) {
            try {
                cs.registerOutParameter("p_assignment_number", Types.VARCHAR);
                cs.registerOutParameter("p_group_name", Types.VARCHAR);
                cs.registerOutParameter("p_concatenated_segments",Types.VARCHAR);
                cs.registerOutParameter("p_cagr_grade_def_id", Types.NUMERIC);
                cs.registerOutParameter("p_cagr_concatenated_segments", Types.VARCHAR);
                cs.registerOutParameter("p_assignment_id", Types.NUMERIC);
                cs.registerOutParameter("p_soft_coding_keyflex_id", Types.NUMERIC);
                cs.registerOutParameter("p_people_group_id", Types.NUMERIC);
                cs.registerOutParameter("p_object_version_number", Types.NUMERIC);
                cs.registerOutParameter("p_effective_start_date", Types.DATE);
                cs.registerOutParameter("p_effective_end_date", Types.DATE);
                cs.registerOutParameter("p_assignment_sequence", Types.NUMERIC);
                cs.registerOutParameter("p_comment_id", Types.NUMERIC);
                cs.registerOutParameter("p_other_manager_warning", Types.VARCHAR);
                cs.registerOutParameter("p_hourly_salaried_warning", Types.VARCHAR);
                cs.registerOutParameter("p_gsp_post_process_warning", Types.VARCHAR);
                cs.registerOutParameter("p_error", Types.VARCHAR);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
