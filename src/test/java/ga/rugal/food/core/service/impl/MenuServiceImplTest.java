package ga.rugal.food.core.service.impl;

import ga.rugal.DBTestBase;
import ga.rugal.food.core.dao.ClientDao;
import ga.rugal.food.core.dao.TaggingDao;
import ga.rugal.food.core.entity.Client;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.entity.Tag;
import ga.rugal.food.core.entity.Tagging;
import ga.rugal.food.core.service.MenuService;
import ga.rugal.food.core.service.RestaurantService;
import ga.rugal.food.core.service.TagService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ying Mi, Rugal Bernstein
 */
public class MenuServiceImplTest extends DBTestBase
{

    @Autowired
    private Restaurant restaurant;

    @Autowired
    private Client client;

    @Autowired
    private Tag tag;

    @Autowired
    private Tagging tagging;

    @Autowired
    private Menu menu;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private TagService tagService;

    @Autowired
    private TaggingDao taggingDao;

    @Autowired
    private MenuService menuService;

    public MenuServiceImplTest()
    {

    }

    @Before
    public void setUp()
    {
        System.out.println("setUp");
        restaurantService.save(restaurant);
        menuService.save(menu);
        clientDao.save(client);
        tagService.save(tag);
        taggingDao.save(tagging);
    }

    @After
    public void tearDown()
    {
        System.out.println("tearDown");
        taggingDao.deleteById(tagging.getGid());
        menuService.deleteById(menu.getMid());
        restaurantService.deleteById(restaurant.getRid());
        clientDao.deleteById(client.getCid());
        tagService.deleteById(tag.getTid());
    }

    @Test
    public void testGetRandomMenuByRetaurant()
    {
        System.out.println("getRandomMenuByRetaurant");
        Restaurant r = restaurantService.getRandomRestaurant();
        Menu m = menuService.getRandomMenuByRetaurant(r);
        Assert.assertNotNull(m);
    }

}
