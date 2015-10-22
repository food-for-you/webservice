package ga.rugal.food.core.service;

import ga.rugal.food.core.entity.Menu;
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

}
