public class Gato extends Paciente {

    private int nivelEstres;

    public Gato(String nombre, double peso, int edad, int nivelEstres) {
        // Llama al constructor de Paciente y le pasa el factorDosis fijo de 6
        super(nombre, peso, edad, 6.0);
        setNivelEstres(nivelEstres);
    }

    // Sobrescribimos setPeso para limitar a máximo 15kg
    @Override
    public void setPeso(double peso) {
        if (peso > 0 && peso <= 15.0) {
            super.setPeso(peso);
        }
    }

    public int getNivelEstres() {
        return nivelEstres;
    }

    public void setNivelEstres(int nivelEstres) {
        // El nivel de estrés solo puede ir de 0 a 10
        if (nivelEstres >= 0 && nivelEstres <= 10) {
            this.nivelEstres = nivelEstres;
        }
    }
}