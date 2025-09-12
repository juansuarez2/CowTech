package models;

import java.sql.Date;

public class RegistroEnfermedad {
    private int id;
    private int idEnfermedad;
    private int idAnimal;
    private Date fechaInicio;
    private Date fechaFinal;
    private boolean estado;

    public RegistroEnfermedad(int idEnfermedad, int idAnimal, Date fechaInicio, Date fechaFinal, boolean estado){
        this.idEnfermedad = idEnfermedad;
        this.idAnimal = idAnimal;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
    }

    public RegistroEnfermedad(int id, int idEnfermedad, int idAnimal, Date fechaInicio, Date fechaFinal, boolean estado){
        this.id = id;
        this.idEnfermedad = idEnfermedad;
        this.idAnimal = idAnimal;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
    }

    //Getters//
    public int getId() {
        return id;
    }
    public int getIdEnfermedad() {
        return idEnfermedad;
    }
    public int getIdAnimal() {
        return idAnimal;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public Date getFechaFinal() {
        return fechaFinal;
    }
    public boolean getEstado() {
        return estado;
    }

    //Setters//
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }
    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void mostrarRegistro(){
        System.out.println("###################");
        System.out.println("ID REGISTRO: " + this.id);
        System.out.println("ID ENFERMEDAD: " + this.idEnfermedad);
        System.out.println("ID ANIMAL: " + this.idAnimal);
        System.out.println("FECHA INICIO:" + this.fechaInicio);
        System.out.println("FECHA FINAL: " + this.fechaFinal);
        System.out.println("ESTADO: " + this.estado);
        System.out.println("###################");
    }
}
