package ga.rugal.food.core.dao;

import ga.rugal.food.core.entity.Client;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.entity.Tag;
import ga.rugal.food.core.entity.Tagging;
import java.util.List;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rugal Bernstein
 */
public interface TaggingDao
{

    Tagging deleteById(Long id);

    @Transactional(readOnly = true)
    Tagging getByID(Long id);

    @Transactional(readOnly = true)
    Pagination getPage(int pageNo, int pageSize);

    Tagging save(Tagging bean);

    Tagging updateByUpdater(Updater<Tagging> updater);

    /**
     * Find taggings that matched given tag and restaurant.
     *
     * @param tag
     * @param restaurant
     *
     * @return
     */
    @Transactional(readOnly = true)
    List<Tagging> findByTagAndRestaurant(Tag tag, Restaurant restaurant);

    @Transactional(readOnly = true)
    List<Tagging> findByMenu(Menu menu);

    @Transactional(readOnly = true)
    List<Tagging> findByRestaurant(Restaurant restaurant);

    @Transactional(readOnly = true)
    List<Tagging> findByTag(Tag tag);

    @Transactional(readOnly = true)
    List<Tagging> findByClient(Client client);

    @Transactional(readOnly = true)
    List<Tagging> findByTagAndMenu(Tag tag, Menu menu);
}
