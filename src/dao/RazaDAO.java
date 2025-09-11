package dao;


import database.DatabaseConnection;
import models.Raza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RazaDAO {

    public void agregarRaza(String nombre){

        String consulta = "INSERT INTO raza (nombre) VALUES (?)";
        try {
            PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta);
            ps.setString(1, nombre);

            ps.executeUpdate();

            System.out.println("Raza agregada correctamente");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void modificarRaza(int id, String nombre) {

        String consulta = "UPDATE raza SET nombre =? WHERE raza.id =?";
        try {
            PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta);
            ps.setString(1, nombre);
            ps.setInt(2, id);

            int existe =ps.executeUpdate();
            if(existe==0){
                System.out.println("No existe una raza con ese Id");
            }else {
                System.out.println("Raza modificada correctamente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Raza> listarRazas(){
        List<Raza> razas = new ArrayList<>();

        String consulta = "SELECT * FROM raza";

        try {
            Statement st = DatabaseConnection.getInstancia().getConnection().createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                Raza raza = new Raza();
                raza.setId(rs.getInt("Id"));
                raza.setNombre(rs.getString("Nombre"));
                razas.add(raza);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return razas;
    }

    public void eliminarRaza (int id) {

        String consulta = "DELETE FROM raza WHERE raza.id=?";

        try {
            PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta);
            ps.setInt(1, id);
            int existe =ps.executeUpdate();

            if(existe==0){
                System.out.println("No existe una raza con ese Id");
            }else {
                System.out.println("Raza eliminada");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }



