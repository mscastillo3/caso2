public class PaginaVirtual {
    private int numeroDePagina;
    private int presencia;
    private int[] referencias;

    public PaginaVirtual(int numeroP, int presencia, int tamaño) {
        this.numeroDePagina = numeroP;
        this.presencia = presencia;
        this.referencias = new int[tamaño];

    }

    public int getDireccion() { return numeroDePagina; }
    public void setDireccion(int direccion) { this.numeroDePagina = direccion; }

    synchronized public int getPresencia() { return presencia; }
    synchronized public void setPresencia(int presencia) { this.presencia = presencia; }

    

    public int getNumereDePagina() {
        return numeroDePagina;
    }

    public void setNumeroDePagina(int numeroDePagina) {
        this.numeroDePagina = numeroDePagina;
    }

    public void agregarDato(int dato, int posicion) {
        referencias[posicion] = dato;
    }
}
