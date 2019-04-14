package domain;

import java.util.List;
import java.util.ArrayList;

public class FichaRook extends Ficha {

	public FichaRook(String color) {
		this.color = color;
		this.ficha = "r";
	}
	
	@Override
	public List<Movimiento> posiblesMovimientos(int i, int j, Ficha[][] casillas) {
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
	
		boolean stop = false;
		int ii = i;
		while (ii > 0 && !stop) {
			ii--;
			if (casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, this));
			else if (!this.color.equals(casillas[ii][j].color)) {
				movimientos.add(new Movimiento(i, j, ii, j, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		ii = i;
		while (ii < 7 && !stop) {
			ii++;
			if (casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, this));
			else if (!this.color.equals(casillas[ii][j].color)) {
				movimientos.add(new Movimiento(i, j, ii, j, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		int jj = j;
		while (jj > 0 && !stop) {
			jj--;
			if (casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, this));
			else if (!this.color.equals(casillas[i][jj].color)) {
				movimientos.add(new Movimiento(i, j, i, jj, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		jj = j;
		while (jj < 7 && !stop) {
			jj++;
			if (casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, this));
			else if (!this.color.equals(casillas[i][jj].color)) {
				movimientos.add(new Movimiento(i, j, i, jj, this));
				stop = true;
			}
			else stop = true;
		}
		
		return movimientos;
	}
	
}
