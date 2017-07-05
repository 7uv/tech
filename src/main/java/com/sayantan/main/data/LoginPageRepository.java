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
import com.sayantan.main.model.Member;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class LoginPageRepository {

    @Inject
    private EntityManager em;

    public LoginPage findById(Long id) {
        return em.find(LoginPage.class, id);
    }

    public LoginPage findByUserId(String userid) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LoginPage> criteria = cb.createQuery(LoginPage.class);
        Root<LoginPage> login = criteria.from(LoginPage.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(login).where(cb.equal(login.get("userid"), userid));
        return em.createQuery(criteria).getSingleResult();
    }
    
    public List<LoginPage> findAllOrderedById() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LoginPage> criteria = cb.createQuery(LoginPage.class);
        Root<LoginPage> member = criteria.from(LoginPage.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(member).orderBy(cb.asc(member.get("id")));
        return em.createQuery(criteria).getResultList();
    }
}
