package domain;

public class LineaRanking {
	String name;
	int puntuacion;
	
	public LineaRanking(String nom, int punts) {
		name=nom;
		puntuacion=punts;
	}
	public String getName() {
		return name;
	}
	public int getPoints() {
		return puntuacion;
	}
	public void setName(String nom) {
		name=nom;
	}
	public void setPoints(int points) {
		puntuacion=points;
	}
}
