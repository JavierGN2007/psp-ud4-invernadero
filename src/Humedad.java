import java.util.concurrent.ThreadLocalRandom;

public class Humedad extends Sensor {
    public Humedad(String nombre, int periodo, int min, int max){
        super(nombre, "%", periodo, min, max);
    }

    @Override
    public double medir() {
        return ThreadLocalRandom.current().nextDouble(20.0, 90.0);
    }
}
