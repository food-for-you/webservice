package ga.rugal.food.core.dao.impl;

import ga.rugal.food.core.dao.TaggingDao;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.entity.Tag;
import ga.rugal.food.core.entity.Tagging;
import java.util.List;
import ml.rugal.sshcommon.hibernate.Finder;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rugal Bernstein
 */
@Repository
public class TaggingDaoImpl extends HibernateBaseDao<Tagging, Long> implements TaggingDao
{

    private static final Logger LOG = LoggerFactory.getLogger(TaggingDaoImpl.class.getName());

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public Tagging getByID(Long id)
    {
        Tagging entity = get(id);
        return entity;
    }

//select * from tagging where tid = (select tid from tag where name = 'lunch' ) and mid in (select mid from menu where rid = (select rid from restaurant where rid = 1));
    @Override
    @Transactional(readOnly = true)
    public List<Tagging> findByMealTypeFromRestaurant(Tag tag, Restaurant restaurant)
    {
        Finder f = Finder.create("FROM Tagging bean WHERE bean.tag=:tag AND bean.menu in (SELECT m FROM Menu m WHERE m.restaurant=:restaurant)");
        f.setParam("tag", tag);
        f.setParam("restaurant", restaurant);
        List<Tagging> list = find(f);
        return list;
    }

    @Override
    public Tagging save(Tagging bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Tagging deleteById(Long id)
    {
        Tagging entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Tagging> getEntityClass()
    {
        return Tagging.class;
    }

}
