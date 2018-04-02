package domain;

import java.io.Serializable;

public class Material implements Serializable{
    
    private int code;
    private String type;

    public Material() {
        this.code = -1;
        this.type = "";
    } // constructor

    public Material(int code, String type) {
        this.code = code;
        this.type = type;
    } // constructor sobrecargado

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Material{" + "code=" + code + ", type=" + type + "} - ";
    }

} //fin de la clase
