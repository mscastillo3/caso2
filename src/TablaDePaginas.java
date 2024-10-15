import java.util.List;

public class TablaDePaginas extends Thread{
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

    synchronized public Integer  quitarPagina(int pocicionPagianReal) {
        for (int i = 0; i < listaDeRelacion.length; i++) {
            if (listaDeRelacion[i] == pocicionPagianReal) {
                listaDeRelacion[i] = -1;
                return i;
            }
        }
        return null;
    }


  

    
}
