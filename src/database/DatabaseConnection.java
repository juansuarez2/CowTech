package database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static DatabaseConnection intancia;
    private Connection connection;

    private static Properties reader = new Properties();

    static {

        try{
            InputStream in = DatabaseConnection.class.getResourceAsStream("/db.properties");
            reader.load(in);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    private DatabaseConnection() throws SQLException {
        try {
            String url = reader.getProperty("db.url");
            String user = reader.getProperty("db.user");
            String password = reader.getProperty("db.password");
            this.connection= DriverManager.getConnection(url,user,password);
            System.out.printf("Ingreso");
        }catch (SQLException e){
            throw new SQLException("Error al conectarnos a la base de datos - " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstacia() throws SQLException{
        if(intancia!= null){
            return intancia;
        }
        else {
            intancia= new DatabaseConnection();
            return intancia;
        }
    }
    public Connection getConnection() throws SQLException {

        return connection;
    }

}
