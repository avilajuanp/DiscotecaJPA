package discotecajpa.main;

import discotecajpa.entities.Artista;
import discotecajpa.entities.Disco;
import discotecajpa.entities.Sello;
import discotecajpa.services.ArtistaService;
import discotecajpa.services.DiscoService;
import discotecajpa.services.SelloService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Juan Pablo Avila (avilajuanp@gmail.com)
 */
public class Menu {
    //inicializo los servicios y el scanner
    private final Scanner leer;
    private final DiscoService discoService;
    private final ArtistaService artistaService;
    private final SelloService selloService;
    
    public Menu() {
        this.leer = new Scanner (System.in).useDelimiter("\n");
        this.discoService = new DiscoService();
        this.artistaService = new ArtistaService();
        this.selloService = new SelloService();
    }
    
    public void menu() throws Exception{
        String respuesta = null;
        do {
            System.out.println("Seleccione la opción:");
            System.out.println("=====================================");

            System.out.println(" 1- Crear Disco");
            System.out.println(" 2- Crear Artista");
            System.out.println(" 3- Crear Sello");

            System.out.println(" 4- Mostrar Discos");
            System.out.println(" 5- Mostrar Artistas");
            System.out.println(" 6- Mostrar Sellos");
            
            System.out.println(" 7- Modificar datos del Disco");
            System.out.println(" 8- Buscar Disco por ID");
            System.out.println(" 9- Buscar Disco por nombre");

            System.out.println(" 10 - Buscar Discos de un Artista");
            System.out.println(" 11 - Buscar Discos por Sello");
            
            System.out.println(" 0- Salir");
            
            //switch para cada opcion del menu
            int opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    discoService.crearDisco(cargarTitulo(), cargarFecha(),
                        cargarCopias(), cargarAlquiladas(), cargarArtista(), cargarSello());
                    break;
                case 2:
                    System.out.println("Ingrese nuevo Artista a la DB: ");
                    artistaService.crearArtista(leer.next());                    
                    break;
                case 3:
                    System.out.println("Ingrese nuevo Sello a la DB: ");
                    selloService.crearSello(leer.next());
                    break;
                case 4:
                    discoService.imprimirDiscos();
                    break;
                case 5:
                    artistaService.imprimirAtistas();
                    break;
                case 6:
                    selloService.imprimirSellos();
                    break;
                case 7:
                    modificarDatosDisco();
                    break;
                case 8:
                    System.out.println("Ingrese Id del disco:");
                    Disco disco = discoService.buscarDiscoXId(leer.nextLong());
                    System.out.printf("%-8s%-38s%-15s%-15s%-25s%-15s\n", "Código", "Título", "Fecha Lanz.", "Cant. Copias", "Artista", "Sello");
                    System.out.println(disco);
                    break;
                case 9:
                    System.out.println("Ingrese nombre del disco:");
                    disco = discoService.buscarDiscoXTitulo(leer.next());
                    System.out.printf("%-8s%-38s%-15s%-15s%-25s%-15s\n", "Código", "Título", "Fecha Lanz.", "Cant. Copias", "Artista", "Sello");
                    System.out.println(disco);
                    break;
                case 10:
                    System.out.println("Ingrese Artista a buscar:");
                    discoService.imprimirDiscosXArtista(leer.next());
                    break;
                default:
                    throw new AssertionError();
            }
            //resolucion de menu
            System.out.println("Desea hacer otra consulta? S/N");
            respuesta = leer.next();
        } while (respuesta.equalsIgnoreCase("S"));
        
    }
    
    public String cargarTitulo() throws Exception{
        String tituloDisco;
        System.out.println("Ingrese título del disco:");
        tituloDisco =leer.next();
        
        //valido q no esté cargado en la DB
        try {
            List<Disco> discos = discoService.listarDiscos();
            for (Disco disco : discos) {
                if (disco.getTitulo().equalsIgnoreCase(tituloDisco)) {
                    System.out.println("título ya cargado!");
                    cargarTitulo();
                }                
            }
        } catch (Exception exception) {
            throw exception;
        }
        return tituloDisco;
    }
    
    public Date cargarFecha() throws ParseException {
        System.out.println("Ingrese fecha de Lanzamiento (MM/DD/AAAA). Presione Enter para cada valor:");
        
        String ingresoFecha = leer.nextInt() + "/" + leer.nextInt() + "/" + leer.nextInt();
        Date fecha = new SimpleDateFormat("MM/dd/yyyy").parse(ingresoFecha);
        
        return fecha;
    }
    
    public int cargarCopias() throws Exception {
        System.out.println("Ingrese cantidad de copias del Disco:");
        int copiasDisco = leer.nextInt();
        
        return copiasDisco;        
    }
    
    public int cargarAlquiladas() throws Exception {
        System.out.println("Ingrese cantidad de copias alquiladas del Disco:");
        int copiasAlq = leer.nextInt();
        
        return copiasAlq;        
    }
    
    public Artista cargarArtista() throws Exception {
        System.out.println("Ingrese Artista del Disco:");
        Artista artista = artistaService.buscarArtistaXNombre(leer.next());
        return artista;
    }
    
    public Sello cargarSello() throws Exception {
        System.out.println("Ingrese Sello del Disco:");
        Sello sello = selloService.buscarSelloXNombre(leer.next());
        
        return sello;
    }
    
    public void modificarDatosDisco() throws Exception{
        System.out.println("Ingrese id del Disco a editar:");
        Disco disco = discoService.buscarDiscoXId(leer.nextLong());
        System.out.println("Ingrese nuevo Título:");
        String nuevoTitulo = leer.next();
        System.out.println("Ingrese nuevo Artista:");
        String nuevoArtista = leer.next();
        discoService.modificarTituloArtista(disco.getId(), nuevoTitulo, nuevoArtista);
    }
    
    
}
