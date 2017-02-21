/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.enumeraciones.Status;

/**
 *
 * @author karimnot
 */
public class Celda {

    private Status status;
    private boolean mina;
    private int indicador;

    private int x;
    private int y;
    
    public Celda(int x, int y){
        this.x = x;
        this.y = y;
        status = Status.DISPONIBLE;
        mina = false;
        indicador = -1;
    }
    
    public void nextStatus(){
        switch (status) {
            case DISPONIBLE:
                status = Status.BANDERA;
                break;
            case INTERROGACION:
                status = Status.DISPONIBLE;
                break;
            case BANDERA:
                status = Status.INTERROGACION;
                break;                
        }
    }
    
    public String toString(){
        return String.format("% 3d", indicador);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    
    
    
    
    
    
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    public int getIndicador() {
        return indicador;
    }

    public void setIndicador(int indicador) {
        this.indicador = indicador;
    }
    
}
