import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        hospital.cargarDatosIniciales(); // Carga de datos por defecto

        Scanner scanner = new Scanner(System.in);
        boolean ejecutando = true;
        System.out.println("---Bienvenido---");
        System.out.println("Tenga en cuenta las siguiente estructura para su registro:");
        System.out.println("Ingresar: ingresar tipoanimal nombre peso edad parametroEspecíficoDelAnimal");
        System.out.println("Vacunar: vacunar y nombreAnimal");
        System.out.println("Asignar una jaula: asignar nombreAnimal");
        System.out.println("Medicar: medicar nombreAnimal y dosis");
        System.out.println("Dar de alta: alta nombreAnimal");
        System.out.println("Generar un reporte: reporte");
        System.out.println("Salir del sistema: salir");
        while (ejecutando) {
            String linea = scanner.nextLine().trim();
            if (linea.isEmpty()) continue;

            String[] partes = linea.split(" ");
            String comando = partes[0].toLowerCase();

            try {
                switch (comando) {
                    case "ingresar":
                        if (partes.length < 5) {
                            System.out.println("ERROR: comando desconocido");
                            break;
                        }
                        String tipo = partes[1].toLowerCase();
                        String nombre = partes[2];
                        double peso = Double.parseDouble(partes[3]);
                        int edad = Integer.parseInt(partes[4]);

                        if (tipo.equals("perro") && partes.length == 7) {
                            String raza = partes[5];
                            boolean vacunado = partes[6].equalsIgnoreCase("si");
                            System.out.println(hospital.ingresarPerro(nombre, peso, edad, raza, vacunado));
                        } else if (tipo.equals("gato") && partes.length == 5) {
                            System.out.println(hospital.ingresarGato(nombre, peso, edad));
                        } else if (tipo.equals("ave") && partes.length == 6) {
                            double envergadura = Double.parseDouble(partes[5]);
                            System.out.println(hospital.ingresarAve(nombre, peso, edad, envergadura));
                        } else {
                            System.out.println("ERROR: comando desconocido");
                        }
                        break;

                    case "vacunar":
                        if (partes.length == 2) {
                            System.out.println(hospital.vacunar(partes[1]));
                        } else {
                            System.out.println("ERROR: comando desconocido");
                        }
                        break;

                    case "asignar":
                        if (partes.length == 2) {
                            System.out.println(hospital.asignar(partes[1]));
                        } else {
                            System.out.println("ERROR: comando desconocido");
                        }
                        break;

                    case "medicar":
                        if (partes.length == 3) {
                            double mg = Double.parseDouble(partes[2]);
                            System.out.println(hospital.medicar(partes[1], mg));
                        } else {
                            System.out.println("ERROR: comando desconocido");
                        }
                        break;

                    case "alta":
                        if (partes.length == 2) {
                            System.out.println(hospital.alta(partes[1]));
                        } else {
                            System.out.println("ERROR: comando desconocido");
                        }
                        break;

                    case "reporte":
                        System.out.println(hospital.obtenerReporte());
                        break;

                    case "salir":
                        ejecutando = false;
                        System.out.println("Fue un placer atenderlo. Siempre será bienvenido usted y su mascota. Hasta pronto.");
                        break;

                    default:
                        System.out.println("ERROR: comando desconocido");
                        break;
                }
            } catch (Exception e) {
                // Si el usuario ingresa texto en lugar de números para peso, edad o mg
                System.out.println("ERROR: datos invalidos");
            }
        }
        scanner.close();
    }
}
