import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManager entityManager = MyUtils.createEntityManager();
        entityManager.getTransaction().begin();

        List<Employee> employees = entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList();

        for (Employee employee : employees) {
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
        }
        entityManager.flush();
        entityManager.getTransaction().commit();

        for (Employee employee : employees) {
            System.out.printf("%s %s ($%.2f)\n",employee.getFirstName(),employee.getLastName(),employee.getSalary());
        }

        entityManager.close();
    }
}
