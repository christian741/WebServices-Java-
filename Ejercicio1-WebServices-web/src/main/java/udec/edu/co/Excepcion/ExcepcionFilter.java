/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Excepcion;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import udec.edu.co.Pojo.ErrorWraper;


/**
 *
 * @author Christian
 */
@Provider
public class ExcepcionFilter implements ExceptionMapper<Exception>{

    private String error415 ="HTTP 415 Unsupported Media Type";
    private String error404 ="HTTP 404 Not Found";
    private String error405 ="HTTP 405 Method Not Allowed";
    private String error400 ="HTTP 400 Bad Request";
    @Override
    public Response toResponse(Exception exception) {
       ErrorWraper error = new ErrorWraper(exception.getMessage(),"500" , "INTERNAL_SERVER_ERROR");
        System.out.println(exception);
       if(this.error415.equals(exception.getMessage())){
            error = new ErrorWraper(exception.getMessage(),"415" , "Unsupported Media Type");
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(error).build();
       }
        if(this.error404.equals(exception.getMessage())){
            error = new ErrorWraper(exception.getMessage(),"404" , "Not Found");
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
       }
        if(this.error405.equals(exception.getMessage())){
            error = new ErrorWraper(exception.getMessage(),"405" , "Method Not Allowed");
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(error).build();
       }
        if(this.error400.equals(exception.getMessage())){
            error = new ErrorWraper(exception.getMessage(),"400" , "BAD REQUEST");
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
       }
       return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
    
    
    
}
