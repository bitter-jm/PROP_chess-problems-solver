package drivers;

import data.CtrlData;

public class DriverTESTS_BORRAR {

	public static void main(String[] args) {
		
		CtrlData d = CtrlData.getInstance();
		String[][] probs = d.getProblemasJugablesOrdenadosPorMovimientos();
		for (String[] prob: probs) {
			for (String elem: prob) {
				System.out.println(elem+" ");
			}
			System.out.println("-----------");
		}

	}

}
