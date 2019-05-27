package drivers;

import java.util.Scanner;

import domain.Jugador;
import domain.Persona;

public class DriverPersona {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("MENU DRIVER DE PERSONA");
		System.out.println("FUNCIONALIDAD 1: Constructora con nombre");
		System.out.println("FUNCIONALIDAD 2: Constructora con nombre y contrasena");
		System.out.println("FUNCIONALIDAD 3: Cambiar contrasena");
		System.out.println("FUNCIONALIDAD 4: Salir");
		System.out.println("Introduce el numero de funcionalidad a probar:");
		int a = sc.nextInt();
		while(a!=5) {
			
			if(a==1) {
				System.out.println("Introduce el nombre de la persona:");
				String nom = sc.next();
				if(nom.length()<=3) System.out.println("ERROR: El nombre de usuario debe tener mas de 3 caracteres.");
				else {
					if(nom.matches("[^a-z].*") || nom.matches("[^A-Z].*") || nom.matches("[^0-9].*"))
						System.out.println("ERROR: El nombre de usuario no puede contener simbolos.");
					else {
						Persona p = new Persona(nom);
						System.out.println("Nombre introducido: "+ p.getNombre());
					}
				}
			}
				
			if(a==2) {
				System.out.println("Introduce el nombre de la persona:");
				String nom = sc.next();
				if(nom.length()<=3) System.out.println("ERROR: El nombre de usuario debe tener mas de 3 caracteres.");
				else {
					System.out.println("Introduce la contrasena:");
					String contra = sc.next();
					if(contra.length()<=3) System.out.println("ERROR: La contrasena debe tener mas de 3 caracteres.");
					else {
						Persona p = new Persona(nom,contra);
						System.out.println("Nombre introducido: "+ p.getNombre());
						System.out.println("Contrasena introducida: "+ p.getContrasena());
					}
				}
			}
			if(a==3) {
				Persona p = new Persona("Usuario","Ajedrez");
				System.out.println("Nombre de Persona creada por defecto: "+ p.getNombre());
				System.out.println("Contrasena actual de "+ p.getNombre() + ": " +p.getContrasena());
				System.out.println("Introduce la nueva contrasena:");
				String contra = sc.next();
				if(contra.length()<=3) {
					System.out.println("ERROR: La contrasenaa debe tener mas de 3 caracteres.");
					System.out.println("Se mantiene sin cambios: "+ p.getContrasena());
				}
				else {
					if(contra.equals(p.getContrasena())) {
						System.out.println("Antigua y nueva contrasena coinciden. Se mantiene sin cambios.");
					}
					else {
						p.setContrasena(contra);
						System.out.println("Nueva contrena introducida en "+p.getNombre()+ ": "+ p.getContrasena());
				
					}
				}	
			}
			System.out.println("Introduce el numero de funcionalidad a probar:");
			a = sc.nextInt();
		}
	}
}
