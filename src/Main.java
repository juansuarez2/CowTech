import database.DatabaseConnection;
import flujos.*;

import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try {
            DatabaseConnection.getInstancia().getConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        try {
            while(true) {

                int opcion=mainMenu();

                switch (opcion) {
                    case 1: RazaMenu menu = new RazaMenu();
                            menu.mostrarMenu();
                        break;
                    case 2: MedicamentoMenu menu2= new MedicamentoMenu();
                            menu2.mostrarMenu();
                        break;
                    case 3: EnfermedadMenu menu3 = new EnfermedadMenu();
                            menu3.mostrarMenu();
                        break;
                    case 4: EventoMedicoMenu menu4 = new EventoMedicoMenu();
                            menu4.iniciar();
                        break;
                    case 5: RegistroDeEnfermedadMenu menu5 = new RegistroDeEnfermedadMenu();
                            menu5.iniciar();
                        break;
                    case 6: UsuarioMenu menu6 = new UsuarioMenu();
                            menu6.mostrarMenu();
                        break;
                    case 7: AnimalMenu menu7 = new AnimalMenu();
                            menu7.start();
                         break;
                    case 0: System.exit(0);

                }
            }
        }catch (Exception e){
            System.out.println("No se pudo conectar");
        }
    }
     static int mainMenu(){
        System.out.println("\n===Menu===");
        System.out.println("1. Gestion Razas");
        System.out.println("2. Gestion Medicamentos");
        System.out.println("3. Gestion Enfermedads");
        System.out.println("4. Gestion Eventos Medicos");
        System.out.println("5. Gestion Registros de Enfermedad");
        System.out.println("6. Gestion Usuarios");
        System.out.println("7. Gestion Animales");
        System.out.println("0. Salir");
        System.out.println("Opcion:");


         Scanner scanner = new Scanner(System.in);
         int opcion = scanner.nextInt();
         scanner.nextLine();

         return opcion;
    }
}


