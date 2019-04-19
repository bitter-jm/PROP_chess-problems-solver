package domain;
import java.util.Calendar;
import domain.Jugador;
import domain.Persona;
import domain.M1;
import domain.Tablero;
//import domain.Problema;

public class Partida {
	//private Problema prob = null;
	private Tablero tab = null;
	private Jugador j1 = null;
	private Jugador j2 = null;
	private Calendar fecha;
	private int dia;
	private int mes;
	private int año;
	
	public String fen; //mybe no hace falta?
	public int max_mov;
	public boolean color;
	public boolean turno = false;
	public int mov_uno = 0;
	public int mov_dos = 0;
	public String ganador = null;
	
	//CONSTRUCTORA
	public Partida(Jugador jugador1, Jugador jugador2, /*Problema*/String prob) {
		fecha = Calendar.getInstance();
	    dia = fecha.get(Calendar.DATE);
	    mes = fecha.get(Calendar.MONTH);
	    año = fecha.get(Calendar.YEAR);
	    
	    //max_mov = prob.get_maxMovimientos();
	    //fen = prob.get_fen();
	    //color = prob.getColorAGanar();
	    String c;
    	if (color) c = "NEGRAS";
    	else c = "BLANCAS"; 
	    tab = new Tablero(fen);
	    
	    if (jugador1.esPersona()) j1 = (Persona) jugador1;
	    else if (!jugador1.esPersona() && jugador1.esM1()) {
	    	//j1 = (M1) jugador1; porque tiene que poner M1 subclase de jugador
	    	//j1.setColor(c);
	    }
	    else if (!jugador1.esPersona() && !jugador1.esM1()) {
	    	j1 = (M2) jugador1;
	    	//j1.setColor(c);
	    }
	    if (jugador2.esPersona()) j2 = (Persona) jugador2;
	    else if (!jugador2.esPersona() && jugador2.esM1()) {
	    	//j1 = (M1) jugador2; porque tiene que poner M1 subclase de jugador
	    	//j1.setColor(c);
	    }
	    else if (!jugador2.esPersona() && !jugador2.esM1()) {
	    	j1 = (M2) jugador2;
	    	//j1.setColor(c);
	    }
	}
		
	//GETTERS
	public int getDia() {
		return this.dia;
	}
	public int getMes() {
		return this.mes;
	}
	public int getAño() {
		return this.año;
	}
	public Jugador getJugador1() {
		return this.j1;
	}
	public Jugador getJugador2() {
		return this.j2;
	}
	public Tablero getTablero() {
		return this.tab;
	}
	
	//METODOS
	public void realizarMovimiento() {
		if(!turno && !j1.esPersona()) {
			++mov_uno;
			turno = !turno;
			//j1.realizarMovimiento(fen);
			
		}
		else if(turno && !j2.esPersona())  {
			++mov_dos;
			turno = !turno;
			//j2.realizarMovimiento(fen);
		}
	}
	
	public void acabarPartida() {
		String c;
    	if (color) c = "NEGRAS";
    	else c = "BLANCAS"; 
		if(tab.esMateColor(c)) {
			ganador = j1.getNombre();
		}
		else ganador = j2.getNombre();
	}
	
	public void cancelarPartida() {
		mov_uno = mov_dos = 0;
		turno = false;
		j1 = j2 = null;
		//prob = null;
		tab.limpiarTablero();
	}
}
