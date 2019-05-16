package drivers;
import data.DBRanking;

public class DriverDBRanking {

	public static void main(String[] args) {

		DBRanking dbR = DBRanking.getInstance();
		
		System.out.println(dbR.getRankingProblema("P2"));
		
		
		
	}

}
