public class Fase3_Experimentos {
    public static void main(String[] args) throws InterruptedException {
        //======EXPERIMENTO 1======
        Temperatura t3 = new Temperatura("Temperatura", 2000, 10, 40);

        Thread hiloTemperatura2 = new Thread(t3);

        hiloTemperatura2.start();

        t3.informe();

        try{
            hiloTemperatura2.join();
        }catch (InterruptedException e) {
        throw new RuntimeException(e);
    }

        t3.informe();

        System.out.println("=========================================");

        //======EXPERIMENTO 2======
        ProgramadorRiego r3 = new ProgramadorRiego(3);

        long tiempoInicio = System.currentTimeMillis();

        r3.run();

        long tiempoFinal = System.currentTimeMillis();

        long tiempoTotal = tiempoFinal - tiempoInicio;

        System.out.println("Tiempo total: " + tiempoTotal);
        System.out.println(r3.getState());

        System.out.println("=========================================");

        //======EXPERIMENTO 3======
        ProgramadorRiego r3s = new ProgramadorRiego(5);

        r3s.start();

        r3s.join();

        try{
            r3s.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
