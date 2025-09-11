package models;

public class Enfermedad {
    private int id;
    private String nombre;


    public Enfermedad() {
    }

    public Enfermedad(String nombre) {
        this.nombre = nombre;
    }

    public Enfermedad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

// Getters y Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // --- toString ---
    @Override
    public String toString() {
        return "Enfermedad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
