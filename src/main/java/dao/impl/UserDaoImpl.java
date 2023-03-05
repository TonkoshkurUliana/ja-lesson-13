package dao.impl;

import dao.UserDao;
import domain.User;
import connection.ConnectionUtils;
import domain.UserRole;
import org.apache.log4j.Logger;
import shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final static String READ_ALL = "select * from users";
    private final static String CREATE = "insert into users(`email`,`firstName`, `lastName`,`password`, `role`) values (?,?,?,?,?)";
    private final static String READ_BY_ID = "select * from users where id =?";
    private final static String READ_BY_EMAIL = "select * from users where email=?";
    private final static String UPDATE_BY_ID = "update users set email=?, firstName = ?, lastName = ?, role=?  where id = ?";
    private final static String DELETE_BY_ID = "delete from users where id=?";

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private String email;
    private EntityManager em = FactoryManager.getEntityManager();


    @Override
    public User create(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User read(Integer id) {
        User user = null;
        try {
            user = em.find(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        return user;
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public List<User> readAllId(Integer id) {
        return null;
    }

    public User getByUserEmail(String email) {
        User user = null;
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> from = criteria.from(User.class);
            criteria.select(from);
            criteria.where(builder.equal(from.get("email"), email));
            TypedQuery<User> typed = em.createQuery(criteria);
            user = typed.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getByUserPassword(String userEmail) throws SQLException {
        return null;
    }
}
