package domain;

import domain.Persona;
import domain.CtrlDB;

public class CtrlPersona {

	private Persona persona;
	private CtrlDB db = CtrlDB.getInstance();
	private static CtrlPersona single_instance = null;

	public static CtrlPersona getInstance() {
		if (single_instance == null) 
			single_instance = new CtrlPersona(); 
		return single_instance; 
	}
	
	/**
	 * Obtiene el nombre de la persona registrada
	 * @return String nombre persona registrada
	 */
	public String getNombrePersona() {
		return persona.getNombre();
	}
	
	/**
	 * Inicia sesion en el sistema
	 * @param nombre String con nombre de usuario
	 * @param contrasena Strung con contrasena del usuario
	 * @return True si se ha iniciado sesion correctamente. False en caso contrario.
	 */
	public boolean login(String nombre,String contrasena) {
		this.persona = db.login(nombre, contrasena);
		if (this.persona != null) return true;
		else return false;
	}
	
	/**
	 * Crea nuevo usuario en el sistema y inicia sesion con el
	 * @param nombre String con nombre de usuario
	 * @param contrasena Strung con contrasena del usuario
	 * @return True si se ha creado el usuario y se ha iniciado sesion correctamente. False en caso contrario.
	 */
	public boolean signIn(String nombre, String contrasena) {
		this.persona = db.createPersona(nombre, contrasena);
		if (this.persona != null) return true;
		else return false;
	}
	
	/**
	 * Cierra la sesion activa
	 */
	public void logOut() {
		this.persona = null;
	}
	
	/**
	 * Consulta si hay una sesion activa
	 * @return True si hay un usuario activo. False en caso contrario
	 */
	public boolean isLoggedIn() {
		if (this.persona == null) return false;
		else return true;
	}
	
}
