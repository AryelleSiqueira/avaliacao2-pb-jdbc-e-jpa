package br.com.compass.pb.avaliacao2.questao10.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class JPAUtil {

    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("shop");


    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
