package discotecajpa.persistence;

import discotecajpa.entities.Disco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author Juan Pablo Avila (avilajuanp@gmail.com)
 */
public final class DiscoDAO {
    //inicializo mi Entity Manager afuera de los metodos, para usar siempre el mismo
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DiscotecaPU");
    private final EntityManager em = emf.createEntityManager();
        
    public void guardarDisco(Disco disco) throws Exception {
        em.getTransaction().begin();
        em.persist(disco);
        em.getTransaction().commit();
        
    }
    

    public void modificarDisco(Disco disco) throws Exception{
        em.getTransaction().begin();
        try {
            em.merge(disco);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } 
    }

    public void eliminarDisco(Disco disco) throws Exception {
        em.getTransaction().begin();
        try {
            em.remove(disco);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } 
    }

    public void eliminarDiscoXID(long id) throws Exception {
        em.getTransaction().begin();
        try {
            em.remove(buscarDiscoXId(id));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } 
    }
    
    public Disco buscarDiscoXId (long id) throws Exception {
        Disco disco = em.find(Disco.class, id);
        return disco;
    }
    
    public Disco buscarDiscoXTitulo (String titulo) throws Exception {
        Disco disco = (Disco) em.createQuery("SELECT a FROM Disco a WHERE "
                + "a.titulo LIKE :titulo").setParameter("titulo", titulo).getSingleResult();
        return disco;
    }
    
    public List<Disco> listarDiscos () throws Exception {
        List<Disco> discos = em.createQuery("SELECT e FROM Disco e").getResultList();
        return discos;
    }
}
