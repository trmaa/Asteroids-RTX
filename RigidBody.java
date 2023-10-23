public class RigidBody {
    public void move() {
        // angle
        if (MyKeyListener.LEFT)
            Player.angle -= 0.3;
        if (MyKeyListener.RIGHT)
            Player.angle += 0.3;

        // shoot (momentanely)
        Main.delay += 1;
        if (Main.delay >= 3) {
            if (MyKeyListener.E) {
                Player.shoot();
                Audio.play("snd/laser.wav");
                Main.delay = 0;
            }
        }
        for (int i = 0; i < Main.asteroidn; i++) {
            Main.asteroids[i].angle += Main.asteroids[i].velocity.x * 0.05;
            Main.asteroids[i].position.add(Main.asteroids[i].velocity);

            if (Main.asteroids[i].position.x > Main.ventana.getWidth() * 0.5)
                Main.asteroids[i].position.x = -Main.ventana.getWidth() * 0.5;
            if (Main.asteroids[i].position.y > Main.ventana.getHeight() * 0.5)
                Main.asteroids[i].position.y = -Main.ventana.getHeight() * 0.5;
            if (Main.asteroids[i].position.x < -Main.ventana.getWidth() * 0.5)
                Main.asteroids[i].position.x = Main.ventana.getWidth() * 0.5;
            if (Main.asteroids[i].position.y < -Main.ventana.getHeight() * 0.5)
                Main.asteroids[i].position.y = Main.ventana.getHeight() * 0.5;
        }

        // force (momentanely sprite)
        if (Player.velocity.magnitude() < Player.maxSpeed)
            if (MyKeyListener.UP) {
                Player.velocity.add(
                        new vec2(Math.cos(Player.angle) * Player.speed,
                                Math.sin(Player.angle) * Player.speed));
                Player.sprite = ImageLoader.cargarImagen("sprites/Player1.png");
            } else
                Player.sprite = ImageLoader.cargarImagen("sprites/Player0.png");

        // friction
        if (Player.velocity.magnitude() < Player.minSpeed)
            Player.velocity = new vec2(0, 0);

        Player.velocity.multiply(new vec2(0.99, 0.99));

        // goto
        Player.position.add(Player.velocity);
        if (Player.position.x > Main.ventana.getWidth() * 0.5)
            Player.position.x = -Main.ventana.getWidth() * 0.5;
        if (Player.position.y > Main.ventana.getHeight() * 0.5)
            Player.position.y = -Main.ventana.getHeight() * 0.5;
        if (Player.position.x < -Main.ventana.getWidth() * 0.5)
            Player.position.x = Main.ventana.getWidth() * 0.5;
        if (Player.position.y < -Main.ventana.getHeight() * 0.5)
            Player.position.y = Main.ventana.getHeight() * 0.5;
    }
}
