package drivers;

import domain.Movimiento;
import domain.Ficha;
import domain.CtrlPartida;
import java.util.*;

import java.util.Scanner;

/**
 * Driver de la clase CtrlPartida
 * @author Carla GarciaC
 */

public class DriverCtrlPartida {
	
	private static Movimiento leerMovimientoDeConsola(Scanner sc) {
		System.out.println("Introduce siguiente movimiento: (ficha, coordInicialI, coordInicialJ, coordFinalI, coordFinalJ)");
		String f = sc.next();
		int ii = sc.nextInt();
		int ij = sc.nextInt();
		int fi = sc.nextInt();
		int fj = sc.nextInt();
		return new Movimiento(ii, ij, fi, fj, Ficha.newFicha(f));
	}

	public static void main(String[] args) {
		
		CtrlPartida Ctrlp = CtrlPartida.getInstance();
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {			
			System.out.println("Introduce un numero segun la funcion a probar:");
			if (Ctrlp.partidaFinalizada()) System.out.println(" -1- Empezar Partida");
			if (!Ctrlp.partidaFinalizada()) System.out.println(" -2- Realizar Movimiento (Persona)");
			if (!Ctrlp.partidaFinalizada()) System.out.println(" -3- Cancelar Partida");
			System.out.println(" -4- Consultar si partida Finalizada");
			System.out.println(" -5- EXIT");
			System.out.println("");
			
			int instr = sc.nextInt();
				
			switch (instr) {
			case 1:
				String j1 = "MAQUINA1";
				String j2 = "MAQUINA1";
				System.out.println("Que tipo de partida quieres jugar:");
				System.out.println(" -1- Persona x Persona");
				System.out.println(" -2- M1 x M1");
				System.out.println(" -3- Persona x M1");
				System.out.println(" -4- M1 x Persona");
				System.out.println("");
				instr = sc.nextInt();
				if (instr == 1 || instr == 3) {
					System.out.print("Introduce el nombre de Persona 1: ");
					j1 = sc.next();
				}
				if (instr == 1 || instr == 4) {
					System.out.print("Introduce el nombre de Persona 2: ");
					j2 = sc.next();
				}	
				System.out.print("Introduce el nombre del problema a jugar: ");
				String nombreP = sc.next();
				Ctrlp.empezarPartida(j1, j2, nombreP);
				break;
			case 2:
				boolean movimientoAceptado = false;
				while (!movimientoAceptado) {
					Movimiento m = DriverCtrlPartida.leerMovimientoDeConsola(sc);
					movimientoAceptado = Ctrlp.personaRealizaMovimiento(m);
					if (!movimientoAceptado) System.out.println("Movimiento no valido");
				}					
				break;
			case 3:
				Ctrlp.cancelarPartida();
				System.out.println("Partida cancelada");
				break;
			case 4:
				if (Ctrlp.partidaFinalizada()) System.out.println("No hay ninguna partida en curso");
				else System.out.println("Hay una partida en curso");
				break;
			case 5:
				exit = true;
				break;
			default:
				System.out.println("Numero no valido");
			}
			System.out.println("");
		}
		
		sc.close();
		
	}

}
