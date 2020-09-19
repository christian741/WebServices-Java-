/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import udec.edu.co.Logica.ProfesorService;
import udec.edu.co.Pojo.Profesor;

/**
 *
 * @author Christian
 */
@Stateless
@Path("/profesores")
public class ProfesorController {

    @Path("/insertar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarProfesor(ArrayList<Profesor> profesor) throws IOException {
        ProfesorService service = new ProfesorService();
        service.insertarProfesor(profesor);
        return Response.status(Response.Status.CREATED).build();
        //201 create si esta bien
        //mal 404
        //base datos 500
    }

    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(Profesor profesor) {
        System.out.println(profesor.getCedula() + " " + profesor.getNombre());
        ProfesorService service = new ProfesorService();
        service.editarProfesor(profesor);
        return Response.status(Response.Status.OK).build();
    }

    @Path("/retornarPorCedula/{cedula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarProfesorPorCedula(@PathParam("cedula") long cedula) {

        ProfesorService service = new ProfesorService();
        Profesor Profesor = service.retornarProfesorPorCedula(cedula);
        return Response.status(Response.Status.OK).entity(Profesor).build();
        //filtros vacio no encontrado 404 no content
        // 400 validaciones en solicitudes mal hechas
    }

    @Path("/retornarMateria/{materia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarProfesorPorMateria(@PathParam("materia") String materia) {

        ProfesorService service = new ProfesorService();
         System.out.println(materia);
        ArrayList<Profesor> lista = service.buscarProfesoresMateria(materia);
        return Response.status(Response.Status.OK).entity(lista).build();
        //filtros vacio no encontrado 404 no content

    }

    @Path("/retornarTodos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarProfesorPorCedula() {

        ProfesorService service = new ProfesorService();
        ArrayList<Profesor> lista = service.retornarProfesores();
        return Response.status(Response.Status.OK).entity(lista).build();
        // vacia 204 no content

    }


    @Path("eliminar/{cedula}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("cedula") long cedula) {
        //Lógica de base de datos
        ProfesorService service = new ProfesorService();
        service.eliminarProfesor(cedula);
        return Response.status(Response.Status.NO_CONTENT).build();
        //204 no content
        //404 not found
    }





}
 