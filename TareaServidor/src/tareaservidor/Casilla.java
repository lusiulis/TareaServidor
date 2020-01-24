package tareaservidor;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Casilla extends JPanel {

    private int posx;
    private int posy;
    private boolean click;

    public Casilla() {
        click = false;
        posx = 0;
        posy = 0;
    }

    public void Actualizar() {
        this.setBorder(BorderFactory.createLineBorder(Color.white));
        this.setSize(25, 25);
    }

    public Casilla(int x, int y) {
        posx = x;
        posy = y;
    }

    public void Cambiar(Color color){
        click = true;
        this.setBackground(color);
    }
    
    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }
    
    
}
