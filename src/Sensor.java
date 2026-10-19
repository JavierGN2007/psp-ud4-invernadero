import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Sensor implements Runnable {
    String nombre;
    String unidad;
    int periodo;
    int numero_lecturas;
    double min;
    double max;
    int numero_alertas;
    int suma;
    int minimo;
    int maximo;

    public Sensor (String nombre, String unidad, int periodo, int min, int max){
        this.nombre = nombre;
        this.unidad = unidad;
        this.periodo = periodo;
        this.numero_lecturas = 0;
        this.min = min;
        this.max = max;
        this.numero_alertas = numero_alertas;
        this.suma = suma;
        this.minimo = -999999;
        this.maximo = 999999;
    }

    public abstract double medir();

    public void run(){
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");

        while(!Thread.currentThread().isInterrupted()){
            try {
                double valor = medir();
                this.numero_lecturas++;

                String hora = LocalTime.now().format(formateador);

                String nombreHilo = Thread.currentThread().getName();

                String log = String.format("[" + hora + "] [Hilo: " + nombreHilo + "] Sensor " + this.nombre + " -> Valor: " + valor + this.unidad);

                if(valor < min || valor > max){
                    log += "ALERTA";
                }

                System.out.println(log);

                Thread.sleep(this.periodo);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void informe(){
        double media = 0;
        if(numero_lecturas > 0){
            media = suma / numero_lecturas;
        }

        System.out.println("ESTADISTICAS [" + nombre + "] -> Lecturas: " + numero_lecturas + " | Alertas: " + numero_alertas + " | Media: " + media + unidad + " | Mínimo: " + minimo + " | Máximo: " + maximo);
    }
}
