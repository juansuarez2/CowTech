package flujos;

import dao.RecordatorioDAO;
import models.Recordatorio;
import models.Usuario;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class RecordatorioMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final RecordatorioDAO recordatorioDAO;

    public RecordatorioMenu(RecordatorioDAO recordatorioDAO) {
        this.recordatorioDAO = recordatorioDAO;
    }

    public void mostrarMenu() {
        try {
            System.out.println("\n=== Gestión de Recordatorios ===");
            System.out.println("1. Enviar recordatorio");
            System.out.println("2. Modificar recordatorio");
            System.out.println("3. Eliminar recordatorio");
            System.out.println("4. Buscar recordatorio por ID");
            System.out.println("5. Listar recordatorios por usuario");
            System.out.println("6. Listar todos los recordatorios");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1: enviarRecordatorio(); break;
                case 2: modificarRecordatorio(); break;
                case 3: eliminarRecordatorio(); break;
                case 4: buscarRecordatorioPorId(); break;
                case 5: listarRecordatoriosPorUsuario(); break;
                case 6: listarTodosRecordatorios(); break;
                case 7: break;
                default: System.out.println("Opción inválida");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void enviarRecordatorio() {
        try {
            System.out.print("ID del usuario: ");
            int usuarioId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Mensaje: ");
            String mensaje = scanner.nextLine();
            System.out.print("Fecha de envío (yyyy-MM-dd HH:mm): ");
            String fechaStr = scanner.nextLine();
            LocalDateTime fecha = LocalDateTime.parse(fechaStr.replace(" ", "T"));
            Recordatorio r = new Recordatorio();
            r.setMensaje(mensaje);
            r.setFechaDeEnvio(fecha);
            r.setEstado(false); // pendiente de enviar
            r.setUsuarioId(usuarioId);
            recordatorioDAO.insertar(r);
            System.out.println("Recordatorio enviado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al enviar recordatorio: " + e.getMessage());
        }
    }

    private void modificarRecordatorio() {
        try {
            System.out.print("ID del recordatorio a modificar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nuevo mensaje: ");
            String mensaje = scanner.nextLine();
            System.out.print("Nueva fecha de envío (yyyy-MM-dd HH:mm): ");
            String fechaStr = scanner.nextLine();
            LocalDateTime fecha = LocalDateTime.parse(fechaStr.replace(" ", "T"));
            System.out.print("Nuevo estado (true/false): ");
            boolean estado = scanner.nextBoolean();
            scanner.nextLine();
            System.out.print("Nuevo ID de usuario: ");
            int usuarioId = scanner.nextInt();
            scanner.nextLine();
            Recordatorio r = new Recordatorio(id, mensaje, fecha, estado, usuarioId);
            recordatorioDAO.actualizar(r);
            System.out.println("Recordatorio modificado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al modificar recordatorio: " + e.getMessage());
        }
    }

    private void eliminarRecordatorio() {
        try {
            System.out.print("ID del recordatorio a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            recordatorioDAO.eliminar(id);
            System.out.println("Recordatorio eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar recordatorio: " + e.getMessage());
        }
    }

    private void buscarRecordatorioPorId() {
        try {
            System.out.print("ID del recordatorio a buscar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Recordatorio r = recordatorioDAO.buscarPorId(id);
            if (r != null) {
                System.out.println(r);
            } else {
                System.out.println("No se encontró el recordatorio.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar recordatorio: " + e.getMessage());
        }
    }

    private void listarRecordatoriosPorUsuario() {
        try {
            System.out.print("ID del usuario: ");
            int usuarioId = scanner.nextInt();
            scanner.nextLine();
            List<Recordatorio> lista = recordatorioDAO.listarPorUsuario(usuarioId);
            if (lista.isEmpty()) {
                System.out.println("No hay recordatorios para este usuario.");
            } else {
                lista.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al listar recordatorios: " + e.getMessage());
        }
    }

    private void listarTodosRecordatorios() {
        try {
            System.out.print("ID del usuario para listar recordatorios: ");
            int usuarioId = scanner.nextInt();
            scanner.nextLine();
            List<Recordatorio> lista = recordatorioDAO.listarPorUsuario(usuarioId);
            if (lista.isEmpty()) {
                System.out.println("No hay recordatorios para este usuario.");
            } else {
                lista.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al listar recordatorios: " + e.getMessage());
        }
    }
}
