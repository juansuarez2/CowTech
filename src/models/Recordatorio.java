package models;

import java.time.LocalDateTime;

public class Recordatorio {
    private int id;
    private String mensaje;
    private LocalDateTime fechaDeEnvio;
    private boolean estado;
    private int usuarioId; // Nuevo campo

    // Constructor vacío
    public Recordatorio() {}

    // Constructor completo
    public Recordatorio(int id, String mensaje, LocalDateTime fechaDeEnvio, boolean estado, int usuarioId) {
        this.id = id;
        this.mensaje = mensaje;
        this.fechaDeEnvio = fechaDeEnvio;
        this.estado = estado;
        this.usuarioId = usuarioId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaDeEnvio() {
        return fechaDeEnvio;
    }

    public void setFechaDeEnvio(LocalDateTime fechaDeEnvio) {
        this.fechaDeEnvio = fechaDeEnvio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "Recordatorio{" +
                "id=" + id +
                ", mensaje='" + mensaje + '\'' +
                ", fechaDeEnvio=" + fechaDeEnvio +
                ", estado=" + estado +
                ", usuarioId=" + usuarioId +
                '}';
    }
}
