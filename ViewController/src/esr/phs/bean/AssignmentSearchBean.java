package esr.nhs.bean;

import esr.nhs.am.NHS_AssignmentAppModuleImpl;

import esr.nhs.vo.AssignmentSearchVOImpl;

import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import nhs.esr.apiCall.SetPrimaryAssignmentAPICall;
import nhs.esr.util.ADFUtil;
import nhs.esr.util.DateUtil;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.adf.view.rich.event.QueryEvent;


import oracle.adf.view.rich.model.AttributeCriterion;
import oracle.adf.view.rich.model.AttributeDescriptor;
import oracle.adf.view.rich.model.ConjunctionCriterion;
import oracle.adf.view.rich.model.Criterion;
import oracle.adf.view.rich.model.QueryDescriptor;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaManager;
import oracle.jbo.ViewObject;
import oracle.jbo.server.ViewObjectImpl;

public class AssignmentSearchBean {
    private RichInputDate effectiveDateBinding;
    private RichCommandButton exportButtonBind;
    private RichCommandButton createAssignmentButtonBind;
    private BindingContainer bindings;
    private RichColumn fromDateColumn;

    public AssignmentSearchBean() {
    }


    public void customAssignmentSearch(QueryEvent queryEvent) {    
       
       
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItrBindings = bindings.findIteratorBinding("AssignmentSearchVO1Iterator");
        ViewObject voImpl = dcItrBindings.getViewObject();
        String sql = null;
        if (null != voImpl.getNamedWhereClauseParam("NInumberBVar")) {
          sql = "national_identifier='" + voImpl.getNamedWhereClauseParam("NInumberBVar").toString() + "'";
        }
        if (null != voImpl.getNamedWhereClauseParam("EmpNumberBVar")) {
          if (null != sql) {
              sql = sql.concat(" AND employee_number='" + voImpl.getNamedWhereClauseParam("EmpNumberBVar").toString() + "'");
          } else {
              sql = "employee_number='" + voImpl.getNamedWhereClauseParam("EmpNumberBVar").toString() + "'";
          }
        }
        if (null != voImpl.getNamedWhereClauseParam("FullNameBVar")) {
          if (null != sql) {
              sql = sql.concat(" AND FULL_NAME='" + voImpl.getNamedWhereClauseParam("FullNameBVar").toString() + "'");
          } else {
              sql = "FULL_NAME='" + voImpl.getNamedWhereClauseParam("FullNameBVar").toString() + "'";
          }
        }
 
        System.out.println("...sql...last ...."+sql);
        if(sql !=null ){
         voImpl.setWhereClause(sql);
         voImpl.executeQuery();
            System.out.println("...sql...last .if..."+sql);
        }
        else{
                voImpl.executeEmptyRowSet();
                System.out.println("...sql...last ..else...."+sql);
            }
            long totalNumberOfAssignment= voImpl.getEstimatedRowCount();
            System.out.println("...voImpl.getEstimatedRowCount...."+voImpl.getEstimatedRowCount());
            
            ExternalContext ectx =  FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> scopeMap = ectx.getSessionMap();
            scopeMap.put("total_Number_Of_Assignment", totalNumberOfAssignment);
            System.out.println("..get from session..."+scopeMap.get("total_Number_Of_Assignment"));    
        
        //========================== initilizing apps==================================================   
//        int p_user_id =Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUserId")));
//        int p_resp_id =Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("responsibilityId")));
//        int p_application_id =Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("applicationId")));
//        int p_org_id = Integer.parseInt((String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("orgId")));
//        SetPrimaryAssignmentAPICall call = new SetPrimaryAssignmentAPICall();
//        String appsSessionStatus = call.appsInitialization(p_user_id, p_resp_id, p_application_id,p_org_id);
        
          
            
    }
    
    

    public void setEffectiveDateBinding(RichInputDate effectiveDateBinding) {
        this.effectiveDateBinding = effectiveDateBinding;

        if (this.effectiveDateBinding.getValue() == null ||
            this.effectiveDateBinding.getValue().toString().equals("")) {
            try {
                if (DateUtil.getEffectiveDate() != null) {
                    this.effectiveDateBinding.setValue(DateUtil.getEffectiveDate());
                }else{
                    this.effectiveDateBinding.setValue(new Date());
                    DateUtil.setEffectiveDate(new java.sql.Date(new Date().getTime()));
                }
            } catch (ParseException e) {
                System.out.println("+++++++++++++++++ Exception while reading effective date from session");
            }

        }
    }
   
    private java.sql.Date convertUtilDateTOSqlDate(String utilDate) {
        java.sql.Date sqldate = null;
        SimpleDateFormat df =
            new SimpleDateFormat("EEEE MMM dd HH:mm:ss Z yyyy");
        if (utilDate != null) {
            try {
                sqldate = new java.sql.Date(df.parse(utilDate).getTime());
            } catch (ParseException e) {
                e.getMessage();
            }
        }
        return sqldate;
    }

    public static Object invokeEL(String el, Class[] paramTypes,
                                  Object[] params) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory =
            facesContext.getApplication().getExpressionFactory();
        MethodExpression exp =
            expressionFactory.createMethodExpression(elContext, el,
                                                     Object.class, paramTypes);

        return exp.invoke(elContext, params);
    }



    public RichInputDate getEffectiveDateBinding() {
        return effectiveDateBinding;
    }

    public void effectiveDateChangeListener(ValueChangeEvent valueChangeEvent) {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            System.out.println("++++++++++++++++++++++ New effective date selected : " + (Date)valueChangeEvent.getNewValue());
            java.util.Date utilDate = (java.util.Date)valueChangeEvent.getNewValue();
            DateUtil.setEffectiveDate(new java.sql.Date(utilDate.getTime()));
        } catch (ParseException e) {
            System.out.println("++++++++++++++++++++++++Exception while setting new selected date as effective date : " +   e.getMessage());
            e.printStackTrace();
        }
    }

    public void setExportButtonBind(RichCommandButton exportButtonBind) {
        this.exportButtonBind = exportButtonBind;
    }

    public RichCommandButton getExportButtonBind() {
        return exportButtonBind;
    }

    public void setCreateAssignmentButtonBind(RichCommandButton createAssignmentButtonBind) {
        this.createAssignmentButtonBind = createAssignmentButtonBind;
    }

    public RichCommandButton getCreateAssignmentButtonBind() {
        return createAssignmentButtonBind;
    }

    public void editAdditionalSupervisorPopupFetchListener(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        if(popupFetchEvent.getLaunchSourceClientId().contains("addSupervisor")){
            BindingContainer bc = getBindings();
            OperationBinding operation = bc.getOperationBinding("CreateInsert");
            operation.execute();
        }
    }
    public BindingContainer getBindings() {
            if (this.bindings == null) {
                FacesContext fc = FacesContext.getCurrentInstance();
                this.bindings =
                        (BindingContainer)fc.getApplication().evaluateExpressionGet(fc, "#{bindings}",
                            BindingContainer.class);
            }
            return this.bindings;
        }

    public void setFromDateColumn(RichColumn fromDateColumn) {
        this.fromDateColumn = fromDateColumn;
    }

    public RichColumn getFromDateColumn() {
        return fromDateColumn;
    }
}
