package flujos;

import dao.EventoMedicoDao;
import enums.TipoEvento;
import models.EventoMedico;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EventoMedicoMenu {
    private final EventoMedicoDao dao;
    private final Scanner sc;

    public EventoMedicoMenu()
    {
        this.sc = new Scanner(System.in);
        this.dao = new EventoMedicoDao();
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
        System.out.print("ID ENFERMEDAD: ");
        int idEnfermedad = sc.nextInt();
        sc.nextLine();
        System.out.print("ID MEDICAMENTO: ");
        int idMedicamento = sc.nextInt();
        sc.nextLine();
        System.out.print("Fecha (Año-Mes-Dia): ");
        Date fecha = Date.valueOf(sc.nextLine());
        sc.nextLine();
        System.out.print("CÓDIGO ANIMAL: ");
        int idAniaml = sc.nextInt();
        sc.nextLine();
        System.out.print("EVENTO (VACUNACIÓN, REVISIÓN, PARTO, CIRUGÍA, REHABILITACIÓN) : ");
        String tipo = sc.nextLine();
        EventoMedico evento = new EventoMedico();
        evento.setFecha(fecha);
        evento.setEvento(TipoEvento.valueOf(tipo.trim().toUpperCase()));
        evento.setCodigoAnimal(idAniaml);
        evento.setIdRegistroMedicamento(idMedicamento);
        evento.setIdRegistroDeEnfermedad(idEnfermedad);
        dao.crearEventoMedico(evento);
    }

    private void listarEventoMedico() throws SQLException{
        List<EventoMedico> eventos = dao.listarEventosMedico();
        if (eventos.isEmpty()) {
            System.out.println("Evento medico vacío.");
        } else {
            for(EventoMedico EVENTO: eventos){
                EVENTO.mostrarEventoMedico();
            }
        }
    }

    private void editarEventoMedico() throws SQLException{
        System.out.print("ID DE EVENTO A EDITAR: ");
        int idEvento = sc.nextInt();
        sc.nextLine();
        System.out.print("FECHA (AÑO-MES-DIA): ");
        Date fecha = Date.valueOf(sc.nextLine());
        sc.nextLine();
        System.out.print("EVENTO (VACUNACIÓN, REVISIÓN, PARTO, CIRUGÍA, REHABILITACIÓN) : ");
        String evento = sc.nextLine();
        dao.editarEventoMedico(idEvento, fecha, evento);
    }

    private void borrarEventoMedico() throws SQLException{
        System.out.print("ID DE EVENTO A ELIMINAR: ");
        int idEvento = sc.nextInt();
        sc.nextLine();
        dao.borrarEventoMedico(idEvento);
    }
}
