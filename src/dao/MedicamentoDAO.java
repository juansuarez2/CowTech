package dao;

import database.DatabaseConnection;
import entidad.Medicamento;
import enums.TipoMed;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {


    public void agregarMedicamento(String nombre, TipoMed tipo) {

        String consulta = "INSERT INTO medicamento (nombre, tipo) VALUES (?,?)";

        try {
            PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta);
            ps.setString(1, nombre);
            ps.setString(2, tipo.name());

            ps.executeUpdate();
            System.out.println("Medicamento agregado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificarMedicamento(int id,String nombre, TipoMed tipo){

        String consulta = "UPDATE medicamento SET nombre=?, tipo=? WHERE medicamento.id=?";

        try {

            PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta);
            ps.setString(1, nombre);
            ps.setString(2, tipo.name());
            ps.setInt(3, id);

            int existe =ps.executeUpdate();
            if(existe==0){
                System.out.println("No existe un medicamento con ese Id");
            }else {
                System.out.println("Medicamento modificado");
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Medicamento> listarMedicamentos(){
        List<Medicamento> medicamentos = new ArrayList<>();

        String consulta = "SELECT * FROM medicamento";

        try{

            Statement st = DatabaseConnection.getInstancia().getConnection().createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setId(rs.getInt("Id"));
                medicamento.setNombre(rs.getString("Nombre"));
                String tipo = rs.getString("tipo");
                medicamento.setTipo(TipoMed.valueOf(tipo.trim().toUpperCase()));
                medicamentos.add(medicamento);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return medicamentos;
    }

    public void eliminarMedicamento(int id){

        String consulta = "DELETE FROM medicamento WHERE medicamento.id =?";

        try {

            PreparedStatement ps = DatabaseConnection.getInstancia().getConnection().prepareStatement(consulta);
            ps.setInt(1,id);
            int existe = ps.executeUpdate();

            if(existe==0){
                System.out.println("No existe un medicamento con ese Id");
            }else {
                System.out.println("Medicamento eliminado");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

}

