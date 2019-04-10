package drivers;

import domain.Tablero;
import domain.Movimiento;
import java.util.List;
import java.util.Iterator;

public class DriverTablero {

	public static void main(String[] args) {
		
		Tablero t = new Tablero();
		
		System.out.println("Initial State:");
		t.imprimirEstadoTableroConsola();
		
		System.out.println("Cargar FEN:");
		t.cargarFEN("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B");
		t.imprimirEstadoTableroConsola();

		System.out.println("Exportar FEN:");
		String fen = t.exportarFEN();
		System.out.println(fen + "\n");
				
		System.out.println("Blancas puede realizar los siguientes movimientos:");
		List<Movimiento> movimientos = t.posiblesMovimientos("BLANCAS");
		Iterator<Movimiento> iterator = movimientos.iterator();
		Movimiento m = null;
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
	    	System.out.println(m.ficha + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");  
	    }
		System.out.println("");

		t.imprimirEstadoTableroConsola();
		System.out.println("Posicion del Rey Negro:");
		if (t.esJaqueEnPosicionANegras(3, 4)) System.out.println("(3,4) -> Jaque");
		else System.out.println("(3,4) -> No jaque");
		if (t.esJaqueEnPosicionANegras(0, 4)) System.out.println("(0,4) -> Jaque");
		else System.out.println("(0,4) -> No jaque");
		if (t.esJaqueEnPosicionANegras(0, 5)) System.out.println("(0,5) -> Jaque");
		else System.out.println("(0,5) -> No jaque");
		if (t.esJaqueEnPosicionANegras(3, 1)) System.out.println("(3,1) -> Jaque");
		else System.out.println("(3,1) -> No jaque");
		System.out.println("");
		
		System.out.println("Vaciar tablero:");
		t.limpiarTablero();
		t.imprimirEstadoTableroConsola();
		
		System.out.println("Insertar piezas:");
		t.anadirFicha("Q", 7, 3);
		t.anadirFicha("k", 0, 3);
		t.anadirFicha("r", 3, 0);
		//t.añadirFicha("R", 7, 2);
		//t.añadirFicha("R", 7, 4);
		t.imprimirEstadoTableroConsola();
		
		System.out.println("Negras puede realizar los siguientes movimientos:");
		movimientos = t.posiblesMovimientos("NEGRAS");
		iterator = movimientos.iterator();
		m = null;
		while(iterator.hasNext()) {
			m = (Movimiento) iterator.next();
	    	System.out.println(m.ficha + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");  
	    }
		System.out.println("");
		
		System.out.println("Realizar movimiento validado:");
		t.registrarMovimientoValidando(new Movimiento(3,0,3,3,"r"));
		t.imprimirEstadoTableroConsola();
		
		System.out.println("Realizar movimiento no validado:");
		boolean res = t.registrarMovimientoValidando(new Movimiento(3,3,3,7,"r"));
		if (!res) System.out.println("No se ha podido mover la torre negra");
		t.imprimirEstadoTableroConsola();
		
	}
	
}
