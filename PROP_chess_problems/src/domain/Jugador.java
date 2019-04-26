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
	
	/**
	 * Metodo sobrecargable que diferencia los tipos de jugadores, personas o maquinas
	 * @return true del tipo Booleano si el jugador es persona, false en caso contrario
	 */
	public abstract boolean esPersona();

	
}
