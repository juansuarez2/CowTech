package dao;

import database.DatabaseConnection;
import models.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    // Crear un nuevo animal
    public void crear(Animal animal) throws SQLException {
        String sql = "INSERT INTO animal (codigo, id_lote, id_raza, peso, sexo, fecha_nacimiento, " +
                "observacion, vacunaObligatoria, fechaExpiracionDeVacuna, id_usuario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getInstancia().getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, animal.getCodigo());
            if (animal.getIdLote() != null) {
                stmt.setInt(2, animal.getIdLote());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setInt(3, animal.getIdRaza());
            stmt.setFloat(4, animal.getPeso());
            stmt.setString(5, animal.getSexo().name());
            stmt.setDate(6, animal.getFechaNacimiento());
            stmt.setString(7, animal.getObservacion());
            stmt.setBoolean(8, animal.isVacunaObligatoria());
            stmt.setDate(9, animal.getFechaExpiracionDeVacuna());
            stmt.setInt(10, animal.getIdUsuario());

            stmt.executeUpdate();
        }
    }

    // Listar todos los animales
    public List<Animal> listar() throws SQLException {
        List<Animal> animales = new ArrayList<>();
        String sql = "SELECT * FROM animal";

        Connection connection = DatabaseConnection.getInstancia().getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setCodigo(rs.getString("codigo"));
                int idLote = rs.getInt("id_lote");
                animal.setIdLote(rs.wasNull() ? null : idLote);
                animal.setIdRaza(rs.getInt("id_raza"));
                animal.setPeso(rs.getFloat("peso"));
                animal.setSexo(Animal.Sexo.valueOf(rs.getString("sexo")));
                animal.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                animal.setObservacion(rs.getString("observacion"));
                animal.setVacunaObligatoria(rs.getBoolean("vacunaObligatoria"));
                animal.setFechaExpiracionDeVacuna(rs.getDate("fechaExpiracionDeVacuna"));
                animal.setIdUsuario(rs.getInt("id_usuario"));

                animales.add(animal);
            }
        }
        return animales;
    }

    // Buscar un animal por ID
    public Animal buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM animal WHERE id = ?";

        Connection connection = DatabaseConnection.getInstancia().getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Animal animal = new Animal();
                    animal.setId(rs.getInt("id"));
                    animal.setCodigo(rs.getString("codigo"));
                    int idLote = rs.getInt("id_lote");
                    animal.setIdLote(rs.wasNull() ? null : idLote);
                    animal.setIdRaza(rs.getInt("id_raza"));
                    animal.setPeso(rs.getFloat("peso"));
                    animal.setSexo(Animal.Sexo.valueOf(rs.getString("sexo")));
                    animal.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    animal.setObservacion(rs.getString("observacion"));
                    animal.setVacunaObligatoria(rs.getBoolean("vacunaObligatoria"));
                    animal.setFechaExpiracionDeVacuna(rs.getDate("fechaExpiracionDeVacuna"));
                    animal.setIdUsuario(rs.getInt("id_usuario"));

                    return animal;
                }
            }
        }
        return null;
    }

    // Modificar un animal existente
    public void modificar(Animal animal) throws SQLException {
        String sql = "UPDATE animal SET codigo = ?, id_lote = ?, id_raza = ?, peso = ?, sexo = ?, " +
                "fecha_nacimiento = ?, observacion = ?, vacunaObligatoria = ?, " +
                "fechaExpiracionDeVacuna = ?, id_usuario = ? WHERE id = ?";

        Connection connection = DatabaseConnection.getInstancia().getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, animal.getCodigo());
            if (animal.getIdLote() != null) {
                stmt.setInt(2, animal.getIdLote());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setInt(3, animal.getIdRaza());
            stmt.setFloat(4, animal.getPeso());
            stmt.setString(5, animal.getSexo().name());
            stmt.setDate(6, animal.getFechaNacimiento());
            stmt.setString(7, animal.getObservacion());
            stmt.setBoolean(8, animal.isVacunaObligatoria());
            stmt.setDate(9, animal.getFechaExpiracionDeVacuna());
            stmt.setInt(10, animal.getIdUsuario());
            stmt.setInt(11, animal.getId());

            stmt.executeUpdate();
        }
    }

    // Eliminar un animal por ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM animal WHERE id = ?";

        Connection connection = DatabaseConnection.getInstancia().getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
