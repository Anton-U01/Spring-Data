import entities.Employee;

import javax.persistence.EntityManager;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManager entityManager = MyUtils.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager
                .createQuery("SELECT e FROM Employee e " +
                "WHERE department.name = 'Research and Development' " +
                        "ORDER BY salary,id", Employee.class)
                .getResultList()
                .forEach(e -> {
                    System.out.printf("%s %s from %s - $%s\n",e.getFirstName(),e.getLastName(),e.getDepartment(),e.getSalary());
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
