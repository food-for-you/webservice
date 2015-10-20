package ga;

import java.io.File;
import org.apache.catalina.startup.Tomcat;

/**
 *
 * @author Rugal Bernstein
 */
public class Main
{
    public static void main(String[] args) throws Exception
    {
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
