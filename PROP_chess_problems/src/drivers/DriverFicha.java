package drivers;

import java.util.Scanner;

import domain.Ficha;
import domain.FichaBishop;
import domain.FichaKing;
import domain.FichaKnight;
import domain.FichaPawn;
import domain.FichaQueen;
import domain.FichaRook;

public class DriverFicha {

	public static void main(String[] args) {

		Ficha f;
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {			
			System.out.println("Introduce un numero segun la funcion a probar:");
			System.out.println(" -1- Obtener nueva Ficha");
			System.out.println(" -2- Comparar Fichas");
			System.out.println(" -3- Obtener caracter en formato FEN de Ficha");
			System.out.println(" -4- EXIT");
			System.out.println("");
			
			int instr = sc.nextInt();
			
			switch (instr) {
			case 1:
				System.out.println("Introduce el caracter en formato FEN:");
				f = Ficha.newFicha(sc.next());
				if (f == null) System.out.println("No se ha creado el objeto. Caracter invalido");
				else if (f.getClass() == FichaPawn.class) System.out.println("Se ha creado un objeto FichaPawn");
				else if (f.getClass() == FichaKnight.class) System.out.println("Se ha creado un objeto FichaKnight");
				else if (f.getClass() == FichaBishop.class) System.out.println("Se ha creado un objeto FichaBishop");
				else if (f.getClass() == FichaRook.class) System.out.println("Se ha creado un objeto FichaRook");
				else if (f.getClass() == FichaQueen.class) System.out.println("Se ha creado un objeto FichaQueen");
				else if (f.getClass() == FichaKing.class) System.out.println("Se ha creado un objeto FichaKing");
				break;
			case 2:
				System.out.println("Esta funcionalidad compara si dos objetos Ficha son identicos (Deep Comparison2)");
				System.out.print("Introduce el caracter en formato FEN de la primera Ficha: ");
				Ficha f1 = Ficha.newFicha(sc.next());
				System.out.print("\nIntroduce el caracter en formato FEN de la segunda Ficha: ");
				Ficha f2 = Ficha.newFicha(sc.next());
				if (f1.equals(f2)) System.out.println("Las fichas son equivalentes");
				else System.out.println("Las fichas no 2son equivalentes");
				break;
			case 3:
				System.out.print("\nIntroduce el caracter en formato FEN de la ficha que quieres obtener su caracter: ");
				f = Ficha.newFicha(sc.next());
				if (f == null) System.out.println("\nCaracter introducido no valido");
				else System.out.println("\nEl caracter del objeto creado es: " + f.getCharacter());
				break;
			case 4:
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
