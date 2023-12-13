package alumnos;
//Verónica Prieto Prieto

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

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

     */
public class ArrayEj3 implements Runnable {
    static Scanner sc = new Scanner(System.in);
    static Random ran = new Random();

    private int [] mitad;
    private String nombreArray;
    public int sumaDeCadaArray;
    private final Semaphore semaforo = new Semaphore(2);

    public ArrayEj3(String nombreArray, int [] mitad){
        this.mitad = mitad;
        this.nombreArray = nombreArray;
    }
    public void sumar(int [] numerosSuma) throws InterruptedException{

        semaforo.acquire();
        int resultado = 0;
        for (int i = 0; i < numerosSuma.length; i++){
            resultado += numerosSuma[i];
        }

        System.out.println("La mitad " + nombreArray + " suma en total " + resultado );

        sumaDeCadaArray = resultado;
        semaforo.release();
    }
    @Override
    public void run() {
        try{
            this.sumar(mitad);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
    public static void secuencial(int [] numerosSuma) {

        int resultado = 0;
        for (int i = 0; i < numerosSuma.length; i++){
            resultado += numerosSuma[i];
        }
        System.out.println("La suma secuencial es = " + resultado + "\n");
    }


    public static void arrayMultihilo(int[] numerosSuma) throws InterruptedException {

        int numerosTotal = numerosSuma.length;
        int numerosTercio2 = numerosSuma.length/3;
        int numerosTercio3 = numerosSuma.length/3;

        int [] mitad1 = new int[numerosTercio2];
        int [] mitad2 = new int[numerosTotal - numerosTercio2 - numerosTercio3];
        int [] mitad3 = new int[numerosTotal - numerosTercio2 - numerosTercio3];

        for (int i = 0; i < numerosTercio2; i++) {
            mitad1[i] = numerosSuma[i];
        }
        Thread.sleep(1000);
        for (int i = numerosTercio2; i < numerosTotal; i++) {
            mitad2[i - numerosTercio2 - numerosTercio3] = numerosSuma[i];
        }
        Thread.sleep(1000);
        for (int i = numerosTercio3; i < numerosTotal; i++) {
            mitad3[i - numerosTercio2 - numerosTercio3] = numerosSuma[i];
        }

        System.out.println("-> Inicio de la Ejecución del pragrama Multihilo");

        ArrayEj3 mitad31 = new ArrayEj3("1", mitad1);
        ArrayEj3 mitad32 = new ArrayEj3("2", mitad2);
        ArrayEj3 mitad33 = new ArrayEj3("3", mitad3);

        Thread hilo_mitad31 = new Thread(mitad31);
        hilo_mitad31.setName("1");
        Thread hilo_mitad32 = new Thread(mitad32);
        hilo_mitad32.setName("2");
        Thread hilo_mitad33 = new Thread(mitad33);
        hilo_mitad33.setName("3");

        hilo_mitad31.start();
        hilo_mitad32.start();
        hilo_mitad33.start();

        hilo_mitad31.join();
        hilo_mitad32.join();
        hilo_mitad33.join();

        System.out.println("-> Fin de la Ejecución del pragrama Multihilo \n");

        System.out.println("El total es de las 2 mitades es: " + (mitad31.sumaDeCadaArray + mitad32.sumaDeCadaArray + mitad33.sumaDeCadaArray));
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