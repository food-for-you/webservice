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
@Table(schema = "food", name = "tag")
public class Tag
{

    private static final String sequence_name = "tag_tid_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = sequence_name)
    @SequenceGenerator(name = sequence_name, sequenceName = SystemDefaultProperties.SCHEMA + sequence_name, allocationSize = 1)
    @Basic(optional = false)
    @Column(nullable = false)
    @Expose
    private Integer tid;

    @Size(max = 20)
    @Column(length = 20)
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<Tagging> taggingList;

    public Tag()
    {
    }

    public Tag(Integer tid)
    {
        this.tid = tid;
    }

    public Integer getTid()
    {
        return tid;
    }

    public void setTid(Integer tid)
    {
        this.tid = tid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Tagging> getTaggingList()
    {
        return taggingList;
    }

    public void setTaggingList(List<Tagging> taggingList)
    {
        this.taggingList = taggingList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (tid != null ? tid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag))
        {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ga.rugal.food.core.entity.Tag[ tid=" + tid + " ]";
    }

}
