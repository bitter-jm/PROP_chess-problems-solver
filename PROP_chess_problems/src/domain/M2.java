package domain;

import domain.Movimiento;
import domain.Tablero;
import domain.Maquina;
import java.util.List;
import java.util.Iterator;

/** 
 * Representa la Maquina 2
 */
public class M2 extends Maquina {
	
	// Piece-Square Tables
	private int[][] PSPawn = {
			{ 0, 0, 0, 0, 0, 0, 0, 0},
			{ 5, 5, 5, 5, 5, 5, 5, 5},
			{ 1, 1, 2, 3, 3, 2, 1, 1},
			{ 1, 1, 1, 3, 3, 1, 1, 1},
			{ 0, 0, 0, 2, 2, 0, 0, 0},
			{ 1,-1,-1, 0, 0,-1,-1, 1},
			{ 1, 1, 1,-2,-2, 1, 1, 1},
			{ 0, 0, 0, 0, 0, 0, 0, 0}};
	
	private int[][] PSKnight = {
			{-5,-4,-3,-3,-3,-3,-4,-5},
	        {-4,-2, 0, 0, 0, 0,-2,-4},
	        {-3, 0, 1, 2, 2, 1, 0,-3},
	        {-3, 1, 1, 2, 2, 2, 1,-3},
	        {-3, 0, 2, 2, 2, 2, 0,-3},
	        {-3, 1, 1, 2, 2, 1, 1,-3},
	        {-4,-2, 0, 1, 0, 0,-2,-4},
	        {-5,-4,-3,-3,-3,-3,-4,-5}};
	
	private int[][] PSBishop = {
			{-2,-1,-1,-1,-1,-1,-1,-2},
			{-1, 0, 0, 0, 0, 0, 0,-1},
			{-1, 0, 1, 1, 1, 1, 0,-1},
			{-1, 1, 1, 1, 1, 1, 1,-1},
			{-1, 0, 1, 1, 1, 1, 0,-1},
			{-1, 1, 1, 1, 1, 1, 1,-1},
			{-1, 1, 0, 0, 0, 0, 1,-1},
			{-2,-1,-1,-1,-1,-1,-1,-2}};
	
	private int[][] PSRook = {
			{ 0, 0, 0, 0, 0, 0, 0, 0},
			{ 1, 1, 1, 1, 1, 1, 1, 1},
			{-1, 0, 0, 0, 0, 0, 0,-1},
			{-1, 0, 0, 0, 0, 0, 0,-1},
			{-1, 0, 0, 0, 0, 0, 0,-1},
			{-1, 0, 0, 0, 0, 0, 0,-1},
			{-1, 0, 0, 0, 0, 0, 0,-1},
			{ 0, 0, 0, 0, 1, 0, 0, 0}};
	
	private int[][] PSQueen = {
			{-2,-1,-1,-1,-1,-1,-1,-2},
			{-1, 0, 0, 0, 0, 0, 0,-1},
			{-1, 0, 1, 1, 1, 1, 0,-1},
			{-1, 0, 1, 1, 1, 1, 0,-1},
			{ 0, 0, 1, 1, 1, 1, 0,-1},
			{-1, 1, 1, 1, 1, 1, 0,-1},
			{-1, 0, 1, 0, 0, 0, 0,-1},
			{-2,-1,-1,-1,-1,-1,-1,-2}};
	
	private int[][] PSKing = {
			{-3,-4,-4,-5,-5,-4,-4,-3},
			{-3,-4,-4,-5,-5,-4,-4,-3},
			{-3,-4,-4,-5,-5,-4,-4,-3},
			{-3,-4,-4,-5,-5,-4,-4,-3},
			{-2,-3,-3,-4,-4,-3,-3,-2},
			{-1,-2,-2,-2,-2,-2,-2,-1},
			{ 2, 2, 0, 0, 0, 0, 2, 2},
			{ 2, 3, 1, 0, 0, 1, 3, 2}};
	
	/**
	 * Crea un objeto M2
	 */
	public M2() {
		this.nombre = "MAQUINA2";
	}
	
