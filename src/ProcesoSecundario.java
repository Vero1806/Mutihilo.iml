import java.util.Random;

public class ProcesoSecundario {
    public static void main (String[] args){
        //int numero = (Math.random() *6)
        Random random = new Random();
            int numero = random.nextInt(3);
         System.exit(numero);
    }
}
