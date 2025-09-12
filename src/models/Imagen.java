package models;

public class Imagen {
    private int id;
    private String url;
    private int idAnimal;

    public Imagen() {}

    public Imagen(int id, String url, int idAnimal) {
        this.id = id;
        this.url = url;
        this.idAnimal = idAnimal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", idAnimal=" + idAnimal +
                '}';
    }
}
