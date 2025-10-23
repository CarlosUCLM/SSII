import java.util.*;

public class Sucesores {
    public static List<String> generarSucesores(String nivel) {
		 
		 	// Comprobar luego si las variables son las correctas
	        char[][] tablero = convertirAMatriz(nivel);
	        
	        Map<Character, List<int[]>> vehiculos = buscarVehiculos(tablero);
	        List<String> lista = new ArrayList<>();

	        List<Character> vehiculosOrdenados = new ArrayList<>(vehiculos.keySet());




        //En proceso de contrucción






}
    //De la clase Pregunta
	 private static char[][] convertirAMatriz(String nivel) {
	        char[][] tablero = new char[6][6];
	        for (int i = 0; i < 36; i++) {
	            tablero[i / 6][i % 6] = nivel.charAt(i);
	        }
	        return tablero;
	    }
    // De la clase pregunta
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