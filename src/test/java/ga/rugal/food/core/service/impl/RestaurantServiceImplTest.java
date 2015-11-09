package ga.rugal.food.core.service.impl;

import ga.rugal.DBTestBase;
import ga.rugal.food.core.dao.MenuDao;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.MenuService;
import ga.rugal.food.core.service.RestaurantService;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ying Mi
 */
public class RestaurantServiceImplTest extends DBTestBase {
    
    @Autowired
    private Restaurant restaurant;
    
    @Autowired
    private Menu menu;
    
    @Autowired
    private MenuDao menuDao;
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private MenuService menuService;
    
    public RestaurantServiceImplTest() {
    
    }
    
    @Before
    public void setUp() {
        System.out.println("setUp");
        restaurantService.save(restaurant);
        menuDao.save(menu);
        menu.setRestaurant(restaurant);
    }

    @After
    public void tearDown() {
        System.out.println("tearDown");
        menuDao.deleteById(menu.getMid());
        restaurantService.deleteById(restaurant.getRid());
    } 
    
    @Test
    public void testGetRandomRestaurant() {
        System.out.println("getRandomRestaurant");
        Restaurant r = restaurantService.getRandomRestaurant();
        Assert.assertNotNull(r);       
    }
   
}
