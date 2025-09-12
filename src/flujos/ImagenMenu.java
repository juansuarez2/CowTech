package flujos;

import dao.ImagenDAO;
import dao.AnimalDAO;
import models.Imagen;
import models.Animal;

import java.util.List;
import java.util.Scanner;

public class ImagenMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final ImagenDAO imagenDAO;
    private final AnimalDAO animalDAO;

    public ImagenMenu(ImagenDAO imagenDAO, AnimalDAO animalDAO) {
        this.imagenDAO = imagenDAO;
        this.animalDAO = animalDAO;
    }

    public void mostrarMenu() {
        try {
            System.out.println("\n=== Gestión de Imágenes ===");
            System.out.println("1. Agregar imagen a animal");
            System.out.println("2. Modificar imagen");
            System.out.println("3. Eliminar imagen");
            System.out.println("4. Buscar imagen por ID");
            System.out.println("5. Listar imágenes por animal");
            System.out.println("6. Listar todas las imágenes");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1: agregarImagenDelAnimal(); break;
                case 2: modificarImagen(); break;
                case 3: eliminarImagen(); break;
                case 4: buscarImagenPorId(); break;
                case 5: listarImagenesPorAnimal(); break;
                case 6: listarTodasImagenes(); break;
                case 7: break;
                default: System.out.println("Opción inválida");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void agregarImagenDelAnimal() {
        try {
            System.out.print("ID del animal: ");
            int idAnimal = scanner.nextInt();
            scanner.nextLine();
            Animal animal = animalDAO.buscarPorId(idAnimal);
            if (animal == null) {
                System.out.println("El animal no existe. No se puede agregar la imagen.");
                return;
            }
            System.out.print("URL de la imagen: ");
            String url = scanner.nextLine();
            imagenDAO.agregarImagen(url, idAnimal);
            System.out.println("Imagen agregada correctamente al animal.");
        } catch (Exception e) {
            System.out.println("Error al agregar imagen: " + e.getMessage());
        }
    }

    private void modificarImagen() {
        try {
            System.out.print("ID de la imagen a modificar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nueva URL: ");
            String url = scanner.nextLine();
            System.out.print("Nuevo ID de animal: ");
            int idAnimal = scanner.nextInt();
            scanner.nextLine();
            imagenDAO.modificarImagen(id, url, idAnimal);
        } catch (Exception e) {
            System.out.println("Error al modificar imagen: " + e.getMessage());
        }
    }

    private void eliminarImagen() {
        try {
            System.out.print("ID de la imagen a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            imagenDAO.eliminarImagen(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar imagen: " + e.getMessage());
        }
    }

    private void buscarImagenPorId() {
        try {
            System.out.print("ID de la imagen a buscar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Imagen imagen = imagenDAO.buscarPorId(id);
            if (imagen != null) {
                System.out.println(imagen);
            } else {
                System.out.println("No se encontró la imagen.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar imagen: " + e.getMessage());
        }
    }

    private void listarImagenesPorAnimal() {
        try {
            System.out.print("ID del animal: ");
            int idAnimal = scanner.nextInt();
            scanner.nextLine();
            List<Imagen> lista = imagenDAO.listarImagenesPorAnimal(idAnimal);
            if (lista.isEmpty()) {
                System.out.println("No hay imágenes para este animal.");
            } else {
                lista.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al listar imágenes: " + e.getMessage());
        }
    }

    private void listarTodasImagenes() {
        try {
            List<Imagen> lista = imagenDAO.listarTodasImagenes();
            if (lista.isEmpty()) {
                System.out.println("No hay imágenes registradas.");
            } else {
                lista.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al listar imágenes: " + e.getMessage());
        }
    }
}
