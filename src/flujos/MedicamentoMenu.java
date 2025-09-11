package flujos;

import dao.MedicamentoDAO;
import models.Medicamento;
import enums.TipoMed;

import java.util.List;
import java.util.Scanner;

public class MedicamentoMenu {

    private final Scanner scanner = new Scanner(System.in);


    private final MedicamentoDAO medicamentoDAO;

    public MedicamentoMenu(){
        this.medicamentoDAO = new MedicamentoDAO();
    }

    public void mostrarMenu(){
        try {

            System.out.println("\n===Gestion de Medicamentos===");
            System.out.println("1. Agregar Medicamento");
            System.out.println("2. Listar Medicamentos");
            System.out.println("3. Editar Medicamento");
            System.out.println("4. Borrar Medicamento");
            System.out.println("5. Salir");
            System.out.println("Opcion:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: agregarMedicamento();
                    break;
                case 2: listarMedicamentos();
                    break;
                case 3: modificarMedicamento();
                    break;
                case 4: eliminarMedicamento();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcion Invalida");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void agregarMedicamento(){
        System.out.println("Nombre");
        String nombre = scanner.nextLine();

        TipoMed tipo = verTipoMed();

        medicamentoDAO.agregarMedicamento(nombre, tipo);

    }

    private void modificarMedicamento(){
        System.out.println("Id");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nombre");
        String nombre = scanner.nextLine();

        TipoMed tipo = verTipoMed();

        medicamentoDAO.modificarMedicamento(id, nombre, tipo);
    }

    private void listarMedicamentos(){
        List<Medicamento> medicamentos = medicamentoDAO.listarMedicamentos();

        if(medicamentos.isEmpty()){
            System.out.println("No hay medicamentos agregados");
        }else{
            for(Medicamento medicamento: medicamentos){
                System.out.println("Id  "+ medicamento.getId() + "  | Nombre    "+ medicamento.getNombre() + "    | Tipo  "+ medicamento.getTipo());
            }
        }
    }

    private void eliminarMedicamento(){
        System.out.println("Id");
        int id = scanner.nextInt();

        medicamentoDAO.eliminarMedicamento(id);

    }

    private TipoMed verTipoMed() {
        TipoMed tipo = null;

        while (tipo == null) {
            System.out.print("Tipo: ");
            String input = scanner.nextLine().toUpperCase();

            try {
                tipo = TipoMed.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido.");
                System.out.println("Los tipos válidos son:");
                for (TipoMed tipos : TipoMed.values()) {
                    System.out.println("- " + tipos);
                }
            }
        }

        return tipo;
    }
}

