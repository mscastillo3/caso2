import java.util.ArrayList;

public class ManejadorMemoria  {

    private Ram ram;
    private MemoriaVirtual memoriaVirtual;
    private TablaDePaginas tablaDePaginas;
    private int hits = 0;
    private int misses = 0;
    private double tiempo = 0;

    public ManejadorMemoria(Ram ram,  MemoriaVirtual memoriaVirtual, TablaDePaginas tablaDePaginas) {
        this.ram = ram;
        this.memoriaVirtual = memoriaVirtual;
        this.tablaDePaginas = tablaDePaginas;
    }

    public void informacion(int pagina, int referencia)  {
        
        synchronized(tablaDePaginas){
                
        PaginaVirtual paginaVirtual = tablaDePaginas.RecuperarPaginaV(pagina, memoriaVirtual);
        if (paginaVirtual.getPresencia() == 1){
            tablaDePaginas.buscarPagina(paginaVirtual.getNumereDePagina(), ram, paginaVirtual);
            
            tiempo += 0.000025;
            hits++;
        }
        else {
                misses++;
                tiempo += 10;
                boolean x = true;
                while (x){
                    
                            if (!ram.estaLlena()){
                                PaginaReal paginaReal = new PaginaReal(0, 0, 0, ram.getTamañoPaginas() );
                                tablaDePaginas.reemplazarPagina(paginaReal, paginaVirtual, ram);
                                x = false;
                            }
                            else {
                                
                                try {
                                    tablaDePaginas.wait();
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
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



    public double getTiempo() {
        return tiempo;
    }


    
}

