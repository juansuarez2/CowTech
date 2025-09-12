package dao;

import models.RegistroEnfermedad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class RegistroDeEnfermedadDao {
    public void crearRegistroEnfermedad(RegistroEnfermedad registro) throws SQLException{
        String sql = "INSERT INTO (id_enfermedad, id_animal, fechaInicio, fechaFinal, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(sql)){
            ps.setInt(1, registro.getIdEnfermedad());
            ps.setInt(2, registro.getIdAnimal());
            ps.setDate(3, registro.getFechaInicio());
            ps.setDate(4, registro.getFechaFinal());
            ps.setBoolean(5, registro.getEstado());
            ps.executeUpdate();
            System.out.println("✅ Evento medico creado.");
        }
    }

    public List<RegistroEnfermedad> listarRegistroEnfermedad() throws SQLException{
        List<RegistroEnfermedad> lista = new ArrayList<>();
        String sql = "SELECT * FROM registroEnfermedad";
        try (Statement st = DatabaseConnection.getInstancia().getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new RegistroEnfermedad(rs.getInt("id"), rs.getInt("id_enfermedad"), rs.getInt("id_animal"), rs.getDate("fechaInicio"), rs.getDate("fechaFinal"), rs.getBoolean("estado") ));
            }
        }
        return lista;
    }

    public void editarRegistroEnfermedad(int id, int newEnfermedad, int newAnimal, Date newFechaINI, Date newFechaFIN, boolean newEstado) throws SQLException{
        String sql = "UPDATE registroEnfermedad SET id_enfermedad = ?, id_animal = ?, fechaInicio = ?, fechaFinal = ?, estado = ? WHERE id = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(sql)) {
            ps.setInt(1, newEnfermedad);
            ps.setInt(2, newAnimal);
            ps.setDate(3, newFechaINI);
            ps.setDate(4, newFechaFIN);
            ps.setBoolean(5, newEstado);
            ps.setInt(6, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✅ Registro de Enfermedad actualizado.");
            else System.out.println("⚠ Registro de Enfermedad no encontrado.");
        }
    }

    public void borrarRegistroEnfermedad(int id) throws SQLException {
        String sql = "DELETE FROM registroEnfermedad WHERE id = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✅ Registro de Enfermedad eliminado.");
            else System.out.println("⚠ Registro de Enfermedad no encontrado.");
        }
    }
}
