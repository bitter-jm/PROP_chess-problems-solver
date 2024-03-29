package domain;

import java.util.Calendar;
import domain.Jugador;
import domain.Maquina;
import domain.Tablero;
import domain.Problema;
import domain.CtrlPartida;
import domain.Cronometro;

/**
 * Clase partida. Representa el momento del juego
 */
public class Partida {
	private Problema prob;
	private Tablero tab;
	private Jugador j1;
	private Jugador j2;
	private Calendar fecha;
	private int dia;
	private int mes;
	private int ano;
	private Cronometro crono;
	
	public int max_mov;
	private boolean color; // false -> BLANCAS, true -> NEGRAS
	private boolean turno = false; // false -> j1, true -> j2
	public int mov_uno = 0;
	public int mov_dos = 0;
	public String ganador;
	private CtrlPartida ctrlP = CtrlPartida.getInstance();
	private CtrlEvaluador ctrlE = CtrlEvaluador.getInstance();
	private boolean actualizarCtrlParita;
	private boolean actualizarCtrlEvaluador;
	
	
	/**
	 * Crea un objeto Partida y lo inicializa con los jugadores y las caracteristicas del problema participantes en el juego
	 * @param jugador1 del tipo Jugador representa al usuario que empieza la partida
	 * @param jugador2 del tipo Jugador representa al contrincante de jugador1
	 * @param prob del tipo Problema representa la situacion incial de la partida
	 */
	public Partida(Jugador jugador1, Jugador jugador2, Problema problem, boolean actualizarCtrlPartida, boolean actualizarCtrlEvaluador) { 
	
		fecha = Calendar.getInstance();
	    dia = fecha.get(Calendar.DATE);
	    mes = fecha.get(Calendar.MONTH) + 1;
	    ano = fecha.get(Calendar.YEAR);
	    
	    this.actualizarCtrlParita = actualizarCtrlPartida;
	    this.actualizarCtrlEvaluador = actualizarCtrlEvaluador;
	    
	    this.crono = new Cronometro();
	    
	    j1 = jugador1;
	    j2 = jugador2;
	    this.prob = problem;
	    
	    max_mov = prob.getMaxMovimientos();
	    color = prob.getColorAGanar();
	    this.tab = new Tablero(prob.getFEN_Tablero());
	    
	
	    if (!(j1 instanceof Persona)) {
	    	String colorString = "BLANCAS";
	    	if (color) colorString = "NEGRAS";
	    	Maquina maquina = (Maquina) j1;
	    	maquina.setColor(colorString);
	    }
	    if (!(j2 instanceof Persona)) {
	    	String colorString = "NEGRAS";
	    	if (color) colorString = "BLANCAS";
	    	Maquina maquina = (Maquina) j2;
	    	maquina.setColor(colorString);
	    }
	    
	    System.out.println("Estado inicial tablero: ");
	    tab.imprimirEstadoTableroConsola();
	    String c = "NECRAS";
	    if (this.color) c = "BLANCAS";
	    if (this.actualizarCtrlParita) ctrlP.tableroModificado(this.tab.exportarFEN(), this.j1.getNombre(), c);
	    
	    this.jugarSiguienteTurno();
	}
	
	/**
	 * Se encarga de pasarle el turno al siguiente jugador, actualizar los movimientos de cada uno e incluso
	 * realizar la siguiente jugada en caso de que el jugador sea Maquina
	 */
	public void jugarSiguienteTurno() { 
		String colorS="BLANCAS";
		String colorSOpuesto="NEGRAS";
		if (color && !turno) {
			colorS="NEGRAS";
			colorSOpuesto="BLANCAS";
		}
		else if (!color && turno) {
			colorS="NEGRAS";
			colorSOpuesto="BLANCAS";
		}
		
		System.out.print("Siguiente turno de ");
		if (!turno) System.out.println(j1.getNombre() + " ("+colorS+")");
		else System.out.println(j2.getNombre() + " ("+colorS+")");
		
		if (!turno) { // j1
			++mov_uno;
			if (!(j1 instanceof Persona)) { // j1 es maquina
				Maquina maquina = (Maquina) j1;
				Movimiento m = maquina.realizarMovimiento(this.tab.exportarFEN());
				boolean valido = this.tab.registrarMovimientoValidando(m);
				if (!valido) System.out.println("Error: Maquina (j1) ha intentado hacer un movimiento no valido.");
				else {
					turno = true;
					tab.imprimirEstadoTableroConsola(); 	    
					if (this.actualizarCtrlParita) ctrlP.tableroModificado(this.tab.exportarFEN(), this.j2.getNombre(), colorS);	
				}
				
				if (this.mov_uno >= this.max_mov) this.acabarPartida();
				else if (this.tab.esMateColor(colorSOpuesto)) this.acabarPartida();
				else this.jugarSiguienteTurno();
			} else { // j1 es persona			
				this.crono.start(); // TODO Added
				System.out.println("Esperando movimiento de Persona:");
			}
		} else { // j2
			++mov_dos;
			if (!(j2 instanceof Persona)) { // j2 es maquina
				Maquina maquina = (Maquina) j2;
				Movimiento m = maquina.realizarMovimiento(this.tab.exportarFEN());
				boolean valido = this.tab.registrarMovimientoValidando(m);
				if (!valido) System.out.println("Error: Maquina (j2) ha intentado hacer un movimiento no valido.");
				else {
					turno = false;
					tab.imprimirEstadoTableroConsola();
					if (this.actualizarCtrlParita) ctrlP.tableroModificado(this.tab.exportarFEN(), this.j1.getNombre(), colorS);
				}
				
				if (this.tab.esMateColor(colorSOpuesto)) this.acabarPartida();
				else this.jugarSiguienteTurno();
			} else { // j2 es persona				
				System.out.println("Esperando movimiento de Persona:");
	
			}
		}
	}
	
