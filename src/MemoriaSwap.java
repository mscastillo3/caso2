
import java.util.HashMap;

public class MemoriaSwap {
    private int tamaño;
    private HashMap<String, PaginaReal> paginas ;
    
    public MemoriaSwap() {
        paginas = new HashMap<String, PaginaReal>();
    }

    public void agregarPaginaReal(PaginaReal paginaReal) {
        paginas.put(String.valueOf(paginaReal.getDireccion()), paginaReal );
    }

    public void quitarPaginaReal(PaginaReal paginaReal) {
        paginas.remove(String.valueOf(paginaReal.getDireccion()));
    }

    public PaginaReal conseguirPaginaReal(int numero) {
        return paginas.get(String.valueOf(numero));
    }


    
    public int getTamaño() { return tamaño; }
    public void setTamaño(int tamaño) { this.tamaño = tamaño; }
}
