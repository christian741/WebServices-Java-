/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Excepcion;

import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import udec.edu.co.Pojo.ErrorWraper;


/**
 *
 * @author Christian
 */
@Provider
public class EJBExceptionFilter implements ExceptionMapper<EJBException>{
 private String error404 ="HTTP 404 Not Found";
    @Override
    public Response toResponse(EJBException exception) {
        
        ErrorWraper error = new ErrorWraper(exception.getMessage(),"415" , "UNSUPPORTED_MEDIA_TYPE");
        if(this.error404.equals(exception.getMessage())){
            error = new ErrorWraper(exception.getMessage(),"404" , "Not Found");
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
       }
       return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(error).build();
    }
    
    
}
