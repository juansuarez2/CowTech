package models;

import java.time.LocalDate;

public class RegistroMedicamento {
    private int id;
    private int idMedicamento;
    private int duracion; // en días
    private LocalDate fechaInicio;

    public RegistroMedicamento() {}

    public RegistroMedicamento(int id, int idMedicamento, int duracion, LocalDate fechaInicio) {
        this.id = id;
        this.idMedicamento = idMedicamento;
        this.duracion = duracion;
        this.fechaInicio = fechaInicio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return "RegistroMedicamento{" +
                "id=" + id +
                ", idMedicamento=" + idMedicamento +
                ", duracion=" + duracion +
                ", fechaInicio=" + fechaInicio +
                '}';
    }
}
