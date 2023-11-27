package alumnos;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

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
public class ArrayEj3 implements Runnable{
    static Scanner sc = new Scanner(System.in);
    static Random ran = new Random();

    private volatile static int tamñoArray;

    public ArrayEj3(ArrayList<Integer> vector){


    }
    public void sumar() throws InterruptedException{

        int resultado = 0;
        for (int i = 0; i < sumar.size(); i++){
            resultado += numerosSuma.get(i);
        }
    }
    @Override
    public void run() {
        try{
            this.sumar();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){

        System.out.println("Introduce la cantida de números que quieras sumar. Los números se crearan aleatoriamente");
        int cantidad = sc.nextInt();

        ArrayList<Integer> numerosSuma = new ArrayList<>();

        for (int i = 0; i < cantidad; i++){
            int numeroRandom = ran.nextInt(100);
            numerosSuma.add(numeroRandom);
        }
        System.out.println(numerosSuma);
        int resultado = 0;
        for (int i = 0; i < numerosSuma.size(); i++){
            resultado += numerosSuma.get(i);
        }
        System.out.println(resultado + "\n");

        int mitad = numerosSuma.size()/2;

        ArrayList<Integer> mitad1 = new ArrayList<>(numerosSuma.subList(0, mitad + 1)); //añade a una nueva arraylist de la posición 0 a la posición mitad+1 (por si el numero es impar)
        ArrayList<Integer> mitad2 = new ArrayList<>(numerosSuma.subList(mitad + 1, numerosSuma.size()));

        //int[] mitad1 = new int[numerosSuma.subList(0, mitad + 1)];

        ArrayEj3 mit1 = new ArrayEj3(mitad1);
        ArrayEj3 mit2 = new ArrayEj3(mitad2);


        System.out.println("-> Inicio de la Ejecución del pragrama Multihilo");

        Thread hilo_mitad1 = new Thread(mitad1);
        Thread hilo_mitad2 = new Thread(mitad2);

        hilo_mitad1.start();
        hilo_mitad2.start();

    }




}
