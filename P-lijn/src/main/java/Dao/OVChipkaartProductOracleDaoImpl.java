package Dao;

import Domain.OVChipkaartProduct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartProductOracleDaoImpl extends OracleBaseDao implements OVChipkaartProductDao{

    //find all OVChipkaart products
    public List<OVChipkaartProduct> findAll() {
        List<OVChipkaartProduct> ovProducten = new ArrayList<OVChipkaartProduct>();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT x FROM OVChipkaartProduct x WHERE x.ovProductID IS NOT NULL";
        TypedQuery<OVChipkaartProduct> typedQuery = em.createQuery(query, OVChipkaartProduct.class);
        try {
            ovProducten = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return ovProducten;
    }

    //find OVChipkaart product by OVProductID
    public OVChipkaartProduct findByOVProductID(long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "select o from OVChipkaartProduct o where o.ovProductID = :OVP";

        TypedQuery<OVChipkaartProduct> tq = em.createQuery(query, OVChipkaartProduct.class);
        tq.setParameter("OVP", id);
        OVChipkaartProduct ovProduct = null;
        try{
            ovProduct = tq.getSingleResult();
            return ovProduct;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return null;
    }

    //save OVChipkaart product
    public OVChipkaartProduct save(OVChipkaartProduct ovChipkaartProduct) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(ovChipkaartProduct);
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
        return ovChipkaartProduct;
    }

    //update OVChipkaart product
    public OVChipkaartProduct update(OVChipkaartProduct ovChipkaartProduct) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.merge(ovChipkaartProduct);
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
        return ovChipkaartProduct;
    }

    //delete OVChipkaart product
    public boolean delete(OVChipkaartProduct ovChipkaartProduct) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        OVChipkaartProduct ovProduct1 = null;
        try {
            et = em.getTransaction();
            et.begin();
            ovProduct1 = em.merge(ovChipkaartProduct);
            em.remove(ovProduct1);
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

    @Override
    public void closeConnection() {

    }
}
