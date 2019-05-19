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
	
	public void addToRanking(String nombrePersona, String nombreProblema, int puntuacion) {
		Ranking r = db.getRankingProblema(nombreProblema);
		r.anadirAlRanking(nombrePersona, puntuacion);
		db.saveRankingProblema(r);
	}
	
}
