package drivers;

import java.util.Scanner;

import domain.Problema;
import domain.Ranking;
import domain.Ficha;
import domain.Movimiento;

/**
 * Driver de la clase Problema
 */
public class DriverProblema {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Este es el driver de la clase Problema que implementa las siguientes funciones:\n");
		System.out.println("1- Creadora de problema\n");
		System.out.println("2- Validar problema\n");
		System.out.println("3- Calcula la dificultad del problema\n");
		System.out.println("4- Imprimir estado problema por la consola\n");
		System.out.println("5- Anade una ficha al tablero del problema\n");
		System.out.println("6- Quita una ficha al tablero del problema\n");
		
		System.out.println("Indica que metodo quieres probar escribiendo el numero correspondiente:\n");
		System.out.println("Pulsa 7 para terminar\n");
		int num = sc.nextInt();
		while (num!=7) {
			
			if (num==1) {
				System.out.println("Comprobamos la creadora de Problema\n");
				System.out.println("Introduce el nombre del problema\n");
				String nom = sc.next();
				
				System.out.println("Introduce el numero de Jugadas.\n");
				int numero = sc.nextInt();
				
				System.out.println("Introduce el FEN del tablero del problema\n");
				String fen = sc.next();
				
				System.out.println("Introduce de que color es el primer jugador: "
						+ "false para blancas.\n" + "true para negras.\n");
				boolean color = sc.nextBoolean();
				
				Problema prob = new Problema(nom,numero,fen,color,false);
				System.out.println("El problema ha sido creado\n");
				System.out.println("El nombre del problema es : " +prob.getNombre()+"\n");
				System.out.println("El numero de jugadas del problema es :" + prob.getMaxMovimientos()+"\n");
				
				
				
			}
			else if (num==2) {
				System.out.println("Comprobamos la funcion de validar problema\n");
				System.out.println("Introduce un problema\n");
				System.out.println("Introduce el nombre del problema\n");
				String nom = sc.next();
				System.out.println("Introduce el numero de Jugadas\n");
				int numero = sc.nextInt();
				
				System.out.println("Introduce el FEN del tablero del problema\n");
				String fen = sc.next();
				
				System.out.println("Introduce de que color es el primer jugador: "
						+ "false para blancas.\n" + "true para negras.\n");
				boolean color = sc.nextBoolean();
				
				Problema prob = new Problema(nom,numero,fen,color,false);
				System.out.println("El problema es valido: " +prob.validarProblema()+"\n");
			
			}
			
			else if (num==3) {
				System.out.println("Comprobamos la funcion de dificultad problema\n");
				
				System.out.println("Introduce un problema\n");
				System.out.println("Introduce el nombre del problema\n");
				String nom = sc.next();
				System.out.println("Introduce el numero de Jugadas.\n");
				int numero = sc.nextInt();
				System.out.println("Introduce el FEN del tablero del problema\n");
				String fen = sc.next();
				System.out.println("Introduce de que color es el primer jugador: "
						+ "false para blancas.\n" + "true para negras.\n");
				boolean color = sc.nextBoolean();
				
				Problema prob = new Problema(nom,numero,fen,color,false);
				
				prob.validarProblema();
				System.out.println("La dificultad es " +prob.getDificultad()+"\n");				
			}
			
			else if (num==4) {
				System.out.println("Comprobamos la funcion de imprimir el tablero del problema\n");
				
				System.out.println("Introduce un problema\n");
				System.out.println("Introduce el nombre del problema\n");
				String nom = sc.next();
				System.out.println("Introduce el numero de Jugadas.\n");
				int numero = sc.nextInt();
				System.out.println("Introduce el FEN del tablero del problema\n");
				String fen = sc.next();
				System.out.println("Introduce de que color es el primer jugador: "
						+ "false para blancas.\n" + "true para negras.\n");
				boolean color = sc.nextBoolean();				
				Problema prob = new Problema(nom,numero,fen,color,false);
				
				System.out.println("Imprimimos el tablero\n");
				prob.imprimirEstadoProblemaConsola();
			}
			
			else if (num==5) {
				System.out.println("Comprobamos la funcion anadir ficha del tablero\n");
				
				System.out.println("Introduce un problema\n");
				System.out.println("Introduce el nombre del problema\n");
				String nom = sc.next();
				System.out.println("Introduce el numero de Jugadas.\n");
				int numero = sc.nextInt();
				System.out.println("Introduce el FEN del tablero del problema\n");
				String fen = sc.next();
				System.out.println("Introduce de que color es el primer jugador: "
						+ "false para blancas.\n" + "true para negras.\n");
				boolean color = sc.nextBoolean();				
				Problema prob = new Problema(nom,numero,fen,color,false);
				prob.imprimirEstadoProblemaConsola();
				
				System.out.println("Que color de ficha quieres anadir:\n BLANCAS o NEGRAS ");
				String colour= sc.next();
				System.out.println("Que tipo de ficha quieres anadir:\n"
						+"peon = p \n caballo = n \n alfil = b \n torre = r \n reina = q \n rey = k\n");
				String figura = sc.next();
				Ficha f = Ficha.newFicha(colour,figura);
				System.out.println("Donde quieres a�adir la ficha?\n");
				System.out.println("Fila [0-7]: \n");
				int i = sc.nextInt();
				System.out.println("Columna [0-7]: \n");
				int j = sc.nextInt();
				prob.anadirFicha(f, i, j);
				System.out.println("La ficha ha sido anadida");
						
			}
			else if (num==6) {
				System.out.println("Comprobamos la funcion quitar ficha del tablero\n");
				
				System.out.println("Introduce un problema\n");
				System.out.println("Introduce el nombre del problema\n");
				String nom = sc.next();
				System.out.println("Introduce el numero de Jugadas.\n");
				int numero = sc.nextInt();
				System.out.println("Introduce el FEN del tablero del problema\n");
				String fen = sc.next();
				System.out.println("Introduce de que color es el primer jugador: "
						+ "false para blancas.\n" + "true para negras.\n");
				boolean color = sc.nextBoolean();				
				Problema prob = new Problema(nom,numero,fen,color,false);
				prob.imprimirEstadoProblemaConsola();
				
				System.out.println("Introduce las coordenadas de la ficha a eliminar.\n");
				System.out.println("Fila [0-7]: \n");
				int i = sc.nextInt();
				System.out.println("Columna[0-7]: \n");
				int j = sc.nextInt();
				prob.retirarFicha(i, j);
				
				System.out.println("La ficha ha sido retirada");
				prob.imprimirEstadoProblemaConsola();						
			}
			
			else {
				System.out.println("El numero que has introducido no corresponde a ningun metodo\n");
				
			}
			System.out.println("Indica que metodo quieres probar escribiendo el numero correspondiente:\n");
			System.out.println("1- Creadora de problema\n");
			System.out.println("2- Validar problema\n");
			System.out.println("3- Calcula la dificultad del problema\n");
			System.out.println("4- Imprimir estado problema por la consola\n");
			System.out.println("5- Anade una ficha al tablero del problema\n");
			System.out.println("6- Quita una ficha al tablero del problema\n");
			System.out.println("Pulsa 7 para terminar\n");
			num = sc.nextInt();
		}
		
	}
}
	
