package dao;

import database.DatabaseConnection;
import models.Imagen;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImagenDAO {
    // Constructor vacío, usa DatabaseConnection como MedicamentoDAO
    public ImagenDAO() {}

    public void agregarImagen(String url, int idAnimal) {
        String consulta = "INSERT INTO Imagen (url, idAnimal) VALUES (?, ?)";
        try {
            PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, url);
            ps.setInt(2, idAnimal);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("Imagen agregada con id: " + rs.getInt(1));
            } else {
                System.out.println("Imagen agregada, pero no se pudo obtener el id generado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificarImagen(int id, String url, int idAnimal) {
        String consulta = "UPDATE Imagen SET url=?, idAnimal=? WHERE id=?";
        try {
            PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta);
            ps.setString(1, url);
            ps.setInt(2, idAnimal);
            ps.setInt(3, id);
            int existe = ps.executeUpdate();
            if (existe == 0) {
                System.out.println("No existe una imagen con ese Id");
            } else {
                System.out.println("Imagen modificada correctamente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarImagen(int id) {
        String consulta = "DELETE FROM Imagen WHERE id=?";
        try {
            PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta);
            ps.setInt(1, id);
            int existe = ps.executeUpdate();
            if (existe == 0) {
                System.out.println("No existe una imagen con ese Id");
            } else {
                System.out.println("Imagen eliminada correctamente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Imagen buscarPorId(int id) {
        String consulta = "SELECT * FROM Imagen WHERE id=?";
        try {
            PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Imagen(
                    rs.getInt("id"),
                    rs.getString("url"),
                    rs.getInt("idAnimal")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Imagen> listarImagenesPorAnimal(int idAnimal) {
        List<Imagen> lista = new ArrayList<>();
        String consulta = "SELECT * FROM Imagen WHERE idAnimal=?";
        try {
            PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta);
            ps.setInt(1, idAnimal);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Imagen img = new Imagen(
                    rs.getInt("id"),
                    rs.getString("url"),
                    rs.getInt("idAnimal")
                );
                lista.add(img);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Imagen> listarTodasImagenes() {
        List<Imagen> lista = new ArrayList<>();
        String consulta = "SELECT * FROM Imagen";
        try {
            Statement st = DatabaseConnection.getInstancia().getConnection().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Imagen img = new Imagen(
                    rs.getInt("id"),
                    rs.getString("url"),
                    rs.getInt("idAnimal")
                );
                lista.add(img);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
