import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

public class mainTesting {

    public static void main(String[] args) {
        MemoriaVirtual memoriaV = new MemoriaVirtual();

        // Definir tamaños de páginas y rutas de imágenes
        List<Integer> tamanosPaginas = Arrays.asList(512, 1024);
        List<String> rutasImagenes = Arrays.asList(
           "C:/Users/pablo/OneDrive/Documents/Universidad/Infra comp/caso2/data/caso2-parrots_mod.bmp",
           "C:/Users/pablo/OneDrive/Documents/Universidad/Infra comp/caso2/data/capi100.bmp",
           "C:/Users/pablo/OneDrive/Documents/Universidad/Infra comp/caso2/data/capi1000.bmp",
           "C:/Users/pablo/OneDrive/Documents/Universidad/Infra comp/caso2/data/capi2000.bmp",
           "C:/Users/pablo/OneDrive/Documents/Universidad/Infra comp/caso2/data/capi4000.bmp",
           "C:/Users/pablo/OneDrive/Documents/Universidad/Infra comp/caso2/data/capi8000.bmp",
           "C:/Users/pablo/OneDrive/Documents/Universidad/Infra comp/caso2/data/venado100.bmp",
           "C:/Users/pablo/OneDrive/Documents/Universidad/Infra comp/caso2/data/venado1000.bmp",
           "C:/Users/pablo/OneDrive/Documents/Universidad/Infra comp/caso2/data/venado2000.bmp",
           "C:/Users/pablo/OneDrive/Documents/Universidad/Infra comp/caso2/data/venado4000.bmp",
           "C:/Users/pablo/OneDrive/Documents/Universidad/Infra comp/caso2/data/venado8000.bmp"
        );

        // Crear archivos CSV para almacenar resultados
        try (BufferedWriter writerOpcion1 = new BufferedWriter(new FileWriter("./data/resultados_opcion1_largo.csv", true));
             BufferedWriter writerOpcion2 = new BufferedWriter(new FileWriter("./data/resultados_opcion2_largo.csv", true))) {

            writerOpcion1.write("TamanoPagina,NFilas,NColumnas,NRegistros,NP,NombreImagen\n"); // Encabezados para la opción 1
            writerOpcion2.write("TamanoPagina,NombreImagen,MarcosPagina,Hits,Misses,Tiempo,TotalAccesos,TiempoRam,TiempoSwap,PorcentajeAciertos\n"); // Encabezados para la opción 2

            for (int TP : tamanosPaginas) {
                for (String ruta : rutasImagenes) {
                    // Opción 1: Generación de referencias
                    Imagen imagen = new Imagen(ruta);
                    String info = imagen.referenciacion(TP, imagen.leerLongitud());

                    int NF = imagen.getAlto();
                    int NC = imagen.getAncho();
                    int NR = imagen.leerLongitud() * 17 + 16;
                    int NP = (NF * NC) * 3 / TP;

                    // Imprimir y guardar resultados de la opción 1
                    System.out.println("Para la imagen: " + ruta);
                    System.out.println("El tamaño de las paginas es: " + TP);
                    System.out.println("El numero de filas es: " + NF);
                    System.out.println("El numero de columnas es: " + NC);
                    System.out.println("El numero de registros son: " + NR);
                    System.out.println("El numero de paginas que se van a usar son: " + NP);

                    // Guardar en el archivo CSV de la opción 1
                    writerOpcion1.write(TP + "," + NF + "," + NC + "," + NR + "," + NP + "," + ruta + "\n");

                    // Escribir datos en el archivo referencias.txt
                    File file = new File("./data/referencias.txt");
                    file.delete();
                    try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true))) {
                        fileWriter.write("P= " + TP + "\n" + "NF= " + NF + "\n" + "NC= " + NC + "\n" + "NR= " + NR + "\n" + "NP= " + NP + "\n");
                        fileWriter.write(info);
                    }

                    // Opción 2: Calcular datos buscados
                    for (int marcosP : new int[]{4, 8}) { // Marcos de página: 2 y 4
                        try (BufferedReader lector = new BufferedReader(new FileReader(file))) {
                            String TPs = lector.readLine().split("=")[1].trim();
                            int TPValor = Integer.parseInt(TPs);
                            String NFs = lector.readLine().split("=")[1].trim();
                            int NFValor = Integer.parseInt(NFs);
                            String NCs = lector.readLine().split("=")[1].trim();
                            int NCValor = Integer.parseInt(NCs);
                            String NRs = lector.readLine().split("=")[1].trim();
                            int NRValor = Integer.parseInt(NRs);
                            String NPs = lector.readLine().split("=")[1].trim();
                            int NPValor = Integer.parseInt(NPs);

                            Ram ram = new Ram(marcosP, TPValor);
                            TablaDePaginas tabla = new TablaDePaginas(marcosP);
                            ManejadorMemoria simulador = new ManejadorMemoria(ram, memoriaV, tabla, lector, TPValor);
                            simulador.start();
                            actualizarR bitR = new actualizarR(ram);
                            bitR.start();
                            synchronized (simulador) {
                                simulador.wait();
                            }

                            bitR.terminar();
                            long hit = simulador.getHits();
                            long miss = simulador.getMisses();
                            long tiempo = simulador.getTiempo();
                            long total = hit + miss;
                            System.out.println("hits: " + hit);
                            System.out.println("misses: " + miss);
                            System.out.println("Tiempo: " + tiempo + "ms");
                            System.out.println("paginas accedidas: " + total);
                            long tRam = (long) (total * 0.000025);
                            long tswap = (long) (total * 10);
                            long porcentaje = hit * 100 / total;
                            System.out.println("Si todo fuera en ram: " + tRam + "ms");
                            System.out.println("Si todo fuera en swap: " + tswap + "ms");
                            System.out.println("Porcentaje de aciertos: " + porcentaje + "%");

                            // Guardar resultados de la opción 2 en CSV
                            writerOpcion2.write(TP + "," + ruta + "," + marcosP + "," + hit + "," + miss + "," + tiempo + "," + total + "," + tRam + "," + tswap + "," + porcentaje + "\n");

                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
