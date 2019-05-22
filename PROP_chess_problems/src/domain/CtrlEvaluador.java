package domain;

import java.util.*;

public class CtrlEvaluador {
	
	private static CtrlEvaluador single_instance = null;
	private CtrlPartida ctrlPa = CtrlPartida.getInstance();
	
	public static CtrlEvaluador getInstance() { 
		if (single_instance == null) 
			single_instance = new CtrlEvaluador(); 
		return single_instance; 
	}
	
	private CtrlEvaluador() {}
	
	// maquina1 y maquina2 pueden ser "MAQUINA1" o "MAQUINA2"
	public String evaluarProblemas(List<String> problemas, String maquina1, String maquina2) {
		
		return "";
	}

}
