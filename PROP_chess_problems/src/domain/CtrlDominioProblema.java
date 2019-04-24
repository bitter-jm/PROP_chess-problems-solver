package domain;
import domain.Problema;

public class CtrlDominioProblema {

	private static CtrlDominioProblema single_instance = null;
	
	public static CtrlDominioProblema getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlDominioProblema(); 
        return single_instance; 
    }
	
	//guarda los problemas en un tree map de problema??
		/*
		 * Donde la clave es String del creador y string del nom
		 * Nose si puede tener tres parametros sino un pair??
		 * 
		 * private TreeMap <int, Problema> Problemas;
		 * 
		 * public CtrlDominioProblemas(){
		 * Problemas = new TreeMap<int,Problema>();
		 * 
		 */
		/*
		public void crearProblema(String nom, Integer maxmov, String FEN, Boolean Color) {
			/*tiene que crear una entrada en el arbol con el identificador del 
			 * pair de strings y el Problema que sera un new.problema 
			 * La funcion ha de recibir pues todos los parametros necesarios para crear el problema
			 * YA NO HACE FALTA NOMBRE PROBLEMA
			 * Numero de Jugadas Quien gana y FEN.
			 * 
			 * Si no valido pedir nuevos?
			 */
		/*
			public Problema (String nom, Integer maxmov, String FEN, Boolean Color)
			{
				Nombre = nom;
				MaxMovimientos = maxmov;
				FEN_Tablero = FEN;
				ColorAGanar = Color;	
				
				VecesJugado=0;
				/*
				Validado = esValido(FEN,Color);
				
				
				
				Dificultad = null;
				if (Validado) CalculoDeDificultad(maxmov, FEN, Color);
			}
			
		}
		public void editarProblema() {
			/*Busca el problema en el arbol, si no valido pedir otro problema y sino nada?
			 * Also si el problema editado deja de ser valido no guardar cambios?
			 * Ha de recibir la identificacion del problema y el del usuario
			 * pero son datos que vienen de sitios distintos
			 *//*
			setValidado= esValidado(fen color);
			setvalidao (FEn color)
		}
		public void eliminarProblema() {
			/*Elimina el problema del arbol de problemas
			 * Ha de recibir la identificacion del problema y el del usuario
			 * pero son datos que vienen de sitios distintos
			 * */
	
}
