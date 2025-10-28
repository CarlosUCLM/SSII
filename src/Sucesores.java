import java.util.*;

public class Sucesores {

    public static List<String> generarSucesores(String nivel) {

        char[][] tablero = convertirAMatriz(nivel);
        Map<Character, List<int[]>> vehiculos = buscarVehiculos(tablero);
        List<String> lista = new ArrayList<>();

        List<Character> vehiculosOrdenados = new ArrayList<>(vehiculos.keySet());
        Collections.sort(vehiculosOrdenados);

        for (char v : vehiculosOrdenados) {
            List<int[]> posiciones = vehiculos.get(v);
            boolean horizontal = esHorizontal(posiciones);

            int minFila = 6, maxFila = -1, minCol = 6, maxCol = -1;
            for (int[] pos : posiciones) {
                int fila = pos[0];
                int col = pos[1];
                if (fila < minFila) minFila = fila;
                if (fila > maxFila) maxFila = fila;
                if (col < minCol) minCol = col;
                if (col > maxCol) maxCol = col;
            }

            if (horizontal) {
                // Mover a la derecha (+)
                for (int paso = 1; maxCol + paso < 6 && tablero[minFila][maxCol + paso] == 'o'; paso++) {
                    String nuevo = mover(tablero, v, paso, true);
                    int costo = 6 - paso;
                    lista.add("[" + v + "+" + paso + "," + nuevo + "," + costo + "]");
                }

                //  Mover a la izquierda (-)
                for (int paso = 1; minCol - paso >= 0 && tablero[minFila][minCol - paso] == 'o'; paso++) {
                    String nuevo = mover(tablero, v, -paso, true);
                    int costo = 6 - paso;
                    lista.add("[" + v + "-" + paso + "," + nuevo + "," + costo + "]");
                }

            } else {
                // Mover hacia abajo (+)
                for (int paso = 1; maxFila + paso < 6 && tablero[maxFila + paso][minCol] == 'o'; paso++) {
                    String nuevo = mover(tablero, v, paso, false);
                    int costo = 6 - paso;
                    lista.add("[" + v + "+" + paso + "," + nuevo + "," + costo + "]");
                }

                // Mover hacia arriba (-)
                for (int paso = 1; minFila - paso >= 0 && tablero[minFila - paso][minCol] == 'o'; paso++) {
                    String nuevo = mover(tablero, v, -paso, false);
                    int costo = 6 - paso;
                    lista.add("[" + v + "-" + paso + "," + nuevo + "," + costo + "]");
                }
            }
        }
        return lista;
    }

 
    private static char[][] convertirAMatriz(String nivel) {
        char[][] tablero = new char[6][6];
        for (int i = 0; i < 36; i++) {
            tablero[i / 6][i % 6] = nivel.charAt(i);
        }
        return tablero;
    }

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

    private static String mover(char[][] tablero, char vehiculo, int pasos, boolean horizontal) {
        char[][] nuevoTablero = new char[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                nuevoTablero[i][j] = tablero[i][j];
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (nuevoTablero[i][j] == vehiculo) {
                    nuevoTablero[i][j] = 'o';
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (tablero[i][j] == vehiculo) {
                    if (horizontal) {
                        nuevoTablero[i][j + pasos] = vehiculo;
                    } else {
                        nuevoTablero[i + pasos][j] = vehiculo;
                    }
                }
            }
        }

        String nivelNuevo = "";
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                nivelNuevo += nuevoTablero[i][j];
            }
        }

        return nivelNuevo;
    }

    private static boolean esHorizontal(List<int[]> Posicion) {
        int fila = Posicion.get(0)[0];
		
        boolean Resultado = true;
        for (int[] p : Posicion)
            if (p[0] != fila)
                Resultado = false;
        return Resultado;
    }
}
