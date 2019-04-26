package drivers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import domain.Ficha;
import domain.Tablero;
import domain.Movimiento;

class DriverTableroJUnit {
	
	Tablero t;

	@BeforeEach
	void setUp() throws Exception {
		t = new Tablero();
	}

	@Test
	void testAnadirFicha() {
		int i = ThreadLocalRandom.current().nextInt(0, 7 + 1);
		int j = ThreadLocalRandom.current().nextInt(0, 7 + 1);
		this.t.anadirFicha(Ficha.newFicha("p"), i, j);
		assertEquals(Ficha.newFicha("p"), t.consultarCasilla(i, j));
	}
	
	@Test
	void testMoverFichaSinValidar() {
		int i = ThreadLocalRandom.current().nextInt(0, 7 + 1);
		int j = ThreadLocalRandom.current().nextInt(0, 7 + 1);
		int ii = ThreadLocalRandom.current().nextInt(0, 7 + 1);
		int jj = ThreadLocalRandom.current().nextInt(0, 7 + 1);
		this.t.anadirFicha(Ficha.newFicha("p"), i, j);
		this.t.registrarMovimientoSinValidar(new Movimiento(i, j, ii, jj, Ficha.newFicha("p")));
		assertAll("Realiza movimiento sin validar correctamente", 
			() -> assertEquals(Ficha.newFicha("p"), t.consultarCasilla(ii, jj)),
			() -> assertNull(t.consultarCasilla(i, j)));
	}
	
	@Test
	void testRetirarFicha() {
		int i = ThreadLocalRandom.current().nextInt(0, 7 + 1);
		int j = ThreadLocalRandom.current().nextInt(0, 7 + 1);
		this.t.anadirFicha(Ficha.newFicha("p"), i, j);
		this.t.quitarFicha(i, j);
		assertNull(t.consultarCasilla(i, j));
	}

	@Test
	void testLimpiarTablero() {
		t.anadirFicha(Ficha.newFicha("K"), 0, 0);
		t.anadirFicha(Ficha.newFicha("r"), 7, 7);
		t.limpiarTablero();
		assertAll("Limpia el tablero correctamente",
			() -> assertNull(t.consultarCasilla(0, 0)),
			() -> assertNull(t.consultarCasilla(7, 7)));
	}
	
	@Test
	void testCargarFEN() {
		t.cargarFEN("8/8/8/8/3p4/5K2/4NN2/2R5");
		assertAll("Limpia el tablero correctamente",
			() -> assertEquals(Ficha.newFicha("p"), t.consultarCasilla(4, 3)),
			() -> assertEquals(Ficha.newFicha("K"), t.consultarCasilla(5, 5)),
			() -> assertEquals(Ficha.newFicha("N"), t.consultarCasilla(6, 4)),
			() -> assertEquals(Ficha.newFicha("N"), t.consultarCasilla(6, 5)),
			() -> assertEquals(Ficha.newFicha("R"), t.consultarCasilla(7, 2)));
	}
	
	@Test
	void testExportarFEN() {
		t.cargarFEN("8/8/8/8/3p4/5K2/4NN2/2R5");
		assertEquals("8/8/8/8/3p4/5K2/4NN2/2R5", t.exportarFEN());
	}
	
	@Test
	void testEsJaque() {
		t.anadirFicha(Ficha.newFicha("k"), 0, 0);
		t.anadirFicha(Ficha.newFicha("Q"), 7, 7);
		assertTrue(t.esJaque("NEGRAS"));
		assertFalse(t.esJaque("BLANCAS"));
		t.anadirFicha(Ficha.newFicha("K"), 0, 0);
		t.anadirFicha(Ficha.newFicha("q"), 7, 7);
		assertTrue(t.esJaque("BLANCAS"));
		assertFalse(t.esJaque("NEGRAS"));
	}
	
	@Test
	void testEsJaqueEnPosicion() {
		t.anadirFicha(Ficha.newFicha("Q"), 7, 7);
		assertTrue(t.esJaqueEnPosicionANegras(0, 0));
		assertFalse(t.esJaqueEnPosicionANegras(1, 0));
		t.anadirFicha(Ficha.newFicha("q"), 7, 7);
		assertTrue(t.esJaqueEnPosicionABlancas(0, 0));
		assertFalse(t.esJaqueEnPosicionABlancas(1, 0));
	}
	
	@Test
	void testEsMate() {
		t.anadirFicha(Ficha.newFicha("k"), 0, 3);
		t.anadirFicha(Ficha.newFicha("Q"), 7, 3);
		t.anadirFicha(Ficha.newFicha("R"), 7, 2);
		t.anadirFicha(Ficha.newFicha("R"), 7, 4);
		t.anadirFicha(Ficha.newFicha("K"), 7, 7);
		assertTrue(t.esMateColor("NEGRAS"));
		assertFalse(t.esMateColor("BLANCAS"));
	}
	
	@Test
	void testDeshacer() {
		t.anadirFicha(Ficha.newFicha("k"), 0, 3);
		t.anadirFicha(Ficha.newFicha("P"), 0, 4);
		t.registrarMovimientoValidando(new Movimiento(0, 3, 0, 4, Ficha.newFicha("k")));
		t.registrarMovimientoValidando(new Movimiento(0, 4, 0, 5, Ficha.newFicha("k")));
		t.deshacer();
		t.deshacer();
		assertEquals(Ficha.newFicha("k"), t.consultarCasilla(0, 3));
		assertEquals(Ficha.newFicha("P"), t.consultarCasilla(0, 4));
	}
	
	@Test
	void testPosiblesMovimientos() {
		t.cargarFEN("8/8/8/8/3p4/5K2/3kNN2/2R5");
		assertAll("Genera movimientos correctamente",
			() -> assertEquals(1, t.posiblesMovimientos("NEGRAS").size()),
			() -> assertEquals(30, t.posiblesMovimientos("BLANCAS").size()));

	}
	
	@Test
	void testMoverFichaValidando() {
		t.anadirFicha(Ficha.newFicha("k"), 0, 3);
		t.anadirFicha(Ficha.newFicha("Q"), 7, 3);
		t.anadirFicha(Ficha.newFicha("r"), 1, 0);
		assertTrue(t.registrarMovimientoValidando(new Movimiento(0, 3, 0, 4, Ficha.newFicha("k"))));
		t.deshacer();
		assertTrue(t.registrarMovimientoValidando(new Movimiento(1, 0, 1, 3, Ficha.newFicha("r"))));
		t.deshacer();
		assertFalse(t.registrarMovimientoValidando(new Movimiento(0, 3, 1, 3, Ficha.newFicha("k"))));
		t.deshacer();
		assertFalse(t.registrarMovimientoValidando(new Movimiento(0, 3, 0, 7, Ficha.newFicha("k"))));
		t.deshacer();
		assertFalse(t.registrarMovimientoValidando(new Movimiento(1, 0, 1, 1, Ficha.newFicha("r"))));
	}
	
}
