/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaservidor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import tareaservidor.control.GestorServidor;
import tareaservidor.model.ModeloServidor;
import static tareaservidor.model.ModeloServidor.primeraConexion;
import static tareaservidor.model.ModeloServidor.serverSocket;

/**
 *
 * @author Kevin F
 */
public class ConexionServidor extends Thread {

    private Socket socket;
    private String IPJUGADORCONTRARIO;
    private GestorServidor gestor;

    public ConexionServidor(Socket socket, GestorServidor gestor) {
        this.socket = socket;
        this.gestor = gestor;
    }

    @Override
    public void run() {
        if (socket == null) {
            try {
                while (ModeloServidor.continuar) {
                    ConexionServidor conexion;
                    conexion = new ConexionServidor(serverSocket.accept(), gestor);
                    conexion.start();
                }
                serverSocket.close();
            } catch (Exception ex) {
            }

        } else {
            try {
                //utiliza un buffered reader para leer lo que llego del socket
                String mensajeRecibido = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
                if (primeraConexion) {
                    establecerJugadorContrario(mensajeRecibido);
                }
                String[] posicion = mensajeRecibido.split(",");
                gestor.pintarOponente(Integer.valueOf(posicion[0]), Integer.valueOf(posicion[1]));

            } catch (Exception ex) {
            }
        }

    }

    private void establecerJugadorContrario(String ipJugContrario) {
        IPJUGADORCONTRARIO = ipJugContrario;
        primeraConexion = false;

        enviarMensaje("CONEXION ESTABLECIDA");
    }

    public void enviarMensaje(String aEnviar) {
        try {
            Socket socket = new Socket(IPJUGADORCONTRARIO, ModeloServidor.puertoOponente);
            OutputStream os;
            os = socket.getOutputStream();
            os.write(aEnviar.getBytes());
            os.flush();
            socket.close();
        } catch (IOException ex) {
        }

    }

    static {
        System.out.println("*******************************\n\tServidor inicio a escuchar \n*******************************");

    }
}
