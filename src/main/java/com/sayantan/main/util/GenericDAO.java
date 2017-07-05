package com.sayantan.main.util;

import javax.ejb.EJB;

public class GenericDAO<T> {

    @EJB
    private EntityManagerFactory entityManagerFactory;

    public void save(T entity, Environment env) {
        entityManagerFactory.getEntityManager(env).persist(entity);
    }

}