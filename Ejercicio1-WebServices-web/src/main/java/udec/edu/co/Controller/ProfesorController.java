/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;
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

import udec.edu.co.Pojo.ErrorWraper;
import udec.edu.co.Pojo.Profesor;
import udec.edu.co.Service.IProfesorService;


/**
 *
 * @author Christian
 */
@Stateless
@Path("/profesores")
public class ProfesorController {

    @EJB
    private IProfesorService service;


    @Path("/insertar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertarProfesor(ArrayList<Profesor> profesor) throws IOException, ObjectNotFoundException, Exception, IllegalArgumentException {

        ErrorWraper mensaje = service.insertarProfesor(profesor);
        return Response.status(Response.Status.CREATED).entity(mensaje).build();
        //201 create si esta bien
        //mal 404
        //base datos 500

    }

    @Path("/editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@Valid Profesor profesor) throws ObjectNotFoundException, ClassNotFoundException, IOException, Exception, ConstraintViolationException {
        System.out.println(profesor.getCedula() + " " + profesor.getNombre());
        ErrorWraper mensaje = service.editarProfesor(profesor);

        return Response.status(Response.Status.OK).entity(mensaje).build();
    }

    @Path("/retornarPorCedula/{cedula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarProfesorPorCedula(@PathParam("cedula") long cedula) throws IOException, ObjectNotFoundException, ClassNotFoundException, Exception {

        Profesor Profesor = service.retornarProfesorPorCedula(cedula);
        return Response.status(Response.Status.OK).entity(Profesor).build();
        //filtros vacio no encontrado 404 
        // 400 validaciones en solicitudes mal hechas
    }

    @Path("/retornarMateria/{materia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarProfesorPorMateria(@PathParam("materia") String materia) throws ObjectNotFoundException, IOException, ClassNotFoundException, Exception {

        System.out.println(materia);
        ArrayList<Profesor> lista = service.buscarProfesoresMateria(materia);
        return Response.status(Response.Status.OK).entity(lista).build();
        //filtros vacio no encontrado 404 no content

    }

    @Path("/retornarTodos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornarProfesor() throws IOException, ClassNotFoundException, ObjectNotFoundException, Exception, IllegalArgumentException, Exception {

        ArrayList<Profesor> lista = service.retornarProfesores();
        return Response.status(Response.Status.OK).entity(lista).build();
        // vacia

    }

    @Path("eliminar/{cedula}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("cedula") long cedula) throws ObjectNotFoundException, ClassNotFoundException, IOException, Exception {
        //Lógica de base de datos

        ErrorWraper error = service.eliminarProfesor(cedula);
        return Response.status(Response.Status.NO_CONTENT).entity(error).build();
        //204 no content
        //404 not found
    }

}
