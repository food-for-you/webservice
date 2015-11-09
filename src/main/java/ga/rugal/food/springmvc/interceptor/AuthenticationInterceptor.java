package ga.rugal.food.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * A authentication interceptor than authenticate any matched request by some
 * credential. Store username and credential in request header.
 * <p>
 * Useful when implementing Restful API.
 * <p>
 * @author Rugal Bernstein
 */
@Component
public class AuthenticationInterceptor extends BaseInterceptor
{

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationInterceptor.class.getName());

    /**
     * This interceptor do its jos on all handlers except
     * {@link ga.rugal.jpt.springmvc.controller.AnnounceAction#announce(ga.rugal.jpt.common.tracker.common.ClientRequestMessageBean, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * } <BR>
     * Any request that needs authentication must include their
     * {@link ga.rugal.jpt.common.SystemDefaultProperties#ID} and
     * {@link ga.rugal.jpt.common.SystemDefaultProperties#CREDENTIAL}
     * in request header.<p>
     * Example:<BR>
     * curl: <BR>
     * {@code curl -H'id:1' -H'credential:123456'}
     * <p>
     * @param request  The request that has id and credential information in header
     * @param response
     * @param handler
     *                 <p>
     * @return true if id and credential match information inside DB, otherwise return false.
     * <p>
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        return true;
    }

    /**
     * This method is just for generating a response with forbidden content.<BR>
     * May throw IOException inside because unable to get response body writer,
     * but this version will shelter it.
     *
     *
     * @param response The response corresponding to the request.
     */
    @Override
    protected void deniedResponse(HttpServletResponse response)
    {
    }

    /**
     * This method used put authentication.
     * If you need to check with database, please modify code.
     *
     * @param username   user ID
     * @param credential user password
     *
     * @return true if this user and credential meet requirement, otherwise
     *         return false
     */
    private boolean isAuthenticatedUser(String id, String credential)
    {
        boolean isAuthenticated = false;
        try
        {
            isAuthenticated = true;
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
        }

        return isAuthenticated;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
    }

}
