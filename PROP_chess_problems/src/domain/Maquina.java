package domain;

/**
 * Representa una Maquina
 * @author Carla GarciaC
 */
public class Maquina extends Jugador {

	protected String color, colorOpuesto;
	protected int maxDepth = 4;
	
	public Maquina() {
		super.nombre = "MAQUINA";
	}
	@Override
	public boolean esPersona() {
		return false;
	}
	
	/**
	 * Calcula cual es el mejor movimiento a realizar
	 * @param fen String con el estado del tablero en formato FEN
	 * @return Devuelve el Movimiento mas optimo 
	 */
	public Movimiento realizarMovimiento(String fen) {
		return new Movimiento(1,1,1,1, Ficha.newFicha("p"));
	}
	
	/**
	 * Especifica en que lado va a jugar la maquina
	 * @param color String indicando el color del jugador
	 */
	public void setColor(String color) { 
		this.color = color;
		if (color.equals("BLANCAS")) this.colorOpuesto = "NEGRAS";
		else this.colorOpuesto = "BLANCAS";
	} 
	
	/**
	 * Especifica a que profundidad quiere evaluar el tablero
	 * @param depth Integer indicando la profundidad de busqueda
	 */
	public void setDepth(int depth) {
		this.maxDepth = depth;
	}

	/**
	 * Devuelve la profundidad a la que se evalua el tablero
	 * 
	 */
	public int getDepth() {
		return this.maxDepth;
	}
}
