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

public class CreateAndUpdateCostAPICall {
    public CreateAndUpdateCostAPICall() {
        super();
    }
    int objectVersionNumber;
    public static void main(String[] args) {
       // new CreateAndUpdateCostAPICall().createCostRecordForAssignmentDecision();
       // new CreateAndUpdateCostAPICall().updateCostRecordForAssignmentDecision();
    }
    
    
 //==================== Create ========================================================================================================
    
    public boolean createCostRecordForAssignmentDecision(){
      String subjectiveCode=(String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subjectiveCode"));   
     //  String subjectiveCode="NHS000001";
        String concatenated_segments="|||"+subjectiveCode+"||||";   
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        BigDecimal cost_allocation_keyflex_id =null;
        boolean createFlag=false;
        
        try {
       // connection = ADFUtil.getStaticConnection();
                connection = ADFUtil.getConnection();
         String sqlQuery ="select cost_allocation_keyflex_id as CostAllocationKeyFlexId from PAY_COST_ALLOCATION_KEYFLE_KFV where concatenated_segments=?";
          preparedstatement = connection.prepareStatement(sqlQuery);
          preparedstatement.setString(1,concatenated_segments);
          ResultSet resultSet = preparedstatement.executeQuery();  
            
         while (resultSet.next()) {
                          System.out.println("......in while............");
                         cost_allocation_keyflex_id = (BigDecimal)resultSet.getObject("CostAllocationKeyFlexId");
                          createFlag=true;
                         System.out.println("..in while......cost_allocation_keyflex_id..." + cost_allocation_keyflex_id.intValue());
                          break; //as we are expecting only one row
                      }
            }catch (Exception e) {
                      e.printStackTrace();
                  } finally {
                      if (preparedstatement != null) {
                          try {
                              preparedstatement.close();
                          } catch (SQLException e) {
                              e.printStackTrace();
                          }
                      }
                  }
        System.out.println("...cost_allocation_keyflex_id."+cost_allocation_keyflex_id);
        if(createFlag){
        createCostRecordForAssignment(cost_allocation_keyflex_id);}
        
        return createFlag;
           
        }
     
    public void createCostRecordForAssignment(BigDecimal cost_allocation_keyflex_id){
                   Map createCostMap = new HashMap();
                  createCostMap.put("p_effective_date",new java.sql.Date(new Date().getTime())); 
                  // createCostMap.put("p_assignment_id",26723);
                   createCostMap.put("p_proportion",1);// '1' will be the same
                   createCostMap.put("p_business_group_id",62);
                   System.out.println("...cost_allocation_keyflex_id..."+cost_allocation_keyflex_id);
                   createCostMap.put("p_cost_allocation_keyflex_id",cost_allocation_keyflex_id.intValue()); //37651
                   
                   // createCostMap.put("p_effective_date",(java.sql.Date)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("p_effective_date")));
                  // createCostMap.put("p_business_group_id",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId"))));
                     createCostMap.put("p_assignment_id",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("assignmentId"))));
                  //   createCostMap.put("p_created_by",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId"))));
                   
