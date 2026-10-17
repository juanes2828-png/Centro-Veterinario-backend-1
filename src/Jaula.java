public class Jaula {

    private int numero;
    private String tamano;
    private String zona;
    private Paciente ocupante;

    public Jaula(int numero, String tamano, String zona) {
        setNumero(numero);
        setTamano(tamano);
        setZona(zona);
        this.ocupante = null; // Por defecto, al crear la jaula está libre
    }

    // --- GETTERS ---
    public int getNumero() {
        return numero;
    }

    public String getTamano() {
        return tamano;
    }

    public String getZona() {
        return zona;
    }
    public Paciente getOcupante() {
        return ocupante;
    }

    // --- SETTERS CON VALIDACIÓN ---
    public void setNumero(int numero) {
        if (numero > 0) {
            this.numero = numero;
        }
    }

    public void setTamano(String tamano) {
        // Validamos que solo acepte S, M o L
        if (tamano != null && (tamano.equals("S") || tamano.equals("M") || tamano.equals("L"))) {
            this.tamano = tamano;
        }
    }

    public void setZona(String zona) {
        // Validamos que solo acepte A o B
        if (zona != null && (zona.equals("A") || zona.equals("B"))) {
            this.zona = zona;
        }
    }

    public void setOcupante(Paciente ocupante) {
        // Permitimos null porque es la forma de liberar la jaula
        this.ocupante = ocupante;
    }

    public boolean isLibre() {
        return this.ocupante == null;
    }
}