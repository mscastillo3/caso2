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
            ArrayList<ArrayList<Integer>> sacar = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> clase0 = new ArrayList<Integer>();
            ArrayList<Integer> clase1 = new ArrayList<Integer>();
            ArrayList<Integer> clase2 = new ArrayList<Integer>();
            ArrayList<Integer> clase3 = new ArrayList<Integer>();
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

                    if (paginaReal.getReferencia() == 0 && paginaReal.getModificacion() == 0) {
                        clase0.add(x);

                    }
                    else if (paginaReal.getReferencia() == 0 && paginaReal.getModificacion() == 1){
                        paginaReal.setModificacion(0);
                        clase1.add(x);
                    }
                    else if (paginaReal.getReferencia() == 1 && paginaReal.getModificacion() == 0){
                        paginaReal.setReferencia(0);
                        clase2.add(x);
                    }
                    else if (paginaReal.getReferencia() == 1 && paginaReal.getModificacion() == 1){
                        paginaReal.setReferencia(0);
                        paginaReal.setModificacion(0);
                        clase3.add(x);
                    }

                    x++;
                
                }

                sacar.add(clase0);
                sacar.add(clase1);
                sacar.add(clase2);
                sacar.add(clase3);
                ram.setSacar(sacar);

    
        }
    }
    }

	public void terminar() {
		termina = false;
	}

}
