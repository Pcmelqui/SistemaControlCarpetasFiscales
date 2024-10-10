/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacontrolcarpetasfiscales;

/**
 *
 * @author Wilson
 */
public class Perito {
    private int idPerito;
    private String nombres;
    private String apellidos;
    private String lugarTrabajo;

    // Constructor
    public Perito(int idPerito, String nombres, String apellidos, String lugarTrabajo) {
        this.idPerito = idPerito;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.lugarTrabajo = lugarTrabajo;
    }

    // Getters y Setters
    public int getIdPerito() {
        return idPerito;
    }

    public void setIdPerito(int idPerito) {
        this.idPerito = idPerito;
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

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }
}

