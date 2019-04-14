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

public class Ficha {
	//pawn = "P", knight = "N", bishop = "B", rook = "R", queen = "Q" and king = "K" //BLANCAS
	//pawn = "p", knight = "n", bishop = "b", rook = "r", queen = "q" and king = "k" //NEGRAS

	public String color;
	public String ficha;
	
	public static Ficha newFicha(String f, String color) {
		if ("p".equals(f)) return new FichaPawn(color);
		else if ("n".equals(f)) return new FichaKnight(color);
		else if ("b".equals(f)) return new FichaBishop(color);
		else if ("r".equals(f)) return new FichaRook(color);
		else if ("q".equals(f)) return new FichaQueen(color);
		else if ("k".equals(f)) return new FichaKing(color);
		return new Ficha();
	}
	
	public static Ficha newFicha(String f) {
		String color = "NEGRAS";
		String fLower = String.valueOf(Character.toLowerCase(f.charAt(0)));
		if (Character.isUpperCase(f.charAt(0))) color = "BLANCAS"; 
		return Ficha.newFicha(fLower, color);
	}

	@Override
    public boolean equals(Object obj) {
		Ficha f = (Ficha) obj;
		if (!this.color.equals(f.color)) return false;
		if (!this.ficha.equals(f.ficha)) return false;
		return true;		
	}
	
	public String getCharacter() {
		if (color.equals("NEGRAS")) return this.ficha;
		else return String.valueOf(Character.toUpperCase(this.ficha.charAt(0)));
	}
	
	public List<Movimiento> posiblesMovimientos(int i, int j, Ficha[][] casillas) {
		return new ArrayList<Movimiento>();
	}
	
}
