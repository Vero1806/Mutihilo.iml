package alumnos;
//Verónica Prieto Prieto


import java.lang.Thread;
import java.util.ArrayList;



public class RatonMultihiloRunnableEntrega implements Runnable {

    private String nombre;
    private long tiempoAlimentacion;

    public RatonMultihiloRunnableEntrega(String nombre, int tiempoAlimentacion){
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }

    public void comer() {
        try {
            System.out.println(" | -> El ratón " + nombre + " ha comenzado a alimentarse.");
            Thread.sleep(tiempoAlimentacion * 1000);
            System.out.println(" | <- El ratón " + nombre + " ha terminado de alimentarse");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run (){
        this.comer();
    }

    public static void main (String[] args) throws InterruptedException {
        RatonMultihiloRunnableEntrega mickey = new RatonMultihiloRunnableEntrega("Mickey", 1);
        RatonMultihiloRunnableEntrega jerry = new RatonMultihiloRunnableEntrega("Jerry", 1);
        RatonMultihiloRunnableEntrega perez = new RatonMultihiloRunnableEntrega("Peréz", 1);
        RatonMultihiloRunnableEntrega mickey1 = new RatonMultihiloRunnableEntrega("Mickey1", 1);
        RatonMultihiloRunnableEntrega jerry1 = new RatonMultihiloRunnableEntrega("Jerry1", 1);
        RatonMultihiloRunnableEntrega perez1 = new RatonMultihiloRunnableEntrega("Peréz1", 1);
        RatonMultihiloRunnableEntrega mickey2 = new RatonMultihiloRunnableEntrega("Mickey2", 1);
        RatonMultihiloRunnableEntrega jerry2= new RatonMultihiloRunnableEntrega("Jerry2", 1);
        RatonMultihiloRunnableEntrega perez2 = new RatonMultihiloRunnableEntrega("Peréz2", 1);
        long tiempoInicio, tiempoFin;
        System.out.println("-> Inicio de la Ejecución del pragrama Multihilo");

        tiempoInicio = System.currentTimeMillis();

        Thread hilo_mickey = new Thread(mickey);
        Thread hilo_jerry = new Thread(jerry);
        Thread hilo_perez = new Thread(perez);
        Thread hilo_mickey1 = new Thread(mickey1);
        Thread hilo_jerry1 = new Thread(jerry1);
        Thread hilo_perez1 = new Thread(perez1);
        Thread hilo_mickey2 = new Thread(mickey2);
        Thread hilo_jerry2 = new Thread(jerry2);
        Thread hilo_perez2 = new Thread(perez2);

        hilo_mickey.start();
        hilo_jerry.start();
        hilo_perez.start();
        hilo_mickey1.start();
        hilo_jerry1.start();
        hilo_perez1.start();
        hilo_mickey2.start();
        hilo_jerry2.start();
        hilo_perez2.start();

        ArrayList<Thread.State> Estados = new ArrayList<>();

        while (hilo_mickey.isAlive() || (hilo_jerry.isAlive()) || hilo_perez.isAlive() || hilo_perez1.isAlive() || (hilo_jerry1.isAlive()) || hilo_mickey1.isAlive() || hilo_perez2.isAlive() || (hilo_jerry2.isAlive()) || hilo_mickey2.isAlive()) {

            Thread.State estadoActual = hilo_perez.getState();
            if (!Estados.contains(estadoActual)) {
                Estados.add(estadoActual);
            }

        }
        System.out.println("");
        System.out.println("El ratón Pérez ha pasado por los estados: " + Estados);

        tiempoFin = System.currentTimeMillis();

        System.out.println("-> Fin de la Ejecución. Tiempo transcurrido: " + (int) (((tiempoFin - tiempoInicio) / 1000)) + " segundos");
    }
}