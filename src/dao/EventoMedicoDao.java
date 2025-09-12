package dao;

import models.EventoMedico;
import enums.TipoEvento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class EventoMedicoDao {

    public void crearEventoMedico(EventoMedico evento) throws SQLException{
        String sql = "INSERT INTO (fecha, tipoDeEvento, idAnimal, idRegistroMedicamento, idRegistroEnfermedad) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(sql)){
            ps.setDate(1, evento.getFecha());
            ps.setString(2, evento.getEvento().name());
            ps.setInt(3, evento.getCodigoAnimal());
            ps.setInt(4, evento.getIdRegistroMedicamento());
            ps.setInt(5, evento.getIdRegistroDeEnfermedad());
            ps.executeUpdate();
            System.out.println("✅ Evento medico creado.");
        }
    }

    public List<EventoMedico> listarEventosMedico() throws SQLException{
        List<EventoMedico> eventos = new ArrayList<>();
        String sql = "SELECT * FROM eventoMedico";
        try (Statement st = DatabaseConnection.getInstancia().getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                EventoMedico evento = new EventoMedico();
                evento.setId(rs.getInt("id"));
                evento.setFecha(rs.getDate("fecha"));
                String tipo = rs.getString("tipoEvento");
                evento.setEvento(TipoEvento.valueOf(tipo.trim().toUpperCase()));
                evento.setCodigoAnimal(rs.getInt("idAniaml"));
                evento.setIdRegistroMedicamento(rs.getInt("idRegistroMedico"));
                evento.setIdRegistroDeEnfermedad(rs.getInt("idRegistroEnfermedad"));
                eventos.add(evento);
            }
        }
        return eventos;
    }

    public List<EventoMedico> listarEventosMedicoPorAnimal(int idAnimal) throws SQLException{
        List<EventoMedico> eventos = new ArrayList<>();
        String sql = "SELECT * FROM eventoMedico WHERE idAnimal = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery(sql)) {
            while (rs.next()) {
                EventoMedico evento = new EventoMedico();
                evento.setId(rs.getInt("id"));
                evento.setFecha(rs.getDate("fecha"));
                String tipo = rs.getString("tipoEvento");
                evento.setEvento(TipoEvento.valueOf(tipo.trim().toUpperCase()));
                evento.setCodigoAnimal(rs.getInt("idAniaml"));
                evento.setIdRegistroMedicamento(rs.getInt("idRegistroMedico"));
                evento.setIdRegistroDeEnfermedad(rs.getInt("idRegistroEnfermedad"));
                eventos.add(evento);
            }
        }
        return eventos;
    }

    public void editarEventoMedico(int id, Date newFecha, String newEvento) throws SQLException{
        String sql = "UPDATE eventoMedico SET fecha = ?, evento = ? WHERE id = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(sql)) {
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
        try (PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✅ Evento eliminado.");
            else System.out.println("⚠ Evento no encontrado.");
        }
    }
}
