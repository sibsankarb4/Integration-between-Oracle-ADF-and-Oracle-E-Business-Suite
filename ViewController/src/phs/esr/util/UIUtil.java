package nhs.esr.util;

import java.io.IOException;

import java.math.BigDecimal;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;

import oracle.jbo.Key;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

public class UIUtil {
    public UIUtil() {
        super();
    }

    public static void showPopUp(RichPopup popup) {
        //System.out.println("opening popup : " + popup);
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popup.show(hints);
    }

    //Resolve Expression

    public static Object resolveEL(String el) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContxt = fc.getELContext();
        ValueExpression valueExp =
            elFactory.createValueExpression(elContxt, el, Object.class);
        return valueExp.getValue(elContxt);
    }

    public static void setCurrentRow(String IteratorName){
            BindingContainer bindings = getBindings();
                 //Get Iterator of table
                 DCIteratorBinding parentIter = (DCIteratorBinding)bindings.get(IteratorName);
                 //Get current row key
                 Key parentKey = parentIter.getCurrentRow().getKey();
            //Set again row key as current row
                 parentIter.setCurrentRowWithKey(parentKey.toStringFormat(true));
        }
    /**
         * Generic Method to call operation binding
         **/
         public static BindingContainer getBindings() {
          return BindingContext.getCurrent().getCurrentBindingsEntry();
         }
    //Set Person_ID for CreateAddress BTF

    public static void setPerIDTFInParam(BigDecimal perId) {
        ADFContext adfContext = ADFContext.getCurrent();
        adfContext.getPageFlowScope().put("personId", perId);
    }

    //Resolve Expression Language to get value from binding

    public static Object resolveExpression(String el) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory =
            facesContext.getApplication().getExpressionFactory();
        ValueExpression valueExp =
            expressionFactory.createValueExpression(elContext, el,
                                                    Object.class);
        return valueExp.getValue(elContext);
    }

    //Set new value to Expression Language by index

    public static void setValueToEL(String el, Object val) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory =
            facesContext.getApplication().getExpressionFactory();
        ValueExpression exp =
            expressionFactory.createValueExpression(elContext, el,
                                                    Object.class);
        exp.setValue(elContext, val);
    }


    /**
     * This utility method use to get IteratorBinding
     * @param iteratorName
     * @return DCIteratorBinding
     */
    public static DCIteratorBinding getIteratorBinding(String iteratorName) {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcIteratorBindings =
            bindings.findIteratorBinding(iteratorName);
        return dcIteratorBindings;
    }

    /**
     * This method use to redirect to assignment dashboard
     */
    public static void sendRedirect(String redirctTO) {
        final FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx =
            FacesContext.getCurrentInstance().getExternalContext();
        String url = null;
        if (redirctTO == null) {
            redirctTO = "/faces/nhs/assignment/dashboards/AssignmentsDashBoard.jspx";
            url = ectx.getRequestContextPath() + redirctTO;
        } else {
            url = ectx.getRequestContextPath() + redirctTO;
        }
        try {
            ectx.redirect(url);
        } catch (IOException e) {
            System.out.println("++++++ Exception while navigating to person dashboard : "+e.getMessage());
        }
        fctx.responseComplete();
    }
    
    public static String getSelectedValueFromLOV(String LOVBindingName){
            BindingContainer bindings =BindingContext.getCurrent().getCurrentBindingsEntry();
            JUCtrlListBinding listBinding =(JUCtrlListBinding)bindings.get(LOVBindingName);
            String selectedValue = (String)listBinding.getSelectedValue();
            return selectedValue;
        }
}
