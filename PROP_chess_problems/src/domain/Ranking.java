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
	public void eliminardelranking (String username) {
		int num =numranking(username);
		if (num!=-1) {
			ranking.removeElementAt(num);
		}
	}

	
	private int numranking(String username) {
		System.out.println("tamany" + ranking.size());
		for (int i=0; i<ranking.size(); ++i) {
			if (username == ranking.get(i).getName())
				return i;
		}
		return -1;
	}
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
