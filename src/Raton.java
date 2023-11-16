public class Raton {
    private String nombre;
    private long tiempoAlimentacion;

    public Raton (String nombre, int tiempoAlimentacion){
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;

    }

    public void comer(){
        try{
            System.out.println(" | -> El ratón " + nombre + "ha comenzado a alimentarse.");
            Thread.sleep(tiempoAlimentacion*1000); // sleep funciona con mili segundos, los convertimos a segundos al multiplicarlo por mil. Le da tiempo a la variable int tiempo alimentación
            System.out.println( "  | <- El ratón " + nombre + "ha terminado de alimentarse");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

public static void main (String[] args) {
    Raton mickey = new Raton("Mickey",5);
    Raton jerry = new Raton("Jerry",10);
    Raton perez = new Raton("Peréz",2);
    long tiempoInicio,tiempoFin;
    System.out.println("-> Inicio de la Ejecución del pragrama Secuencial");
    tiempoInicio=System.nanoTime();

    mickey.comer();
    jerry.comer();
    perez.comer();

    tiempoFin = System.nanoTime();
    System.out.println("-> Fin de la Ejecución. Tiempo transcurrido: " +(int)(((tiempoFin-tiempoInicio)/1e9)) + " segundos"); // 1 elevado a 9 para convertir los nanos segundos a segundos

}



}
