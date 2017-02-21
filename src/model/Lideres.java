/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author karimnot
 */
public class Lideres implements Serializable, Iterable<Lider>{

    private Lider[] lideres;
    private int maximo;
    private int elementos;

    public Lideres(int max) {
        maximo = max;
        lideres = new Lider[max];
        elementos = 0;
    }

    public void add(Lider lider) {
        if (isAceptado(lider.getTiempo())) {
            boolean bandera = true;
            int i = 0;
            while ((i < elementos) && bandera) {
                if (lideres[i].getTiempo() > lider.getTiempo()) {
                    bandera = false;
                } else {
                    i++;
                }
            }
            elementos = elementos < maximo-1 ? elementos + 1 : elementos;
            for (int j = elementos; j > i; j--) {
                lideres[j] = lideres[j-1];                
            }
            lideres[i] = lider;
        }
    }

    public boolean isAceptado(int tiempo) {
        if (elementos < maximo) {
            return true;
        } else {
            for (int i = 0; i <= elementos; i++) {
                if (lideres[i].getTiempo() > tiempo) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Lider[] getLideres(){
        return lideres;
    }

    public void setLider(Lider[] lider){
        lideres = lider;
    }

    @Override
    public Iterator<Lider> iterator() {
        return new Iterator<Lider>() {

            private int aux = 0;
            
            @Override
            public boolean hasNext() {
                return aux <= elementos;
            }

            @Override
            public Lider next() {
                Lider l = lideres[aux];
                aux++;
                return l;
            }
        };
    }

}
