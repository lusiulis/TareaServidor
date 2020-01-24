package tareaservidor.views;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import tareaservidor.Casilla;

public class VentanaJuego extends JFrame implements Observer{

    private Casilla casillas[][];
    
    public VentanaJuego() {
        super("Juego");
        configurar();
    }

    @Override
    public void update(Observable o, Object o1) {
        
    }

    private void configurar() {
        Ajustar(getContentPane());
        setResizable(true);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void Ajustar(Container c) {
        c.setLayout(new GridLayout(8,8));
        casillas = new Casilla[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Casilla casilla = new Casilla(j,i);
                casilla.setBackground(Color.GREEN);
                casilla.Actualizar();
                AgregarEscucha(casilla);
                casillas[i][j] = casilla;
                c.add(casilla);
            }
        }
    }
    
    private void AgregarEscucha(Casilla casilla){
        casilla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(!casilla.isClick()){
                    System.out.println("x: "+casilla.getPosx()+", y: "+casilla.getPosy());
                }
            }
        });
    }
    
    public void init(){
        setVisible(true);
    }
    
}
