package br.com.compass.pb.avaliacao2.questao10.application;

import br.com.compass.pb.avaliacao2.questao10.service.EmployeeMoodService;
import br.com.compass.pb.avaliacao2.questao10.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class Program {

    public static void main(String[] args) {
        try {
            EntityManager em = JPAUtil.getEntityManager();
            EmployeeMoodService service = new EmployeeMoodService(em);

            Menu.menu(service);

            em.close();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
