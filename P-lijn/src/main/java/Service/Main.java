package Service;

import Dao.*;
import Domain.Adres;
import Domain.OVChipkaart;
import Domain.OVChipkaartProduct;
import Domain.Reiziger;

import javax.persistence.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {

        //dao objecten aanmaken
        ReizigerDao reizigerDao = new ReizigerOracleDaoImpl();
        OVChipkaartDao ovChipkaartDao = new OVChipkaartOracleDaoImpl();
        OVChipkaartProductDao ovProductDao = new OVChipkaartProductOracleDaoImpl();
        ProductDao productDao = new ProductOracleDaoImpl();
        AdresDao adresDao = new AdresOracleDaoImpl();


        //stap 1 laat alle reizigers met hun adressen en chipkaartenzien
        List<Reiziger> reizigers = new ArrayList<Reiziger>();
        reizigers = reizigerDao.findAll();
        for (Reiziger reiziger : reizigers) {
            System.out.println(reiziger);
            System.out.println("Met de volgende adressen:");
            for (Adres adr : reiziger.getAdressen()) {
                System.out.println(adr);
            }
            System.out.println("\nEn met de volgende OV-Chipkaarten:");
            for (OVChipkaart kaart : reiziger.getKaarten()) {
                System.out.println(kaart);
            }
            System.out.println("-------------------------------------------------------");
        }

//        stap 2 maak een nieuwe reiziger aan en sla deze op
        Reiziger reiziger1 = new Reiziger("KJ", null, "Koops", Date.valueOf("1998-08-17"));
        reizigerDao.save(reiziger1);
//
//        //stap 3 update de achternaam van de nieuwe reiziger
//        reiziger1.setAchternaam("Bergen");
//        reizigerDao.update(reiziger1);
//
//        //stap 4 voeg een adress toe aan de reiziger
//        Adres adres1 = new Adres("3754NR","22", "Kerkepad", "Eemdijk");
//        adres1.setReiziger(reiziger1);
//        adresDao.save(adres1);

        //stap 5 maak een nieuwe reiziger met een OV-Chipkaart
//        Reiziger reiziger2 = new Reiziger("shahin", "Basht", "Bavian", Date.valueOf("1997-10-10"));
//        reizigerDao.save(reiziger2);
//        OVChipkaart kaart = new OVChipkaart(Date.valueOf("2021-01-01"), 2, 20.00);
//        kaart.setReiziger(reiziger2);
//        ovChipkaartDao.save(kaart);

        //stap 6 verwijder de kaart van de reiziger
//        ovChipkaartDao.delete(ovChipkaartDao.findByKaartNummer(46));

//        reizigerDao.delete(reizigerDao.findById(61));

    }
}