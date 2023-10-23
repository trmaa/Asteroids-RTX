import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

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

        Color innerColor = new Color(0x00aaaa);
        Color midColor = new Color(0x110033);
        Color outerColor = new Color(0x000000);

        this.gradiente(g, Mcorrect.redondear(Player.position.x),
                Mcorrect.redondear(Player.position.y), 500,
                innerColor, midColor, outerColor);
        this.printImg(g, Player.position.x - 16, Player.position.y - 16, 32, 32, Player.angle, Player.sprite);

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < Main.estrellas.length; i++) {
                this.print(g,
                        Mcorrect.center(Main.estrellas[i].position, Player.position).x * 15
                                / Main.estrellas[i].distance
                                + (j == 1 ? Main.ventana.getWidth() : j == 2 ? -Main.ventana.getWidth() : 0),
                        Mcorrect.center(Main.estrellas[i].position, Player.position).y * 15
                                / Main.estrellas[i].distance
                                + (j == 3 ? Main.ventana.getHeight() : j == 4 ? -Main.ventana.getHeight() : 0),
                        10 - Main.estrellas[i].distance, 10 - Main.estrellas[i].distance, new Color(0xffffff));

            }
        }
        for (int i = 0; i < Main.asteroidn; i++) {
            this.printImg(g,
                    Mcorrect.center(Main.asteroids[i].position, Player.position).x,
                    Mcorrect.center(Main.asteroids[i].position, Player.position).y,
                    Main.asteroids[i].thick, Main.asteroids[i].thick, Main.asteroids[i].angle,
                    Main.asteroids[i].sprite);
        }
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < Player.shots; i++) {
                this.print(g,
                        Player.blts[i].position.x
                                + (j == 1 ? Main.ventana.getWidth() : j == 2 ? -Main.ventana.getWidth() : 0),
                        Player.blts[i].position.y
                                + (j == 3 ? Main.ventana.getHeight() : j == 4 ? -Main.ventana.getHeight() : 0),
                        8, 8, new Color(0xffffff));
            }
        }
        this.print(g, -Main.ventana.getWidth() * 0.5 + 100, Main.ventana.getHeight() * 0.5 - 132, 200, 32,
                new Color(0, 255, 255, 50));
        this.print(g, -Main.ventana.getWidth() * 0.5 + 100, Main.ventana.getHeight() * 0.5 - 132, Player.hp * 2, 32,
                new Color(0xffffff));
        this.printText(g, Player.points + "", -Main.ventana.getWidth() * 0.5 + 100,
                -Main.ventana.getHeight() * 0.5 + 100, "Monospaced", 50, Color.WHITE);
    }

    public void printImg(Graphics g, double x, double y, double w, double h, double angle, BufferedImage image) {
        Graphics2D g2d = (Graphics2D) g;
        double centerX = Utils.redondear(Main.ventana.getWidth() * 0.5 + x + w / 2);
        double centerY = Utils.redondear(Main.ventana.getHeight() * 0.5 + y + h / 2);
        AffineTransform at = new AffineTransform();
        at.translate(centerX, centerY);
        at.rotate(angle - Math.PI * 1.5);
        at.translate(-centerX, -centerY);
        g2d.setTransform(at);
        g2d.drawImage(image, Utils.redondear(Main.ventana.getWidth() * 0.5 + x),
                Utils.redondear(Main.ventana.getHeight() * 0.5 + y), Utils.redondear(w), Utils.redondear(h), null);
        g2d.setTransform(new AffineTransform());
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

    public void printText(Graphics g, String msg, double x, double y, String fnt, int d, Color c) {
        if (msg != null && !msg.isEmpty()) {
            g.setColor(c);
            g.setFont(new Font(fnt, Font.BOLD, d));
            int drawX = Utils.redondear(Main.ventana.getWidth() * 0.5 + x);
            int drawY = Utils.redondear(Main.ventana.getHeight() * 0.5 + y);
            g.drawString(msg, drawX, drawY);
        }
    }

    public void gradiente(Graphics g, int centerX, int centerY, int radius, Color color1, Color color2,
            Color color3) {
        for (int r = radius; r > 0; r--) {
            float ratio = (float) (radius - r) / radius;
            Color gradientColor;

            if (ratio < 0.5f) {
                float subRatio = ratio / 0.5f;
                int red = (int) (color3.getRed() + subRatio * (color2.getRed() - color3.getRed()));
                int green = (int) (color3.getGreen() + subRatio * (color2.getGreen() - color3.getGreen()));
                int blue = (int) (color3.getBlue() + subRatio * (color2.getBlue() - color3.getBlue()));
                gradientColor = new Color(red, green, blue);
            } else {
                float subRatio = (ratio - 0.5f) / 0.5f;
                int red = (int) (color2.getRed() + subRatio * (color1.getRed() - color2.getRed()));
                int green = (int) (color2.getGreen() + subRatio * (color1.getGreen() - color2.getGreen()));
                int blue = (int) (color2.getBlue() + subRatio * (color1.getBlue() - color2.getBlue()));
                gradientColor = new Color(red, green, blue);
            }

            g.setColor(gradientColor);
            g.fillOval(Mcorrect.redondear(Main.ventana.getWidth() * 0.5 + centerX) - r,
                    Mcorrect.redondear(Main.ventana.getHeight() * 0.5 + centerY) - r, 2 * r, 2 * r);
        }
    }
}