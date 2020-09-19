/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Excepcion;

import javax.ejb.ObjectNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import udec.edu.co.Pojo.ErrorWraper;

/**
 *
 * @author Christian
 */
@Provider
public class ObjectNotFoundExcepcionFilter implements ExceptionMapper<ObjectNotFoundException>{

    @Override
    public Response toResponse(ObjectNotFoundException exception) {
       ErrorWraper error = new ErrorWraper(exception.getMessage(),"404" , "NOT_FOUND");
       return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
    
}
