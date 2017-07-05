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
package com.sayantan.main.data;

import com.sayantan.main.model.LoginPage;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@RequestScoped
public class LoginPageListProducer {

    @Inject
    private Logger log;

    @Inject
    private LoginPageRepository loginPageRepository;

    private LoginPage login;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @SuppressWarnings("cdi-ambiguous-name")
    @Produces
    @Named
    public LoginPage getLoginPage() {
        return login;
    }

    public void onLoginPageChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final LoginPage login) {
        retrieveLoginCredentials();
    }

    @PostConstruct
    public void retrieveLoginCredentials() {
    	log.info("retrieving details for " + login.getUserid() + login.getPassword());
        login = loginPageRepository.findByUserId(login.getUserid());
    }
}
