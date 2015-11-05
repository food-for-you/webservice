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
    public void testFindByMealTypeFromRestaurant()
    {
        System.out.println("findByMealTypeFromRestaurant");
        List<Tagging> list = taggingDao.findByMealTypeFromRestaurant(tag, restaurant);
        Assert.assertEquals(1, list.size());
    }

}
