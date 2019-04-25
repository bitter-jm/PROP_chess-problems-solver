package drivers;

import domain.CtrlPartida;
import domain.Ficha;
import domain.Movimiento;

import java.util.Scanner;

/**
 * Driver de la clase CtrlPartida
 */
public class DriverCtrlPartida {
	
	private static Movimiento leerMovimientoDeConsola() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter next move: (ficha, coordInicialI, coordInicialJ, coordFinalI, coordFinalJ)");
		String f = sc.next();
		int ii = sc.nextInt();
		int ij = sc.nextInt();
		int fi = sc.nextInt();
		int fj = sc.nextInt();
		return new Movimiento(ii, ij, fi, fj, Ficha.newFicha(f));
	}

	public static void main(String[] args) {
		
		CtrlPartida ctrlP = CtrlPartida.getInstance();
		ctrlP.empezarPartida("Eustaquio", "MAQUINA1", "prob1");
		
		while (!ctrlP.partidaFinalizada()) {
			boolean movimientoAceptado = false;
			while (!movimientoAceptado) {
				Movimiento m = DriverCtrlPartida.leerMovimientoDeConsola();
				movimientoAceptado = ctrlP.personaRealizaMovimiento(m);
				if (!movimientoAceptado) System.out.println("Movimiento no valido");
			}	
		}
		
		System.out.println("Partida Finalizada.");
		
	}

}
