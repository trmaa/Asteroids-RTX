public class Player {
    public static vec2 position = new vec2(0, 0);
    public static float angle = 0;
    public static vec2 velocity = new vec2(0, 0);
    public static int maxSpeed = 50;
    public static float minSpeed = 0.1f;
    public static float speed = 2;

    public static int municion;

    public static RigidBody rb = new RigidBody();
}
