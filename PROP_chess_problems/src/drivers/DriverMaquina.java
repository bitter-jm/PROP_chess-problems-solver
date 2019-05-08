package drivers;

import java.util.Scanner;
import domain.Jugador;
import domain.Maquina;
import domain.M1;

public class DriverMaquina {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DRIVER DE MAQUINA");
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
				if(color.equals("BLANCAS")||color.equals("NEGRAS")) {
					m.setColor(color);
					System.out.println("La maquina creada con color " + color + " tiene nombre " + m.getNombre()+ " y produndidad " +m.getDepth());
					System.out.println("Es persona? "+ m.esPersona());
				}
				else {
					System.out.println("ERROR: Color inv�lido");
				}
				
			}
			if (a == 2) {
				M1 m = new M1();
				System.out.println("Profundidad actual: " + m.getDepth());
				System.out.println("Introduce la nueva profundidad:");
				int depth = sc.nextInt();
				if(depth > 0) {
					m.setDepth(depth);					
					System.out.println("Nueva profundidad introducida: " + m.getDepth());
				}
				else {
					System.out.println("ERROR: Profundidad inv�lida");
				}
				
				
			}
			System.out.println("Introduce el numero de funcionalidad a probar:");
			a = sc.nextInt();
		}

	}

}
