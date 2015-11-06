package ga.rugal.food.springmvc.controller;

import config.SystemDefaultProperties;
import ga.rugal.food.common.CommonLogContent;
import ga.rugal.food.common.CommonMessageContent;
import ga.rugal.food.core.entity.MealType;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.entity.Tag;
import ga.rugal.food.core.service.MenuService;
import ga.rugal.food.core.service.RestaurantService;
import ga.rugal.food.core.service.TagService;
import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ying Mi, Rugal Bernstein
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuAction
{

    private static final Logger LOG = LoggerFactory.getLogger(MenuAction.class.getName());

    private static final File imageFolder = new File(SystemDefaultProperties.IMAGE_FOLDER);

    @Autowired
    private ServletContext context;

    @Autowired
    private MenuService menuService;

    @Autowired
    private TagService tagService;

    @Autowired
    private RestaurantService restaurantService;

    /**
     * Get menu information through URL /menu<BR>
     * This method is to feed client with a random menu. The detail way of getting menu is depending
     * on input parameter.
     *
     * @param meal if this parameter is specified and valid, server will find a menu that meet the
     *             meal type requirement; otherwise, server will just randomly find a menu for client.
     *
     * @return Give successful message and menu data in JSON format if the menu exist,
     *         on the contrary, return failed message
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Message getMenu(@RequestParam(value = "meal", required = false, defaultValue = "") String meal)
    {
        Tag tag = null;
        try
        {
            //In case of invalid meal type name
            MealType mt = MealType.valueOf(meal);
            tag = tagService.getByName(mt.toString());
        }
        catch (IllegalArgumentException iae)
        {
            //ignore invalid meal type
        }
        Message message;
        Restaurant restaurant = restaurantService.getRandomRestaurant();
        if (null == restaurant)
        {
            //check validity of restaurant object
            return Message.failMessage(CommonMessageContent.MENU_NOT_FOUND);
        }
        if (null == tag)
        {
            //no or wrong meal type, just use old function.
            message = fullRandomMenuRecommendation(restaurant);
        }
        else
        {
            //Now all parameters have been validated
            Menu menu = menuService.getRandomMenuByTagAndRestaurant(tag, restaurant);
            message = Message.successMessage(CommonMessageContent.GET_MENU, menu);
        }
        return message;
    }

    /**
     * This is the old way to get thorough random menu
     *
     * @param restaurant
     *
     * @return
     */
    private Message fullRandomMenuRecommendation(Restaurant restaurant)
    {
        Menu menu;
        Message message;
        int count = menuService.countMenusByRestaurant(restaurant);
        if (0 == count)
        {
            //check if there is no menu available from this restaurant.
            LOG.warn(CommonLogContent.NO_MENU);
            message = Message.failMessage(CommonMessageContent.MENU_NOT_FOUND);
        }
        else
        {
            menu = menuService.getRandomMenuByRetaurant(restaurant);
            message = Message.successMessage(CommonMessageContent.GET_MENU, menu);
        }
        return message;
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
