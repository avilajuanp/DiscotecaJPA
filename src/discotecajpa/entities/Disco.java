package discotecajpa.entities;

import java.io.PrintStream;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Juan Pablo Avila (avilajuanp@gmail.com)
 */
//mapeo JPA
@Entity
public class Disco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
    @Column(unique = true)
    private String titulo; 
    @Temporal(TemporalType.DATE)
    private Date fechaLanzamiento;
    private int cantCopias; private int cantCopiasAlquiladas; private int cantCopiasRestantes;
    @ManyToOne//(cascade = CascadeType.PERSIST) no funciona con estoooo
    private Artista artista; 
    @ManyToOne//(cascade = CascadeType.PERSIST)
    private Sello sello;

    public Disco() {
    }

    public Disco (String titulo, Date fechaLanzamiento, int cantCopias, int cantCopiasAlquiladas, 
            Artista artista, Sello sello) {
        this.titulo = titulo; this.fechaLanzamiento = fechaLanzamiento;
        this.cantCopias = cantCopias; this.cantCopiasAlquiladas = cantCopiasAlquiladas;
        cantCopiasRestantes = cantCopias-cantCopiasAlquiladas;
        this.artista = artista; this.sello = sello;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getCantCopias() {
        return cantCopias;
    }

    public void setCantCopias(int cantCopias) {
        this.cantCopias = cantCopias;
    }

    public int getCantCopiasAlquiladas() {
        return cantCopiasAlquiladas;
    }

    public void setCantCopiasAlquiladas(int cantCopiasAlquiladas) {
        this.cantCopiasAlquiladas = cantCopiasAlquiladas;
    }

    public int getCantCopiasRestantes() {
        return cantCopiasRestantes;
    }

    public void setCantCopiasRestantes(int cantCopiasRestantes) {
        this.cantCopiasRestantes = cantCopiasRestantes;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Sello getSello() {
        return sello;
    }

    public void setSello(Sello sello) {
        this.sello = sello;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-38s%-15tF%-15s%-25s%-15s\n", id,titulo,fechaLanzamiento,cantCopias,artista.getNombre(),sello.getNombre());
    }        
}
