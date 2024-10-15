import java.util.ArrayList;

public class SimuladorNRU extends Thread {

    private Ram ram;
    private MemoriaSwap memoriaSwap;
    private MemoriaVirtual memoriaVirtual;
    private TablaDePaginas tablaDePaginas;
    private int hits = 0;
    private int misses = 0;
    private int tiempo = 0;

    public SimuladorNRU(Ram ram, MemoriaSwap memoriaSwap, MemoriaVirtual memoriaVirtual, TablaDePaginas tablaDePaginas) {
        this.ram = ram;
        this.memoriaSwap = memoriaSwap;
        this.memoriaVirtual = memoriaVirtual;
        this.tablaDePaginas = tablaDePaginas;
    }

    public int informacion(int pagina, int referencia) throws InterruptedException {
        PaginaVirtual paginaVirtual = memoriaVirtual.RecuperarPagina(referencia);
        if (paginaVirtual.getPresencia() == 1){
            PaginaReal paginaReal = ram.conseguirPaginaReal(tablaDePaginas.buscarPagina(paginaVirtual.getNumeorDePagina())) ;
            paginaReal.setReferencia(1);
            tiempo += 25;
            hits++;
            return paginaReal.getDato(referencia);
        }
        else {
                misses++;
                tiempo += 10000000;
                boolean x = true;
                while (x){
                    synchronized (ram){
                        synchronized (tablaDePaginas){
                            synchronized (memoriaSwap){
                            if (!ram.estaLlena()){
                                PaginaReal paginaReal = memoriaSwap.conseguirPaginaReal(pagina);
                                memoriaSwap.quitarPaginaReal(paginaReal);
                                int pocicion = ram.getPaginasReales().size();
                                ram.agregarPaginaReal(paginaReal);
                                tablaDePaginas.reemplazarPagina(pocicion, pagina);
                                paginaReal.setReferencia(1);
                                paginaVirtual.setPresencia(1);
                                x = false;
                                return paginaReal.getDato(referencia);
                            }
                            else {
                                ram.wait();
                            }
                        }
                    }

                    }
                }
            }
        return 0;  
    }

    public void start(){
        while (true){
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (memoriaSwap){
                synchronized (ram){
                    synchronized (memoriaVirtual){
                        synchronized (tablaDePaginas){
                            ArrayList<Integer> sacar = ram.getSacar();
                            for (int i = 0; i < sacar.size(); i++){
                                int pocicion = sacar.get(i);
                                PaginaReal paginaReal = ram.conseguirPaginaReal(pocicion);
                                paginaReal.setPresencia(0);
                                memoriaSwap.agregarPaginaReal(paginaReal);
                                ram.quitarPaginaReal(paginaReal);
                                int pagina = tablaDePaginas.quitarPagina(paginaReal.getNumero());
                                memoriaVirtual.RecuperarPagina(pagina).setPresencia(0);
                                ram.notify();
                                
                            }
                        }
                    }
                }
            }
        }
    }

    public int getHits() {
        return hits;
    }


    public int getMisses() {
        return misses;
    }



    public int getTiempo() {
        return tiempo;
    }

    
}

