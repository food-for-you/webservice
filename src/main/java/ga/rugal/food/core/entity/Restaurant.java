package ga.rugal.food.core.entity;

import com.google.gson.annotations.Expose;
import config.SystemDefaultProperties;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Rugal Bernstein
 */
@Entity
@Table(schema = "food", name = "restaurant")

public class Restaurant
{

    private static final String sequence_name = "restaurant_rid_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequence_name)
    @SequenceGenerator(name = sequence_name, sequenceName = SystemDefaultProperties.SCHEMA + sequence_name, allocationSize = 1)
    @Basic(optional = false)
    @Column(nullable = false)
    @Expose
    private Integer rid;

    @Size(max = 50)
    @Column(length = 50)
    private String name;

    @Size(max = 50)
    @Column(length = 50)
    private String address;

    @Size(max = 20)
    @Column(length = 20)
    private String phone;

    @Size(max = 10)
    @Column(length = 10)
    private String postalcode;

    @OneToMany(mappedBy = "restaurant")
    private List<Tagging> taggingList;

    @OneToMany(mappedBy = "rid")
    private List<Menu> menuList;

    public Restaurant()
    {
    }

    public Restaurant(Integer rid)
    {
        this.rid = rid;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPostalcode()
    {
        return postalcode;
    }

    public void setPostalcode(String postalcode)
    {
        this.postalcode = postalcode;
    }

    public List<Tagging> getTaggingList()
    {
        return taggingList;
    }

    public void setTaggingList(List<Tagging> taggingList)
    {
        this.taggingList = taggingList;
    }

    public List<Menu> getMenuList()
    {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList)
    {
        this.menuList = menuList;
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
        return !((this.rid == null && other.rid != null) || (this.rid != null && !this.rid.equals(other.rid)));
    }

    @Override
    public String toString()
    {
        return "ga.rugal.food.core.entity.Restaurant[ rid=" + rid + " ]";
    }

}
