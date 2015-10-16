package ga.rugal.food.core.dao.impl;

import ga.rugal.DBTestBase;
import ga.rugal.food.core.dao.RestaurantDao;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import java.util.List;
import ml.rugal.sshcommon.page.Pagination;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein, Ying Mi
 */
public class RestaurantDaoImplTest extends DBTestBase
{

    @Autowired
    private Restaurant restaurant;

    @Autowired
    private RestaurantDao restaurantDao;

    public RestaurantDaoImplTest()
    {
    }

    @Before
    public void setUp()
    {
        System.out.println("setUp");
        restaurantDao.save(restaurant);
    }

    @After
    public void tearDown()
    {
        System.out.println("tearDown");
        restaurantDao.deleteById(restaurant.getRid());
    }

    @Test
    public void testGetPage()
    {
        System.out.println("getPage");
        int pageNo = 1;
        int pageSize = 1;
        Pagination result = restaurantDao.getPage(pageNo, pageSize);
        Assert.assertEquals(1, result.getList().size());
    }

    @Test
    public void testGetByID()
    {
        System.out.println("getByID");
        Integer id = restaurant.getRid();
        Assert.assertNotNull(restaurantDao.getByID(id));
    }
    
    @Test
    public void testGetWholeList()
    {
        System.out.println("getWholeList");
        //String address = "Rankin";
        List <Restaurant> list = restaurantDao.getWholeList();
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());
        //List<Menu> mList = list.get(1).getMenuList();
//        for(Menu m : mList) {
//            System.out.println(m.getName());
//        }
        //Assert.assertTrue(mList.size() == 0);
    }
}
