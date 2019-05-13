package drivers;

import domain.CtrlPersona;

public class DriverCtrlPersona {

	public static void main(String[] args) {

		CtrlPersona ctrlP = CtrlPersona.getInstance();
		System.out.println(ctrlP.isLoggedIn());
		System.out.println(ctrlP.login("Evaristo", "1234"));
		System.out.println(ctrlP.isLoggedIn());
		System.out.println(ctrlP.getNombrePersona());
		
	}

}
