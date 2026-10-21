public class ProgramadorRiego extends Thread {
    int ciclos;
    double litrosTotales;

    public ProgramadorRiego(int ciclos){
        super("riego");
        this.ciclos = ciclos;
        this.litrosTotales = 0;
    }

    public void run(){
        for(int i = this.ciclos; i>=1; i--){
            this.ciclos = i;
            this.litrosTotales = this.litrosTotales + 2.5;
            System.out.println("[" + getName() + "] Ciclo " + i + ": ABIERTA (Suma 2.5L)");

                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    System.out.println("El riego fue interrumpido.");
                    break;
                }

            System.out.println("[" + getName() + "] Ciclo " + i + ": Cerrada");

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println("El riego fue interrumpido.");
                break;
            }


        }
    }

    public void informe(){
        System.out.println("Ciclos: " + this.ciclos + " | Litros totales: " + this.litrosTotales + "L");
    }
}
