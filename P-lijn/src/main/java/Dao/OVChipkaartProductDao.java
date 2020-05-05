package Dao;

import Domain.OVChipkaartProduct;

import java.util.List;

public interface OVChipkaartProductDao {
    List<OVChipkaartProduct> findAll();
    OVChipkaartProduct findByOVProductID(long id);
    OVChipkaartProduct save(OVChipkaartProduct ovChipkaartProduct);
    OVChipkaartProduct update(OVChipkaartProduct ovChipkaartProduct);
    boolean delete(OVChipkaartProduct ovChipkaartProduct);
    void closeConnection();
}
