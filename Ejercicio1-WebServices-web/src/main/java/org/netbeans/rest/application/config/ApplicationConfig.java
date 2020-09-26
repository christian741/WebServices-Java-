/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Christian
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(udec.edu.co.Controller.ProfesorController.class);
        resources.add(udec.edu.co.Excepcion.ClassNotFoundExceptionFilter.class);
        resources.add(udec.edu.co.Excepcion.ConstrainsVioletionfilter.class);
        resources.add(udec.edu.co.Excepcion.EJBExceptionFilter.class);
        resources.add(udec.edu.co.Excepcion.EOFExceptionFilter.class);
        resources.add(udec.edu.co.Excepcion.ExcepcionFilter.class);
        resources.add(udec.edu.co.Excepcion.IOExceptionFilter.class);
        resources.add(udec.edu.co.Excepcion.NotFoundExceptionFilter.class);
        resources.add(udec.edu.co.Excepcion.NulPointerExcepcionFilter.class);
        resources.add(udec.edu.co.Excepcion.ObjectNotFoundExcepcionFilter.class);
    }
    
}
