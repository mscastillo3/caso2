public class PaginaReal {
    private int numero;
    private int modificacion;
    private int referencia;
    private int presencia;
    private int contenido;

    // Constructor
    public PaginaReal(int numero, int modificacion, int referencia, int presencia, int contenido) {
        this.numero = numero;
        this.modificacion = modificacion;
        this.referencia = referencia;
        this.presencia = presencia;
        this.contenido = contenido;
    }

    // Getters y Setters
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public int getModificacion() { return modificacion; }
    public void setModificacion(int modificacion) { this.modificacion = modificacion; }

    public int getReferencia() { return referencia; }
    public void setReferencia(int referencia) { this.referencia = referencia; }

    public int getPresencia() { return presencia; }
    public void setPresencia(int presencia) { this.presencia = presencia; }

    public int getContenido() { return contenido; }
    public void setContenido(int contenido) { this.contenido = contenido; }
}
