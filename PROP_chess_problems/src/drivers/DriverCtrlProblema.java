package drivers;

import domain.CtrlProblema;
import domain.Ficha;

/**
 * Driver de la clase CtrlProblema
 * @author Joan Marc Pastor
 */
public class DriverCtrlProblema {
	
	public static void main(String[] args) {
		
		CtrlProblema CtrlP = CtrlProblema.getInstance();
		
		System.out.println("Loading problem: Problema_1");
		CtrlP.cargarProblema("Problema_1");
		CtrlP.imprimirEstadoProblemaConsola();
		
		System.out.println("Deleting Problema_1:");
		CtrlP.eliminarProblema();
		System.out.println("");

		System.out.println("Creating new empty problem:");
		CtrlP.crearProblema();
		CtrlP.setNombre("Problema_2");
		CtrlP.setColorAGanar(false);
		CtrlP.setMaxMovimientos(2);
		CtrlP.imprimirEstadoProblemaConsola();
		
		System.out.println("Setting FEN:");
		CtrlP.setFEN("8/8/8/8/3p4/5K2/4NN2/2R5");
		CtrlP.imprimirEstadoProblemaConsola();
		
		System.out.println("Adding pieces:");
		CtrlP.anadirFicha(Ficha.newFicha("k"), 6, 3);
		CtrlP.imprimirEstadoProblemaConsola();
		
		System.out.println("Validating problem:");
		boolean valid = CtrlP.validarProblema();
		if (valid) System.out.println("Problem validated successfully");
		else System.out.println("Problem is not valid");
		System.out.println("");
		
		System.out.println("Saving problem:");
		CtrlP.guardarProblema();

	}
	
}
