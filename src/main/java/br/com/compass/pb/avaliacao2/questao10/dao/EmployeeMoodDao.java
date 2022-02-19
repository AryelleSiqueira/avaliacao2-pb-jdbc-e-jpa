package br.com.compass.pb.avaliacao2.questao10.dao;

import br.com.compass.pb.avaliacao2.questao10.model.EmployeeMood;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeMoodDao {

    private final EntityManager entityManager;

    public EmployeeMoodDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void save(EmployeeMood employeeMood) {
        this.entityManager.persist(employeeMood);
    }


    public List<EmployeeMood> listAll() {
        String jpql = "SELECT M FROM EmployeeMood M";
        return this.entityManager.createQuery(jpql, EmployeeMood.class).getResultList();
    }
}
