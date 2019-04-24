package stubs;

public class Problema {
	public String nombre;
	
	public Problema() {
		nombre = "PROBLEMA1";
	}
	public int getMaxMovimientos() {
		if(nombre.equals("PROBLEMA1")) return 3;
		return 0;
	}
	public boolean getColorAGanar() {
		if(nombre.equals("PROBLEMA1")) return true;
		return false;
	}
	
	
}
