/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga.rugal.food.core.service;

import ga.rugal.food.core.entity.Menu;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jinsongchu
 */
public interface MenuService {
    
    Menu save(Menu bean);
    
    //Menu getByRestaurantID(Integer rid);
    
    @Transactional(readOnly = true)
    Pagination getPage(int pageNo, int pageSize);
}
