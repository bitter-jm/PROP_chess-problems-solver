package domain;

public class Persona extends Jugador {
	
	private String contrase�a;
	
	//CONSTRUCTORAS
	public Persona() {}
	
	public Persona(String nom) {
		super.nombre = nom;
	}
	
	public Persona(String nom, String contra) {
		super.nombre = nom;
		this.contrase�a = contra;
	}
	
	//SETTERS Y GETTERS
	public void setContrase�a(String contra) {
		this.contrase�a = contra;
	}
	
	public String getContrase�a() {
		return this.contrase�a;
	}
	
}
