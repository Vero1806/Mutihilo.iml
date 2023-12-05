package multihilo;

//Consideraciones:
//1. Probar a usar synchronized en los métodos de la clase

public class RatonSincronizado100hilos implements Runnable {
    String nombre;
    long tiempoAlimentacion;
    int alimentoConsumido;

    //Object candado2 = new Object();

    //Constructor
    public RatonSincronizado100hilos(String nombre, int tiempoAlimentacion) {
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }

    public void comer() {
        //synchronized (candado2){
        System.out.println("  |-> El ratón " + nombre + ", hilo " + Thread.currentThread().getName() + ", ha comenzado a alimentarse.");
        alimentoConsumido++;
        System.out.println("  |<- El ratón " + nombre + ", hilo " + Thread.currentThread().getName() + ", ha terminado de alimentarse. Alimento Consumido: " + alimentoConsumido);
        //}
    }


    @Override
    public void run() {
        this.comer();
    }

    public static void main(String[] args) {
        RatonSincronizado100hilos mickey = new RatonSincronizado100hilos("Mickey", 1);

        System.out.println("-> Inicio de la Ejecución del programa MULTIHILO");

//      Nota: Crear 100, 1000, 10000 o 100000 hilos sobre el mismo objeto ¿Qué ocurre? ¿Y si el método comer() está synchronized?
        for (int numeroHilos = 0; numeroHilos < 1000000; numeroHilos++) {
            new Thread(mickey).start();
        }
    }
}
