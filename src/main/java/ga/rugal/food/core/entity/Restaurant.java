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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Rugal Bernstein
 */
@Entity(name = "restaurant")
public class Restaurant implements Serializable
{

    private static final String sequence_name = "restaurant_rid_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequence_name)
    @SequenceGenerator(name = sequence_name, sequenceName = SystemDefaultProperties.SCHEMA + sequence_name, allocationSize = 1)
    @Basic(optional = false)
    @Column(nullable = false)
    @Expose
    private Integer rid;

    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    @Expose
    private String name;

    @Column(length = 30)
    @Expose
    private String phone;

    @Column(length = 50)
    @Expose
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rid")
    private List<Menu> menuList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rid")
    private List<OrderLog> orderLogList;

    public Restaurant()
    {
    }

    public Restaurant(Integer rid)
    {
        this.rid = rid;
    }

    public Restaurant(Integer rid, String name)
    {
        this.rid = rid;
        this.name = name;
    }

    public Integer getRid()
    {
        return rid;
    }

    public void setRid(Integer rid)
    {
        this.rid = rid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public List<Menu> getMenuList()
    {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList)
    {
        this.menuList = menuList;
    }

    public List<OrderLog> getOrderLogList()
    {
        return orderLogList;
    }

    public void setOrderLogList(List<OrderLog> orderLogList)
    {
        this.orderLogList = orderLogList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (rid != null ? rid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurant))
        {
            return false;
        }
        Restaurant other = (Restaurant) object;
        if ((this.rid == null && other.rid != null) || (this.rid != null && !this.rid.equals(other.rid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "rugal.food.core.entity.Restaurant[ rid=" + rid + " ]";
    }

}
