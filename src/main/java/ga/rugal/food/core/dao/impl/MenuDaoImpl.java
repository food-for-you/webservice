package ga.rugal.food.core.dao.impl;

import ga.rugal.food.common.CommonLogContent;
import ga.rugal.food.core.dao.MenuDao;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import java.util.Random;
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
    public int countMenusByRestaurant(Restaurant r) {     
        int count;
        if(null == r) {
           count = 0; 
        }
        count = countByProperty("restaurant", r);        
        return count;
        
    }
    
    @Override
    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public Menu getRandomMenuByRestaurant(Restaurant r) {
       
        int count = countMenusByRestaurant(r);
        if(0 == count) {
            LOG.info(CommonLogContent.NO_MENU);
            return null;
        }
        Menu m = findByProperty("restaurant", r).get(random.nextInt(count));
        return m;
    }

}
