/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Christian
 */
public class Conexion {

    private String host ="localhost";
    private String puerto ="5432";
    private String baseDatos ="";
    private String usuario ="postgres";
    private String password ="$$4172007$$";

    private Connection connection = null;
    
    public Connection ConectarBD() {
        String url = "";
        try {
            
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            url = "jdbc:postgresql://" + this.host + ":" + this.puerto + "/" + this.baseDatos;
            
            connection = DriverManager.getConnection(
                    url,
                    this.usuario, this.password);
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "conexion bien" : "conexion mal");
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error al conectar con la base de datos de PostgreSQL (" + url + "): " + sqle);
        }
        return connection;
    }
    /**
     * Cerrar Base Datos
     */
    public void cerrar(){
    	try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("No se pudo cerrar la conexi�n"); 
	}
    }
   
}
