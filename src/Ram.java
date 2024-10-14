public class Ram {
    private int tamaño;
    private int tamañoPaginas;

    // Constructor
    public Ram(int tamaño, int tamañoPaginas) {
        this.tamaño = tamaño;
        this.tamañoPaginas = tamañoPaginas;
    }

    // Getters y Setters
    public int getTamaño() { return tamaño; }
    public void setTamaño(int tamaño) { this.tamaño = tamaño; }

    public int getTamañoPaginas() { return tamañoPaginas; }
    public void setTamañoPaginas(int tamañoPaginas) { this.tamañoPaginas = tamañoPaginas; }
}
