import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;

public class ManejadorMemoria extends Thread {

    private Ram ram;
    private MemoriaVirtual memoriaVirtual;
    private TablaDePaginas tablaDePaginas;
    private long hits = 0;
    private long misses = 0;
    private long tiempo = 0;
    private double tiempor = 0;
    private BufferedReader lector;
    private int TP;

    public ManejadorMemoria(Ram ram, MemoriaVirtual memoriaVirtual, TablaDePaginas tablaDePaginas, BufferedReader lector, int TP) {
        this.ram = ram;
        this.memoriaVirtual = memoriaVirtual;
        this.tablaDePaginas = tablaDePaginas;
        this.lector = lector;
        this.TP = TP;
    }

    public void informacion(int pagina, int mod)  {
        
        synchronized(ram){
                
        PaginaVirtual paginaVirtual = tablaDePaginas.RecuperarPaginaV(pagina, memoriaVirtual);
        if (paginaVirtual.getPresencia() == 1){
            tablaDePaginas.buscarPagina(paginaVirtual.getNumereDePagina(), ram, paginaVirtual);
            
            tiempor += 0.000025;
            hits++;
        }
        else {
                misses++;
                tiempo += 10;
                    
                            if (!ram.estaLlena()){
                                PaginaReal paginaReal = new PaginaReal(mod, 0, 0, ram.getTamañoPaginas() );
                                tablaDePaginas.reemplazarPagina(paginaReal, paginaVirtual, ram);
                            }
                            else {
                                ArrayList<ArrayList<Integer>> sacar = tablaDePaginas.getSacar(ram);
                                int pocicion= 0;

                                for (int i = 0; i < sacar.size(); i++){
                                    ArrayList<Integer> clase = sacar.get(i);
                                    if (clase.size()>0 ){
                                        pocicion = clase.get(0);
                                        clase.remove(0);
                                        break;

                                    }
                                        
                                }

                                tablaDePaginas.quitarPagina(pocicion, ram, memoriaVirtual);
                                PaginaReal paginaReal = new PaginaReal(mod, 0, 0, ram.getTamañoPaginas() );
                                tablaDePaginas.reemplazarPagina(paginaReal, paginaVirtual, ram);

                                
                            }
                        }
                    }
                }

    
    public void run ()  {
        String linea;
        try {
            linea = lector.readLine();
        HashMap<Integer, Integer> paginasC = new HashMap<Integer, Integer>();
        while (linea != null){
            
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            

            String[] contenido = linea.split(",");
            int mod = 0;
            if (contenido[3].equals("W")){
                mod = 1;
            }

            if (!paginasC.containsKey(Integer.parseInt(contenido[1]))){
                paginasC.put(Integer.parseInt(contenido[1]), Integer.parseInt(contenido[1]));
                PaginaVirtual paginaV = new PaginaVirtual(Integer.parseInt(contenido[1]), 0, TP);
             
                PaginaReal paginaR = new PaginaReal(mod,0,0, TP);
                memoriaVirtual.agregarPaginaVirtual(paginaV);
               
                if (!ram.estaLlena()){
                                tablaDePaginas.reemplazarPagina(paginaR, paginaV, ram);
                                tiempor += 0.000025;
                                hits++;

                        }
                else {
                    informacion(Integer.parseInt(contenido[1]), mod);
                }

                
            }
            else {
                informacion(Integer.parseInt(contenido[1]), mod);
            }
            linea = lector.readLine();
        }

        synchronized (this) {

            this.notify();
        }
            }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }



    

    public long getHits() {
        return hits;
    }


    public long getMisses() {
        return misses;
    }



    public long getTiempo() {
        return tiempo + (long)tiempor;
    }


    
}

