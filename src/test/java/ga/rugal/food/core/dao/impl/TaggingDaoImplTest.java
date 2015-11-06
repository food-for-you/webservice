package ga.rugal.food.core.dao.impl;

import ga.rugal.DBTestBase;
import ga.rugal.food.core.dao.ClientDao;
import ga.rugal.food.core.dao.MenuDao;
import ga.rugal.food.core.dao.RestaurantDao;
import ga.rugal.food.core.dao.TagDao;
import ga.rugal.food.core.dao.TaggingDao;
import ga.rugal.food.core.entity.Client;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.entity.Tag;
import ga.rugal.food.core.entity.Tagging;
import java.util.List;
import ml.rugal.sshcommon.page.Pagination;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein
 */
public class TaggingDaoImplTest extends DBTestBase
{

    @Autowired
    private Client client;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private Tag tag;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private Menu menu;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private Restaurant restaurant;

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private Tagging tagging;

    @Autowired
    private TaggingDao taggingDao;

    public TaggingDaoImplTest()
    {
    }

    @Before
    public void setUp()
    {
        System.out.println("setUp");
        clientDao.save(client);
        tagDao.save(tag);
        restaurantDao.save(restaurant);
        menuDao.save(menu);
        taggingDao.save(tagging);
    }

    @After
    public void tearDown()
    {
        System.out.println("tearDown");
        taggingDao.deleteById(tagging.getGid());
        menuDao.deleteById(menu.getMid());
        restaurantDao.deleteById(restaurant.getRid());
        clientDao.deleteById(client.getCid());
        tagDao.deleteById(tag.getTid());
    }

    @Test
    public void testGetPage()
    {
        System.out.println("getPage");
        int pageNo = 1;
        int pageSize = 1;
        Pagination result = taggingDao.getPage(pageNo, pageSize);
        Assert.assertEquals(1, result.getList().size());
    }

    @Test
    public void testGetByID()
    {
        System.out.println("getByID");
        Long id = tagging.getGid();
        Assert.assertNotNull(taggingDao.getByID(id));
    }

    @Test
    public void testFindByTagAndRestaurant()
    {
        System.out.println("findByTagAndRestaurant");
        List<Tagging> result = taggingDao.findByTagAndRestaurant(tag, restaurant);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testFindByTagAndMenu()
    {
        System.out.println("findByTagAndMenu");
        List<Tagging> result = taggingDao.findByTagAndMenu(tag, menu);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testFindByMenu()
    {
        System.out.println("findByMenu");
        List<Tagging> result = taggingDao.findByMenu(menu);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testFindByClient()
    {
        System.out.println("findByClient");
        List<Tagging> result = taggingDao.findByClient(client);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testFindByRestaurant()
    {
        System.out.println("findByRestaurant");
        List<Tagging> result = taggingDao.findByRestaurant(restaurant);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testFindByTag()
    {
        System.out.println("findByTag");
        List<Tagging> result = taggingDao.findByTag(tag);
        Assert.assertEquals(1, result.size());
    }

}
