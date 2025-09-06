package dao;

import models.registroDeEnfermedad;
import java.sql.*;
import database.DatabaseConnection;

public class registroDeEnfermedadDao {
    public void crearRegistroEnfermedad(registroDeEnfermedad registro) throws SQLException{
        String sql = "INSERT INTO (id, id_enfermedad, id_animal, fechaInicio, fechaFinal, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseConnection.getInstacia().getConnection().prepareStatement(sql)){
            ps.setInt(1, registro.getId());
            ps.setInt(2, registro.getIdEnfermedad());
            ps.setInt(3, registro.getIdAnimal());
            ps.setDate(4, registro.getFechaInicio());
            ps.setDate(5, registro.getFechaFinal());
            ps.setBoolean(6, registro.getEstado());
            ps.executeUpdate();
            System.out.println("✅ Evento medico creado.");
        }
    }
}
