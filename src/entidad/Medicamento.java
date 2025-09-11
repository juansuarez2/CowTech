package entidad;

import enums.TipoMed;

public class Medicamento {

    private int id;
    private String nombre;
    private TipoMed tipo;

    public Medicamento(){};

    public Medicamento(int id, String nombre, TipoMed tipo){
        this.id=id;
        this.nombre=nombre;
        this.tipo=tipo;
    };

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

    public TipoMed getTipo() {
        return tipo;
    }

    public void setTipo(TipoMed tipo) {
        this.tipo = tipo;
    }

}
