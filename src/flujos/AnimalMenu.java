package flujos;

import dao.AnimalDAO;
import models.Animal;
import models.Animal.Sexo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AnimalMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final AnimalDAO dao = new AnimalDAO();

    public static void main(String[] args) {
        new AnimalMenu().start();
    }

    public void start() {
        while (true) {
            System.out.println("\n--- MENU ANIMAL ---");
            System.out.println("1. Crear animal");
            System.out.println("2. Listar animales");
            System.out.println("3. Buscar animal por ID");
            System.out.println("4. Modificar animal");
            System.out.println("5. Eliminar animal");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            String opcion = scanner.nextLine().trim();
            try {
                switch (opcion) {
                    case "1" -> crearAnimal();
                    case "2" -> listarAnimales();
                    case "3" -> buscarAnimal();
                    case "4" -> modificarAnimal();
                    case "5" -> eliminarAnimal();
                    case "6" -> {
                        System.out.println("Saliendo...");
                        return;
                    }
                    default -> System.out.println("Opción inválida. Intenta de nuevo.");
                }
            } catch (SQLException e) {
                System.out.println("Error de base de datos: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void crearAnimal() throws SQLException {
        System.out.println("\n-- CREAR ANIMAL --");
        String codigo = readNonEmptyString("Código: ");
        Integer idLote = readNullableInt("Id lote (enter para null): ");
        int idRaza = readInt("Id raza: ");
        float peso = readFloat("Peso: ");
        Sexo sexo = readSexo("Sexo (HEMBRA/MACHO): ");
        Date fechaNacimiento = readDate("Fecha nacimiento (YYYY-MM-DD): ", true);
        String observacion = readString("Observación (enter para dejar vacio): ");
        boolean vacunaObligatoria = readBoolean("Vacuna obligatoria? (s/n): ");
        Date fechaExpiracion = readDate("Fecha expiración vacuna (YYYY-MM-DD) (enter para null): ", false);
        int idUsuario = readInt("Id usuario: ");

        Animal animal = new Animal();
        animal.setCodigo(codigo);
        animal.setIdLote(idLote);
        animal.setIdRaza(idRaza);
        animal.setPeso(peso);
        animal.setSexo(sexo);
        animal.setFechaNacimiento(fechaNacimiento);
        animal.setObservacion(observacion.isEmpty() ? null : observacion);
        animal.setVacunaObligatoria(vacunaObligatoria);
        animal.setFechaExpiracionDeVacuna(fechaExpiracion);
        animal.setIdUsuario(idUsuario);

        dao.crear(animal);
        System.out.println("Animal creado correctamente.");
    }

    private void listarAnimales() throws SQLException {
        System.out.println("\n-- LISTAR ANIMALES --");
        List<Animal> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay animales registrados.");
            return;
        }
        for (Animal a : lista) {
            printAnimal(a);
            System.out.println("----------------------------");
        }
    }

    private void buscarAnimal() throws SQLException {
        System.out.println("\n-- BUSCAR ANIMAL --");
        int id = readInt("ID: ");
        Animal a = dao.buscarPorId(id);
        if (a == null) {
            System.out.println("Animal no encontrado con id = " + id);
        } else {
            printAnimal(a);
        }
    }

    private void modificarAnimal() throws SQLException {
        System.out.println("\n-- MODIFICAR ANIMAL --");
        int id = readInt("ID del animal a modificar: ");
        Animal a = dao.buscarPorId(id);
        if (a == null) {
            System.out.println("No existe animal con id = " + id);
            return;
        }

        System.out.println("Dejar en blanco para mantener el valor actual.");
        System.out.println("Valor actual -> ");
        printAnimal(a);

        String codigo = readString("Código [" + a.getCodigo() + "]: ");
        String sIdLote = readString("Id lote [" + (a.getIdLote() == null ? "null" : a.getIdLote()) + "]: ");
        String sIdRaza = readString("Id raza [" + a.getIdRaza() + "]: ");
        String sPeso = readString("Peso [" + a.getPeso() + "]: ");
        String sSexo = readString("Sexo (HEMBRA/MACHO) [" + a.getSexo() + "]: ");
        String sFechaNac = readString("Fecha nacimiento (YYYY-MM-DD) [" + a.getFechaNacimiento() + "]: ");
        String observacion = readString("Observación [" + (a.getObservacion() == null ? "" : a.getObservacion()) + "]: ");
        String sVac = readString("Vacuna obligatoria? (s/n) [" + (a.isVacunaObligatoria() ? "s" : "n") + "]: ");
        String sFechaExp = readString("Fecha expiración vacuna (YYYY-MM-DD) [" + a.getFechaExpiracionDeVacuna() + "]: ");
        String sIdUsuario = readString("Id usuario [" + a.getIdUsuario() + "]: ");

        if (!codigo.isEmpty()) a.setCodigo(codigo);
        if (!sIdLote.isEmpty()) {
            a.setIdLote(sIdLote.equalsIgnoreCase("null") ? null : Integer.parseInt(sIdLote));
        }
        if (!sIdRaza.isEmpty()) a.setIdRaza(Integer.parseInt(sIdRaza));
        if (!sPeso.isEmpty()) a.setPeso(Float.parseFloat(sPeso));
        if (!sSexo.isEmpty()) a.setSexo(Sexo.valueOf(sSexo.toUpperCase()));
        if (!sFechaNac.isEmpty()) a.setFechaNacimiento(Date.valueOf(sFechaNac));
        if (!observacion.isEmpty()) a.setObservacion(observacion);
        if (!sVac.isEmpty()) a.setVacunaObligatoria(parseBooleanFromString(sVac));
        if (!sFechaExp.isEmpty()) a.setFechaExpiracionDeVacuna(sFechaExp.equalsIgnoreCase("null") ? null : Date.valueOf(sFechaExp));
        if (!sIdUsuario.isEmpty()) a.setIdUsuario(Integer.parseInt(sIdUsuario));

        dao.modificar(a);
        System.out.println("Animal modificado correctamente.");
    }

    private void eliminarAnimal() throws SQLException {
        System.out.println("\n-- ELIMINAR ANIMAL --");
        int id = readInt("ID a eliminar: ");
        dao.eliminar(id);
        System.out.println("Si existía, el animal fue eliminado.");
    }

    /* ------------- Helpers de lectura y utilidades ------------- */

    private void printAnimal(Animal a) {
        System.out.println("ID: " + a.getId());
        System.out.println("Código: " + a.getCodigo());
        System.out.println("IdLote: " + (a.getIdLote() == null ? "null" : a.getIdLote()));
        System.out.println("IdRaza: " + a.getIdRaza());
        System.out.println("Peso: " + a.getPeso());
        System.out.println("Sexo: " + (a.getSexo() == null ? "null" : a.getSexo()));
        System.out.println("Fecha Nacimiento: " + a.getFechaNacimiento());
        System.out.println("Observación: " + (a.getObservacion() == null ? "" : a.getObservacion()));
        System.out.println("Vacuna Obligatoria: " + a.isVacunaObligatoria());
        System.out.println("Fecha Expiración Vacuna: " + a.getFechaExpiracionDeVacuna());
        System.out.println("IdUsuario: " + a.getIdUsuario());
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private String readNonEmptyString(String prompt) {
        String s;
        do {
            s = readString(prompt);
            if (s.isEmpty()) System.out.println("No puede estar vacío.");
        } while (s.isEmpty());
        return s;
    }

    private int readInt(String prompt) {
        while (true) {
            String s = readString(prompt);
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Numero inválido. Intenta de nuevo.");
            }
        }
    }

    private Integer readNullableInt(String prompt) {
        while (true) {
            String s = readString(prompt);
            if (s.isEmpty()) return null;
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Numero inválido. Intenta de nuevo.");
            }
        }
    }

    private float readFloat(String prompt) {
        while (true) {
            String s = readString(prompt);
            try {
                return Float.parseFloat(s);
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Intenta de nuevo.");
            }
        }
    }

    private boolean readBoolean(String prompt) {
        while (true) {
            String s = readString(prompt).toLowerCase();
            if (s.equals("s") || s.equals("si") || s.equals("y") || s.equals("true") || s.equals("1")) return true;
            if (s.equals("n") || s.equals("no") || s.equals("false") || s.equals("0")) return false;
            System.out.println("Respuesta inválida. Usa 's' o 'n'.");
        }
    }

    private boolean parseBooleanFromString(String s) {
        s = s.trim().toLowerCase();
        return s.equals("s") || s.equals("si") || s.equals("true") || s.equals("1") || s.equals("y");
    }

    private Sexo readSexo(String prompt) {
        while (true) {
            String s = readString(prompt).toUpperCase();
            if (s.equals("HEMBRA") || s.equals("MACHO")) {
                return Sexo.valueOf(s);
            }
            System.out.println("Valor inválido. Escribe 'HEMBRA' o 'MACHO'.");
        }
    }

    private Date readDate(String prompt, boolean required) {
        while (true) {
            String s = readString(prompt);
            if (s.isEmpty() && !required) return null;
            try {
                return Date.valueOf(s); // formato YYYY-MM-DD
            } catch (IllegalArgumentException e) {
                System.out.println("Fecha inválida. Usa formato YYYY-MM-DD.");
            }
        }
    }
}
