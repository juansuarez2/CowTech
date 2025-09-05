import database.DatabaseConnection;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            DatabaseConnection.getInstacia().getConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}


