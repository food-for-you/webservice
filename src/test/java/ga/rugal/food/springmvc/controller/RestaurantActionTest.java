package ga.rugal.food.springmvc.controller;

import ga.rugal.ControllerClientSideTestBase;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.MenuService;
import ga.rugal.food.core.service.RestaurantService;
import ml.rugal.sshcommon.springmvc.util.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Ying Mi
 */
public class RestaurantActionTest extends ControllerClientSideTestBase
{
    public RestaurantActionTest() {
        
    }
    
    @Autowired
    private RestaurantService restaurantService;
    
     @Autowired
    private Restaurant restaurant;

    @Autowired
    private MenuService menuService;
    
    @Autowired
    private Menu menu;
    
    @Before
    public void setUp()
    {
        System.out.println("setUp");
        restaurantService.save(restaurant);
        menuService.save(menu);
    }

    @After
    public void tearDown()
    {
        System.out.println("tearDown");
        menuService.deleteById(menu.getMid());
        restaurantService.deleteById(restaurant.getRid());
    }
    
    @Test
    public void testRandomRestaurant() throws Exception {
        System.out.println("randomRestaurant");
        MvcResult result = this.mockMvc.perform(get("/restaurant")
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn();
        Message message = GSON.fromJson(result.getResponse().getContentAsString(), Message.class);
        Menu getFromDB = menu.backToObject(message.getData());
        Assert.assertNotNull(getFromDB);
    }
}
