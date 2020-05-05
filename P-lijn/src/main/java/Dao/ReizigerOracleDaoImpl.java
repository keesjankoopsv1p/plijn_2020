package Dao;

import Domain.Adres;
import Domain.Reiziger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl extends OracleBaseDao implements ReizigerDao{

    //find all reizigers
    public List<Reiziger> findAll() {
        List<Reiziger> reizigers = new ArrayList<Reiziger>();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT r FROM Reiziger r WHERE r.id IS NOT NULL";
        TypedQuery<Reiziger> typedQuery = em.createQuery(query, Reiziger.class);
        try {
            reizigers = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return reizigers;
    }

    //find reiziger by ID
    public Reiziger findById(long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "select r from Reiziger r where r.id = :reiID";

        TypedQuery<Reiziger> tq = em.createQuery(query, Reiziger.class);
        tq.setParameter("reiID", id);
        Reiziger r1 = null;
        try{
            r1 = tq.getSingleResult();
            return r1;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return null;
    }

    //find reiziger by date of birth
    public List<Reiziger> findByGBdatum(Date GBdatum) {
        List<Reiziger> reizigers = new ArrayList<Reiziger>();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "select r from Reiziger r where r.geboortedatum = :gb";

        TypedQuery<Reiziger> tq = em.createQuery(query, Reiziger.class);
        tq.setParameter("gb", GBdatum);
        try{
            reizigers = tq.getResultList();
            return reizigers;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return null;
    }

    //save reiziger
    public Reiziger save(Reiziger reiziger) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(reiziger);
            et.commit();
        }
        catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return reiziger;
    }

    //update reiziger
    public Reiziger update(Reiziger reiziger) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.merge(reiziger);
            et.commit();
        }
        catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return reiziger;
    }

    //delete reiziger
    public boolean delete(Reiziger reiziger) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Reiziger reiziger1 = null;
        try {
            et = em.getTransaction();
            et.begin();
            reiziger1 = em.merge(reiziger);
            em.remove(reiziger1);
            et.commit();
        }
        catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
            em.close();
            return false;
        }
//        finally {
//            em.close();
//        }
        return true;
    }

    public void closeConnection() {

    }
}