	/**
	 * Calcula cual es el mejor movimiento a realizar
	 * @param fen String con el estado del tablero en formato FEN
	 * @return Devuelve el Movimiento mas optimo 
	 */
	@Override
	public Movimiento realizarMovimiento(String fen) {
		Tablero t = new Tablero(fen);
	    List<Movimiento> movimientosPosibles = t.posiblesMovimientos(this.color);
	    int mejorValor = -10000;
	    Movimiento mejorMovimiento = null;
	    Iterator<Movimiento> iterator = movimientosPosibles.iterator();
		Movimiento m = null;
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
			t.registrarMovimientoSinValidar(m);
			int valor = this.minimax(t, 1, false, -10000, 10000);
			if (valor > mejorValor) {
				mejorMovimiento = m;
				mejorValor = valor;
			}
			t.deshacer();
		}
		return mejorMovimiento;
	}
	
	/**
	 * Calcula la heuristica del estado actual del tablero
	 * @param casillas Ficha[][] con el contenido de las casillas a analizar
	 * @return Integer con el valor de la heuristica
	 */
	private int evaluarTablero(Ficha[][] casillas) {
		int value = 0;
		int sign = 1;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (casillas[i][j] != null) {		
					int iPS = i;
					int jPS = j;
					if (casillas[i][j].color.equals("BLANCAS")) {
						sign = 1;
					}
					else {
						sign = -1;
						iPS = 7-i;
						jPS = 7-j;
					}
					if (casillas[i][j].ficha.equals("p")) value += 10 * sign + PSPawn[iPS][jPS] * sign; // PAWN
					else if (casillas[i][j].ficha.equals("n")) value += 30 * sign + PSKnight[iPS][jPS]; // KNIGHT
					else if (casillas[i][j].ficha.equals("b")) value += 30 * sign + PSBishop[iPS][jPS] * sign; // BISHOP
					else if (casillas[i][j].ficha.equals("r")) value += 50 * sign + PSPawn[iPS][jPS] * sign; // ROOK
					else if (casillas[i][j].ficha.equals("q")) value += 90 * sign + PSQueen[iPS][jPS]; // QUEEN
					else if (casillas[i][j].ficha.equals("k")) value += 900 * sign + PSKing[iPS][jPS] * sign; // KING
				}
			}
		}
		return value;
	}
	
	/**
	 * Miminiza la perdida maxima entre los movimientos posibles
	 * @param t Tablero conteniendo la informacion de las casillas
	 * @param depth Integer con la profundidad actual de busqueda
	 * @param max Boolean indicando si se quiere maximizar o miminizar la perdida
	 * @param alpha Integer conteniendo el <em>alpha<em> acumulado de previas iteraciones
	 * @param beta Integer conteniendo la <em>beta<em> acumulado de previas ejecuciones
	 * @return Devuelve la heuristica maxima o minima obtenible segun <em>max</em>
	 */
	private int minimax(Tablero t, int depth, boolean max, int alpha, int beta) { //DONE
		if (depth == this.maxDepth) { 
			return this.evaluarTablero(t.getCasillas());
		}
		if (max) {			
			List<Movimiento> movimientosPosibles = t.posiblesMovimientos(this.color);
			int mejorValor = -9999;
			Iterator<Movimiento> iterator = movimientosPosibles.iterator();
			Movimiento m = null;
			while(iterator.hasNext()) {
				m = (Movimiento) iterator.next();
				t.registrarMovimientoSinValidar(m);
				mejorValor = max(mejorValor, this.minimax(t, depth+1, !max, alpha, beta));
				t.deshacer();
				alpha = max(mejorValor, alpha);
				if (beta <= alpha) {
	                return mejorValor;
	            }
			}
			return mejorValor;
		} else { // if min
			List<Movimiento> movimientosPosibles = t.posiblesMovimientos(this.colorOpuesto);
			int mejorValor = 9999;
			Iterator<Movimiento> iterator = movimientosPosibles.iterator();
			Movimiento m = null;
			while(iterator.hasNext()) {
				m = (Movimiento) iterator.next();
				t.registrarMovimientoSinValidar(m);
				mejorValor = min(mejorValor, this.minimax(t, depth+1, !max, alpha, beta));
				t.deshacer();
				beta = min(mejorValor, beta);
				if (beta <= alpha) {
	                return mejorValor;
	            }
			}
			return mejorValor;
		}
	}

	/**
	 * Devuelve el valor maximo
	 * @param a Integer 1 a analizar
	 * @param b Integer 2 a analizar
	 * @return Integer con el valor maximo entre <em>a</em> y <em>b</em>
	 */
	private int max(int i, int j) {
		if (i >= j) return i;
		else return j;
	}
	
	/**
	 * Devuelve el valor minimo
	 * @param a Integer 1 a analizar
	 * @param b Integer 2 a analizar
	 * @return Integer con el valor minimo entre <em>a</em> y <em>b</em>
	 */
	private int min(int a, int b) {
		if (a >= b) return b;
		else return a;
	}
	
}