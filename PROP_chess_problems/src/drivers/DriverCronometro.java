package drivers;

import java.util.concurrent.TimeUnit;
import domain.Cronometro;

public class DriverCronometro {

	public static void main(String[] args) {
		try {
			
			Cronometro c = new Cronometro();
			c.start();
			TimeUnit.SECONDS.sleep(3);
			System.out.println(c.getTime() + " seg"); // 3 seg
			c.pause();
			TimeUnit.SECONDS.sleep(1);
			c.start();
			TimeUnit.SECONDS.sleep(2);
			c.pause();
			TimeUnit.SECONDS.sleep(1);
			System.out.println(c.getTime() + " seg"); // 5 seg
			
			
		}
		catch (Exception e) {
			System.out.println("Un errror ha ocurrido...");
		}
		
		
	}
	
}
