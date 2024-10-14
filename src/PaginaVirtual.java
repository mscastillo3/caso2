public class PaginaVirtual {
    private int numeorDePagina;
    private int presencia;
    private int[] referencias;

    public PaginaVirtual(int numeroP, int presencia, int tamaño) {
        this.numeorDePagina = numeroP;
        this.presencia = presencia;
        this.referencias = new int[tamaño];

    }

    public int getDireccion() { return numeorDePagina; }
    public void setDireccion(int direccion) { this.numeorDePagina = direccion; }

    public int getPresencia() { return presencia; }
    public void setPresencia(int presencia) { this.presencia = presencia; }

    

    public int getNumeorDePagina() {
        return numeorDePagina;
    }

    public void setNumeorDePagina(int numeorDePagina) {
        this.numeorDePagina = numeorDePagina;
    }

    public void agregarDato(int dato, int posicion) {
        referencias[posicion] = dato;
    }
}
