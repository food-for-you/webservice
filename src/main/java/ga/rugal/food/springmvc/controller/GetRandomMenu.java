/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga.rugal.food.springmvc.controller;

import ga.rugal.food.core.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Ying Mi
 */
@Controller
public class GetRandomMenu {
    private static final Logger LOG = LoggerFactory.getLogger(StaticResourceAction.class.getName());
    
    @Autowired
    private RestaurantService restaurant;
    
    
}
