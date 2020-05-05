package Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "product_generator", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @Column(name = "PRODUCTNUMMER")
    private long productNummer;

    @Column(name = "PRODUCTNAAM", nullable = false)
    private String naam;

    @Column(name = "BESCHRIJVING", nullable = false)
    private String beschrijving;

    @Column(name = "PRIJS", nullable = false)
    private double prijs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<OVChipkaartProduct> ovProducten;

    public Product() {

    }

    public Product(String nm, String bes, double pr) {
        ovProducten = new ArrayList<OVChipkaartProduct>();
        naam = nm;
        beschrijving = bes;
        prijs = pr;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public long getProductNummer() {
        return productNummer;
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
        return String.format("naam: %s, beschrijving: %s, prijs: %s", this.naam, this.beschrijving, this.prijs);
    }
}
