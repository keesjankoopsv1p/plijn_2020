package Dao;

import Domain.OVChipkaart;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartOracleDaoImpl extends OracleBaseDao implements OVChipkaartDao{

    //find all OVChipkaarten
    public List<OVChipkaart> findAll() {
        List<OVChipkaart> kaarten = new ArrayList<OVChipkaart>();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT o FROM OVChipkaart o WHERE o.kaartNummer IS NOT NULL";
        TypedQuery<OVChipkaart> typedQuery = em.createQuery(query, OVChipkaart.class);
        try {
            kaarten = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return kaarten;
    }

    //find OVChipkaart by kaartnummer
    public OVChipkaart findByKaartNummer(long nummer) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT o FROM OVChipkaart o WHERE o.kaartNummer = :nmr";

        TypedQuery<OVChipkaart> tq = em.createQuery(query, OVChipkaart.class);
        tq.setParameter("nmr", nummer);
        OVChipkaart kaart = null;
        try{
            kaart = tq.getSingleResult();
            return kaart;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return null;
    }

    //save OVChipkaart
    public OVChipkaart save(OVChipkaart ovChipkaart) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(ovChipkaart);
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
        return ovChipkaart;
    }

    //update OVChipkaart
    public OVChipkaart update(OVChipkaart ovChipkaart) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.merge(ovChipkaart);
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
        return ovChipkaart;
    }

    //delete OVChipkaart
    public boolean delete(OVChipkaart ovChipkaart) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        OVChipkaart kaart1 = null;
        try {
            et = em.getTransaction();
            et.begin();
            kaart1 = em.merge(ovChipkaart);
            em.remove(kaart1);
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
