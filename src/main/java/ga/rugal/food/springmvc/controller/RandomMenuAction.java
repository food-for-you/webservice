/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga.rugal.food.springmvc.controller;

import ga.rugal.food.common.CommonMessageContent;
import ga.rugal.food.core.service.RestaurantService;
import ga.rugal.food.core.entity.Menu;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping(value = "/menu")
public class RandomMenuAction {
    private static final Logger LOG = LoggerFactory.getLogger(StaticResourceAction.class.getName());
    
    @Autowired
    private RestaurantService restaurantService;
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Object recommendByLocation(HttpServletResponse response) 
    {
        Menu menu = restaurantService.getMenuByLocation();
        if (null == menu)
        {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            return Message.failMessage(CommonMessageContent.MENU_NOT_FOUND);
        } else
        return Message.successMessage(CommonMessageContent.GET_MENU, menu);
    }
    
}
