package ga.rugal.food.core.service.impl;

import ga.rugal.food.common.CommonLogContent;
import ga.rugal.food.core.dao.RestaurantDao;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.RestaurantService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ml.rugal.sshcommon.page.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ying Mi
 */
@Service
public class RestaurantServiceImpl implements RestaurantService
{

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantServiceImpl.class.getName());
    
    @Autowired
    private RestaurantDao restaurantDao;
    
    @Autowired
    private Random random;

    @Override
    public Restaurant save(Restaurant bean)
    {
        return restaurantDao.save(bean);
    }

    @Override
    public Restaurant deleteById(Integer id)
    {
        return restaurantDao.deleteById(id);
    }

    @Override
    public List<Restaurant> getWholeList()
    {
        return restaurantDao.getWholeList();
    }

    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        return restaurantDao.getPage(pageNo, pageSize);
    }
    
    @Override
    /**
     * {@inheritDoc}
     */
    public List<Menu> getMenuList()
    {
        List<Restaurant> rList = getWholeList();
        List<Menu> listFinal = new ArrayList();
        rList.stream().map((a) -> a.getMenuList()).forEach((menuList) ->
        {
            listFinal.addAll(menuList);
        });
        return listFinal;
    }

    @Override
    @Transactional(readOnly = true)
    public int countTotal()
    {
        return restaurantDao.countTotal();
    }

    @Override
    public Restaurant getRandomRestaurant() {
      
        int total = countTotal();
        if(0 == total) {
            LOG.info(CommonLogContent.RESAURANT_NOT_FOUND);
            return null;          
        }
        Restaurant r = (Restaurant) getPage(random.nextInt(total), 1).getList().get(0);
        return r;
    }
}
