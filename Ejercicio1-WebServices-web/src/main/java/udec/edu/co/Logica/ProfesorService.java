/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Logica;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ObjectNotFoundException;
import javax.validation.ConstraintViolationException;
import udec.edu.co.Excepcion.ConstrainsVioletionfilter;
import udec.edu.co.Pojo.ErrorWraper;
import udec.edu.co.Pojo.Profesor;

/**
 *
 * @author Christian
 */
/**
 *
 */
public class ProfesorService {

    private String ruta = "C:/Users/Christian/Desktop/fichero.dat";
    private File fichero = new File(ruta);

    public ErrorWraper insertarProfesor(ArrayList<Profesor> profesor) throws ObjectNotFoundException, NullPointerException, Exception {

        boolean validacion = true;
        boolean datos = true;
        //devuelve todos
        if (profesor.size() > 10) {
            throw new ObjectNotFoundException("Mas de 10 objetos insertados");
        }
        if (profesor.size() <= 0 || profesor == null) {
            throw new ObjectNotFoundException("Viene Vacio");
        }
        if (fichero.exists()) {
            System.out.println("si existe---------------------------");
             for (int j = 0; j < profesor.size(); j++) {
                if (profesor.get(j).getCedula() == 0 || profesor.get(j).getNombre() == null || profesor.get(j).getApellido() == null || profesor.get(j).getEdad() == 0 || profesor.get(j).getListaMateria().isEmpty()) {
                    System.out.println(profesor.get(j).getCedula());
                    System.out.println(profesor.get(j).getNombre());
                    System.out.println(profesor.get(j).getApellido());
                    System.out.println(profesor.get(j).getEdad());
                    System.out.println(profesor.get(j).getListaMateria());
                    datos = false;
                }
            }
            ArrayList<Profesor> busqueda = retornarProfesores();
            //no mas de 10 objetos
            if (profesor.size() > 10) {
                throw new ObjectNotFoundException("Mas de 10 objetos insertados");
            }

            for (int i = 0; i < busqueda.size(); i++) {
                for (int j = 0; j < profesor.size(); j++) {
                    if (busqueda.get(i).getCedula() == profesor.get(j).getCedula()) {
                        validacion = false;
                    }
                }
            }

           

            if (datos == false) {
                throw new ConstraintViolationException("Datos erroneos", null);
            }

        } else {
            validacion = true;
        }
        if (validacion == false) {
            throw new NullPointerException("Error hay una cedula repetida");
        } else {
            try {
                if (!fichero.exists()) {// A partir del objeto File creamos el fichero físicamente
                    if (fichero.createNewFile()) {
                        System.out.println("El fichero se ha creado correctamente");
                    } else {
                        System.out.println("No ha podido ser creado el fichero");
                    }
                }
                if (fichero.exists()) {

                    ArrayList<Profesor> arrayProfesores = new ArrayList<>();

                    try {
                        //ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(ruta));
                        InputStream file = new FileInputStream(ruta);
                        InputStream buffer = new BufferedInputStream(file);
                        if (file.read() == 0) {
                            /*ObjectInput input = new ObjectInputStream(buffer);

                             try {
                             System.out.println("aqui llegue2");
                             arrayProfesores = (ArrayList<Profesor>) input.readObject();
                             System.out.println("aqui llegue3");
                             if (arrayProfesores != null) {
                             for (Profesor arrayProfesore : arrayProfesores) {
                             profesor.add(arrayProfesore);
                             }

                             }
                             input.close();
                             } catch (ClassNotFoundException ex) {
                             System.out.println(ex);
                             throw new ClassNotFoundException(ex + "Error no se encontro clase en el archivo");
                             }*/
                        } else {
                            //if (file.read() == 0) {
                            System.out.println("aqui");
                            ObjectInput input = new ObjectInputStream(new FileInputStream(fichero));

                            try {

                                arrayProfesores = (ArrayList<Profesor>) input.readObject();

                                if (arrayProfesores != null) {
                                    for (Profesor arrayProfesore : arrayProfesores) {
                                        profesor.add(arrayProfesore);
                                    }

                                }
                                input.close();
                            } catch (ClassNotFoundException ex) {
                                System.out.println(ex);
                                throw new ClassNotFoundException(ex + "Error no se encontro clase en el archivo");
                            }
                            //}
                        }

                    } catch (EOFException ex) {
                        System.out.println(ex);
                        throw new EOFException(ex + "No se cual es etsa expcecion");
                    } catch (IOException ex) {
                        System.out.println(ex);
                        throw new IOException(ex + "Error en reocrrido de archivo");
                    }

                    OutputStream file = new FileOutputStream(ruta);
                    OutputStream buffer = new BufferedOutputStream(file);
                    ObjectOutput output = new ObjectOutputStream(buffer);
                    //ObjectOutputStream escribirFichero = new ObjectOutputStream(new FileOutputStream(ruta));
                    output.writeObject(profesor);
                    output.close();
                    //return new ErrorWraper("Creado Satisfactoriamente", "201", "Created");
                }
            } catch (IOException ex) {
                System.out.println(ex);
                throw new IOException(ex + "Error en crear el archivo");
            }
        }
        return new ErrorWraper("Creado Satisfactoriamente", "201", "Created");
    }

