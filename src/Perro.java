public class Perro extends Paciente {

    private String raza;
    private boolean vacunado;

    public Perro(String nombre, double peso, int edad, String raza, boolean vacunado) {
        // Llama al constructor de Paciente y le pasa el factorDosis fijo de 10
        super(nombre, peso, edad, 10.0);
        setRaza(raza);
        this.vacunado = vacunado;
    }

    // Sobrescribimos setPeso para limitar a máximo 80kg (Regla 1)
    @Override
    public void setPeso(double peso) {
        if (peso > 0 && peso <= 80.0) {
            super.setPeso(peso);
        }
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        if (raza != null && !raza.trim().isEmpty()) {
            this.raza = raza;
        }
    }

    public boolean isVacunado() {
        return vacunado;
    }

    public void setVacunado(boolean vacunado) {
        this.vacunado = vacunado;
    }
}