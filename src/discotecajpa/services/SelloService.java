package discotecajpa.services;

import discotecajpa.entities.Sello;
import discotecajpa.persistence.SelloDAO;
import java.util.List;

/**
 * 
 * @author Juan Pablo Avila (avilajuanp@gmail.com)
 */
public class SelloService {

    private final SelloDAO selloDao = new SelloDAO();
    
    public void crearSello (String nombre) throws Exception {
        Sello sello = new Sello(nombre, true);
        
        try {
            sello.setNombre(nombre);
            sello.setAlta(true);
            
            selloDao.guardarSello(sello);
        } catch (Exception e) {
            throw new Exception("No se pudo crear el sello.");
        }
    }
    
    public void modificarSello(long id, String nuevoNombre, boolean aLta) throws Exception {

        try {
            //Validar datos
            if (nuevoNombre == null | nuevoNombre.trim().isEmpty()) {
                throw new Exception("Debe ingresar un nombre de sello válido.");
            }
                        
            //genero un sello y chequo si existe ese id
            Sello sello = selloDao.buscarSelloXId(id);
            if (sello == null) {
                
                throw new Exception("El ID ingresado no existe en la Base de Datos.");
            }
            //cargo los nuevos datos y envío la modificacion al DAO
            sello.setNombre(nuevoNombre);
            sello.setAlta(aLta);
                        
            selloDao.modificarSello(sello);
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarSello (long id) throws Exception {
        
        try {
            if (id == 0) {
                throw new Exception("Debe ingresar un ID válido.");
            }
            
            Sello sello = selloDao.buscarSelloXId(id);
            
            if (sello == null) {
                
                throw new Exception("El ID ingresado no existe en la Base de Datos.");
            }
            
            selloDao.eliminarSello(sello);
            
        } catch (Exception e) {
            throw e;
        }        
    }

    public void imprimirSellos() throws Exception {
        try {
            //instancio lista de productos a mostrar desde el DAO
            List<Sello> sellos = selloDao.listarSellos();
            
            if (sellos.isEmpty()) {
                throw new Exception("No existen sellos en la lista!");
            } else {
                
                System.out.println("----LISTADO DE Sellos Discográficos----");
                System.out.printf("%-8s%-35s%-15s\n", "Código", "Nombre", "Alta");
                for (Sello sello : sellos) {
                    System.out.println(sello);
                }
                System.out.println("");
            }            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Sello buscarSelloXNombre(String nombre) throws Exception {
        try {
            //Validamos
            if (nombre == null | nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del Sello a buscar.");
            }
            Sello sello = selloDao.buscarSelloXnombre(nombre);
            //Verificamos
            if (sello == null) {
                throw new Exception("No se econtró el sello con ese nombre.");
            }
            return sello;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } return null;
    }
}
