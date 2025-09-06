package flujo;

import models.eventoMedico;
import dao.eventoMedicoDao;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

public class eventoMedicoMenu {
    private final eventoMedicoDao dao;
    private final Scanner sc;

    public eventoMedicoMenu()
    {
        this.sc = new Scanner(System.in);
        this.dao = new eventoMedicoDao();
    }

    public void iniciar() {
        while (true) {
            mostrarMenu();
            int opcion = leerOpcion();
            ejecutarOpcion(opcion);
        }
    }

    private void mostrarMenu() {
        System.out.println("\n=== Gestión de Eventos ===");
        System.out.println("1. Crear evento");
        System.out.println("2. Listar eventos");
        System.out.println("3. Editar evento");
        System.out.println("4. Borrar evento");
        System.out.print("Opción: ");
    }

    private int leerOpcion() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        int opcion = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        return opcion;
    }

    private void ejecutarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> crearEventoMedico();
                case 2 -> listarEventoMedico();
                //case 3 -> editarEventoMedico();
                //case 4 -> borrarEventoMedico();
                default -> System.out.println("❌ Opción inválida.");
            }
        } catch (SQLException e) {
            System.out.println("💥 Error: " + e.getMessage());
        }
    }

    private void crearEventoMedico() throws SQLException{
        System.out.print("ID EVENTO: ");
        int idEvento = sc.nextInt();
        sc.nextLine();
        System.out.print("ID ENFERMEDAD: ");
        int idEnfermedad = sc.nextInt();
        sc.nextLine();
        System.out.print("ID MEDICAMENTO: ");
        int idMedicamento = sc.nextInt();
        sc.nextLine();
        System.out.print("Fecha (Año-Mes-Dia): ");
        Date fecha = Date.valueOf(sc.nextLine());
        sc.nextLine();
        System.out.print("CODIGO ANIMAL: ");
        int idAniaml = sc.nextInt();
        sc.nextLine();
        System.out.print("EVENTO: ");
        String tipoEvento = sc.nextLine();
        dao.crearEventoMedico(new eventoMedico(idEvento, fecha, eventoMedico.tipoEvento.valueOf(tipoEvento), idAniaml, idMedicamento, idEnfermedad));
    }

    private void listarEventoMedico() throws SQLException{
        List<eventoMedico> eventos = null;//dao.listarUsuarios();
        if (eventos.isEmpty()) {
            System.out.println("Evento medico vacio.");
        } else {
            for(eventoMedico EVENTO: eventos){
                EVENTO.mostrarEventoMedico();
            }
        }
    }
}
