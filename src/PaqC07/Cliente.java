package PaqC07;

import java.io.Serializable;

public class Cliente implements Serializable {

     private String nombre, apellidos;
     private int dni, teléfono, numTarjeta;
     private String alta, baja;
     private String régimen;

    public Cliente(String nombre, String apellidos, int dni, int teléfono, int numTarjeta, String alta, String baja, String régimen) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.teléfono = teléfono;
        this.numTarjeta = numTarjeta;
        this.alta = alta;
        this.baja = baja;
        this.régimen = régimen;
    }
    public Cliente() {
        this.nombre = null;
        this.apellidos = null;
        this.dni = 0;
        this.teléfono = 0;
        this.numTarjeta = 0;
        this.alta = null;
        this.baja = null;
        this.régimen = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getTeléfono() {
        return teléfono;
    }

    public void setTeléfono(int teléfono) {
        this.teléfono = teléfono;
    }

    public int getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(int numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getAlta() {
        return alta;
    }

    public void setAlta(String alta) {
        this.alta = alta;
    }

    public String getBaja() {
        return baja;
    }

    public void setBaja(String baja) {
        this.baja = baja;
    }

    public String getRégimen() {
        return régimen;
    }

    public void setRégimen(String régimen) {
        this.régimen = régimen;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + " " + this.apellidos +
                "\tDNI: " + this.dni + "\nTeléfono: " + this.teléfono +
                "\tNúmero de tarjeta: " + this.numTarjeta + "\nFecha de alta: " + this.alta +
                "\tFecha de baja: " + this.baja + "\nRégimen: " + this.régimen;
    }
}
