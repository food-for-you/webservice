package ga.rugal.food.springmvc.controller;

import config.SystemDefaultProperties;
import ga.rugal.food.common.CommonLogContent;
import ga.rugal.food.common.CommonMessageContent;
import ga.rugal.food.core.dao.RestaurantDao;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.RestaurantService;
import java.io.File;
import java.io.IOException;
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
@RequestMapping(value = "/restaurant")
public class RestaurantAction 
{
    
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantAction.class.getName());
    private static final File imageFolder = new File(SystemDefaultProperties.IMAGE_FOLDER);
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private RestaurantDao restaurantDao;
    
    @Autowired
    private Random random;
    
    @Autowired
    private ServletContext context;
    
    /**
     * Find a restaurant randomly from table.
     * Get restaurant information through URL /restaurant
     * 
     * @return Give successful message and restaurant data in JSON format if exists, 
     * otherwise return failed message
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Message randomRestaurant() {
        
        int total = restaurantService.countTotal();
        LOG.debug(CommonLogContent.RESTAURANT_NUMBER, total);
        
        if (0 == total) {
            
            LOG.warn(CommonLogContent.NO_RESTAURANT);
            return Message.failMessage(CommonMessageContent.RESTAURANT_NOT_FOUND);
        }
        
        Restaurant restaurant = (Restaurant) restaurantService.getPage(random.nextInt(total), 1).getList().get(0);
        return Message.successMessage(CommonMessageContent.GET_RESTAURANT, restaurant);
    }
    
    /**
     * GET image by the request restaurant id.
     * A default image will be returned if image cannot be found.
     *
     * @param rid
     * @param response
     *
     * @return Give Message object in JSON format if any exception occurs;
     *         otherwise, return the icon data in byte array format.
     *
     */
    @ResponseBody
    @RequestMapping(value = "/{rid}/image")
    public Object getImage(@PathVariable("rid") Integer rid, HttpServletResponse response)
    {
        Restaurant restaurant = restaurantDao.getByID(rid);
        byte[] data;
        File image = new File(imageFolder, SystemDefaultProperties.DEFAULT_IMAGE);
        try
        {
            if (null != restaurant)
            {
                image = new File(imageFolder, restaurant.getImage());
            }
            //prevent from loading non-exists image
            if (!image.exists())
            {
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
        return data;
    }
 
    /**
     * Get a specific restaurant by restaurant id.
     * Return restaurant not found if the restaurant id is invalid.
     * @param rid
     * @param response
     * @return Give successful message and restaurant data in JSON format if exists, 
     * otherwise return failed message
     */
    @ResponseBody
    @RequestMapping(value = "/{rid}")
    public Message getSpecificRestaurant(@PathVariable("rid") Integer rid, HttpServletResponse response)
    {
        Restaurant restaurant = restaurantDao.getByID(rid);
        //In case of invalid restaurant id
        if (null == restaurant) {
            LOG.warn(CommonLogContent.NO_RESTAURANT);
            return Message.failMessage(CommonMessageContent.RESTAURANT_NOT_FOUND);
        }
        
        return Message.successMessage(CommonMessageContent.GET_RESTAURANT, restaurant);
        
    }
        
}
