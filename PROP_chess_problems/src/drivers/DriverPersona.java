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
		System.out.println("FUNCIONALIDAD 4: Es persona?");
		System.out.println("FUNCIONALIDAD 5: Salir");
		System.out.println("Introduce el numero de funcionalidad a probar:");
		int a = sc.nextInt();
		while(a!=5) {
			if(a==1) {
				System.out.println("Introduce el nombre de la persona:");
				String nom = sc.next();
				Persona p = new Persona(nom);
				System.out.println("Nombre introducido: "+ p.getNombre());
			}
			if(a==2) {
				System.out.println("Introduce el nombre de la persona:");
				String nom = sc.next();
				System.out.println("Introduce la contrasena:");
				String contra = sc.next();
				Persona p = new Persona(nom,contra);
				System.out.println("Nombre introducido: "+ p.getNombre());
				System.out.println("Contrasena introducida: "+ p.getContrasena());
			}
			if(a==3) {
				System.out.println("Introduce el nombre de la persona:");
				String nom = sc.next();
				System.out.println("Introduce la contrasena:");
				String contra = sc.next();
				Persona p = new Persona(nom,contra);
				System.out.println("Nombre introducido: "+ p.getNombre());
				System.out.println("Contrasena introducida: "+ p.getContrasena());
				System.out.println("Introduce la nueva contrasena:");
				contra = sc.next();
				p.setContrasena(contra);
				System.out.println("Nueva contrasena introducida: "+ p.getContrasena());
			}
			if(a==4) {
				System.out.println("Introduce el nombre:");
				String nom = sc.next();
				Persona p = new Persona(nom);
				System.out.println("Es persona? "+ p.esPersona());
			}
			a = sc.nextInt();
		}
	}
}
