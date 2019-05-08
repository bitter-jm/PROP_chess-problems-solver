package stubs;

import java.util.Scanner;

import domain.Problema;
import domain.Jugador;
import domain.M1;
import domain.Persona;

/**
 * Stub del controlador de datos
 */
public class CtrlDB {
	
	private static CtrlDB single_instance = null;

	public Jugador getJugador(String nombre) {
		if (nombre.equals("MAQUINA1")) return new M1();
		return new Persona(nombre, "1234");
	}

	public Problema getProblema(String nombre) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Buscando " + nombre + " en la base de datos...");
		System.out.println("Introduce el FEN del problema (sin espacios): ");
		String fen = sc.next();
		System.out.print("Que Jugador empieza? (B/N): ");
		String colorS = sc.next();
		boolean color = false;
		if (colorS.equals("N")) color = true;
		System.out.print("Cuantos movimientos tiene para hacer mate?: ");
		int maxMovs = sc.nextInt();
		return new Problema(nombre, maxMovs, fen, color, true);
	}
	
	public void saveProblema(Problema p) {
		// Save or update p
		System.out.println("Problem has been saved successfully");
	}
	
	public void deleteProblema(Problema p) {
		//Elimina p
		System.out.println("Problem has been deleted successfully");
	}
	public boolean existsProblema(String nombre) {
		return false;
	}
	
	public static CtrlDB getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlDB(); 
        return single_instance; 
    }
	
}
