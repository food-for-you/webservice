package ga.rugal.food.springmvc.controller;

import ga.rugal.food.common.CommonLogContent;
import ga.rugal.food.common.CommonMessageContent;
import ga.rugal.food.core.entity.Menu;
import ga.rugal.food.core.service.MenuService;
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
@RequestMapping(value = "/menu")
public class MenuAction
{

    private static final Logger LOG = LoggerFactory.getLogger(MenuAction.class.getName());

    private final Random random = new Random();

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
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
}
