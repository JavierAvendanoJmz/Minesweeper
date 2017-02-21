package controller;

import controller.enumeraciones.StatusJuego;
import controller.enumeraciones.Status;
import java.util.Random;

/**
 *
 * @author karimnot
 */
public class Controller {
    
    private Celda[][] celda;
    private Integer x;
    private Integer y;
    private Integer minas;
    private boolean juegoActivo;
    private StatusJuego status;
    
    public Controller(int x, int y, int minas){
        this.x = x;
        this.y = y;
        this.minas = minas;
        this.juegoActivo = true;
        inicializar();
        status = StatusJuego.NORMAL;
    }
    
    public void abrirCelda(int i, int j) {
        if (!celda[i][j].isMina()) {
            celda[i][j].setStatus(Status.ABIERTO);
            int minitas = evaluaCeldas(i, j);
            if (minitas == 0) {
                celda[i][j].setIndicador(0);
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (isCeldaValida(k, l)&&(celda[k][l].getStatus() == Status.DISPONIBLE)) {
                            abrirCelda(k, l);
                        }
                    }
                }
            } else {
                celda[i][j].setIndicador(minitas);
            }
        }else{
            celda[i][j].setStatus(Status.EXPLOSION); 
            status = StatusJuego.PERDEDOR;
            juegoActivo = false;
        }
    }   
    
    public void marcarCelda(int x, int y){
        celda[x][y].nextStatus();
    }
    
    public Celda[][] getCelda(){
        return celda;
    }
    
    
    
    public boolean isGanador(){
        boolean resultado = true;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if ( (celda[j][i].getStatus() != Status.ABIERTO) &&
                        (!celda[j][i].isMina() ) ){
                    return false;
                }
            }
        }      
        if (resultado){
            juegoActivo = false;
            status = StatusJuego.GANADOR;            
        }
        return resultado;
    }
    
    public int getCeldasMarcadas(){
        int resultado = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if ( celda[j][i].getStatus() == Status.BANDERA){
                    resultado++;
                }
            }
        }         
        return minas - resultado;        
    }
    
    public void destaparCeldas(){
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if ( celda[j][i].getStatus() != Status.ABIERTO){
                    if ((celda[j][i].isMina())&&(celda[j][i].getStatus() == Status.DISPONIBLE)){
                        celda[j][i].setStatus(Status.MINA);
                    }else if ((!celda[j][i].isMina())&&(celda[j][i].getStatus() == Status.BANDERA)){
                        celda[j][i].setStatus(Status.BANDERAINCORRECTA);
                    }
                }
            }
        }           
    }
    
    public StatusJuego getStatus(){
        return status;
    }
    
    private void inicializar(){
        celda = new Celda[x][y];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                celda[j][i] = new Celda(j,i);
            }
        }     
        colocarMinas();
    }
    
    
    
    
    private void colocarMinas() {
        int minaX, minaY;
        int i = 0;
        while (i < minas) {
            Random random = new Random();
            minaX = random.nextInt(x);
            minaY = random.nextInt(y);
            if (!(celda[minaX][minaY]).isMina()) {
                celda[minaX][minaY].setMina(true);
                i++;
            }
        }
    }    

    private boolean isCeldaValida(int i, int j) {
        return (((i >= 0) && (i <= x)) && ((j >= 0) && (j <= y))) && ((i != x) && (j != y));
    }

    private int evaluaCeldas(int i, int j) {
        int minitas = 0;
        for (int n = i - 1; n <= i + 1; n++) {
            for (int m = j - 1; m <= j + 1; m++) {
                if ((isCeldaValida(n, m)) && (celda[n][m].isMina())) {
                    minitas++;
                }
            }
        }
        return minitas;
    }    

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getMinas() {
        return minas;
    }

    public void setMinas(Integer minas) {
        this.minas = minas;
    }

    public boolean isJuegoActivo() {
        return juegoActivo;
    }
}