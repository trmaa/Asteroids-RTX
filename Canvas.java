import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    public MyKeyListener kl = new MyKeyListener();

    public Canvas() {
        this.addKeyListener(this.kl);
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.cls(g, new Color(0x000000));

        Color innerColor = new Color(0x00ffff);
        Color outerColor = new Color(0x000000);
        drawCircularGradient(g, Mcorrect.redondear(Player.position.x),
                Mcorrect.redondear(Player.position.y), 128,
                innerColor, outerColor);

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < Main.estrellas.length; i++) {
                drawCircularGradient(g,
                        Mcorrect.redondear(
                                Mcorrect.center(
                                        Main.estrellas[i].position, Player.position).x * 15 / Main.estrellas[i].distance
                                        + (j == 1 ? Main.ventana.getWidth() : j == 2 ? -Main.ventana.getWidth() : 0)),
                        Mcorrect.redondear(
                                Mcorrect.center(
                                        Main.estrellas[i].position, Player.position).y * 15 / Main.estrellas[i].distance
                                        + (j == 3 ? Main.ventana.getHeight() : j == 4 ? -Main.ventana.getHeight() : 0)),
                        Mcorrect.redondear((50 - Main.estrellas[i].distance) * 2), innerColor, outerColor);
                this.print(g,
                        Mcorrect.center(Main.estrellas[i].position, Player.position).x * 15
                                / Main.estrellas[i].distance
                                + (j == 1 ? Main.ventana.getWidth() : j == 2 ? -Main.ventana.getWidth() : 0),
                        Mcorrect.center(Main.estrellas[i].position, Player.position).y * 15
                                / Main.estrellas[i].distance
                                + (j == 3 ? Main.ventana.getHeight() : j == 4 ? -Main.ventana.getHeight() : 0),
                        20 - Main.estrellas[i].distance, 20 - Main.estrellas[i].distance, new Color(0xffffff));

            }
        }
        for (int i = 0; i < Main.asteroidn; i++) {
            this.print(g,
                    Mcorrect.center(Main.asteroids[i].position, Player.position).x,
                    Mcorrect.center(Main.asteroids[i].position, Player.position).y,
                    Main.asteroids[i].thick, Main.asteroids[i].thick, new Color(0xffff00));
        }
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < Player.shots; i++) {
                this.print(g,
                        Player.blts[i].position.x
                                + (j == 1 ? Main.ventana.getWidth() : j == 2 ? -Main.ventana.getWidth() : 0),
                        Player.blts[i].position.y
                                + (j == 3 ? Main.ventana.getHeight() : j == 4 ? -Main.ventana.getHeight() : 0),
                        5, 5, new Color(0xff0000));
            }
        }
        this.print(g, Player.position.x, Player.position.y, 10, 10, new Color(0xffffff));
        this.print(g,
                Player.position.x + Math.cos(Player.angle) * 30,
                Player.position.y + Math.sin(Player.angle) * 30,
                5, 5, new Color(0xff00ff));
        this.print(g, -Main.ventana.getWidth() * 0.5 + 100, Main.ventana.getHeight() * 0.5 - 132, Player.hp * 2, 32,
                new Color(0xffffff));
        // this.printext(g, Player.points + "", Main.ventana.getWidth() * 0.5 + 50,
        // -Main.ventana.getHeight() * 0.5 + 350,"arial", 1000, new Color(0xffffff));
    }

    public void cls(Graphics g, Color c) {
        g.setColor(c);
        g.fillRect(0, 0, Main.ventana.getWidth(), Main.ventana.getHeight());
    }

    public void print(Graphics g, double x, double y, double w, double h, Color c) {
        g.setColor(c);
        g.fillRect(Utils.redondear(Main.ventana.getWidth() * 0.5 + x),
                Utils.redondear(Main.ventana.getHeight() * 0.5 + y), Utils.redondear(w), Utils.redondear(h));
    }

    public void printext(Graphics g, String msg, double x, double y, String fnt, int d, Color c) {
        g.setColor(c);
        g.setFont(new Font(fnt, Font.BOLD, d));
        g.drawString(msg, Utils.redondear(Main.ventana.getWidth() * 0.5 + x),
                Utils.redondear(Main.ventana.getHeight() * 0.5 + y));
    }

    public void drawCircularGradient(Graphics g, int centerX, int centerY, int radius, Color innerColor,
            Color outerColor) {
        for (int r = radius; r > 0; r--) {
            float ratio = (float) (radius - r) / radius;
            int red = (int) (innerColor.getRed() + (1 - ratio) * (outerColor.getRed() - innerColor.getRed()));
            int green = (int) (innerColor.getGreen() + (1 - ratio) * (outerColor.getGreen() - innerColor.getGreen()));
            int blue = (int) (innerColor.getBlue() + (1 - ratio) * (outerColor.getBlue() - innerColor.getBlue()));
            Color gradientColor = new Color(red, green, blue, 5);
            g.setColor(gradientColor);
            g.fillOval(Mcorrect.redondear(Main.ventana.getWidth() * 0.5 + centerX) - r,
                    Mcorrect.redondear(Main.ventana.getHeight() * 0.5 + centerY) - r, 2 * r, 2 * r);
        }
    }
}