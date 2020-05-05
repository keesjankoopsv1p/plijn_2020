package Dao;

import Domain.Adres;

import java.util.List;

public interface AdresDao {

    List<Adres> findAll();
    Adres findById(long id);
    Adres save(Adres adres);
    Adres update(Adres adres);
    boolean delete(Adres adres);
    void closeConnection();

}
