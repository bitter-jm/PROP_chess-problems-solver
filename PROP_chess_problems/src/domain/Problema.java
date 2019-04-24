package domain;
import java.util.*;
import domain.Tablero;
//import stubs.Tablero;

public class Problema {
	
//tema: maxMovimientos y ColorAGanar
	enum Dificulty 
	{
		Easy, Medium, Hard;
	}
	//INICIALIZAR VALORES ASI
	private String Id;
	private String FEN_Tablero;
	private Dificulty Dificultad;
	private Boolean Validado;
	private Integer VecesJugado;
	private Integer MaxMovimientos;
	private Boolean ColorAGanar;//quien ha de ganar empieza y es jugador1
	
	public Problema() {
		Id = null;
		MaxMovimientos = 0;
		FEN_Tablero = null;
		ColorAGanar = null;	
		VecesJugado=0;
		Validado = false;
		Dificultad = null;
	}
	
	public Problema (String id, Integer maxmov, String FEN, Boolean Color)
	{
		Id = id;
		MaxMovimientos = maxmov;
		FEN_Tablero = FEN;
		ColorAGanar = Color;	
		
		VecesJugado=0;
		
		Validado = esValido(FEN,Color);
		
		
		
		Dificultad = null;
		if (Validado) CalculoDeDificultad(maxmov, FEN, Color);
	}
	
	
	//TODOS LOS GETTERS Y SETTERS
	public String getNombre() {
		return Id;
	}
	public void setNombre(String id) {
		Id = id;
	}
	public String getFEN_Tablero() {
		return FEN_Tablero;
	}
	public void setFEN_Tablero(String fEN_Tablero) {
		FEN_Tablero = fEN_Tablero;
	}
	public Dificulty getDificultad() {
		return Dificultad;
	}
	public Boolean getValidado() {
		return Validado;
	}

	public Integer getVecesJugado() {
		return VecesJugado;
	}
	public void setVecesJugado(Integer vecesJugado) {
		 VecesJugado =vecesJugado;
	}
	public Integer getMaxMovimientos() {
		return MaxMovimientos;
	}
	public void setMaxMovimientos(Integer maxMovimientos) {
		MaxMovimientos = maxMovimientos;
	}
	public Boolean getColorAGanar() {
		return ColorAGanar;
	}
	public void setColorAGanar(Boolean colorAGanar) {
		ColorAGanar = colorAGanar;
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
			if (Situacion >130) Dificultad = Dificulty.Easy;
			else if (Situacion >-130) Dificultad = Dificulty.Medium;
			else Dificultad = Dificulty.Hard;
		}
		else {//NEGRAS
			Situacion -= SituacionMov;
			if (Situacion >130) Dificultad = Dificulty.Hard;
			else if (Situacion >-130) Dificultad = Dificulty.Medium;
			else Dificultad = Dificulty.Easy;
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

	private boolean esValido (String FEN, boolean colorGanar) {
		Tablero tab = new Tablero(FEN);
		int result = minimax( (MaxMovimientos*2)-1, tab, colorGanar);
		
		if (!colorGanar && result == 9999999 ) return true;		
		else if (colorGanar && result == -9999999) return true;
		System.out.println("COLOR: " + colorGanar+ "result" + result + "\n");
		return false;		
	}
		
	

	private Integer minimax(Integer MaxMov, Tablero tab, boolean ColorGanar)  
	{
		
		;
		if (MaxMov==0) {
			
			
			if (!ColorAGanar && tab.esMateColor("BLANCAS")) {
				System.out.println("MATEBLANCAS: " + tab.esMateColor("BLANCAS") + "\n");
				return 9999999;//caso base hemos llegado al numero maximo de movimientos	
			}
			else if (ColorAGanar && tab.esMateColor("NEGRAS")) {
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
	
///CARLA
	
/*	
	private boolean esValido (String FEN, boolean colorGanar) {
		Tablero tab = new Tablero(FEN);
		int max= (MaxMovimientos*2)-1;
		int result = minimax(max, tab, colorGanar);
		if (!this.ColorAGanar && result == 9999999 ) return true;
		System.out.println("Blancas " + this.ColorAGanar + "\n");
		else if (this.ColorAGanar && result== -9999999) return true;
		System.out.println("Negras " + this.ColorAGanar + "\n");
		return false;		
	}
		
	

	private Integer minimax(Integer MaxMov, Tablero tab, boolean ColorGanar)  
	{
		if (MaxMov==0) {
			if (!ColorAGanar && tab.esMateColor("BLANCAS"))return 9999999;//caso base hemos llegado al numero maximo de movimientos
			else if (ColorAGanar && tab.esMateColor("NEGRAS")) return -9999999;
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
				if (valor > mejorMov) {
					mejorMov = valor;

				}
				tab.deshacer();
			}
			return mejorMov;
		}
			
	}
	
	*/
	
	
	
//////////////
	
	//funciones del ranking
	

	/* 
	 private boolean esValido (String FEN, boolean colorGanar) {
		Tablero tab = new Tablero(FEN);
		List<Movimiento> CaminoMinimax.empty();
		int result = minimax( MaxMovimientos, tab, colorGanar, CaminoMinimax);
		if (result>=0) {
			for (int i=0; i<CaminoMinimax.size(); ++i) {
				tab.registrarMovimientoValidando(CaminoMinimax.get(i));
			}
			if (colorGanar) return tab.esMateColor("BLANCAS");
			return tab.esMateColor("NEGRAS");
		}		
	}
		
	private Integer minimax(Integer MaxMov, Tablero tab, boolean ColorGanar, List<Movimiento> caminoMinimax)  
	{
		if (MaxMov==0) {
			if ()
			if (!ColorGanar && tab.esMateColor("BLANCAS"))return 9999999;//caso base hemos llegado al numero maximo de movimientos
			else if (ColorGanar && tab.esMateColor("NEGRAS")) return -9999999;
			return evaluarTablero(Tab); //situacion actual en puntuacion 
			//y si es mate suma muchisimo
		}
		
		if (!ColorGanar) { //BLANCAS
			List<Movimiento> movimientosPosibles = tab.posiblesMovimientos(ColorGanar);
			int mejorMov = -99999;
			for (int i=0;i< movimientosPosibles.length; ++i) {
				tab.registrarMovimientoSinValidar(m);
				int valor= minimax( MaxMov-1, tab, !ColorGanar);
				if (valor > mejorMov) {
					mejorMov = valor;
					CaminoMiniMax.pop();
					CaminoMiniMax.push(m);
				}
				tab.deshacer();
			}
		}
		//se tiene que haber cambiado el caminominimax;
		else {
			List<Movimiento> movimientosPosibles = tab.posiblesMovimientos(ColorGanar);
			int mejorMov = 99999;
			for (int i=0;i< movimientosPosibles.length; ++i) {
				tab.registrarMovimientoSinValidar(m);
				int valor= minimax( MaxMov-1, tab, !ColorGanar);
				if (valor > mejorMov) {
					mejorMov = valor;
					CaminoMiniMax.pop();
					CaminoMiniMax.push(m);
				}
				tab.deshacer();
			}
			
		}
		return mejorMov;	
	}
	 
	*/ 
}
