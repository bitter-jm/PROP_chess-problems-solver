package drivers;

import java.util.List;
import data.CtrlDBProblema;

public class DriverCtrlDBProblema {

	public static void main(String[] args) {

		CtrlDBProblema dbP = CtrlDBProblema.getInstance();
		//dbP.createProblema("P1\n2\nfenDelProblema...\nfalse\ntrue\n0\nEvaristo");
		System.out.println(dbP.existsProblema("P1"));
		System.out.println(dbP.existsProblema("P2"));
		System.out.println(dbP.getProblema("P1"));
		List<String> problemas = dbP.getMisProblemas("Evaristo");
		System.out.println("# de problemas de Evaristo: " + problemas.size());
		List<String> problemasJ = dbP.getProblemasJugables();
		System.out.println("# de problemas jugables: " + problemasJ.size());
		//dbP.deleteProblema("P1");
	}

}
