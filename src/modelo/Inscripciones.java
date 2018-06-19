/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Inscripciones {
    private int edad;
    private String carnet, nombres, apellidos, universidad;
    private boolean existencia;
    
    public Inscripciones(){}

    public Inscripciones(String carnet, int edad, String nombres, String apellidos, String universidad, boolean existencia) {
        this.carnet = carnet;
        this.edad = edad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.universidad = universidad;
        this.existencia = existencia;
    }

    public Inscripciones(int edad, String nombres, String apellidos, String universidad, boolean existencia) {
        this.edad = edad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.universidad = universidad;
        this.existencia = existencia;
    }

    public Inscripciones(String nombres, String apellidos, String universidad, boolean existencia) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.universidad = universidad;
        this.existencia = existencia;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public boolean getExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    @Override
    public String toString() {
        return "Inscripciones{" + "carnet=" + carnet + ", edad=" + edad + ", nombres=" + nombres + ", apellidos=" + apellidos + ", universidad=" + universidad + ", existencia=" + existencia + '}';
    }
}
