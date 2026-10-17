public class Paciente {
    public enum EstadoPaciente{
        INGRESADO,
        CUARENTENA,
        EN_TRATAMIENTO
    }

    //Definición de atributos
    private String nombre;
    private double peso;
    private int edad;
    private EstadoPaciente estado;
    private double factorDosis;
    private double dosisAcumulada;
    private Jaula jaula;

    //Constructor
    public Paciente(String nombre, double peso, int edad, double factorDosis) {
        setNombre(nombre);
        setPeso(peso);
        setEdad(edad);
        this.factorDosis = factorDosis;
        this.dosisAcumulada = 0.0;
        this.jaula = null;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public int getEdad() {
        return edad;
    }

    public EstadoPaciente getEstado() {
        return estado;
    }

    public double getFactorDosis() {
        return factorDosis;
    }

    public double getDosisAcumulada() {
        return dosisAcumulada;
    }

    public Jaula getJaulaAsignada() {
        return jaula;
    }

    //Setters
    //Se valida que el atributo no esté nulo
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }
    //Validación que el peso sea positivo
    public void setPeso(double peso) {
        if (peso > 0) {
            this.peso = peso;
        }
    }

    //Se valida que la edad sea positiva y no supere los 30Kg
    public void setEdad(int edad) {
        if (edad >= 0 && edad <= 30) {
            this.edad = edad;
        }
    }

    //Validar que el estado no sea nulo
    public void setEstado(EstadoPaciente estado) {
        if (estado != null) {
            this.estado = estado;
        }
    }

    public void setFactorDosis(double factorDosis) {
        this.factorDosis = factorDosis;
    }

    //Validación que la dosis sea positiva
    public void setDosisAcumulada(double dosisAcumulada) {
        if (dosisAcumulada >= 0) {
            this.dosisAcumulada = dosisAcumulada;
        }
    }

    public void setJaula(Jaula jaula) {
        this.jaula = jaula;
    }





}
