import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MemoriaVirtual {
    private HashMap<Integer, PaginaVirtual> paginasVirtuales;

    public MemoriaVirtual() {
        this.paginasVirtuales = new  HashMap<Integer, PaginaVirtual>();
    }

    public void definirPaginaVirtual() {
        
    }

    public void agregarPaginaVirtual(PaginaVirtual paginaVirtual) {
        paginasVirtuales.put(paginaVirtual.getNumereDePagina(), paginaVirtual);
    }

    public PaginaVirtual  RecuperarPagina(int numero){
        return paginasVirtuales.get(numero);

    }

    
    
}
