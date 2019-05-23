package domain;

import java.util.Date;;

public class Cronometro {

	private long initialPoint;
	private long sum = 0;
	private boolean paused = true;
	
	/**
	 * Inicia el cronometro
	 */
	public void start() {
		this.paused = false;
		Date d = new Date();
		this.initialPoint = d.getTime();
	}
	
	/**
	 * Pausa el cronometro
	 */
	public void pause() {
		if (this.paused == false) {			
			this.paused = true;
			Date d = new Date();
			this.sum += d.getTime() - this.initialPoint;
		}
	}
	
	/**
	 * Reinicia el cronometro y lo inicia
	 */
	public void restart() {
		this.sum = 0;
		this.paused = false;
		Date d = new Date();
		this.initialPoint = d.getTime();
	}
	
	/**
	 * Obtiene el tiempo contado por el cronometro en segundos
	 * @return Integer con el tiempo actual del cronometro en segundos
	 */
	public int getTime() {
		if (this.paused == true) {
			return (int) (this.sum / 1000); 
		} else {
			Date d = new Date();
			return (int) ((this.sum + d.getTime() - this.initialPoint) / 1000) ; 
		}
	}
	
}
