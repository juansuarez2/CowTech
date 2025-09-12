package dao;

import models.RegistroMedicamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroMedicamentoDAO {
    private Connection conexion;

    public RegistroMedicamentoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertar(RegistroMedicamento r) throws SQLException {
        String sql = "INSERT INTO registroMedicamento (idMedicamento, duracion, fechaInicio) VALUES (?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, r.getIdMedicamento());
        ps.setInt(2, r.getDuracion());
        ps.setDate(3, Date.valueOf(r.getFechaInicio()));
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            r.setId(rs.getInt(1));
        }
    }

    public void actualizar(RegistroMedicamento r) throws SQLException {
        String sql = "UPDATE registroMedicamento SET idMedicamento=?, duracion=?, fechaInicio=? WHERE id=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, r.getIdMedicamento());
        ps.setInt(2, r.getDuracion());
        ps.setDate(3, Date.valueOf(r.getFechaInicio()));
        ps.setInt(4, r.getId());
        ps.executeUpdate();
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM registroMedicamento WHERE id=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public RegistroMedicamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM registroMedicamento WHERE id=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new RegistroMedicamento(
                rs.getInt("id"),
                rs.getInt("idMedicamento"),
                rs.getInt("duracion"),
                rs.getDate("fechaInicio").toLocalDate()
            );
        }
        return null;
    }

    public List<RegistroMedicamento> listar() throws SQLException {
        List<RegistroMedicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM registroMedicamento";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            RegistroMedicamento r = new RegistroMedicamento(
                rs.getInt("id"),
                rs.getInt("idMedicamento"),
                rs.getInt("duracion"),
                rs.getDate("fechaInicio").toLocalDate()
            );
            lista.add(r);
        }
        return lista;
    }

    public List<RegistroMedicamento> listarPorMedicamento(int idMedicamento) throws SQLException {
        List<RegistroMedicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM registroMedicamento WHERE idMedicamento=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idMedicamento);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            RegistroMedicamento r = new RegistroMedicamento(
                rs.getInt("id"),
                rs.getInt("idMedicamento"),
                rs.getInt("duracion"),
                rs.getDate("fechaInicio").toLocalDate()
            );
            lista.add(r);
        }
        return lista;
    }
}
