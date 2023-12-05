package multihilo;

public class RatonSincronizadoBloque implements Runnable {
    String nombre;
    long tiempoAlimentacion;
    int alimentoConsumido;
    //  Se va a usar el nombre mutex para señalar la zona crítica o de exclusión mutua
    Object mutex = new Object();

    //Constructor
    public RatonSincronizadoBloque(String nombre, int tiempoAlimentacion) {
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }

    public void comer() {
//      Sentencias no sincronizadas
        System.out.println("  || El ratón " + nombre + ", hilo " + Thread.currentThread().getName() + ", está dentro del método comer().");
        try {
            synchronized (mutex) {
//              Sentencias sincronizadas
                System.out.println("  |-> El ratón " + nombre + ", hilo " + Thread.currentThread().getName() + ", ha comenzado a alimentarse.");
//              Se deja esta parada para que se vea más claro
                Thread.sleep(tiempoAlimentacion * 1000);
                alimentoConsumido++;
                System.out.println("  |<- El ratón " + nombre + ", hilo " + Thread.currentThread().getName() + ", ha terminado de alimentarse. Alimento consumido: " + alimentoConsumido);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.comer();
    }

    public static void main(String[] args) {
        RatonSincronizadoBloque mickey = new RatonSincronizadoBloque("Mickey", 1);

        System.out.println("-> Inicio de la Ejecución del programa MULTIHILO");

        System.out.println("-> Inicio de la Ejecución del programa MULTIHILO");

//      Nota: Crear 10 hilos sobre el mismo objeto ¿Qué ocurre? ¿Y si el bloque no está synchronized?
        for (int numeroHilos=0;numeroHilos<10;numeroHilos++){
            new Thread(mickey).start();
        }
    }
}
