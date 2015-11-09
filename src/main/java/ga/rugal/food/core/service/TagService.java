package ga.rugal.food.core.service;

import ga.rugal.food.core.entity.Tag;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rugal Bernstein
 */
public interface TagService
{

    Tag deleteById(Integer id);

    @Transactional(readOnly = true)
    Tag getByID(Integer id);

    @Transactional(readOnly = true)
    Pagination getPage(int pageNo, int pageSize);

    Tag save(Tag bean);

    Tag updateByUpdater(Updater<Tag> updater);

    @Transactional(readOnly = true)
    Tag getByName(String name);

}
