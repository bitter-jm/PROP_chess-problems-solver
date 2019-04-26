package drivers;

import java.util.Scanner;

import domain.M1;
import domain.Tablero;
import domain.Movimiento;

/**
 * Driver de la maquina M1
 */
public class DriverM1 {

	public static void main(String[] args) {
		
		
		M1 m1 = new M1();

		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {			
			System.out.println("Introduce un numero segun la funcion a probar:");
			System.out.println(" -1- Realizar movimiento");
			System.out.println(" -2- EXIT");
			System.out.println("");
			
			int instr = sc.nextInt();
			
			switch (instr) {
			case 1:
				System.out.println("Especifica el estado del tablero segun anotacion FEN (sin espacios):");
				String fen = sc.next();
				Tablero t = new Tablero(fen);
				t.imprimirEstadoTableroConsola();
				System.out.println("Indica en que equipo juega M1 (B/N):");
				String c = sc.next();
				if (c.equals("B")) m1.setColor("BLANCAS");
				else m1.setColor("NEGRAS");
				System.out.println("Indica hasta que profundidad quieres analizar (recomendado 4):");
				m1.setDepth(sc.nextInt());
				Movimiento m = m1.realizarMovimiento(fen);
				System.out.println("M1 va a mover: " + m.ficha.getCharacter() + " - ("+m.inicioI+","+m.inicioJ+") --> ("+m.finalI+","+m.finalJ+")");
				break;
			case 2:
				exit = true;
				break;
			default:
				System.out.println("Numero no valido");
			}
			System.out.println("");
		}
		
		sc.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/**
		//Problemas Jaque en 2: http://www.ajedrezdeataque.com/17%20Aprendizaje/2/Bloque1/Bloque1.htm

		//INICIALIZAR MAQUINAS
		M1 m1 = new M1();
		m1.setColor("BLANCAS");
		m1.setDepth(4);
		M1 m2 = new M1();
		m2.setColor("NEGRAS");
		m2.setDepth(4);
		
		//INICIALIZAR TABLERO
		//Tablero t = new Tablero("8/8/8/8/3p4/5K2/3kNN2/2R5"); // 1
		Tablero t = new Tablero("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B"); // 2

		System.out.println("Estado inicial:");
		t.imprimirEstadoTableroConsola();
		
		Movimiento m;
		System.out.println("Empezando a jugar:");
		
		m = m1.realizarMovimiento(t.exportarFEN());
		t.registrarMovimientoSinValidar(m);
		t.imprimirEstadoTableroConsola();
		System.out.println("Es mate? " + t.esMateColor("NEGRAS") + "\n");
		
		m = m2.realizarMovimiento(t.exportarFEN());
		t.registrarMovimientoSinValidar(m);
		t.imprimirEstadoTableroConsola();
		System.out.println("Es mate? " + t.esMateColor("BLANCAS") + "\n");
		
		m = m1.realizarMovimiento(t.exportarFEN());
		t.registrarMovimientoSinValidar(m);
		t.imprimirEstadoTableroConsola();
		System.out.println("Es mate? " + t.esMateColor("NEGRAS") + "\n");
		
		System.out.println("Prueba terminada.");
		*/
	}
}
