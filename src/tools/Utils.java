/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author karimnot
 */
public abstract class Utils {

    public static ImageIcon createIcon(String path) {
        URL url = System.class.getResource(path);
        if (url == null) {
            System.out.println("Recurso no disponible");
        }
        ImageIcon im = new ImageIcon(url);
        return im;
    }
}
