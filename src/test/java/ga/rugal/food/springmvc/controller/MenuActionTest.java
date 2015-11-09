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
public class MenuActionTest extends ControllerClientSideTestBase
{

    public MenuActionTest()
    {

    }

    @Autowired
    private Restaurant restaurant;

    @Autowired
    private Menu menu;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

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
    public void testGetMenu() throws Exception
    {
        System.out.println("getMenu");
        MvcResult result = this.mockMvc.perform(get("/menu")
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn();
        Message message = GSON.fromJson(result.getResponse().getContentAsString(), Message.class);
        Menu getFromDB = menu.backToObject(message.getData());
        Assert.assertNotNull(getFromDB);
    }

    @Test
    public void testGetMenuByMealType() throws Exception
    {
        System.out.println("getMenuByMealType");
        MvcResult result = this.mockMvc.perform(get("/menu")
            .param("meal", "lunch")
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn();
        Message message = GSON.fromJson(result.getResponse().getContentAsString(), Message.class);
        //Use this assertion because there is a empty data in unit test set up
        Assert.assertEquals(Message.SUCCESS, message.getStatus());
    }

    @Test
    public void testGetImage() throws Exception
    {
        System.out.println("getImage");
        this.mockMvc.perform(get(String.format("/menu/%d/image", menu.getMid()))
            .accept(MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE
            ))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void testGetMissedImage() throws Exception
    {
        System.out.println("getMissedImage");
        this.mockMvc.perform(get(String.format("/menu/%d/image", 0))
            .accept(MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE
            ))
            .andExpect(status().isOk())
            .andDo(print());
    }
}
