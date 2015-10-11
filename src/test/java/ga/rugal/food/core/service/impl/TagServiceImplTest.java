package ga.rugal.food.core.service.impl;

import ga.rugal.DBTestBase;
import ga.rugal.food.core.entity.Tag;
import ga.rugal.food.core.service.TagService;
import ml.rugal.sshcommon.hibernate.Updater;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rugal Bernstein
 */
public class TagServiceImplTest extends DBTestBase
{

    @Autowired
    private TagService tagService;

    @Autowired
    private Tag tag;

    public TagServiceImplTest()
    {
    }

    @Before
    public void setUp()
    {
        System.out.println("setUp");
        tagService.save(tag);
    }

    @After
    public void tearDown()
    {
        System.out.println("tearDown");
        tagService.deleteById(tag.getTid());
    }

    @Test
    public void testGetPage()
    {
        System.out.println("updateByUpdater");
        String content = "Tenjin";
        Updater<Tag> updater = new Updater<>(tag);
        tag.setName(content);
        Tag newTag = tagService.updateByUpdater(updater);
        Assert.assertEquals(content, newTag.getName());
    }
}
