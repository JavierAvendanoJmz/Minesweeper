/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.enumeraciones;

/**
 *
 * @author karimnot
 */
public enum Niveles {
    
    FACIL(0),
    INTERMEDIO(1),
    DIFICIL(2),
    PERSONALIZADO(3);
    
    private int nivel;
    
    private Niveles(int nivel){
        this.nivel = nivel;
    }
    
    public int getNivel(){
        return nivel;
    }
    
}
