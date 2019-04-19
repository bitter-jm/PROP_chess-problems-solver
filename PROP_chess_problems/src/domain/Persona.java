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
	//HERENCIA
	@Override
	public boolean esPersona() {
		return true;
	}
	@Override
	public boolean esM1() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.nombre;
	}
}
