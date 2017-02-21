/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.Celda;
import controller.Controller;
import controller.LideresController;
import controller.enumeraciones.Niveles;
import controller.enumeraciones.Status;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import tools.Utils;

/**
 *
 * @author karimnot
 */
public final class MainFrame extends JFrame {

    private Integer dimensionX;
    private Integer dimensionY;
    private Integer noMinas;
    private Niveles nivel;

    private EncabezadoPanel pnlEncabezado;
    private BotonesPanel pnlBotones;
    private Controller controller;
    private LideresController lideresController;
    private LideresDialoj lideresDlg;
    private AboutDialoj aboutDlg;

    public MainFrame() {
        super("MineSweeper");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());

        dimensionX = 8;
        dimensionY = 8;
        noMinas = 10;
        nivel = Niveles.FACIL;

        controller = new Controller(dimensionX, dimensionY, noMinas);
        lideresController = new LideresController();
        lideresDlg = new LideresDialoj(this);
        aboutDlg = new AboutDialoj(this);

        initComponents();

        super.setJMenuBar(createMenu());
        super.add(pnlEncabezado, BorderLayout.NORTH);
        super.add(pnlBotones, BorderLayout.CENTER);

        super.pack();
        super.setResizable(false);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    public void initComponents() {
        pnlEncabezado = new EncabezadoPanel();
        pnlEncabezado.setMinas(controller.getCeldasMarcadas());
        pnlEncabezado.setListener(new EncabezadoListener() {
            @Override
            public void doControlButtonClick() {
                juegoNuevo();
            }
        });
        pnlBotones = new BotonesPanel(controller.getCelda(),
                controller.getX(), controller.getY());
        pnlBotones.setListener(new BotonesListener() {
            @Override
            public void doBotonesTableroClick(Celda celda) {
                if (controller.isJuegoActivo()) {
                    if (celda.getStatus() == Status.DISPONIBLE) {
                        controller.abrirCelda(celda.getX(), celda.getY());
                        pnlBotones.refresh();
                        pnlEncabezado.iniciarCronometro();
                        evaluaJuego();
                    }
                }
            }

            @Override
            public void doBotonesRigthClick(Celda celda) {
                if (controller.isJuegoActivo()) {
                    celda.nextStatus();
                    pnlEncabezado.setMinas(controller.getCeldasMarcadas());
                }
            }
        });
    }

    public void evaluaJuego() {
        boolean isPerdedor = controller.isJuegoActivo();
        boolean isGanador = controller.isGanador();
        if (!isPerdedor || isGanador) {
            pnlEncabezado.detenerCronometro();
            controller.destaparCeldas();
            pnlBotones.refresh();
            pnlEncabezado.changeControlButtonIcon(controller.getStatus());
            if (isGanador) {
                establecerMarca(pnlEncabezado.getCronometro().getTiempo());
            }
        }
    }

    private void establecerMarca(int tiempo) {
        if (lideresController.isAceptado(nivel, tiempo)) {
            String nombre = (String) JOptionPane.showInputDialog(
                    this,
                    "Escribe tu nombre para seas recordado:",
                    "Has impuesto nuevo record",
                    JOptionPane.PLAIN_MESSAGE,
                    Utils.createIcon("/images/descargas.jpg"),
                    null,
                    "noname");
            lideresController.add(nombre, tiempo, nivel);
        }
    }

    public void juegoNuevo() {
        super.remove(pnlEncabezado);
        super.remove(pnlBotones);
        controller = new Controller(dimensionX, dimensionY, noMinas);
        initComponents();
        super.add(pnlEncabezado, BorderLayout.NORTH);
        super.add(pnlBotones, BorderLayout.CENTER);
        pnlEncabezado.repaint();
        pnlBotones.repaint();
        super.repaint();
        super.pack();
    }

    public JMenuBar createMenu() {
        JMenuBar mainMenu = new JMenuBar();

        JMenu mmJuego = new JMenu("Juego");
        JMenu mmDificultad = new JMenu("Dificultad");
        JMenu mmAyuda = new JMenu("Ayuda");

        JMenuItem miNuevo = new JMenuItem("Nuevo");
        miNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        miNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juegoNuevo();
            }
        });
        JMenuItem miTop = new JMenuItem("Mejores tiempos...");
        miTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lideresDlg.mostrar(lideresController.getLista(nivel));
            }
        });
        JMenuItem miSalir = new JMenuItem("Salir");
        miSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        miSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        
        
        
        JMenuItem miFacil = new JRadioButtonMenuItem("Facil", true);
        miFacil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dimensionX = 8;
                dimensionY = 8;
                noMinas = 10;
                nivel = Niveles.FACIL;
                juegoNuevo();
                MainFrame.this.setLocationRelativeTo(null);
            }
        });
        JMenuItem miMedio = new JRadioButtonMenuItem("Medio");
        miMedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dimensionX = 15;
                dimensionY = 12;
                noMinas = 30;
                nivel = Niveles.INTERMEDIO;
                juegoNuevo();
                MainFrame.this.setLocationRelativeTo(null);
            }
        });
        JMenuItem miDificil = new JRadioButtonMenuItem("Dificil");
        miDificil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dimensionX = 30;
                dimensionY = 15;
                noMinas = 70;
                nivel = Niveles.DIFICIL;
                juegoNuevo();
                MainFrame.this.setLocationRelativeTo(null);
            }
        });
        JMenuItem miPersonalizado = new JRadioButtonMenuItem("Personalizado");
        miPersonalizado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField edtColumn = new JTextField();
                JTextField edtRow = new JTextField();
                JTextField edtMina = new JTextField();
                Object[] message = {"Filas:", edtRow, "Columnas:", edtColumn, "Minas:", edtMina};
                boolean bandera = false;
                do {
                    try {
                        int option = JOptionPane.showConfirmDialog(null, message, 
                                "Personalizado", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            dimensionX = Integer.valueOf(edtColumn.getText());
                            dimensionY = Integer.valueOf(edtRow.getText());
                            noMinas = Integer.valueOf(edtMina.getText());
                            nivel = Niveles.PERSONALIZADO;
                        }
                    } catch (Exception ex) {
                        bandera = true;
                    }
                } while (bandera);
                juegoNuevo();
                MainFrame.this.setLocationRelativeTo(null);
            }
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(miFacil);
        buttonGroup.add(miMedio);
        buttonGroup.add(miDificil);
        buttonGroup.add(miPersonalizado);

        JMenuItem miAcerca = new JMenuItem("Acerca de...");
        miAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        miAcerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutDlg.setVisible(true);
            }
        });

        mmJuego.add(miNuevo);
        mmJuego.add(miTop);
        mmJuego.add(new JSeparator());
        mmJuego.add(miSalir);

        mmDificultad.add(miFacil);
        mmDificultad.add(miMedio);
        mmDificultad.add(miDificil);
        mmDificultad.add(new JSeparator());
        mmDificultad.add(miPersonalizado);

        mmAyuda.add(miAcerca);

        mainMenu.add(mmJuego);
        mainMenu.add(mmDificultad);
        mainMenu.add(mmAyuda);
        return mainMenu;
    }
}
