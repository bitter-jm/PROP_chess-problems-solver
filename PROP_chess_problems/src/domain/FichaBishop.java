package domain;

import java.util.List;
import java.util.ArrayList;

public class FichaBishop extends Ficha {

	/**
	 * Crea un objeto del tipo FichaBishop
	 * @param color String indicando de que color es
	 */
	public FichaBishop(String color) {
		this.color = color;
		this.ficha = "b";
	}
	
	@Override
	public List<Movimiento> posiblesMovimientos(int i, int j, Ficha[][] casillas) {
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		
		boolean stop = false;
		int ii = i;
		int jj = j;
		while (ii > 0 && jj > 0 && !stop) {
			ii--; jj--;
			if (casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, this));
			else if (!this.color.equals(casillas[ii][jj].color)) {
				movimientos.add(new Movimiento(i, j, ii, jj, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		ii = i;
		jj = j;
		while (ii > 0 && jj < 7 && !stop) {
			ii--; jj++;
			if (casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, this));
			else if (!this.color.equals(casillas[ii][jj].color)) {
				movimientos.add(new Movimiento(i, j, ii, jj, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		ii = i;
		jj = j;
		while (ii < 7 && jj < 7 && !stop) {
			ii++; jj++;
			if (casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, this));
			else if (!this.color.equals(casillas[ii][jj].color)) {
				movimientos.add(new Movimiento(i, j, ii, jj, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		ii = i;
		jj = j;
		while (ii < 7 && jj > 0 && !stop) {
			ii++; jj--;
			if (casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, this));
			else if (!this.color.equals(casillas[ii][jj].color)) {
				movimientos.add(new Movimiento(i, j, ii, jj, this));
				stop = true;
			}
			else stop = true;
		}
		
		return movimientos;
	}
	
}