                   try {
                       Map createCostReturnMap = callCreateCostAPI(createCostMap);
                       Set keys = createCostReturnMap.keySet();
                       Iterator itr = keys.iterator();
                       String key = null;
                       Object value = null;
                       while (itr.hasNext()) {
                           key = (String)itr.next();
                           value = createCostReturnMap.get(key);
                          // if ("p_error".equalsIgnoreCase(key)) {
                          if ("p_error".equalsIgnoreCase(key) &&(createCostReturnMap.get("p_error") !=  null)) {
                               System.out.println("+++++++Error: " + createCostReturnMap.get("p_error"));
                               FacesContext fctx = FacesContext.getCurrentInstance();
                               FacesMessage message =  new FacesMessage("Error : "+ (String)createCostReturnMap.get("p_error"));
                               fctx.addMessage(null, message);
                              
                           } else {
                               System.out.println("+++Key++++" + key +  "++++++++Value++++++++++" +   createCostReturnMap.get(key));
                           }
                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }   
    
    public Map callCreateCostAPI(Map createCostMap) {
          Map createCostReturnMap = new HashMap();
          CallableStatement cs = null;
          Connection conn = null;
          try {
              //conn = ADFUtil.getStaticConnection();
              conn = ADFUtil.getConnection();
              String statement =
                  "PAY_COST_ALLOCATION_API.CREATE_COST_ALLOCATION(?,?,?,?,?,?,?,?,?,?)";
              cs = conn.prepareCall("begin " + statement + " ;end;");
              Set keys = createCostMap.keySet();
              Iterator itr = keys.iterator();
              String key = null;
              Object value = null;
             // setDefaultInParamsCreateCostAPI(cs);
              registerOutParamsCreateCostAPI(cs);
              while (itr.hasNext()) {
                  key = (String)itr.next();
                  value = createCostMap.get(key);
                  System.out.println("++++ Input : Key : " + key + ", Value : " +
                                     value);
                  if ("p_effective_date".equals(key) && value != null) {
                      cs.setObject("p_effective_date", value);
                  }
                  if ("p_assignment_id".equals(key) && value != null) {
                      cs.setObject("p_assignment_id", value);
                  }
                  if ("p_proportion".equals(key) && value != null) {
                      cs.setObject("p_proportion", value);
                  }
                  if ("p_business_group_id".equals(key) && value != null) {
                      cs.setObject("p_business_group_id", value);
                  }
                  if ("p_cost_allocation_keyflex_id".equals(key) &&
                      value != null) {
                      cs.setObject("p_cost_allocation_keyflex_id", value);
                  }
//                  if ("p_segment1".equals(key) && value != null) {
//                      cs.setObject("p_segment1", value);
//                  }
//                  if ("p_segment2".equals(key) && value != null) {
//                      cs.setObject("p_segment2", value);
//                  }
//                  if ("p_segment3".equals(key) && value != null) {
//                      cs.setObject("p_segment3", value);
//                  }
//                  if ("p_segment4".equals(key) && value != null) {
//                      cs.setObject("p_segment4", value);
//                  }
//                  if ("p_segment5".equals(key) && value != null) {
//                      cs.setObject("p_segment5", value);
//                  }
//                  if ("p_segment6".equals(key) && value != null) {
//                      cs.setObject("p_segment6", value);
//                  }
//                  if ("p_segment7".equals(key) && value != null) {
//                      cs.setObject("p_segment7", value);
//                  }
//                  if ("p_segment8".equals(key) && value != null) {
//                      cs.setObject("p_segment8", value);
//                  }
//                  if ("p_segment9".equals(key) && value != null) {
//                      cs.setObject("p_segment9", value);
//                  }
//                  if ("p_segment10".equals(key) && value != null) {
//                      cs.setObject("p_segment10", value);
//                  }
//                  if ("p_segment11".equals(key) && value != null) {
//                      cs.setObject("p_segment11", value);
//                  }
//                  if ("p_segment12".equals(key) && value != null) {
//                      cs.setObject("p_segment12", value);
//                  }
//                  if ("p_segment13".equals(key) && value != null) {
//                      cs.setObject("p_segment13", value);
//                  }
//                  if ("p_segment14".equals(key) && value != null) {
//                      cs.setObject("p_segment14", value);
//                  }
//                  if ("p_segment15".equals(key) && value != null) {
//                      cs.setObject("p_segment15", value);
//                  }
//                  if ("p_segment16".equals(key) && value != null) {
//                      cs.setObject("p_segment16", value);
//                  }
//                  if ("p_segment17".equals(key) && value != null) {
//                      cs.setObject("p_segment17", value);
//                  }
//                  if ("p_segment18".equals(key) && value != null) {
//                      cs.setObject("p_segment18", value);
//                  }
//                  if ("p_segment19".equals(key) && value != null) {
//                      cs.setObject("p_segment19", value);
//                  }
//                  if ("p_segment20".equals(key) && value != null) {
//                      cs.setObject("p_segment20", value);
//                  }
//                  if ("p_segment21".equals(key) && value != null) {
//                      cs.setObject("p_segment21", value);
//                  }
//                  if ("p_segment22".equals(key) && value != null) {
//                      cs.setObject("p_segment22", value);
//                  }
//                  if ("p_segment23".equals(key) && value != null) {
//                      cs.setObject("p_segment23", value);
//                  }
//                  if ("p_segment24".equals(key) && value != null) {
//                      cs.setObject("p_segment24", value);
//                  }
//                  if ("p_segment25".equals(key) && value != null) {
//                      cs.setObject("p_segment25", value);
//                  }
//                  if ("p_segment26".equals(key) && value != null) {
//                      cs.setObject("p_segment26", value);
//                  }
//                  if ("p_segment27".equals(key) && value != null) {
//                      cs.setObject("p_segment27", value);
//                  }
//                  if ("p_segment28".equals(key) && value != null) {
//                      cs.setObject("p_segment28", value);
//                  }
//                  if ("p_segment29".equals(key) && value != null) {
//                      cs.setObject("p_segment29", value);
//                  }
//                  if ("p_segment30".equals(key) && value != null) {
//                      cs.setObject("p_segment30", value);
//                  }
//                  if ("p_concat_segments".equals(key) && value != null) {
//                      cs.setObject("p_concat_segments", value);
//                  }
//                  if ("p_request_id".equals(key) && value != null) {
//                      cs.setObject("p_request_id", value);
//                  }
//                  if ("p_program_application_id".equals(key) && value != null) {
//                      cs.setObject("p_program_application_id", value);
//                  }
//                  if ("p_program_id".equals(key) && value != null) {
//                      cs.setObject("p_program_id", value);
//                  }
//                  if ("p_program_update_date".equals(key) && value != null) {
//                      cs.setObject("p_program_update_date", value);
//                  }
                
              }
              cs.execute();
              if (cs.getObject("p_combination_name") != null) {
                  createCostReturnMap.put("p_combination_name",
                                           cs.getObject("p_combination_name"));
              }
              if (cs.getObject("p_cost_allocation_id") != null) {
                  createCostReturnMap.put("p_cost_allocation_id",
                                           cs.getObject("p_cost_allocation_id"));
              }
              if (cs.getObject("p_effective_start_date") != null) {
                  createCostReturnMap.put("p_effective_start_date",
                                           cs.getObject("p_effective_start_date"));
              }
              if (cs.getObject("p_effective_end_date") != null) {
                  createCostReturnMap.put("p_effective_end_date",
                                           cs.getObject("p_effective_end_date"));
              }
              if (cs.getObject("p_cost_allocation_keyflex_id") != null) {
                  createCostReturnMap.put("p_cost_allocation_keyflex_id",
                                           cs.getObject("p_cost_allocation_keyflex_id"));
              }
              if (cs.getObject("p_object_version_number") != null) {
                  createCostReturnMap.put("p_object_version_number",
                                           cs.getObject("p_object_version_number"));
              }

              for (Object returnKey : createCostReturnMap.keySet()) {
                  System.out.println("++++++ Return map key : " +
                                     returnKey.toString() + ", Value : " +
                                     createCostReturnMap.get(returnKey));
              }


          } catch (SQLException e) {
              createCostReturnMap.put("p_error", e.getMessage());
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
          return createCostReturnMap;
      }
    
    public void registerOutParamsCreateCostAPI(CallableStatement cs) {
          try {
              cs.registerOutParameter("p_combination_name", Types.VARCHAR);
              cs.registerOutParameter("p_cost_allocation_id", Types.NUMERIC);
              cs.registerOutParameter("p_effective_start_date", Types.DATE);
              cs.registerOutParameter("p_effective_end_date", Types.DATE);
              cs.registerOutParameter("p_cost_allocation_keyflex_id", Types.NUMERIC);
              cs.registerOutParameter("p_object_version_number", Types.NUMERIC);
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
        
    public void setDefaultInParamsCreateCostAPI(CallableStatement cs) {
    //          try {
    //              cs.setObject("p_segment1", null);
    //              cs.setObject("p_segment2", null);
    //              cs.setObject("p_segment3", null);
    //              cs.setObject("p_segment4", null);
    //              cs.setObject("p_segment5", null);
    //              cs.setObject("p_segment6", null);
    //              cs.setObject("p_segment7", null);
    //              cs.setObject("p_segment8", null);
    //              cs.setObject("p_segment9", null);
    //              cs.setObject("p_segment10", null);
    //              cs.setObject("p_segment11", null);
    //              cs.setObject("p_segment12", null);
    //              cs.setObject("p_segment13", null);
    //              cs.setObject("p_segment14", null);
    //              cs.setObject("p_segment15", null);
    //              cs.setObject("p_segment16", null);
    //              cs.setObject("p_segment17", null);
    //              cs.setObject("p_segment18", null);
    //              cs.setObject("p_segment19", null);
    //              cs.setObject("p_segment20", null);
    //              cs.setObject("p_segment21", null);
    //              cs.setObject("p_segment22", null);
    //              cs.setObject("p_segment23", null);
    //              cs.setObject("p_segment24", null);
    //              cs.setObject("p_segment25", null);
    //              cs.setObject("p_segment26", null);
    //              cs.setObject("p_segment27", null);
    //              cs.setObject("p_segment28", null);
    //              cs.setObject("p_segment29", null);
    //              cs.setObject("p_segment30", null);
    //              cs.setObject("p_concat_segments", null);
    //              cs.setObject("p_request_id", null);
    //              cs.setObject("p_program_application_id", null);
    //              cs.setObject("p_program_id", null);
    //              cs.setObject("p_program_update_date", null);

    //          } catch (SQLException e) {
    //              e.printStackTrace();
    //          }
      }
    
    
    //==================== Update ========================================================================================================
       
       public void updateCostRecordForAssignmentDecision(){
        String subjectiveCode=(String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subjectiveCode"));   
         // String subjectiveCode="NHS0020y";
           String concatenated_segments="|||"+subjectiveCode+"||||";   
           Connection connection = null;
           PreparedStatement preparedstatement = null;
           BigDecimal cost_allocation_keyflex_id =null;
           boolean updateFlag=false;
           
           try {
          // connection = ADFUtil.getStaticConnection();
             connection = ADFUtil.getConnection();
            String sqlQuery ="select cost_allocation_keyflex_id as CostAllocationKeyFlexId from PAY_COST_ALLOCATION_KEYFLE_KFV where concatenated_segments=?";
             preparedstatement = connection.prepareStatement(sqlQuery);
             preparedstatement.setString(1,concatenated_segments);
             ResultSet resultSet = preparedstatement.executeQuery();  
               
            while (resultSet.next()) {
                             System.out.println("......in while......update......");
                            cost_allocation_keyflex_id = (BigDecimal)resultSet.getObject("CostAllocationKeyFlexId");
                             updateFlag=true;
                            System.out.println("..in while...update...cost_allocation_keyflex_id..." + cost_allocation_keyflex_id.intValue());
                             break; //as we are expecting only one row
                         }
               }catch (Exception e) {
                         e.printStackTrace();
                     } finally {
                         if (preparedstatement != null) {
                             try {
                                 preparedstatement.close();
                             } catch (SQLException e) {
                                 e.printStackTrace();
                             }
                         }
                     }
           System.out.println("..update...cost_allocation_keyflex_id."+cost_allocation_keyflex_id);
           if(updateFlag){
           updateCostRecordForAssignment(cost_allocation_keyflex_id);}
              
           }
        
       public void updateCostRecordForAssignment(BigDecimal cost_allocation_keyflex_id){
           
                      int cost_allocation_id  = getCostAllocationId();
                      int object_version_number=getObjectVersionNumber1();
                     
                      Map updateCostMap = new HashMap();
                     // updateCostMap.put("p_effective_date",new java.sql.Date(new Date().getTime())); 
                      updateCostMap.put("p_datetrack_update_mode","CORRECTION");
                      updateCostMap.put("p_cost_allocation_id",cost_allocation_id);
                      updateCostMap.put("p_object_version_number",object_version_number);
                      System.out.println("...cost_allocation_keyflex_id....upadte..."+cost_allocation_keyflex_id);
                      updateCostMap.put("p_cost_allocation_keyflex_id",cost_allocation_keyflex_id.intValue()); //37651
                      
                      updateCostMap.put("p_effective_date",(Date)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("p_effective_date")));
                    // updateCostMap.put("p_business_group_id",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId"))));
//                       updateCostMap.put("p_assignment_id",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("assignmentId"))));
//                     updateCostMap.put("p_created_by",Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId"))));
                      
                      try {
                          Map updateCostReturnMap = callUpdateCostAPI(updateCostMap);
                          Set keys = updateCostReturnMap.keySet();
                          Iterator itr = keys.iterator();
                          String key = null;
                          Object value = null;
                          while (itr.hasNext()) {
                              key = (String)itr.next();
                              value = updateCostReturnMap.get(key);
                             // if ("p_error".equalsIgnoreCase(key)) {
                             if ("p_error".equalsIgnoreCase(key) &&(updateCostReturnMap.get("p_error") !=  null)) {
                                  System.out.println("+++++++Error: " + updateCostReturnMap.get("p_error"));
                                  FacesContext fctx = FacesContext.getCurrentInstance();
                                  FacesMessage message =  new FacesMessage("Error : "+ (String)updateCostReturnMap.get("p_error"));
                                  fctx.addMessage(null, message);
                                 
                              } else {
                                  System.out.println("+++Key++++" + key +  "++++++++Value++++++++++"+updateCostReturnMap.get(key));
                              }
                          }
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }   
       
       public Map callUpdateCostAPI(Map createCostMap) {
             Map updateCostReturnMap = new HashMap();
             CallableStatement cs = null;
             Connection conn = null;
             try {
                 //conn = ADFUtil.getStaticConnection();
                 conn = ADFUtil.getConnection();
                 String statement =
                     "PAY_COST_ALLOCATION_API.UPDATE_COST_ALLOCATION(?,?,?,?,?,?,?,?)";
                 cs = conn.prepareCall("begin " + statement + " ;end;");
                 Set keys = createCostMap.keySet();
                 Iterator itr = keys.iterator();
                 String key = null;
                 Object value = null;
                // setDefaultInParamsCreateCostAPI(cs);
                 registerOutParamsCreateCostAPI(cs);
                 while (itr.hasNext()) {
                     key = (String)itr.next();
                     value = createCostMap.get(key);
                     System.out.println("++++ Input : Key : " + key + ", Value : " +
                                        value);
                     if ("p_effective_date".equals(key) && value != null) {
                         cs.setObject("p_effective_date", value);
                     }
                     if ("p_datetrack_update_mode".equals(key) && value != null) {
                         cs.setObject("p_datetrack_update_mode", value);
                     }
                     if ("p_cost_allocation_id".equals(key) && value != null) {
                         cs.setObject("p_cost_allocation_id", value);
                     }
                     if ("p_object_version_number".equals(key) && value != null) {
                         cs.setObject("p_object_version_number", value);
                     }
                     if ("p_cost_allocation_keyflex_id".equals(key) &&
                         value != null) {
                         cs.setObject("p_cost_allocation_keyflex_id", value);
                     }
                       
                 }
                 cs.execute();
                 if (cs.getObject("p_combination_name") != null) {
                     updateCostReturnMap.put("p_combination_name",
                                              cs.getObject("p_combination_name"));
                 }
                if (cs.getObject("p_cost_allocation_keyflex_id") != null) {
                     updateCostReturnMap.put("p_cost_allocation_keyflex_id",
                                              cs.getObject("p_cost_allocation_keyflex_id"));
                 }
                 if (cs.getObject("p_effective_start_date") != null) {
                     updateCostReturnMap.put("p_effective_start_date",
                                              cs.getObject("p_effective_start_date"));
                 }
                 if (cs.getObject("p_effective_end_date") != null) {
                     updateCostReturnMap.put("p_effective_end_date",
                                              cs.getObject("p_effective_end_date"));
                 }
               
                 if (cs.getObject("p_object_version_number") != null) {
                     updateCostReturnMap.put("p_object_version_number",
                                              cs.getObject("p_object_version_number"));
                 }

                 for (Object returnKey : updateCostReturnMap.keySet()) {
                     System.out.println("++++++ Return map key : " +
                                        returnKey.toString() + ", Value : " +
                                        updateCostReturnMap.get(returnKey));
                 }


             } catch (SQLException e) {
                 updateCostReturnMap.put("p_error", e.getMessage());
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
             return updateCostReturnMap;
         }
       
       public void registerOutParamsUpdateCostAPI(CallableStatement cs) {
             try {
                 cs.registerOutParameter("p_object_version_number", Types.NUMERIC);
                 cs.registerOutParameter("p_combination_name", Types.VARCHAR);
                 cs.registerOutParameter("p_cost_allocation_keyflex_id", Types.NUMERIC);
                 cs.registerOutParameter("p_effective_start_date", Types.DATE);
                 cs.registerOutParameter("p_effective_end_date", Types.DATE);
         
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }



    private int getCostAllocationId() {
        BigDecimal costAllocationId =null;
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
                "select cost_allocation_id CostAllocationId,object_version_number as ObjectVersionNumber from PAY_COST_ALLOCATIONS_f where assignment_id=? and ? between effective_start_date and effective_end_date";
            statement = connection.prepareStatement(sqlQuery);
           // statement.setInt(1, assignmentId);
              statement.setInt(1, assignmentId); 
           statement.setDate(2, effectiveDatesql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                costAllocationId = (BigDecimal)rs.getObject("CostAllocationId");
                objectVersionNumber = (BigDecimal)rs.getObject("ObjectVersionNumber");

                System.out.println("...AssignmentBudgetValueId..." + costAllocationId);
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
        return costAllocationId.intValue();
        
    }

        public void setObjectVersionNumber(int objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public int getObjectVersionNumber1() {
        return objectVersionNumber;
    }

    
}
