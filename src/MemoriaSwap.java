
import java.util.HashMap;

public class MemoriaSwap {
    private int tamaño;
    private HashMap<String, PaginaReal> paginas ;
    
    public MemoriaSwap(int tamaño) {
        this.tamaño = tamaño;
        paginas = new HashMap<String, PaginaReal>();
    }

    public void agregarPaginaReal(PaginaReal paginaReal) {
        paginas.put(String.valueOf(paginaReal.getDireccion()), paginaReal );
    }

    public void quitarPaginaReal(PaginaReal paginaReal) {
        paginas.remove(String.valueOf(paginaReal.getDireccion()));
    }


    
    public int getTamaño() { return tamaño; }
    public void setTamaño(int tamaño) { this.tamaño = tamaño; }
}
