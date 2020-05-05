package Dao;

import Domain.OVChipkaart;

import java.util.List;

public interface OVChipkaartDao {

    List<OVChipkaart> findAll();
    OVChipkaart findByKaartNummer(long nummer);
    OVChipkaart save(OVChipkaart ovChipkaart);
    OVChipkaart update(OVChipkaart ovChipkaart);
    boolean delete(OVChipkaart ovChipkaart);
    void closeConnection();

}
