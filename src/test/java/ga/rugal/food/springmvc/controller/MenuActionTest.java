package ga.rugal.food.springmvc.controller;

import ga.rugal.ControllerClientSideTestBase;
import ga.rugal.food.core.dao.MenuDao;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.RestaurantService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Ying Mi
 */
public class MenuActionTest extends ControllerClientSideTestBase {
    
    public MenuActionTest() {
        
    }
    
    @Autowired
    private Restaurant restaurant;
    
    @Autowired
    private Menu menu;
    
    @Autowired
    private MenuDao menuDao;
    
    @Autowired
    private RestaurantService restaurantService;    
    
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
    public void testRecommendByLocation() throws Exception {
        System.out.println("recommend menu by location");
        this.mockMvc.perform(get("/menu")
            //.contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andDo(print());
            
    }    
}
