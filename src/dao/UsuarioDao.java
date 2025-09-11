package dao;

import database.DatabaseConnection;
import models.Usuario;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UsuarioDao {

    public void crearUsuario(Usuario usuario) throws SQLException {
        String consulta = "INSERT INTO usuario (nombre, apellido,fechaNacimiento, correo, password) VALUES (?, ?, ?,?,?)";

        try (PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setDate(3, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getPassword());

            ps.executeUpdate();
            System.out.println("Usuario creado correctamente");

        }

    }

    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String consulta = "SELECT * from usuario";

        try {
            Statement st = DatabaseConnection.getInstancia().getConnection().createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail(rs.getString("correo"));
                usuario.setPassword(rs.getString("password"));

                // Aquí se obtiene la fecha desde el ResultSet
                java.sql.Date sqlDate = rs.getDate("fechaNacimiento");
                usuario.setFechaNacimiento(sqlDate); // java.sql.Date es compatible con java.util.Date


                lista.add(usuario);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Usuario obtenerPorId(int id) throws SQLException {
        String consulta = "SELECT * FROM usuario WHERE id = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail(rs.getString("correo"));
                usuario.setPassword(rs.getString("password"));
                java.sql.Date sqlDate = rs.getDate("fechaNacimiento");
                usuario.setFechaNacimiento(sqlDate);
                return usuario;
            }
        }
        return null; // Si no se encuentra el usuario
    }

    public void editarUsuario(Usuario usuario) throws SQLException {
        String consulta = " UPDATE usuario SET nombre = ?, apellido = ?, fechaNacimiento = ?, correo = ?, password = ? WHERE id= ?";
        try (PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setDate(3, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getPassword());
            ps.setInt(6, usuario.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("Usuario actualizado");
            else System.out.println("Usuario no encontrado");
        }
    }

    public void eliminarUsuario(int id) throws SQLException {
        String consulta = "DELETE FROM usuario WHERE id = ?";

        try (PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta)) {
            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("Usuario actualizado");
            else System.out.println("Usuario no encontrado");

        }
    }
}
