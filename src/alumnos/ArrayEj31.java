package alumnos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
    Realiza un programa que calcule la suma de un array de números enteros.
    El tamaño del array se solicitará al usuario por pantalla, y
    posteriormente se asignará, de forma aleatoria un valor entero
    (entre 0 y 100) para cada uno de los elementos del array.

    Realiza la suma de los elementos del array:
    1. Mediante un método secuencial, tradicional

    2. Mediante un método multihilo, dividiendo el array en dos trozos iguales,
    realizando la suma parcial de cada trozo y posteriormente
    sumando ambas sumas parciales.
    3. Comprueba que ambos resultados son iguales y calcula el tiempo
    que se tarda en realizar el cálculo mediante ambos métodos.

    Nota: Para que los resultados sean más evidentes, puedes “retrasar artificialmente”
    la ejecución de la suma de cada elemento usando
    Thread.sleep (<numero_milisegundos>). Con 1 milisegundo suele ser suficiente.
     */
public class ArrayEj31 implements Runnable {
        static Scanner sc = new Scanner(System.in);
        static Random ran = new Random();

        private int [] mitad;
        private String nombreArray;

        public ArrayEj31(String nombreArray, int [] mitad){
            this.mitad = mitad;

        }
        public void sumar(int [] numerosSuma) throws InterruptedException{

            int resultado = 0;
            for (int i = 0; i < numerosSuma.length; i++){
                resultado += numerosSuma[i];
            }
            System.out.println("La mitad " + nombreArray + " suma en total " + resultado );

        }
        @Override
        public void run() {
            try{
                this.sumar();
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }

        public static void secuencial(int [] numerosSuma) {

            System.out.println(numerosSuma);
            int resultado = 0;
            for (int i = 0; i < numerosSuma.length; i++){
                resultado += numerosSuma[i];
            }
            System.out.println(resultado + "\n");
        }
        public static void arrayMultihilo(int[] numerosSuma) throws InterruptedException {

            int numerosTotal = numerosSuma.length;
            int numerosMitad = numerosSuma.length/2;

            int [] mitad1 = new int[numerosMitad];
            int [] mitad2 = new int[numerosTotal - numerosMitad];

            for (int i = 0; i < numerosMitad; i++) {
                mitad1[i] = numerosSuma[i];
            }

            for (int i = numerosMitad; i < numerosTotal; i++) {
                mitad2[i - numerosMitad] = numerosSuma[i];
            }

            System.out.println("-> Inicio de la Ejecución del pragrama Multihilo");

            ArrayEj31 mitad31 = new ArrayEj31("1", mitad1);
            ArrayEj31 mitad32 = new ArrayEj31("2", mitad2);

            Thread hilo_mitad31 = new Thread(mitad31);
            Thread hilo_mitad32 = new Thread(mitad32);

               hilo_mitad31.start();
               hilo_mitad32.start();

               hilo_mitad31.join();
               hilo_mitad32.join();

            System.out.println("-> Fin de la Ejecución del pragrama Multihilo");
        }
        public static void main(String[] args) throws InterruptedException {

            System.out.println("Introduce la cantida de números que quieras sumar. Los números se crearan aleatoriamente");
            int cantidad = sc.nextInt();

            int[] numerosSuma = new int[cantidad];

            for (int i = 0; i < cantidad; i++){
                numerosSuma[i] = ran.nextInt(100);
            }

            secuencial(numerosSuma);

            arrayMultihilo(numerosSuma);


        }


}
