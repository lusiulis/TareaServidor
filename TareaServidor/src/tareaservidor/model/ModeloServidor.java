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
    private Color colorPropio = Color.RED; //color que pinto mis casillas
    private Color colorOponente = Color.BLUE; //color que pinto las casillas de mi oponente
    private Boolean miTurno; //booleano para saber si puedo jugar o ocupo esperar a que el oponente juege

    public ModeloServidor() {
        miTurno = false;

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
            //pintarcasilla
            enviarMensaje(x + "," + y);
            //revisar si hay ganadores
            System.out.println("usted jugo");
        } else {
            System.out.println(" no es su turno");
            setChanged();
            notifyObservers("Debe esperar su turno...");
        }

    }

    public void pintarOponente(Integer x, Integer y) {
        //pintarcasilla
        //if(revisarGanadores){
        //miTurno=true;
        //}
        miTurno = true;
        System.out.println("el oponente jugo");
        setChanged();
        notifyObservers();

        setChanged();
        notifyObservers("Es tu turno...");
    }
}
