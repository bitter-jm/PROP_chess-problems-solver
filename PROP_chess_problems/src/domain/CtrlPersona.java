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
	
	public String getNombrePersona() {
		return persona.getNombre();
	}
	
	public boolean login(String nombre,String contrasena) {
		this.persona = db.login(nombre, contrasena);
		if (this.persona != null) return true;
		else return false;
	}
	
	public boolean signIn(String nombre, String contrasena) {
		this.persona = db.createPersona(nombre, contrasena);
		if (this.persona != null) return true;
		else return false;
	}
	
	public void logOut() {
		this.persona = null;
	}
	
	public boolean isLoggedIn() {
		if (this.persona == null) return false;
		else return true;
	}
	
}