    public ErrorWraper editarProfesor(Profesor profesor) throws ObjectNotFoundException, ClassNotFoundException, IOException {

        ArrayList<Profesor> arrayProfesores = new ArrayList<>();

        if (this.validarCedula((long) profesor.getCedula()) == false) {
            throw new ObjectNotFoundException("No se encontro la cedula");
        } else {
            if (fichero.exists()) {
                try {
                    //ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(ruta));
                    InputStream file = new FileInputStream(ruta);
                    InputStream buffer = new BufferedInputStream(file);
                    ObjectInput input = new ObjectInputStream(buffer);

                    try {

                        arrayProfesores = (ArrayList<Profesor>) input.readObject();

                    } catch (ClassNotFoundException ex) {
                        throw new ClassNotFoundException(ex + "Error no se encontro clase en el archivo");
                    }
                    input.close();
                    Profesor editado = new Profesor();
                    boolean encontrado = false;
                    for (Profesor arrayProfesore : arrayProfesores) {
                        if (profesor.getCedula() == arrayProfesore.getCedula()) {
                            editado = arrayProfesore;
                            encontrado = true;
                            System.out.println(editado.getCedula());

                        }
                    }
                    if (encontrado == true) {
                        arrayProfesores.remove(editado);
                        arrayProfesores.add(profesor);
                    }
                    this.reconstruirArchivo(arrayProfesores);

                } catch (IOException ex) {
                    System.out.println("error" + ex);
                    throw new IOException(ex + "Error en crear el archivo");
                }

            }
        }
        return new ErrorWraper("Modificado Satisfactoriamente", "200", "OK");
    }

    public ArrayList<Profesor> retornarProfesores() throws IOException, ClassNotFoundException, ObjectNotFoundException {
        ArrayList<Profesor> arrayProfesores = new ArrayList<>();
        if (fichero.exists()) {
            if (fichero.length() == 0) {
                throw new ObjectNotFoundException("No se encontro Archivo");
            }
            try {
                //ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(ruta));
                InputStream file = new FileInputStream(ruta);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);

                try {

                    arrayProfesores = (ArrayList<Profesor>) input.readObject();

                } catch (ClassNotFoundException ex) {
                    System.out.println("error al buscar en ex");
                    throw new ClassNotFoundException(ex + "Error no se encontro clase en el archivo");
                }
                input.close();

            } catch (IOException ex) {
                System.out.println("error" + ex);
                throw new IOException(ex + "Error en crear el archivo");
            }

        } else {
            throw new ObjectNotFoundException("No se encontro Archivo");
        }

