package stubs;
/**
 * 
 * @author Carla Garc�aC
 *
 */
public class Tablero {

	public boolean Tablero(String FEN) {
		if (FEN.equals("BUENO")) return true;
		return false;
	}
	public boolean esMateColor(String c) {
		if (c.equals("BLANCAS")) return true;
		return false;
	}
}
