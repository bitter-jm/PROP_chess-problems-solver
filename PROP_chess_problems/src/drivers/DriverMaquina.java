package drivers;

import java.util.Scanner;
import domain.Jugador;
import domain.Maquina;
import domain.M1;

/**
 *
 * @author Carla GarcíaC
 *
 */
public class DriverMaquina {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DRIVER DE PERSONA");
		System.out.println("FUNCIONALIDAD 1: Constructora");
		System.out.println("FUNCIONALIDAD 2: Cambia la profundidad del recorrido de jugadas");
		System.out.println("FUNCIONALIDAD 3: Salir");
		System.out.println("Introduce el numero de funcionalidad a probar:");
		int a = sc.nextInt();
		while(a!=3) {
			if (a==1) {
				M1 m = new M1();
				System.out.println("Introduce el color con el que juega(BLANCAS|NEGRAS):");
				String color = sc.next();
				m.setColor(color);
				System.out.println("La maquina creada con color " + color + " tiene nombre " + m.getNombre());
				System.out.println("Es persona? "+ m.esPersona());
				
			}
			if (a == 2) {
				M1 m = new M1();
				System.out.println("Profundidad actual: " + m.getDepth());
				System.out.println("Introduce la nueva profundidad:");
				int depth = sc.nextInt();
				m.setDepth(depth);
				System.out.println("Nueva profundidad introducida: " + m.getDepth());
				
			}
			a = sc.nextInt();
		}

	}

}
