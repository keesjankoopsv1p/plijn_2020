package Dao;

import Domain.Adres;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class AdresOracleDaoImpl extends OracleBaseDao implements AdresDao{

    //find all adressen
    public List<Adres> findAll() {
        List<Adres> adressen = new ArrayList<Adres>();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT a FROM Adres a WHERE a.adresID IS NOT NULL";
        TypedQuery<Adres> typedQuery = em.createQuery(query, Adres.class);
        try {
            adressen = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return adressen;
    }

    //find adres by id
    public Adres findById(long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT a FROM Adres a WHERE a.adresID = :id";

        TypedQuery<Adres> tq = em.createQuery(query, Adres.class);
        tq.setParameter("id", id);
        Adres adres = null;
        try{
            adres = tq.getSingleResult();
            return adres;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return null;
    }

    //save adres
    public Adres save(Adres adres) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(adres);
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
        return adres;
    }

    //update adres
    public Adres update(Adres adres) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.merge(adres);
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
        return adres;
    }

    //delete adres
    public boolean delete(Adres adres) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Adres adres1 = null;
        try {
            et = em.getTransaction();
            et.begin();
            adres1 = em.merge(adres);
            em.remove(adres1);
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
