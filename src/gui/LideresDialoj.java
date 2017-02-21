/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Lider;

/**
 *
 * @author karimnot
 */
public class LideresDialoj extends JDialog {

    private JPanel pnlBotones;
    private JButton btnCancelar;
    private JPanel pnlLideres;

    public LideresDialoj(JFrame parent) {
        super(parent, true);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 200));
        setResizable(false);

        pnlBotones = createBotonesPanel();
        pnlLideres = createLideresPanel();

        instalaComponentes(null);

        add(pnlLideres, BorderLayout.CENTER);
        add(pnlBotones, BorderLayout.SOUTH);
        pack();
        super.setLocationRelativeTo(parent);
    }

    
    
    
    
    
    
    
    
    
    
    private JPanel createBotonesPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnCancelar = new JButton("Cerrar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LideresDialoj.this.setVisible(false);
            }
        });
        panel.add(btnCancelar);
        return panel;
    }

    private JPanel createLideresPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(500, 150));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }

    public void mostrar(Iterable<Lider> lideres) {
        instalaComponentes(lideres);
        pack();
        super.setVisible(true);
    }

    private void instalaComponentes(Iterable<Lider> lideres) {
        pnlLideres.removeAll();
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1;
        gc.weighty = 0.1;
        colocarTitulo(gc);
        int filas = 1;
        if (lideres != null) {
            for (Lider lider : lideres) {
                if (lider != null) {
                    colocarFila(gc, filas, String.valueOf(filas),
                            lider.getNombre(), lider.getFecha().toString(),
                            lider.getNivel().toString(), lider.getTiempo().toString());
                    filas++;
                }
            }
        }
        for (int i = filas; i <= 5; i++) {
            colocarFila(gc, i, String.valueOf(i), "---", "---", "---", "---");
        }
        validate();
        repaint();
    }

    
    
    
    
    
    
    
    
    
    
    
    private void colocarTitulo(GridBagConstraints gc) {

        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        JLabel tmpTitulo = new JLabel("No.");
        tmpTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        pnlLideres.add(tmpTitulo, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        tmpTitulo = new JLabel("Nombre");
        tmpTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        pnlLideres.add(tmpTitulo, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        tmpTitulo = new JLabel("Fecha");
        tmpTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        pnlLideres.add(tmpTitulo, gc);

        gc.gridx = 3;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        tmpTitulo = new JLabel("Nivel");
        tmpTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        pnlLideres.add(tmpTitulo, gc);

        gc.gridx = 4;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        tmpTitulo = new JLabel("Tiempo");
        tmpTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        pnlLideres.add(tmpTitulo, gc);

    }

    private void colocarFila(GridBagConstraints gc, int row, String posicion,
            String nombre, String fecha, String nivel, String tiempo) {

        gc.gridx = 0;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.CENTER;
        pnlLideres.add(new JLabel(posicion), gc);

        gc.gridx = 1;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.LINE_START;
        pnlLideres.add(new JLabel(nombre), gc);

        gc.gridx = 2;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.LINE_START;
        pnlLideres.add(new JLabel(fecha), gc);

        
        
        
        
        gc.gridx = 3;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.LINE_START;
        pnlLideres.add(new JLabel(nivel), gc);

        gc.gridx = 4;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.CENTER;
        pnlLideres.add(new JLabel(tiempo), gc);
    }

}
