package drivers;

import java.util.List;
import data.DBProblema;

public class DriverDBProblema {

	public static void main(String[] args) {

		DBProblema dbP = DBProblema.getInstance();
		//dbP.createProblema("P1\n2\n8/8/8/8/3p4/5K2/3kNN2/2R5\nfalse\ntrue\n0\nEvaristo");
		System.out.println(dbP.existsProblema("P1"));
		System.out.println(dbP.existsProblema("P2"));
		System.out.println(dbP.getProblema("P1"));
		List<String> problemas = dbP.getMisProblemas("Evaristo");
		System.out.println("# de problemas de Evaristo: " + problemas.size());
		List<String> problemasJ = dbP.getProblemasJugables();
		System.out.println("# de problemas jugables: " + problemasJ.size());
		//dbP.deleteProblema("P1");
		dbP.incrementarVecesJugado("P1");
		System.out.println(dbP.getProblema("P1"));
		
	}

}
