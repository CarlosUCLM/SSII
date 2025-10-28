import java.util.*;

public class Pregunta {
   
    public static void responder(String nivel, String[] args) {
        private static final char[][] tablero = convertirAMatriz(nivel);
        for (int i = 0; i < args.length; i++) {

            //Con este switch puedo, según la orden, hacer solo lo que me pide la linea de comando. Cada case con su correspondiente orden
            switch (args[i]) {
                //llamada al método contar vehículos para
                case "--howmany" -> System.out.println(contarVehiculos(tablero));

                case "--size" -> {
                    if (i + 1 < args.length) {
                        char vehiculo = args[i + 1].charAt(0);
                        System.out.println(tamanioVehiculo(tablero, vehiculo));
                        i++; //Para ir al siguiente args, hay que sumarle 1 pq si no no se mueve
                    }
                }

                case "--what" -> {
                    if (i + 1 < args.length) {
                        String[] partes = args[i + 1].split(",");
                        int fila = Integer.parseInt(partes[0]);
                        int col = Integer.parseInt(partes[1]);
                        System.out.println(tablero[fila][col]);
                        i++;
                    }
                }

                case "--whereis" -> {
                    if (i + 1 < args.length) {
                        char vehiculo = args[i + 1].charAt(0);
                        List<int[]> posiciones = buscarVehiculos(tablero).get(vehiculo);
                        if (posiciones != null) {
                            for (int[] pos : posiciones) {
                                System.out.print("(" + pos[0] + "," + pos[1] + ")");
                            }
                            System.out.println();
                        }
                        i++;
                    }
                }
                case "--goal" -> {
                    System.out.println(esObjetivo(tablero));
                }

                default -> {
                }

                case "--move" -> {
                    if (i + 1 < args.length) {
                        String[] acciones = args[i + 1].split(",");
                        String resultado = aplicarMovimientos(nivel, acciones);
                        System.out.println(resultado);
                        i++;
                    }
                }
            }
            
            }

       
    }

    // Hace que la linea de letras se vuelva una matriz
    private static char[][] convertirAMatriz(String nivel) {
        char[][] tablero = new char[6][6];
        for (int i = 0; i < 36; i++) {
            tablero[i / 6][i % 6] = nivel.charAt(i);
        }
        return tablero;
    }

    //Recorró la matriz para así guardar las letras con los vehículos que hay para luego mostrarlos.
    private static int contarVehiculos(char[][] tablero) {
        List<Character> lista = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                char c = tablero[i][j];
                if (c != 'o' && !lista.contains(c)) {
                    lista.add(c);
                }
            }
        }
        return lista.size();
    }

    //Este método requiere del siguiente pues primero busca el vehículo, ve donde esta y según donde esté cuenta y devuelve el tamaño del vehículo
    private static int tamanioVehiculo(char[][] tablero, char vehiculo) {
        List<int[]> posiciones = buscarVehiculos(tablero).get(vehiculo);
        if (posiciones == null) return 0;
        return posiciones.size();
    }

    //Con esto puedo saber donde está el vehículo que me piden
    private static Map<Character, List<int[]>> buscarVehiculos(char[][] tablero) {
        Map<Character, List<int[]>> mapa = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                char c = tablero[i][j];
                if (c != 'o') {
                    if (!mapa.containsKey(c)) {
                        mapa.put(c, new ArrayList<>());
                    }
                    mapa.get(c).add(new int[]{i, j});
                }
            }
        }
        return mapa;
    }
    private static boolean esObjetivo(char[][] tablero){
        boolean resultado = true;
         for (int j = 4; j < 6; j++){
            if (tablero[2][j] == 'A'){
                resultado=false;
            }
         }
		 return resultado;
    }

    private static String MoverCoches(String nivel, String[] acciones){
         char[][] tablero = convertirAMatriz(nivel);
        for (String accion : acciones) {
            char coche = accion.charAt(0);
            char dir = accion.charAt(1);
            int pasos = Character.getNumericValue(accion.charAt(2));
            tablero = mover(tablero, coche, dir, pasos);
        }
        return convertirACadena(tablero);
    }
     private static String convertirACadena(char[][] tablero) {
        String resultado = "";
        for (char[] fila : tablero)
            for (char c : fila)
                resultado +=c;
        return resultado;
    }

     private static char[][] mover(char[][] original, char v, char dir, int pasos) {
        Map<Character, List<int[]>> pos = buscarVehiculos(original);
        List<int[]> coordenada = pos.get(v);
        boolean horizontal = coordenada.get(0)[0] == coordnada.get(1)[0];
        char[][] copia = new char[6][6];
        for (int i = 0; i < 6; i++) copia[i] = Arrays.copyOf(original[i], 6);

        for (int[] p : coordenada) copia[p[0]][p[1]] = 'o';
        int delta = (dir == '+') ? pasos : -pasos;

        if (horizontal) {
            for (int[] p : coordnada) copia[p[0]][p[1] + delta] = v;
        } else {
            for (int[] p : coordenada) copia[p[0] + delta][p[1]] = v;
        }
        return copia;
    }
}


