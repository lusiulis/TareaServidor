/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4server.util;

import connect4server.model.ModeloConnect4;
import static connect4server.model.ModeloConnect4.serverSocket;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.control.Label;

/**
 *
 * @author Kevin F
 */
public class Conexion extends Thread {

    private Socket socket;

    public Conexion(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        if (socket == null) {
            try {
                while (ModeloConnect4.continuar) {
                    System.out.println("*******************************\nSe Inicio a escuchar\n*******************************");
                    Conexion conexion;
                    conexion = new Conexion(serverSocket.accept());
                    conexion.start();
                }
                serverSocket.close();
            } catch (SocketException ex) {
                //Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException so) {

            }

        } else {
            try {
                System.out.println("se conecto");
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                Object objetoRecibido = ois.readObject();
                //aqui deberia enviarle al gestor lo recibido para que el gestor decida que hacer, lo mas probable, pintar en el modelo
                
                
                //if (trama.getError() == false) {

                //esta porcion es para devolver un mensaje al cliente
                PrintWriter pr = new PrintWriter(socket.getOutputStream());
                pr.write("MENSAJE A DEVOLVER");
                pr.flush();
                //fin de porcion de devolver mensaje

            } catch (Exception ex) {
            }
        }

    }

}
