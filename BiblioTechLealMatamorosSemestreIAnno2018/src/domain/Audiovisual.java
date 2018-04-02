package domain;

public class Audiovisual extends Material {

    private String id; // marca, CD, DVD
    private String description;
    private boolean availability;

    public Audiovisual() {
        this.id = "";
        this.description = "";
        availability = false;
    } // constructor

    public Audiovisual(String id, String description, int code, String type, boolean availability) {
        super(code, type);
        this.id = id;
        this.description = description;
        this.availability = availability;
    } // sobrecargado

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() + "Audiovisual{" + "id=" + id + ", description=" + description + ", availability=" + availability + '}';
    }

} // fin de la clase
