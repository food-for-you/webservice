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
@Entity(name = "client")
public class Client implements Serializable
{

    private static final String sequence_name = "client_cid_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequence_name)
    @SequenceGenerator(name = sequence_name, sequenceName = SystemDefaultProperties.SCHEMA + sequence_name, allocationSize = 1)
    @Basic(optional = false)
    @Column(nullable = false)
    @Expose
    private Integer cid;

    @Column(length = 50)
    @Expose
    private String name;

    @Column(length = 15)
    @Expose
    private String phone;

    @Column(name = "wechat_id", length = 20)
    @Expose
    private String wechatId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cid")
    private List<OrderLog> orderLogList;

    public Client()
    {
    }

    public Client(Integer cid)
    {
        this.cid = cid;
    }

    public Integer getCid()
    {
        return cid;
    }

    public void setCid(Integer cid)
    {
        this.cid = cid;
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

    public String getWechatId()
    {
        return wechatId;
    }

    public void setWechatId(String wechatId)
    {
        this.wechatId = wechatId;
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
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client))
        {
            return false;
        }
        Client other = (Client) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "rugal.food.core.entity.Client[ cid=" + cid + " ]";
    }

}
