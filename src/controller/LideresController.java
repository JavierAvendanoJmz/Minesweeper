/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.enumeraciones.Niveles;
import model.Lideres;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import model.Lider;

/**
 *
 * @author karimnot
 */
public class LideresController implements Serializable{

    public static String FILE = "records.dat";

    private Lideres[] listaLideres;

    public LideresController() {
        listaLideres = new Lideres[4];
        listaLideres[Niveles.FACIL.getNivel()] = new Lideres(5);
        listaLideres[Niveles.INTERMEDIO.getNivel()] = new Lideres(5);
        listaLideres[Niveles.DIFICIL.getNivel()] = new Lideres(5);
        listaLideres[Niveles.PERSONALIZADO.getNivel()] = new Lideres(5);
        load();
    }

    public void add(String nombre, int tiempo, Niveles nivel) {
        listaLideres[nivel.getNivel()].add(new Lider(nombre, new Date(), tiempo, nivel));
        save();
    }

    public boolean isAceptado(Niveles nivel, int tiempo) {
        return listaLideres[nivel.getNivel()].isAceptado(tiempo);
    }

    public Iterable getLista(Niveles nivel) {
        return listaLideres[nivel.getNivel()];
    }
    
    private void save() {
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream(new File(FILE)));
            out.writeObject(listaLideres);
            out.close();
        } catch (IOException ex) {
        }
    }

    
    
    private void load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(FILE)));
            listaLideres = (Lideres[])in.readObject();
            in.close();
        } catch (ClassNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}
