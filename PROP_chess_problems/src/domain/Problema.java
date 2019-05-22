package domain;

import java.util.*;
import domain.Tablero;
import domain.Ficha;
import domain.Movimiento;

/**
 * Clase Problema
 */
public class Problema {
	
	enum Dificulty { Easy, Medium, Hard; }
	private String nombre;
	private Tablero tab;

	private Dificulty dificultad;
	private Boolean validado;
	private Integer vecesJugado;
	private Integer maxMovimientos;
	private Boolean colorAGanar; //quien ha de ganar empieza y es jugador1
	
	/**
	 * Creadora vacia de problema
	 */
	public Problema() {
		nombre = null;
		maxMovimientos = 0;
		tab = new Tablero();
		colorAGanar = null;	
		vecesJugado=0;
		validado = false;
		dificultad = null;
	}

	/**
	 *Creadora de problema inicializada
	 * @param nombre inicializa el atributo nombre
	 * @param maxmov inicializa el atributo maxMovimientos
	 * @param FEN inicializa el atributo tab
	 * @param Color inicializa el atributo colorAGanar
	 * @param valid inicializa el atributo validado
	 */
	public Problema (String nombre, Integer maxmov, String FEN, Boolean Color,Boolean valid, Integer vecesJugado, String dif)
	{
		this.nombre = nombre;
		maxMovimientos = maxmov;
		tab = new Tablero(FEN);
		colorAGanar = Color;	
		
		this.vecesJugado = vecesJugado;
		
		validado = valid;
		
		dificultad = null;
		if (dif != null) {
			if (dif.equals("EASY")) dificultad = Dificulty.Easy;
			if (dif.equals("MEDIUM")) dificultad = Dificulty.Medium;
			if (dif.equals("HARD")) dificultad = Dificulty.Hard;
		}
		
		if (validado && dificultad == null) CalculoDeDificultad(maxmov, FEN, Color);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getFEN_Tablero() {
		return tab.exportarFEN();
	}

	public Dificulty getDificultad() {
		return dificultad;
	}
	
	public String getDificultadString() {
		if (this.dificultad == null) return null;
		if (this.dificultad.equals(Dificulty.Easy)) return "EASY";
		if (this.dificultad.equals(Dificulty.Medium)) return "MEDIUM";
		return "HARD";
	}

	public Boolean getValidado() {
		return validado;
	}

	public Integer getVecesJugado() {
		return vecesJugado;
	}

	public Integer getMaxMovimientos() {
		return maxMovimientos;
	}

	public Boolean getColorAGanar() {
		return colorAGanar;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setFEN_Tablero(String FEN_Tablero) {
		tab = new Tablero(FEN_Tablero);
	}

	public void setVecesJugado(Integer vecesJugado) {
		 this.vecesJugado = vecesJugado;
	}
	public void setMaxMovimientos(Integer maxMovimientos) {
		this.maxMovimientos = maxMovimientos;
	}

	public void setColorAGanar(Boolean colorAGanar) {
		this.colorAGanar = colorAGanar;
	}
	
	public void anadirFicha(Ficha f, int i, int j) {
		this.tab.anadirFicha(f, i, j);
	}

	public void retirarFicha(int i, int j) {
		this.tab.quitarFicha(i, j);
	}
	
	public void moverFicha(Movimiento mov) {
		this.tab.registrarMovimientoSinValidar(mov);
	}
	
	
	//PARA CALCULAR LA DIFICULTAD DE UN PROBLEMA:
	//Miramos el numero de piezas que hay en el tablero inicial 
	//Es decir, proporcion de piezas mias con las enemigas 
	//el valor que tienen estas y numero de jugadas que hay
	
	/**
	 * metodo privado que calcula la dificultad de una partida segun el numero de movimientos que tenga
	 * quien empieza i la situacion inicial del tablero
	 * @param MaxMov indica el numero de movimientos que tiene
	 * @param FEN indica la situacion inicial del tablero
	 * @param Color indica el color que ha de ganar
	 */
	
	private void CalculoDeDificultad (Integer MaxMov, String FEN, Boolean Color ) {
		int Situacion = AnalizeBoard (FEN);
		int SituacionMov=0; //3 o 4 jugadas Medio
		if (MaxMov==1 || MaxMov==2 ) SituacionMov=130;//facil
		else if (MaxMov>3 ) SituacionMov=-130; //dificil*/
		
		if (!Color) {//BLANCAS
			Situacion += SituacionMov;
			if (Situacion >130) dificultad = Dificulty.Easy;
			else if (Situacion >-130) dificultad = Dificulty.Medium;
			else dificultad = Dificulty.Hard;
		}
		else {//NEGRAS
			Situacion -= SituacionMov;
			if (Situacion >130) dificultad = Dificulty.Hard;
			else if (Situacion >-130) dificultad = Dificulty.Medium;
			else dificultad = Dificulty.Easy;
		}
		
	}
	/**
	 * metodo privado que analiza el valor de un tablero inicializado
	 * @param fEN es el string que inicializa el tablero
	 * @return
	 */
	private Integer AnalizeBoard(String fEN) {
		int count=0;		
		for (int i=0; i<fEN.length(); ++i) {
			if (Character.isLetter(fEN.charAt(i))) {
				count += FigureEvaluation(fEN.charAt(i));
			}
		}
		return count;
	}
/**
 *  metodo privado que devuelve el valor de cada figura
 * @param letra indica que figura es
 * @return
 */
	private int FigureEvaluation(char letra) {
		if (letra=='P') return 10;
		if (letra=='p') return -10;
		if (letra=='N') return 30;
		if (letra=='n') return -30;
		if (letra=='B') return 30;
		if (letra=='b') return -30;
		if (letra=='R') return 50;
		if (letra=='r') return -50;
		if (letra=='Q') return 90;
		if (letra=='q') return -90;
		if (letra=='K') return 900;
		if (letra=='k') return -900;
		return 0;
	}
	
	/**
	 * Analiza si el problema es valido
	 * @return True si el problema es valido. False en caso contrario
	 */
	public boolean validarProblema() {
		if (this.colorAGanar == null || this.validado == true || this.maxMovimientos == 0 ) return false;
		this.CalculoDeDificultad(this.maxMovimientos, this.tab.exportarFEN(), this.colorAGanar);
		//Buscar dos reyes:		
		boolean reyB = false;		
		boolean reyN = false;		
		for (int i = 0; i < 8; ++i) {		
			for (int j = 0; j < 8; ++j) {		
				if (this.tab.consultarCasilla(i, j) != null) {		
					if (this.tab.consultarCasilla(i, j).getCharacter().equals("k")) {		
						if (reyN) return false; //Dos reyes negros existentes		
						reyN = true;		
					} else if (this.tab.consultarCasilla(i, j).getCharacter().equals("K")) {		
						if (reyB) return false; //Dos reyes blancos existentes		
						reyB = true;		
					}		
				}		
			}		
		}		
		if (!reyN || !reyB) return false; //Falta algun rey por colocar		
		//Buscar mate:
		String color = "BLANCAS";
		if (this.colorAGanar) color = "NEGRAS";
		boolean valid = this.busquedaExhaustivaMate(color, true, 1);
		this.validado = valid;
		return valid;
	}
	
	/**
	 * Funcion recursiva de fuerza bruta para buscar si se puede realizar mate en menos de movsJ1 movimientos
	 * @param color color a empezar a analizar. false -> Blancas, true -> Negras
	 * @param aGanar True si el jugador <em>color</em> es quien intenta hacer mate. False en caso contrario
	 * @param movsJ1 Movimientos restantes del jugador 1 para intentar hacer mate
	 * @return True si el jugador 1 puede hacer mate asegurado. False en caso contrario
	 */
	private boolean busquedaExhaustivaMate(String color, boolean aGanar, int movsJ1) {
		String colorOpuesto = "BLANCAS";
		if (color.equals("BLANCAS")) colorOpuesto = "NEGRAS";
		
		if (aGanar) { // Jugador a ganar
			if (movsJ1 > this.maxMovimientos) return false; //CASO BASE 1
			boolean mate = false;
			Movimiento m = null;
			List<Movimiento> movimientosPosibles = tab.posiblesMovimientos(color);
		    Iterator<Movimiento> iterator = movimientosPosibles.iterator();
			while(iterator.hasNext()) {
				m = (Movimiento) iterator.next();
				tab.registrarMovimientoSinValidar(m);
				mate = this.busquedaExhaustivaMate(colorOpuesto, false, movsJ1+1);
				tab.deshacer();
				if (mate) return true;
			}
			return false;
			
		} else { // Jugador a perder
			if (tab.esMateColor(color)) return true; //CASO BASE 2
			boolean mate = false;
			Movimiento m = null;
			List<Movimiento> movimientosPosibles = tab.posiblesMovimientos(color);
		    Iterator<Movimiento> iterator = movimientosPosibles.iterator();
			while(iterator.hasNext()) {
				m = (Movimiento) iterator.next();
				tab.registrarMovimientoSinValidar(m);
				mate = this.busquedaExhaustivaMate(colorOpuesto, true, movsJ1);
				tab.deshacer();
				if (!mate) return false;
			}
			return true;
		}
	}
	
	/**
	 * Imprime el estado del tablero por consola
	 */
	public void imprimirEstadoProblemaConsola() {
		this.tab.imprimirEstadoTableroConsola();
	}
	
}

