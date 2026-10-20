public class Fase2_Concurrente {
    public static void main(String[] args){
        Temperatura t2 = new Temperatura("Temperatura", 2000, 10, 40);
        Humedad h2 = new Humedad("Humedad", 2000, 20, 90);
        Luz l2 = new Luz("Luz", 2000, 0, 1000);
        ProgramadorRiego r2 = new ProgramadorRiego();

        Thread hiloTemperatura = new Thread(t2);
        Thread hiloHumedad = new Thread(h2);
        Thread hiloLuz = new Thread(l2);

        long tiempoInicio = System.currentTimeMillis();

        hiloTemperatura.start();
        hiloHumedad.start();
        hiloLuz.start();
        r2.start();

        try{
            hiloTemperatura.join();
            hiloHumedad.join();
            hiloLuz.join();
            r2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long tiempoFinal = System.currentTimeMillis();

        long tiempoTotal = tiempoFinal - tiempoInicio;

        System.out.println("Tiempo total: " + tiempoTotal + "ms");
        t2.informe();
        h2.informe();
        l2.informe();
        r2.informe();

        //No sale siempre en el mismo orden y, en comparación con el secuencial, es mucho más rápido
    }
}
