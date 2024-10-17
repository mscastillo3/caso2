import java.util.ArrayList;

public class actualizarR extends Thread {

    private Ram ram;
    private boolean termina = true;

    public actualizarR(Ram ram) {
        this.ram = ram;
    }

    public void run() {
        while (termina) {
            try {
                sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<Integer> sacar = new ArrayList<Integer>();
            int x = 0;
            int pr = 0 ;
            synchronized (ram) {
                for (PaginaReal paginaReal : ram.getPaginasReales()) {
                    if (paginaReal == null){
                        x++;
                        continue;
                    }
                    pr ++;
                    if (pr == ram.getPaginas()) {
                        break; 
                    }
                    if (paginaReal.getReferencia() == 1) {
                        paginaReal.setReferencia(0);
                    }
                    else if (paginaReal.getReferencia() == 0){
                        sacar.add(0, x);
                    }
                    x++;
                
                }

                ram.setSacar(sacar);

    
        }
    }
    }

	public void terminar() {
		termina = false;
	}

}
