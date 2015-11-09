package ga.rugal.food.core.dao.impl;

import ga.rugal.food.core.dao.TaggingDao;
import ga.rugal.food.core.entity.Client;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.entity.Tag;
import ga.rugal.food.core.entity.Tagging;
import java.util.List;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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

    @Override
    @Transactional(readOnly = true)
    public List<Tagging> findByTagAndRestaurant(Tag tag, Restaurant restaurant)
    {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("tag", tag));
        crit.add(Restrictions.eq("restaurant", restaurant));
        return crit.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tagging> findByTagAndMenu(Tag tag, Menu menu)
    {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("tag", tag));
        crit.add(Restrictions.eq("menu", menu));
        return crit.list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tagging> findByMenu(Menu menu)
    {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("menu", menu));
        return crit.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tagging> findByClient(Client client)
    {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("client", client));
        return crit.list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tagging> findByRestaurant(Restaurant restaurant)
    {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("restaurant", restaurant));
        return crit.list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tagging> findByTag(Tag tag)
    {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("tag", tag));
        return crit.list();
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
