public class Hospital {

    // Vectores de capacidad fija exigidos
    private Paciente[] pacientes;
    private int cantPacientes;
    private Jaula[] jaulas;

    public Hospital() {
        this.pacientes = new Paciente[10];
        this.cantPacientes = 0;
        this.jaulas = new Jaula[6];
    }

    public void cargarDatosIniciales() {
        // Carga de jaulas: 1 (A, S), 2 (A, M), 3 (A, M), 4 (A, L), 5 (B, S), 6 (B, M)
        jaulas[0] = new Jaula(1, "S", "A");
        jaulas[1] = new Jaula(2, "M", "A");
        jaulas[2] = new Jaula(3, "M", "A");
        jaulas[3] = new Jaula(4, "L", "A");
        jaulas[4] = new Jaula(5, "S", "B");
        jaulas[5] = new Jaula(6, "M", "B");

        // Carga de pacientes (todos sin jaula asignada e ingresados)
        Perro rocky = new Perro("Rocky", 30.0, 4, "labrador", true);
        rocky.setEstado(Paciente.EstadoPaciente.INGRESADO);
        pacientes[cantPacientes++] = rocky;

        Gato misifu = new Gato("Misifu", 4.0, 3, 0);
        misifu.setEstado(Paciente.EstadoPaciente.INGRESADO);
        pacientes[cantPacientes++] = misifu;

        AveExotica kiwi = new AveExotica("Kiwi", 0.8, 2, 70.0);
        kiwi.setEstado(Paciente.EstadoPaciente.INGRESADO);
        pacientes[cantPacientes++] = kiwi;
    }

