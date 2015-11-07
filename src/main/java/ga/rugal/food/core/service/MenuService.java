package ga.rugal.food.core.service;

import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.entity.Tag;
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
     * Count the number of menus that belong to a specific restaurant
     *
     * @param r
     *
     * @return Give the number of menus
     */
    @Transactional(readOnly = true)
    int countMenusByRestaurant(Restaurant r);

    /**
     * Get a random menu from all menus belong to a specific restaurant
     *
     * @param r
     *
     * @return Give a menu
     */
    @Transactional(readOnly = true)
    Menu getRandomMenuByRetaurant(Restaurant r);

    /**
     * Find a menu that match given tag and provided by restaurant.
     *
     * @param tag
     * @param restaurant
     *
     * @return
     */
    @Transactional(readOnly = true)
    Menu getRandomMenuByTagAndRestaurant(Tag tag, Restaurant restaurant);
}
