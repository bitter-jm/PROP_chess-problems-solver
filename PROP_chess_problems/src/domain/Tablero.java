package domain;

import domain.Movimiento;
import domain.MovimientoCompleto;
import java.lang.Character;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Tablero {
	//pawn = "P", knight = "N", bishop = "B", rook = "R", queen = "Q" and king = "K" //BLANCAS
	//pawn = "p", knight = "n", bishop = "b", rook = "r", queen = "q" and king = "k" //NEGRAS
	
	private String[][] casillas = new String[8][8];
	private List<MovimientoCompleto> historyStack = new ArrayList<MovimientoCompleto>();
	
	public Tablero() {} //DONE
	
	public Tablero(String fen) { //DONE
		this.cargarFEN(fen);
	}
	
	public void limpiarTablero() { //DONE
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.casillas[i][j] = null;
			}
		}
		this.historyStack.clear();
	}
	
	public void cargarFEN(String fen) { //DONE
		this.limpiarTablero();
		int i = 0;
		int j = 0;
		for (int it = 0; it < fen.length(); ++it) {
			String c = String.valueOf(fen.charAt(it));
			if (Character.isDigit(c.charAt(0))) {
				j += Integer.parseInt(c);
			}
			else if (c.equals("/")) {
				i++;
				j = 0;
			}
			else {		
				this.casillas[i][j] = c;
				if (j < 7) j++;
			}
		}
	}
	
	public String exportarFEN() { //DONE
		String fen = "";
		int contador = 0;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (this.casillas[i][j] != null) {
					if (contador > 0) fen += String.valueOf(contador);
					fen += this.casillas[i][j];
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
	
	public void deshacer() { //DONE
		if (this.historyStack.isEmpty()) return;
		MovimientoCompleto mc = this.historyStack.remove(this.historyStack.size()-1);
		this.casillas[mc.inicioI][mc.inicioJ] = mc.ficha;
		this.casillas[mc.finalI][mc.finalJ] = mc.fichaMatada;
	}
	
	public boolean registrarMovimientoValidando(Movimiento mov) { //DONE
		List<Movimiento> movimientosValidos;
		if (Character.isUpperCase(mov.ficha.charAt(0))) movimientosValidos = this.posiblesMovimientos("BLANCAS");
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
	
	public void registrarMovimientoSinValidar(Movimiento mov) { //DONE
		this.historyStack.add(new MovimientoCompleto(mov, this.casillas[mov.finalI][mov.finalJ]));
		this.casillas[mov.inicioI][mov.inicioJ] = null;
		this.casillas[mov.finalI][mov.finalJ] = mov.ficha;
	}
	
	public boolean esJaque(String color) { //DONE
		String find = "";
		if (color.equals("BLANCAS")) find = "K";
		else find = "k";
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (this.casillas[i][j] != null && this.casillas[i][j].equals(find)) {
					if (color.equals("BLANCAS")) return this.esJaqueEnPosicionABlancas(i, j);
					else return this.esJaqueEnPosicionANegras(i, j);
				}
			}
		}
		return false;
	}
	
	public boolean esJaqueEnPosicionANegras(int i, int j) { //DONE
		// (i, j) -> Posición rey negro
		boolean stop = false;
		int ii = i;
		int jj = j;
		while (ii > 0 && jj > 0 && !stop) {
			ii--; jj--;
			if (this.casillas[ii][jj] != null) {
				if (this.casillas[ii][jj].equals("k")) {}
				else if (this.casillas[ii][jj].equals("Q") || this.casillas[ii][jj].equals("B")) return true;
				else if (i-ii == 1 && j-jj == 1 && this.casillas[ii][jj].equals("K")) return true;
				else stop = true;
			}
		}
		
		stop = false;
		ii = i;
		jj = j;
		while (ii > 0 && jj < 7 && !stop) {
			ii--; jj++;
			if (this.casillas[ii][jj] != null) {
				if (this.casillas[ii][jj].equals("k")) {}
				else if (this.casillas[ii][jj].equals("Q") || this.casillas[ii][jj].equals("B")) return true;
				else if (i-ii == 1 && jj-j == 1 && this.casillas[ii][jj].equals("K")) return true;
				else stop = true;
			}
		}
		
		stop = false;
		ii = i;
		jj = j;
		while (ii < 7 && jj < 7 && !stop) {
			ii++; jj++;
			if (this.casillas[ii][jj] != null) {
				if (this.casillas[ii][jj].equals("k")) {}
				else if (this.casillas[ii][jj].equals("Q") || this.casillas[ii][jj].equals("B")) return true;
				else if (ii-i == 1 && jj-j == 1 && (this.casillas[ii][jj].equals("K") || this.casillas[ii][jj].equals("P"))) return true;
				else stop = true;
			}
		}
		
		stop = false;
		ii = i;
		jj = j;
		while (ii < 7 && jj > 0 && !stop) {
			ii++; jj--;
			if (this.casillas[ii][jj] != null) {
				if (this.casillas[ii][jj].equals("k")) {}
				else if (this.casillas[ii][jj].equals("Q") || this.casillas[ii][jj].equals("B")) return true;
				else if (ii-i == 1 && j-jj == 1 && (this.casillas[ii][jj].equals("K") || this.casillas[ii][jj].equals("P"))) return true;
				else stop = true;
			}
		}
		
		stop = false;
		ii = i;
		while (ii > 0 && !stop) {
			ii--;
			if (this.casillas[ii][j] != null) {
				if (this.casillas[ii][j].equals("k")) {}
				else if (this.casillas[ii][j].equals("Q") || this.casillas[ii][j].equals("R")) return true;
				else if (i-ii == 1 && this.casillas[ii][j].equals("K")) return true;
				else stop = true;
			}
		}
		
		stop = false;
		ii = i;
		while (ii < 7 && !stop) {
			ii++;
			if (this.casillas[ii][j] != null) {
				if (this.casillas[ii][j].equals("k")) {}
				else if (this.casillas[ii][j].equals("Q") || this.casillas[ii][j].equals("R")) return true;
				else if (ii-i == 1 && this.casillas[ii][j].equals("K")) return true;
				else stop = true;
			}
		}
		
		stop = false;
		jj = j;
		while (jj > 0 && !stop) {
			jj--;
			if (this.casillas[i][jj] != null) {
				if (this.casillas[i][jj].equals("k")) {}
				else if (this.casillas[i][jj].equals("Q") || this.casillas[i][jj].equals("R")) return true;
				else if (j-jj == 1 && this.casillas[i][jj].equals("K")) return true;
				else stop = true;
			}
		}
		
		stop = false;
		jj = j;
		while (jj < 7 && !stop) {
			jj++;
			if (this.casillas[i][jj] != null) {
				if (this.casillas[i][jj].equals("k")) {}
				else if (this.casillas[i][jj].equals("Q") || this.casillas[i][jj].equals("R")) return true;
				else if (jj - j == 1 && this.casillas[i][jj].equals("K")) return true;
				else stop = true;
			}
		}
		
		if (i-2 >= 0 && j+1 <= 7 && this.casillas[i-2][j+1]!=null && this.casillas[i-2][j+1].equals("N")) return true;
		if (i-1 >= 0 && j+2 <= 7 && this.casillas[i-1][j+2]!=null && this.casillas[i-1][j+2].equals("N")) return true;
		if (i+1 <= 7 && j+2 <= 7 && this.casillas[i+1][j+2]!=null && this.casillas[i+1][j+2].equals("N")) return true;
		if (i+2 <= 7 && j+1 <= 7 && this.casillas[i+2][j+1]!=null && this.casillas[i+2][j+1].equals("N")) return true;
		if (i+2 <= 7 && j-1 >= 0 && this.casillas[i+2][j-1]!=null && this.casillas[i+2][j-1].equals("N")) return true;
		if (i+1 <= 7 && j-2 >= 0 && this.casillas[i+1][j-2]!=null && this.casillas[i+1][j-2].equals("N")) return true;
		if (i-1 >= 0 && j-2 >= 0 && this.casillas[i-1][j-2]!=null && this.casillas[i-1][j-2].equals("N")) return true;
		if (i-2 >= 0 && j-1 >= 0 && this.casillas[i-2][j-1]!=null && this.casillas[i-2][j-1].equals("N")) return true;
		
		return false;
	}
	
	public boolean esJaqueEnPosicionABlancas(int i, int j) { //DONE 
		// (i, j) -> Posición rey blanco
		boolean stop = false;
		int ii = i;
		int jj = j;
		while (ii > 0 && jj > 0 && !stop) {
			ii--; jj--;
			if (this.casillas[ii][jj] != null) {
				if (this.casillas[ii][jj].equals("K")) {}
				else if (this.casillas[ii][jj].equals("q") || this.casillas[ii][jj].equals("b")) return true;
				else if (i-ii == 1 && j-jj == 1 && (this.casillas[ii][jj].equals("k") || this.casillas[ii][jj].equals("p"))) return true;
				else stop = true;
			}
		}
		
		stop = false;
		ii = i;
		jj = j;
		while (ii > 0 && jj < 7 && !stop) {
			ii--; jj++;
			if (this.casillas[ii][jj] != null) {
				if (this.casillas[ii][jj].equals("K")) {}
				else if (this.casillas[ii][jj].equals("q") || this.casillas[ii][jj].equals("b")) return true;
				else if (i-ii == 1 && jj-j == 1 && (this.casillas[ii][jj].equals("k") || this.casillas[ii][jj].equals("p"))) return true;
				else stop = true;
			}
		}
		
		stop = false;
		ii = i;
		jj = j;
		while (ii < 7 && jj < 7 && !stop) {
			ii++; jj++;
			if (this.casillas[ii][jj] != null) {
				if (this.casillas[ii][jj].equals("K")) {}
				else if (this.casillas[ii][jj].equals("q") || this.casillas[ii][jj].equals("b")) return true;
				else if (ii-i == 1 && jj-j == 1 && this.casillas[ii][jj].equals("k")) return true;
				else stop = true;
			}
		}
		
		stop = false;
		ii = i;
		jj = j;
		while (ii < 7 && jj > 0 && !stop) {
			ii++; jj--;
			if (this.casillas[ii][jj] != null) {
				if (this.casillas[ii][jj].equals("K")) {}
				else if (this.casillas[ii][jj].equals("q") || this.casillas[ii][jj].equals("b")) return true;
				else if (ii-i == 1 && j-jj == 1 && this.casillas[ii][jj].equals("k")) return true;
				else stop = true;
			}
		}
		
		stop = false;
		ii = i;
		while (ii > 0 && !stop) {
			ii--;
			if (this.casillas[ii][j] != null) {
				if (this.casillas[ii][j].equals("K")) {}
				else if (this.casillas[ii][j].equals("q") || this.casillas[ii][j].equals("r")) return true;
				else if (i-ii == 1 && this.casillas[ii][j].equals("k")) return true;
				else stop = true;
			}
		}
		
		stop = false;
		ii = i;
		while (ii < 7 && !stop) {
			ii++;
			if (this.casillas[ii][j] != null) {
				if (this.casillas[ii][j].equals("K")) {}
				else if (this.casillas[ii][j].equals("q") || this.casillas[ii][j].equals("r")) return true;
				else if (ii-i == 1 && this.casillas[ii][j].equals("k")) return true;
				else stop = true;
			}
		}
		
		stop = false;
		jj = j;
		while (jj > 0 && !stop) {
			jj--;
			if (this.casillas[i][jj] != null) {
				if (this.casillas[i][jj].equals("K")) {}
				else if (this.casillas[i][jj].equals("q") || this.casillas[i][jj].equals("r")) return true;
				else if (j-jj == 1 && this.casillas[i][jj].equals("k")) return true;
				else stop = true;
			}
		}
		
		stop = false;
		jj = j;
		while (jj < 7 && !stop) {
			jj++;
			if (this.casillas[i][jj] != null) {
				if (this.casillas[i][jj].equals("K")) {}
				else if (this.casillas[i][jj].equals("q") || this.casillas[i][jj].equals("r")) return true;
				else if (jj - j == 1 && this.casillas[i][jj].equals("k")) return true;
				else stop = true;
			}
		}
		
		if (i-2 >= 0 && j+1 <= 7 && this.casillas[i-2][j+1]!=null && this.casillas[i-2][j+1].equals("n")) return true;
		if (i-1 >= 0 && j+2 <= 7 && this.casillas[i-1][j+2]!=null && this.casillas[i-1][j+2].equals("n")) return true;
		if (i+1 <= 7 && j+2 <= 7 && this.casillas[i+1][j+2]!=null && this.casillas[i+1][j+2].equals("n")) return true;
		if (i+2 <= 7 && j+1 <= 7 && this.casillas[i+2][j+1]!=null && this.casillas[i+2][j+1].equals("n")) return true;
		if (i+2 <= 7 && j-1 >= 0 && this.casillas[i+2][j-1]!=null && this.casillas[i+2][j-1].equals("n")) return true;
		if (i+1 <= 7 && j-2 >= 0 && this.casillas[i+1][j-2]!=null && this.casillas[i+1][j-2].equals("n")) return true;
		if (i-1 >= 0 && j-2 >= 0 && this.casillas[i-1][j-2]!=null && this.casillas[i-1][j-2].equals("n")) return true;
		if (i-2 >= 0 && j-1 >= 0 && this.casillas[i-2][j-1]!=null && this.casillas[i-2][j-1].equals("n")) return true;
		
		return false;
	}
	
	public List<Movimiento> posiblesMovimientos(String color) { //DONE
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (this.casillas[i][j] != null) {
					if (color.equals("BLANCAS") && Character.isUpperCase(this.casillas[i][j].charAt(0))) {
						this.anadirPosiblesMovimientosPieza(this.casillas[i][j], i, j, movimientos);
					}
					else if (color.equals("NEGRAS") && !Character.isUpperCase(this.casillas[i][j].charAt(0))) {
						this.anadirPosiblesMovimientosPieza(this.casillas[i][j], i, j, movimientos);
					}
				}
			}
		}
		
		// Elimina los movimientos ilegales (del tipo: el contrario podria matar al rey en el siguiente turno)
		//String FENinicial = this.exportarFEN();
		Iterator<Movimiento> iterator = movimientos.iterator();
		Movimiento m = null;
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
			this.registrarMovimientoSinValidar(m);
			if (this.esJaque(color)) {
				iterator.remove();
			}
			this.deshacer();
			//this.cargarFEN(FENinicial);
	    }
		
		return movimientos;
	}
	
	private void anadirPosiblesMovimientosPieza(String pieza, int i, int j, List<Movimiento> movimientos) { //DONE
		switch (pieza) {
		case "P":
			if (i == 0) return;
			if (this.casillas[i-1][j] == null) {
				movimientos.add(new Movimiento(i, j, i-1, j, pieza));
			}
			if (j>0 && this.casillas[i-1][j-1] != null && !Character.isUpperCase(this.casillas[i-1][j-1].charAt(0))) {
				movimientos.add(new Movimiento(i, j, i-1, j-1, pieza));
			}
			if (j<7 && this.casillas[i-1][j+1] != null && !Character.isUpperCase(this.casillas[i-1][j+1].charAt(0))) {
				movimientos.add(new Movimiento(i, j, i-1, j+1, pieza));
			}
			break;
		case "p":
			if (i == 7) return;
			if (this.casillas[i+1][j] == null) {
				movimientos.add(new Movimiento(i, j, i+1, j, pieza));
			}
			if (j>0 && this.casillas[i+1][j-1] != null && !Character.isUpperCase(this.casillas[i+1][j-1].charAt(0))) {
				movimientos.add(new Movimiento(i, j, i+1, j-1, pieza));
			}
			if (j<7 && this.casillas[i+1][j+1] != null && !Character.isUpperCase(this.casillas[i+1][j+1].charAt(0))) {
				movimientos.add(new Movimiento(i, j, i+1, j+1, pieza));
			}
			break;
		case "N":
			if (i-2 >= 0 && j+1 <= 7 && (this.casillas[i-2][j+1]==null || !Character.isUpperCase(this.casillas[i-2][j+1].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i-2, j+1, pieza));
			if (i-1 >= 0 && j+2 <= 7 && (this.casillas[i-1][j+2]==null || !Character.isUpperCase(this.casillas[i-1][j+2].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i-1, j+2, pieza));
			if (i+1 <= 7 && j+2 <= 7 && (this.casillas[i+1][j+2]==null || !Character.isUpperCase(this.casillas[i+1][j+2].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i+1, j+2, pieza));
			if (i+2 <= 7 && j+1 <= 7 && (this.casillas[i+2][j+1]==null || !Character.isUpperCase(this.casillas[i+2][j+1].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i+2, j+1, pieza));
			if (i+2 <= 7 && j-1 >= 0 && (this.casillas[i+2][j-1]==null || !Character.isUpperCase(this.casillas[i+2][j-1].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i+2, j-1, pieza));
			if (i+1 <= 7 && j-2 >= 0 && (this.casillas[i+1][j-2]==null || !Character.isUpperCase(this.casillas[i+1][j-2].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i+1, j-2, pieza));
			if (i-1 >= 0 && j-2 >= 0 && (this.casillas[i-1][j-2]==null || !Character.isUpperCase(this.casillas[i-1][j-2].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i-1, j-2, pieza));
			if (i-2 >= 0 && j-1 >= 0 && (this.casillas[i-2][j-1]==null || !Character.isUpperCase(this.casillas[i-2][j-1].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i-2, j-1, pieza));
			break;
		case "n":
			if (i-2 >= 0 && j+1 <= 7 && (this.casillas[i-2][j+1]==null || Character.isUpperCase(this.casillas[i-2][j+1].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i-2, j+1, pieza));
			if (i-1 >= 0 && j+2 <= 7 && (this.casillas[i-1][j+2]==null || Character.isUpperCase(this.casillas[i-1][j+2].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i-1, j+2, pieza));
			if (i+1 <= 7 && j+2 <= 7 && (this.casillas[i+1][j+2]==null || Character.isUpperCase(this.casillas[i+1][j+2].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i+1, j+2, pieza));
			if (i+2 <= 7 && j+1 <= 7 && (this.casillas[i+2][j+1]==null || Character.isUpperCase(this.casillas[i+2][j+1].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i+2, j+1, pieza));
			if (i+2 <= 7 && j-1 >= 0 && (this.casillas[i+2][j-1]==null || Character.isUpperCase(this.casillas[i+2][j-1].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i+2, j-1, pieza));
			if (i+1 <= 7 && j-2 >= 0 && (this.casillas[i+1][j-2]==null || Character.isUpperCase(this.casillas[i+1][j-2].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i+1, j-2, pieza));
			if (i-1 >= 0 && j-2 >= 0 && (this.casillas[i-1][j-2]==null || Character.isUpperCase(this.casillas[i-1][j-2].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i-1, j-2, pieza));
			if (i-2 >= 0 && j-1 >= 0 && (this.casillas[i-2][j-1]==null || Character.isUpperCase(this.casillas[i-2][j-1].charAt(0)))) 
				movimientos.add(new Movimiento(i, j, i-2, j-1, pieza));
			break;
		case "B":
			boolean stop = false;
			int ii = i;
			int jj = j;
			while (ii > 0 && jj > 0 && !stop) {
				ii--; jj--;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii > 0 && jj < 7 && !stop) {
				ii--; jj++;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii < 7 && jj < 7 && !stop) {
				ii++; jj++;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii < 7 && jj > 0 && !stop) {
				ii++; jj--;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			break;
		case "b":
			stop = false;
			ii = i;
			jj = j;
			while (ii > 0 && jj > 0 && !stop) {
				ii--; jj--;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii > 0 && jj < 7 && !stop) {
				ii--; jj++;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii < 7 && jj < 7 && !stop) {
				ii++; jj++;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii < 7 && jj > 0 && !stop) {
				ii++; jj--;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			break;
		case "R":
			stop = false;
			ii = i;
			while (ii > 0 && !stop) {
				ii--;
				if (this.casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][j].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, j, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			while (ii < 7 && !stop) {
				ii++;
				if (this.casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][j].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, j, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			jj = j;
			while (jj > 0 && !stop) {
				jj--;
				if (this.casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[i][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, i, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			jj = j;
			while (jj < 7 && !stop) {
				jj++;
				if (this.casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[i][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, i, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			break;
		case "r":
			stop = false;
			ii = i;
			while (ii > 0 && !stop) {
				ii--;
				if (this.casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, pieza));
				else if (Character.isUpperCase(this.casillas[ii][j].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, j, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			while (ii < 7 && !stop) {
				ii++;
				if (this.casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, pieza));
				else if (Character.isUpperCase(this.casillas[ii][j].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, j, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			jj = j;
			while (jj > 0 && !stop) {
				jj--;
				if (this.casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, pieza));
				else if (Character.isUpperCase(this.casillas[i][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, i, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			jj = j;
			while (jj < 7 && !stop) {
				jj++;
				if (this.casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, pieza));
				else if (Character.isUpperCase(this.casillas[i][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, i, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			break;
		case "Q":
			stop = false;
			ii = i;
			jj = j;
			while (ii > 0 && jj > 0 && !stop) {
				ii--; jj--;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii > 0 && jj < 7 && !stop) {
				ii--; jj++;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii < 7 && jj < 7 && !stop) {
				ii++; jj++;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii < 7 && jj > 0 && !stop) {
				ii++; jj--;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			while (ii > 0 && !stop) {
				ii--;
				if (this.casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][j].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, j, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			while (ii < 7 && !stop) {
				ii++;
				if (this.casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, pieza));
				else if (!Character.isUpperCase(this.casillas[ii][j].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, j, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			jj = j;
			while (jj > 0 && !stop) {
				jj--;
				if (this.casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[i][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, i, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			jj = j;
			while (jj < 7 && !stop) {
				jj++;
				if (this.casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, pieza));
				else if (!Character.isUpperCase(this.casillas[i][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, i, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			break;
		case "q":
			stop = false;
			ii = i;
			jj = j;
			while (ii > 0 && jj > 0 && !stop) {
				ii--; jj--;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii > 0 && jj < 7 && !stop) {
				ii--; jj++;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii < 7 && jj < 7 && !stop) {
				ii++; jj++;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			jj = j;
			while (ii < 7 && jj > 0 && !stop) {
				ii++; jj--;
				if (this.casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, pieza));
				else if (Character.isUpperCase(this.casillas[ii][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			while (ii > 0 && !stop) {
				ii--;
				if (this.casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, pieza));
				else if (Character.isUpperCase(this.casillas[ii][j].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, j, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			ii = i;
			while (ii < 7 && !stop) {
				ii++;
				if (this.casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, pieza));
				else if (Character.isUpperCase(this.casillas[ii][j].charAt(0))) {
					movimientos.add(new Movimiento(i, j, ii, j, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			jj = j;
			while (jj > 0 && !stop) {
				jj--;
				if (this.casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, pieza));
				else if (Character.isUpperCase(this.casillas[i][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, i, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			stop = false;
			jj = j;
			while (jj < 7 && !stop) {
				jj++;
				if (this.casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, pieza));
				else if (Character.isUpperCase(this.casillas[i][jj].charAt(0))) {
					movimientos.add(new Movimiento(i, j, i, jj, pieza));
					stop = true;
				}
				else stop = true;
			}
			break;
		case "K":
			if (i-1 >= 0 && j-1 >= 0 && (this.casillas[i-1][j-1] == null || !Character.isUpperCase(this.casillas[i-1][j-1].charAt(0)))) {
				if (!this.esJaqueEnPosicionABlancas(i-1, j-1)) {
					movimientos.add(new Movimiento(i, j, i-1, j-1, pieza));
				}
			}
			if (i-1 >= 0 && (this.casillas[i-1][j] == null || !Character.isUpperCase(this.casillas[i-1][j].charAt(0)))) {
				if (!this.esJaqueEnPosicionABlancas(i-1, j)) {
					movimientos.add(new Movimiento(i, j, i-1, j, pieza));
				}
			}
			if (i-1 >= 0 && j+1 <= 7 && (this.casillas[i-1][j+1] == null || !Character.isUpperCase(this.casillas[i-1][j+1].charAt(0)))) {
				if (!this.esJaqueEnPosicionABlancas(i-1, j+1)) {
					movimientos.add(new Movimiento(i, j, i-1, j+1, pieza));
				}
			}
			if (j+1 <= 7 && (this.casillas[i][j+1] == null || !Character.isUpperCase(this.casillas[i][j+1].charAt(0)))) {
				if (!this.esJaqueEnPosicionABlancas(i, j+1)) {
					movimientos.add(new Movimiento(i, j, i, j+1, pieza));
				}
			}
			if (i+1 <= 7 && j+1 <= 7 && (this.casillas[i+1][j+1] == null || !Character.isUpperCase(this.casillas[i+1][j+1].charAt(0)))) {
				if (!this.esJaqueEnPosicionABlancas(i+1, j+1)) {
					movimientos.add(new Movimiento(i, j, i+1, j+1, pieza));
				}
			}
			if (i+1 <= 7 && (this.casillas[i+1][j] == null || !Character.isUpperCase(this.casillas[i+1][j].charAt(0)))) {
				if (!this.esJaqueEnPosicionABlancas(i+1, j)) {
					movimientos.add(new Movimiento(i, j, i+1, j, pieza));
				}
			}
			if (i+1 <= 7 && j-1 >= 0 && (this.casillas[i+1][j-1] == null || !Character.isUpperCase(this.casillas[i+1][j-1].charAt(0)))) {
				if (!this.esJaqueEnPosicionABlancas(i+1, j-1)) {
					movimientos.add(new Movimiento(i, j, i+1, j-1, pieza));
				}
			}
			if (j-1 >= 0 && (this.casillas[i][j-1] == null || !Character.isUpperCase(this.casillas[i][j-1].charAt(0)))) {
				if (!this.esJaqueEnPosicionABlancas(i, j-1)) {
					movimientos.add(new Movimiento(i, j, i, j-1, pieza));
				}
			}
			break;
		case "k":
			if (i-1 >= 0 && j-1 >= 0 && (this.casillas[i-1][j-1] == null || Character.isUpperCase(this.casillas[i-1][j-1].charAt(0)))) {
				if (!this.esJaqueEnPosicionANegras(i-1, j-1)) {
					movimientos.add(new Movimiento(i, j, i-1, j-1, pieza));
				}
			}
			if (i-1 >= 0 && (this.casillas[i-1][j] == null || Character.isUpperCase(this.casillas[i-1][j].charAt(0)))) {
				if (!this.esJaqueEnPosicionANegras(i-1, j)) {
					movimientos.add(new Movimiento(i, j, i-1, j, pieza));
				}
			}
			if (i-1 >= 0 && j+1 <= 7 && (this.casillas[i-1][j+1] == null || Character.isUpperCase(this.casillas[i-1][j+1].charAt(0)))) {
				if (!this.esJaqueEnPosicionANegras(i-1, j+1)) {
					movimientos.add(new Movimiento(i, j, i-1, j+1, pieza));
				}
			}
			if (j+1 <= 7 && (this.casillas[i][j+1] == null || Character.isUpperCase(this.casillas[i][j+1].charAt(0)))) {
				if (!this.esJaqueEnPosicionANegras(i, j+1)) {
					movimientos.add(new Movimiento(i, j, i, j+1, pieza));
				}
			}
			if (i+1 <= 7 && j+1 <= 7 && (this.casillas[i+1][j+1] == null || Character.isUpperCase(this.casillas[i+1][j+1].charAt(0)))) {
				if (!this.esJaqueEnPosicionANegras(i+1, j+1)) {
					movimientos.add(new Movimiento(i, j, i+1, j+1, pieza));
				}
			}
			if (i+1 <= 7 && (this.casillas[i+1][j] == null || Character.isUpperCase(this.casillas[i+1][j].charAt(0)))) {
				if (!this.esJaqueEnPosicionANegras(i+1, j)) {
					movimientos.add(new Movimiento(i, j, i+1, j, pieza));
				}
			}
			if (i+1 <= 7 && j-1 >= 0 && (this.casillas[i+1][j-1] == null || Character.isUpperCase(this.casillas[i+1][j-1].charAt(0)))) {
				if (!this.esJaqueEnPosicionANegras(i+1, j-1)) {
					movimientos.add(new Movimiento(i, j, i+1, j-1, pieza));
				}
			}
			if (j-1 >= 0 && (this.casillas[i][j-1] == null || Character.isUpperCase(this.casillas[i][j-1].charAt(0)))) {
				if (!this.esJaqueEnPosicionANegras(i, j-1)) {
					movimientos.add(new Movimiento(i, j, i, j-1, pieza));
				}
			}
			break;
		default:
			return;
		}
	}

	public void anadirFicha(String ficha, int i, int j) { //DONE
		this.casillas[i][j] = ficha;
	}
	
	public void quitarFicha(int i, int j) { //DONE
		this.casillas[i][j] = null;
	}
	
	public String consultarCasilla(int i, int j) { //DONE
		return this.casillas[i][j];
	}
	
	public void imprimirEstadoTableroConsola() { //DONE
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.casillas[i][j] == null) System.out.print("- "); 
				else System.out.print(this.casillas[i][j] + " "); 
			}
			System.out.print("\n");
		}
		System.out.println("");
	}
	
}
