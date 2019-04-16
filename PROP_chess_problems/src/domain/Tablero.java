package domain;

import domain.Movimiento;
import domain.MovimientoCompleto;
import domain.Ficha;
import java.lang.Character;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Tablero {
	//pawn = "P", knight = "N", bishop = "B", rook = "R", queen = "Q" and king = "K" //BLANCAS
	//pawn = "p", knight = "n", bishop = "b", rook = "r", queen = "q" and king = "k" //NEGRAS
	
	private Ficha[][] casillas = new Ficha[8][8];
	private List<MovimientoCompleto> historyStack = new ArrayList<MovimientoCompleto>();
	
	public Tablero() {} //DONE DONE
	
	public Tablero(String fen) { //DONE DONE
		this.cargarFEN(fen);
	}
	
	public void limpiarTablero() { //DONE DONE
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.casillas[i][j] = null;
			}
		}
		this.historyStack.clear();
	}
	
	public void cargarFEN(String fen) { //DONE DONE
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
				this.casillas[i][j] = Ficha.newFicha(c);
				if (j < 7) j++;
			}
		}
	}
	
	public String exportarFEN() { //DONE DONE
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
	
	public void deshacer() { //DONE DONE
		if (this.historyStack.isEmpty()) return;
		MovimientoCompleto mc = this.historyStack.remove(this.historyStack.size()-1);
		this.casillas[mc.inicioI][mc.inicioJ] = mc.ficha;
		this.casillas[mc.finalI][mc.finalJ] = mc.fichaMatada;
	}
	
	public boolean registrarMovimientoValidando(Movimiento mov) { //DONE DONE
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
	
	public void registrarMovimientoSinValidar(Movimiento mov) { //DONE DONE
		this.historyStack.add(new MovimientoCompleto(mov, this.casillas[mov.finalI][mov.finalJ]));
		this.casillas[mov.inicioI][mov.inicioJ] = null;
		this.casillas[mov.finalI][mov.finalJ] = mov.ficha;
	}
	
	public boolean esJaque(String color) { //DONE DONE
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
	
	public boolean esJaqueEnPosicionANegras(int i, int j) { // DONE DONE
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
	
	public boolean esJaqueEnPosicionABlancas(int i, int j) { // DONE DONE
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
	
	public List<Movimiento> posiblesMovimientos(String color) { //DONE DONE
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
	
	public void anadirFicha(Ficha ficha, int i, int j) { //DONE DONE
		this.casillas[i][j] = ficha;
	}
	
	public void quitarFicha(int i, int j) { //DONE DONE
		this.casillas[i][j] = null;
	}
	
	public Ficha consultarCasilla(int i, int j) { //DONE DONE
		return this.casillas[i][j];
	}
	
	public void imprimirEstadoTableroConsola() { //DONE DONE
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.casillas[i][j] == null) System.out.print("- "); 
				else System.out.print(this.casillas[i][j].getCharacter() + " "); 
			}
			System.out.print("\n");
		}
		System.out.println("");
	}
	
	public Ficha[][] getCasillas() { //DONE DONE
		return this.casillas;
	}
	
	public boolean esMateColor(String color) {
		List<Movimiento> movimientos = this.posiblesMovimientos(color);
		//System.out.println("Posibles movimientos: " + movimientos.size());
		if (movimientos.isEmpty()) return true;
		return false;
	}
	
}
