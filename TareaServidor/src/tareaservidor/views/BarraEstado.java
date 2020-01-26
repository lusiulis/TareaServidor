package tareaservidor.views;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class BarraEstado extends JPanel {

    public BarraEstado() {
        super();
        configurar();
    }

    private void configurar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(etqEstado = new JLabel());
    }

    public void init() {
        mostrarMensaje();
    }

    public void mostrarMensaje(String msj) {
        etqEstado.setText(String.format("%s ", msj));
    }

    public void mostrarMensaje() {
        mostrarMensaje("");
    }

    private JLabel etqEstado;
}
