package alumnos;

import java.lang.Thread;
import java.util.Scanner;
public class corredoresNew implements Runnable{
    String nombre;
    private int distanciaCarrera;
    int distanciaRecorrida;

     static Scanner sc = new Scanner(System.in);

    public corredoresNew (String nombre, int distanciaCarrera) {
        this.nombre = nombre;
        this.distanciaCarrera = distanciaCarrera;
        this.distanciaRecorrida = 0;
    }

    public void run() {
        while (distanciaRecorrida < distanciaCarrera) {
            // Incrementar distanciaRecorrida solo cuando el estado del hilo es RUNNABLE
            if (Thread.currentThread().getState() == Thread.State.RUNNABLE) {
                distanciaRecorrida += 1;  // Incremento constante de 1 metro
                int distanciamostrada = 0;
                if (distanciaRecorrida > 0 && distanciaRecorrida%5==0)
                    distanciamostrada = distanciaRecorrida;
                System.out.println(nombre + " ha recorrido " + distanciamostrada + " metros.");
            }

            // Pequeño retardo para simular la velocidad del corredor

               }


        System.out.println(nombre + " ha terminado la carrera!");
    }



    public static void main(String[] args) {
        int distanciaCarrera = 0;

        try {
            // Obtener la longitud de la carrera desde la entrada del usuario
            System.out.println("Ingrese la longitud de la carrera en metros: ");
            distanciaCarrera = sc.nextInt();
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
            System.exit(1);
        }

        // Instanciar cada corredor por separado
        corredoresNew pedro = new corredoresNew("Pedro", distanciaCarrera);
        corredoresNew marcos = new corredoresNew("Marcos", distanciaCarrera);
        corredoresNew alfonso = new corredoresNew("Alfonso", distanciaCarrera);
        corredoresNew paco = new corredoresNew("Paco", distanciaCarrera);
        corredoresNew lorent = new corredoresNew("Lorent", distanciaCarrera);

        System.out.println("¡Comienza la carrera!");

        Thread hilo_pedro = new Thread(pedro);
        Thread hilo_marco = new Thread(marcos);
        Thread hilo_alfonso = new Thread(alfonso);
        Thread hilo_paco = new Thread(paco);
        Thread hilo_lorent = new Thread(lorent);


        // Iniciar cada hilo (corredor)
        hilo_pedro.start();
        hilo_marco.start();
        hilo_alfonso.start();
        hilo_paco.start();
        hilo_lorent.start();

        // Esperar a que todos los hilos (corredores) terminen
        try {
            hilo_pedro.join();
            hilo_marco.join();
            hilo_alfonso.join();
            hilo_paco.join();
            hilo_lorent.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Encontrar al ganador
        corredoresNew ganador = alfonso;
        if (marcos.distanciaRecorrida > ganador.distanciaRecorrida) ganador = marcos;
        if (pedro.distanciaRecorrida > ganador.distanciaRecorrida) ganador = pedro;
        if (paco.distanciaRecorrida > ganador.distanciaRecorrida) ganador = paco;
        if (lorent.distanciaRecorrida > ganador.distanciaRecorrida) ganador = lorent;

        System.out.println("\n¡" + ganador.nombre + " ha ganado la carrera!");
    }
}
