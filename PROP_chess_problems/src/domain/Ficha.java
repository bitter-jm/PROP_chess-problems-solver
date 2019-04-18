package domain;

import java.util.List;
import java.util.ArrayList;
import domain.Movimiento;
import domain.FichaPawn; // DONE
import domain.FichaKnight; // DONE
import domain.FichaBishop; // DONE
import domain.FichaRook; // DONE
import domain.FichaQueen; // DONE
import domain.FichaKing;
import java.lang.Character;

/**
 * Representa una ficha
 * @author Joan Pastor
 */
public class Ficha {
	//pawn = "P", knight = "N", bishop = "B", rook = "R", queen = "Q" and king = "K" //BLANCAS
	//pawn = "p", knight = "n", bishop = "b", rook = "r", queen = "q" and king = "k" //NEGRAS

	public String color;
	public String ficha;
	
	/**
	 * Devuelve una ficha nueva segun los parametros proporcionados
	 * @param f String que indica en minusculas que tipo de ficha es
	 * @param color String que indica que color es
	 * @return nuevo objeto FichaX segun los parametros proporcionados
	 */
	public static Ficha newFicha(String f, String color) {
		if ("p".equals(f)) return new FichaPawn(color);
		else if ("n".equals(f)) return new FichaKnight(color);
		else if ("b".equals(f)) return new FichaBishop(color);
		else if ("r".equals(f)) return new FichaRook(color);
		else if ("q".equals(f)) return new FichaQueen(color);
		else if ("k".equals(f)) return new FichaKing(color);
		return new Ficha();
	}
	
	/**
	 * Devuelve una ficha nueva segun el parametro proporcionado
	 * @param f String de la ficha segun anotacion FEN
	 * @return nuevo objeto FichaX segun el parametro proporcionado
	 */
	public static Ficha newFicha(String f) {
		String color = "NEGRAS";
		String fLower = String.valueOf(Character.toLowerCase(f.charAt(0)));
		if (Character.isUpperCase(f.charAt(0))) color = "BLANCAS"; 
		return Ficha.newFicha(fLower, color);
	}

	/**
	 * Compara si dos Fichas son equivalentes en cuanto a color y tipo (Deep Comparison)
	 * @return Booleano indicando si dos Fichas son equivalentes
	 */
	@Override
    public boolean equals(Object obj) {
		Ficha f = (Ficha) obj;
		if (!this.color.equals(f.color)) return false;
		if (!this.ficha.equals(f.ficha)) return false;
		return true;		
	}
	
	/**
	 * Consulta el caracter de la Ficha en formato FEN
	 * @return Devuelve un String con el caracter de la Ficha en formato FEN
	 */
	public String getCharacter() {
		if (color.equals("NEGRAS")) return this.ficha;
		else return String.valueOf(Character.toUpperCase(this.ficha.charAt(0)));
	}
	
	/**
	 * Consulta los posibles movimientos que puede ralizar la Ficha en una determinada posicion
	 * @param i Integer indicando la coordenada i de la Ficha
	 * @param j Integer indicando la coordenada j de la Ficha
	 * @param casillas Matriz de Fichas indicando el estado del Tablero
	 * @return Devuelve una List de Movimientos con todos los movimientos legales que puede realizar la Ficha
	 */
	public List<Movimiento> posiblesMovimientos(int i, int j, Ficha[][] casillas) {
		return new ArrayList<Movimiento>();
	}
	
}
