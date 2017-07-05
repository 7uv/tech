package com.sayantan.main.util;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by me on 6/10/17.
 */
@ManagedBean(name="commmonUtils")
@ApplicationScoped
public class CommonUtils implements Serializable{
    private static final long serialVersionUID = -1L;
    public void redirectWithGet(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        if(queryString != null){
           requestURL.append('?').append(queryString).toString();
        }
        String url = requestURL.toString();
        try{
            externalContext.redirect(requestURL.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to redirect to: " + url);
        }
        facesContext.responseComplete();
    }
}
