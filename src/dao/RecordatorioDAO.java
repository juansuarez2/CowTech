package dao;

import models.Recordatorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordatorioDAO {
    private Connection conn;

    public RecordatorioDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertar(Recordatorio recordatorio) {
        String sql = "INSERT INTO Recordatorio (mensaje, fechaDeEnvio, estado, usuarioId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, recordatorio.getMensaje());
            ps.setTimestamp(2, Timestamp.valueOf(recordatorio.getFechaDeEnvio()));
            ps.setBoolean(3, recordatorio.isEstado());
            ps.setInt(4, recordatorio.getUsuarioId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Recordatorio recordatorio) {
        String sql = "UPDATE Recordatorio SET mensaje=?, fechaDeEnvio=?, estado=?, usuarioId=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, recordatorio.getMensaje());
            ps.setTimestamp(2, Timestamp.valueOf(recordatorio.getFechaDeEnvio()));
            ps.setBoolean(3, recordatorio.isEstado());
            ps.setInt(4, recordatorio.getUsuarioId());
            ps.setInt(5, recordatorio.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM Recordatorio WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Recordatorio buscarPorId(int id) {
        String sql = "SELECT * FROM Recordatorio WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Recordatorio r = new Recordatorio();
                r.setId(rs.getInt("id"));
                r.setMensaje(rs.getString("mensaje"));
                r.setFechaDeEnvio(rs.getTimestamp("fechaDeEnvio").toLocalDateTime());
                r.setEstado(rs.getBoolean("estado"));
                r.setUsuarioId(rs.getInt("usuarioId"));
                return r;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Recordatorio> listarPorUsuario(int usuarioId) {
        List<Recordatorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM Recordatorio WHERE usuarioId=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Recordatorio r = new Recordatorio();
                r.setId(rs.getInt("id"));
                r.setMensaje(rs.getString("mensaje"));
                r.setFechaDeEnvio(rs.getTimestamp("fechaDeEnvio").toLocalDateTime());
                r.setEstado(rs.getBoolean("estado"));
                r.setUsuarioId(rs.getInt("usuarioId"));
                lista.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
