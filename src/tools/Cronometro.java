/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import javax.swing.JTextField;

/**
 *
 * @author karimnot
 */
public class Cronometro  extends Thread {

    private int tiempo;
    private JTextField oTexto;

    public Cronometro(JTextField textField) {
        tiempo = 0;
        oTexto = textField;
    }

    @Override
    public void run() {
        while (true) {
            tiempo++;
            oTexto.setText(String.format(" %03d ", tiempo));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getTiempo() {
        return tiempo;
    }
    
    
}
