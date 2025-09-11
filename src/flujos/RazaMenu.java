package flujos;

import dao.RazaDAO;
import models.Raza;

import java.util.List;
import java.util.Scanner;

public class RazaMenu {

    private final Scanner scanner= new Scanner(System.in);

    private final RazaDAO razaDAO;

    public RazaMenu(){
        this.razaDAO = new RazaDAO();
    }

    public void mostrarMenu(){
        try {
            System.out.println("\n===Gestion de Razas===");
            System.out.println("1. Añadir Raza");
            System.out.println("2. Listar Razas");
            System.out.println("3. Editar Raza");
            System.out.println("4. Borrar Raza");
            System.out.println("5. Salir");
            System.out.println("Opcion:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarRaza();
                    break;
                case 2:
                    listarRazas();
                    break;
                case 3:
                    modificarRaza();
                    break;
                case 4:
                    eliminarRaza();
                    break;
                default:
                    System.out.println("Opcion Invalida");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void agregarRaza(){
        System.out.println("Nombre");
        String nombre = scanner.nextLine();

        razaDAO.agregarRaza(nombre);
    }

    private void modificarRaza(){
        System.out.println("Id");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nombre");
        String nombre = scanner.nextLine();

        razaDAO.modificarRaza(id, nombre);
    }

    private void listarRazas(){
        List<Raza> razas = razaDAO.listarRazas();
        if(razas.isEmpty()){
            System.out.println("No hay razas para listar");
        }else{
            System.out.println("Lista de razas");
            for(Raza raza : razas){
                System.out.println("Id: " + raza.getId() + "  Nombre: "+ raza.getNombre());
            }

        }
    }

    private void eliminarRaza(){
        System.out.println("Id");
        int id = scanner.nextInt();

        razaDAO.eliminarRaza(id);
    }
}
