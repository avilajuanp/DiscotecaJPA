package discotecajpa.persistence;

import discotecajpa.entities.Sello;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author Juan Pablo Avila (avilajuanp@gmail.com)
 */
public final class SelloDAO {
    //inicializo mi Entity Manager afuera de los metodos, para usar siempre el mismo
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DiscotecaPU");
    private final EntityManager em = emf.createEntityManager();
        
    public void guardarSello(Sello sello) throws Exception {
        em.getTransaction().begin();
        try {
            em.persist(sello);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } 
    }

    public void modificarSello(Sello sello) throws Exception{
        em.getTransaction().begin();
        try {
            em.merge(sello);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } 
    }

    public void eliminarSello(Sello sello) throws Exception {
        em.getTransaction().begin();
        try {
            em.remove(sello);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } 
    }

    public void eliminarSelloXID(long id) throws Exception {
        em.getTransaction().begin();
        try {
            em.remove(buscarSelloXId(id));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } 
    }
    
    public Sello buscarSelloXId (long id) throws Exception {
        Sello sello = em.find(Sello.class, id);
        return sello;
    }
    
    public Sello buscarSelloXnombre (String nombre) throws Exception {
        Sello sello = (Sello) em.createQuery("SELECT a FROM Sello a WHERE "
                + "a.nombre LIKE :nombre").setParameter("nombre", nombre).getSingleResult();
        return sello;
    }
    
    public List<Sello> listarSellos () throws Exception {
        List<Sello> sellos = em.createQuery("SELECT e FROM Sello e").getResultList();
        return sellos;
    }
}
