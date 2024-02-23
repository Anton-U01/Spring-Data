import com.mysql.cj.xdevapi.AddResult;
import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        EntityManager entityManager = MyUtils.createEntityManager();
        entityManager.getTransaction().begin();

        String town = scan.nextLine();

        Town townForDelete = entityManager.createQuery("FROM Town where name = :name1", Town.class)
                .setParameter("name1",town)
                .getSingleResult();

        List<Address> addressList = entityManager.createQuery("FROM Address WHERE town.id = :id", Address.class)
                .setParameter("id",townForDelete.getId())
                .getResultList();

        addressList.forEach(a -> a.getEmployees().forEach(e -> {
            e.setAddress(null);
        }));

        addressList.forEach(entityManager::remove);
        entityManager.remove(townForDelete);

        int countDeletedAddresses = addressList.size();

        System.out.printf("%d address%s in %s deleted",
                countDeletedAddresses,
                countDeletedAddresses == 1 ? "" : "es",
                townForDelete.getName());
    }
}
