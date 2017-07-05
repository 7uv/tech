package com.sayantan.main.util;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class EntityManagerFactory {

    //PersistenceContext(unitName = "red")
    //EntityManager em_alt;

    public EntityManager getEntityManager(Environment env) {
        switch (env) {
         //case EXT:
          //                    return em_alt;
        default:
            return null;
        }
    }
}
