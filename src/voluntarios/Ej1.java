package voluntarios;
/*
Vamos a ver un problema típico de concurrencia: El problema de Productores y Consumidores.

Realiza un programa que simule la escritura/lectura de una frase, solicitada por consola, a un fichero .

Para ejercitar las estructuras que se usan en multihilo y concurrencia, supongamos que existen dos métodos, uno que se encarga de escribir y el otro de leer, y:

La escritura y lectura se hace de un solo carácter a la vez.
Cuando se “escribe” un carácter, el proceso que lee espera a que la escritura termine.
Cuando se “lee” el carácter, el proceso que escribe espera a que se haya leído el carácter escrito.

Así hasta finalizar toda la frase.

Nota: No se va a trabajar realmente con ficheros.
Para simularlo, el carácter que se escribiría en el fichero lo puedes guardar en una variable para que posteriormente lo lea el otro proceso.

En definitiva, los métodos se esperan mutuamente a que hayan terminado. El uso de semáforos es útil aquí.


Nota: Para que los resultados sean más evidentes, puedes “retrasar artificialmente” la ejecución de la “escritura/lectura” Thread.sleep (<numero_milisegundos>).
*/


import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Ej1 implements Runnable{

    private char contenido;
    String nombre;
    static Scanner sc = new Scanner(System.in);
    Semaphore semaforo = new Semaphore(1);

    public Ej1(String nombre){
        this.nombre = nombre;
    }

    public void leer (String palabra){
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            //System.out.println(letra);
        }
    }

    public void escribir(char letra){
        System.out.println(letra);
    }



    @Override
    public void run() {
        try {
//            if
            semaforo.acquire();


            semaforo.release();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }


    public static void main (String[]arg){

        System.out.println("Dame una palabra para pasarla por el hilo");
        String palabra = sc.next();


//            Ej1 leer = new Ej1();
//            Ej1 escribir = new Ej1();







    }


}
