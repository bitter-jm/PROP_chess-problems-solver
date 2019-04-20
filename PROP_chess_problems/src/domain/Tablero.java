package domain;

import domain.Movimiento;
import domain.MovimientoCompleto;
import domain.Ficha;
import java.lang.Character;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/** 
 * Representa un tablero de ajedrez
 * @author Joan Marc Pastor
 */
public class Tablero {
	//pawn = "P", knight = "N", bishop = "B", rook = "R", queen = "Q" and king = "K" //BLANCAS
	//pawn = "p", knight = "n", bishop = "b", rook = "r", queen = "q" and king = "k" //NEGRAS
	
	private Ficha[][] casillas = new Ficha[8][8];
	private List<MovimientoCompleto> historyStack = new ArrayList<MovimientoCompleto>();
	
	/**
	 * Crea un objeto Tablero vacio
	 */
	public Tablero() {} 
	
	/** 
	 * Crea un objeto Tablero a partir de una anotacion FEN
	 * @param fen String con el estado del tablero en formato FEN
	 */
	public Tablero(String fen) {
		this.cargarFEN(fen);
	}
	
	/** 
	 * Vacia el tablero eliminando todas las instancias de Fichas
	 */
	public void limpiarTablero() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.casillas[i][j] = null;
			}
		}
		this.historyStack.clear();
	}
	
	/** 
	 * Carga el estado del tablero segun anotacion FEN
	 * @param fen String en formato FEN (Forsyth Edwards Notation)
	 */
	public void cargarFEN(String fen) {
		this.limpiarTablero();
		boolean stop = false;
		int i = 0;
		int j = 0;
		for (int it = 0; it < fen.length() && !stop; ++it) {
			String c = String.valueOf(fen.charAt(it));
			if (Character.isDigit(c.charAt(0))) {
				j += Integer.parseInt(c);
			}
			else if (c.equals("/")) {
				i++;
				j = 0;
			}
			else if (c.equals(" ")) stop = true;
			else {		
				this.casillas[i][j] = Ficha.newFicha(c);
				if (j < 7) j++;
			}
		}
	}
	
	/** 
	 * Devuelve el estado del tablero en anotacion FEN
	 * @return String con el estado del tablero en formato FEN
	 */
	public String exportarFEN() {
		String fen = "";
		int contador = 0;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (this.casillas[i][j] != null) {
					if (contador > 0) fen += String.valueOf(contador);
					fen += this.casillas[i][j].getCharacter();
					contador = 0;
				}
				else {
					contador++;
					if (j == 7) {
						fen += contador;
						contador = 0;
					}
				}
			}
			if (i < 7) fen += "/";
		}
		
		return fen;
	}
	
	/** 
	 * Deshace el ultimo movimiento realizado
	 */
	public void deshacer() {
		if (this.historyStack.isEmpty()) return;
		MovimientoCompleto mc = this.historyStack.remove(this.historyStack.size()-1);
		this.casillas[mc.inicioI][mc.inicioJ] = mc.ficha;
		this.casillas[mc.finalI][mc.finalJ] = mc.fichaMatada;
	}
	
	/** 
	 * Realiza un movimiento valido en el tablero
	 * @param mov Objeto Movimiento indicando el movimiento a realizar
	 * @return Booleano indicando si se ha podido realizar el movimiento o si este era ilegal
	 */
	public boolean registrarMovimientoValidando(Movimiento mov) { //DONE
		List<Movimiento> movimientosValidos;
		if (mov.ficha.color.equals("BLANCAS")) movimientosValidos = this.posiblesMovimientos("BLANCAS");
		else movimientosValidos = this.posiblesMovimientos("NEGRAS");
		Iterator<Movimiento> iterator = movimientosValidos.iterator();
		Movimiento m = null;
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
			if (m.equals(mov)) {
				this.registrarMovimientoSinValidar(mov);
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * Realiza un movimiento en el tablero sin ser validado
	 * @param mov Objeto Movimiento indicando el movimiento a realizar
	 */
	public void registrarMovimientoSinValidar(Movimiento mov) {
		this.historyStack.add(new MovimientoCompleto(mov, this.casillas[mov.finalI][mov.finalJ]));
		this.casillas[mov.inicioI][mov.inicioJ] = null;
		this.casillas[mov.finalI][mov.finalJ] = mov.ficha;
	}
	
	/** 
	 * Indica si un jugador se encuentra en jaque.
	 * @param color Color del jugador a analizar
	 * @return Booleano indicando si el jugador <em>color</em> se encuentra en jaque
	 */
	public boolean esJaque(String color) {
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (this.casillas[i][j] != null && this.casillas[i][j].ficha.equals("k")) {
					if (color.equals("BLANCAS") && this.casillas[i][j].color.equals("BLANCAS")) return this.esJaqueEnPosicionABlancas(i, j);
					else if (color.equals("NEGRAS") && this.casillas[i][j].color.equals("NEGRAS")) return this.esJaqueEnPosicionANegras(i, j);
				}
			}
		}
		return false;
	}
	
	/** 
	 * Indica si un jugador negro se encuentra en jaque.
	 * @param i Coordenada i del rey negro
	 * @param j Coordenada j del rey negro
	 * @return Booleano indicando si rey negro en coordenadas <em>i</em>, <em>j</em> se encuentra en jaque
	 */
	public boolean esJaqueEnPosicionANegras(int i, int j) {
		// (i, j) -> Posición rey negro
		for (int ii = 0; ii < 8; ++ii) {
			for (int jj = 0; jj < 8; ++jj) {
				if (this.casillas[ii][jj] != null && this.casillas[ii][jj].color.equals("BLANCAS")) {
					List<Movimiento> movsFicha = this.casillas[ii][jj].posiblesMovimientos(ii, jj, this.casillas);
					Iterator<Movimiento> iterator = movsFicha.iterator();
					Movimiento m = null;
					while(iterator.hasNext()) {
						m = (Movimiento) iterator.next();
						if (m.finalI == i && m.finalJ == j) return true;
				    }
				}
			}
		}
		return false;
	}
	
	/** 
	 * Indica si un jugador blanco se encuentra en jaque.
	 * @param i Coordenada i del rey blanco
	 * @param j Coordenada j del rey blanco
	 * @return Booleano indicando si rey blanco en coordenadas <em>i</em>, <em>j</em> se encuentra en jaque
	 */
	public boolean esJaqueEnPosicionABlancas(int i, int j) {
		// (i, j) -> Posición rey blanco
		for (int ii = 0; ii < 8; ++ii) {
			for (int jj = 0; jj < 8; ++jj) {
				if (this.casillas[ii][jj] != null && this.casillas[ii][jj].color.equals("NEGRAS")) {
					List<Movimiento> movsFicha = this.casillas[ii][jj].posiblesMovimientos(ii, jj, this.casillas);
					Iterator<Movimiento> iterator = movsFicha.iterator();
					Movimiento m = null;
					while(iterator.hasNext()) {
						m = (Movimiento) iterator.next();
						if (m.finalI == i && m.finalJ == j) return true;
				    }
				}
			}
		}
		return false;
	}
	
	/** 
	 * Obtiene los posibles movimientos que un jugador puede realizar
	 * @param color String indicando el color del jugador a analizar
	 * @return Devuelve un List de Movimientos conteniendo todos los posibles movimientos del jugador <em>color</em>
	 */
	public List<Movimiento> posiblesMovimientos(String color) {
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		boolean kingFound = false;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (this.casillas[i][j] != null) {
					if (this.casillas[i][j].color.equals(color)) {
						if (this.casillas[i][j].ficha.equals("k")) kingFound = true;
						movimientos.addAll(this.casillas[i][j].posiblesMovimientos(i, j, this.casillas));
					}
				}
			}
		}
		
		//Si no hay rey devuelve una lista vacia
		if (!kingFound) {
			System.out.println("Advertencia: Llamando a posiblesMovimientos() pero no hay rey"); 
			return new ArrayList<Movimiento>();
		}
		
		// Elimina los movimientos ilegales (del tipo: el contrario podria matar al rey en el siguiente turno)
		Iterator<Movimiento> iterator = movimientos.iterator();
		Movimiento m = null;
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
			this.registrarMovimientoSinValidar(m);
			if (this.esJaque(color)) {
				iterator.remove();
			}
			this.deshacer();
	    }
		
		return movimientos;
	}
	
	/** 
	 * Anade una Ficha al tablero
	 * @param Ficha Ficha a colocar en el tablero
	 * @param i Coordenada i de la Ficha a colocar
	 * @param j Coordenada j de la Ficha a colocar
	 */
	public void anadirFicha(Ficha ficha, int i, int j) {
		this.casillas[i][j] = ficha;
	}
	
	/** 
	 * Limpia una casilla del tablero
	 * @param i Coordenada i de la Ficha a quitar
	 * @param j Coordenada j de la Ficha a quitar
	 */
	public void quitarFicha(int i, int j) {
		this.casillas[i][j] = null;
	}
	
	/** 
	 * Obtiene la Ficha en unas coordenadas del tablero
	 * @param i Coordenada i de la Ficha a devolver
	 * @param j Coordenada j de la Ficha a devolver
	 * @return Ficha en las coordenadas <em>i</em>, <em>j</em>
	 */
	public Ficha consultarCasilla(int i, int j) {
		return this.casillas[i][j];
	}
	
	/** 
	 * Imprime el estado del tablero por la salida estandar del sistema
	 */
	public void imprimirEstadoTableroConsola() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.casillas[i][j] == null) System.out.print("- "); 
				else System.out.print(this.casillas[i][j].getCharacter() + " "); 
			}
			System.out.print("\n");
		}
		System.out.println("");
	}
	
	/** 
	 * Obtiene una matriz con el estado de cada casilla del tablero
	 * @return Matriz Ficha[][] con el estado de cada casilla
	 */
	public Ficha[][] getCasillas() {
		return this.casillas;
	}
	
	/** 
	 * Consulta si es jaque mate para un jugador
	 * @param color String indicando que jugador analizar
	 * @return Booleano indicando si el jugador <em>color</em> se encuentra en jaque mate
	 */
	public boolean esMateColor(String color) {
		List<Movimiento> movimientos = this.posiblesMovimientos(color);
		if (movimientos.isEmpty()) return true;
		return false;
	}
	
}
