package domain;

import domain.Ranking;
import domain.CtrlDB;

public class CtrlRanking {

	private static CtrlRanking single_instance = null;
	private CtrlDB db = CtrlDB.getInstance();

	public static CtrlRanking getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlRanking(); 
        return single_instance; 
    }
	
	/**
	 * Anade una linea al ranking de unn problema
	 * @param nombrePersona String con nombre de la persona
	 * @param nombreProblema String con el nombre del problema jugado
	 * @param puntuacion Integer con la puntuacion de la persona
	 */
	public void addToRanking(String nombrePersona, String nombreProblema, int puntuacion) {
		Ranking r = db.getRankingProblema(nombreProblema);
		r.anadirAlRanking(nombrePersona, puntuacion);
		db.saveRankingProblema(r);
	}
	
}
