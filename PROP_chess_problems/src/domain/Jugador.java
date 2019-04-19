package domain;

public abstract class Jugador {
	protected String nombre;
	
	public Jugador() {
		this.nombre = null;
	}
	public abstract boolean esPersona();
	public abstract boolean esM1();
	public abstract String getNombre(); 
	
}
