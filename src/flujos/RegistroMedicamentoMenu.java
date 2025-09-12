package flujos;

import dao.RegistroMedicamentoDAO;
import models.RegistroMedicamento;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class RegistroMedicamentoMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final RegistroMedicamentoDAO dao;

    public RegistroMedicamentoMenu(RegistroMedicamentoDAO dao) {
        this.dao = dao;
    }

    public void mostrarMenu() {
        try {
            System.out.println("\n=== Gestión de Registros de Medicamento ===");
            System.out.println("1. Registrar nuevo");
            System.out.println("2. Modificar registro");
            System.out.println("3. Eliminar registro");
            System.out.println("4. Buscar registro por ID");
            System.out.println("5. Listar todos los registros");
            System.out.println("6. Listar registros por medicamento");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    modificarRegistro();
                    break;
                case 3:
                    eliminarRegistro();
                    break;
                case 4:
                    buscarRegistroPorId();
                    break;
                case 5:
                    listarTodos();
                    break;
                case 6:
                    listarPorMedicamento();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void registrar() {
        try {
            System.out.print("ID del medicamento: ");
            int idMedicamento = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Duración (días): ");
            int duracion = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Fecha de inicio (yyyy-MM-dd): ");
            String fechaStr = scanner.nextLine();
            LocalDate fechaInicio = LocalDate.parse(fechaStr);
            RegistroMedicamento r = new RegistroMedicamento(0, idMedicamento, duracion, fechaInicio);
            dao.insertar(r);
            System.out.println("Registro de medicamento creado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }

    private void modificarRegistro() {
        try {
            System.out.print("ID del registro a modificar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nuevo ID de medicamento: ");
            int idMedicamento = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nueva duración (días): ");
            int duracion = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nueva fecha de inicio (yyyy-MM-dd): ");
            String fechaStr = scanner.nextLine();
            LocalDate fechaInicio = LocalDate.parse(fechaStr);
            RegistroMedicamento r = new RegistroMedicamento(id, idMedicamento, duracion, fechaInicio);
            dao.actualizar(r);
            System.out.println("Registro modificado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al modificar: " + e.getMessage());
        }
    }

    private void eliminarRegistro() {
        try {
            System.out.print("ID del registro a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            dao.eliminar(id);
            System.out.println("Registro eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    private void buscarRegistroPorId() {
        try {
            System.out.print("ID del registro a buscar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            RegistroMedicamento r = dao.buscarPorId(id);
            if (r != null) {
                System.out.println(r);
            } else {
                System.out.println("No se encontró el registro.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
    }

    private void listarTodos() {
        try {
            List<RegistroMedicamento> lista = dao.listar();
            if (lista.isEmpty()) {
                System.out.println("No hay registros de medicamento.");
            } else {
                lista.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }

    private void listarPorMedicamento() {
        try {
            System.out.print("ID del medicamento: ");
            int idMedicamento = scanner.nextInt();
            scanner.nextLine();
            List<RegistroMedicamento> lista = dao.listarPorMedicamento(idMedicamento);
            if (lista.isEmpty()) {
                System.out.println("No hay registros para este medicamento.");
            } else {
                lista.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al listar por medicamento: " + e.getMessage());
        }
    }
}
