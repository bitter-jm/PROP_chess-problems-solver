package domain;
/**
 * CLASE JUGADOR
 * @author Carla Garc�aC
 *
 */
public class Jugador {
	
	protected String nombre;
	/**
	 * Crea un objeto vac�o de Jugador
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
	 * M�todo abstracto que diferencia los tipos de jugadores, personas o m�quinas
	 * @return true del tipo Booleano si el jugador es persona, false en caso contrario
	 */
	public boolean esPersona() {
		return true;
	}

	
}
