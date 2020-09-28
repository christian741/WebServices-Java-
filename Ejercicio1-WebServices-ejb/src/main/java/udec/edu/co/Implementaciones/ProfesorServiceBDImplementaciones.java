/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Implementaciones;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.ObjectNotFoundException;
import udec.edu.co.BaseDatos.Conexion;
import udec.edu.co.Pojo.ErrorWraper;
import udec.edu.co.Pojo.Profesor;
import udec.edu.co.Service.IProfesorServiceBD;

/**
 *
 * @author Christian
 */
public class ProfesorServiceBDImplementaciones implements IProfesorServiceBD {
    
    private  Conexion database = new Conexion();
    
    /**
     * 
     * @param profesor
     * @return
     * @throws ObjectNotFoundException
     * @throws NullPointerException
     * @throws Exception 
     */
    @Override
    public ErrorWraper insertarProfesor(ArrayList<Profesor> profesor) throws ObjectNotFoundException, NullPointerException, Exception {

        for (int i = 0; i < profesor.size(); i++) {
            try {
                String query = "INSERT INTO usuarios (id,cedula, nombre, apellido,edad) VALUES (?, ?, ?, ?, ?);";
                PreparedStatement sentenciaP = database.ConectarBD().prepareStatement(query);
                sentenciaP.setLong(2, profesor.get(i).getCedula());
                sentenciaP.setString(2, profesor.get(i).getNombre());
                sentenciaP.setString(2, profesor.get(i).getApellido());
                sentenciaP.setInt(2, profesor.get(i).getEdad());
                sentenciaP.executeUpdate();
                sentenciaP.close();
                database.cerrar();
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public ErrorWraper editarProfesor(Profesor profesor) throws ObjectNotFoundException, ClassNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Profesor> buscarProfesoresMateria(String materias) throws ObjectNotFoundException, IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Profesor retornarProfesorPorCedula(long cedula) throws IOException, ObjectNotFoundException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Profesor> retornarProfesores() throws IOException, ClassNotFoundException, ObjectNotFoundException {
          ArrayList<Profesor> listaProfesores = new ArrayList<>();
          Profesor profesor;
        try {
            String query = "SELECT * FROM usuarios;";
            PreparedStatement sentenciaP = database.ConectarBD().prepareStatement(query);
            ResultSet resultado = sentenciaP.executeQuery();

            while (resultado.next()) {
                listaProfesores.add(profesor = new Profesor((long)resultado.getInt("cedula"),resultado.getString("nombre"), resultado.getString("apellido"), resultado.getInt("nombre")));
            }

            sentenciaP.close();
            database.cerrar();

            return listaProfesores;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listaProfesores;
    }

    @Override
    public ErrorWraper eliminarProfesor(long cedula) throws ObjectNotFoundException, ClassNotFoundException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
