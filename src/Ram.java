import java.util.ArrayList;
import java.util.HashMap;

public class Ram {
    private int tamaño;
    private int tamañoPaginas;
    private ArrayList<PaginaReal> paginasReales;
    
    public Ram(int tamaño, int tamañoPaginas) {
        this.tamaño = tamaño;
        this.tamañoPaginas = tamañoPaginas;
        paginasReales = new ArrayList<PaginaReal>();
    }

    public void agregarPaginaReal(PaginaReal paginaReal) {
        paginasReales.add (paginaReal) ;
    }

    public void quitarPaginaReal(PaginaReal paginaReal) {
        paginasReales.remove(paginaReal.getNumero());
    }

    public PaginaReal conseguirPaginaReal(int numero) {
        return paginasReales.get(numero);
    }



    
    public int getTamaño() { return tamaño; }
    public void setTamaño(int tamaño) { this.tamaño = tamaño; }

    public int getTamañoPaginas() { return tamañoPaginas; }
    public void setTamañoPaginas(int tamañoPaginas) { this.tamañoPaginas = tamañoPaginas; }
}
