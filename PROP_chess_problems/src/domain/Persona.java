package domain;
/**
 * CLASE HEREDADA DE JUGADOR, REPRESENTA UNA PERSONA
 * @author Carla Garc�aC
 *
 */
public class Persona extends Jugador {
	
	private String contrase�a;
	
	/**
	 * Crea un objeto persona vac�o
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
	 * Crea un objeto persona con nombre y contrase�a
	 * @param nom del tipo String es el nombre de la persona creada
	 * @param contra del tipo String es la contrase�a del usuario para hacer log in
	 */
	public Persona(String nom, String contra) {
		super.nombre = nom;
		this.contrase�a = contra;
	}
	/**
	 * Modifica la contrase�a de un usuario
	 * @param contra del tipo String es la nueva contrase�a de la persona
	 */
	public void setContrase�a(String contra) {
		this.contrase�a = contra;
	}
	
	@Override
	public boolean esPersona() {
		return true;
	}

}
