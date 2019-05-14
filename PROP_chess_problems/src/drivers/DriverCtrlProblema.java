package drivers;

import java.util.Scanner;

import domain.CtrlProblema;
import domain.Ficha;
import domain.Movimiento;

/**
 * Driver de la clase CtrlProblema
 */
public class DriverCtrlProblema {
	
	public static void main(String[] args) {
		
		CtrlProblema CtrlP = CtrlProblema.getInstance();
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {		
			boolean activo = CtrlP.hayProblemaCargado();
			boolean validado = CtrlP.estaValidado();
			System.out.println("Introduce un numero segun la funcion a probar:");
			System.out.println(" -1- Cargar problema existente");
			System.out.println(" -2- Crear problema");
			if (activo && !validado) System.out.println(" -3- Guardar problema");
			if (activo && !validado) System.out.println(" -4- Eliminar problema");
			if (activo && !validado) System.out.println(" -5- Anadir ficha al problema");
			if (activo && !validado) System.out.println(" -6- Retirar ficha del problema");
			if (activo && !validado) System.out.println(" -7- Mover ficha del problema");
			if (activo && !validado) System.out.println(" -8- Cargar FEN a problema");
			if (activo && !validado) System.out.println(" -9- Validar problema");
			System.out.println(" -10- EXIT");
			System.out.println("");
			
			int instr = sc.nextInt();
				
			switch (instr) {
			case 1:
				System.out.println("Introduce el nombre del problema que quieres cargar:");
				String nombre = sc.next();
				boolean exists = CtrlP.cargarProblema(nombre);
				if (exists) {
					System.out.println("Problema cargado correctamente");
					CtrlP.imprimirEstadoProblemaConsola();
				}
				else System.out.println("Problema no encontrado");
				break;
			case 2:
				System.out.println("Creando problema vacio:");
				CtrlP.crearProblema();
				boolean valid = false;
				while (!valid) {
					System.out.println("Introduce un nombre para el problema:");
					valid = CtrlP.setNombre(sc.next());
					if (!valid) System.out.println("Ya existe un problema con ese nombre");
				}
				System.out.print("Que Jugador empieza? (B/N): ");
				boolean color = true;
				if (sc.next().equals("B")) color = false;
				CtrlP.setColorAGanar(color);
				System.out.println("Indica el maximo de movimientos permitidos para hacer mate: ");
				CtrlP.setMaxMovimientos(sc.nextInt());
				System.out.println("Problema vacio creado");
				CtrlP.imprimirEstadoProblemaConsola();
				break;
			case 3:
				CtrlP.guardarProblema();
				break;
			case 4:
				CtrlP.eliminarProblema();
				break;
			case 5:
				System.out.println("Especifique que ficha quieres anadir en formato FEN: ");
				String fString = sc.next();
				System.out.println("Indica en que coordenadas quieres anadir la ficha (i j):");
				int i = sc.nextInt();
				int j = sc.nextInt();
				CtrlP.anadirFicha(Ficha.newFicha(fString), i, j);
				System.out.println("Ficha anadida correctamente");
				CtrlP.imprimirEstadoProblemaConsola();
				break;
			case 6:
				System.out.println("Indica las coordenadas de la casilla que quieres vaciar (i j):");
				i = sc.nextInt();
				j = sc.nextInt();
				CtrlP.retirarFicha(i, j);
				System.out.println("Casilla vaciada");
				CtrlP.imprimirEstadoProblemaConsola();
				break;
			case 7:
				System.out.println("Introduce el movimiento a realizar: (ficha, coordInicialI, coordInicialJ, coordFinalI, coordFinalJ)");
				String f = sc.next();
				int ii = sc.nextInt();
				int ij = sc.nextInt();
				int fi = sc.nextInt();
				int fj = sc.nextInt();
				Movimiento m = new Movimiento(ii, ij, fi, fj, Ficha.newFicha(f));
				CtrlP.moverFicha(m);
				System.out.println("Ficha movida");
				CtrlP.imprimirEstadoProblemaConsola();
				break;
			case 8:
				System.out.println("Introduce el FEN del problema (sin espacios): ");
				CtrlP.setFEN(sc.next());
				System.out.println("FEN cargado correctamente");
				CtrlP.imprimirEstadoProblemaConsola();
				break;
			case 9:
				valid = CtrlP.validarProblema();
				if (valid) System.out.println("El problema ha sido validado");
				else System.out.println("El problema no es valido");
				break;
			case 10:
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
