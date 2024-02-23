import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManager entityManager = MyUtils.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager
                .createQuery("select department.name,max(salary) from Employee " +
                        "group by department.name " +
                        "having max(salary) not between 30000 and 70000", Object[].class)
                .getResultList()
                .forEach(info -> System.out.println(info[0] + " " + info[1]));

    }
}
