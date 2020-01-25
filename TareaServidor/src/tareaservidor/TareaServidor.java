package tareaservidor;

import tareaservidor.control.GestorServidor;
import tareaservidor.views.VentanaJuegoServidor;

public class TareaServidor {

    public static void main(String[] args) {
        new VentanaJuegoServidor(new GestorServidor()).init();
    }
    
}
