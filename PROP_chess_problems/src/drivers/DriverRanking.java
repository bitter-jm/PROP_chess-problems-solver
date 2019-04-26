package drivers;
import java.util.Scanner;

import domain.Ranking;
public class DriverRanking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Ranking r= new Ranking();
		
		//comprovacion de la funcion de calculo de puntuacion
		String nom = "Maria";
		int punt = r.getpuntuacion();
		System.out.println(nom + " tendra una puntuacion de " + punt+ "\n");
		r.anadirAlRanking(nom, punt);
		r.printranking();
		//a�adir a alguien al ranking
		System.out.println("Escribe nombre de persona \n");
		nom = sc.nextLine();
		System.out.println("Escribe puntuacion\n");
		punt = sc.nextInt();
		r.anadirAlRanking(nom, punt);
		r.printranking();
		//caso en que dos con la misma puntuacion: aparece arriba el mas reciente
		System.out.println("Se anade Pepita con 35000 puntos al ranking");
		r.anadirAlRanking("Pepita", 35);
		r.printranking();
		System.out.println("Se anade Jose con 35000 puntos al ranking");
		r.anadirAlRanking("Jose", 35);
		r.printranking();
		//caso en que la misma persona y misma puntuacion : no se duplica en el ranking
		System.out.println("Se a�ade Cris con 47234 puntos al ranking");
		r.anadirAlRanking("Cris", 47);
		r.printranking();
		System.out.println("Se vuelve a a�adir Cris con 47234 puntos al ranking");
		r.anadirAlRanking("Cris", 47);
		r.printranking();
		//una persona empeora su puntuacion: se queda la mejor
		System.out.println("Se vuelve a a�adir Cris con 42391 puntos al ranking");
		r.anadirAlRanking("Cris", 47);
		r.printranking();
		//una persona mejora su puntuacion: updetea su puntuacion a la mejor
		System.out.println("Se vuelve a a�adir Cris con 48359 puntos al ranking");
		r.anadirAlRanking("Cris", 47);
		r.printranking();
		//eliminar a alguien del ranking
		System.out.println("Se elimina a Pepita del ranking");
		r.eliminardelranking("Pepita");
		r.printranking();
	}

}
