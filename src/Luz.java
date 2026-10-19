import java.util.concurrent.ThreadLocalRandom;

public class Luz extends Sensor {
    public Luz(String nombre, int periodo, int min, int max){
        super(nombre, "lux", periodo, min, max);
    }

    @Override
    public double medir() {
        return ThreadLocalRandom.current().nextDouble(0.0, 1000.0);
    }
}
