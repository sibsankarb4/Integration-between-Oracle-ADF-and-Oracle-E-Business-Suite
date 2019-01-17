package nhs.esr.ebiz.security;

import esr.nhs.am.NHS_AssignmentAppModuleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nhs.esr.apiCall.SetPrimaryAssignmentAPICall;

import nhs.esr.util.ADFUtil;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.controller.v2.lifecycle.PagePhaseEvent;
import oracle.adf.controller.v2.lifecycle.PagePhaseListener;

import oracle.adf.share.ADFContext;

import oracle.apps.fnd.ext.common.AppsRequestWrapper;
import oracle.apps.fnd.ext.common.CookieStatus;
import oracle.apps.fnd.ext.common.EBiz;
import oracle.apps.fnd.ext.common.Session;

import oracle.jbo.server.ViewObjectImpl;

public class ADFCustomPhaseListner implements PagePhaseListener {
    public ADFCustomPhaseListner() {
        super();
    }

    AppsRequestWrapper wrappedRequest = null;
    HttpServletRequest request = null;
    HttpServletResponse response = null;
    String functionId = null;
    String currentUser = null;
    String currentUserId = null;
    String responsibilityId = null;
    String applicationId = null;
    String orgId = null;
    String trustId = null;
    String functionName = null;
    String effectiveDate = null;
    String personId = null;
    String personTypeId = null;
    boolean HRVersion =false;
    boolean PayrollVersion =false;

