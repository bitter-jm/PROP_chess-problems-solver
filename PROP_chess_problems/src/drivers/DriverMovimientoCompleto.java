package drivers;

import java.util.Scanner;

import domain.Ficha;
import domain.Movimiento;
import domain.MovimientoCompleto;

public class DriverMovimientoCompleto {

	public static void main(String[] args) {

		Movimiento m;
		MovimientoCompleto mc;
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {			
			System.out.println("Introduce un numero segun la funcion a probar:");
			System.out.println(" -1- Crear MovimientoCompleto a partir de Strings");
			System.out.println(" -2- Crear MovimientoCompleto a partir de un Movimiento");
			System.out.println(" -3- EXIT");
			System.out.println("");
			
			int instr = sc.nextInt();
			
			switch (instr) {
			case 1:
				System.out.print("Introduce la ficha a mover en anotacion FEN: ");
				Ficha f = Ficha.newFicha(sc.next());
				System.out.print("Introduce las coordenadas iniciales de la ficha (i j): ");
				int i = sc.nextInt();
				int j = sc.nextInt();
				System.out.print("Introduce las coordenadas finales de la ficha (i j): ");
				int ii = sc.nextInt();
				int jj = sc.nextInt();
				System.out.println("Introduce la ficha capturada en anotacion FEN (\"-\" si no captura ninguna ficha)");
				String fcap = sc.next();
				if (fcap.equals("-")) mc = new MovimientoCompleto(i, j, ii, jj, f, null);
				else {					
					Ficha fc = Ficha.newFicha(fcap);
					mc = new MovimientoCompleto(i, j, ii, jj, f, fc);
				}
				System.out.print("MovimientoCompleto creado con exito con los siguientes valores: "+ mc.ficha.getCharacter() + " - ("+mc.inicioI+","+mc.inicioJ+") -> ("+mc.finalI+","+mc.finalJ+") ");
				if (mc.fichaMatada==null) System.out.println(" - No captura ninguna ficha");
				else System.out.println(" - captura " + mc.fichaMatada.getCharacter());
				break;
			case 2:
				System.out.println("Creando objeto Movimiento:");
				System.out.print("Introduce la ficha a mover en anotacion FEN: ");
				f = Ficha.newFicha(sc.next());
				System.out.print("Introduce las coordenadas iniciales de la ficha (i j): ");
				i = sc.nextInt();
				j = sc.nextInt();
				System.out.print("Introduce las coordenadas finales de la ficha (i j): ");
				ii = sc.nextInt();
				jj = sc.nextInt();
				m = new Movimiento(i, j, ii, jj, f);
				System.out.println("Objeto movimiento creado con exito con los siguientes valores: " + m.ficha.getCharacter() + " - ("+m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");
				System.out.println("Creando MovimientoCompleto a partir del anterior Movimiento:");
				System.out.println("Introduce la ficha capturada en anotacion FEN (\"-\" si no captura ninguna ficha)");
				fcap = sc.next();
				if (fcap.equals("-")) mc = new MovimientoCompleto(m, null);
				else {					
					Ficha fc = Ficha.newFicha(fcap);
					mc = new MovimientoCompleto(m, fc);
				}
				System.out.print("MovimientoCompleto creado con exito con los siguientes valores: "+ mc.ficha.getCharacter() + " - ("+mc.inicioI+","+mc.inicioJ+") -> ("+mc.finalI+","+mc.finalJ+") ");
				if (mc.fichaMatada==null) System.out.println(" - No captura ninguna ficha");
				else System.out.println(" - captura " + mc.fichaMatada.getCharacter());
				break;
			case 3:
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
