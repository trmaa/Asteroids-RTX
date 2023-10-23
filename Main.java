public class Main {
    public static Canvas cvs = new Canvas();
    public static Ventana ventana = new Ventana();

    public static Estrella[] estrellas = new Estrella[10];
    public static Asteroid[] asteroids = new Asteroid[999];
    public static int asteroidn = 7;

    public static int delay = 0;

    public static void main(String[] args) {
        for (int i = 0; i < Main.estrellas.length; i++) {
            Main.estrellas[i] = new Estrella(
                    new vec2(Math.random() * Main.ventana.getWidth() - Main.ventana.getWidth() * 0.5,
                            Math.random() * Main.ventana.getHeight() - Main.ventana.getHeight() * 0.5),
                    Math.random() * 5 + 5);
        }
        for (int i = 0; i < asteroidn; i++) {
            Main.asteroids[i] = new Asteroid();
        }

        Audio.play("snd/song.wav");

        while (true) {
            Main.loop();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loop() {
        Main.cvs.repaint();

        Player.rb.move();

        for (int i = 0; i < Player.shots; i++)
            for (int j = 0; j < Bullet.step; j++)
                Player.blts[i].move();
        Player.getDamaged();
    }
}