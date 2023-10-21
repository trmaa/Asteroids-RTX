public class Main {
    public static Canvas cvs = new Canvas();
    public static Ventana ventana = new Ventana();

    public static void main(String[] args) {
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
    }
}