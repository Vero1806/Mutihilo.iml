package alumnos;
//Verónica Prieto Prieto


import java.lang.Thread;
import java.util.Scanner;

public class CorredoresEj2 implements Runnable{

    private String nombreCorredor;
    private int metros;
    private volatile static int metrosarecorrer;
    private volatile static int contador = 0;
    static Scanner sc = new Scanner(System.in);

    public CorredoresEj2(String nombreCorredor, int metros){
        this.nombreCorredor = nombreCorredor;
        this.metros = metros;
        this.metrosarecorrer = 0;
    }

    public void correr() throws InterruptedException {

        while (metros<metrosarecorrer){
            // Incrementar distanciaRecorrida solo cuando el estado del hilo es RUNNABLE
            if (Thread.currentThread().getState() == Thread.State.RUNNABLE) {
                metros += 1;  // Incremento constante de 1 metro
                int distanciamostrada = 0;
                if (metros > 0 && metros%5==0){
                    distanciamostrada = metros;
                System.out.println("--->" + nombreCorredor + " ha recorrido " + distanciamostrada + " metros.");}

                if (metros == metrosarecorrer){
                    contador++;
                    System.out.println("------>" + nombreCorredor + " ha quedado "+ contador + "º lugar. <------");
                }
            }
        }
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

        System.out.println("Introduce los metros que tiene la carrera");
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

        hilo_pedro.join();
        hilo_marco.join();
        hilo_alfonso.join();
        hilo_paco.join();
        hilo_lorent.join();


        System.out.println("-> Fin de la Ejecución del pragrama Multihilo");
    }
}


