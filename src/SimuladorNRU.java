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

    public int informacion(int pagina, int referencia) {

        PaginaVirtual paginaVirtual = memoriaVirtual.RecuperarPagina(referencia);

        if (paginaVirtual.getPresencia() == 1){

            PaginaReal paginaReal = ram.conseguirPaginaReal(tablaDePaginas.buscarPagina(paginaVirtual.getNumeorDePagina())) ;
            tiempo += 25;
            hits++;
            return paginaReal.getDato(referencia);

            
        }

        else {

            

        }
        return 0;






        
    }
}