    public void afterPhase(PagePhaseEvent pagePhaseEvent) {

        if (pagePhaseEvent.getPhaseId() == Lifecycle.PREPARE_RENDER_ID) {
            System.out.println("in after phase ++++++++++++++");
            if (wrappedRequest != null &&
                wrappedRequest.getConnection() != null) {
                try {
                    if (!wrappedRequest.getConnection().isClosed())
                        wrappedRequest.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void beforePhase(PagePhaseEvent pagePhaseEvent) {

        if (pagePhaseEvent.getPhaseId() == Lifecycle.INIT_CONTEXT_ID) {
            String agent = null;
            PreparedStatement stmt = null;
            Connection EBSconn = null;
           FacesContext fctx = FacesContext.getCurrentInstance();
            request =
                    (HttpServletRequest)fctx.getExternalContext().getRequest();
            response =
                    (HttpServletResponse)fctx.getExternalContext().getResponse();
            CookieStatus icxCookieStatus = null;
            
    /**
      * BYPASSING SECURITY - REMOVE AFTER TESTING FROM LOCAL IS DONE
      */
        //started
 /* 
          try {

                    EBSconn = ConnectionProvider.getConnection();
                    EBiz instance = EBizUtil.getEBizInstance();

                    wrappedRequest = new AppsRequestWrapper(request, response, EBSconn,  instance);
                    Session session = wrappedRequest.getAppsSession(true);
                    icxCookieStatus =session.getCurrentState().getIcxCookieStatus();
                    agent =  wrappedRequest.getEbizInstance().getAppsServletAgent();

                    if (!icxCookieStatus.equals(CookieStatus.VALID)) {
                        response.sendRedirect(agent + "AppsLocalLogin.jsp");
                        return;
                    }

                    currentUser = session.getUserName();
                    currentUserId = session.getUserId();
                    // oracle.jbo.domain.Number userIdNumber= new oracle.jbo.domain.Number(currentUserId);
                    Map sessionMap = session.getInfo();

                    responsibilityId =(String)sessionMap.get("RESPONSIBILITY_ID");
                    applicationId = (String)sessionMap.get("RESPONSIBILITY_APPLICATION_ID");
                    orgId = (String)sessionMap.get("ORG_ID");
                    functionId = (String)sessionMap.get("FUNCTION_ID");
                    effectiveDate = (String)sessionMap.get("EFFECTIVE_DATE");
                    personId = (String)sessionMap.get("PERSON_ID");
                    personTypeId = (String)sessionMap.get("PERSON_TYPE_ID");


                    ExternalContext ectx = fctx.getExternalContext();
                    HttpSession httpsession =(HttpSession)ectx.getSession(true);
                    httpsession.setAttribute("currentUser", currentUser);
                    httpsession.setAttribute("currentUserId", currentUserId);
                    httpsession.setAttribute("responsibilityId",  responsibilityId);
                    httpsession.setAttribute("applicationId", applicationId);
                    httpsession.setAttribute("functionId", functionId);
                    httpsession.setAttribute("orgId", orgId);

                    //==========================for trust id==================================================================
                    String sqlQuery =
                        "SELECT fnd_profile.value_specific (name => 'XX_VPD_TRUST_IDENTIFIER' ,responsibility_id => ? ,application_id => ? ) as Trust_ID from dual";

                    stmt = wrappedRequest.getConnection().prepareStatement(sqlQuery);
                    stmt.setString(1, responsibilityId);
                    stmt.setString(2, applicationId);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        trustId = (String)rs.getObject("Trust_ID");
                        break; //as we are expecting only one row
                    }

                    httpsession.setAttribute("trustId", trustId);
                //    ===================================================================================================================
                 // select function_name from fnd_form_functions where function_id=55973;
                  // httpsession.setAttribute("functionName", functionName);
                //    ===================================================================================================================
                    //putting the same in session map as well
                    Map<String, Object> scopeMap = ectx.getSessionMap();
                    scopeMap.put("currentUser", currentUser);
                    scopeMap.put("currentUserId", currentUserId);
                    scopeMap.put("responsibilityId", responsibilityId);
                    scopeMap.put("functionId", functionId);
                    scopeMap.put("orgId", orgId);
                    scopeMap.put("trustId", trustId);
                    scopeMap.put("functionName", functionName);
                    scopeMap.put("applicationId", applicationId);

                    //===================================================================================================================
                       
                    //================================do not use this below effective date code===================================================================================
//                    java.sql.Date effectiveDatesql=null;
//                        effectiveDatesql =new java.sql.Date(new java.util.Date().getTime());
//                        scopeMap.put("p_effective_date", effectiveDatesql);   
//                            httpsession.setAttribute("p_effective_date", effectiveDatesql);
//                       
//                                       
       //==========================/HR==================================================================
                       PreparedStatement stmtHR = null;
                       String sqlQueryHR = " select * from fnd_responsibility where responsibility_key like '%HR%' and responsibility_id=?";
                      

                      stmtHR = wrappedRequest.getConnection().prepareStatement(sqlQueryHR);
                       stmtHR.setString(1, responsibilityId);
                       
                       ResultSet rsHR = stmtHR.executeQuery();
                     
                    if (!rsHR.next()){
                    //ResultSet is empty
                    HRVersion =false;
                      } else {
                            HRVersion =true;
                        }
    //========================//==================payroll======//========================//========================               
                    
                    PreparedStatement stmtPayroll = null;
                    String sqlQueryPayroll = "select * from fnd_responsibility where responsibility_key like '%PAY%' and responsibility_id=?";
                    stmtPayroll = wrappedRequest.getConnection().prepareStatement(sqlQueryPayroll);
                    
                    stmtPayroll.setString(1, responsibilityId);
                    ResultSet rsPayroll = stmtPayroll.executeQuery();
                    
                     
                    if (!rsPayroll.next()){
                    //ResultSet is empty
                    PayrollVersion =false;
                      } else {
                            PayrollVersion =true;
                        }
   
       //========================//========================//========================//========================
                    scopeMap.put("PayrollVersion", PayrollVersion);
                    scopeMap.put("HRVersion", HRVersion);
                    httpsession.setAttribute("PayrollVersion", PayrollVersion);
                    httpsession.setAttribute("HRVersion", HRVersion);
     //========================//========================//========================//========================
//                    FacesMessage message =
//                    new FacesMessage("User Name : " + currentUser +
//                                     " , Current User Id : " + currentUserId +
//                                     " , responsibility-Id : " +  responsibilityId +
//                                     " , application-Id : " + applicationId +
//                                     " , trust--Id: " + trustId +
//                                      " , function-Id: " + functionId +
//                                     " , org-Id : " + orgId+
//                                     " , effectiveDatesql : " + scopeMap.get("p_effective_date")+
//                                      " , assignmentId : " + scopeMap.get("assignmentId")+
//                                     " , functionName : " + functionName);
//                fctx.addMessage(null, message); 

       //========================== initilizing apps==================================================
       int p_user_id =Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId")));
       int p_resp_id =Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("responsibilityId")));
       int p_application_id =Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("applicationId")));
       int p_org_id =  Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId")));
       SetPrimaryAssignmentAPICall call = new SetPrimaryAssignmentAPICall();
       
       String appsSessionStatus = call.appsInitialization(p_user_id, p_resp_id, p_application_id,p_org_id);

       NHS_AssignmentAppModuleImpl appmodule =
         (NHS_AssignmentAppModuleImpl)ADFUtil.getDataControl("NHS_AssignmentAppModuleDataControl");
         ViewObjectImpl orgVO =appmodule.getOrganizationsLOVVO1();
           orgVO.executeQuery();


                } catch (Exception e) {
                    e.printStackTrace();
                } 
                  finally {
                    if (EBSconn != null) {
                        try {
                            EBSconn.close();
                        } catch (SQLException e) {
                            e.getMessage();
                        }
                    }
                    if (wrappedRequest != null) {
                        try {
                            wrappedRequest.getConnection().close();
                        } catch (SQLException e3) {
                            e3.getMessage();
                            wrappedRequest = null;
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }  */          //ended
            
             //===================================setting session value hard coded to run from local================================================================================ 
    // /*
          ExternalContext ectx = fctx.getExternalContext();
             HttpSession httpsession = (HttpSession)ectx.getSession(true);
             httpsession.setAttribute("currentUser", "SROY");
             httpsession.setAttribute("currentUserId", "13558");
             httpsession.setAttribute("responsibilityId", "50552");
           //  httpsession.setAttribute("responsibilityId", "50396");
             httpsession.setAttribute("applicationId", "800");
             httpsession.setAttribute("functionId", "55973");
             httpsession.setAttribute("orgId", "62");
            httpsession.setAttribute("trustId", "298");
             //httpsession.setAttribute("trustId", "258");
             //===================================================================================================================
             //putting the same in session map as well adf.context.current.sessionScope.get('test')
             Map<String, Object> scopeMap = ectx.getSessionMap();
             scopeMap.put("currentUser", "SROY");
             scopeMap.put("currentUserId", "13558");
              scopeMap.put("responsibilityId", "50552");
            // scopeMap.put("responsibilityId", "50396");
             scopeMap.put("applicationId", "800");
             scopeMap.put("functionId", "55973");
             scopeMap.put("orgId", "62");
             scopeMap.put("trustId", "298");
           //  scopeMap.put("trustId", "258");
             DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            //===================================================================================================================
            PayrollVersion=true;
            HRVersion=true;
            scopeMap.put("PayrollVersion", PayrollVersion);
            scopeMap.put("HRVersion", HRVersion);
            httpsession.setAttribute("PayrollVersion", PayrollVersion);
            httpsession.setAttribute("HRVersion", HRVersion);
            //===================================================================================================================
            
            //========================== initilizing apps :: dont remove this below code ==================================================
         int p_user_id =
             Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId")));
         int p_resp_id =
             Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("responsibilityId")));
         int p_application_id =
             Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("applicationId")));
         int p_org_id =
             Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId")));
         SetPrimaryAssignmentAPICall call = new SetPrimaryAssignmentAPICall();
         
         String appsSessionStatus = call.appsInitialization(p_user_id, p_resp_id, p_application_id,p_org_id);
         
              NHS_AssignmentAppModuleImpl appmodule =
                (NHS_AssignmentAppModuleImpl)ADFUtil.getDataControl("NHS_AssignmentAppModuleDataControl");
                ViewObjectImpl orgVO =appmodule.getOrganizationsLOVVO1();
                orgVO.executeQuery();
         
           
          }  //  */
     }

   }

    

