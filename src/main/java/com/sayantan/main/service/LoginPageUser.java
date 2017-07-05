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
package com.sayantan.main.service;

import com.sayantan.main.model.LoginPage;
import com.sayantan.main.data.LoginPageRepository;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class LoginPageUser {

    @Inject
    private Logger log;

    //Inject
    //private EntityManager em;

    @Inject
    private Event<LoginPage> loginEvent;
    
    @Inject
    private LoginPageRepository loginPageRepository;
    
    private int val;
    //Inject
    //private LoginPageListProducer lplp;
    
    public void validator(LoginPage login) throws Exception {
    	setVal(0);
    	String inUId = login.getUserid();
        String inPass = login.getPassword();
        login = loginPageRepository.findByUserId(login.getUserid());
        String outUId = login.getUserid();
        String outPass = login.getPassword();
        if(inUId.equals(outUId)){
        	if(BCrypt.checkpw(inPass, outPass)){
				setVal(1);
        	}
        }
        //lplp.retrieveLoginCredentials();
        //Shortest way to create a user
        //login.setPassword(BCrypt.hashpw(login.getPassword(), BCrypt.gensalt((int)Math.abs(Math.floor(Math.random() * 10) + 1))));
        //em.persist(login);
        //log.info("Validating  User " + inUId + "/" + inPass +  " with DB user " + outUId + "/" + outPass);
        //log.info("Matched: " + login.getValP());
        loginEvent.fire(login);
    }



	public int getVal() {
		return val;
	}



	public void setVal(int val) {
		this.val = val;
	}
}
