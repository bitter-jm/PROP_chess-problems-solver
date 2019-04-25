package domain;
import java.util.*;
import domain.LineaRanking;


public class Ranking {
	private Vector< LineaRanking > ranking;
	

	//no se me cambia el tamano del vector
	
	public Ranking() {
		this.ranking = new Vector<LineaRanking>(2,4);
		ranking.ensureCapacity(50); //nose si la he de ir llamando al añadir cosas
	}
/**
 * Esta funcion añade al ranking el username y su puntuacion si ha sacado una de las 
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
		System.out.println("Aqui");
		if (num==-1) { //no esta en elranking
			//para asegurar que el tamano de ranking no pasa de 50
			if (ranking.size()==50 && ranking.get(49).getPoints()< puntuacion) 
				ranking.remove(49);
			
			for (int i=0; i<ranking.size()-1; ++i) {
				if (puntuacion < ranking.get(i).getPoints() && puntuacion > ranking.get(i+1).getPoints()) {
					ranking.insertElementAt(lr, i+1);
				}
				else if (puntuacion >= ranking.get(i).getPoints()) {
					ranking.insertElementAt(lr, i);
				}
			}
		}
		else {//ya estaba en el ranking
			if (puntuacion > ranking.get(num).getPoints()) {
				ranking.removeElementAt(num);//elimino la antigua puntuacion
				for (int i=0; i<ranking.size()-1; ++i) { //añado la nueva ordenada
					if (puntuacion < ranking.get(i).getPoints() && puntuacion > ranking.get(i+1).getPoints()) {
						ranking.insertElementAt(lr, i+1);
					}
					else if (puntuacion >= ranking.get(i).getPoints()) {
						ranking.insertElementAt(lr, i);
					}
				}
			}
			//else si hay una puntuacion antigua mejor, no se cambia
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
		System.out.println("tamany" + ranking.size());
		for (int i=0; i<ranking.size(); ++i) {
			if (username == ranking.get(i).getName())
				return i;
		}
		return -1;
	}
	/**
	 * Imprime por la consola el ranking
	 */
	public void printranking() {
		for (int i=0; i<ranking.size(); ++i) {
			System.out.println(i + " : " + ranking.get(i).getName() +" with " + ranking.get(i).getPoints() + " Points.\n");
		}
	}

	public int getpuntuacion () { //segun que parametros calculo la puntuacion?
		//ahora esta en random
		int max= 50000;
		Random random = new Random();
		return random.nextInt(max);
		
	}
	
}
