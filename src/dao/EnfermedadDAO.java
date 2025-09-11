package dao;

import database.DatabaseConnection;
import models.Enfermedad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnfermedadDAO {

    // Crear una nueva enfermedad
    public void crear(Enfermedad enfermedad) throws SQLException {
        String sql = "INSERT INTO enfermedad (nombre) VALUES (?)";

        Connection connection = DatabaseConnection.getInstancia().getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, enfermedad.getNombre());
            stmt.executeUpdate();
        }
    }

    // Listar todas las enfermedades
    public List<Enfermedad> listar() throws SQLException {
        List<Enfermedad> enfermedades = new ArrayList<>();
        String sql = "SELECT * FROM enfermedad";

        Connection connection = DatabaseConnection.getInstancia().getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Enfermedad enfermedad = new Enfermedad();
                enfermedad.setId(rs.getInt("id"));
                enfermedad.setNombre(rs.getString("nombre"));
                enfermedades.add(enfermedad);
            }
        }
        return enfermedades;
    }

    // Buscar una enfermedad por ID
    public Enfermedad buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM enfermedad WHERE id = ?";

        Connection connection = DatabaseConnection.getInstancia().getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Enfermedad(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                }
            }
        }
        return null;
    }

    // Modificar una enfermedad existente
    public void modificar(Enfermedad enfermedad) throws SQLException {
        String sql = "UPDATE enfermedad SET nombre = ? WHERE id = ?";

        Connection connection = DatabaseConnection.getInstancia().getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, enfermedad.getNombre());
            stmt.setInt(2, enfermedad.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminar una enfermedad por ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM enfermedad WHERE id = ?";

        Connection connection = DatabaseConnection.getInstancia().getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
