package ga.rugal.food.core.service.impl;

import ga.rugal.food.core.dao.MenuDao;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.MenuService;
import java.util.List;
import java.util.Random;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ying Mi
 */
@Service
public class MenuServiceImpl implements MenuService
{

    @Autowired
    private MenuDao menuDao;

    @Override
    public Menu save(Menu bean)
    {
        return menuDao.save(bean);
    }

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        return menuDao.getPage(pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public int countTotal()
    {
        return menuDao.countTotal();
    }

    @Override
    @Transactional(readOnly = true)
    public Menu getByID(Integer id)
    {
        return menuDao.getByID(id);
    }

    @Override
    public Menu deleteById(Integer id)
    {
        return menuDao.deleteById(id);
    }
    
    @Override
    public List<Menu> getMenusByRestaurant(Restaurant r) {
        return menuDao.getMenusByRestaurant(r);
    }
    
    @Override
    public Menu getRandomMenuByRetaurant(Restaurant r) {
        Random rd = new Random();
        List<Menu> list = getMenusByRestaurant(r);
        Menu m = list.get(rd.nextInt(list.size()));
        return m;
    }

}
