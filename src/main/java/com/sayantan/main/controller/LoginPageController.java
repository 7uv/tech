/*
 *
 * Copyright 2016, Sayantan Ghosh, and/or his affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sayantan.main.controller;

import com.sayantan.main.model.LoginPage;
import com.sayantan.main.service.LoginPageUser;
import com.sayantan.main.data.LoginPageListProducer;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://www.cdi-spec.org/faq/#accordion6
@Model
public class LoginPageController {

    @Inject
    Logger log;
    
	@Inject
    private FacesContext facesContext;

    @Inject
    private LoginPageUser loginPageUser;
    
    @Produces
    @Named
    private LoginPage newLogin;

    @PostConstruct
    public void initNewLogin() {
        newLogin = new LoginPage();
    }

    public String validator() throws Exception {
        try {
            loginPageUser.validator(newLogin);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Successful");
            facesContext.addMessage(null, m);
            initNewLogin();
            //Session s;
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Login Failed!!!");
            facesContext.addMessage(null, m);
        }
        log.info("Match Value: " + loginPageUser.getVal());
        if(loginPageUser.getVal() == 1){ 
        	return "home";
        } else {
        	FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Check username/password!", "Auth Failed!!!");
        	facesContext.addMessage(null, m);
        	return "index";
        }
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Login failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }

}
