import database.DatabaseConnection;
import flujos.RazaMenu;

import java.sql.SQLException;

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
                RazaMenu menu = new RazaMenu();
                menu.mostrarMenu();
//            MedicamentoMenu menu = new MedicamentoMenu();
//            menu.mostrarMenu();

        }catch (Exception e){
            System.out.println("No se pudo conectar");
        }
    }
}


