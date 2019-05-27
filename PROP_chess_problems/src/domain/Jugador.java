package domain;

/**
 * Representa un Jugador
 */
public abstract class Jugador {
	
	protected String nombre;
	
	/**
	 * Crea un objeto vacio de Jugador
	 */
	public Jugador() {
		this.nombre = null;
	}
	
	/**
	 * Devuelve el nombre de un jugador
	 * @return nombre del tipo String
	 */
	public String getNombre() {
		return this.nombre;
	}
		
}
