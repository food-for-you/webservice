package ga.rugal.food.springmvc.controller;

import ga.rugal.ControllerClientSideTestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Rugal Bernstein
 */
@Ignore
public class StaticResourceActionTest extends ControllerClientSideTestBase
{

    public StaticResourceActionTest()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetImage() throws Exception
    {
        System.out.println("getImage");
        this.mockMvc.perform(get("/img/rugal.jpg")
            .accept(MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE
            ))
            .andExpect(status().isOk());
        //.andDo(print());
    }

    @Test
    public void testGetMissedImage() throws Exception
    {
        System.out.println("getImage");
        this.mockMvc.perform(get("/img/rugal")
            .accept(MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE
            ))
            .andExpect(status().isOk())
            .andDo(print());
    }
}
