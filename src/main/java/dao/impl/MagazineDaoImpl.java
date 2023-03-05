package dao.impl;

import dao.MagazineDao;
import domain.Magazine;
import connection.ConnectionUtils;
import domain.User;
import org.apache.log4j.Logger;
import servlet.LoginServlet;
import shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagazineDaoImpl implements MagazineDao {
    private static String READ_ALL = "select * from magazine";
    private static String CREATE = "insert into magazine(`name`,`information`, `price`) values (?,?,?)";
    private static String READ_BY_ID = "select * from magazine where id =?";
    private static String UPDATE_BY_ID = "update magazine set name=?, information = ?, price = ?";
    private static String DELETE_BY_ID = "delete from magazine where id=?";

    private EntityManager em = FactoryManager.getEntityManager();
    private static final Logger LOGGER = Logger.getLogger(MagazineDaoImpl.class);

    @Override
    public Magazine create(Magazine magazine) {
            try {
                em.getTransaction().begin();
                em.persist(magazine);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

       return magazine;
    }

    @Override
    public Magazine read(Integer id) {
        Magazine magazine = null;
        try {
            magazine = em.find(Magazine.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return magazine;
    }

    @Override
    public Magazine update(Magazine magazine) {
        return magazine;

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Magazine> readAll() {
        List<Magazine> magazineRecords = new ArrayList<>();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Magazine> criteria = builder.createQuery(Magazine.class);
            Root<Magazine> from = criteria.from(Magazine.class);
            criteria.select(from);
            TypedQuery<Magazine> typed = em.createQuery(criteria);
            magazineRecords = typed.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
    }

        return magazineRecords;
    }

    @Override
    public List<Magazine> readAllId(Integer id) {
        return null;
    }
}

