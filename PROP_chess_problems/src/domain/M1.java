package domain;

import domain.Movimiento;
import domain.Tablero;
import stubs.Maquina;
import java.util.List;
import java.util.Iterator;

/** 
 * Representa la M�quina 1
 * @author Joan Marc Pastor
 */
public class M1 extends Maquina {
	
	private int maxDepth = 5;
	private String color, colorOpuesto;
	
	/**
	 * Calcula cual es el mejor movimiento a realizar
	 * @param fen String con el estado del tablero en formato FEN
	 * @return Devuelve el Movimiento m�s �ptimo 
	 */
	public Movimiento realizarMovimiento(String fen) { //DONE
		Tablero t = new Tablero(fen);
	    List<Movimiento> movimientosPosibles = t.posiblesMovimientos(this.color);
	    int mejorValor = -10000;
	    Movimiento mejorMovimiento = new Movimiento(0,0,0,0,new Ficha());
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
	 * Calcula la heur�stica del estado actual del tablero
	 * @param casillas Ficha[][] con el contenido de las casillas a analizar
	 * @return Integer con el valor de la heur�stica
	 */
	private int evaluarTablero(Ficha[][] casillas) { //DONE
		int value = 0;
		int sign = 1;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (casillas[i][j] != null) {					
					if (casillas[i][j].color.equals("BLANCAS")) sign = 1;
					else sign = -1;
					if (casillas[i][j].ficha.equals("p")) value += 10 * sign; // PAWN
					else if (casillas[i][j].ficha.equals("n")) value += 30 * sign; // KNIGHT
					else if (casillas[i][j].ficha.equals("b")) value += 30 * sign; // BISHOP
					else if (casillas[i][j].ficha.equals("r")) value += 50 * sign; // ROOK
					else if (casillas[i][j].ficha.equals("q")) value += 90 * sign; // QUEEN
					else if (casillas[i][j].ficha.equals("k")) value += 900 * sign; // KING
				}
			}
		}
		return value;
	}
	
	/**
	 * Miminiza la p�rdida m�xima entre los movimientos posibles
	 * @param t Tablero conteniendo la informaci�n de las casillas
	 * @param depth Integer con la profundidad actual de b�squeda
	 * @param max Boolean indicando si se quiere maximizar o miminizar la p�rdida
	 * @param alpha Integer conteniendo el <em>alpha<em> acumulado de previas iteraciones
	 * @param beta Integer conteniendo la <em>beta<em> acumulado de previas ejecuciones
	 * @return Devuelve la heur�stica m�xima o m�nima obtenible seg�n <em>max</em>
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
		} else {
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
	 * Especifica a que profundidad quiere evaluar el tablero
	 * @param depth Integer indicando la profundidad de b�squeda
	 */
	public void setDepth(int depth) { //DONE
		this.maxDepth = depth;
	} 
	
	/**
	 * Especifica en que lado va a jugar la m�quina
	 * @param color String indicando el color del jugador
	 */
	public void setColor(String color) { //DONE
		this.color = color;
		if (color.equals("BLANCAS")) this.colorOpuesto = "NEGRAS";
		else this.colorOpuesto = "BLANCAS";
	} 
	
	/**
	 * Devuelve el valor m�ximo
	 * @param a Integer 1 a analizar
	 * @param b Integer 2 a analizar
	 * @return Integer con el valor m�ximo entre <em>a</em> y <em>b</em>
	 */
	private int max(int i, int j) { //DONE
		if (i >= j) return i;
		else return j;
	}
	
	/**
	 * Devuelve el valor m�nimo
	 * @param a Integer 1 a analizar
	 * @param b Integer 2 a analizar
	 * @return Integer con el valor m�nimo entre <em>a</em> y <em>b</em>
	 */
	private int min(int a, int b) { //DONE
		if (a >= b) return b;
		else return a;
	}
	
}
