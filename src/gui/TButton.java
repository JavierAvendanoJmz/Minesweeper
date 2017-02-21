/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.Celda;
import javax.swing.JButton;
import tools.Utils;

/**
 *
 * @author karimnot
 */
public class TButton extends JButton {

    private Celda celda;

    public TButton(Celda celda, int sizeX, int sizeY) {
        this.celda = celda;
        super.setBounds(5 + celda.getX() * sizeX, 5 + celda.getY() * sizeY, sizeX, sizeY);
    }

    public void refreshIcon() {
        String imgPath = null;
        switch (celda.getStatus()) {
            case INTERROGACION:
                imgPath = "/images/interrogacion.png";
                break;
            case MINA:
                imgPath = "/images/mina.png";
                break;
            case EXPLOSION:
                imgPath = "/images/explosion.png";
                break;
            case BANDERA:
                imgPath = "/images/bandera.png";
                break;
            case BANDERAINCORRECTA:
                imgPath = "/images/banderaError.png";
                break;
        }
        super.setIcon( imgPath != null ? Utils.createIcon(imgPath) : null);
    }

    public String toString() {
        return "[" + celda.getX() + "," + celda.getY() + "]";
    }

    public Celda getCelda() {
        return celda;
    }

    public void setCelda(Celda celda) {
        this.celda = celda;
    }

}
