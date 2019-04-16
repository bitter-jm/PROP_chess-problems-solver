package domain;

import domain.Movimiento;
import domain.Tablero;
import stubs.Maquina;
import java.util.List;
import java.util.Iterator;

public class M1 extends Maquina {
	
	private int maxDepth = 5;
	private String color, colorOpuesto;
	
	public Movimiento realizarMovimiento(String fen) { 
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
	
	private int evaluarTablero(Ficha[][] casillas) { // DONE
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
	
	private int minimax(Tablero t, int depth, boolean max, int alpha, int beta) {
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
	
	public void setDepth(int depth) { // DONE
		this.maxDepth = depth;
	} 
	
	public void setColor(String color) { // DONE
		this.color = color;
		if (color.equals("BLANCAS")) this.colorOpuesto = "NEGRAS";
		else this.colorOpuesto = "BLANCAS";
	} 
	
	private int max(int i, int j) { // DONE
		if (i >= j) return i;
		else return j;
	}
	
	private int min(int i, int j) { // DONE
		if (i >= j) return j;
		else return i;
	}
	
}
