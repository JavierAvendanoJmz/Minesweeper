/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.Celda;
import controller.enumeraciones.Status;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author karimnot
 */
public final class BotonesPanel extends JPanel {

    private static final int DIMX = 40;
    private static final int DIMY = 40;

    private BotonesListener listener;
    private Celda[][] celdas;
    private int xx;
    private int yy;

    public BotonesPanel(Celda[][] celdas, int x, int y) {
        xx = x;
        yy = y;
        this.celdas = celdas;
        super.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED), 
                BorderFactory.createBevelBorder(BevelBorder.RAISED)));
        super.setPreferredSize(new Dimension(x * DIMX + 10, y * DIMY + 10));
        super.setLayout(null);
        installButtons(x, y);
    }

    private void installButtons(int x, int y) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (celdas[j][i].getStatus() != Status.ABIERTO) {
                    super.add(getTButton(celdas[j][i]));
                } else {
                    super.add(getLabel(celdas[j][i]));
                }
            }
        }
    }

    
    
    
    private TButton getTButton(Celda celdita) {
        TButton btnTemporal = new TButton(celdita, DIMX, DIMY);
        btnTemporal.setFocusable(false);
        btnTemporal.refreshIcon();
        btnTemporal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.doBotonesTableroClick(celdita);
            }
        });
        btnTemporal.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON3) {
                    listener.doBotonesRigthClick(celdita);
                    btnTemporal.refreshIcon();
                }
            }
        });
        return btnTemporal;
    }

    private JLabel getLabel(Celda celdita) {
        int in = celdita.getIndicador();
        JLabel label = new JLabel(in > 0 ? String.valueOf(in) : "");
        label.setBounds(5 + celdita.getX() * DIMX, 5 + celdita.getY() * DIMY, DIMX, DIMY);
        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(getColorLabel(in));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public void refresh() {
        super.removeAll();
        installButtons(xx, yy);
        super.repaint();
    }

    public void setListener(BotonesListener listener) {
        this.listener = listener;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private Color getColorLabel(int valor) {
        switch (valor) {
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.RED;
            case 4:
                return Color.BLACK;
            case 5:
                return Color.CYAN;
            case 6:
                return Color.PINK;
            case 7:
                return Color.MAGENTA;
            case 8:
                return Color.ORANGE;
            case 9:
                return Color.YELLOW;
            case 0:
                return Color.LIGHT_GRAY;
        }
        return Color.BLUE;
    }
}
