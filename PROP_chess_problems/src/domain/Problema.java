package domain;

import java.util.*;
import domain.Tablero;
//import domain.Ranking
public class Problema {
	
	enum Dificulty 
	{
		Easy, Medium, Hard;
	}
	private String nombre;
	private String FEN_Tablero;
	private Dificulty dificultad;
	private Boolean validado;
	private Integer vecesJugado;
	private Integer maxMovimientos;
	private Boolean colorAGanar; //quien ha de ganar empieza y es jugador1
//  private Ranking rank;
	
	public Problema() {
		nombre = null;
		maxMovimientos = 0;
		FEN_Tablero = null;
		colorAGanar = null;	
		vecesJugado=0;
		validado = false;
		dificultad = null;
	// rank = new Ranking();
	}
	
	public Problema (String Nombre, Integer maxmov, String FEN, Boolean Color)
	{
		nombre = Nombre;
		maxMovimientos = maxmov;
		FEN_Tablero = FEN;
		colorAGanar = Color;	
		
		this.vecesJugado=0;
		
		validado = esValido(FEN,Color);
		
		dificultad = null;
		if (validado) CalculoDeDificultad(maxmov, FEN, Color);
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getFEN_Tablero() {
		return FEN_Tablero;
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
	public void setFEN_Tablero(String fEN_Tablero) {
		FEN_Tablero = fEN_Tablero;
	}

	public void setVecesJugado(Integer vecesJugado) {
		 this.vecesJugado =vecesJugado;
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
		//Algoritmo que calcula la dificultad del problema
		int Situacion = AnalizeBoard (FEN);
		//NO HAY MOVIMIENTOS EXTRA
		int SituacionMov=0; //3 o 4 jugadas Medio
		if (MaxMov==1 || MaxMov==2 )SituacionMov=130;//facil
		else if (MaxMov>4 )SituacionMov=-130; //dificil*/
		
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
	
	private int evaluarTablero(Ficha[][] casillas) {
		int value = 0;
		int sign = 1;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (casillas[i][j] != null) {					
					if (casillas[i][j].color.equals("BLANCAS")) sign = 1;
					else sign = -1;
					if (casillas[i][j].ficha.equals("p")) value += 10 * sign; // PAWN
					else if (casillas[i][j].ficha.equals("n")) value += 30 * sign; // KNIGHT
					else if (casillas[i][j].ficha.equals("b")) value += 30 * sign; // BISHOP
					else if (casillas[i][j].ficha.equals("r")) value += 50 * sign; // ROOK
					else if (casillas[i][j].ficha.equals("q")) value += 90 * sign; // QUEEN
					else if (casillas[i][j].ficha.equals("k")) value += 900 * sign; // KING
				}
			}
		}
		return value;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean esValido (String FEN, boolean colorGanar) {
		Tablero tab = new Tablero(FEN);
		//ATENTOS A LA SIGUIENTE LINEA!!!
		if (maxMovimientos == 0) return false;
		int result = minimax( (maxMovimientos*2)-1, tab, colorGanar);
		
		if (!colorGanar && result == 9999999 ) return true;		
		else if (colorGanar && result == -9999999) return true;
		System.out.println("COLOR: " + colorGanar+ "result" + result + "\n");
		return false;		
	}

	
	
	private Integer minimax(Integer MaxMov, Tablero tab, boolean ColorGanar)  
	{
		
		
		if (MaxMov==0) {
			
			
			if (!colorAGanar && tab.esMateColor("BLANCAS")) {
				System.out.println("MATEBLANCAS: " + tab.esMateColor("BLANCAS") + "\n");
				return 9999999;//caso base hemos llegado al numero maximo de movimientos	
			}
			else if (colorAGanar && tab.esMateColor("NEGRAS")) {
				System.out.println("MATENEGRAS: " + tab.esMateColor("NEGRAS") + "\n");
				return -9999999;	
			}
			return evaluarTablero(tab.getCasillas()); //situacion actual en puntuacion 
			//y si es mate suma muchisimo
		}
		
		else if (!ColorGanar) { //BLANCAS
			
			
			List<Movimiento> movimientosPosibles = tab.posiblesMovimientos("BLANCAS");
			int mejorMov = -9999999;
			Iterator<Movimiento> iterator = movimientosPosibles.iterator();
			Movimiento m = null;
			while(iterator.hasNext()) {
				m = (Movimiento) iterator.next();
				tab.registrarMovimientoSinValidar(m);
				
				int valor= minimax( MaxMov-1, tab, true);
				if (valor > mejorMov) {
					mejorMov = valor;
				}
				tab.deshacer();
			}
			return mejorMov;
		}
		
		//se tiene que haber cambiado el caminominimax;
		else {
			List<Movimiento> movimientosPosibles = tab.posiblesMovimientos("NEGRAS");
			int mejorMov = 9999999;
			Iterator<Movimiento> iterator = movimientosPosibles.iterator();
			Movimiento m = null;
			while(iterator.hasNext()) {
				m = (Movimiento) iterator.next();
				tab.registrarMovimientoSinValidar(m);
				int valor= minimax( MaxMov-1, tab, false);
				if (valor < mejorMov) {
					mejorMov = valor;
				}
				tab.deshacer();
			}
			return mejorMov;
		}
			
	}
	
}
