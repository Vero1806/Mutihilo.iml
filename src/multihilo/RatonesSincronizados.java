package multihilo;

//Consideraciones:
//1. Probar a usar synchronized en los métodos de la clase

public class RatonesSincronizados implements Runnable {
    String nombre;
    long tiempoAlimentacion;

    Object candado1 = new Object();

    //Constructor
    public RatonesSincronizados(String nombre, int tiempoAlimentacion) {
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }

    public synchronized void comer() {
        try {
            System.out.println("  |-> El ratón " + nombre + ", hilo " + Thread.currentThread().getName() + ", ha comenzado a alimentarse.");
            Thread.sleep(tiempoAlimentacion * 1000);
            System.out.println("  |<- El ratón " + nombre + ", hilo " + Thread.currentThread().getName() + ", ha terminado de alimentarse.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void beber() {
        try {
            System.out.println("  |-> El ratón " + nombre + ", hilo " + Thread.currentThread().getName() + ", ha comenzado a hidratarse.");
            Thread.sleep(tiempoAlimentacion * 1000);
            System.out.println("  |<- El ratón " + nombre + ", hilo " + Thread.currentThread().getName() + ", ha terminado de hidratarse.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        synchronized (candado1) {
            this.comer();
            this.beber();
        }
    }

    public static void main(String[] args) {
        RatonesSincronizados mickey = new RatonesSincronizados("Mickey", 2);
        RatonesSincronizados jerry = new RatonesSincronizados("Jerry", 2);

        long tiempoInicio, tiempoFin;
        System.out.println("-> Inicio de la Ejecución del programa MULTIHILO");
        tiempoInicio = System.nanoTime();

//      Se crean dos hilos sobre el mismo objeto
        Thread hilo_mickey1 = new Thread(mickey);
        hilo_mickey1.setName("Mickey 1");
        Thread hilo_mickey2 = new Thread(mickey);
        hilo_mickey2.setName("Mickey 2");

//      Se crea un hilo del otro objeto
        Thread hilo_jerry1 = new Thread(jerry);
        hilo_jerry1.setName("Jerry");

        hilo_mickey1.start();
        hilo_mickey2.start();
        hilo_jerry1.start();

        try {
            hilo_mickey1.join();
            hilo_mickey2.join();
            hilo_jerry1.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        tiempoFin = System.nanoTime();
        System.out.println("-> Fin de la Ejecución del programa MULTIHILO. Tiempo transcurrido: " + (int) ((tiempoFin - tiempoInicio) / 1e9) + " segundos");
    }
}