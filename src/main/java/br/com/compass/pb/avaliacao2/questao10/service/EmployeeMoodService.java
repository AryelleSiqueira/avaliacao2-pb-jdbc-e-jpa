package br.com.compass.pb.avaliacao2.questao10.service;

import br.com.compass.pb.avaliacao2.questao10.dao.EmployeeMoodDao;
import br.com.compass.pb.avaliacao2.questao10.model.EmployeeMood;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeMoodService {

    private final EntityManager em;
    private final EmployeeMoodDao dao;

    public EmployeeMoodService(EntityManager em) {
        this.em = em;
        this.dao = new EmployeeMoodDao(em);
    }

    public void save(EmployeeMood employeeMood) {
        em.getTransaction().begin();
        this.dao.save(employeeMood);
        em.getTransaction().commit();
    }

    public List<EmployeeMood> listAll() {
        List<EmployeeMood> list = this.dao.listAll();

        if (list.isEmpty()) {
            throw new NoRegisterFoundException("No registers yet");
        }
        return list;
    }
}
