public class Bullet {
    public vec2 position = new vec2(Player.position.x, Player.position.y);
    public float angle = Player.angle + 0;

    public static int speed = 10;
    public static int step = 10;

    public Bullet() {
        this.position = new vec2(Player.position.x, Player.position.y);
        this.angle = Player.angle + 0;
    }

    public void move() {
        this.position.add(
                new vec2(Bullet.speed * Math.cos(this.angle),
                        Bullet.speed * Math.sin(this.angle)));
    }
}