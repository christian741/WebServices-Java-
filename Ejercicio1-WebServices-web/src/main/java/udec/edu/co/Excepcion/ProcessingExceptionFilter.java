/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Excepcion;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import udec.edu.co.Pojo.ErrorWraper;
/**
 *
 * @author Christian
 */
@Provider
public class ProcessingExceptionFilter implements ExceptionMapper<ProcessingException>{

    @Override
    public Response toResponse(ProcessingException exception) {
       ErrorWraper error = new ErrorWraper(exception.getMessage(),"500" , "INTERNAL_SERVER_ERROR");
       return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
    
}
