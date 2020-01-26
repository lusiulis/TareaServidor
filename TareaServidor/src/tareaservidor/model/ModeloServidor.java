/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaservidor.model;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Observable;
import tareaservidor.control.GestorServidor;
import tareaservidor.util.ConexionServidor;
import tareaservidor.views.Casilla;

/**
 *
 * @author Kevin
 */
public class ModeloServidor extends Observable {

    private final Integer PUERTOPROPIO = 420; //puerto donde se va abrir el socket
    public static Integer puertoOponente = 421; //puerto del oponente
    public static Boolean continuar = true; //booleano para saber si continuar escuchando
    public static ServerSocket serverSocket; //socket donde se escucha
    public static Boolean primeraConexion = true; // booleando para saber si es la primera transaccion en una conexion
    private ConexionServidor conexion; //instancia de clase que maneja conexiones
    private final Color colorPropio = Color.RED; //color que pinto mis casillas
    private final Color colorOponente = Color.BLUE; //color que pinto las casillas de mi oponente
    private Boolean miTurno; //booleano para saber si puedo jugar o ocupo esperar a que el oponente juege
   
    private final Casilla casillas[][];
    
    public ModeloServidor() {
        miTurno = false;
        casillas = new Casilla[8][8];
    }

    public void empezarAEscuchar(GestorServidor gestor) {
        try {
            serverSocket = new ServerSocket(PUERTOPROPIO);
            continuar = true;
            //esperar conexion
            conexion = new ConexionServidor(null, gestor);
            conexion.start();
        } catch (Exception ex) {
            System.err.println("Error al iniciar a escuchar: " + ex.getLocalizedMessage());
        }

    }

    //metodo que hay que llamar cuando se cierre el programa para que no quede abierto un hilo
    public void detenerEscuchar() throws IOException {
        serverSocket.close();
    }

    public void enviarMensaje(String aEnviar) {
        conexion.enviarMensaje(aEnviar);
        miTurno = false;
    }

    public void pintar(Integer x, Integer y) {
        if (miTurno) {
            Casilla casilla = new Casilla(x, y, colorPropio);
            casilla.setClick(true);
            casillas[x][y] = casilla;
            enviarMensaje(x + "," + y);
            
            
            if (Gane(x, y)) {
                setChanged();
                notifyObservers(casillas);
                
                setChanged();
                notifyObservers("Gane propio");
            } else {
                setChanged();
                notifyObservers(casillas);
                
                setChanged();
                notifyObservers("Ha jugado");
            }
        } else {
            setChanged();
            notifyObservers("Debe esperar su turno...");
        }

    }

    public void pintarOponente(Integer x, Integer y) {
        Casilla casilla = new Casilla(x, y, colorOponente);
        casilla.setClick(true);
        casillas[x][y] = casilla;
        miTurno = true;

        setChanged();
        notifyObservers(casillas);
        if (Gane(x, y)) {
            setChanged();
            notifyObservers("Gane enemigo");
        } else {

            setChanged();
            notifyObservers("Su turno");
        }
    }
    
     public void setCasilla(int x,int y,Casilla m){
        casillas[x][y] = m;   
    }

    public Casilla[][] getCasillas(){
        return casillas;
    }
    
    private boolean Gane(int x, int y) {
        boolean gane;

        gane = x <= 4;
        for (int i = x; i < x + 4 && gane; i++) {
            if (!casillas[i][y].getColorCasilla().equals(casillas[x][y].getColorCasilla())) {
                gane = false;
            }
        }
        if (gane) {
            return gane;
        }

        gane = x >= 3;
        for (int i = x; i > x - 4 && gane; i--) {
            if (!casillas[i][y].getColorCasilla().equals(casillas[x][y].getColorCasilla())) {
                gane = false;
            }
        }
        if (gane) {
            return gane;
        }

        gane = y <= 4;
        for (int i = y; i < y + 4 && gane; i++) {
            if (!casillas[x][i].getColorCasilla().equals(casillas[x][y].getColorCasilla())) {
                gane = false;
            }
        }
        if (gane) {
            return gane;
        }

        gane = y >= 3;
        for (int i = y; i > y - 4 && gane; i--) {
            if (!casillas[x][i].getColorCasilla().equals(casillas[x][y].getColorCasilla())) {
                gane = false;
            }
        }
        if (gane) {
            return gane;
        }

        gane = x >= 3 && y >= 3;
        for (int i = 0; i < 4 && gane; i++) {
            if (!casillas[x - i][y - i].getColorCasilla().equals(casillas[x][y].getColorCasilla())) {
                gane = false;
            }
        }
        if (gane) {
            return gane;
        }

        gane = x >= 3 && y <= 4;
        for (int i = 0; i < 4 && gane; i++) {
            if (!casillas[x - i][y + i].getColorCasilla().equals(casillas[x][y].getColorCasilla())) {
                gane = false;
            }
        }
        if (gane) {
            return gane;
        }

        gane = x <= 4 && y <= 4;
        for (int i = 0; i < 4 && gane; i++) {
            if (!casillas[x + i][y + i].getColorCasilla().equals(casillas[x][y].getColorCasilla())) {
                gane = false;
            }
        }
        if (gane) {
            return gane;
        }

        gane = x <= 4 && y >= 3;
        for (int i = 0; i < 4 && gane; i++) {
            if (!casillas[x + i][y - i].getColorCasilla().equals(casillas[x][y].getColorCasilla())) {
                gane = false;
            }
        }

        return gane;
    }
}
