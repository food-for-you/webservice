package ga.rugal.food.core.dao;

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

    @Transactional(readOnly = true)
    List<Tagging> findByMealTypeFromRestaurant(Tag tag, Restaurant restaurant);
}
