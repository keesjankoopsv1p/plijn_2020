package Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "OV_CHIPKAART_PRODUCT")
public class OVChipkaartProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ovp_generator", sequenceName = "OVP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ovp_generator")
    @Column(name = "OVPRODUCTID")
    private long ovProductID;

    @Column(name = "REISPRODUCTSTATUS", nullable = false)
    private String status;

    @Column(name = "LASTUPDATE", nullable = false)
    private Date lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "KAARTNUMMER")
    private OVChipkaart kaart;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "PRODUCTNUMMER")
    private Product product;

    public OVChipkaartProduct() {

    }

    public OVChipkaartProduct(String stat, Date lastU) {
        status = stat;
        lastUpdate = lastU;
    }

    public long getOvProductID() {
        return ovProductID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public OVChipkaart getKaart() {
        return kaart;
    }

    public void setKaart(OVChipkaart kaart) {
        this.kaart = kaart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String toString() {
        return String.format("status: %s, last Update: %s", this.status, this.lastUpdate);
    }
}
