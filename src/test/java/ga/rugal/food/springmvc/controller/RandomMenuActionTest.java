/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga.rugal.food.springmvc.controller;

import ga.rugal.ControllerClientSideTestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Ying Mi
 */
public class RandomMenuActionTest extends ControllerClientSideTestBase{
    
    public RandomMenuActionTest() {
        
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
    public void testRecommendByLocation() throws Exception {
        System.out.println("recommend menu by location");
        this.mockMvc.perform(get("/menu")
            //.contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andDo(print());
            
    }
    
}
