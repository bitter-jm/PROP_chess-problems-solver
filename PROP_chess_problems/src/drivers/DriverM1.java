package drivers;

import java.util.Iterator;
import java.util.List;
import domain.Ficha;
import domain.M1;
import domain.Movimiento;
import domain.Tablero;

public class DriverM1 {

	public static void main(String[] args) {

		//INICIALIZAR MAQUINAS
		M1 m1_1 = new M1();
		m1_1.setColor("BLANCAS");
		M1 m1_2 = new M1();
		m1_2.setColor("NEGRAS");
		
		//INICIALIZAR TABLERO
		Tablero t = new Tablero("8/8/8/8/3p4/5K2/3rNN2/2R5");
		//Tablero t = new Tablero("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B");
		//Tablero t = new Tablero("3r1n1k/ppr4b/1np2p1p/q4P2/3PN1QN/1B5P/PP4R1/3R2K1");
		//Tablero t = new Tablero("7k/2r4b/8/8/6Q1/8/6R1/6K1");
		//Tablero t = new Tablero(); t.anadirFicha(Ficha.newFicha("k"), 7, 6); t.anadirFicha(Ficha.newFicha("Q"), 4, 4); t.anadirFicha(Ficha.newFicha("Q"), 3, 5);
		t.imprimirEstadoTableroConsola();
		
		
		//REALIZAR MOVIMIENTO 1
		System.out.println("Blancas puede realizar los siguientes movimientos:");
		List<Movimiento> movimientos = t.posiblesMovimientos("BLANCAS");
		Iterator<Movimiento> iterator = movimientos.iterator();
		Movimiento m = null;
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
	    	System.out.println(m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");  
	    }
		m = m1_1.realizarMovimiento(t.exportarFEN());
		System.out.println("Realiza: " + m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");
		t.registrarMovimientoSinValidar(m);
		t.imprimirEstadoTableroConsola();
		System.out.println("");
		// OUT: N: (0,1) -> (1,3)
		
		//REALIZAR MOVIMIENTO 2
		System.out.println("Negras puede realizar los siguientes movimientos:");
		movimientos = t.posiblesMovimientos("NEGRAS");
		iterator = movimientos.iterator();
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
	    	System.out.println(m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");  
	    }
		m = m1_2.realizarMovimiento(t.exportarFEN());
		System.out.println("Realiza: " + m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");
		t.registrarMovimientoSinValidar(m);
		System.out.println("");
		t.imprimirEstadoTableroConsola();
		// OUT: q: (4,6) -> (1,3)
		
		
		//REALIZAR MOVIMIENTO 3
		System.out.println("Blancas puede realizar los siguientes movimientos:");
		movimientos = t.posiblesMovimientos("BLANCAS");
		iterator = movimientos.iterator();
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
	    	System.out.println(m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");  
	    }
		m = m1_1.realizarMovimiento(t.exportarFEN());
		System.out.println("Realiza: " + m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");
		t.registrarMovimientoSinValidar(m);
		System.out.println("");
		t.imprimirEstadoTableroConsola();
		// OUT: R: (2,0) -> (2,2)
		
		//REALIZAR MOVIMIENTO 4
		System.out.println("Negras puede realizar los siguientes movimientos:");
		movimientos = t.posiblesMovimientos("NEGRAS");
		iterator = movimientos.iterator();
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
	    	System.out.println(m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");  
	    }
		m = m1_2.realizarMovimiento(t.exportarFEN());
		System.out.println("Realiza: " + m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");
		t.registrarMovimientoSinValidar(m);
		System.out.println("");
		t.imprimirEstadoTableroConsola();
		// OUT: b: (0,3) -> (1,4)
		
		//REALIZAR MOVIMIENTO 5
		System.out.println("Blancas puede realizar los siguientes movimientos:");
		movimientos = t.posiblesMovimientos("BLANCAS");
		iterator = movimientos.iterator();
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
	    	System.out.println(m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");  
	    }
		m = m1_1.realizarMovimiento(t.exportarFEN());
		System.out.println("Realiza: " + m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");
		t.registrarMovimientoSinValidar(m);
		System.out.println("");
		t.imprimirEstadoTableroConsola();
		// OUT: B: (7,0) -> (4,3)
		
		
		//OBSERVAR POSIBLES MOVIMIENTOS:
		System.out.println("Negras puede realizar los siguientes movimientos:");
		movimientos = t.posiblesMovimientos("NEGRAS");
		iterator = movimientos.iterator();
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
	    	System.out.println(m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");  
	    }
		t.imprimirEstadoTableroConsola();
		System.out.println("Mate?");
		
	}

}
