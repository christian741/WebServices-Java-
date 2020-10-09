/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Excepcion;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import udec.edu.co.Pojo.ErrorWraper;


/**
 *
 * @author Christian
 */
@Provider
public class NotFoundExceptionFilter implements ExceptionMapper<NotFoundException>{

    @Override
    public Response toResponse(NotFoundException exception) {
       ErrorWraper error = new ErrorWraper(exception.getMessage(),"404" , "Not Found");
       return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
    
}
