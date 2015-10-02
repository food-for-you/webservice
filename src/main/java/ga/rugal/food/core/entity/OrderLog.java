package ga.rugal.food.core.entity;

import com.google.gson.annotations.Expose;
import config.SystemDefaultProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Rugal Bernstein
 */
@Entity(name = "order_log")
public class OrderLog implements Serializable
{

    private static final String sequence_name = "order_log_oid_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequence_name)
    @SequenceGenerator(name = sequence_name, sequenceName = SystemDefaultProperties.SCHEMA + sequence_name, allocationSize = 1)
    @Basic(optional = false)
    @Column(nullable = false)
    @Expose
    private Long olid;

    @Basic(optional = false)
    @Column(name = "order_timestamp", nullable = false)
    @Expose
    private Long orderTimestamp;

    @Column(name = "deliver_timestamp")
    @Expose
    private Long deliverTimestamp;

    @Expose
    private Boolean accepted;

    @Expose
    private Boolean cancel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "olid")
    private List<OrderContent> orderContentList;

    @JoinColumn(name = "cid", referencedColumnName = "cid", nullable = false)
    @ManyToOne(optional = false)
    @Expose
    private Client cid;

    @JoinColumn(name = "rid", referencedColumnName = "rid", nullable = false)
    @ManyToOne(optional = false)
    @Expose
    private Restaurant rid;

    public OrderLog()
    {
    }

    public OrderLog(Long olid)
    {
        this.olid = olid;
    }

    public OrderLog(Long olid, long orderTimestamp)
    {
        this.olid = olid;
        this.orderTimestamp = orderTimestamp;
    }

    public Long getOlid()
    {
        return olid;
    }

    public void setOlid(Long olid)
    {
        this.olid = olid;
    }

    public Long getOrderTimestamp()
    {
        return orderTimestamp;
    }

    public void setOrderTimestamp(Long orderTimestamp)
    {
        this.orderTimestamp = orderTimestamp;
    }

    public Long getDeliverTimestamp()
    {
        return deliverTimestamp;
    }

    public void setDeliverTimestamp(Long deliverTimestamp)
    {
        this.deliverTimestamp = deliverTimestamp;
    }

    public Boolean getAccepted()
    {
        return accepted;
    }

    public void setAccepted(Boolean accepted)
    {
        this.accepted = accepted;
    }

    public Boolean getCancel()
    {
        return cancel;
    }

    public void setCancel(Boolean cancel)
    {
        this.cancel = cancel;
    }

    public List<OrderContent> getOrderContentList()
    {
        return orderContentList;
    }

    public void setOrderContentList(List<OrderContent> orderContentList)
    {
        this.orderContentList = orderContentList;
    }

    public Client getCid()
    {
        return cid;
    }

    public void setCid(Client cid)
    {
        this.cid = cid;
    }

    public Restaurant getRid()
    {
        return rid;
    }

    public void setRid(Restaurant rid)
    {
        this.rid = rid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (olid != null ? olid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderLog))
        {
            return false;
        }
        OrderLog other = (OrderLog) object;
        if ((this.olid == null && other.olid != null) || (this.olid != null && !this.olid.equals(other.olid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "rugal.food.core.entity.OrderLog[ olid=" + olid + " ]";
    }

}
