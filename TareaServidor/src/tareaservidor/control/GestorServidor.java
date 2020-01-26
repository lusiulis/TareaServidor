/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaservidor.control;

import java.util.Observer;
import tareaservidor.model.ModeloServidor;
import tareaservidor.views.Casilla;

/**
 *
 * @author Kevin
 */
public class GestorServidor {

    ModeloServidor modelo;

    public GestorServidor() {
        modelo = new ModeloServidor();
        modelo.empezarAEscuchar(this);
    }

    public void registrar(Observer observador) {
        modelo.addObserver(observador);
    }

    public void cerrarAplicacion() {
        modelo.deleteObservers();
        System.exit(0);
    }

    public void suprimir(Observer observador) {
        modelo.deleteObserver(observador);
        if (modelo.countObservers() == 0) {
            System.exit(0);
        }
    }

    public void pintar(Integer x, Integer y) {
        modelo.pintar(x, y);
    }

    public void pintarOponente(Integer x, Integer y) {
        modelo.pintarOponente(x, y);
    }
    
    public void setCasilla(int x,int y,Casilla m){
        modelo.setCasilla(x, y, m);
    }

    public Casilla[][] getCasillas(){
        return modelo.getCasillas();
    }
}
