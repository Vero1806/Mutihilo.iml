import java.lang.Thread;
import java.util.ArrayList;
import static java.lang.Thread.State.*;


//Verónica Prieto Prieto

public class RatonMultihiloRunnable implements Runnable {

    private String nombre;
    private long tiempoAlimentacion;

    public RatonMultihiloRunnable (String nombre, int tiempoAlimentacion){
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;

    }

    public void comer(){
        try{
            System.out.println(" | -> El ratón " + nombre + " ha comenzado a alimentarse.");
            Thread.sleep(tiempoAlimentacion*1000); // sleep funciona con mili segundos, los convertimos a segundos al multiplicarlo por mil
            System.out.println( " | <- El ratón " + nombre + " ha terminado de alimentarse");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run (){
        this.comer();

    }

    public static void main (String[] args) throws InterruptedException {
        RatonMultihiloRunnable mickey = new RatonMultihiloRunnable ("Mickey",6);
        RatonMultihiloRunnable jerry = new RatonMultihiloRunnable("Jerry",5);
        RatonMultihiloRunnable perez = new RatonMultihiloRunnable("Peréz",9);
        long tiempoInicio,tiempoFin;
        System.out.println("-> Inicio de la Ejecución del pragrama Multihilo");

        tiempoInicio=System.currentTimeMillis();

        Thread hilo_mickey = new Thread(mickey); // conversión al hilo al invocar Thread como objeto
        Thread hilo_jerry = new Thread(jerry);
        Thread hilo_perez = new Thread(perez);

        //System.out.println("Pérez = " + hilo_perez.getState()); //aquí esta new
        hilo_mickey.start();
        hilo_jerry.start();
        hilo_perez.start();


        ArrayList<Thread.State> Estados = new ArrayList<>(); //Array de estados, variable Enum
//        Estados.add(NEW);
//        Estados.add(RUNNABLE);
//        Estados.add(BLOCKED);
//        Estados.add(WAITING);
//        Estados.add(TIMED_WAITING);
//        Estados.add(TERMINATED);

        // Thread.State estadoAnterior = NEW; //En el bucle siemple va empezar en runnable por lo que el estado anterior siempre es new

        while (hilo_perez.isAlive()|| (hilo_jerry.isAlive()) || hilo_perez.isAlive()) {

            Thread.State estadoActual = hilo_perez.getState();
            if (!Estados.contains(estadoActual) ) {
                Estados.add(estadoActual);

            }

        }
        System.out.println("");
        System.out.println("El ratón Pérez a pasado por los estados: " + Estados);
        //System.out.println("Pérez = " + hilo_perez.getState()); //Terminated siempre va a estar fuera del bucle, no se imprimirá aunque este como variable


        tiempoFin = System.currentTimeMillis();


        System.out.println("-> Fin de la Ejecución. Tiempo transcurrido: " +(int)(((tiempoFin-tiempoInicio)/1000)) + " segundos"); // (int)(((tiempoFin-tiempoInicio)/1e9)) 1 elevado a 9 para convertir los nanos segundos a segundos
    }
}