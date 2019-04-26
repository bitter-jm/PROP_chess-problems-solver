package drivers;

import java.util.Scanner;

import domain.Movimiento;
import domain.Ficha;

public class DriverMovimiento {

	public static void main(String[] args) {

		Movimiento m;
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {			
			System.out.println("Introduce un numero segun la funcion a probar:");
			System.out.println(" -1- Crear Movimiento");
			System.out.println(" -2- Comparar Movimientos");
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
				m = new Movimiento(i, j, ii, jj, f);
				System.out.println("Objeto movimiento creado con exito con los siguientes valores: " + m.ficha.getCharacter() + " - ("+m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");
				break;
			case 2:
				System.out.println("Creando Movimiento 1:");
				System.out.print("Introduce la ficha a mover en anotacion FEN: ");
				f = Ficha.newFicha(sc.next());
				System.out.print("Introduce las coordenadas iniciales de la ficha (i j): ");
				i = sc.nextInt();
				j = sc.nextInt();
				System.out.print("Introduce las coordenadas finales de la ficha (i j): ");
				ii = sc.nextInt();
				jj = sc.nextInt();
				Movimiento m1 = new Movimiento(i, j, ii, jj, f);
				System.out.println("Creando Movimiento 2:");
				System.out.print("Introduce la ficha a mover en anotacion FEN: ");
				f = Ficha.newFicha(sc.next());
				System.out.print("Introduce las coordenadas iniciales de la ficha (i j): ");
				i = sc.nextInt();
				j = sc.nextInt();
				System.out.print("Introduce las coordenadas finales de la ficha (i j): ");
				ii = sc.nextInt();
				jj = sc.nextInt();
				Movimiento m2 = new Movimiento(i, j, ii, jj, f);
				if (m1.equals(m2)) System.out.println("Movimiento 1 es equivalente a Movimiento 2");
				else System.out.println("Movimiento 1 no es equivalente a Movimiento 2");
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
