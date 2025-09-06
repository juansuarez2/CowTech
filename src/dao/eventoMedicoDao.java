package dao;

import models.eventoMedico;
import java.sql.*;
import database.DatabaseConnection;

public class eventoMedicoDao {

    public void crearEventoMedico(eventoMedico evento) throws SQLException{
        String sql = "INSERT INTO (id, fecha, tipoDeEvento, idAnimal, idRegistroMedicamento, idRegistroEnfermedad) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseConnection.getInstacia().getConnection().prepareStatement(sql)){
            ps.setInt(1, evento.getId());
            ps.setDate(2, evento.getFecha());
            ps.setString(3, evento.getEvento().name());
            ps.setInt(4, evento.getCodigoAnimal());
            ps.setInt(5, evento.getIdRegistroMedicamento());
            ps.setInt(6, evento.getIdRegistroDeEnfermedad());
            ps.executeUpdate();
            System.out.println("✅ Evento medico creado.");
        }
    }
}
