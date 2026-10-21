public class Fase4_Vigilante {
    public static void main(String[] args) throws InterruptedException {
        Temperatura t4 = new Temperatura("Temperatura", 2000, 10, 40);
        Humedad h4 = new Humedad("Humedad", 2000, 20, 90);
        Luz l4 = new Luz("Luz", 2000, 0, 1000);
        ProgramadorRiego r4 = new ProgramadorRiego(3);

        Thread hiloTemperatura = new Thread(t4);
        Thread hiloHumedad = new Thread(h4);
        Thread hiloLuz = new Thread(l4);

        Runnable tarea = () -> {
            while(true){
                System.out.println(hiloTemperatura.getState());
                System.out.println(hiloHumedad.getState());

                System.out.println(hiloLuz.getState());
                System.out.println(r4.getState());


                try{
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        };

        Thread vigilante = new Thread(tarea);

        vigilante.setDaemon(true);

        System.out.println(hiloTemperatura.getState() + "\n");
        System.out.println(hiloHumedad.getState() + "\n");
        System.out.println(hiloLuz.getState() + "\n");
        System.out.println(r4.getState() + "\n");

        hiloTemperatura.start();
        hiloHumedad.start();
        hiloLuz.start();
        r4.start();
        vigilante.start();

        hiloTemperatura.join();
        hiloHumedad.join();
        hiloLuz.join();
        r4.join();

        System.out.println(hiloTemperatura.getState() + "\n");
        System.out.println(hiloHumedad.getState() + "\n");
        System.out.println(hiloLuz.getState() + "\n");
        System.out.println(r4.getState() + "\n");


    }
}
