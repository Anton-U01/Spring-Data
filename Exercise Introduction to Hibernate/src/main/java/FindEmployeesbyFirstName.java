import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesbyFirstName {
    public static void main(String[] args) {
        EntityManager entityManager = MyUtils.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scan = new Scanner(System.in);
        String pattern = scan.nextLine().toLowerCase();

        List<Employee> employees = entityManager.createQuery("FROM Employee WHERE lower(firstName) LIKE :pattern",Employee.class)
                .setParameter("pattern",pattern + "%")
                .getResultList();

        for (Employee employee : employees) {
            System.out.printf("%s %s - %s - ($%.2f)\n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getJobTitle(),
                    employee.getSalary());
        }
    }
}
