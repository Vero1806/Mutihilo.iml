public class RatonMultihilo extends Thread { //El Theard es una clase de java para multihilos. convierte el raton en ratónmultihilo
    private String nombre;
    private long tiempoAlimentacion;

    public RatonMultihilo (String nombre, int tiempoAlimentacion){
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;

    }

    public void comer(){
        try{
            System.out.println(" | -> El ratón " + nombre + " ha comenzado a alimentarse.");
            Thread.sleep(tiempoAlimentacion*1000); // sleep funciona con mili segundos, los convertimos a segundos al multiplicarlo por mil
            System.out.println( "  | <- El ratón " + nombre + " ha terminado de alimentarse");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run (){
        this.comer();
    }

    public static void main (String[] args) {
        RatonMultihilo mickey = new RatonMultihilo ("Mickey",5);
        RatonMultihilo jerry = new RatonMultihilo("Jerry",10);
        RatonMultihilo perez = new RatonMultihilo("Peréz",2);
        long tiempoInicio,tiempoFin;
        System.out.println("-> Inicio de la Ejecución del pragrama Multihilo");

        tiempoInicio=System.currentTimeMillis();

        mickey.start();

        jerry.start();

        perez.start();


                while  ((mickey.isAlive()) || (jerry.isAlive()) || perez.isAlive())   {
                    //System.out.println("IS A LIVE");
                    }

       //try {
        //   mickey.join();
        //   jerry.join();
        //   perez.join();
       //}catch (InterruptedException e){
        //   e.printStackTrace();
       //}


       tiempoFin = System.currentTimeMillis();

        //if(mickey.tiempoAlimentacion < jerry.tiempoAlimentacion){
        //    tiempoFin = jerry.tiempoAlimentacion*1000;
        //} else if (jerry.tiempoAlimentacion < perez.tiempoAlimentacion) {
        //    tiempoFin = perez.tiempoAlimentacion*1000;
        //}else tiempoFin = mickey.tiempoAlimentacion*1000;


        System.out.println("-> Fin de la Ejecución. Tiempo transcurrido: " +(int)(((tiempoFin-tiempoInicio)/1000)) + " segundos"); // (int)(((tiempoFin-tiempoInicio)/1e9)) 1 elevado a 9 para convertir los nanos segundos a segundos
    }

}
