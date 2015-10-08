package ga.rugal.food.core.dao;

import ga.rugal.food.core.entity.Menu;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rugal Bernstein
 */
public interface MenuDao
{

    Menu deleteById(Integer id);

    @Transactional(readOnly = true)
    Menu getByID(Integer id);

    @Transactional(readOnly = true)
    Pagination getPage(int pageNo, int pageSize);

    Menu save(Menu bean);

    Menu updateByUpdater(Updater<Menu> updater);
}
