import javax.swing.*;

public class Ventana extends JFrame {
    public Ventana() {
        setTitle("Asteroids | trmaa");

        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon ico = new ImageIcon("icono.png");
        setIconImage(ico.getImage());

        add(Main.cvs);

        setVisible(true);
    }
}