package ga.rugal.food.springmvc.controller;

import config.SystemDefaultProperties;
import ga.rugal.food.common.CommonLogContent;
import ga.rugal.food.common.CommonMessageContent;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.MenuService;
import ga.rugal.food.core.service.RestaurantService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import ml.rugal.sshcommon.springmvc.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ying Mi
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuAction
{

    private static final Logger LOG = LoggerFactory.getLogger(MenuAction.class.getName());

    private static final File imageFolder = new File(SystemDefaultProperties.IMAGE_FOLDER);

    @Autowired
    private ServletContext context;

    private final Random random = new Random();

    @Autowired
    private MenuService menuService;
    
    @Autowired
    private RestaurantService restaurantService;
/*
    @ResponseBody
    @RequestMapping(value = "/menu1", method = RequestMethod.GET)
    public Message randomMenu()
    {
        int total = menuService.countTotal();
        LOG.debug(CommonLogContent.MENU_NUMBER, total);
        if (0 == total)
        {
            LOG.warn(CommonLogContent.NO_MENU);
            return Message.failMessage(CommonMessageContent.MENU_NOT_FOUND);
        }
        Menu menu = (Menu) menuService.getPage(random.nextInt(total), 1).getList().get(0);
        return Message.successMessage(CommonMessageContent.GET_MENU, menu);
    }
*/
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Message menuByRestaurant() {
  
        Restaurant restaurant = restaurantService.getRandomRestaurant();
        List<Menu> menuList = menuService.getByRestaurant(restaurant);
        
        if (0 == menuList.size()) {
            LOG.warn(CommonLogContent.NO_MENU);
            return Message.failMessage(CommonMessageContent.MENU_NOT_FOUND);
        }
        Menu menu = menuService.getRandomMenuByRetaurant(menuList);
        return Message.successMessage(CommonMessageContent.GET_MENU, menu);
    }
    
    /**
     * GET image by the request menu id.
     * We will return a default picture if no image found or path not accessible for reading.<BR>
     * Currently, this image is {@link config.SystemDefaultProperties#DEFAULT_IMAGE}
     *
     * @param mid
     * @param response
     *
     * @return Give Message object in JSON format if any exception occurs;
     *         otherwise, return the icon data in byte array format.
     *
     */
    @ResponseBody
    @RequestMapping(value = "/{mid}/image")
    public Object getImage(@PathVariable("mid") Integer mid, HttpServletResponse response)
    {
        Menu menu = menuService.getByID(mid);
        byte[] data;
        File image = new File(imageFolder, SystemDefaultProperties.DEFAULT_IMAGE);
        try
        {
            if (null != menu)
            {
                image = new File(imageFolder, menu.getImage());
            }
            if (!image.exists())
            {
                //if no image file found in path
                LOG.info(String.format(CommonLogContent.IMAGE_NOT_FOUND, image.getName()));
            }
            LOG.trace(image.getPath());
            data = FileCopyUtils.copyToByteArray(image);
        }
        catch (IOException ex)
        {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            LOG.error(String.format(CommonLogContent.ERROR_READ_IMAGE, image.getName()), ex);
            return Message.failMessage(CommonMessageContent.IMAGE_NOT_FOUND);
        }
        LOG.trace(String.format(CommonLogContent.IMAGE_LENGTH, data.length));
        response.setContentType(context.getMimeType(image.getName()));
        response.setContentLength(data.length);
        //The code below is for in browser displaying
//        response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", image.getName()));
        return data;
    }
}
