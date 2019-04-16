package drivers;

import java.util.Iterator;
import java.util.List;
import domain.Ficha;
import domain.M1v2;
import domain.Movimiento;
import domain.Tablero;

public class DriverM1v2 {

	public static void main(String[] args) {
		
		//Problemas faciles: http://www.ajedrezdeataque.com/17%20Aprendizaje/2/Bloque1/Bloque1.htm

		//INICIALIZAR MAQUINAS
		M1v2 m1 = new M1v2();
		m1.setColor("BLANCAS");
		M1v2 m2 = new M1v2();
		m2.setColor("NEGRAS");
		
		//INICIALIZAR TABLERO
		//Tablero t = new Tablero("8/8/8/8/3p4/5K2/3kNN2/2R5");
		Tablero t = new Tablero("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B");
		System.out.println("Estado inicial:");
		t.imprimirEstadoTableroConsola();
		
		Movimiento m;
		System.out.println("Empezando a jugar:");
		
		m = m1.realizarMovimiento(t.exportarFEN());
		System.out.println("mov calculado");
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
		
	}
}
