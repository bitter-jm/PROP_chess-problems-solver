package domain;

public class M2 extends Jugador {

	public M2() {
		// TODO Auto-generated constructor stub
		super.nombre = "MAQUINA2";
	}

	@Override
	public boolean esPersona() {
		// TODO Auto-generated method stub
		return false;
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
