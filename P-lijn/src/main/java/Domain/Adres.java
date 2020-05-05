package Domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "ADRES")
public class Adres implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "adres_generator", sequenceName = "ADRES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adres_generator")
    @Column(name = "ADRESID")
    private long adresID;

    @Column(name = "POSTCODE", nullable = false)
    private String postcode;

    @Column(name = "HUISNUMMER", nullable = false)
    private String huisnummer;

    @Column(name = "STRAAT", nullable = false)
    private String straat;

    @Column(name = "WOONPLAATS", nullable = false)
    private String woonplaats;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "REIZIGERID")
    private Reiziger reiziger;

    public Adres() {

    }

    public Adres (String postc, String huisn, String strt, String woon) {
        postcode = postc;
        huisnummer = huisn;
        straat = strt;
        woonplaats = woon;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public long getAdresID() {
        return adresID;
    }

//    public void setAdresID(long adresID) {
//        this.adresID = adresID;
//    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public String toString() {
        return String.format("postcode: %s, huisnummer: %s, straat: %s, woonplaats: %s", this.postcode, this.huisnummer, this.straat, this.woonplaats);
    }
}
