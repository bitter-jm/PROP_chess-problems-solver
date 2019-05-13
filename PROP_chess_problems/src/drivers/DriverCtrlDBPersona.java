package drivers;

import data.CtrlDBPersona;

public class DriverCtrlDBPersona {

	public static void main(String[] args) {
		
		CtrlDBPersona dbP = CtrlDBPersona.getInstance();
		//dbP.createPersona("Evaristo", "1234");
		//dbP.createPersona("Eulogio", "4321");
		//dbP.createPersona("Eustaquio", "1221");
		
		System.out.println(dbP.existsPersona("Evaristo"));
		System.out.println(dbP.existsPersona("NombreNoGuay"));
		
		System.out.println(dbP.checkLogIn("Eulogio", "4321"));
		System.out.println(dbP.checkLogIn("Eustaquio", "1234"));
	}

}