	/**
	 * Realiza la jugada hecha por un jugador Persona
	 * @param m recibe el movimiento que quiere realizar el jugador en esta jugada
	 * @return true cuando el movimiento es valido y se ha realizado, false en caso contrario
	 */
	public boolean jugarPersona(Movimiento m) {
		String colorS = "NEGRAS";
		String colorOpuesto = "BLANCAS";
		if (color && !turno) {
			colorS="BLANCAS";
			colorOpuesto = "NEGEAS";
		}
		else if (!color && turno) {
			colorS="BLANCAS";
			colorOpuesto = "NEGEAS";
		}
		if (!turno && j1 instanceof Persona) {
			this.crono.pause();// TODO: Pausar cronometro
			// Mirar si es del mismo color la pieza movida 
			boolean valid = tab.registrarMovimientoValidando(m);
			if (valid) {
				turno = true;
				tab.imprimirEstadoTableroConsola(); 
				if (this.actualizarCtrlParita) ctrlP.tableroModificado(this.tab.exportarFEN(), this.j2.getNombre(), colorOpuesto);
				if (this.mov_uno >= this.max_mov) this.acabarPartida();
				else if (this.tab.esMateColor(colorS)) this.acabarPartida();
				else this.jugarSiguienteTurno();				
				return true;
			} else {
				return false;
			}
		} else if (turno && j2 instanceof Persona) {
			// Mirar si es del mismo color la pieza movida 
			boolean valid = tab.registrarMovimientoValidando(m);
			if (valid) {
				turno = false;
				tab.imprimirEstadoTableroConsola(); 
				if (this.actualizarCtrlParita) ctrlP.tableroModificado(this.tab.exportarFEN(), this.j1.getNombre(), colorOpuesto);
				if (this.tab.esMateColor(colorS)) this.acabarPartida();
				else this.jugarSiguienteTurno();
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Computa el jugador ganador de la partida cuando esta finaliza
	 */
	public void acabarPartida() {
		int timeJ1 = -1; 
		String colorS = "BLANCAS";
		String c;
    	if (!color) c = "NEGRAS";
    	else c = "BLANCAS"; 
		if(tab.esMateColor(c)) {
			if (j1 instanceof Persona && (!(j2 instanceof Persona))) timeJ1 = this.calcularPuntuacion(this.crono.getTime());
			else timeJ1 = -1;
			// TODO: Calcular puntuacion ranking
			ganador = j1.getNombre();
			if (color) colorS = "NEGRAS";
			if (this.actualizarCtrlParita) ctrlP.partidaFinaliza(this.j1.getNombre(), colorS, timeJ1, this.prob.getNombre());
			if (this.actualizarCtrlEvaluador) ctrlE.setGanador("Jugador 1");
			System.out.println("PARTIDA ACABADA. Gana Jugador1: " + ganador + " ("+colorS+")" );
		}
		else {
			ganador = j2.getNombre();
			if (!color) colorS = "NEGRAS";
			if (this.actualizarCtrlParita) ctrlP.partidaFinaliza(this.j2.getNombre(), colorS, timeJ1, this.prob.getNombre());
			if (this.actualizarCtrlEvaluador) ctrlE.setGanador("Jugador 2");
			System.out.println("PARTIDA ACABADA. Gana Jugador2: " + ganador + " ("+colorS+")" );
		}
	}
	
	private int calcularPuntuacion(int time) {
		if (time >= 1800) return 1; // Ha tardado mas de media hora
		return 1800 - time;		
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
	 * Devuelve el dia de comienzo la partida
	 * @return Integer indicando el n�mero de dia de la fecha de inicio
	 */
	public int getDia() {
		return this.dia;
	}
	
	/**
	 * Devuelve el mes de comienzo la partida
	 * @return Integer indicando el n�mero de mes de la fecha de inicio
	 */
	public int getMes() {
		return this.mes;
	}
	
	/**
	 * Devuelve el ano de comienzo la partida
	 * @return Integer indicando el ano fecha de inicio
	 */
	public int getAno() {
		return this.ano;
	}
	
	/**
	 * Devuelve el jugador1
	 * @return del jugador1 participante en la partida, del tipo Jugador, o mas especificamente Persona o Maquina
	 */
	public Jugador getJugador1() {
		return this.j1;
	}
	
	/**
	 * Devuelve el jugador2
	 * @return del jugador2 participante en la partida, del tipo Jugador, o m�s especificamente Persona o M�quina
	 */
	public Jugador getJugador2() {
		return this.j2;
	}

	/**
	 * Devuelve la situacion actual del juego
	 * @return tab del tipo Tablero con el estado actual de la partida
	 */
	public Tablero getTablero() {
		return this.tab;
	}
	
	public String getCurrentFEN() {
		return this.tab.exportarFEN();
	}
}
