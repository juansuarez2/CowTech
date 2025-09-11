package models;
import java.sql.Date;

public class Animal {

    private int id;
    private String codigo;
    private Integer idLote; // puede ser null
    private int idRaza;
    private float peso;
    private Sexo sexo;
    private Date fechaNacimiento;
    private String observacion;
    private boolean vacunaObligatoria;
    private Date fechaExpiracionDeVacuna;
    private int idUsuario;


    public enum Sexo {
        HEMBRA, MACHO
    }


    public Animal() {
    }

    // Constructor con todo
    public Animal(int id, String codigo, Integer idLote, int idRaza, float peso, Sexo sexo,
                  Date fechaNacimiento, String observacion, boolean vacunaObligatoria,
                  Date fechaExpiracionDeVacuna, int idUsuario) {
        this.id = id;
        this.codigo = codigo;
        this.idLote = idLote;
        this.idRaza = idRaza;
        this.peso = peso;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.observacion = observacion;
        this.vacunaObligatoria = vacunaObligatoria;
        this.fechaExpiracionDeVacuna = fechaExpiracionDeVacuna;
        this.idUsuario = idUsuario;
    }

    // Constructor sin ID (para el insert)
    public Animal(String codigo, Integer idLote, int idRaza, float peso, Sexo sexo,
                  Date fechaNacimiento, String observacion, boolean vacunaObligatoria,
                  Date fechaExpiracionDeVacuna, int idUsuario) {
        this.codigo = codigo;
        this.idLote = idLote;
        this.idRaza = idRaza;
        this.peso = peso;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.observacion = observacion;
        this.vacunaObligatoria = vacunaObligatoria;
        this.fechaExpiracionDeVacuna = fechaExpiracionDeVacuna;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isVacunaObligatoria() {
        return vacunaObligatoria;
    }

    public void setVacunaObligatoria(boolean vacunaObligatoria) {
        this.vacunaObligatoria = vacunaObligatoria;
    }

    public Date getFechaExpiracionDeVacuna() {
        return fechaExpiracionDeVacuna;
    }

    public void setFechaExpiracionDeVacuna(Date fechaExpiracionDeVacuna) {
        this.fechaExpiracionDeVacuna = fechaExpiracionDeVacuna;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}