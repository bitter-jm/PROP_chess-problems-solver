package domain;

public class Jugador {
	
	protected String nombre;
	
	public Jugador() {
		this.nombre = null;
	}
	
	public boolean esPersona() {
		return true;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
}
