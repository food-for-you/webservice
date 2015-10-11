package ga.rugal.food.springmvc.controller;

import ml.rugal.sshcommon.springmvc.controller.BaseExceptionAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 *
 *
 * @author Rugal Bernstein
 */
@ControllerAdvice
@Controller
public class ExceptionAction extends BaseExceptionAction
{

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionAction.class.getName());

}
