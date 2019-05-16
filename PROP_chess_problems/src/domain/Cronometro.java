package domain;

import java.util.Date;;

public class Cronometro {

	private long initialPoint;
	private long sum = 0;
	private boolean paused = true;
	
	public void start() {
		this.paused = false;
		Date d = new Date();
		this.initialPoint = d.getTime();
	}
	
	public void pause() {
		if (this.paused == false) {			
			this.paused = true;
			Date d = new Date();
			this.sum += d.getTime() - this.initialPoint;
		}
	}
	
	public void restart() {
		this.sum = 0;
		this.paused = false;
		Date d = new Date();
		this.initialPoint = d.getTime();
	}
	
	// Devuelve segundos
	public int getTime() {
		if (this.paused == true) {
			return (int) (this.sum / 1000); 
		} else {
			Date d = new Date();
			return (int) ((this.sum + d.getTime() - this.initialPoint) / 1000) ; 
		}
	}
	
}
