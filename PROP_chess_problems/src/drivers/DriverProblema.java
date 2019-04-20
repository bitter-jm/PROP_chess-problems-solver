package drivers;

import domain.Problema;

public class DriverProblema {
	
	public static void main(String[] args) {
		
		//INITIALIZE PROBLEMA
		//CREAR PROBLEMA PASANDO LOS PARAMETROS DIRECTAMENTE:
		//mate en 2 por parte de las blancas
		Problema prob = new Problema("Problema_1", 2, "4r3/2r5/Bp2R2b/1n6/2n2Q2/3kNbRq/2p1p1N1/2K1B3 w - - 0 1", false);
		
		//Comprobacion de la creacion correcta de problema y de getters y setters:
		System.out.println("Nombre Problema: " + prob.getNombre() + "\n");
		System.out.println("Numero de Jugadas: " + prob.getMaxMovimientos() + "\n");
		System.out.println("FEN: " + prob.getFEN_Tablero() + "\n");
		
		String s;
		if (prob.getColorAGanar()) s="NEGRAS";
		else s= "BLANCAS";
		
		System.out.println("Color A Hacer Mate " + s + "\n");
		System.out.println("Veces Jugado " + prob.getVecesJugado() + "\n");
		System.out.println("EsValido " + prob.getValidado() + "\n");
		if(prob.getDificultad()==null) System.out.println("Dificultad sin determinar\n");
		else System.out.println("Dificultad " + prob.getDificultad() + "\n");
		
		//CREAR PROBLEMA VACIO Y USAR SETTERS PARA PASAR PARAMETROS:
		//Este problema no es valido
		Problema prob2 = new Problema();
		//Comprobacion de la creacion correcta de problema y de getters y setters:
		prob2.setNombre("Problema_2");
		System.out.println("Nombre Problema: " + prob.getNombre() + "\n");
		prob2.setMaxMovimientos(1);
		System.out.println("Numero de Jugadas: " + prob.getMaxMovimientos() + "\n");
		prob.setFEN_Tablero("5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5 w - - 0 1");
		System.out.println("FEN " + prob.getFEN_Tablero() + "\n");
		prob2.setColorAGanar(true);
		String s1;
		if (prob.getColorAGanar()) s1="NEGRAS";
		else s1="BLANCAS";
		System.out.println("Color A Hacer Mate " + s1 + "\n");
		//Las veces jugadas por default son 0;
		System.out.println("Veces Jugado: " + prob.getVecesJugado() + "\n");
		//Ha de dar Falso
		System.out.println("EsValido " + prob.getValidado() + "\n");
		if(prob.getDificultad()==null) System.out.println("Dificultad sin determinar\n");
		else System.out.println("Dificultad " + prob.getDificultad() + "\n");
		
	}
}
