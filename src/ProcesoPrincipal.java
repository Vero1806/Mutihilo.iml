public class ProcesoPrincipal {

    public static void main(String[] args){

        String rutaClase = "C:\\Users\\prieto.prver\\IdeaProjects\\untitled1\\src\\";
        try{
            System.out.println("-> Inicio del Proceso Principal");
            String [] infoProceso = {"java" , rutaClase + "ProcesoSecundario.java"};
            Process proceso = Runtime.getRuntime().exec(infoProceso);
            int valorRetorno = proceso.waitFor();

            switch (valorRetorno) {
                case 0 : System.out.println(" Valor 0 : Proceso finalizado correctamente");
                    break;
                case 1 : System.out.println("Valor 1: Proceso en Espera");
                    break;
                case 2 : System.out.println("Valor 2: Proceso en Ejecución");
                    break;
                case 3 : System.out.println("Valor 3: Proceso Bloqueado");
                    break;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
