package flujos;

import dao.EnfermedadDAO;
import models.Enfermedad;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EnfermedadMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final EnfermedadDAO enfermedadDAO = new EnfermedadDAO();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- MENU ENFERMEDAD ---");
            System.out.println("1. Crear enfermedad");
            System.out.println("2. Listar enfermedades");
            System.out.println("3. Buscar enfermedad por ID");
            System.out.println("4. Modificar enfermedad");
            System.out.println("5. Eliminar enfermedad");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> crearEnfermedad();
                case 2 -> listarEnfermedades();
                case 3 -> buscarEnfermedad();
                case 4 -> modificarEnfermedad();
                case 5 -> eliminarEnfermedad();
                case 6 -> System.out.println("Saliendo del menú...");
                default -> System.out.println("⚠️ Opción inválida.");
            }
        } while (opcion != 6);
    }

    private void crearEnfermedad() {
        try {
            System.out.println("\n-- CREAR ENFERMEDAD --");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            Enfermedad enfermedad = new Enfermedad(nombre);
            enfermedadDAO.crear(enfermedad);
            System.out.println("✅ Enfermedad creada con éxito.");
        } catch (SQLException e) {
            System.out.println("⚠️ Error al crear enfermedad: " + e.getMessage());
        }
    }

    private void listarEnfermedades() {
        try {
            System.out.println("\n-- LISTAR ENFERMEDADES --");
            List<Enfermedad> enfermedades = enfermedadDAO.listar();
            if (enfermedades.isEmpty()) {
                System.out.println("No hay enfermedades registradas.");
            } else {
                for (Enfermedad e : enfermedades) {
                    imprimirEnfermedad(e);
                }
            }
        } catch (SQLException e) {
            System.out.println("⚠️ Error al listar enfermedades: " + e.getMessage());
        }
    }

    private void buscarEnfermedad() {
        try {
            System.out.println("\n-- BUSCAR ENFERMEDAD --");
            System.out.print("ID: ");
            int id = leerEntero();

            Enfermedad enfermedad = enfermedadDAO.buscarPorId(id);
            if (enfermedad != null) {
                imprimirEnfermedad(enfermedad);
            } else {
                System.out.println("⚠️ Enfermedad no encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("⚠️ Error al buscar enfermedad: " + e.getMessage());
        }
    }

    private void modificarEnfermedad() {
        try {
            System.out.println("\n-- MODIFICAR ENFERMEDAD --");
            System.out.print("ID de la enfermedad a modificar: ");
            int id = leerEntero();

            Enfermedad enfermedad = enfermedadDAO.buscarPorId(id);
            if (enfermedad == null) {
                System.out.println("⚠️ Enfermedad no encontrada.");
                return;
            }

            imprimirEnfermedad(enfermedad);

            System.out.print("¿Quieres modificar esta enfermedad? (s/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.println("❌ Operación cancelada.");
                return;
            }

            System.out.print("Nuevo nombre (actual " + enfermedad.getNombre() + "): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) enfermedad.setNombre(nuevoNombre);

            enfermedadDAO.modificar(enfermedad);
            System.out.println("✅ Enfermedad modificada con éxito.");
        } catch (SQLException e) {
            System.out.println("⚠️ Error al modificar enfermedad: " + e.getMessage());
        }
    }

    private void eliminarEnfermedad() {
        try {
            System.out.println("\n-- ELIMINAR ENFERMEDAD --");
            System.out.print("ID de la enfermedad a eliminar: ");
            int id = leerEntero();

            Enfermedad enfermedad = enfermedadDAO.buscarPorId(id);
            if (enfermedad == null) {
                System.out.println("⚠️ Enfermedad no encontrada.");
                return;
            }

            imprimirEnfermedad(enfermedad);

            System.out.print("¿Seguro que deseas eliminar esta enfermedad? (s/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.println("❌ Operación cancelada.");
                return;
            }

            enfermedadDAO.eliminar(id);
            System.out.println("✅ Enfermedad eliminada con éxito.");
        } catch (SQLException e) {
            System.out.println("⚠️ Error al eliminar enfermedad: " + e.getMessage());
        }
    }

    // --- Métodos auxiliares ---
    private void imprimirEnfermedad(Enfermedad e) {
        System.out.println("ID: " + e.getId() + " | Nombre: " + e.getNombre());
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Ingresa un número válido: ");
            }
        }
    }

    // --- Punto de entrada de prueba ---
    public static void main(String[] args) {
        new EnfermedadMenu().mostrarMenu();
    }
}
