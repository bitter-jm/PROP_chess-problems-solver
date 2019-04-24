package domain;
import java.util.Calendar;
import domain.Jugador;
import domain.Maquina;
import domain.Tablero;
import domain.Problema;

/**
 * CLASE PARTIDA, REPRESENTA EL MOMENTO DE JUEGO DEL AJEDREZ
 * @author Carla GarcíaC
 *
 */
public class Partida {
	private Problema prob;
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
	
	/**
	 * Crea un objeto Partida y lo inicializa con los jugadores y las características del problema participantes en el juego
	 * @param jugador1 del tipo Jugador representa al usuario que empieza la partida
	 * @param jugador2 del tipo Jugador representa al contrincante de jugador1
	 * @param prob del tipo Problema representa la situación incial de la partida
	 */
	public Partida(Jugador jugador1, Jugador jugador2, Problema problem) { // DONE
		//Inicializar fecha
		fecha = Calendar.getInstance();
	    dia = fecha.get(Calendar.DATE);
	    mes = fecha.get(Calendar.MONTH);
	    año = fecha.get(Calendar.YEAR);
	    
	    j1 = jugador1;
	    j2 = jugador2;
	    this.prob = problem;
	    
	    max_mov = prob.getMaxMovimientos();
	    color = prob.getColorAGanar();
	    this.tab = new Tablero(prob.getFEN_Tablero());
	    
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
	    
	    System.out.println("Estado inicial tablero: ");
	    tab.imprimirEstadoTableroConsola();
	    
	    this.jugarsiguienteTurno();
	}
		
	/**
	 * Devuelve el dia de comienzo la partida
	 * @return Integer indicando el número de día de la fecha de inicio
	 */
	public int getDia() {
		return this.dia;
	}
	/**
	 * Devuelve el mes de comienzo la partida
	 * @return Integer indicando el número de mes de la fecha de inicio
	 */
	public int getMes() {
		return this.mes;
	}
	/**
	 * Devuelve el año de comienzo la partida
	 * @return Integer indicando el año fecha de inicio
	 */
	public int getAño() {
		return this.año;
	}
	/**
	 * Devuelve el jugador1
	 * @return del jugador1 participante en la partida, del tipo Jugador, o más específicamente Persona o Máquina
	 */
	public Jugador getJugador1() {
		return this.j1;
	}
	/**
	 * Devuelve el jugador2
	 * @return del jugador2 participante en la partida, del tipo Jugador, o más específicamente Persona o Máquina
	 */
	public Jugador getJugador2() {
		return this.j2;
	}

	/**
	 * 	Comprueba si la partida ha finalizado o no
	 * @return true en el caso de que la partida haya acabado correctamente y tenga asignado un ganador, false en caso contrario
	 */
	public boolean partidaAcabada() {
		if (this.ganador != null) return true;
		return false;
	}
	/**
	 * Se encarga de pasarle el turno al siguiente jugador, actualizar los movimientos de cada uno e incluso
	 * realizar la siguiente jugada en caso de que el jugador sea Máquina
	 */
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
	/**
	 * Realiza la jugada hecha por un jugador Persona
	 * @param m recibe el movimiento que quiere realizar el jugador en esta jugada
	 * @return true cuando el movimiento es válido y se ha realizado, false en caso contrario
	 */
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
	/**
	 * Computa el jugador ganador de la partida cuando ésta finaliza
	 */
	public void acabarPartida() {
		String c;
    	if (!color) c = "NEGRAS";
    	else c = "BLANCAS"; 
		if(tab.esMateColor(c)) {
			ganador = j1.getNombre();
		}
		else ganador = j2.getNombre();
		prob.setVecesJugado(prob.getVecesJugado()+1);
		System.out.println("PARTIDA ACABADA. Gana: " + ganador);
	}
	
}
