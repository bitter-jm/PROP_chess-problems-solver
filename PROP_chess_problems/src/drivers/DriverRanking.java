package drivers;
import java.util.Scanner;
import domain.Ranking;
/**
 * Driver de la clase Ranking
 */
public class DriverRanking {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Este es el driver de la clase Ranking que implementa los siguientes metodos:\n");
		System.out.println("1- Anadir al ranking\n");
		System.out.println("2- Eliminar del ranking\n");
		System.out.println("3- Imprimir el ranking\n");
		System.out.println("4- Probar mas de un metodo");
		System.out.println("Indica que metodo quieres probar escribiendo el numero correspondiente:\n");
		System.out.println("Pulsa 5 para terminar\n");
		int num = sc.nextInt();
		while (num!=5) {
			if (num==1) {
				Ranking r= new Ranking();
				System.out.println("Comprobamos la funcion anadir al ranking\n");
				System.out.println("Para terminar de probar esta funcionalidad y volver al menu escribe 0 \n"
						+ "sino escribe cualquier numero distinto para continuar anadiendo al ranking\n");
				int n = sc.nextInt();
				while (n!=0) {
					System.out.println("Introduce el nombre del usuario que quieres que se anada al ranking\n");
					String name = sc.next();
					System.out.println("Introduce la puntuacion que quieres que tenga el usuario\n");
					int pointss = sc.nextInt();	
					r.anadirAlRanking(name, pointss);
					System.out.println("Para dejar de a�adir al ranking escribe 0 sino "
							+ "sino escribe cualquier numero distinto para continuar anadiendo al ranking\n");
					n = sc.nextInt();
				}
				r.printranking();
			}
			else if (num==2) {
				System.out.println("Comprobamos la funcion eliminar del ranking\n");
				System.out.println("Si quieres eliminar de un ranking ya creado escribe true, si quieres crearte tu propio ranking elige false:\n");
				Boolean b= sc.nextBoolean();
				Ranking r= new Ranking();
				if (b) {
					r.anadirAlRanking("Clara", 34592);
					r.anadirAlRanking("Juan", 23543);
					r.anadirAlRanking("Pedro", 1273);
					r.anadirAlRanking("Natalia", 1232);
					r.anadirAlRanking("Roberto", 28762);
					r.anadirAlRanking("Jimena", 543);
					r.anadirAlRanking("Marc", 234);
					r.anadirAlRanking("Claudia", 918);
					r.anadirAlRanking("Antonio", 627);
					r.anadirAlRanking("Triana", 12);
					r.printranking();
					
				}
				else {
					System.out.println("Para dejar de a�adir al ranking escribe 0 sino \n "
							+ " sino escribe cualquier numero distinto para continuar anadiendo al ranking\n");
					int n = sc.nextInt();
					while (n!=0) {
						System.out.println("Introduce el nombre del usuario que quieres que se anada al ranking\n");
						String name = sc.next();
						System.out.println("Introduce la puntuacion que quieres que tenga el usuario\n");
						int pointss = sc.nextInt();	
						r.anadirAlRanking(name, pointss);
						System.out.println("Para dejar de a�adir al ranking escribe 0 sino "
								+ "sino escribe cualquier numero distinto para continuar anadiendo al ranking\n");
						n = sc.nextInt();
					}
				}
				r.printranking();
				System.out.println("Introduce el nombre del usuario que quieres eliminar del ranking\n");
				String name = sc.next();
				r.eliminardelranking(name);
				r.printranking();
			}
			
			else if (num==3) {
				System.out.println("Comprobamos la funcion imprimir ranking\n");
				System.out.println("Si quieres imprimir un ranking ya creado escribe true, si quieres crearte tu propio ranking elige false:\n");
				Boolean b= sc.nextBoolean();
				Ranking r= new Ranking();
				if (b) {
					r.anadirAlRanking("Clara", 34592);
					r.anadirAlRanking("Juan", 23543);
					r.anadirAlRanking("Pedro", 1273);
					r.anadirAlRanking("Natalia", 1232);
					r.anadirAlRanking("Roberto", 28762);
					r.anadirAlRanking("Jimena", 543);
					r.anadirAlRanking("Marc", 234);
					r.anadirAlRanking("Claudia", 918);
					r.anadirAlRanking("Antonio", 627);
					r.anadirAlRanking("Triana", 12);
					r.printranking();
					
				}
				else {
					System.out.println("Para dejar de a�adir al ranking escribe 0 sino \n"
							+ "sino escribe cualquier numero distinto para continuar anadiendo al ranking\n");
					int n = sc.nextInt();
					while (n!=0) {
						System.out.println("Introduce el nombre del usuario que quieres que se anada al ranking\n");
						String name = sc.next();
						System.out.println("Introduce la puntuacion que quieres que tenga el usuario\n");
						int pointss = sc.nextInt();	
						r.anadirAlRanking(name, pointss);
						System.out.println("Para dejar de a�adir al ranking escribe 0 sino "
								+ "sino escribe cualquier numero distinto para continuar anadiendo al ranking\n");
						n = sc.nextInt();
					}
				}
				System.out.println("Imprimimos el ranking\n");
				r.printranking();
			}
			else if (num==4){
				System.out.println("Indica que metodo quieres probar escribiendo el numero correspondiente:\n");
				System.out.println("1- Anadir al ranking\n");
				System.out.println("2- Eliminar del ranking\n");
				System.out.println("3- Imprimir el ranking\n");
				System.out.println("Pulsa 0 para terminar\n");
				int num2 = sc.nextInt();
				Ranking r = new Ranking();
				while (num2!=4) {
				if (num2==1) {
					
					System.out.println("Comprobamos la funcion anadir al ranking\n");
					System.out.println("Para terminar de probar esta funcionalidad y volver al menu escribe 0 \n"
							+ "sino escribe cualquier numero distinto para continuar anadiendo al ranking\n");
					int n = sc.nextInt();
					while (n!=0) {
						System.out.println("Introduce el nombre del usuario que quieres que se anada al ranking\n");
						String name = sc.next();
						System.out.println("Introduce la puntuacion que quieres que tenga el usuario\n");
						int pointss = sc.nextInt();	
						r.anadirAlRanking(name, pointss);
						System.out.println("Para dejar de a�adir al ranking escribe 0 sino "
								+ "sino escribe cualquier numero distinto para continuar anadiendo al ranking\n");
						n = sc.nextInt();
					}
					r.printranking();
				}
				else if (num2==2) {
					
					System.out.println("Introduce el nombre del usuario que quieres eliminar del ranking\n");
					String name = sc.next();
					r.eliminardelranking(name);
						
				}
				else if (num2==3) {
					System.out.println("Imprimimos el ranking\n");
					r.printranking();
				}
				else {
					System.out.println("El numero que has introducido no corresponde a ningun metodo\n");
					
				}
				System.out.println("Indica que metodo quieres probar escribiendo el numero correspondiente:\n");
				System.out.println("1- Anadir al ranking\n");
				System.out.println("2- Eliminar del ranking\n");
				System.out.println("3- Imprimir el ranking\n");
				System.out.println("Pulsa 4 para terminar\n");
				
				num2 = sc.nextInt();
				}
			}
			else {
				System.out.println("El numero que has introducido no corresponde a ningun metodo\n");
				
			}
			System.out.println("Indica que metodo quieres probar escribiendo el numero correspondiente:\n");
			System.out.println("1- Anadir al ranking\n");
			System.out.println("2- Eliminar del ranking\n");
			System.out.println("3- Imprimir el ranking\n");
			System.out.println("4- Probar mas de un metodo");
			System.out.println("Pulsa 5 para terminar\n");
			
			num = sc.nextInt();
		}
	}
}