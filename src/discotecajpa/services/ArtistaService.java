package discotecajpa.services;

import discotecajpa.entities.Artista;
import discotecajpa.persistence.ArtistaDAO;
import java.util.List;

/**
 * 
 * @author Juan Pablo Avila (avilajuanp@gmail.com)
 */
public class ArtistaService {

    private final ArtistaDAO artistaDao = new ArtistaDAO();
    
    public void crearArtista (String nombre) throws Exception {
        Artista artista = new Artista(nombre, true);
        
        try {
            artista.setNombre(nombre);
            artista.setAlta(true);
            
            artistaDao.guardarArtista(artista);
        } catch (Exception e) {
            throw new Exception("No se pudo crear el Artista.");
        }
    }
    
    public void modificarArtista(long id, String nuevoNombre, boolean alta) throws Exception {

        try {
            //Validar datos
            if (nuevoNombre == null | nuevoNombre.trim().isEmpty()) {
                throw new Exception("Debe ingresar un nombre de Artista válido.");
            }
                        
            //genero un sello y chequo si existe ese id
            Artista artista = artistaDao.buscarArtistaXId(id);
            if (artista == null) {
                
                throw new Exception("El ID ingresado no existe en la Base de Datos.");
            }
            //cargo los nuevos datos y envío la modificacion al DAO
            artista.setNombre(nuevoNombre);
            artista.setAlta(alta);
                        
            artistaDao.modificarArtista(artista);
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarArtista (long id) throws Exception {
        
        try {
            if (id == 0) {
                throw new Exception("Debe ingresar un ID válido.");
            }
            
            Artista artista = artistaDao.buscarArtistaXId(id);
            
            if (artista == null) {
                
                throw new Exception("El ID ingresado no existe en la Base de Datos.");
            }
            
            artistaDao.eliminarArtista(artista);
            
        } catch (Exception e) {
            throw e;
        }        
    }

    public void imprimirAtistas() throws Exception {
        try {
            //instancio lista de productos a mostrar desde el DAO
            List<Artista> artistas = artistaDao.listarArtistas();
            
            if (artistas.isEmpty()) {
                throw new Exception("No existen artistas en la lista!");
            } else {
                
                System.out.println("----LISTADO DE Artistas----");
                System.out.printf("%-8s%-35s%-15s\n", "Código", "Nombre", "Alta");
                for (Artista artista : artistas) {
                    System.out.println(artista);
                }
                System.out.println("");
            }            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Artista buscarArtistaXNombre(String nombre) throws Exception {
        try {
            //Validamos
            if (nombre == null | nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del Artista a buscar.");
            }
            Artista artista = artistaDao.buscarArtistaXnombre(nombre);
            //Verificamos
            if (artista == null) {
                throw new Exception("No se encontró el artista con ese nombre.");
            }
            return artista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } return null;
    }
}
