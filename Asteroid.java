import java.awt.image.BufferedImage;

public class Asteroid {
    public vec2 position;
    public vec2 velocity;
    public int thick;
    public float angle;

    public BufferedImage sprite = ImageLoader.cargarImagen("sprites/Asteroide0.png");

    public Asteroid() {
        this.position = new vec2(
                Math.random() * Main.ventana.getWidth() * 2 - Main.ventana.getWidth(),
                Math.random() * Main.ventana.getHeight() * 2 - Main.ventana.getHeight());
        this.velocity = new vec2(Math.random() * 2 - 1, Math.random() * 2 - 1);
        this.thick = Utils.redondear(Math.random() * 128 + 64);
        this.sprite = Math.random() > 0.5 ? ImageLoader.cargarImagen("sprites/Asteroide0.png")
                : ImageLoader.cargarImagen("sprites/Asteroide1.png");
    }

    public void destroy() {
        if (this.thick < 32) {
            this.velocity = new vec2(0, 0);
            this.position = new vec2(9999, 9999);
        } else {
            this.thick -= Mcorrect.redondear(this.thick * 0.5);
        }
        Player.points++;
    }
}
