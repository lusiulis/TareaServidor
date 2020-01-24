/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4server.model;

import connect4server.util.Conexion;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Observable;

/**
 *
 * @author Kevin
 */
public class ModeloConnect4 extends Observable {

    private Integer puerto;
    public static Boolean continuar = true;
    public static ServerSocket serverSocket;
    private Conexion conexion;

    private void empezarAEscuchar() throws IOException {
        serverSocket = new ServerSocket(puerto);
        continuar = true;

        //esperar conexion
        conexion = new Conexion(null);
        conexion.start();
    }
    
    //metodo que hay que llamar cuando se cierre el programa para que no quede abierto un hilo
    public void detenerEscuchar() throws IOException {
        serverSocket.close();
    }
}
