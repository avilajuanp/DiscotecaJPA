package discotecajpa.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Juan Pablo Avila (avilajuanp@gmail.com)
 */
public class MainJPA {

    public static void main(String[] args) throws Exception {
        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DiscotecaPU");
//        EntityManager em = emf.createEntityManager();
        
        Menu nuevoMenu = new Menu();
        nuevoMenu.menu();
    }
    
}
