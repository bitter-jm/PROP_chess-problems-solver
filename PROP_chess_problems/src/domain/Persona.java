package domain;

public class Persona extends Jugador {
	
	private String contraseña;
	
	//CONSTRUCTORAS
	public Persona() {}
	
	public Persona(String nom) {
		super.nombre = nom;
	}
	
	public Persona(String nom, String contra) {
		super.nombre = nom;
		this.contraseña = contra;
	}
	
	//SETTERS Y GETTERS
	public void setContraseña(String contra) {
		this.contraseña = contra;
	}
	
	public String getContraseña() {
		return this.contraseña;
	}
	
}
