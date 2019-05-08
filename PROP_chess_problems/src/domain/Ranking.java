package domain;
import java.util.*;
import domain.LineaRanking;
import java.util.Vector;

/**
 * Clase Ranking no terminada de implementar
 */
public class Ranking {
	
	private Vector<LineaRanking>ranking=new Vector<LineaRanking>(2,2);

	//no se me cambia el tamano del vector
	
	public Ranking() {}
/**
 * Esta funcion a�ade al ranking el username y su puntuacion si ha sacado una de las 
 * 50 mejores puntuaciones de ese problema. 
 * Si el username ya existia se actualiza su valor y sitio en el ranking.
 * Si tiene una puntuacion anterior mas elevada mantiene la 
 * puntuacion antigua.
 * @param nombre  Es el username, nunca habra dos iguales en un ranking
 * @param puntuacion	la puntuacion que ha sacado el usuario en esa ronda
 */
	public void anadirAlRanking(String nombre, int puntuacion) {
		LineaRanking lr = new LineaRanking (nombre, puntuacion);		
		int num=numranking(nombre);
		
		if (num==-1) { //no esta en elranking
			if (ranking.size()==50 && ranking.get(49).getPoints()< puntuacion) 
				ranking.remove(49);
			else if (ranking.size()<50) {
				ranking.add(lr);
				this.ordenar(this.ranking);
			}
		}
		else {
			if(puntuacion >ranking.get(num).getPoints()) {
				ranking.remove(num);
				ranking.add(lr);
				this.ordenar(this.ranking);
			}
		}
	}
	/**
	 * Ordena el ranking para que este de mayor a menor puntuacion
	 * @param ranking es el vector atributo de la clase que se ha de ordenar
	 */
	private void ordenar(Vector<LineaRanking> ranking) {
		for (int i=0;i<ranking.size()-1; ++i) {
			for (int j=i+1; j<ranking.size(); ++j) {
				if (ranking.get(i).getPoints()<ranking.get(j).getPoints()) {
					LineaRanking aux = ranking.get(i);
					this.ranking.setElementAt(ranking.get(j), i);
					this.ranking.setElementAt(aux, j);
				}
			}
		}
	}
	/**
	 * Eliminamos del ranking el Nombre (username) y su puntuacion.
	 * @param username Nombre que queremos quitar del ranking
	 */
	public void eliminardelranking (String username) {
		int num =numranking(username);
		if (num!=-1) {
			ranking.removeElementAt(num);
		}
	}

	/**
	 * Devuelve si ya existe el username en el ranking, a que posicion del vector de ranking estan.
	 * Si el username no existe en el ranking devuelve -1
	 * @param username El nombre que identifica al usuario en el ranking
	 * @return
	 */
	private int numranking(String username) {
		for (int i=0; i<ranking.size(); ++i) {
			String nombre =ranking.get(i).getName();
			if(nombre.equals(username)) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * Imprime por la consola el ranking
	 */
	public void printranking() {
		for (int i=0; i<ranking.size(); ++i) {
			System.out.println(i+1 + " - " + ranking.get(i).getName() +" with " + ranking.get(i).getPoints() + " Points.\n");
		}
	}
/**
 * Funcion que segun los resultados de los parametros obtenidos de la partida 
 * otorgara una puntuacion
 * @return devuelve un int que sera la puntuacion
 */
	private int getpuntuacion () { //segun que parametros calculo la puntuacion?
		//ahora esta en random
		int max= 50000;
		Random random = new Random();
		return random.nextInt(max);
		
	}
	
}
