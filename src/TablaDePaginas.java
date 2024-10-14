import java.util.List;

public class TablaDePaginas {
    private int[] listaDeRelacion;

    public TablaDePaginas(int numeroDePaginas) {
        this.listaDeRelacion = new int[numeroDePaginas];
    }

    synchronized public int buscarPagina(int pocicionPaginaVirtual) {
        
        return listaDeRelacion[pocicionPaginaVirtual];
    }

    synchronized public void reemplazarPagina(int pocicionPagianReal, int pocicionPaginaVirtual) {
        listaDeRelacion[pocicionPaginaVirtual] = pocicionPagianReal;
    }
    
}
