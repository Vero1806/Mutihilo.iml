package voluntarios;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.Semaphore;

//La idea es un programa que simule como se escribe/lee un fichero o algo similar
//La escritura/lectura está sincronizada, de modo que no se lee hasta que no se escribe y se escribe cuando se ha leído
//Por tanto, los procesos se esperan mutuamente.

public class Ej1Resuelto implements Runnable {
    public String fraseContenido;
    public char caracterFrase;
    private final Semaphore semaforoEscritura = new Semaphore(1);
    //    Ojo, se puede inicializar a 0, porque release() le dará un permiso
    private final Semaphore semaforoLectura = new Semaphore(0);

    public void escritura() {
        int tiempoEscritura;
        for (int indice = 0; indice < fraseContenido.length(); indice++) {
            try {
//                "Bloqueo" el semáforo de lectura: Estaba a 1 y ahora queda a 0
                semaforoEscritura.acquire();

                caracterFrase=fraseContenido.charAt(indice);
//                Esto es para simular que se tardan de 1 a 5 segundos en escribir en el fichero
                tiempoEscritura=new Random().nextInt(5) + 1;
                System.out.println("+-----------------------------------------------------+");
                System.out.println("+ Escritura                                           +");
                System.out.println("  -> Escribiendo en fichero el carácter: " + caracterFrase);
                System.out.println("  -> Tiempo de escritura estimado: " + tiempoEscritura + " segundos");
                System.out.println("+-----------------------------------------------------+");
                Thread.sleep(tiempoEscritura * 1000);

//                "Libero" el semáforo de lectura: Estaba a 0 y ahora queda a 1
                semaforoLectura.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void lectura() {
        //¡Ojo! Para comparar un char se usan comillas simples
        while(caracterFrase!='#'){
            try {
//                "Bloqueo" el semáforo de lectura: Estaba a 1 y ahora queda a 0
                semaforoLectura.acquire();

                System.err.println("+-----------------------------------------------------+");
                System.err.println("+ Lectura                                             +");
                System.err.println("  <- Leyendo de fichero el carácter: " + caracterFrase);
                System.err.println("+-----------------------------------------------------+");

//                "Libero" el semáforo de escritura: Estaba a 0 y ahora queda a 1
                semaforoEscritura.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Hilo Escritor")) escritura();
        if (Thread.currentThread().getName().equals("Hilo Lector")) lectura();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ej1Resuelto ficheroTrabajo = new Ej1Resuelto();

        System.out.println("Introduzca a continuación la frase para simular la E/S a fichero: ");
        ficheroTrabajo.fraseContenido = sc.nextLine() + "#";

        Thread hiloEscritor = new Thread(ficheroTrabajo);
        hiloEscritor.setName("Hilo Escritor");
        Thread hiloLector = new Thread(ficheroTrabajo);
        hiloLector.setName("Hilo Lector");

        hiloEscritor.start();
        hiloLector.start();
    }
}