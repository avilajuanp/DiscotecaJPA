package discotecajpa.persistence;

import discotecajpa.entities.Artista;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author Juan Pablo Avila (avilajuanp@gmail.com)
 */
public final class ArtistaDAO {
    //inicializo mi Entity Manager afuera de los metodos, para usar siempre el mismo
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DiscotecaPU");
    private final EntityManager em = emf.createEntityManager();
        
    public void guardarArtista(Artista artista) throws Exception {
        em.getTransaction().begin();
        try {
            em.persist(artista);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } 
    }

    public void modificarArtista(Artista artista) throws Exception{
        em.getTransaction().begin();
        try {
            em.merge(artista);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } 
    }

    public void eliminarArtista(Artista artista) throws Exception {
        em.getTransaction().begin();
        try {
            em.remove(artista);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } 
    }
    
    public Artista buscarArtistaXId (long id) throws Exception {
        Artista artista = em.find(Artista.class, id);
        return artista;
    }
    
    public Artista buscarArtistaXnombre (String nombre) throws Exception {
        Artista artista = new Artista();
        try {
            artista = (Artista) em.createQuery("SELECT d FROM Artista d "
                    + "WHERE d.nombre LIKE :nombre")
                    .setParameter("nombre", nombre).getSingleResult();
        } catch (Exception e) {
            throw new Exception("No existe el artista!");
        }
            return artista;
    }
    
    public List<Artista> listarArtistas () throws Exception {
        List<Artista> artistas = em.createQuery("SELECT a FROM Artista a").getResultList();
        return artistas;
    }
}
