/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.SwaggerDoc;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


/**
 *
 * @author Christian
 */
public class SwaggerConfigurationServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setBasePath("/SwaggerBlogPostWS/api/v1");
        beanConfig.setHost("localhost:8080");
        beanConfig.setTitle("Swagger Blog Post Web Services Documentation");
        beanConfig.setResourcePackage("com.blogpost.swaggerblogpostws.controller");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setVersion("1.0");
    }
}
