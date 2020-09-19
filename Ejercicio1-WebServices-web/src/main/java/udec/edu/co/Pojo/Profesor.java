/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Pojo;

import java.io.Serializable;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


/**
 *
 * @author Christian y Cristian
 */

@Schema(name = "Profesor", description = "POJO que representa el profesor")
public class Profesor implements Serializable {

    private static final long serialVersionUID=-1L;
    @Schema(required = true)
    private long cedula;
    @Schema(required = true)
    private String nombre;
    @Schema(required = true)
    private String apellido;
    @Schema(required = true)
    private int edad;
    @Schema(required = true)
    private List<String> listaMateria;



    public Profesor() {

    }

    public Profesor(long cedula, String nombre, String apellido, int edad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    
    public Profesor(long cedula, String nombre, String apellido, int edad, List<String> listaMateria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.listaMateria = listaMateria;
     
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<String> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(List<String> listaMateria) {
        this.listaMateria = listaMateria;
    }

   

}
