package ga.rugal.food.core.entity;

import com.google.gson.annotations.Expose;
import config.SystemDefaultProperties;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Rugal Bernstein
 */
@Entity(name = "order_content")
public class OrderContent implements Serializable
{

    private static final String sequence_name = "order_content_ocid_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequence_name)
    @SequenceGenerator(name = sequence_name, sequenceName = SystemDefaultProperties.SCHEMA + sequence_name, allocationSize = 1)
    @Basic(optional = false)
    @Column(nullable = false)
    @Expose
    private Long ocid;

    @Column(length = 100)
    @Expose
    private String message;

    @Expose
    private Integer amount;

    @JoinColumn(name = "mid", referencedColumnName = "mid")
    @ManyToOne
    @Expose
    private Menu mid;

    @JoinColumn(name = "olid", referencedColumnName = "olid", nullable = false)
    @ManyToOne(optional = false)
    @Expose
    private OrderLog olid;

    public OrderContent()
    {
    }

    public OrderContent(Long ocid)
    {
        this.ocid = ocid;
    }

    public Long getOcid()
    {
        return ocid;
    }

    public void setOcid(Long ocid)
    {
        this.ocid = ocid;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    public Menu getMid()
    {
        return mid;
    }

    public void setMid(Menu mid)
    {
        this.mid = mid;
    }

    public OrderLog getOlid()
    {
        return olid;
    }

    public void setOlid(OrderLog olid)
    {
        this.olid = olid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (ocid != null ? ocid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderContent))
        {
            return false;
        }
        OrderContent other = (OrderContent) object;
        if ((this.ocid == null && other.ocid != null) || (this.ocid != null && !this.ocid.equals(other.ocid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "rugal.food.core.entity.OrderContent[ ocid=" + ocid + " ]";
    }

}
