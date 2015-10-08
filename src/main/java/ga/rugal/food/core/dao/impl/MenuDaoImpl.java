package ga.rugal.food.core.dao.impl;

import ga.rugal.food.core.dao.MenuDao;
import ga.rugal.food.core.entity.Menu;
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
public class MenuDaoImpl extends HibernateBaseDao<Menu, Integer> implements MenuDao
{

    private static final Logger LOG = LoggerFactory.getLogger(MenuDaoImpl.class.getName());

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

}
