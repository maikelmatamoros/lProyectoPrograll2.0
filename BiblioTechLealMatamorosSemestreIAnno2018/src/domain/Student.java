package domain;

public class Student {
    private String id,name,lastName,carrera;
    private int year;
    
    public Student(){
        this.id="";
        this.name="";
        this.lastName="";
        this.carrera="";
        this.year=0;
    }

    public Student(String id, String name, String lastName, String carrera, int year) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.carrera = carrera;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public int sizeInBytes(){
        return this.name.length()*2+this.lastName.length()*2+this.id.length()*2+this.carrera.length()*2+4;
    }
    
} // fin de la clase
