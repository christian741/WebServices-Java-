/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Excepcion;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import udec.edu.co.Pojo.ErrorWraper;

/**
 *
 * @author Christian
 */
@Provider
public class ConstrainsVioletionfilter implements ExceptionMapper<ConstraintViolationException> {
     
    private String errorInsertar ="Datos erroneos";
    @Override
    public Response toResponse(ConstraintViolationException exception) {
         ErrorWraper error = new ErrorWraper("", "400", "BAD REQUEST");
         System.out.println("asdasdsa"+exception);
         if (exception.getMessage()!=null) {
             
            if(exception.getMessage().equals(this.errorInsertar)){
             error = new ErrorWraper("Error en sus parametros","400" , "BAD REQUEST");
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
            }
        }
          error = new ErrorWraper(this.prepareMensaje(exception), "400", "BAD REQUEST");
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
    
    private String prepareMensaje (ConstraintViolationException ex){
        String mensaje ="";
        for (ConstraintViolation <?> nose : ex.getConstraintViolations()) {
          mensaje+= nose.getPropertyPath() + " "+ nose.getMessage();
        }
        return mensaje;
    }

}
