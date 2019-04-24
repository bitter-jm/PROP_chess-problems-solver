package stubs;
/**
 * 
 * @author Carla GarcíaC
 *
 */
public class Problema {
	public String nombre;
	private int veces;
	public Problema() {
		nombre = "PROBLEMA1";
		veces = 2;
	}
	public int getMaxMovimientos() {
		if(nombre.equals("PROBLEMA1")) return 3;
		return 0;
	}
	public boolean getColorAGanar() {
		if(nombre.equals("PROBLEMA1")) return true;
		return false;
	}
	public String getFEN_Tablero() {
		if(nombre.equals("PROBLEMA1")) return "BUENO";
		return "MALO";
		
	}
	public int getVecesJugado() {
		return veces;
	}
	public void setVecesJugado(int v) {
		veces = v;
	}

}
