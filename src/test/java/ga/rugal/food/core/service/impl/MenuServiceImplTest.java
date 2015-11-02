package ga.rugal.food.core.service.impl;

import ga.rugal.DBTestBase;
import ga.rugal.food.core.dao.MenuDao;
import ga.rugal.food.core.dao.RestaurantDao;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.MenuService;
import ga.rugal.food.core.service.RestaurantService;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 *
 * @author Ying Mi
 */
public class MenuServiceImplTest extends DBTestBase{
    
    @Autowired
    private Restaurant restaurant;
    
    @Autowired
    private Menu menu;
    
    @Autowired
    private MenuDao menuDao;
    
    @Autowired
    private RestaurantDao restaurantDao;
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private MenuService menuService;
    
    public MenuServiceImplTest() {
    
    }
    
    @Before
    public void setUp() {
        System.out.println("setUp");
        restaurantDao.save(restaurant);
        menuDao.save(menu);
        menu.setRestaurant(restaurant);
    }

    @After
    public void tearDown() {
        System.out.println("tearDown");
        menuDao.deleteById(menu.getMid());
        restaurantDao.deleteById(restaurant.getRid());
    }
    
    @Test
    public void testGetRandomMenuByRetaurant() {
        System.out.println("getRandomMenuByRetaurant");
        Restaurant r = restaurantService.getRandomRestaurant();
        List<Menu> list = menuService.getByRestaurant(r);
        Menu m = menuService.getRandomMenuByRetaurant(list);
        Assert.notNull(m);
    }
}
