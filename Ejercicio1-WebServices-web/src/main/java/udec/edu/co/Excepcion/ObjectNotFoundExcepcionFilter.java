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
        private String error400 ="HTTP 400 Bad Request";
    @Override
    public Response toResponse(ObjectNotFoundException exception) {
       ErrorWraper error = new ErrorWraper(exception.getMessage(),"404" , "NOT_FOUND");
        if(this.error400.equals(exception.getMessage())){
            error = new ErrorWraper(exception.getMessage(),"400" , "BAD REQUEST");
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
       }
       return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
    
}
