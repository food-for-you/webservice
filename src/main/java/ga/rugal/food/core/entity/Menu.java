package ga.rugal.food.core.entity;

import com.google.gson.annotations.Expose;
import config.SystemDefaultProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Entity(name = "menu")
public class Menu implements Serializable
{

    private static final String sequence_name = "menu_mid_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequence_name)
    @SequenceGenerator(name = sequence_name, sequenceName = SystemDefaultProperties.SCHEMA + sequence_name, allocationSize = 1)
    @Basic(optional = false)
    @Column(nullable = false)
    @Expose
    private Integer mid;

    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    @Expose
    private String name;

    @Column(precision = 17, scale = 17)
    @Expose
    private Double price;

    @OneToMany(mappedBy = "mid")
    private List<OrderContent> orderContentList;

    @JoinColumn(name = "rid", referencedColumnName = "rid", nullable = false)
    @ManyToOne(optional = false)
    @Expose
    private Restaurant rid;

    public Menu()
    {
    }

    public Menu(Integer mid)
    {
        this.mid = mid;
    }

    public Menu(Integer mid, String name)
    {
        this.mid = mid;
        this.name = name;
    }

    public Integer getMid()
    {
        return mid;
    }

    public void setMid(Integer mid)
    {
        this.mid = mid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public List<OrderContent> getOrderContentList()
    {
        return orderContentList;
    }

    public void setOrderContentList(List<OrderContent> orderContentList)
    {
        this.orderContentList = orderContentList;
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
        hash += (mid != null ? mid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu))
        {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "rugal.food.core.entity.Menu[ mid=" + mid + " ]";
    }

}
