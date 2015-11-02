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
    
    @Transactional(readOnly = true)
    List<Menu> getByRestaurant(Restaurant r);
    
    @Transactional(readOnly = true)
    Menu getRandomMenuByRetaurant(List<Menu> list);

}
