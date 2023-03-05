package dao.impl;

import connection.ConnectionUtils;
import dao.BucketDao;
import domain.Bucket;
import domain.User;
import org.apache.log4j.Logger;
import shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;

public class BucketDaoImpl implements BucketDao {
    private  static String READ_BY_ALL 			= "SELECT * FROM bucket";
    private  static String READ_BY_ALLID 		= "SELECT * FROM bucket WHERE userId=?";
    private static String CREATE = "insert into bucket(`userId`,`magazineId`, `purchaseDate`) values (?,?,?)";
    private static String READ_BY_ID = "select * from bucket where id =?";
    private static String UPDATE_BY_ID = "update bucket set userId=?, magazineId = ?, purchaseDate = ?";
    private static String DELETE_BY_ID = "delete from bucket where id=?";


    private EntityManager em = FactoryManager.getEntityManager();

    private static final Logger LOGGER = Logger.getLogger(BucketDaoImpl.class);


    @Override
    public Bucket create(Bucket bucket) {
            try {
                em.getTransaction().begin();
                em.persist(bucket);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

        return bucket;
    }

    @Override
    public Bucket read(Integer id) {
        Bucket bucket = null;
        try {
            bucket = em.find(Bucket.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {

        return bucket;

    }

    @Override
    public void delete(Integer id) {
        try {
            Bucket bucket = read(id);
            em.getTransaction().begin();
            em.remove(bucket);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bucket> readAll() {
      return null;
    }

    @Override
    public List<Bucket> readAllId(Integer id) {
        List<Bucket> bucketRecords = new ArrayList<>();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Bucket> criteria = builder.createQuery(Bucket.class);
            Root<Bucket> from = criteria.from(Bucket.class);
            criteria.select(from);
            criteria.where(builder.equal(from.get("userId"), id));
            TypedQuery<Bucket> typed = em.createQuery(criteria);
            bucketRecords = typed.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bucketRecords;
    }

}
