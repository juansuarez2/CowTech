package flujos;

import dao.registroDeEnfermedadDao;
import models.registroEnfermedad;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

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
                case 2 -> listarRegistroEnfermedad();
                case 3 -> editarRegistroEnfermedad();
                case 4 -> borrarRegistroEnfermedad();
                default -> System.out.println("❌ Opción inválida.");
            }
        } catch (SQLException e) {
            System.out.println("💥 Error: " + e.getMessage());
        }
    }

    private void crearRegistroEnfermedad() throws SQLException{
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
        dao.crearRegistroEnfermedad(new registroEnfermedad(idEnfermedad, idAnimal, fechaInicio, fechaFinal, estado));
    }

    private void listarRegistroEnfermedad() throws SQLException{
        List<registroEnfermedad> registros = dao.listarRegistroEnfermedad();
        if (registros.isEmpty()) {
            System.out.println("Evento medico vacío.");
        } else {
            for(registroEnfermedad REGISTRO: registros){
                REGISTRO.mostrarRegistro();
            }
        }
    }

    private void editarRegistroEnfermedad() throws SQLException{
        System.out.print("ID DEL REGISTRO A EDITAR: ");
        int idRegistro = sc.nextInt();
        sc.nextLine();
        System.out.print("ID DE LA ENFERMEDAD: ");
        int idEnfermedad = sc.nextInt();
        sc.nextLine();
        System.out.print("CÓDIGO DEL ANIMAL: ");
        int idAnimal = sc.nextInt();
        sc.nextLine();
        System.out.print("NUEVA FECHA DE INICIO(AÑO-MES-DIA): ");
        Date fechaIni = Date.valueOf(sc.nextLine());
        sc.nextLine();
        System.out.print("NUEVA FECHA FINAL (AÑO-MES-DIA): ");
        Date fechaFin = Date.valueOf(sc.nextLine());
        sc.nextLine();
        System.out.print("NUEVO ESTADO: ");
        boolean estado = sc.nextBoolean();
        sc.nextLine();
        dao.editarRegistroEnfermedad(idRegistro, idEnfermedad, idAnimal, fechaIni, fechaFin, estado);
    }

    private void borrarRegistroEnfermedad() throws SQLException{
        System.out.print("ID DEL REGISTRO DE ENFERMEDAD A ELIMINAR: ");
        int id = sc.nextInt();
        sc.nextLine();
        dao.borrarRegistroEnfermedad(id);
    }
}
