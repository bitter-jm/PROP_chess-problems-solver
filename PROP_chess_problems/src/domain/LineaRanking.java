package domain;
/**
 * Clase Linea Ranking
 */
public class LineaRanking {
	private String name;
	private int puntuacion;
	
	/**
	 * Creadora vacia
	 */
	public LineaRanking() {}
	/**
	 * Creadora del LineaRanking que indica lo que habra en cada posicion del vector de Ranking
	 * @param nom username del usuario que juega
	 * @param punts  puntuacion del dicho usuario(username)
	 */
	
	
	public LineaRanking(String nom, int punts) {
		name=nom;
		puntuacion=punts;
	}
	/**
	 * Devuelve el atributo name de la clase LineaRanking
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * Devuelve el atibuto puntuacion de la clase LineaRanking
	 * @return
	 */
	public int getPoints() {
		return puntuacion;
	}
	/**
	 * Establece valor al atributo name de la clase LineaRanking
	 * @param nom es el valor que se le establece a name
	 */
	public void setName(String nom) {
		name=nom;
	}
	/**
	 * Establece valor al atributo puntuacion de la clase LineaRanking
	 * @param points es el valor que se le establece a puntuacion
	 */
	public void setPoints(int points) {
		puntuacion=points;
	}
}
