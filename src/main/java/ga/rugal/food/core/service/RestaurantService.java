/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga.rugal.food.core.service;
import ga.rugal.food.core.entity.Restaurant;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Ying Mi
 */
public interface RestaurantService {
     
    Restaurant getByAddress(String address);
    
    @Transactional(readOnly = true)
    Pagination getPage(int pageNo, int pageSize);
    
}
