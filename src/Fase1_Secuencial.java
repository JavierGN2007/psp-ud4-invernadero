public class Fase1_Secuencial {
    public static void main(String[] args) {
        Temperatura t = new Temperatura("Temperatura", 2000, 10, 40);
        Humedad h = new Humedad("Humedad", 2000, 20, 90);
        Luz l = new Luz("Luz", 2000, 0, 1000);
        ProgramadorRiego r = new ProgramadorRiego(3);

        long tiempoInicio = System.currentTimeMillis();
        t.run();
        h.run();
        l.run();
        r.run();

        long tiempoFinal = System.currentTimeMillis();

        long tiempoTotal = tiempoFinal - tiempoInicio;

        System.out.println("Tiempo total: " + tiempoTotal + "ms");
        t.informe();
        h.informe();
        l.informe();
        r.informe();
    }
}
