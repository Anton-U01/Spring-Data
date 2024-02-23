import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {
        EntityManager entityManager = MyUtils.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT e FROM Employee e WHERE salary > 50000",Employee.class);
        List<Employee> employeeList = query.getResultList();
        for (Employee employee : employeeList) {
            System.out.println(employee.getFirstName());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
