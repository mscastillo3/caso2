import java.util.ArrayList;
import java.util.List;

public class TablaDePaginas extends Thread{
    private int[] listaDeRelacion;

    public TablaDePaginas(int numeroDePaginas) {
        this.listaDeRelacion = new int[numeroDePaginas];
        for (int i = 0; i < listaDeRelacion.length; i++) {
            listaDeRelacion[i] = -1; 
        }
    }

    synchronized public void buscarPagina(int pocicionPaginaVirtual, Ram ram, PaginaVirtual paginaVirtual) {
        synchronized (ram) {
        int x = 0;
        for (int i = 0; i < listaDeRelacion.length; i++) {
            if (listaDeRelacion[i] == pocicionPaginaVirtual) {
                x= i;
            }
        }
        PaginaReal paginaReal = ram.conseguirPaginaReal(x) ;
        if (paginaReal == null){
            int y = 1;
         }
        paginaReal.setReferencia(1);
    }
    }

    synchronized public void reemplazarPagina( PaginaReal pagianReal, PaginaVirtual paginaVirtual, Ram ram) {
        synchronized (ram) {
            ram.agregarPaginaReal(pagianReal);
            listaDeRelacion[pagianReal.getNumero()] = paginaVirtual.getNumereDePagina();
            paginaVirtual.setPresencia(1);}

    }

    synchronized public void  quitarPagina(Integer vpagianReal, Ram ram, MemoriaVirtual memoriaVirtual) {
        synchronized (ram) {
        PaginaReal paginaReal = ram.conseguirPaginaReal(vpagianReal);
        if (paginaReal == null){
           int x = 1;
        }
        memoriaVirtual.RecuperarPagina(listaDeRelacion[paginaReal.getNumero()]).setPresencia(0);
        listaDeRelacion[paginaReal.getNumero()] = -1;

        ram.quitarPaginaReal(paginaReal);
        this.notify();}


    }

    synchronized public  PaginaVirtual RecuperarPaginaV(int pagina, MemoriaVirtual memoriaVirtual) {
        
        return memoriaVirtual.RecuperarPagina(pagina);
    }

    public ArrayList<Integer> getSacar(Ram ram) {
        return ram.getSacar();
    }

    public void setSacar(ArrayList<Integer> arrayList, Ram ram) {
       ram.setSacar(arrayList);;
    }

  

    
}
