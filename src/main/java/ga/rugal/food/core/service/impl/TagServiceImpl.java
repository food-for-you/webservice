package ga.rugal.food.core.service.impl;

import ga.rugal.food.core.dao.TagDao;
import ga.rugal.food.core.entity.Tag;
import ga.rugal.food.core.service.TagService;
import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rugal Bernstein
 */
@Service
public class TagServiceImpl implements TagService
{

    @Autowired
    private TagDao tagDao;

    @Override
    public Tag deleteById(Integer id)
    {
        return tagDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Tag getByID(Integer id)
    {
        return tagDao.getByID(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Pagination getPage(int pageNo, int pageSize)
    {
        return tagDao.getPage(pageNo, pageSize);
    }

    @Override
    public Tag save(Tag bean)
    {
        return tagDao.save(bean);
    }

    @Override
    public Tag updateByUpdater(Updater<Tag> updater)
    {
        return tagDao.updateByUpdater(updater);
    }

    @Transactional(readOnly = true)
    public Tag getByName(String name)
    {
        return tagDao.getByName(name);
    }
}
