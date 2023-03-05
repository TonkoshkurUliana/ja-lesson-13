import domain.User;
import service.MagazineService;
import service.UserService;
import service.impl.MagazineServiceImpl;
import service.impl.UserServiceImpl;
import shared.FactoryManager;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.text.ParseException;

public class MainTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
//        UserService userService = new UserServiceImpl();
//        userService.create(new User("test@test", "test", "test", "test","admin"));

       // MagazineService magazineService = new MagazineServiceImpl();
//        magazineService.create(new Magazine(  "test", "test",120));
//
//        BucketService bucketService = new BucketServiceImpl();
//        long millis=System.currentTimeMillis();
//        java.sql.Date date = new java.sql.Date(millis);
//        bucketService.create(new Bucket(1,1, date));

//        userService.delete(1);
       // magazineService.delete(2);
//        bucketService.delete(1);
//        EntityManager em = FactoryManager.getEntityManager();
//
//        System.out.println(em.isOpen());
//
//        em.getTransaction().begin();
        User user = new  User("test@test", "test", "test", "test","admin");
//        em.persist(user);
//        em.getTransaction().commit();

        UserService userService = UserServiceImpl.getUserServiceImpl();
        userService.create(user);

    }
}