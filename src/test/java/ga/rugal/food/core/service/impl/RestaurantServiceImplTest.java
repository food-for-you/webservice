/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga.rugal.food.core.service.impl;

import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.RestaurantService;
import java.util.List;
import javafx.scene.AccessibleAttribute;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ying Mi
 */
public class RestaurantServiceImplTest {
    
    @Autowired
    private Restaurant restaurant;
    
    @Autowired
    private RestaurantService restaurantService;    
    
    public RestaurantServiceImplTest() {
    
    }
    
    @Before
    public void setUp()
    {
        System.out.println("setUp");
        restaurantService.save(restaurant);
    }

    @After
    public void tearDown()
    {
        System.out.println("tearDown");
        restaurantService.deleteById(restaurant.getRid());
    }
    
    
    @Test
    public void testgetMenuByLocation() {
        System.out.println("get menu by location");
        Menu menu = restaurantService.getMenuByLocation();
        Assert.assertNotNull(menu);
    }
    
}
