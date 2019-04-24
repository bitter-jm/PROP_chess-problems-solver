package domain;
/**
 * CLASE HEREDADA DE JUGADOR, REPRESENTA UNA PERSONA
 * @author Carla GarcíaC
 *
 */
public class Persona extends Jugador {
	
	private String contraseña;
	
	/**
	 * Crea un objeto persona vacío
	 */
	public Persona() {}
//LAS NECESITAMOS PARA ALGO? QUIZAS LA VACIA SÍ DESDE PARTIDA PARA DEFINIR EL TIPO DE JUGADOR Q LE PASAN PERO LA CREADORA(NOM)?
	/**
	 * Crea un objeto persona con nombre
	 * @param nom del tipo String es el nombre de la persona creada
	 */	
	public Persona(String nom) {
		super.nombre = nom;
	}
	/**
	 * Crea un objeto persona con nombre y contraseña
	 * @param nom del tipo String es el nombre de la persona creada
	 * @param contra del tipo String es la contraseña del usuario para hacer log in
	 */
	public Persona(String nom, String contra) {
		super.nombre = nom;
		this.contraseña = contra;
	}
	/**
	 * Modifica la contraseña de un usuario
	 * @param contra del tipo String es la nueva contraseña de la persona
	 */
	public void setContraseña(String contra) {
		this.contraseña = contra;
	}

}
