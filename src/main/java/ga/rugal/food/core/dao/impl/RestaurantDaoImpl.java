package ga.rugal.food.core.dao.impl;

import ga.rugal.food.core.dao.RestaurantDao;
import ga.rugal.food.core.entity.Restaurant;
import java.util.List;
import java.util.Random;
import ml.rugal.sshcommon.hibernate.Finder;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rugal Bernstein, Ying Mi
 */
@Repository
public class RestaurantDaoImpl extends HibernateBaseDao<Restaurant, Integer> implements RestaurantDao
{

    @Autowired
    private Random random;

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

    public Restaurant getAvailableRestaurant()
    {
//select r.rid, count(m.mid)  from restaurant r, menu m where m.rid = r.rid group by r.rid having count(m.mid)>0;

        String HQL = "from Restaurant r, Menu m where r.rid=m.rid group by r.rid having count(m.mid)>0";
        Finder finder = Finder.create(HQL);
        int count = countQueryResult(finder);
        Restaurant restaurant = null;
        if (count > 0)
        {
            finder.setMaxResults(1);
            finder.setFirstResult(random.nextInt(count));
            restaurant = (Restaurant) super.find(finder).get(0);
        }

        return restaurant;
    }
}
