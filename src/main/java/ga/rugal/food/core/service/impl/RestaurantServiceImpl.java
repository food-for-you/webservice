package ga.rugal.food.core.service.impl;

import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.RestaurantService;
import ga.rugal.food.core.dao.RestaurantDao;
import ga.rugal.food.core.entity.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Ying Mi
 */
@Service
public class RestaurantServiceImpl implements RestaurantService{
    
    @Autowired
    private RestaurantDao restaurantDao;
    
    @Override
    public Restaurant save(Restaurant bean) {
        return restaurantDao.save(bean);
    }
    @Override
    public Restaurant deleteById(Integer id) {
        return restaurantDao.deleteById(id);
    }
    
    @Override
    public List<Restaurant> getWholeList() {
        return restaurantDao.getWholeList();
    }
    
    @Override
    public Pagination getPage(int pageNo, int pageSize) {
        return restaurantDao.getPage(pageNo, pageSize);
    }
    
    @Override
    public Menu getMenuByLocation() {
        
        List<Menu> mList = getMenuList();
        Menu menu = getRandomMenu(mList);
        return menu;
    }
    
    public List<Menu> getMenuList() {
        List<Restaurant> rList = getWholeList();
        List<Menu> listFinal = new ArrayList();
        rList.stream().map((a) -> a.getMenuList()).forEach((menuList) -> {
            listFinal.addAll(menuList);
        });
        return listFinal;
    }
    
    public Menu getRandomMenu(List<Menu> mList) {
        
        Random rd = new Random();       
        Menu m = mList.get(rd.nextInt(mList.size()));
        return m;
    }   
}
