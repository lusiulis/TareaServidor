package tareaservidor.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Casilla extends JPanel {

    private int posx;
    private int posy;
    private boolean click;
    private Color color;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;    
        g2.setColor(color);
        g2.fillOval(10, 10 , 80, 80);
    }

    public Casilla() {
        setBackground(new Color(111, 88, 82));
        color = Color.WHITE;
        click = false;
        posx = 0;
        posy = 0;
    }
  
    public Casilla(int x, int y,Color color) {
        setBackground(new Color(111, 88, 82));
        click = false;
        this.color = color;
        posx = x;
        posy = y;
    }

    public void Actualizar() {
        this.setSize(100, 100);
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
    
    public Color getColorCasilla(){
        return color;
    }
}
