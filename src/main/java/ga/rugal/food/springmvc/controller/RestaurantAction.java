package ga.rugal.food.springmvc.controller;

import ga.rugal.food.common.CommonLogContent;
import ga.rugal.food.common.CommonMessageContent;
import ga.rugal.food.core.entity.Restaurant;
import ga.rugal.food.core.service.RestaurantService;
import java.util.Random;
import ml.rugal.sshcommon.springmvc.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    private final Random rd = new Random();

    @Autowired
    private RestaurantService restaurantService;
    
    /**
     * Find a restaurant randomly from table
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
        Restaurant restaurant = (Restaurant) restaurantService.getPage(rd.nextInt(total), 1).getList().get(0);
        return Message.successMessage(CommonMessageContent.GET_RESTAURANT, restaurant);
    }
    
}
