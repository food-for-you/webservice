package ga.rugal.food.core.dao.impl;

import ga.rugal.food.common.CommonLogContent;
import ga.rugal.food.core.dao.MenuDao;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.entity.Tag;
import ga.rugal.food.core.entity.Tagging;
import java.util.List;
import java.util.Random;
import ml.rugal.sshcommon.hibernate.HibernateBaseDao;
import ml.rugal.sshcommon.page.Pagination;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rugal Bernstein
 */
@Repository
public class MenuDaoImpl extends HibernateBaseDao<Menu, Integer> implements MenuDao
{

    private static final Logger LOG = LoggerFactory.getLogger(MenuDaoImpl.class.getName());

    @Autowired
    private Random random;

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize)
    {
        Criteria crit = createCriteria();
        Pagination page = findByCriteria(crit, pageNo, pageSize);
        return page;
    }

    @Transactional(readOnly = true)
    @Override
    public int countTotal()
    {
        Criteria crit = createCriteria();
        crit.setProjection(Projections.count("mid"));
        return ((Number) crit.list().get(0)).intValue();
    }

    @Override
    @Transactional(readOnly = true)
    public Menu getByID(Integer id)
    {
        Menu entity = get(id);
        return entity;
    }

    @Override
    public Menu save(Menu bean)
    {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Menu deleteById(Integer id)
    {
        Menu entity = super.get(id);
        if (entity != null)
        {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Menu> getEntityClass()
    {
        return Menu.class;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public int countMenusByRestaurant(Restaurant r)
    {

        int count = 0;
        if (null != r)
        {
            count = countByProperty("restaurant", r);
        }
        return count;

    }

    @Override
    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public Menu getRandomMenuByRestaurant(Restaurant r)
    {

        int count = countMenusByRestaurant(r);
        if (0 == count)
        {
            LOG.info(CommonLogContent.NO_MENU);
            return null;
        }
        Menu m = findByProperty("restaurant", r).get(random.nextInt(count));
        return m;
    }

    @Transactional(readOnly = true)
    public List<Menu> getMenusByRestaurant(Restaurant r)
    {
        List<Menu> list = this.findByProperty("restaurant", r);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Menu getRandomMenuByTagAndRestaurant(Tag tag, Restaurant restaurant)
    {
        //This method will not validate input parameters
        int count = countByTagAndRestaurant(tag, restaurant);
        if (0 == count)
        {
            LOG.info(CommonLogContent.NO_MENU);
            return null;
        }
        Criteria crit = this.searchByTagAndRestaurant(tag, restaurant);
        return (Menu) findByCriteria(crit, random.nextInt(count), 1).getList().get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public int countByTagAndRestaurant(Tag tag, Restaurant restaurant)
    {
        Criteria crit = this.searchByTagAndRestaurant(tag, restaurant);
        crit.setProjection(Projections.count(Projections.id().toString()));
        return ((Number) crit.list().get(0)).intValue();
    }

    /**
     * Private method that wrap common SQL together.<BR>
     * This SQL could be optimized by using inner join, but doing this will uglify Hibernate's
     * structure.<BR>
     * SELECT * FROM menu WHERE mid =ANY(ARRAY(SELECT mid FROM tagging WHERE tid = 1 AND mid =
     * ANY(ARRAY (SELECT mid FROM menu WHERE rid = 1))));
     *
     * @param tag
     * @param restaurant
     *
     * @return
     */
    private Criteria searchByTagAndRestaurant(Tag tag, Restaurant restaurant)
    {
        //Subquery for menus that from given restaurant
        DetachedCriteria subquery1 = DetachedCriteria.forClass(Menu.class);
        subquery1.add(Restrictions.eq("restaurant", restaurant));
        subquery1.setProjection(Projections.id());

        //Subquery for menu that meet target tag and from target restaurant
        DetachedCriteria subquery2 = DetachedCriteria.forClass(Tagging.class);
        subquery2.add(Restrictions.eq("tag", tag));
        subquery2.add(Subqueries.propertyIn("menu.mid", subquery1));
        subquery2.setProjection(Property.forName("menu.mid"));

        //Get menu list
        Criteria crit = createCriteria();
        crit.add(Subqueries.propertyIn("mid", subquery2));
        return crit;
    }

}
