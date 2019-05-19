package drivers;
import data.DBRanking;
import domain.CtrlDB;
import domain.Ranking;

public class DriverDBRanking {

	public static void main(String[] args) {

		DBRanking dbR = DBRanking.getInstance();
		
		System.out.println(dbR.getRankingProblema("P2"));
		//dbR.updateRanking("Pers1?6\nPers2?7\nPers3?8", "P3");
		
		CtrlDB ctrlDB = CtrlDB.getInstance();
		Ranking r = ctrlDB.getRankingProblema("P3");
		r.anadirAlRanking("Pers4", 9);
		ctrlDB.saveRankingProblema(r);
		System.out.println(dbR.getRankingProblema("P3"));
		
	}

}
