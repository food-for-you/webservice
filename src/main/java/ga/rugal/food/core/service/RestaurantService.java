/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga.rugal.food.core.service;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import java.util.List;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Ying Mi
 */
public interface RestaurantService {
    
    @Transactional(readOnly = true) 
    List<Restaurant> getByAddress(String address);
    
    
    @Transactional(readOnly = true)
    Pagination getPage(int pageNo, int pageSize);
    
    @Transactional(readOnly = true)
    Menu getMenuByLocation(String address);
}
