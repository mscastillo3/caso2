import java.util.ArrayList;
import java.util.List;

public class MemoriaVirtual {
    private List<PaginaVirtual> paginasVirtuales;

    public MemoriaVirtual() {
        this.paginasVirtuales = new ArrayList<PaginaVirtual>();
    }

    public void definirPaginaVirtual() {
        
    }

    public void agregarPaginaVirtual(PaginaVirtual paginaVirtual) {
        paginasVirtuales.add(paginaVirtual);
    }

    public PaginaVirtual  RecuperarPagina(int numero){
        return paginasVirtuales.get(numero);

    }

    
    
}