        return arrayProfesores;
    }

    public Profesor retornarProfesorPorCedula(long cedula) throws IOException, ObjectNotFoundException, ClassNotFoundException {
        ArrayList<Profesor> arrayProfesores = new ArrayList<>();
        Profesor profesor = new Profesor();
        if (fichero.exists()) {
            if (fichero.length() == 0) {
                throw new ObjectNotFoundException("No se encontro  datos en el Archivo");
            }
            try {
                //ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(ruta));
                InputStream file = new FileInputStream(ruta);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);

                try {

                    arrayProfesores = (ArrayList<Profesor>) input.readObject();

                } catch (ClassNotFoundException ex) {
                    System.out.println("error al buscar en ex" + ex);
                    throw new ClassNotFoundException(ex + "Error no se encontro clase en el archivo");
                }
                input.close();
                boolean validar = false;
                for (Profesor arrayProfesore : arrayProfesores) {
                    if (arrayProfesore.getCedula() == cedula) {
                        validar = true;
                        return arrayProfesore;
                    }
                }
                if (validar == false) {
                    throw new ObjectNotFoundException("No se encontro la cedula");
                }

            } catch (IOException ex) {
                System.out.println("error" + ex);
                throw new IOException(ex + "Error en crear el archivo");
            }

        } else {
            throw new ObjectNotFoundException("No se encontro Archivo");
        }

        return profesor;
    }

    public ArrayList<Profesor> buscarProfesoresMateria(String materias) throws ObjectNotFoundException, IOException, ClassNotFoundException {
        String accion = "no realizado";
        ArrayList<Profesor> arrayProfesores = new ArrayList<>();
        ArrayList<Profesor> listaProfesor = new ArrayList<>();
        if (fichero.exists()) {
            if (fichero.length() == 0) {
                throw new ObjectNotFoundException("No se encontro Archivo");
            }
            try {
                //ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(ruta));
                InputStream file = new FileInputStream(ruta);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);

                try {

                    arrayProfesores = (ArrayList<Profesor>) input.readObject();

                } catch (ClassNotFoundException ex) {
                    System.out.println("error al buscar en ex" + ex);
                    throw new ClassNotFoundException(ex + "Error no se encontro clase en el archivo");
                }
                input.close();

                for (Profesor arrayProfesore : arrayProfesores) {

                    if (!arrayProfesore.getListaMateria().isEmpty()) {

                        for (int i = 0; i < arrayProfesore.getListaMateria().size(); i++) {

                            if (arrayProfesore.getListaMateria().get(i).equals(materias)) {

                                listaProfesor.add(arrayProfesore);
                            }
                        }
                    }

                }

                if (listaProfesor.isEmpty()) {
                    throw new ObjectNotFoundException("No se encontraron Profesores con esta materia");
                }

            } catch (IOException ex) {
                System.out.println("error" + ex);
                throw new IOException(ex + "Error en crear el archivo");
            }

        } else {
            throw new ObjectNotFoundException("No se encontro Archivo");
        }

        return listaProfesor;
    }

    public ErrorWraper eliminarProfesor(long cedula) throws ObjectNotFoundException, ClassNotFoundException, IOException {
        String accion = "no realizado";
        ArrayList<Profesor> arrayProfesores = new ArrayList<>();
        Profesor profesor = new Profesor();
        if (this.validarCedula(cedula) == false) {
            System.out.println("no se encokntro la cedula");
            throw new ObjectNotFoundException("No se encontro la cedula");
        } else {
            if (fichero.exists()) {
                if (fichero.length() == 0) {
                    throw new ObjectNotFoundException("No se encontro Archivo");
                }
                try {
                    //ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(ruta));
                    InputStream file = new FileInputStream(ruta);
                    InputStream buffer = new BufferedInputStream(file);
                    ObjectInput input = new ObjectInputStream(buffer);

                    try {

                        arrayProfesores = (ArrayList<Profesor>) input.readObject();

                    } catch (ClassNotFoundException ex) {
                        System.out.println("error al buscar en ex" + ex);
                        throw new ClassNotFoundException(ex + "Error no se encontro clase en el archivo");
                    }
                    input.close();
                    boolean encontrado = false;
                    Profesor eliminado = new Profesor();
                    for (Profesor arrayProfesore : arrayProfesores) {
                        if (arrayProfesore.getCedula() == cedula) {
                            encontrado = true;
                            System.out.println("encontrado");
                            eliminado = arrayProfesore;

                        }
                    }
                    if (encontrado == true) {
                        arrayProfesores.remove(eliminado);
                    }
                    if (encontrado == false) {
                        throw new ObjectNotFoundException("No se encontro el Profesor");
                    }
                    System.out.println("estoy aqui eliminar");
                    if (this.reconstruirArchivo(arrayProfesores)) {
                        return new ErrorWraper("Eliminado satisfactoriamente", "204", "No content");
                    }
                    System.out.println("me fui");

                } catch (IOException ex) {
                    System.out.println("error" + ex);
                    throw new IOException(ex + "Error en crear el archivo");
                }

            }
        }
        return new ErrorWraper("Eliminado satisfactoriamente", "204", "No content");

    }

    private boolean reconstruirArchivo(ArrayList<Profesor> lista) {
        boolean cambiar = false;
        if (fichero.exists()) {
            fichero.delete();
        }
        try {
            // A partir del objeto File creamos el fichero físicamente
            if (fichero.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente");
            } else {
                System.out.println("No ha podido ser creado el fichero");
            }

            if (fichero.exists()) {
                System.out.println("cambiando");

                OutputStream file = new FileOutputStream(ruta);
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
                //ObjectOutputStream escribirFichero = new ObjectOutputStream(new FileOutputStream(ruta));
                output.writeObject(lista);
                output.close();
                cambiar = true;
            } else {
                System.out.println("no cambio");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);

        }
        cambiar = true;
        return cambiar;

    }

    private boolean validarCedula(long cedula) throws ObjectNotFoundException {
        boolean cedulaEncontrada = false;
        ArrayList<Profesor> arrayProfesores = new ArrayList<>();
        Profesor profesor = new Profesor();
        if (fichero.exists()) {
            try {
                //ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(ruta));
                InputStream file = new FileInputStream(ruta);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);

                try {

                    arrayProfesores = (ArrayList<Profesor>) input.readObject();

                } catch (ClassNotFoundException ex) {
                    System.out.println("error al buscar en ex");
                }
                input.close();

                for (Profesor arrayProfesore : arrayProfesores) {
                    if (arrayProfesore.getCedula() == cedula) {
                        cedulaEncontrada = true;
                    }
                }

            } catch (IOException ex) {
                System.out.println("error" + ex);
            }

        } else {
            throw new ObjectNotFoundException("No se encontro Archivo");
        }

        return cedulaEncontrada;
    }

}
