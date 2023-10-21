public class RigidBody {
    public void move() {
        // angle
        if (MyKeyListener.LEFT)
            Player.angle -= 0.3;
        if (MyKeyListener.RIGHT)
            Player.angle += 0.3;

        // force
        if (Player.velocity.magnitude() < Player.maxSpeed)
            if (MyKeyListener.UP)
                Player.velocity.add(
                        new vec2(Math.cos(Player.angle) * Player.speed,
                                Math.sin(Player.angle) * Player.speed));

        // friction
        if (Player.velocity.magnitude() < Player.minSpeed)
            Player.velocity = new vec2(0, 0);

        Player.velocity.multiply(new vec2(0.97, 0.97));

        // goto
        Player.position.add(Player.velocity);
    }
}