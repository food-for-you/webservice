package ga.rugal.food.springmvc.controller;

import config.SystemDefaultProperties;
import ga.rugal.food.common.CommonLogContent;
import ga.rugal.food.common.CommonMessageContent;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Rugal Bernstein
 */
@Controller
public class StaticResourceAction
{

    private static final File imageFolder = new File(SystemDefaultProperties.IMAGE_FOLDER);

    private static final Logger LOG = LoggerFactory.getLogger(StaticResourceAction.class.getName());

    @Autowired
    private ServletContext context;

    /**
     * GET image by the requested file name.
     *
     * @param filename full file name with extension
     * @param response
     *
     * @return Give Message object in JSON format if any exception occurs or file not found;
     *         otherwise, return the icon data in byte array format.
     *
     */
    @ResponseBody
    @RequestMapping(value = "/img/{filename:.+}")
    public Object getImage(@PathVariable("filename") String filename, HttpServletResponse response)
    {
        File image = new File(imageFolder, filename);
        LOG.info(image.getAbsolutePath());
        if (!image.exists())
        {
            LOG.info(String.format(CommonLogContent.IMAGE_NOT_FOUND, image.getName()));
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            return Message.failMessage(CommonMessageContent.IMAGE_NOT_FOUND);
        }
        byte[] data;
        try
        {
            data = FileCopyUtils.copyToByteArray(image);
        }
        catch (IOException ex)
        {
            LOG.error(String.format(CommonLogContent.ERROR_READ_IMAGE, image.getName()), ex);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            return Message.failMessage(CommonMessageContent.IMAGE_NOT_FOUND);
        }
        LOG.trace(String.format(CommonLogContent.IMAGE_LENGTH, data.length));
        response.setContentType(context.getMimeType(image.getName()));
        response.setContentLength(data.length);
        //The code below is for in browser displaying
        response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", image.getName()));
        return data;
    }
}
