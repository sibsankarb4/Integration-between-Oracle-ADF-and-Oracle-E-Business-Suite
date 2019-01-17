package nhs.esr.apiCall;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import nhs.esr.util.ADFUtil;

public class CreateAndUpdateBudgetAPICall {
    public CreateAndUpdateBudgetAPICall() {
        super();
    }
    int objectVersionNumber;
    public static void main(String[] args) {
//            new CreateAndUpdateBudgetAPICall().updateBugetRecordForAssignment();
//            new CreateAndUpdateBudgetAPICall().createBudgetRecordForAssignment();
        }
    
 //==================== Create ========================================================================================================

        public void createBudgetRecordForAssignment(){
                Map createBudgetMap = new HashMap();
              createBudgetMap.put("p_effective_date",new java.sql.Date(new Date().getTime())); 
                createBudgetMap.put("p_business_group_id",62);
               // createBudgetMap.put("p_assignment_id",28215);
                createBudgetMap.put("p_unit","FTE");
                createBudgetMap.put("p_value",1);
                createBudgetMap.put("p_creation_date",new java.sql.Date(new Date().getTime()));
                
                // createBudgetMap.put("p_effective_date",(Date)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("p_effective_date")));
                //createBudgetMap.put("p_business_group_id",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId"))));
                  createBudgetMap.put("p_assignment_id",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("assignmentId"))));
                createBudgetMap.put("p_created_by",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId"))));
                
                try {
                    Map createBudgetReturnMap = callCreateBudgetAPI(createBudgetMap);
                    Set keys = createBudgetReturnMap.keySet();
                    Iterator itr = keys.iterator();
                    String key = null;
                    Object value = null;
                    while (itr.hasNext()) {
                        key = (String)itr.next();
                        value = createBudgetReturnMap.get(key);
                       // if ("p_error".equalsIgnoreCase(key)) {
                       if ("p_error".equalsIgnoreCase(key) &&(createBudgetReturnMap.get("p_error") !=  null)) {
                            System.out.println("+++++++Error: " + createBudgetReturnMap.get("p_error"));
//                            FacesContext fctx = FacesContext.getCurrentInstance();
//                            FacesMessage message =  new FacesMessage("Error : "+ (String)createBudgetReturnMap.get("p_error"));
//                            fctx.addMessage(null, message);
                           
                        } else {
                            System.out.println("+++Key++++" + key +  "++++++++Value++++++++++" +   createBudgetReturnMap.get(key));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        
        public Map callCreateBudgetAPI(Map createBudgetMap) {
            
            Map createBudgetReturnMap = new HashMap();
                     CallableStatement cs = null;
                     Connection conn = null;
                     try {
                        // conn = ADFUtil.getStaticConnection();
                        conn = ADFUtil.getConnection();
                         String statement =
                             "hr_asg_budget_value_api.CREATE_ASG_BUDGET_VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                         cs = conn.prepareCall("begin " + statement + " ;end;");
                         Set keys = createBudgetMap.keySet();
                         Iterator itr = keys.iterator();
                         String key = null;
                         Object value = null;
                         setDefaultInParamsCreateBudgetAPI(cs);
                         registerOutParamsCreateBudgetAPI(cs);
                         while (itr.hasNext()) {
                             key = (String)itr.next();
                             value = createBudgetMap.get(key);
                             System.out.println("++++ Input : Key : " + key + ", Value : " +
                                                value);
                             if ("p_effective_date".equals(key) && value != null) {
                                 cs.setObject("p_effective_date", value);
                             }
                             if ("p_business_group_id".equals(key) && value != null) {
                                 cs.setObject("p_business_group_id", value);
                             }
                             if ("p_assignment_id".equals(key) && value != null) {
                                 cs.setObject("p_assignment_id", value);
                             }
                             if ("p_unit".equals(key) && value != null) {
                                 cs.setObject("p_unit", value);
                             }
                             if ("p_value".equals(key) && value != null) {
                                 cs.setObject("p_value", value);
                             }
                             if ("p_request_id".equals(key) && value != null) {
                                 cs.setObject("p_request_id", value);
                             }
                             if ("p_program_application_id".equals(key) && value != null) {
                                 cs.setObject("p_program_application_id", value);
                             }
                             if ("p_program_id".equals(key) && value != null) {
                                 cs.setObject("p_program_id", value);
                             }
                             if ("p_program_update_date".equals(key) && value != null) {
                                 cs.setObject("p_program_update_date", value);
                             }
                             if ("p_last_update_date".equals(key) && value != null) {
                                 cs.setObject("p_last_update_date", value);
                             }
                             if ("p_last_updated_by".equals(key) && value != null) {
                                 cs.setObject("p_last_updated_by", value);
                             }
                             if ("p_last_update_login".equals(key) && value != null) {
                                 cs.setObject("p_last_update_login", value);
                             }
                             if ("p_created_by".equals(key) && value != null) {
                                 cs.setObject("p_created_by", value);
                             }
                             if ("p_creation_date".equals(key) && value != null) {
                                 cs.setObject("p_creation_date", value);
                             }
                         }
                         cs.execute();
                         if (cs.getObject("p_object_version_number") != null) {
                             createBudgetReturnMap.put("p_object_version_number",
                                                      cs.getObject("p_object_version_number"));
                         }
                         if (cs.getObject("p_assignment_budget_value_id") != null) {
                             createBudgetReturnMap.put("p_assignment_budget_value_id",
                                                      cs.getObject("p_assignment_budget_value_id"));
                         }

                         for (Object returnKey : createBudgetReturnMap.keySet()) {
                             System.out.println("++++++ Return map key : " +
                                                returnKey.toString() + ", Value : " +
                                                createBudgetReturnMap.get(returnKey));
                         }


                     } catch (SQLException e) {
                         createBudgetReturnMap.put("p_error", e.getMessage());
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
                     return createBudgetReturnMap;
                 }

        public void setDefaultInParamsCreateBudgetAPI(CallableStatement cs) {
                     try {
                         cs.setObject("p_request_id", null);
                         cs.setObject("p_program_application_id", null);
                         cs.setObject("p_program_id", null);
                         cs.setObject("p_program_update_date", null);
                         cs.setObject("p_last_update_date", null);
                         cs.setObject("p_last_updated_by", null);
                         cs.setObject("p_last_update_login", null);
                         cs.setObject("p_created_by", null);
                        java.util.Date utilDate =  new java.util.Date();
                         cs.setObject("p_creation_date", new java.sql.Date(utilDate.getTime()));

                     } catch (SQLException e) {
                         e.printStackTrace();
                     }
                 }

        public void registerOutParamsCreateBudgetAPI(CallableStatement cs) {
                     try {
                         cs.registerOutParameter("p_object_version_number", Types.NUMERIC);
                         cs.registerOutParameter("p_assignment_budget_value_id", Types.NUMERIC);
                     } catch (SQLException e) {
                         e.printStackTrace();
                     }
                 }


    //==================== Update ========================================================================================================
    public void updateBugetRecordForAssignment(){
        
            int assignment_budget_value_id= getAssignmentBudgetValueId();
            int object_version_number=getObjectVersionNumber1();
                    
            Map updateBudgetMap = new HashMap();
            updateBudgetMap.put("p_assignment_budget_value_id",assignment_budget_value_id);
            updateBudgetMap.put("p_effective_date",new java.sql.Date(new Date().getTime())); 
            updateBudgetMap.put("p_datetrack_mode","CORRECTION");
            updateBudgetMap.put("p_business_group_id",62);
            updateBudgetMap.put("p_unit","FTE");
             updateBudgetMap.put("p_value",0.40);
            updateBudgetMap.put("p_object_version_number",object_version_number);
             
           
            // updateBudgetMap.put("p_effective_date",(Date)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("p_effective_date")));
            //updateBudgetMap.put("p_business_group_id",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId"))));
            //  updateBudgetMap.put("p_assignment_id",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("assignmentId"))));
            // updateBudgetMap.put("p_created_by",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId"))));
            
            try {
                Map updateBudgetReturnMap = callupdateBudgetAPI(updateBudgetMap);
                Set keys = updateBudgetReturnMap.keySet();
                Iterator itr = keys.iterator();
                String key = null;
                Object value = null;
                while (itr.hasNext()) {
                    key = (String)itr.next();
                    value = updateBudgetReturnMap.get(key);
                   // if ("p_error".equalsIgnoreCase(key)) {
                   if ("p_error".equalsIgnoreCase(key) &&(updateBudgetReturnMap.get("p_error") !=  null)) {
                        System.out.println("+++++++Error: " + updateBudgetReturnMap.get("p_error"));
//                        FacesContext fctx = FacesContext.getCurrentInstance();
//                        FacesMessage message =  new FacesMessage("Error : "+ (String)updateBudgetReturnMap.get("p_error"));
//                        fctx.addMessage(null, message);
                       
                    } else {
                        System.out.println("+++Key++++" + key +  "++++++++Value++++++++++" +   updateBudgetReturnMap.get(key));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        }
        
    public Map callupdateBudgetAPI(Map updateBudgetMap) {
        
        Map updateBudgetReturnMap = new HashMap();
                 CallableStatement cs = null;
                 Connection conn = null;
                 try {
                     //conn = ADFUtil.getStaticConnection();
                     conn = ADFUtil.getConnection();
                     String statement =
                         "hr_asg_budget_value_api.UPDATE_ASG_BUDGET_VALUE(?,?,?,?,?,?,?)";  //"hr_asg_budget_value_api.UPDATE_ASG_BUDGET_VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                     cs = conn.prepareCall("begin " + statement + " ;end;");
                     Set keys = updateBudgetMap.keySet();
                     Iterator itr = keys.iterator();
                     String key = null;
                     Object value = null;
                     setDefaultInParamsUpadteBudgetAPI(cs);
                     registerOutParamsUpdateBudgetAPI(cs);
                     while (itr.hasNext()) {
                         key = (String)itr.next();
                         value = updateBudgetMap.get(key);
                         System.out.println("++++ Input : Key : " + key + ", Value : " +
                                            value);
                         if ("p_assignment_budget_value_id".equals(key) && value != null) {
                             cs.setObject("p_assignment_budget_value_id", value);
                         }
                         if ("p_effective_date".equals(key) && value != null) {
                             cs.setObject("p_effective_date", value);
                         }
                         if ("p_datetrack_mode".equals(key) && value != null) {
                             cs.setObject("p_datetrack_mode", value);
                         }
                         if ("p_business_group_id".equals(key) && value != null) {
                             cs.setObject("p_business_group_id", value);
                         }
                         if ("p_unit".equals(key) && value != null) {
                             cs.setObject("p_unit", value);
                         }
                         if ("p_value".equals(key) && value != null) {
                             cs.setObject("p_value", value);
                         }
                         if ("p_object_version_number".equals(key) && value != null) {
                             cs.setObject("p_object_version_number", value);
                         }
//                         if ("p_request_id".equals(key) && value != null) {
//                             cs.setObject("p_request_id", value);                             
//                         } 
//                         if ("p_program_application_id".equals(key) && value != null) {
//                             cs.setObject("p_program_application_id", value);
//                         }
//                         if ("p_program_id".equals(key) && value != null) {
//                            cs.setObject("p_program_id", value);
//                            }
//                         if ("p_program_update_date".equals(key) && value != null) {
//                             cs.setObject("p_program_update_date", value);
//                         }
//                         if ("p_last_update_date".equals(key) && value != null) {
//                             cs.setObject("p_last_update_date", value);
//                         }
//                         if ("p_last_updated_by".equals(key) && value != null) {
//                             cs.setObject("p_last_updated_by", value);
//                         }
//                         if ("p_last_update_login".equals(key) && value != null) {
//                             cs.setObject("p_last_update_login", value);
//                         }
//                         if ("p_created_by".equals(key) && value != null) {
//                             cs.setObject("p_created_by", value);
//                         }
//                         if ("p_creation_date".equals(key) && value != null) {
//                             cs.setObject("p_creation_date", value);
//                         }
                       
                     }
                     cs.execute();
                     
                   for (Object returnKey : updateBudgetReturnMap.keySet()) {
                         System.out.println("++++++ Return map key : " +
                                            returnKey.toString() + ", Value : " +
                                            updateBudgetReturnMap.get(returnKey));
                     }


                 } catch (SQLException e) {
                     updateBudgetReturnMap.put("p_error", e.getMessage());
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
                 return updateBudgetReturnMap;
             }
    
    
    public void setDefaultInParamsUpadteBudgetAPI(CallableStatement cs) {
                 try {
                     cs.setObject("p_business_group_id", null);
                     cs.setObject("p_unit", null);
                     cs.setObject("p_value", null);
//                     cs.setObject("p_request_id", null);
//                     cs.setObject("p_program_application_id", null);
//                     cs.setObject("p_program_id", null);
//                     cs.setObject("p_program_update_date", null);
//                     cs.setObject("p_last_update_date", null);
//                     cs.setObject("p_last_updated_by", null);
//                     cs.setObject("p_last_update_login", null);
//                     cs.setObject("p_created_by", null);
//                     cs.setObject("p_creation_date", null);
                      cs.setObject("p_object_version_number", null);

                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }

    public void registerOutParamsUpdateBudgetAPI(CallableStatement cs) {
                 try {
                     cs.registerOutParameter("p_object_version_number", Types.NUMERIC);
                    } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }


    private int getAssignmentBudgetValueId() {
        
        BigDecimal assignmentBudgetValueId =null;
        BigDecimal objectVersionNumber=null;
        java.util.Date  effectiveDate =null;
        java.sql.Date effectiveDatesql=null;
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("p_effective_date") != null) {
        effectiveDate =(java.util.Date)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("p_effective_date");
            effectiveDatesql = new java.sql.Date(effectiveDate.getTime());
           } else {
              effectiveDatesql = new java.sql.Date(new java.util.Date().getTime());
           }
        
        int assignmentId=Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("assignmentId")));
       
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //connection = ADFUtil.getStaticConnection();
            connection = ADFUtil.getConnection();

            String sqlQuery =
                "select assignment_budget_value_id as AssignmentBudgetValueId ,object_version_number as ObjectVersionNumber from PER_ASSIGNMENT_BUDGET_VALUES_F where assignment_id=? and ? between effective_start_date and effective_end_date";
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, assignmentId);
           //   statement.setInt(1, 28197); 
           statement.setDate(2, effectiveDatesql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                assignmentBudgetValueId = (BigDecimal)rs.getObject("AssignmentBudgetValueId");
                objectVersionNumber = (BigDecimal)rs.getObject("ObjectVersionNumber");

                System.out.println("...AssignmentBudgetValueId..." + assignmentBudgetValueId);
                System.out.println("...objectVersionNumber..." + objectVersionNumber);
                setObjectVersionNumber(objectVersionNumber.intValue());

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
        return assignmentBudgetValueId.intValue();
    }

   
    public void setObjectVersionNumber(int objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public int getObjectVersionNumber1() {
        return objectVersionNumber;
    }
}
