package control;
import domain.Partida;
import domain.Jugador;

//import domain.Problema;

public class ControladorDominioPartida extends ControladorDominio {
	private Partida part = null;
	
	public ControladorDominioPartida() {
		// TODO Auto-generated constructor stub
	}
		
	//le paso el nombre del jugador al controlador de datos y este me devuelve el objeto jugador
	public Jugador controldatosj(String Jugador) {
		Jugador j1= null;// = llamada entre controladores;
		return j1;
	}
	
	//le paso el nombre del problema al controlador de datos y este me devuelve el objeto jugador
	/*public Problema controldatosp(String idprob) {
		devuelve obj prob con idprob
	}*/
	
		
	public void jugar() {
		while(part.mov_uno <= part.max_mov /*&& part.getTablero().esMateColor(part.color)*/) { // que no sea mate (funcionara cuando color sea bool) aunque solo miro que j1 no haga mate...
			if ((!part.turno && !part.getJugador1().esPersona()) ||
				(part.turno && !part.getJugador2().esPersona())) {
					part.realizarMovimiento();
					
			}
			else if (!part.turno && part.getJugador1().esPersona()) {
				++part.mov_uno;
				part.turno = !part.turno;
				//llamada de espera a controlador de presentacion para que el usuario mueva, cuando lo haga le pasara el nuevo fen a tablero
			}
			else if (part.turno &&  part.getJugador2().esPersona()) {
				++part.mov_dos;
				part.turno = !part.turno;
				//llamada
			}	
		}
		part.acabarPartida();
	}
	
	public void newPartida(String Jugador1, String Jugador2, String idprob) {
		//Problema idprob = controldatosp(idprob);
		Jugador j1 = controldatosj(Jugador1);
		Jugador j2 = controldatosj(Jugador2);
		part = new Partida(j1, j2, idprob);
		
		//por si hago 4 constructoras de partidas para caso de jugador
		/*
		if (controldatosj(Jugador1).esPersona() && controldatosj(Jugador2).esPersona()) {
			Persona p1 = (Persona) controldatosj(Jugador1);
			Persona p2 = (Persona) controldatosj(Jugador2);
			part = new Partida(p1, p2, idprob);
		}
		else if (controldatosj(Jugador1).esPersona() && !controldatosj(Jugador2).esPersona()) {
			Persona p1 = (Persona) controldatosj(Jugador1);
			Maquina m2 = (Maquina) controldatosj(Jugador2);
			part = new Partida(p1, m2, idprob);
		}
		else if (!controldatosj(Jugador1).esPersona() && controldatosj(Jugador2).esPersona()) {
			Maquina m1 = (Maquina) controldatosj(Jugador1);
			Persona p2 = (Persona) controldatosj(Jugador2);
			part = new Partida(m1, p2, idprob);
		}
		else if (!controldatosj(Jugador1).esPersona() && !controldatosj(Jugador2).esPersona()) {
			Maquina m1 = (Maquina) controldatosj(Jugador1);
			Maquina m2 = (Maquina) controldatosj(Jugador2);
			part = new Partida(m1, m2, idprob);
		}*/
		jugar();
	}

}
