import java.util.ArrayList;

public class manejoT extends Thread{
    private Ram ram;
    private MemoriaVirtual memoriaVirtual;
    private TablaDePaginas tablaDePaginas;
    private boolean terminar = true;

    public manejoT(Ram ram, MemoriaVirtual memoriaVirtual, TablaDePaginas tablaDePaginas){
        this.ram = ram;
        this.memoriaVirtual = memoriaVirtual;
        this.tablaDePaginas = tablaDePaginas;
    }

    


    public void setTerminar(boolean terminar) {
        this.terminar = terminar;
    }




    public void run(){
        while (terminar){
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (tablaDePaginas){
            
            ArrayList<Integer> sacar = tablaDePaginas.getSacar(ram);
            for (int i = 0; i < sacar.size(); i++){
                int pocicion = sacar.get(i);
                
                tablaDePaginas.quitarPagina(pocicion, ram, memoriaVirtual);
                
                
            }
            tablaDePaginas.setSacar(new ArrayList<Integer>(), ram);
        }
        }
    }




    public void terminar() {
        terminar = false;
    }

}
