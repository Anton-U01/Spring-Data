import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class GetEmployeesWithProject {
    public static void main(String[] args) {
        EntityManager entityManager = MyUtils.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scan = new Scanner(System.in);
        int id = Integer.parseInt(scan.nextLine());
        System.out.println(entityManager.createQuery("SELECT e FROM Employee e WHERE id = :p", Employee.class)
                .setParameter("p",id)
                .getSingleResult()
                .toString());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
