public class AveExotica extends Paciente {

    private double envergadura;

    public AveExotica(String nombre, double peso, int edad, double envergadura) {
        // Llama al constructor de Paciente y le pasa el factorDosis fijo de 3
        super(nombre, peso, edad, 3.0);
        setEnvergadura(envergadura);
    }

    // Sobrescribimos setPeso para limitar a máximo 15kg
    @Override
    public void setPeso(double peso) {
        if (peso > 0 && peso <= 15.0) {
            super.setPeso(peso);
        }
    }

    public double getEnvergadura() {
        return envergadura;
    }

    public void setEnvergadura(double envergadura) {
        if (envergadura > 0) {
            this.envergadura = envergadura;
        }
    }
}