package drivers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.CtrlData;
import domain.CtrlPersona;
import domain.CtrlProblema;
import domain.Ficha;
import domain.FichaBishop;
import domain.FichaKing;
import domain.FichaKnight;
import domain.FichaPawn;
import domain.FichaQueen;
import domain.FichaRook;
import domain.Movimiento;
import domain.CtrlPartida;
import domain.CtrlEvaluador;

public class DriverIntegration {

	public static void main(String[] args) {

		CtrlData ctrlD = CtrlData.getInstance();
		CtrlPersona ctrlPe = CtrlPersona.getInstance();
		CtrlProblema ctrlPr = CtrlProblema.getInstance();
		CtrlPartida ctrlPa = CtrlPartida.getInstance();
		CtrlEvaluador ctrlE = CtrlEvaluador.getInstance();
		
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {			
			System.out.println("\nIntroduce un numero segun la funcion a probar:");
			System.out.println(" **** USUARIOS ****");
			System.out.println(" -1- Iniciar sesion");
			System.out.println(" -2- Registrarse");
			System.out.println(" -3- Cerrar sesion");
			System.out.println(" -4- Consultar usuario activo");
			System.out.println(" **** CONSULTAR PROBLEMAS ****");
			System.out.println(" -5- Consultar problemas jugables");
			System.out.println(" -6- Consultar mis problemas");
			System.out.println(" **** EDITAR PROBLEMAS ****");
			System.out.println(" -7- Cargar problema existente al editor");
			System.out.println(" -8- Crear nuevo problema en el editor");
			System.out.println(" -9- Guardar problema");
			System.out.println(" -10- Eliminar problema");
			System.out.println(" -11- Anadir ficha al problema");
			System.out.println(" -12- Retirar ficha del problema");
			System.out.println(" -13- Mover ficha del problema");
			System.out.println(" -14- Cargar FEN a problema");
			System.out.println(" -15- Validar problema");
			System.out.println(" **** JUGAR PARTIDA ****");
			System.out.println(" -16- Empezar Partida");
			System.out.println(" -17- Realizar Movimiento (Persona)");
			System.out.println(" -18- Cancelar Partida");
			System.out.println(" -19- Consultar si Partida Finalizada");
			System.out.println(" **** CONSULTAR RANKINGS ****");
			System.out.println(" -20- Consultar ranking de un problema");
			System.out.println(" **** EVALUAR ****");
			System.out.println(" -21- Evaluar problemas");
			System.out.println(" -22- EXIT");
			System.out.println("");
			
			int instr = sc.nextInt();
			
			switch (instr) {
			case 1: // INICIAR SESION
				String nombre = null;
				String contrasena = null;
				while (nombre == null || !ctrlPe.login(nombre, contrasena)) {		
					if (nombre != null) System.out.println("Combinación nombre contrasena incorrectas. Intentelo de nuevo.");
					System.out.println("Introduce tu nombre de usuario (\"EXIT para cancelar\"): ");
					nombre = sc.next();
					if (nombre.equals("EXIT")) break;
					System.out.println("Introduce tu contrasena: ");
					contrasena = sc.next();				
				}
				System.out.println("Has iniciado sesion como: " + nombre);
				break;
			case 2: // REGISTRARSE
				nombre = null;
				contrasena = null;
				while (nombre == null || !ctrlPe.signIn(nombre, contrasena)) {		
					if (nombre != null) System.out.println("El usuario ya existe. Intentelo de nuevo.");
					System.out.println("Introduce tu nombre de usuario unico (\"EXIT para cancelar\"): ");
					nombre = sc.next();
					if (nombre.equals("EXIT")) break;
					System.out.println("Introduce tu contrasena: ");
					contrasena = sc.next();				
				}
				System.out.println("Has creado un nuevo usuario y te has registrado como: " + nombre);
				break;
			case 3: // CERRAR SESION
				if (ctrlPe.isLoggedIn()) {
					ctrlPe.logOut();
					System.out.println("Sesion finalizada.");
				} else {
					System.out.println("No tienes una sesion abierta.");
				}
				break;
			case 4: // CONSULTAR USUARIO ACTIVO
				if (ctrlPe.isLoggedIn()) {
					System.out.println("Tienes una sesion abierta como: " + ctrlPe.getNombrePersona());
				} else {
					System.out.println("No tienes una sesion abierta.");
				}
				break;
			case 5: // CONSULTAR PROBLEMAS JUGABLES
				String[][] problemasJugables = ctrlD.getProblemasJugables();
				System.out.println("Listando problemas jugables...");
				for (String[] pJugable : problemasJugables) {
					System.out.println("nombre: "+pJugable[0]+", #Jugadas: "+pJugable[1]+", colorAEmpezar: "+pJugable[2]+", dificultad: "+pJugable[3]+", vecesJugado: " + pJugable[4]);
				}
				break;
			case 6: // CONSULTAR MIS PROBLEMAS
				if (!ctrlPe.isLoggedIn()) {
					System.out.println("No tienes una sesion iniciada.");
					break;
				}
				String[][] misProblemas = ctrlD.getMisProblemas();
				System.out.println("Listando mis problemas...");
				for (String[] p : misProblemas) {
					System.out.println("nombre: "+p[0]+", #Jugadas: "+p[1]+", colorAEmpezar: "+p[2]+", valido: "+p[3]+", vecesJugado: " + p[4]);
				}
				break;
			case 7: // CARGAR PROBLEMA EXISTENTE AL EDITOR
				if (!ctrlPe.isLoggedIn()) {
					System.out.println("No hay una sesion de usuario iniciada.");
					break;
				}
				System.out.println("Escribe el nombre del problema que quieres cargar de la siguiente lista de tus problemas:");
				misProblemas = ctrlD.getMisProblemas();
				for (String[] p : misProblemas) {
					System.out.println("nombre: "+p[0]+", #Jugadas: "+p[1]+", colorAEmpezar: "+p[2]+", valido: "+p[3]+", vecesJugado: " + p[4]);
				}
				String nombreP = sc.next();
				boolean exists = ctrlPr.cargarProblema(nombreP);
				if (!exists) {
					System.out.println("El problema seleccionado no existe.");
					break;
				}
				System.out.println("Problema cargado correctamente");
				ctrlPr.imprimirEstadoProblemaConsola();
				
				break;
			case 8: // CREAR NUEVO PROBLEMA EN EL EDITOR
				if (!ctrlPe.isLoggedIn()) {
					System.out.println("No hay una sesion de usuario iniciada.");
					break;
				}
				System.out.println("Creando problema vacio:");
				ctrlPr.crearProblema();
				boolean valid = false;
				while (!valid) {
					System.out.println("Introduce un nombre para el problema:");
					valid = ctrlPr.setNombre(sc.next());
					if (!valid) System.out.println("Ya existe un problema con ese nombre");
				}
				System.out.print("Que Jugador empieza? (B/N): ");
				boolean color = true;
				if (sc.next().equals("B")) color = false;
				ctrlPr.setColorAGanar(color);
				System.out.println("Indica el maximo de movimientos permitidos para hacer mate: ");
				ctrlPr.setMaxMovimientos(sc.nextInt());
				System.out.println("Problema vacio creado");
				ctrlPr.imprimirEstadoProblemaConsola();
				break;
			case 9: // GUARDAR PROBLEMA
				if (!ctrlPr.hayProblemaCargado()) {
					System.out.println("No hay ningun problema activo en el editor.");
					break;
				}
				if (ctrlPr.estaValidado()) {
					System.out.println("El problema cargado ya está validado.");
					break;
				}
				if (!ctrlPe.isLoggedIn()) {
					System.out.println("No hay una sesion de usuario iniciada.");
					break;
				}
				ctrlPr.guardarProblema();
				System.out.println("Problema guardado.");
				break;
			case 10: // ELIMINAR PROBLEMA
				if (!ctrlPr.hayProblemaCargado()) {
					System.out.println("No hay ningun problema activo en el editor.");
					break;
				}
				if (!ctrlPr.haSidoJugado()) {
					System.out.println("El problema ya ha sido jugado. No se puede borrar.");
					break;
				}
				ctrlPr.eliminarProblema();
				System.out.println("Problema Eliminado.");
				break;
			case 11: // ANADIR FICHA AL PROBLEMA
				if (!ctrlPr.hayProblemaCargado()) {
					System.out.println("No hay ningun problema activo en el editor.");
					break;
				}
				if (ctrlPr.estaValidado()) {
					System.out.println("El problema cargado ya está validado.");
					break;
				}
				System.out.println("Especifique que ficha quieres anadir en formato FEN: ");
				String fString = sc.next();
				System.out.println("Indica en que coordenadas quieres anadir la ficha (i j):");
				int i = sc.nextInt();
				int j = sc.nextInt();
				ctrlPr.anadirFicha(Ficha.newFicha(fString), i, j);
				System.out.println("Ficha anadida correctamente");
				ctrlPr.imprimirEstadoProblemaConsola();
				break;
			case 12: // RETIRAR FICHA DEL PROBLEMA
				if (!ctrlPr.hayProblemaCargado()) {
					System.out.println("No hay ningun problema activo en el editor.");
					break;
				}
				if (ctrlPr.estaValidado()) {
					System.out.println("El problema cargado ya está validado.");
					break;
				}
				System.out.println("Indica las coordenadas de la casilla que quieres vaciar (i j):");
				i = sc.nextInt();
				j = sc.nextInt();
				ctrlPr.retirarFicha(i, j);
				System.out.println("Casilla vaciada");
				ctrlPr.imprimirEstadoProblemaConsola();
				break;
			case 13: // MOVER FICHA DEL PROBLEMA
				if (!ctrlPr.hayProblemaCargado()) {
					System.out.println("No hay ningun problema activo en el editor.");
					break;
				}
				if (ctrlPr.estaValidado()) {
					System.out.println("El problema cargado ya está validado.");
					break;
				}
				System.out.println("Introduce el movimiento a realizar: (ficha, coordInicialI, coordInicialJ, coordFinalI, coordFinalJ)");
				String f = sc.next();
				int ii = sc.nextInt();
				int ij = sc.nextInt();
				int fi = sc.nextInt();
				int fj = sc.nextInt();
				Movimiento m = new Movimiento(ii, ij, fi, fj, Ficha.newFicha(f));
				ctrlPr.moverFicha(m);
				System.out.println("Ficha movida");
				ctrlPr.imprimirEstadoProblemaConsola();
				break;
			case 14: // CARGAR FEN A PROBLEMA
				if (!ctrlPr.hayProblemaCargado()) {
					System.out.println("No hay ningun problema activo en el editor.");
					break;
				}
				if (ctrlPr.estaValidado()) {
					System.out.println("El problema cargado ya está validado.");
					break;
				}
				System.out.println("Introduce el FEN del problema (sin espacios): ");
				ctrlPr.setFEN(sc.next());
				System.out.println("FEN cargado correctamente");
				ctrlPr.imprimirEstadoProblemaConsola();
				break;
			case 15: // VALIDAR PROBLEMA
				if (!ctrlPr.hayProblemaCargado()) {
					System.out.println("No hay ningun problema activo en el editor.");
					break;
				}
				if (ctrlPr.estaValidado()) {
					System.out.println("El problema cargado ya está validado.");
					break;
				}
				if (!ctrlPe.isLoggedIn()) {
					System.out.println("No hay una sesion de usuario iniciada.");
					break;
				}
				valid = ctrlPr.validarProblema();
				if (valid) System.out.println("El problema ha sido validado");
				else System.out.println("El problema no es valido");
				break;
			case 16: // EMPEZAR PARTIDA
				if (!ctrlPe.isLoggedIn()) {
					System.out.println("No tienes una sesion iniciada.");
					break;
				}
				String j1 = null;
				String j2 = null;
				boolean validJ1 = false;
				while (!validJ1) {					
					System.out.println("Introduce el Jugador 1 (Tu usuario, \"MAQUINA1\" o \"MAQUINIA2\"):");
					j1 = sc.next();
					if (j1.equals(ctrlPe.getNombrePersona()) || j1.equals("MAQUINA1") || j1.equals("MAQUINA2")) validJ1 = true;
					if (!validJ1) System.out.println("Jugador 1 no valido.");
				}
				boolean validJ2 = false;
				while (!validJ2) {					
					System.out.println("Introduce el Jugador 2 (\"INVITADO\", \"MAQUINA1\" o \"MAQUINIA2\"):");
					j2 = sc.next();
					if (j2.equals("INVITADO") || j2.equals("MAQUINA1") || j2.equals("MAQUINA2")) validJ2 = true;
					if (!validJ2) System.out.println("Jugador 2 no valido.");
				}
				System.out.println("Introduce el nombre de un problema jugable de la siguiente lista:");
				problemasJugables = ctrlD.getProblemasJugables();
				for (String[] pJugable : problemasJugables) {
					System.out.println("nombre: "+pJugable[0]+", #Jugadas: "+pJugable[1]+", colorAEmpezar: "+pJugable[2]+", dificultad: "+pJugable[3]+", vecesJugado: " + pJugable[4]);
				}
				String problema = sc.next();
				ctrlPa.empezarPartida(j1, j2, problema);
				break;
			case 17: // REALIZAR MOVIMIENTO (PERSONA)
				if (!ctrlPe.isLoggedIn()) {
					System.out.println("No tienes una sesion iniciada.");
					break;
				}
				if (ctrlPa.partidaFinalizada()) {
					System.out.println("No hay partida en curso.");
					break;
				}
				boolean movimientoAceptado = false;
				while (!movimientoAceptado) {
					m = DriverIntegration.leerMovimientoDeConsola(sc);
					movimientoAceptado = ctrlPa.personaRealizaMovimiento(m);
					if (!movimientoAceptado) System.out.println("Movimiento no valido");
				}					
				break;
			case 18: // CANCELAR PARTIDA
				if (!ctrlPe.isLoggedIn()) {
					System.out.println("No tienes una sesion iniciada.");
					break;
				}
				if (ctrlPa.partidaFinalizada()) {
					System.out.println("No hay partida en curso.");
					break;
				}
				System.out.println("Partida cancelada.");
				ctrlPa.cancelarPartida();
				break;
			case 19: // CONSULTAR SI PARTIDA FINALIZADA
				if (!ctrlPe.isLoggedIn()) {
					System.out.println("No tienes una sesion iniciada.");
					break;
				}
				if (ctrlPa.partidaFinalizada()) {
					System.out.println("No hay ninguna partida en curso.");
				} else {
					System.out.println("Hay una partida en curso.");
				}
				break;
			case 20: // CONSULTAR RANKING DE UN PROBLEMA
				System.out.println("Introduce el nombre del problema jugable que quieres consultar rankings:");
				problemasJugables = ctrlD.getProblemasJugables();
				for (String[] pJugable : problemasJugables) {
					System.out.println("nombre: "+pJugable[0]+", #Jugadas: "+pJugable[1]+", colorAEmpezar: "+pJugable[2]+", dificultad: "+pJugable[3]+", vecesJugado: " + pJugable[4]);
				}
				String nombreProblema = sc.next();
				System.out.println("Ranking de "+nombreProblema+":");
				String[][] ranking = ctrlD.getMatrixRanking(nombreProblema);
				for (String[] lineaR : ranking) {
					System.out.println(lineaR[0] + " -> "+lineaR[1]+" puntos");
				}
				break;
			case 21: // EVALUAR PROBLEMAS
				System.out.println("Selecciona que maquina jugara como Jugador 1 (\"MAQUINA1\", \"MAQUINA2\"):");
				j1 = sc.next();
				System.out.println("Selecciona que maquina jugara como Jugador 2 (\"MAQUINA1\", \"MAQUINA2\"):");
				j2 = sc.next();
				System.out.println("Listando problemas jugables:");
				problemasJugables = ctrlD.getProblemasJugables();
				for (String[] pJugable : problemasJugables) {
					System.out.println("nombre: "+pJugable[0]+", #Jugadas: "+pJugable[1]+", colorAEmpezar: "+pJugable[2]+", dificultad: "+pJugable[3]+", vecesJugado: " + pJugable[4]);
				}
				System.out.println("Escribe los nombres de los problemas a evaluar acabado con \"END\":");
				List<String> aEvaluar = new ArrayList<String>();
				String nextP = sc.next();
				while (!nextP.equals("END")) {
					aEvaluar.add(nextP);
					nextP = sc.next();
				}
				String[][] ganadores = ctrlE.evaluarProblemas(aEvaluar, "MAQUINA2", "MAQUINA1");
				System.out.println("\nListando resultados:");
				for (String[] ganador : ganadores) {
					System.out.println("Problema: " + ganador[0] + " - Ganador: " + ganador[1]);
				}
				break;
			case 22:
				exit = true;
				break;
			default:
				System.out.println("Numero no valido");
			}
			System.out.println("");
		}
		
		sc.close();

	}

	private static Movimiento leerMovimientoDeConsola(Scanner sc) {
		System.out.println("Introduce siguiente movimiento: (ficha, coordInicialI, coordInicialJ, coordFinalI, coordFinalJ)");
		String f = sc.next();
		int ii = sc.nextInt();
		int ij = sc.nextInt();
		int fi = sc.nextInt();
		int fj = sc.nextInt();
		return new Movimiento(ii, ij, fi, fj, Ficha.newFicha(f));
	}
	
}
