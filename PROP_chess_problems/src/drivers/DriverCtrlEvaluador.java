package drivers;

import domain.CtrlEvaluador;
import java.util.*;

public class DriverCtrlEvaluador {

	public static void main(String[] args) {

		CtrlEvaluador ctrlE = CtrlEvaluador.getInstance();
		List<String> aEvaluar = new ArrayList<String>();
		aEvaluar.add("P1");
		aEvaluar.add("Problema1");
		aEvaluar.add("Problema1");
		String[][] ganadores = ctrlE.evaluarProblemas(aEvaluar, "MAQUINA2", "MAQUINA1");
		for (String[] ganador : ganadores) {
			System.out.println(ganador[0]);
			System.out.println(ganador[1]);
		}
	}

}
