import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingNewAddressAndUpdatingTheEmployee {
    public static void main(String[] args) {
        EntityManager entityManager = MyUtils.createEntityManager();
        entityManager.getTransaction().begin();
        Address address = new Address();
        address.setText("Vitoshka 15");
        entityManager.persist(address);

        Scanner scan = new Scanner(System.in);
        String employeeLastName = scan.nextLine();
       int result = entityManager.createQuery("UPDATE Employee e SET  e.address = :address WHERE e.lastName = :ln")
                .setParameter("address",address)
                .setParameter("ln",employeeLastName)
                .executeUpdate();

       entityManager.getTransaction().commit();
       entityManager.close();
    }

}
