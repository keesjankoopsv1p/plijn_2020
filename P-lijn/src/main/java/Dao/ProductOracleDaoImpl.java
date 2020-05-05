package Dao;

import Domain.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProductOracleDaoImpl extends OracleBaseDao implements ProductDao {

    //find all producten
    public List<Product> findAll() {
        List<Product> producten = new ArrayList<Product>();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT p FROM Product p WHERE p.productNummer IS NOT NULL";
        TypedQuery<Product> typedQuery = em.createQuery(query, Product.class);
        try {
            producten = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return producten;
    }

    //find product by id
    public Product findById(long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT p FROM Product p WHERE p.productNummer = :id";

        TypedQuery<Product> tq = em.createQuery(query, Product.class);
        tq.setParameter("id", id);
        Product product = null;
        try{
            product = tq.getSingleResult();
            return product;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
//        finally {
//            em.close();
//        }
        return null;
    }

    //save product
    public Product save(Product product) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(product);
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
        return product;
    }

    //update product
    public Product update(Product product) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.merge(product);
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
        return product;
    }

    //delete product
    public boolean delete(Product product) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Product product1 = null;
        try {
            et = em.getTransaction();
            et.begin();
            product1 = em.merge(product);
            em.remove(product1);
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
