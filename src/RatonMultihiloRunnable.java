import java.lang.Thread;
import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        //Thread.State Estado = Thread.currentThread().getState();
        //System.out.println(Thread.currentThread()); //Me dice el nombre del hilo que ha terminado del 0 al 2
        //System.out.println("state = " + Estado); //Estado del hilo. Solo pone Runnable
    }

    public static void main (String[] args) throws InterruptedException {
        RatonMultihiloRunnable mickey = new RatonMultihiloRunnable ("Mickey",6);
        RatonMultihiloRunnable jerry = new RatonMultihiloRunnable("Jerry",5);
        RatonMultihiloRunnable perez = new RatonMultihiloRunnable("Peréz",2);
        long tiempoInicio,tiempoFin;
        System.out.println("-> Inicio de la Ejecución del pragrama Multihilo");

        tiempoInicio=System.currentTimeMillis();

        Thread hilo_mickey = new Thread(mickey); // conversión al hilo al invocar Thread como objeto
        Thread hilo_jerry = new Thread(jerry);
        Thread hilo_perez = new Thread(perez);

       System.out.println(hilo_perez.getState());
        hilo_mickey.start();
        hilo_jerry.start();
        hilo_perez.start();




        while (hilo_perez.isAlive()|| (hilo_jerry.isAlive()) || hilo_perez.isAlive()) {
            ArrayList <Enum> Estados;
            //Estados.addElement(hilo_perez.getState());
            System.out.println();


            //Thread.sleep(1000);

            }
        System.out.println(hilo_perez.getState());

        //try {
        //   mickey.join();
        //   jerry.join();
        //   perez.join();
        //}catch (InterruptedException e){
        //   e.printStackTrace();
        //}

        tiempoFin = System.currentTimeMillis();

        //if(mickey.tiempoAlimentacion < jerry.tiempoAlimentacion){
        //    tiempoFin = jerry.tiempoAlimentacion*1000;
        //} else if (jerry.tiempoAlimentacion < perez.tiempoAlimentacion) {
        //    tiempoFin = perez.tiempoAlimentacion*1000;
        //}else tiempoFin = mickey.tiempoAlimentacion*1000;


        System.out.println("-> Fin de la Ejecución. Tiempo transcurrido: " +(int)(((tiempoFin-tiempoInicio)/1000)) + " segundos"); // (int)(((tiempoFin-tiempoInicio)/1e9)) 1 elevado a 9 para convertir los nanos segundos a segundos
    }
}
