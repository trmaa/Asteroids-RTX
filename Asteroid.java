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
    public void destroy(){
        if(this.thick < 32){
            this.velocity = new vec2(0, 0);
            this.position = new vec2(9999, 9999);
        } else {
            this.thick -= Mcorrect.redondear(this.thick*0.5);
        }
        Player.points++;
    }
}
