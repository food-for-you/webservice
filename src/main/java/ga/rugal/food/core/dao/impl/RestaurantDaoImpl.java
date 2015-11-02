package ga.rugal.food.core.dao.impl;

import ga.rugal.food.core.dao.RestaurantDao;
import ga.rugal.food.core.entity.Restaurant;
import java.util.List;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rugal Bernstein, Ying Mi
 */
@Repository
public class RestaurantDaoImpl extends HibernateBaseDao<Restaurant, Integer> implements RestaurantDao
{

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantDaoImpl.class.getName());

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
    public Restaurant getByID(Integer id)
    {
        Restaurant entity = get(id);
        return entity;
    }
    
    @Override
    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<Restaurant> getWholeList()
    {
        Criteria crit = createCriteria();
        List<Restaurant> list = (List<Restaurant>) crit.list();
        return list;
    }

    @Override
    public Restaurant save(Restaurant bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Restaurant deleteById(Integer id)
    {
        Restaurant entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Transactional(readOnly = true)
    @Override
    public int countTotal()
    {
        Criteria crit = createCriteria();
        crit.setProjection(Projections.count("rid"));
        return ((Number) crit.list().get(0)).intValue();
    }

    @Override
    protected Class<Restaurant> getEntityClass()
    {
        return Restaurant.class;
    }
}
