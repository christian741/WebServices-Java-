/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udec.edu.co.Logica;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import udec.edu.co.Pojo.Profesor;

/**
 *
 * @author Christian
 */
public class ProfesorService {

    private String ruta = "C:/Users/Christian/Desktop/fichero.dat";
    private File fichero = new File(ruta);

    public void insertarProfesor(ArrayList<Profesor> profesor) {
        //Profesor maestro = new Profesor(profesor.getCedula(), profesor.getNombre(), profesor.getApellido(), profesor.getEdad());

        try {
            // A partir del objeto File creamos el fichero físicamente
            if (fichero.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente");
            } else {
                System.out.println("No ha podido ser creado el fichero");
            }

            if (fichero.exists()) {

                ArrayList<Profesor> arrayProfesores = new ArrayList<>();

                try {
                    //ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(ruta));
                    InputStream file = new FileInputStream(ruta);
                    InputStream buffer = new BufferedInputStream(file);
                    ObjectInput input = new ObjectInputStream(buffer);

                    try {

                        arrayProfesores = (ArrayList<Profesor>) input.readObject();

                        if (arrayProfesores != null) {
                            for (Profesor arrayProfesore : arrayProfesores) {
                                profesor.add(arrayProfesore);
                            }

                        }
                    } catch (ClassNotFoundException ex) {
                        System.out.println("error al buscar en ex");
                    }
                    input.close();
                } catch (IOException ex) {
                    System.out.println("error" + ex);
                }

                OutputStream file = new FileOutputStream(ruta);
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
                //ObjectOutputStream escribirFichero = new ObjectOutputStream(new FileOutputStream(ruta));
                output.writeObject(profesor);
                output.close();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void editarProfesor(Profesor profesor) {

        ArrayList<Profesor> arrayProfesores = new ArrayList<>();

        if (this.validarCedula((long) profesor.getCedula()) == false) {
            System.out.println("no se encokntro la cedula");
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
                        System.out.println("error al buscar en ex");
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
                }

            }
        }
    }

    public ArrayList<Profesor> retornarProfesores() {
        ArrayList<Profesor> arrayProfesores = new ArrayList<>();
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

            } catch (IOException ex) {
                System.out.println("error" + ex);
            }

        }

        return arrayProfesores;
    }

    public Profesor retornarProfesorPorCedula(long cedula) {
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
                boolean validar;
                for (Profesor arrayProfesore : arrayProfesores) {
                    if (arrayProfesore.getCedula() == cedula) {
                        return arrayProfesore;
                    }
                }
                return null;

            } catch (IOException ex) {
                System.out.println("error" + ex);
            }

        }

        return profesor;
    }

    public ArrayList<Profesor> buscarProfesoresMateria(String materias) {
        String accion = "no realizado";
        ArrayList<Profesor> arrayProfesores = new ArrayList<>();
        ArrayList<Profesor> listaProfesor = new ArrayList<>();
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

                    if (!arrayProfesore.getListaMateria().isEmpty()) {

                        for (int i = 0; i < arrayProfesore.getListaMateria().size(); i++) {

                            if (arrayProfesore.getListaMateria().get(i).equals(materias)) {

                                listaProfesor.add(arrayProfesore);
                            }
                        }
                    }

                }

            } catch (IOException ex) {
                System.out.println("error" + ex);
            }

        }

        return listaProfesor;
    }

    public String eliminarProfesor(long cedula) {
        String accion = "no realizado";
        ArrayList<Profesor> arrayProfesores = new ArrayList<>();
        Profesor profesor = new Profesor();
        if (this.validarCedula(cedula) == false) {
            System.out.println("no se encokntro la cedula");
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
                        System.out.println("error al buscar en ex");
                    }
                    input.close();
                    boolean encontrado = false;
                    Profesor eliminado = new Profesor();
                    for (Profesor arrayProfesore : arrayProfesores) {
                        if (arrayProfesore.getCedula() == cedula) {
                            accion = " realizado";
                            encontrado = true;
                            System.out.println("encontrado");
                            eliminado = arrayProfesore;

                        }
                    }
                    if (encontrado == true) {
                        arrayProfesores.remove(eliminado);
                    }
                    this.reconstruirArchivo(arrayProfesores);

                } catch (IOException ex) {
                    System.out.println("error" + ex);
                }

            }
        }
        return accion;
    }

    private void reconstruirArchivo(ArrayList<Profesor> lista) {
        boolean cambiar = false;

        fichero.delete();
        try {
            // A partir del objeto File creamos el fichero físicamente
            if (fichero.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente");
            } else {
                System.out.println("No ha podido ser creado el fichero");
            }

            if (fichero.exists()) {

                OutputStream file = new FileOutputStream(ruta);
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
                //ObjectOutputStream escribirFichero = new ObjectOutputStream(new FileOutputStream(ruta));
                output.writeObject(lista);
                output.close();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

    }

    private boolean validarCedula(long cedula) {
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

        }

        return cedulaEncontrada;
    }

}
