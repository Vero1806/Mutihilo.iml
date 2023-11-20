package alumnos;
//Verónica Prieto Prieto


import java.lang.Thread;
import java.util.Scanner;

public class CorredoresEj2 implements Runnable{

    private String nombreCorredor;
    private int metros;
    private static int metrosrecorridos;
    static Scanner sc = new Scanner(System.in);

    public CorredoresEj2(String nombreCorredor, int metros){
        this.nombreCorredor = nombreCorredor;
        this.metros = metros;
        this.metrosrecorridos = 0;
    }

    public void correr() {
        System.out.println("El corredor " + nombreCorredor + " ha empezado a correr");

        while (metros < metrosrecorridos){
            metros++;
        }


        System.out.println("El corredor " + nombreCorredor + " ha terminado a correr");
    }

    @Override
    public void run (){ this.correr();}
    public static void main (String[]args) throws InterruptedException{

        CorredoresEj2 pedro = new CorredoresEj2 ("Pedro",0);
        CorredoresEj2 marcos = new CorredoresEj2("Marcos",0);
        CorredoresEj2 alfonso = new CorredoresEj2("Alfonso", 0);
        CorredoresEj2 paco = new CorredoresEj2("Paco",0);
        CorredoresEj2 lorent = new CorredoresEj2("Lorent", 0);

        System.out.println("introduce los metros que tiene la carrera");
        metrosrecorridos = sc.nextInt();

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




        System.out.println("-> Fin de la Ejecución del pragrama Multihilo");


    }

}
