import java.util.ArrayList;
import java.util.HashMap;

public class Ram {
    private int marcos;
    private int tamañoPaginas;
    private ArrayList<PaginaReal> paginasReales;
    private ArrayList<ArrayList<Integer>> sacar = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> pociconesLibres = new ArrayList<Integer>();
    private int paginas = 0;
    
    public Ram(int marcos, int tamañoPaginas) {
        this.marcos = marcos;
        this.tamañoPaginas = tamañoPaginas;
        paginasReales = new ArrayList<PaginaReal>();
        for (int i = 0; i < marcos; i++) {
            paginasReales.add(null);
            pociconesLibres.add(i);
        }
        ArrayList<Integer> clase0 = new ArrayList<Integer>();
        ArrayList<Integer> clase1 = new ArrayList<Integer>();
        ArrayList<Integer> clase2 = new ArrayList<Integer>();
        ArrayList<Integer> clase3 = new ArrayList<Integer>();
        sacar.add(clase0);
        sacar.add(clase1);
        sacar.add(clase2);
        sacar.add(clase3);


    }

    synchronized public int getPaginas() {
        return paginas;
    }

    synchronized public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    synchronized public void agregarPaginaReal(PaginaReal paginaReal) {

        paginas ++;

        paginasReales.set (pociconesLibres.get(0), paginaReal) ;
        paginaReal.setNumero(pociconesLibres.get(0));
        paginaReal.setPresencia(1);
        paginaReal.setReferencia(1);
        pociconesLibres.remove(0);
        if (paginaReal.getModificacion() == 0){
            sacar.get(2).add(paginaReal.getNumero());

        }
        else {
            sacar.get(3).add(paginaReal.getNumero());
        }
        
    }

    synchronized public void quitarPaginaReal(PaginaReal paginaReal) {
        paginas --;
        pociconesLibres.add(paginaReal.getNumero());
        paginasReales.set(paginaReal.getNumero(), null);
        paginaReal.setNumero(-1);
        paginaReal.setPresencia(0);
        paginaReal.setReferencia(0);
        paginaReal.setModificacion(0);
    }

    synchronized public PaginaReal conseguirPaginaReal(int numero) {
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

    public synchronized ArrayList<ArrayList<Integer>> getSacar() {
        return sacar;
    }

    public synchronized void setSacar(ArrayList<ArrayList<Integer>> prioridad) {
        this.sacar = prioridad;
    }

    public synchronized boolean estaLlena() {
        return (paginas >= marcos);
    }
    


    
}