    // Método auxiliar para buscar pacientes por nombre (Regla 2 indica que es sin distinguir mayúsculas)
    private Paciente buscarPaciente(String nombre) {
        for (int i = 0; i < cantPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return pacientes[i];
            }
        }
        return null; // Si no lo encuentra
    }

    // COMANDOS DE INGRESO

    public String ingresarPerro(String nombre, double peso, int edad, String raza, boolean vacunado) {
        // 1. Validar datos (Regla 1 y Orden Regla 2)
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 80.0 || edad < 0 || edad > 30 || raza == null || raza.trim().isEmpty()) {
            return "ERROR: datos invalidos";
        }
        // 2. Validar duplicado (Regla 2)
        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }
        // 3. Validar lleno (Regla 2)
        if (cantPacientes >= 10) {
            return "ERROR: hospital lleno";
        }

        // Crear e ingresar (Regla 3)
        Perro nuevoPerro = new Perro(nombre, peso, edad, raza, vacunado);
        if (!vacunado) {
            nuevoPerro.setEstado(Paciente.EstadoPaciente.CUARENTENA);
            pacientes[cantPacientes++] = nuevoPerro;
            return "OK: " + nombre + " ingresado en CUARENTENA";
        } else {
            nuevoPerro.setEstado(Paciente.EstadoPaciente.INGRESADO);
            pacientes[cantPacientes++] = nuevoPerro;
            return "OK: " + nombre + " ingresado";
        }
    }

    public String ingresarGato(String nombre, double peso, int edad) {
        // 1. Validar datos (Regla 1 y Orden Regla 2)
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 15.0 || edad < 0 || edad > 30) {
            return "ERROR: datos invalidos";
        }
        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }
        if (cantPacientes >= 10) {
            return "ERROR: hospital lleno";
        }

        // Se inicializa con nivel de estrés 0 por defecto
        Gato nuevoGato = new Gato(nombre, peso, edad, 0);
        nuevoGato.setEstado(Paciente.EstadoPaciente.INGRESADO); // Regla 3
        pacientes[cantPacientes++] = nuevoGato;

        return "OK: " + nombre + " ingresado";
    }

    public String ingresarAve(String nombre, double peso, int edad, double envergadura) {
        // 1. Validación de datos iniciales según la reglas 1 y 2
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 15.0 || edad < 0 || edad > 30 || envergadura <= 0) {
            return "ERROR: datos invalidos";
        }
        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }
        if (cantPacientes >= 10) {
            return "ERROR: hospital lleno";
        }

        AveExotica nuevaAve = new AveExotica(nombre, peso, edad, envergadura);
        nuevaAve.setEstado(Paciente.EstadoPaciente.INGRESADO); // Regla 3
        pacientes[cantPacientes++] = nuevaAve;

        return "OK: " + nombre + " ingresado";
    }

    // --- COMANDOS DE GESTIÓN ---

    public String vacunar(String nombre) {
        Paciente p = buscarPaciente(nombre);

        // Orden de errores según la tabla de comandos
        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (!(p instanceof Perro)) {
            return "ERROR: solo aplica a perros";
        }

        Perro perro = (Perro) p;
        if (perro.isVacunado()) {
            return "ERROR: ya vacunado";
        }

        // Aplicar vacunación y cambiar estado si estaba en cuarentena (Regla 3)
        perro.setVacunado(true);
        if (perro.getEstado() == Paciente.EstadoPaciente.CUARENTENA) {
            perro.setEstado(Paciente.EstadoPaciente.INGRESADO);
        }

        return "OK: " + nombre + " vacunado";
    }

    public String asignar(String nombre) {
        Paciente p = buscarPaciente(nombre);

        // Orden de validaciones (Regla 6)
        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (p.getEstado() == Paciente.EstadoPaciente.CUARENTENA) {
            return "ERROR: paciente en cuarentena";
        }
        if (p.getJaula() != null) {
            return "ERROR: paciente ya tiene jaula";
        }

        // Determinar zona requerida (Regla 5)
        String zonaRequerida = (p instanceof AveExotica) ? "B" : "A";

        // Determinar tamaño requerido (Regla 4)
        String tamanoRequerido;
        if (p.getPeso() < 5.0) {
            tamanoRequerido = "S";
        } else if (p.getPeso() <= 25.0) {
            tamanoRequerido = "M";
        } else {
            tamanoRequerido = "L";
        }

        // Condición especial para aves grandes (Regla 4)
        if (p instanceof AveExotica) {
            AveExotica ave = (AveExotica) p;
            if (ave.getEnvergadura() > 60.0 && tamanoRequerido.equals("S")) {
                tamanoRequerido = "M";
            }
        }

        // Buscar primera jaula libre compatible (Regla 6)
        Jaula jaulaCompatible = null;
        for (int i = 0; i < jaulas.length; i++) {
            if (jaulas[i].isLibre() && jaulas[i].getZona().equals(zonaRequerida) && jaulas[i].getTamano().equals(tamanoRequerido)) {
                jaulaCompatible = jaulas[i];
                break; // Toma la primera que encuentra
            }
        }

        if (jaulaCompatible == null) {
            return "ERROR: no hay jaula compatible";
        }

        // Enlazar paciente y jaula
        p.setJaula(jaulaCompatible);
        jaulaCompatible.setOcupante(p);

        return "OK: " + nombre + " asignado a jaula " + jaulaCompatible.getNumero();
    }

    // --- COMANDOS FINALES: MEDICAR, ALTA Y REPORTE ---

    public String medicar(String nombre, double mg) {
        Paciente p = buscarPaciente(nombre);

        // 1. Validaciones en orden estricto (Regla 8)
        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (mg <= 0) {
            return "ERROR: datos invalidos";
        }
        if (p.getEstado() == Paciente.EstadoPaciente.CUARENTENA) {
            return "ERROR: paciente en cuarentena";
        }
        if (p.getJaula() == null) {
            return "ERROR: paciente sin jaula";
        }
        if (p instanceof Gato && ((Gato) p).getNivelEstres() >= 8) {
            return "ERROR: gato con estres alto";
        }

        // 2. Cálculos de dosis (Regla 7)
        double dosisMaxima = p.getPeso() * p.getFactorDosis();
        double topeAcumulado = dosisMaxima * 3;

        if (mg > dosisMaxima) {
            return "ERROR: dosis excede el maximo";
        }
        if ((p.getDosisAcumulada() + mg) > topeAcumulado) {
            return "ERROR: tope acumulado superado";
        }

        // 3. Aplicar medicación (Regla 9)
        p.setDosisAcumulada(p.getDosisAcumulada() + mg);
        p.setEstado(Paciente.EstadoPaciente.EN_TRATAMIENTO);

        // Si es gato, sube el estrés 2 puntos, máximo 10
        if (p instanceof Gato) {
            Gato gato = (Gato) p;
            int nuevoEstres = gato.getNivelEstres() + 2;
            if (nuevoEstres > 10) {
                nuevoEstres = 10;
            }
            gato.setNivelEstres(nuevoEstres);
        }

        // Se usa String.format para mostrar decimales prolijamente (opcional según el test, pero evita notación científica)
        // Convertimos mg a entero si no tiene decimales para que el mensaje coincida exacto
        String mgStr = (mg % 1 == 0) ? String.valueOf((int)mg) : String.valueOf(mg);
        return "OK: " + nombre + " medicado con " + mgStr + " mg";
    }

    public String alta(String nombre) {
        // Regla 10: Buscar y validar
        int indice = -1;
        Paciente p = null;
        for (int i = 0; i < cantPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                indice = i;
                p = pacientes[i];
                break;
            }
        }

        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (p.getEstado() != Paciente.EstadoPaciente.EN_TRATAMIENTO) {
            return "ERROR: paciente no apto para alta";
        }

        // 1. Liberar la jaula
        if (p.getJaula() != null) {
            p.getJaula().setOcupante(null);
            p.setJaula(null);
        }

        // 2. Compactar el vector desplazando los elementos hacia la izquierda
        for (int i = indice; i < cantPacientes - 1; i++) {
            pacientes[i] = pacientes[i + 1];
        }

        // Disminuir contador y limpiar la última posición que quedó duplicada
        cantPacientes--;
        pacientes[cantPacientes] = null;

        return "OK: " + nombre + " dado de alta";
    }

    public String obtenerReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE\n");
        sb.append("Pacientes: ").append(cantPacientes).append("/10\n");

        for (int i = 0; i < cantPacientes; i++) {
            Paciente p = pacientes[i];

            // Determinar tipo
            String tipo = "";
            if (p instanceof Perro) tipo = "Perro";
            else if (p instanceof Gato) tipo = "Gato";
            else if (p instanceof AveExotica) tipo = "Ave";

            // Formatear peso a 1 decimal y reemplazar comas por puntos si el sistema está en español
            String pesoFormateado = String.format("%.1f", p.getPeso()).replace(",", ".");

            // Determinar jaula
            String jaulaStr = (p.getJaula() != null) ? String.valueOf(p.getJaula().getNumero()) : "-";

            // Armar la línea exacta: <nombre> | <tipo> | <peso> kg | <estado> | jaula: <n o ->
            sb.append(p.getNombre()).append(" | ")
                    .append(tipo).append(" | ")
                    .append(pesoFormateado).append(" kg | ")
                    .append(p.getEstado()).append(" | jaula: ")
                    .append(jaulaStr).append("\n");
        }

        // Contar jaulas libres
        int libres = 0;
        for (int i = 0; i < jaulas.length; i++) {
            if (jaulas[i].isLibre()) {
                libres++;
            }
        }
        sb.append("Jaulas libres: ").append(libres);

        return sb.toString();
    }
}