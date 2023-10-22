public class Bullet {
    public vec2 position = new vec2(Player.position.x, Player.position.y);
    public float angle = Player.angle + 0;

    public int speed = 1;
    public static int step = 100;

    public Bullet() {
        this.position = new vec2(Player.position.x, Player.position.y);
        this.angle = Player.angle + 0;
    }

    public void move() {
        this.position.add(
                new vec2(this.speed * Math.cos(this.angle),
                        this.speed * Math.sin(this.angle)));
        for (int i = 0; i < Main.asteroidn; i++) {
            if (this.position.x > Mcorrect.center(Main.asteroids[i].position, Player.position).x
                    &&
                    this.position.x < Mcorrect.center(Main.asteroids[i].position, Player.position).x
                            + Main.asteroids[i].thick
                    &&
                    this.position.y > Mcorrect.center(Main.asteroids[i].position, Player.position).y
                    &&
                    this.position.y < Mcorrect.center(Main.asteroids[i].position, Player.position).y
                            + Main.asteroids[i].thick) {
                this.speed = 0;
                this.position = new vec2(1000, 1000);
                Main.asteroids[i].destroy();
            }
        }
    }
}