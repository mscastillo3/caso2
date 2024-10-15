import java.util.ArrayList;
import java.util.HashMap;

public class Ram {
    private int marcos;
    private int tamañoPaginas;
    private ArrayList<PaginaReal> paginasReales;
    private ArrayList<Integer> sacar = new ArrayList<Integer>();
    
    public Ram(int marcos, int tamañoPaginas) {
        this.marcos = marcos;
        this.tamañoPaginas = tamañoPaginas;
        paginasReales = new ArrayList<PaginaReal>();
    }

    public synchronized void agregarPaginaReal(PaginaReal paginaReal) {
        paginasReales.add (paginaReal) ;
    }

    public synchronized void quitarPaginaReal(PaginaReal paginaReal) {
        paginasReales.remove(paginaReal.getNumero());
    }

    public synchronized PaginaReal conseguirPaginaReal(int numero) {
        return paginasReales.get(numero);
    }



    
    public int getTamaño() { return marcos; }
    public void setTamaño(int tamaño) { this.marcos = tamaño; }

    public int getTamañoPaginas() { return tamañoPaginas; }
    public void setTamañoPaginas(int tamañoPaginas) { this.tamañoPaginas = tamañoPaginas; }

    public synchronized ArrayList<PaginaReal> getPaginasReales() {
        return paginasReales;
    }

    public synchronized void setPaginasReales(ArrayList<PaginaReal> paginasReales) {
        this.paginasReales = paginasReales;
    }

    public synchronized ArrayList<Integer> getSacar() {
        return sacar;
    }

    public synchronized void setSacar(ArrayList<Integer> prioridad) {
        this.sacar = prioridad;
    }

    public synchronized boolean estaLlena() {
        return (paginasReales.size() >= marcos);
    }


    
}
