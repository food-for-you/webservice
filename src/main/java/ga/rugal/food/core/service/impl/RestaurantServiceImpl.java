/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga.rugal.food.core.service.impl;

import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.RestaurantService;
import ga.rugal.food.core.dao.RestaurantDao;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.RestaurantService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author Ying Mi
 */
public class RestaurantServiceImpl implements RestaurantService{
    
    @Autowired
    private RestaurantDao restaurantDao;
    
    @Override
    public List<Restaurant> getByAddress(String address) {
        return restaurantDao.getByAddress(address);
    }
    
    @Override
    public Pagination getPage(int pageNo, int pageSize) {
        return restaurantDao.getPage(pageNo, pageSize);
    }
    
    @Override
    public Menu getMenuByLocation(String address) {
        
        List<Menu> mList = getMenuList(address);
        Menu menu = getRandomMenu(mList);
        return menu;
    }
    
    public List<Menu> getMenuList(String address) {
        RestaurantServiceImpl rs = new RestaurantServiceImpl();
        List<Restaurant> rList = rs.getByAddress(address);
        List<Menu> listFinal = new ArrayList();
        rList.stream().map((a) -> a.getMenuList()).forEach((menuList) -> {
            listFinal.addAll(menuList);
        });
        return listFinal;
    }
    
    public Menu getRandomMenu(List<Menu> mList) {
        
        Random rd = new Random();       
        Menu m = mList.get(rd.nextInt(mList.size()));
        return m;
    }
    
}
