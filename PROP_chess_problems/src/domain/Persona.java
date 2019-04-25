package domain;
/**
 * CLASE HEREDADA DE JUGADOR, REPRESENTA UNA PERSONA
 * @author Carla Garcia
 *
 */
public class Persona extends Jugador {
	
	private String contrasena;
	
	/**
	 * Crea un objeto persona vacio
	 */
	public Persona() {}

	/**
	 * Crea un objeto persona con nombre
	 * @param nom del tipo String es el nombre de la persona creada
	 */	
	public Persona(String nom) {
		super.nombre = nom;
	}
	
	/**
	 * Crea un objeto persona con nombre y contrasena
	 * @param nom del tipo String es el nombre de la persona creada
	 * @param contra del tipo String es la contrasena del usuario para hacer log in
	 */
	public Persona(String nom, String contra) {
		super.nombre = nom;
		this.contrasena = contra;
	}
	
	/**
	 * Modifica la contrasena de un usuario
	 * @param contra del tipo String es la nueva contrasena de la persona
	 */
	public void setContrasena(String contra) {
		this.contrasena = contra;
	}
	
	/**
	 * Obtiene la contrasena de la Persona
	 * @return String conteniendo la contrasena
	 */
	public String getContrasena() {
		return this.contrasena;
	}
	
	@Override
	public boolean esPersona() {
		return true;
	}

}
