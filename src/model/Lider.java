/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.enumeraciones.Niveles;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author karimnot
 */
public class Lider implements Serializable{

    private String nombre;
    private Date fecha;
    private Integer tiempo;
    private Niveles nivel;

    public Lider(String nombre, Date fecha, Integer tiempo, Niveles nivel) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tiempo = tiempo;
        this.nivel = nivel;
    }  
    
    @Override
    public String toString(){
        return nombre+" "+tiempo+" "+fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Niveles getNivel() {
        return nivel;
    }

    public void setNivel(Niveles nivel) {
        this.nivel = nivel;
    }

}
