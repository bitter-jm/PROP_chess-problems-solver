package domain;
import java.util.Calendar;
import domain.Jugador;
import domain.Maquina;
import domain.Tablero;
import domain.Problema;

public class Partida {
	
	private Tablero tab;
	private Jugador j1;
	private Jugador j2;
	private Calendar fecha;
	private int dia;
	private int mes;
	private int año;
	
	public int max_mov;
	public boolean color; // false -> BLANCAS, true -> NEGRAS
	public boolean turno = false; // false -> j1, true -> j2
	public int mov_uno = 0;
	public int mov_dos = 0;
	public String ganador;
	
	//CONSTRUCTORA
	public Partida(Jugador jugador1, Jugador jugador2, Problema prob) { // DONE
		//Inicializar fecha
		fecha = Calendar.getInstance();
	    dia = fecha.get(Calendar.DATE);
	    mes = fecha.get(Calendar.MONTH);
	    año = fecha.get(Calendar.YEAR);
	    
	    j1 = jugador1;
	    j2 = jugador2;
	    
	    //Inicializar maquinas
	    if (!j1.esPersona()) {
	    	String colorString = "BLANCAS";
	    	if (color) colorString = "NEGRAS";
	    	Maquina maquina = (Maquina) j1;
	    	maquina.setColor(colorString);
	    }
	    if (!j2.esPersona()) {
	    	String colorString = "NEGRAS";
	    	if (color) colorString = "BLANCAS";
	    	Maquina maquina = (Maquina) j2;
	    	maquina.setColor(colorString);
	    }
	    
	    max_mov = prob.getMaxMovimientos();
	    color = prob.getColorAGanar();
	    this.tab = new Tablero(prob.getFEN_Tablero());
	    
	    System.out.println("Estado inicial tablero: ");
	    tab.imprimirEstadoTableroConsola();
	    
	    this.jugarsiguienteTurno();
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
	public boolean partidaAcabada() {
		if (this.ganador != null) return true;
		return false;
	}
	
	private void jugarsiguienteTurno() { //PENDIENTE
		System.out.print("Siguiente turno de ");
		if (!turno) System.out.println(j1.getNombre());
		else System.out.println(j2.getNombre());
		
		if (!turno) { // j1
			++mov_uno;
			if (!j1.esPersona()) { // j1 es maquina
				Maquina maquina = (Maquina) j1;
				Movimiento m = maquina.realizarMovimiento(this.tab.exportarFEN());
				boolean valido = this.tab.registrarMovimientoValidando(m);
				if (!valido) System.out.println("Error: Maquina (j1) ha intentado hacer un movimiento no valido.");
				tab.imprimirEstadoTableroConsola();
				turno = true;
				if (this.mov_uno >= this.max_mov) this.acabarPartida();
				else this.jugarsiguienteTurno();
			} else { // j1 es persona				
				System.out.println("Esperando movimiento de Persona:");
			}
		} else { // j2
			++mov_dos;
			if (!j2.esPersona()) { // j2 es maquina
				Maquina maquina = (Maquina) j2;
				Movimiento m = maquina.realizarMovimiento(this.tab.exportarFEN());
				boolean valido = this.tab.registrarMovimientoValidando(m);
				if (!valido) System.out.println("Error: Maquina (j1) ha intentado hacer un movimiento no valido.");
				tab.imprimirEstadoTableroConsola();
				turno = false;
				this.jugarsiguienteTurno();
			} else { // j2 es persona				
				System.out.println("Esperando movimiento de Persona:");
			}
		}
	}
	
	public boolean jugarPersona(Movimiento m) { //PENDIENTE
		if (!turno && j1.esPersona()) {
			// Mirar si es del mismo color la pieza movida 
			boolean valid = tab.registrarMovimientoValidando(m);
			if (valid) {
				turno = true;
				tab.imprimirEstadoTableroConsola();
				if (this.mov_uno >= this.max_mov) this.acabarPartida();
				else this.jugarsiguienteTurno();				
				return true;
			} else {
				return false;
			}
		} else if (turno && j2.esPersona()) {
			// Mirar si es del mismo color la pieza movida 
			boolean valid = tab.registrarMovimientoValidando(m);
			if (valid) {
				turno = false;
				tab.imprimirEstadoTableroConsola();
				this.jugarsiguienteTurno();
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public void acabarPartida() {
		String c;
    	if (!color) c = "NEGRAS";
    	else c = "BLANCAS"; 
		if(tab.esMateColor(c)) {
			ganador = j1.getNombre();
		}
		else ganador = j2.getNombre();
		System.out.println("PARTIDA ACABADA. Gana: " + ganador);
	}
	
	/* He puesto esta función en CtrlPartida
	public void cancelarPartida() {
		mov_uno = mov_dos = 0;
		turno = false;
		j1 = j2 = null;
		prob = null;
		tab = null;
	}
	*/
}
