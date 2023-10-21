public class Asteroid {
    public vec2 position;
    public vec2 velocity;
    public int thick;

    public Asteroid() {
        this.position = new vec2(
                Math.random() * Main.ventana.getWidth() * 2 - Main.ventana.getWidth(),
                Math.random() * Main.ventana.getHeight() * 2 - Main.ventana.getHeight());
        this.velocity = new vec2(Math.random() * 20 - 10, Math.random() * 20 - 10);
        this.thick = Utils.redondear(Math.random() * 64 + 32);
    }
}
