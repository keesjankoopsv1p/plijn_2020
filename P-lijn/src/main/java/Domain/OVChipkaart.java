package Domain;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OV_CHIPKAART")
public class OVChipkaart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "OV_generator", sequenceName = "OV_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OV_generator")
    @Column(name = "KAARTNUMMER")
    private long kaartNummer;

    @Column(name = "GELDIGTOT", nullable = false)
    private Date geldigTot;

    @Column(name = "KLASSE")
    private int klasse;

    @Column(name = "SALDO")
    private double saldo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "reizigerid")
    private Reiziger reiziger;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kaart", cascade = CascadeType.REMOVE)
    private List<OVChipkaartProduct> ovProducten;

    public OVChipkaart(){

    }

    public OVChipkaart(Date gt, int klas, double sld) {
        ovProducten = new ArrayList<OVChipkaartProduct>();
        geldigTot = gt;
        klasse = klas;
        saldo = sld;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return this.reiziger;
    }

    public void setReiziger(Reiziger rei) {
        this.reiziger = rei;
    }

    public long getKaartNummer() {
        return kaartNummer;
    }

    public List<OVChipkaartProduct> getOvProducten() {
        return ovProducten;
    }

    public void setOvProducten(List<OVChipkaartProduct> ovProducten) {
        this.ovProducten = ovProducten;
    }

    public void addOvProduct(OVChipkaartProduct ovProduct) {
        this.ovProducten.add(ovProduct);
    }

    public String toString() {
        return String.format("Kaartnummer: %s, geldig tot: %s, klasse: %s, saldo: %s",  this.kaartNummer, this.geldigTot, this.klasse, this.saldo);
    }
}
