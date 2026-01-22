import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Radio radio = new RadioImpl();
        boolean continuar = true;

        System.out.println("-------------------------------------");
        System.out.println("   SIMULADOR DEL RADIO DE CARRO    ");
        System.out.println("-------------------------------------\n");

        while (continuar) {
            mostrarMenu();

            try {
                System.out.print("Selecciona una opción: ");
                int opcion = scanner.nextInt();
                System.out.println();

                switch (opcion) {
                    case 1:
                        radio.prenderRadio();
                        break;

                    case 2:
                        radio.cambiarAM();
                        break;

                    case 3:
                        radio.cambiarFM();
                        break;

                    case 4:
                        radio.avanzarEstacion();
                        break;

                    case 5:
                        System.out.print("Ingresa el número de botón (1-12): ");
                        int botonGuardar = scanner.nextInt();
                        radio.guardarEstacion(botonGuardar);
                        break;

                    case 6:
                        System.out.print("Ingresa el número de botón (1-12): ");
                        int botonCargar = scanner.nextInt();
                        radio.cargarEstacion(botonCargar);
                        break;

                    case 7:
                        if (radio instanceof RadioImpl) {
                            ((RadioImpl) radio).mostrarBotonesGuardados();
                        }
                        break;

                    case 8:
                        radio.apagarRadio();
                        break;

                    case 9:
                        System.out.println("Saliendo del programa...");
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opción incorrecta. Intenta de nuevo.\n");
                }

            } catch (Exception e) {
                System.out.println("Error: Entrada incorrecta. Intenta de nuevo.\n");
                scanner.nextLine();
            }

            if (continuar) {
                esperarEnter(scanner);
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n-------------------------------------");
        System.out.println("|             MENU RADIO            |");
        System.out.println("-------------------------------------");
        System.out.println("| 1. Prende el radio                |");
        System.out.println("| 2. Cambiar a AM                   |");
        System.out.println("| 3. Cambiar a FM                   |");
        System.out.println("| 4. Avanzar en el dial             |");
        System.out.println("| 5. Guardar emisora en botón       |");
        System.out.println("| 6. Seleccionar emisora guardada   |");
        System.out.println("| 7. Ver botones guardados          |");
        System.out.println("| 8. Apagar el radio                |");
        System.out.println("| 9. Salir                          |");
        System.out.println("-------------------------------------");
    }

    private static void esperarEnter(Scanner scanner) {
        System.out.println("\nPresiona Enter para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }
}