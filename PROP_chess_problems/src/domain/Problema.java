package domain;

import java.util.*;
import domain.Tablero;

public class Problema {
	
	enum Dificulty { Easy, Medium, Hard; }
	private String nombre;
	private Tablero tab;

	private Dificulty dificultad;
	private Boolean validado;
	private Integer vecesJugado;
	private Integer maxMovimientos;
	private Boolean colorAGanar; //quien ha de ganar empieza y es jugador1
	
	public Problema() {
		nombre = null;
		maxMovimientos = 0;
		tab = new Tablero();
		colorAGanar = null;	
		vecesJugado=0;
		validado = false;
		dificultad = null;
	}
	
	public Problema (String nombre, Integer maxmov, String FEN, Boolean Color, Boolean valido)
	{
		this.nombre = nombre;
		maxMovimientos = maxmov;
		tab = new Tablero(FEN);
		colorAGanar = Color;	
		
		this.vecesJugado=0;
		
		validado = valido;
		
		dificultad = null;
		if (validado) CalculoDeDificultad(maxmov, FEN, Color);
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
	
	public void setNombre(String Nombre) {
		nombre = Nombre;
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

	
	
	//PARA CALCULAR LA DIFICULTAD DE UN PROBLEMA:
	//Miramos el numero de piezas que hay en el tablero inicial 
	//Es decir, proporcion de piezas mias con las enemigas 
	//el valor que tienen estas y numero de jugadas que hay
	private void CalculoDeDificultad (Integer MaxMov, String FEN, Boolean Color ) {
		int Situacion = AnalizeBoard (FEN);
		//NO HAY MOVIMIENTOS EXTRA
		int SituacionMov=0; //3 o 4 jugadas Medio
		if (MaxMov==1 || MaxMov==2 ) SituacionMov=130;//facil
		else if (MaxMov>4 ) SituacionMov=-130; //dificil*/
		
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
	
	private Integer AnalizeBoard(String fEN) {
		int count=0;		
		for (int i=0; i<fEN.length(); ++i) {
			if (Character.isLetter(fEN.charAt(i))) {
				count += FigureEvaluation(fEN.charAt(i));
			}
		}
		return count;
	}

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
	
	public boolean validarProblema() {
		this.CalculoDeDificultad(this.maxMovimientos, this.tab.exportarFEN(), this.colorAGanar);
		String color = "BLANCAS";
		if (this.colorAGanar) color = "NEGRAS";
		boolean valid = this.busquedaExhaustivaMate(color, true, 1);
		this.validado = valid;
		return valid;
	}
	
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
	
}
