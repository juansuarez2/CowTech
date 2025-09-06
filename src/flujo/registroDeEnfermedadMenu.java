package flujo;

import models.registroDeEnfermedad;
import dao.registroDeEnfermedadDao;
import java.sql.*;
import java.util.Scanner;
import java.util.List;

public class registroDeEnfermedadMenu {
    private final registroDeEnfermedadDao dao;
    private final Scanner sc;

    public registroDeEnfermedadMenu()
    {
        this.sc = new Scanner(System.in);
        this.dao = new registroDeEnfermedadDao();
    }

    public void iniciar() {
        while (true) {
            mostrarMenu();
            int opcion = leerOpcion();
            ejecutarOpcion(opcion);
        }
    }

    private void mostrarMenu() {
        System.out.println("\n=== Gestión de Registros de Enfermedad ===");
        System.out.println("1. Crear registro");
        System.out.println("2. Listar registros");
        System.out.println("3. Editar registro");
        System.out.println("4. Borrar registro");

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
                case 1 -> crearRegistroEnfermedad();
                //case 2 -> listarRegistrosEnfermedad();
                //case 3 -> editarEventoMedico();
                //case 4 -> borrarEventoMedico();
                default -> System.out.println("❌ Opción inválida.");
            }
        } catch (SQLException e) {
            System.out.println("💥 Error: " + e.getMessage());
        }
    }

    private void crearRegistroEnfermedad() throws SQLException{
        System.out.print("ID REGISTRO: ");
        int idRegistro = sc.nextInt();
        sc.nextLine();
        System.out.print("ID ENFERMEDAD: ");
        int idEnfermedad = sc.nextInt();
        sc.nextLine();
        System.out.print("ID ANIMAL: ");
        int idAnimal = sc.nextInt();
        sc.nextLine();
        System.out.print("FECHA INICIO(AÑO-MES-DIA): ");
        Date fechaInicio = Date.valueOf(sc.nextLine());
        sc.nextLine();
        System.out.print("FECHA FINAL(AÑO-MES-DIA): ");
        Date fechaFinal = Date.valueOf(sc.nextLine());
        sc.nextLine();
        System.out.print("ESTADO: ");
        boolean estado = sc.nextBoolean();
        sc.nextLine();
        String tipoEvento = sc.nextLine();
        dao.crearRegistroEnfermedad(new registroDeEnfermedad(idRegistro, idEnfermedad, idAnimal, fechaInicio, fechaFinal, estado));
    }
}
