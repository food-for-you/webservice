package ga.rugal.food.core.dao.impl;

import ga.rugal.DBTestBase;
import ga.rugal.food.core.dao.MenuDao;
import ga.rugal.food.core.dao.RestaurantDao;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
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
public class MenuDaoImplTest extends DBTestBase
{

    @Autowired
    private Menu menu;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private Restaurant restaurant;

    @Autowired
    private RestaurantDao restaurantDao;

    public MenuDaoImplTest()
    {
    }

    @Before
    public void setUp()
    {
        System.out.println("setUp");
        restaurantDao.save(restaurant);
        menuDao.save(menu);
    }

    @After
    public void tearDown()
    {
        System.out.println("tearDown");
        menuDao.deleteById(menu.getMid());
        restaurantDao.deleteById(restaurant.getRid());
    }

    @Test
    public void testGetPage()
    {
        System.out.println("getPage");
        int pageNo = 1;
        int pageSize = 1;
        Pagination result = menuDao.getPage(pageNo, pageSize);
        Assert.assertEquals(1, result.getList().size());
    }

    @Test
    public void testGetByID()
    {
        System.out.println("getByID");
        Integer id = menu.getMid();
        Assert.assertNotNull(menuDao.getByID(id));
    }

}
