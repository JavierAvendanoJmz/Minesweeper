package minesweeper;

import gui.MainFrame;
import javax.swing.SwingUtilities;


/**
 *
 * @author karimnot
 */
public class Minesweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }


}
