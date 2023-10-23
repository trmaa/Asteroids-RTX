import javax.sound.sampled.*;

public class Utils {
    public static int redondear(double n) {
        int res = 0;
        while (n >= 1 || n <= -1) {
            n += n > 0 ? -1 : 1;
            res += n < 0 ? -1 : 1;
        }
        res += n > 0.5 ? 1 : 0;
        return res;
    }
}

class vec3 {
    double x;
    double y;
    double z;

    vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    double magnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    void add(vec3 vector) {
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
    }

    void multiply(vec3 vector) {
        this.x *= vector.x;
        this.y *= vector.y;
        this.z *= vector.z;
    }
}

class vec2 {
    double x;
    double y;

    vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double magnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    void add(vec2 vector) {
        this.x += vector.x;
        this.y += vector.y;
    }

    void multiply(vec2 vector) {
        this.x *= vector.x;
        this.y *= vector.y;
    }
}

class Mcorrect {
    static int redondear(double n) {
        int res = 0;
        while (n >= 1 || n <= -1) {
            n += n > 0 ? -1 : 1;
            res += n < 0 ? -1 : 1;
        }
        res += n > 0.5 ? 1 : 0;
        return res;
    }

    static vec2 center(vec2 point, vec2 o) {
        return new vec2(point.x - o.x, point.y - o.y);
    }
}

class Audio {
    static void play(String src) {
        Thread audioThread = new Thread(() -> {
            try {
                // Obtén un Clip de audio
                Clip clip = AudioSystem.getClip();

                // Carga el archivo de audio
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                        Audio.class.getResourceAsStream(src));

                // Abre el Clip y carga los datos del audio
                clip.open(audioInputStream);

                // Reproduce el audio
                clip.start();

                // Espera a que termine la reproducción
                Thread.sleep(clip.getMicrosecondLength() / 1000);

                // Cierra el Clip y libera los recursos
                clip.close();
                audioInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Inicia el subproceso de audio
        audioThread.start();
    }
}