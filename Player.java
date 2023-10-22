public class Player {
    public static vec2 position = new vec2(0, 0);
    public static float angle = 0;
    public static vec2 velocity = new vec2(0, 0);
    public static int maxSpeed = 50;
    public static float minSpeed = 0.1f;
    public static float speed = 2;

    public static RigidBody rb = new RigidBody();

    public static int hp = 100;
    public static int points = 0;

    public static Bullet[] blts = new Bullet[99999];
    public static int shots = 0;

    public static void shoot() {
        Player.blts[Player.shots] = new Bullet();
        Player.shots++;
    }
    public static void getDamaged(){
        if(Player.hp <= 0){
            Player.position = null;
        }
        for (int i = 0; i < Main.asteroidn; i++) {
            if (Player.position.x > Mcorrect.center(Main.asteroids[i].position, Player.position).x
                    &&
                    Player.position.x < Mcorrect.center(Main.asteroids[i].position, Player.position).x
                            + Main.asteroids[i].thick
                    &&
                    Player.position.y < Mcorrect.center(Main.asteroids[i].position, Player.position).y
                    &&
                    Player.position.y > Mcorrect.center(Main.asteroids[i].position, Player.position).y
                            - Main.asteroids[i].thick) {
                Player.hp--;
            }
        }
    }
}
