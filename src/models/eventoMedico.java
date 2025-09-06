package models;

import java.sql.Date;

public class eventoMedico {
    public enum tipoEvento{
        Vacunacion,
        Revision,
        Parto,
        Cirugia,
        Rehabilitacion
    }
    private int id;
    private tipoEvento evento;
    private Date fecha;
    private int codigoAnimal;
    private int idRegistroMedicamento;
    private int idRegistroDeEnfermedad;

    public eventoMedico(int id, Date fecha, tipoEvento evento, int codigoAnimal, int idRegistroMedicamento, int idRegistroDeEnfermedad){
        this.codigoAnimal = codigoAnimal;
        this.idRegistroMedicamento = idRegistroMedicamento;
        this.idRegistroDeEnfermedad = idRegistroDeEnfermedad;
        this.evento = evento;
        this.fecha = fecha;
    }

    //Getters//
    public int getId() {
        return id;
    }
    public tipoEvento getEvento() {
        return evento;
    }
    public Date getFecha() {
        return fecha;
    }
    public int getCodigoAnimal() {
        return codigoAnimal;
    }
    public int getIdRegistroMedicamento() {
        return idRegistroMedicamento;
    }
    public int getIdRegistroDeEnfermedad() {
        return idRegistroDeEnfermedad;
    }

    //Setters//

    public void setEvento(tipoEvento evento) {
        this.evento = evento;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setIdRegistroDeEnfermedad(int idRegistroDeEnfermedad) {
        this.idRegistroDeEnfermedad = idRegistroDeEnfermedad;
    }
    public void setIdRegistroMedicamento(int idRegistroMedicamento) {
        this.idRegistroMedicamento = idRegistroMedicamento;
    }

    public void mostrarEventoMedico(){
        System.out.println("###################");
        System.out.println("ID EVENTO: " + this.id);
        System.out.println("ID ENFERMEDAD: " + this.idRegistroDeEnfermedad);
        System.out.println("ID MEDICAMENTO: " + this.idRegistroMedicamento);
        System.out.println("FECHA: " + this.fecha);
        System.out.println("EVENTO: " + this.evento);
        System.out.println("###################");
    }
}
