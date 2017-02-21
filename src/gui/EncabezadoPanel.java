/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.enumeraciones.StatusJuego;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import tools.Cronometro;
import tools.Utils;

/**
 *
 * @author karimnot
 */
public class EncabezadoPanel extends JPanel {

    private JTextField edtMinas;
    private JTextField edtTiempo;
    private JButton btnControl;
    private EncabezadoListener listener;
    private Cronometro cronometro;

    public EncabezadoPanel() {
        super.setLayout(new BorderLayout());
        super.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED), 
                BorderFactory.createBevelBorder(BevelBorder.RAISED)));

        edtMinas = new JTextField(6);
        edtMinas.setText("000");
        edtMinas.setHorizontalAlignment(SwingConstants.CENTER);
        edtMinas.setFont(new Font("Courier New", Font.BOLD, 24));
        edtMinas.setForeground(Color.RED);
        edtMinas.setBackground(Color.BLACK);
        edtMinas.setFocusable(false);

        edtTiempo = new JTextField(6);
        edtTiempo.setText("000");
        edtTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        edtTiempo.setFont(new Font("Courier New", Font.BOLD, 24));
        edtTiempo.setForeground(Color.RED);
        edtTiempo.setBackground(Color.BLACK);
        edtTiempo.setFocusable(false);


        
        cronometro = new Cronometro(edtTiempo);

        btnControl = new JButton();
        btnControl.setPreferredSize(new Dimension(40, 40));
        btnControl.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeControlButtonIcon(StatusJuego.NORMAL);
        btnControl.setFocusable(false);
        btnControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.doControlButtonClick();
            }
        });

        JPanel pnlBoton = new JPanel();
        pnlBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBoton.add(btnControl);

        add(edtMinas, BorderLayout.WEST);
        add(edtTiempo, BorderLayout.EAST);
        add(pnlBoton, BorderLayout.CENTER);
    }

    public void setListener(EncabezadoListener listener) {
        this.listener = listener;
    }

    public void iniciarCronometro() {
        if (!cronometro.isAlive()) {
            cronometro.start();
        }
    }

    public void detenerCronometro(){
        cronometro.stop();
    }
    
    public void setMinas(int minitas) {
        edtMinas.setText(String.format(" %03d ", minitas));
    }

    public void changeControlButtonIcon(StatusJuego status){
        switch (status) {
            case NORMAL:         
                btnControl.setIcon(Utils.createIcon("/images/vivo.png"));
                break;
            case PERDEDOR:         
                btnControl.setIcon(Utils.createIcon("/images/muerto.png"));
                break;
            case GANADOR:         
                btnControl.setIcon(Utils.createIcon("/images/ganador.png"));
                break;                
        }
    }
    
    public Cronometro getCronometro() {
        return cronometro;
    }
}