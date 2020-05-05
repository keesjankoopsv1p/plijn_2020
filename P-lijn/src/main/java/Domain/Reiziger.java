package Domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "REIZIGER")
public class Reiziger implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "reiziger_generator", sequenceName = "REIZIGER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reiziger_generator")
    @Column(name = "REIZIGERID")
    private long id;

    @Column(name = "VOORLETTERS", nullable = false)
    private String voorletters;

    @Column(name = "TUSSENVOEGSEL")
    private String tussenvoegsel;

    @Column(name = "ACHTERNAAM", nullable = false)
    private String achternaam;

    @Column(name = "GEBORTEDATUM")
    private Date geboortedatum;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reiziger")
    private List<OVChipkaart> kaarten;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reiziger", cascade = CascadeType.REMOVE)
    private List<Adres>adressen;


    public Reiziger() {

    }

    public Reiziger(String voorl, String tussen, String achternm, Date gbDatum) {
        kaarten = new ArrayList<OVChipkaart>();
        adressen = new ArrayList<Adres>();
        voorletters = voorl;
        tussenvoegsel = tussen;
        achternaam = achternm;
        geboortedatum = gbDatum;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public List<OVChipkaart> getKaarten() {
        return kaarten;
    }

    public void setKaarten(List<OVChipkaart> kaarten) {
        this.kaarten = kaarten;
    }

    public void addKaart(OVChipkaart kaart){
        kaarten.add(kaart);
    }

    public List<Adres> getAdressen() {
        return adressen;
    }

    public void setAdressen(List<Adres> adressen) {
        this.adressen = adressen;
    }

    public void addAdres(Adres adr) {
        this.adressen.add(adr);
    }

    public String toString(){
        return String.format("Reiziger met ID: %s: %s %s%s, geboren op %s", this.id, this.voorletters, (this.tussenvoegsel != null) ? this.tussenvoegsel + " " : "", this.achternaam, this.geboortedatum);
    }
}
