package drivers;

import domain.Partida;
import java.util.*;
import domain.Problema;
import domain.Jugador;
import domain.M1;
import domain.Maquina;
import domain.Persona;
import domain.Movimiento;
import domain.Ficha;
/**
 *Driver de la clase Partida
 */
public class DriverPartida {

	private static Partida part;
	private static Scanner sc;

	public static void main(String[] args) {


		sc = new Scanner(System.in);
		System.out.println("DRIVER DE PARTIDA");
		System.out.println("Para inicializar una partida necesitamos inicializar un problema.");
		System.out.println("Introduce el nombre del problema:");
		String nomp = sc.nextLine();
		System.out.println("Introduce el numero maximo de movimientos del problema:");
		int nmaxp = sc.nextInt();
		System.out.println("Introduce un fen valido para problema:");
		String fen = sc.next();
		System.out.println("Introduce '0' si han de ganar blancas, '1' en caso de las negras");
		int c  = sc.nextInt();
		Boolean b;
		if (c == 0) b = false;
		else b = true;
		Problema prob = new Problema(nomp, nmaxp, fen, b, true);
		System.out.println("Selecciona el tipo de jugador1. Introduce '0' para Persona o '1' para Maquina");
		int x = sc.nextInt();
		Jugador j1, j2;
		if(x == 0) {
			System.out.println("Introduce el nombre del jugador:");
			String nom = sc.next();
			j1 = new Persona(nom);
		}
		else {
			j1 = new M1();
		}
		System.out.println("Selecciona el tipo de jugador2. Introduce '0' para Persona o '1' para Maquina");
		x = sc.nextInt();
		if(x == 0) {
			System.out.println("Introduce el nombre del jugador:");
			String nom = sc.next();
			j2 = new Persona(nom);
		}
		else {
			j2 = new M1();
		}
		System.out.println("Comienza el juego");
		part = new Partida(j1, j2, prob);
		while(part.mov_uno < part.max_mov) {
			System.out.println("Introcude las coordenadas iniciales y finales de la ficha a mover");
			int i, j, ii, jj;
			i = sc.nextInt();
			j = sc.nextInt();
			ii = sc.nextInt();
			jj = sc.nextInt();
			String f, color;
			System.out.println("Introcude el tipo de ficha(letra en minusculas) y el color(BLANCAS|NEGRAS) de la ficha a mover");
			f = sc.next();
			color = sc.next();
			Ficha ficha =Ficha.newFicha(f,color);
			Movimiento mov = new Movimiento(i, j, ii, jj, ficha);
			part.jugarPersona(mov);			
		}
		if (part.partidaAcabada()) {
			System.out.println("Numero de veces jugado : " + prob.getVecesJugado());
			System.out.println("Fecha de creación de partida " + part.getDia() +"/"+ part.getMes()+"/"+ part.getAno());
			System.out.println("Jugador1: " + part.getJugador1().getNombre());
			System.out.println("Jugador2: " + part.getJugador1().getNombre());
			System.out.println("Problema: " + prob.getNombre());
		}

	}
}
