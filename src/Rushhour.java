public class Rushhour {
    
    public static void main(String[] args) {
        //Se escribe aquí para ver si se ha metido el mensaje que se debe. Si no ha mandado lo que se debe, se acaba el programa 
        if (args.length < 3) {
            System.out.println("Uso: rushhour <verify|question> -s <cadena> [opciones]");
            return;
        }
        //Aquí se van a guardar el verify o el question en la variable action. En el level se guardará la cadena larga de letras para la matriz
        String action = args[0];
        String level = null;

        // Método para que lea el mensaje hasta llegar a la -s y así saber luego que debe hacer. Si verificar o si responder preguntas. Si no
        // No hace nada y te avisa que está mal.
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-s") && i + 1 < args.length) {
                level = args[i + 1];
            }
        }
        // Se debe comprobar si hay algo en level, si no hay nada se termina el programa.
        if (level == null) {
            System.out.println("Error: falta la cadena del nivel (-s).");
            return;
        }
        //Un switch para hacer el verify o el question después de haber comprobrado que la cadena está bien escrita y que 
        //de momento parece estra todo bien
        switch (action) {
            case "verify":
                break;
            case "question":
                break;
            default:
                System.out.println("Acción desconocida: " + action);
                break;
        }
    }
}

