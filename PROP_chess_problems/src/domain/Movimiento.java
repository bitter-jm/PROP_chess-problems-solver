package domain;

public class Movimiento {
	//pawn = "P", knight = "N", bishop = "B", rook = "R", queen = "Q" and king = "K" //BLANCAS
	//pawn = "p", knight = "n", bishop = "b", rook = "r", queen = "q" and king = "k" //NEGRAS
	
	public int inicioI;
	public int inicioJ;
	public int finalI;
	public int finalJ;
	public String ficha;
	
	public Movimiento(int ii, int ij, int fi, int fj, String ficha) {
		this.inicioI = ii;
		this.inicioJ = ij;
		this.finalI = fi;
		this.finalJ = fj;
		this.ficha = ficha;
	}
	
}
