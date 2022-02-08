package discotecajpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Juan Pablo Avila (avilajuanp@gmail.com)
 */
@Entity
public class Sello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    private String nombre; private boolean alta;

    public Sello() {
    }

    public Sello(String nombre, boolean alta) {
        this.nombre = nombre;
        this.alta = alta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }
    
    @Override
    public String toString() {
        return String.format("%-8s%-35s%-15s", id,nombre,alta);
    }
    
}
