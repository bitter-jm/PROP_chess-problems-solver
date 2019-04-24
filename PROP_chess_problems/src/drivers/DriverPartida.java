package drivers;

import domain.Partida;
import stubs.Problema;
import stubs.Jugador;
import java.util.*;

/**
 * 
 * @author Carla GarcíaC
 *
 */
public class DriverPartida {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		Problema prob = new Problema();
		System.out.println("Numero de veces jugado : " + prob.getVecesJugado());
		
		System.out.println("Introduce el nombre del jugador1");
		String s1 = sc.nextLine();
		System.out.println("Introduce el nombre del jugador2");
		String s2 = sc.nextLine();
		Jugador j1 = new Jugador(s1);
		Jugador j2 = new Jugador (s2);
		Partida part = new Partida(j1, j2, prob);
		
		System.out.println("Fecha de creación de partida " + part.getDia() +"/"+ part.getMes()+"/"+ part.getAño());
		System.out.println("Jugador1: " + part.getJugador1().getNombre());
		System.out.println("Jugador2: " + part.getJugador1().getNombre());
		
		part.acabarPartida();
		System.out.println("Numero de veces jugado : " + prob.getVecesJugado());
		System.out.println("Ha terminado la partida : " + part.partidaAcabada());
	}

}
