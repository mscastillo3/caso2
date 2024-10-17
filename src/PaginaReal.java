public class PaginaReal {
    private int numero;
    private int modificacion;
    private int referencia;
    private int presencia;
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    private int[] contenido;

    
    public PaginaReal( int modificacion, int referencia, int presencia, int tamañoP) {
        this.modificacion = modificacion;
        this.referencia = referencia;
        this.presencia = presencia;
        this.contenido = new int[tamañoP];
    }


    public int getModificacion() { return modificacion; }
    public void setModificacion(int modificacion) { this.modificacion = modificacion; }

    public int getReferencia() { return referencia; }
    public void setReferencia(int referencia) { this.referencia = referencia; }

    synchronized public int getPresencia() { return presencia; }
    synchronized public void setPresencia(int presencia) { this.presencia = presencia; }

    public void agregarDato(int dato, int posicion) {
        contenido[posicion] = dato;
    }

    public int getDato (int desplasamiento){
        return contenido[desplasamiento];
    }
}
