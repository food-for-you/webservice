package ga.rugal.food.core.dao.impl;

import ga.rugal.DBTestBase;
import ga.rugal.food.core.dao.TagDao;
import ga.rugal.food.core.entity.Tag;
import ml.rugal.sshcommon.page.Pagination;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein
 */
public class TagDaoImplTest extends DBTestBase
{

    @Autowired
    private Tag tag;

    @Autowired
    private TagDao tagDao;

    public TagDaoImplTest()
    {
    }

    @Before
    public void setUp()
    {
        System.out.println("setUp");
        tagDao.save(tag);
    }

    @After
    public void tearDown()
    {
        System.out.println("tearDown");
        tagDao.deleteById(tag.getTid());
    }

    @Test
    public void testGetPage()
    {
        System.out.println("getPage");
        int pageNo = 1;
        int pageSize = 1;
        Pagination result = tagDao.getPage(pageNo, pageSize);
        Assert.assertEquals(1, result.getList().size());
    }

    @Test
    public void testGetByID()
    {
        System.out.println("getByID");
        Integer id = tag.getTid();
        Assert.assertNotNull(tagDao.getByID(id));
    }

}
