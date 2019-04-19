package domain;
//import domain.Tablero
import stubs.Tablero;
public class Problema {
//tema: maxMovimientos y ColorAGanar
	enum Dificulty 
	{
		Easy, Medium, Hard;
	}
	//INICIALIZAR VALORES ASI
	private String Nombre;
	private String FEN_Tablero;
	private Dificulty Dificultad;
	private Boolean Validado;
	private Integer VecesJugado;
	private Integer MaxMovimientos;
	private Boolean ColorAGanar;//quien ha de ganar empieza y es jugador1

	
	//CREADORAS:
	//ME FALTA UNA CREADORA VACIA??
	
	public Problema() {
		Nombre = null;
		MaxMovimientos = 0;
		FEN_Tablero = null;
		ColorAGanar = null;	
		VecesJugado=0;
		Validado = false;
		Dificultad = null;
	}
	
	public Problema (String nom, Integer maxmov, String FEN, Boolean Color)
	{
		Nombre = nom;
		MaxMovimientos = maxmov;
		FEN_Tablero = FEN;
		ColorAGanar = Color;	
		
		VecesJugado=0;
		/*
		Validado = esValido(FEN,Color);
		esValido(FEN,Color);*/
		Validado=true;
		
		Dificultad = null;
		if (Validado) CalculoDeDificultad(maxmov, FEN, Color);
	}
	
	
	//TODOS LOS GETTERS Y SETTERS
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
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
	/*

	private boolean esValido (String FEN, boolean colorGanar) {
		Tablero tab = new.Tablero(FEN);
		List<Movimiento> CaminoMinimax;
		int result = minimax( MaxMov, Tab,ColorGanar, CaminoMinimax);
		if (result>=0) {
			for (int i=0; i<CaminoMinimax.length; ++i) {
				tab.registrarMovimientoValidando(CaminoMinimax[i]);
			}
			if (ColorGanar) return tab.esMateColor("BLANCAS");
			return tab.esMateColor("NEGRAS");
		}		
	}
	
	
	private Integer minimax( MaxMov, Tab, ColorGanar,List <Movimiento>& CaminoMiniMax)  
	{
		if (MaxMov==0) {//caso base hemos llegado al numero maximo de movimientos
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
	//funciones del ranking
}
