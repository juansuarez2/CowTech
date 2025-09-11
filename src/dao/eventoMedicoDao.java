package dao;

import models.eventoMedico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class eventoMedicoDao {

    public void crearEventoMedico(eventoMedico evento) throws SQLException{
        String sql = "INSERT INTO (fecha, tipoDeEvento, idAnimal, idRegistroMedicamento, idRegistroEnfermedad) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseConnection.getInstacia().getConnection().prepareStatement(sql)){
            ps.setDate(1, evento.getFecha());
            ps.setString(2, evento.getEvento().name());
            ps.setInt(3, evento.getCodigoAnimal());
            ps.setInt(4, evento.getIdRegistroMedicamento());
            ps.setInt(5, evento.getIdRegistroDeEnfermedad());
            ps.executeUpdate();
            System.out.println("✅ Evento medico creado.");
        }
    }

    public List<eventoMedico> listarEventosMedico() throws SQLException{
        List<eventoMedico> lista = new ArrayList<>();
        String sql = "SELECT * FROM eventoMedico";
        try (Statement st = DatabaseConnection.getInstacia().getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new eventoMedico(rs.getInt("id"), rs.getDate("fecha"), eventoMedico.tipoEvento.valueOf(rs.getString("tipoEvento")), rs.getInt("idAniaml"), rs.getInt("idRegistroMedico"), rs.getInt("idRegistroEnfermedad") ));
            }
        }
        return lista;
    }

    public void editarEventoMedico(int id, Date newFecha, String newEvento) throws SQLException{
        String sql = "UPDATE eventoMedico SET fecha = ?, evento = ? WHERE id = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstacia().getConnection().prepareStatement(sql)) {
            ps.setDate(1, newFecha);
            ps.setString(2, newEvento);
            ps.setInt(3, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✅ Evento actualizado.");
            else System.out.println("⚠ Evento no encontrado.");
        }
    }

    public void borrarEventoMedico(int id) throws SQLException {
        String sql = "DELETE FROM eventoMedico WHERE id = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstacia().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✅ Evento eliminado.");
            else System.out.println("⚠ Evento no encontrado.");
        }
    }
}
