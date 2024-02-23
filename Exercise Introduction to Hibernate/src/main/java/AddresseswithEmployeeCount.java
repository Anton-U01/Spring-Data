import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.util.List;

public class AddresseswithEmployeeCount {
    public static void main(String[] args) {
        EntityManager entityManager = MyUtils.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("select a FROM Address a order by a.employees.size desc",Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
