import java.util.concurrent.ThreadLocalRandom;

public class Temperatura extends Sensor{

    public Temperatura(String nombre, int periodo, int min, int max){
        super(nombre, "ºC", periodo, min, max);
    }

    @Override
    public double medir() {
        return ThreadLocalRandom.current().nextDouble(15.0, 40.0);
    }
}
