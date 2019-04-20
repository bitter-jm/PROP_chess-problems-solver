package control;

public class CtrlDominioProblemas {

	//guarda los problemas en un tree map de problema??
	/*
	 * Donde la clave es String del creador y string del nom
	 * Nose si puede tener tres parametros sino un pair??
	 * 
	 * private TreeMap <String, String, Problema> Problemas;
	 * 
	 * public CtrlDominioProblemas(){
	 * Problemas = new TreeMap<String,String,Problema>();
	 * 
	 */
	
	public void crearProblema() {
		/*tiene que crear una entrada en el arbol con el identificador del 
		 * pair de strings y el Problema que sera un new.problema 
		 * La funcion ha de recibir pues todos los parametros necesarios para crear el problema
		 * YA NO HACE FALTA NOMBRE PROBLEMA
		 * Numero de Jugadas Quien gana y FEN.
		 * 
		 * Si no valido pedir nuevos?
		 */
	}
	public void editarProblema() {
		/*Busca el problema en el arbol, si no valido pedir otro problema y sino nada?
		 * Also si el problema editado deja de ser valido no guardar cambios?
		 * Ha de recibir la identificacion del problema y el del usuario
		 * pero son datos que vienen de sitios distintos
		 */
	}
	public void eliminarProblema() {
		/*Elimina el problema del arbol de problemas
		 * Ha de recibir la identificacion del problema y el del usuario
		 * pero son datos que vienen de sitios distintos
		 * */
	}
}
