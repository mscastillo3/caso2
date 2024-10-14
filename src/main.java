import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        String accion = input("¿Que quiers hacer ingresa el numero? \nGeneración de las referencias : 1 \nCalcular datos buscados : 2\n");
        if (accion.equals("1")){
            int TP = Integer.valueOf(input("Por favor, ingrese el tamaño de las paginas")) ;
            String ruta = input("Por favor, ingrese la ruta de la imagen");
            Imagen imagen = new Imagen(ruta);


            
            int NF = imagen.getAlto();
            int NC = imagen.getAncho();
            int NR = imagen.leerLongitud()*17+16;
            int NP = (NF*NC)*3/TP;
            
            System.out.println("El tamaño de las paginas es: " + TP);
            System.out.println("El numero de filas es: " + NF);
            System.out.println("El numero de columnas es: " + NC);
            System.out.println("El numero de registros son: " + NR);
            System.out.println("El numero de paginas que se van a usar son: " + NP);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("./data/referencias.txt", true))) {
			String datos =  "TP: " + TP + "\n" + "NF: " + NF + "\n" + "NC: " + NC + "\n" + "NR: " + NR + "\n" + "NP: " + NP + "\n" ;
			writer.write(datos);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.println(imagen.getImagen());


        }

        else if (accion.equals("2")){
            int marcosP = Integer.valueOf(input("Por favor, ingrese el numero de marcos de pagina")) ;
            int TP = 0;
            Ram ram = new Ram(marcosP, 4);
            



        }
        else{
            System.out.println("Opción no valida");
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
