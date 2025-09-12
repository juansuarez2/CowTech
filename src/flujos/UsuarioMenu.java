package flujos;

import dao.UsuarioDao;
import models.Usuario;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UsuarioMenu {
    int opcion;
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioDao usuarioDao;


    public UsuarioMenu() {
        this.usuarioDao = new UsuarioDao();
    }


    public void mostrarMenu() {
        do {
            System.out.println("\n== Gestion de Usuarios ==");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Listar Usuario");
            System.out.println("3. Editar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("0. Salir");
            System.out.println("Opcion:");

            opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1:
                        crearUsuario();
                        break;
                    case 2:
                        listarUsuarios();
                        break;
                    case 3:
                        editarUsuario();
                        break;
                    case 4:
                        eliminarUsuario();
                        break;
                    case 0://salir
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Presiona Enter para continuar...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine(); // Espera hasta que el usuario presione Enter
            System.out.println("¡Continuamos!");

        } while (opcion != 0);

    }

    private void listarUsuarios() throws SQLException {
        List<Usuario> usuarios = usuarioDao.listarUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("Sin usuarios para listar");
        } else {

            for (int i = 0; i < usuarios.size(); i++) {
                Usuario valor = usuarios.get(i);
                valor.mostrarInformacion();

            }
        }
    }

    private void crearUsuario() throws ParseException, SQLException {

        System.out.println("Nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Apellido:");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
        String fechaTexto = scanner.nextLine();

        System.out.println("Correo:");
        String email = scanner.nextLine();

        System.out.println("Password:");
        String password = scanner.nextLine();


        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");//pasar la fecha de texto a tipo date
        Date fechaNacimiento = formato.parse(fechaTexto);

        Usuario nuevoUsuario = new Usuario(0, nombre, apellido, email, password, fechaNacimiento);// Crear objeto Usuario (id = 0 si es autoincremental)

        usuarioDao.crearUsuario(nuevoUsuario);// Guardar en la base de datos

    }

    private void editarUsuario() throws ParseException, SQLException {
        System.out.println("Ingrese ID de usuario a modificar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el salto de línea pendiente

        Usuario usuario = usuarioDao.obtenerPorId(id);

        if (usuario == null) {
            System.out.println("Usuario con ID " + id + " no encontrado.");
            return;
        }


        System.out.println("\nNuevo Nombre:");
        String nombre = scanner.nextLine();

        System.out.println("\nNuevo Apellido:");
        String apellido = scanner.nextLine();

        System.out.println("Nuevo Correo:");
        String email = scanner.nextLine();

        System.out.println("\nNueva Password:");
        String password = scanner.nextLine();

        System.out.print("Nueva fecha de nacimiento (dd/MM/yyyy): ");
        String fechaTexto = scanner.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = formato.parse(fechaTexto);


        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setFechaNacimiento(fechaNacimiento);

        usuarioDao.editarUsuario(usuario);
    }

    private void eliminarUsuario() throws SQLException {
        System.out.println("Ingrese ID de usuario a ELIMINAR:");
        int id = scanner.nextInt();
        scanner.nextLine();
        usuarioDao.eliminarUsuario(id);
    }
}
