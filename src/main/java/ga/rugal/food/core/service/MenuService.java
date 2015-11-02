package ga.rugal.food.core.service;

import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import java.util.List;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ying Mi, Rugal Bernstein
 */
public interface MenuService
{

    Menu save(Menu bean);

    @Transactional(readOnly = true)
    Pagination getPage(int pageNo, int pageSize);

    @Transactional(readOnly = true)
    int countTotal();

    Menu deleteById(Integer id);

    @Transactional(readOnly = true)
    Menu getByID(Integer id);
    
    /**
     * Find all menus that belong to a specific restaurant
     * @param r
     * @return Give a list with all matched menus
     */
    @Transactional(readOnly = true)
    List<Menu> getMenusByRestaurant(Restaurant r);
    
    /**
     * Get a random menu from all menus belong to a specific restaurant
     * @param r
     * @return Give a menu
     */
    @Transactional(readOnly = true)
    Menu getRandomMenuByRetaurant(Restaurant r);

}
