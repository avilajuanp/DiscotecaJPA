package discotecajpa.services;

import discotecajpa.entities.Artista;
import discotecajpa.entities.Disco;
import discotecajpa.entities.Sello;
import discotecajpa.persistence.ArtistaDAO;
import discotecajpa.persistence.DiscoDAO;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Juan Pablo Avila (avilajuanp@gmail.com)
 */
public class DiscoService {
    //inicializo DAOs y creo DiscoService
    private final DiscoDAO discoDAO;
    private final ArtistaDAO artistaDAO;

    public DiscoService() {
        this.discoDAO = new DiscoDAO();
        this.artistaDAO = new ArtistaDAO();
    }
    
    public void crearDisco(String titulo, Date fechaLanzamiento, int cantCopias, int cantCopiasAlquiladas, 
            Artista artista, Sello sello) throws Exception{
        
        try {
            //Validar datos de carga
            if (titulo == null | titulo.trim().isEmpty()) {
                throw new Exception("Debe ingresar un título para el disco.");
            }
            if (fechaLanzamiento == null) {
                throw new Exception("Debe ingresar una fecha válida.");
            }
            if (cantCopias == 0) {
                throw new Exception("Debe ingresar al menos una copia!");
            }
            if (artista == null) {
                throw new Exception("El disco debe tener un artista!");
            }
            
            if (sello == null) {
                throw new Exception("El disco debe tener un sello!");
            }
            
            //creamos Disco nuevo
            Disco disco = new Disco();
            disco.setTitulo(titulo);
            disco.setFechaLanzamiento(fechaLanzamiento);
            disco.setCantCopias(cantCopias);
            disco.setCantCopiasAlquiladas(cantCopiasAlquiladas);
            disco.setArtista(artista);
            disco.setSello(sello);
            
            discoDAO.guardarDisco(disco); //guardo en el DAO y retorno objeto
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificarTituloArtista (long id, String nuevoTitulo, String nuevoArtista) throws Exception {
        
        try {
            Disco disco = discoDAO.buscarDiscoXId(id);
            disco.setTitulo(nuevoTitulo);
            
            //valido que el Artista Exista
            if (artistaDAO.buscarArtistaXnombre(nuevoArtista) == null) {
                throw new Exception("No se encontró el Artista indicado. Ingréselo primero a la DB.");
            }
            disco.setArtista(artistaDAO.buscarArtistaXnombre(nuevoArtista));
            
            discoDAO.modificarDisco(disco);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Disco buscarDiscoXId(long id) throws Exception {
        try {
            //Validamos
            if (id == 0) {
                throw new Exception("Debe indicar el id del disco a buscar.");
            }
            Disco disco = discoDAO.buscarDiscoXId(id);
            //Verificamos
            if (disco == null) {
                throw new Exception("No se encontró el disco con ese ID.");
            }
            return disco;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } return null;
    }
    
    public Disco buscarDiscoXTitulo(String titulo) throws Exception {
        try {
            //Validamos
            if (titulo.isEmpty()) {
                throw new Exception("Debe indicar el Título del disco a buscar.");
            }
            Disco disco = discoDAO.buscarDiscoXTitulo(titulo);
            //Verificamos
            if (disco == null) {
                throw new Exception("No se econtró un disco con ese título.");
            }
            return disco;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } return null;
    }
    
    public List<Disco> listarDiscos() throws Exception {
        try {
            List<Disco> discos = discoDAO.listarDiscos();
            return discos;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirDiscos() throws Exception {
        try {
            
            List<Disco> discos = listarDiscos();
            //una vez llena la lista, valido y mando a consola
            if (discos.isEmpty()) {
                throw new Exception("No existen discos en la DB.");
            } else {
                
                System.out.println("----LISTA DE DISCOS EN LA DB----");
                System.out.printf("%-8s%-38s%-15s%-15s%-25s%-15s\n", "Código", "Título", "Fecha Lanz.", "Cant. Copias", "Artista", "Sello");
                for (Disco disco : discos) {
                    System.out.println(disco.toString());
                }
                System.out.println("");
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void eliminarDiscoXId (long id) throws Exception {
        
        try {
            if (id == 0) {
                throw new Exception("Debe ingresar un ID válido.");
            }
            
            Disco disco = discoDAO.buscarDiscoXId(id);
            
            if (disco == null) {
                
                throw new Exception("El ID ingresado no existe en la Base de Datos.");
            }
            
            discoDAO.eliminarDiscoXID(id);
            
        } catch (Exception e) {
            throw e;
        }
        
    }
}
