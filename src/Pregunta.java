import java.util.*;

public class Pregunta {
   
    public static void responder(String nivel, String[] args) {
        char[][] tablero = convertirAMatriz(nivel);
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

                default -> {
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
}

