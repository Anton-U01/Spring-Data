import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ChangeCasing {
    public static void main(String[] args) {
        EntityManager entityManager = MyUtils.createEntityManager();
        entityManager.getTransaction().begin();

        List<Town> resultList = entityManager.createQuery("SELECT t FROM Town t",Town.class).getResultList();
        for (Town town : resultList) {
            String townName = town.getName();
            if(townName.length() <= 5){
                town.setName(townName.toUpperCase());
                entityManager.persist(town);
            }

        }
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
