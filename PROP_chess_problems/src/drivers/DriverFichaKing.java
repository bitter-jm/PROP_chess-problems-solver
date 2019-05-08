package drivers;

import domain.Ficha;
import domain.FichaKing;
import domain.Movimiento;
import java.util.*;

public class DriverFichaKing {

	public static void main(String[] args) {
		
		FichaKing f;
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {			
			System.out.println("Introduce un numero segun la funcion a probar:");
			System.out.println(" -1- Crear Ficha King");
			System.out.println(" -2- Obtener posibles movimientos");
			System.out.println(" -3- EXIT");
			System.out.println("");
			
			int instr = sc.nextInt();
			
			switch (instr) {
			case 1:
				System.out.print("Selecciona el color de la ficha (\"BLANCAS\"/\"NEGRAS\"): ");
				f = new FichaKing(sc.next());
				if (f != null) System.out.println("Objeto FichaKing creado con color \"" + f.color + "\"");
				break;
			case 2:
				System.out.println("Anotacion: Esta clase no es la responsable de comprobar si el movimiento no es valido por amenaza");
				System.out.println("Especifica el estado del tablero en formato FEN (todo seguido):");
				String fen = sc.next();
				// Inicializando casillas
				Ficha[][] casillas = new Ficha[8][8];
				boolean stop = false;
				int i = 0;
				int j = 0;
				for (int it = 0; it < fen.length() && !stop; ++it) {
					String c = String.valueOf(fen.charAt(it));
					if (Character.isDigit(c.charAt(0))) {
						j += Integer.parseInt(c);
					}
					else if (c.equals("/")) {
						i++;
						j = 0;
					}
					else if (c.equals(" ")) stop = true;
					else {		
						casillas[i][j] = Ficha.newFicha(c);
						if (j < 7) j++;
					}
				}
				// Imprimiendo casillas
				for (i = 0; i < 8; i++) {
					for (j = 0; j < 8; j++) {
						if (casillas[i][j] == null) System.out.print("- "); 
						else System.out.print(casillas[i][j].getCharacter() + " "); 
					}
					System.out.print("\n");
				}
				System.out.println("");
				// Obteniendo coordenadas
				System.out.println("Indica las coordenadas de FichaKing a analizar (de arriba a abajo, izquierda a derecha): ");
				i = sc.nextInt();
				j = sc.nextInt();
				if (casillas[i][j] == null || !casillas[i][j].ficha.equals("k")) {
					System.out.println("No hay un King en esas coordenadas");
					break;
				}
				// Obtener posibles movimientos
				System.out.println("Puede realizar los siguientes movimientos:");
				List<Movimiento> movs = casillas[i][j].posiblesMovimientos(i, j, casillas);
				Iterator<Movimiento> iterator = movs.iterator();
				Movimiento m = null;
				while(iterator.hasNext()) {
					m = (Movimiento) iterator.next();
			    	System.out.println(m.ficha.getCharacter() + ": (" + m.inicioI+","+m.inicioJ+") -> ("+m.finalI+","+m.finalJ+")");  
			    }
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
