import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
public class main {

    public static void main(String[] args) {

        MemoriaVirtual memoriaV = new MemoriaVirtual();

        boolean terminar = true;
        while (terminar){
        String accion = input("¿Que quiers hacer ingresa el numero? \n1.Generación de las referencias \n2. Calcular datos buscados \n3. salir \nrespuesta");
        if (accion.equals("1")){
            int TP = Integer.valueOf(input("Por favor, ingrese el tamaño de las paginas")) ;
            String ruta = input("Por favor, ingrese la ruta de la imagen");
            Imagen imagen = new Imagen(ruta);
            String info = imagen.referenciacion(TP, imagen.leerLongitud()); 

            int NF = imagen.getAlto();
            int NC = imagen.getAncho();
            int NR = imagen.leerLongitud()*17+16;
            int NP = (NF*NC)*3/TP;
            
            System.out.println("El tamaño de las paginas es: " + TP);
            System.out.println("El numero de filas es: " + NF);
            System.out.println("El numero de columnas es: " + NC);
            System.out.println("El numero de registros son: " + NR);
            System.out.println("El numero de paginas que se van a usar son: " + NP);
            File file = new File("./data/referencias.txt");
            file.delete();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("./data/referencias.txt", true))) {
			String datos =  "TP: " + TP + "\n" + "NF: " + NF + "\n" + "NC: " + NC + "\n" + "NR: " + NR + "\n" + "NP: " + NP + "\n" ;
			writer.write(datos);
            writer.write(info);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


        }

        else if (accion.equals("2")){
            int marcosP = Integer.valueOf(input("Por favor, ingrese el numero de marcos de pagina")) ;
            String archivo = input("Por favor, ingrese  la direccion del archivo ") ;


            try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {

                String TPs = lector.readLine().split("=")[1].replaceAll(" ", "");
                int TP = Integer.parseInt(TPs);
                String NFs = lector.readLine().split("=")[1].replaceAll(" ", "");
                int NF = Integer.parseInt(NFs);
                String NCs = lector.readLine().split("=")[1].replaceAll(" ", "");
                int NC = Integer.parseInt(NCs);
                String NRs = lector.readLine().split("=")[1].replaceAll(" ", "");
                int NR = Integer.parseInt(NRs);
                String NPs = lector.readLine().split("= ")[1].replaceAll(" ", "");
                int NP = Integer.parseInt(NPs);

                Ram ram = new Ram(marcosP, TP);
                TablaDePaginas tabla = new TablaDePaginas(marcosP);
                ManejadorMemoria simulador = new ManejadorMemoria(ram, memoriaV, tabla);
                manejoT manejo = new manejoT(ram, memoriaV, tabla);
                manejo.start();
                actualizarR bitR = new actualizarR(ram);
                bitR.start();
                int hit = 0;
                double tiempo = 0; 

                String linea = lector.readLine();

                HashMap<Integer, Integer> paginasC = new HashMap<Integer, Integer>();


                while (linea != null){

                    String[] contenido = linea.split(",");

                    if (!paginasC.containsKey(Integer.parseInt(contenido[1]))){
                        paginasC.put(Integer.parseInt(contenido[1]), Integer.parseInt(contenido[1]));
                        PaginaVirtual paginaV = new PaginaVirtual(Integer.parseInt(contenido[1]), 0, TP);
                        PaginaReal paginaR = new PaginaReal(0,0,0, TP);
                        memoriaV.agregarPaginaVirtual(paginaV);
                       
                        if (!ram.estaLlena()){
                                        tabla.reemplazarPagina(paginaR, paginaV, ram);
                                        tiempo += 0.000025;
                                        hit++;

                                }
                        else {
                            simulador.informacion(Integer.parseInt(contenido[1]), Integer.parseInt(contenido[2]));
                        }

                        
                    }
                    else {
                        simulador.informacion(Integer.parseInt(contenido[1]), Integer.parseInt(contenido[2]));
                    }
                    linea = lector.readLine();
                }

                bitR.terminar();
                manejo.terminar();
                hit += simulador.getHits();
                int miss = simulador.getMisses();
                tiempo += simulador.getTiempo();
                int total = hit + miss;
                System.out.println("hits:"+ hit);
                System.out.println("misses:"+ miss);
                System.out.println("Tiempo:"+ tiempo);
                System.out.println("paginas hacedidas:"+ total);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }}

        else if (accion.equals("3")){
            terminar = false;
        }
        else{
            System.out.println("Opción no valida");
        }
    }




        



    }

    public static String obtenerRutaImagen() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, ingrese la ruta de la imagen: ");
        String ruta = scanner.nextLine();
        scanner.close();
        return ruta;
    }

    private static String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.err.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

}
