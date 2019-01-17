package nhs.esr.util;

import java.math.BigDecimal;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

public class DateUtil {
    public DateUtil() {
        super();
    }

    public static void main(String[] args) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        System.out.println(new DateUtil().getAge(df.parse("27-JAN-1990")));
    }

    public static int getAge(Date dob) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (dob != null) {
            now.setTime(new Date());
            born.setTime(dob);
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            if (now.get(Calendar.DAY_OF_YEAR) <
                born.get(Calendar.DAY_OF_YEAR)) {
                age -= 1;
            }
        }
        return age;
    }


    //Convert jbo.domain.Date to java.util.Date

    public static java.util.Date convertDomainDatetoUtilDate(oracle.jbo.domain.Date domainDate) {
        java.util.Date date = null;
        if (domainDate != null) {
            java.sql.Date sqlDate = domainDate.dateValue();
            date = new Date(sqlDate.getTime());
        }
        return date;
    }


    /**
     * Method to set effective date in session
     */
    /*public static void setEffectiveDate(Date effectiveDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        Map sessionMap =
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (effectiveDate != null) {
            sessionMap.put("p_effective_date", effectiveDate);
        } else {
            if (sessionMap.get("p_effective_date") != null) {
                System.out.println("++++++++ Effective date already set in session as : " +
                                   sessionMap.get("p_effective_date"));
            } else {
                sessionMap.put("p_effective_date",
                               df.parse(df.format(new Date())));
            }
        }
        
        //The below code added by Priya to set the effective date in the session scope
        ExternalContext ext =  FacesContext.getCurrentInstance().getExternalContext();
        HttpSession userSession = (HttpSession)ext.getSession(true);
        userSession.setAttribute("effectiveDate", effectiveDate);
                                         

    }*/
    
    public static void setEffectiveDate(java.sql.Date effectiveDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        Map sessionMap =
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (effectiveDate != null) {
            sessionMap.put("p_effective_date", effectiveDate);
        } else {
            if (sessionMap.get("p_effective_date") != null) {
                System.out.println("++++++++ Effective date already set in session as : " +
                                   sessionMap.get("p_effective_date"));
            } else {
                sessionMap.put("p_effective_date",effectiveDate);
            }
        }                                        

    }
    /**
     * Get effective date from session in DD-MMM-YYYY format
     */
    public static Date getEffectiveDate() throws ParseException {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        Date effectiveDate = null;
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("p_effective_date") != null){
            effectiveDate = (Date)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("p_effective_date");
        }else{
            effectiveDate = df.parse(df.format(new Date()));
        }
        return effectiveDate;
    }
    /**
     *Converting date from one format to another
     */
    
    public static String convertDateFormat(String originalDate){
        DateFormat originalFormat = new SimpleDateFormat("dd-MMM-yyyy");
        DateFormat newFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = null;
        String formattedDate = null;
        try {
            date = originalFormat.parse(originalDate);
            formattedDate = newFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
        }
}
