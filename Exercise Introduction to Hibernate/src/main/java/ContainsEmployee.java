import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        EntityManager entityManager = MyUtils.createEntityManager();
        entityManager.getTransaction().begin();
        Scanner scan = new Scanner(System.in);
        String[] fullName = scan.nextLine().split(" ");
        String firstName = fullName[0];
        String lastName = fullName[1];
        Query query = entityManager.createQuery("SELECT count(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln",Long.class);
        query.setParameter("fn",firstName);
        query.setParameter("ln",lastName);
        Long result = (Long) query.getSingleResult();
        if(result == 0){
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
