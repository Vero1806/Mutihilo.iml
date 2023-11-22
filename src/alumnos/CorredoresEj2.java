package alumnos;
//Verónica Prieto Prieto


import java.lang.Thread;
import java.util.ArrayList;
import java.util.Scanner;

public class CorredoresEj2 implements Runnable{

    private String nombreCorredor;
    private int metros;
    private volatile static int metrosarecorrer;
    static Scanner sc = new Scanner(System.in);

    public CorredoresEj2(String nombreCorredor, int metros){
        this.nombreCorredor = nombreCorredor;
        this.metros = metros;
    }

    public void correr() throws InterruptedException {
        System.out.println("El corredor " + nombreCorredor + " ha empezado a correr");

        Thread.sleep(metrosarecorrer*1000);
     //   while (metros < metrosarecorrer){
     //       Thread.sleep(metrosarecorrer*1000);
     //       metros++;
     //   }


        System.out.println("El corredor " + nombreCorredor + " ha terminado a correr");
    }

    @Override
    public void run (){
        try {
            this.correr();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main (String[]args) throws InterruptedException{

        CorredoresEj2 pedro = new CorredoresEj2 ("Pedro",0);
        CorredoresEj2 marcos = new CorredoresEj2("Marcos",0);
        CorredoresEj2 alfonso = new CorredoresEj2("Alfonso", 0);
        CorredoresEj2 paco = new CorredoresEj2("Paco",0);
        CorredoresEj2 lorent = new CorredoresEj2("Lorent", 0);

        System.out.println("introduce los metros que tiene la carrera");
        metrosarecorrer = sc.nextInt();

        System.out.println("-> Inicio de la Ejecución del pragrama Multihilo");



        Thread hilo_pedro = new Thread(pedro);
        Thread hilo_marco = new Thread(marcos);
        Thread hilo_alfonso = new Thread(alfonso);
        Thread hilo_paco = new Thread(paco);
        Thread hilo_lorent = new Thread(lorent);

        hilo_pedro.start();
        hilo_marco.start();
        hilo_alfonso.start();
        hilo_paco.start();
        hilo_lorent.start();

        ArrayList<Thread.State> Estados = new ArrayList<>();
            Estados.add(Thread.State.RUNNABLE);

        while (hilo_pedro.isAlive() || (hilo_marco.isAlive()) || hilo_alfonso.isAlive() || hilo_paco.isAlive() || (hilo_lorent.isAlive())){

            Thread.State estadoPedro = hilo_pedro.getState();
            if (Estados.contains(estadoPedro)){
                pedro.metros++;
                //System.out.println("Pedro a recorrido " + pedro.metros);
            }
            Thread.State estadoMarco = hilo_marco.getState();
            if (Estados.contains(estadoMarco)){
                paco.metros++;
            }
            Thread.State estadoAlfonso = hilo_alfonso.getState();
            if (Estados.contains(estadoAlfonso)){
                alfonso.metros++;
            }
            Thread.State estadoPaco = hilo_paco.getState();
            if (Estados.contains(estadoPaco)){
                paco.metros++;
            }
            Thread.State estadoLorent = hilo_lorent.getState();
            if (Estados.contains(estadoLorent)){
                lorent.metros++;
            }
        }

        System.out.println("-> Fin de la Ejecución del pragrama Multihilo");
    }

}
