/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Service;

import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import udec.edu.co.Pojo.ErrorWraper;

import udec.edu.co.Pojo.Profesor;

/**
 *
 * @author Christian
 */
@Local
public interface IProfesorService {
    public ErrorWraper insertarProfesor(ArrayList<Profesor> profesor) throws ObjectNotFoundException, NullPointerException, Exception;
    public ErrorWraper editarProfesor(Profesor profesor) throws ObjectNotFoundException, ClassNotFoundException, IOException;
    public ArrayList<Profesor> buscarProfesoresMateria(String materias) throws ObjectNotFoundException, IOException, ClassNotFoundException ;
    public Profesor retornarProfesorPorCedula(long cedula) throws IOException, ObjectNotFoundException, ClassNotFoundException;
    public ArrayList<Profesor> retornarProfesores() throws IOException, ClassNotFoundException, ObjectNotFoundException;
    public ErrorWraper eliminarProfesor(long cedula) throws ObjectNotFoundException, ClassNotFoundException, IOException;

    
}
