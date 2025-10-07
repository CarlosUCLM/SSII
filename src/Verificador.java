import java.util.*;

public class Verificador {
   
// Esta función se llama desde RushHour.java y devuelve un número según el error
    public static int verificarNivel(String nivel) {

      
        if (nivel.length() != 36) {
            return 1;
        }

      
        for (int i = 0; i < nivel.length(); i++) {
            char c = nivel.charAt(i);
            if (!(c == 'o' || (c >= 'A' && c <= 'Z'))) {
                return 2;
            }
        }

        if (!nivel.contains("A")) {
            return 3;
        }

      
        char[][] tablero = convertirAMatriz(nivel);

       
        boolean rojoEnFila2 = false;
        for (int j = 0; j < 6; j++) {
            if (tablero[2][j] == 'A') {
                rojoEnFila2 = true;
            }
        }
        if (!rojoEnFila2) {
            return 4;
        }

        
        if (!esHorizontal(tablero, 'A')) {
            return 5;
        }

       
        Map<Character, List<int[]>> vehiculos = localizarVehiculos(tablero);

        for (Map.Entry<Character, List<int[]>> entrada : vehiculos.entrySet()) {
            char letra = entrada.getKey();
            List<int[]> posiciones = entrada.getValue();

            
            if (posiciones.size() < 2 || posiciones.size() > 3) {
                return 6;
            }

         
            if (!esContinuo(posiciones)) {
                return 7;
            }
        }

      
        return 0;
    }

    // Convierte el string en una matriz 6x6
    private static char[][] convertirAMatriz(String nivel) {
        char[][] tablero = new char[6][6];
        for (int i = 0; i < 36; i++) {
            tablero[i / 6][i % 6] = nivel.charAt(i);
        }
        return tablero;
    }

    // Comprueba si un vehículo está colocado horizontalmente
    private static boolean esHorizontal(char[][] tablero, char vehiculo) {
        List<int[]> posiciones = localizarVehiculos(tablero).get(vehiculo);
        if (posiciones == null || posiciones.size() < 2) {
            return false;
        }
        int fila = posiciones.get(0)[0];
        // Si todas las posiciones están en la misma fila, es horizontal
        for (int[] pos : posiciones) {
            if (pos[0] != fila) {
                return false;
            }
        }
        return true;
    }

    // Comprueba que las partes del vehículo estén juntas
    private static boolean esContinuo(List<int[]> posiciones) {
        // Ordena por fila y columna
        posiciones.sort((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        for (int i = 1; i < posiciones.size(); i++) {
            int fila1 = posiciones.get(i - 1)[0];
            int col1 = posiciones.get(i - 1)[1];
            int fila2 = posiciones.get(i)[0];
            int col2 = posiciones.get(i)[1];

           
            if (!((fila1 == fila2 && Math.abs(col1 - col2) == 1)
               || (col1 == col2 && Math.abs(fila1 - fila2) == 1))) {
                return false;
            }
        }
        return true;
    }

    // Encuentra todos los vehículos y las posiciones que ocupan
    private static Map<Character, List<int[]>> localizarVehiculos(char[][] tablero) {
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
