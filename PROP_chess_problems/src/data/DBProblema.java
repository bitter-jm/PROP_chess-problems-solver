package data;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class DBProblema {

	private File fichero;
	JSONArray problemas;
	JSONParser parser = new JSONParser();
	private static DBProblema single_instance = null;

	public static DBProblema getInstance() {  // DONE
		if (single_instance == null) 
			single_instance = new DBProblema(); 
		return single_instance; 
	}
	
	private DBProblema() { // DONE
		this.cargarFichero();
		System.out.println("Problemas loaded: " + this.problemas.toJSONString());
	}
	
	/**
	 * Carga el fichero DBProblema.txt al estado interno del objeto en formato JSON
	 */
	private void cargarFichero() { // DONE
		try {
			this.fichero = new File("DBProblemas.txt");
			if (this.fichero.exists()) { // FICHERO EXISTE
				// Reading File
				FileInputStream fis = new FileInputStream(this.fichero);
				byte[] data = new byte[(int) this.fichero.length()];
				fis.read(data);
				fis.close();

				// Parsing to String
				String content = new String(data, "UTF-8");
				
				// Parsing to JSON
				if (content.isEmpty()) {
					this.problemas = new JSONArray();
					// Insertar problemas DEFAULT
					this.createProblema("Problema1\n2\n8/8/8/8/3p4/5K2/3kNN2/2R5\nfalse\ntrue\n0\nDEFAULT\nEASY");
					this.createProblema("Problema2\n2\n8/6N1/6pp/6k1/4P1N1/6K/8/8\nfalse\ntrue\n0\nDEFAULT\nEASY");
					this.createProblema("Problema3\n2\n5Br1/6P1/5KBk/8/8/8/8/8\nfalse\ntrue\n0\nDEFAULT\nEASY");
					this.createProblema("Problema4\n3\n6K1/3r3r/5kn1/5p2/5P2/6N1/8/4R1R1\nfalse\ntrue\n0\nDEFAULT\nMEDIUM");
					this.createProblema("Problema5\n3\nr6k/pp2BpRp/8/8/5N3/7P/qPP5/2KR4\nfalse\ntrue\n0\nDEFAULT\nMEDIUM");
					this.createProblema("Problema6\n3\nkr5Q/p3q3/3R1p2/1P2B3/8/8/1K6/8\nfalse\ntrue\n0\nDEFAULT\nMEDIUM");
					this.guardarFichero();
				}
				else {
					Object obj = parser.parse(content);
					this.problemas = (JSONArray) obj;
				}
				
			} else { // FICHERO NO EXISTE
				this.fichero.createNewFile();
		        this.problemas = new JSONArray();
		        // Insertar problemas DEFAULT
				this.createProblema("Problema1\n2\n8/8/8/8/3p4/5K2/3kNN2/2R5\nfalse\ntrue\n0\nDEFAULT\nEASY");
				this.createProblema("Problema2\n2\n8/6N1/6pp/6k1/4P1N1/6K/8/8\nfalse\ntrue\n0\nDEFAULT\nEASY");
				this.createProblema("Problema3\n2\n5Br1/6P1/5KBk/8/8/8/8/8\nfalse\ntrue\n0\nDEFAULT\nEASY");
				this.createProblema("Problema4\n3\n6K1/3r3r/5kn1/5p2/5P2/6N1/8/4R1R1\nfalse\ntrue\n0\nDEFAULT\nMEDIUM");
				this.createProblema("Problema5\n3\nr6k/pp2BpRp/8/8/5N3/7P/qPP5/2KR4\nfalse\ntrue\n0\nDEFAULT\nMEDIUM");
				this.createProblema("Problema6\n3\nkr5Q/p3q3/3R1p2/1P2B3/8/8/1K6/8\nfalse\ntrue\n0\nDEFAULT\nMEDIUM");
		        this.guardarFichero();
			}
			
		} catch(Exception e) {
			System.out.println("Error cargando fichero DBPersonas.txt");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Guarda el estado interno del objeto en formato JSON al fichero DBProblema.txt
	 */
	private void guardarFichero() { // DONE
		try {			
			String fileContent = this.problemas.toJSONString();
			BufferedWriter writer = new BufferedWriter(new FileWriter("DBProblemas.txt"));
			writer.write(fileContent);
			writer.close();
		} catch (Exception err) {
			System.out.println("Error saving file DBProblemas.txt");
		}
	}
	
	/**
	 * Guarda la informacion de un nuevo problema
	 * @param data String con la informacion del problema en formato:
	 * nombreproblema + "\n" + maxmov + "\n" + FEN + "\n" + colorAGanar + "\n" + valido + "\n" + veces jugado + "\n" + autor + "\n" + dificultad
	 */
	public void createProblema(String data) {
		String[] probData = data.split("\n");
		JSONObject problema = new JSONObject();
		String nombre = probData[0];
		Integer maxmov = Integer.valueOf(probData[1]);
		String FEN = probData[2];
		boolean color = false;
		if (probData[3].equals("true")) color = true;
		boolean valid = false;
		if (probData[4].equals("true")) valid = true;
		Integer vecesJugado = Integer.valueOf(probData[5]);
		String autor = probData[6];
		String dificultad = probData[7];
		problema.put("nombre", nombre);
		problema.put("maxmov", maxmov);
		problema.put("FEN", FEN);
		problema.put("color", color);
		problema.put("valid", valid);
		problema.put("vecesJugado", vecesJugado);
		problema.put("autor", autor);
		problema.put("dificultad", dificultad);
		this.problemas.add(problema);
		this.guardarFichero();
	}
	
	/**
	 * Guarda la nueva informacion para un problema ya existente
	 * @param p String con la informacion del nuevo problema con el siguiente formato:
	 * nombreproblema + "\n" + maxmov + "\n" + FEN + "\n" + colorAGanar + "\n" + valido + "\n" + veces jugado + "\n" + autor + "\n" + dificultad
	 */
	public void updateProblema(String p) {
		String nombre = p.split("\n")[0];
		for (int i = 0; i < this.problemas.size(); ++i) {
			JSONObject problema = (JSONObject) this.problemas.get(i);
			if (problema.get("nombre").equals(nombre)) {
				this.problemas.remove(problema);
				this.createProblema(p);
			}
		}
	}
	
	/**
	 * Consulta si la informacion para un problema existe en la base de datos
	 * @param nombre Stringo con el nombre del problema
	 * @return True si el problema existe. False en caso contrario.
	 */
	public boolean existsProblema(String nombre) {
		for (int i = 0; i < this.problemas.size(); ++i) {
			JSONObject problema = (JSONObject) this.problemas.get(i);
			if (problema.get("nombre").equals(nombre)) return true;
		}
		return false;
	}
	
	/**
	 * Obtiene la informacion de un problema conreto
	 * @param nombre String del nombre del problema a obtener
	 * @return String con la informacion del problema en formato:
	 * nombreproblema + "\n" + maxmov + "\n" + FEN + "\n" + colorAGanar + "\n" + valido + "\n" + veces jugado + "\n" + autor + "\n" + dificultad
	 */
	public String getProblema(String nombre) {
		for (int i = 0; i < this.problemas.size(); ++i) {
			JSONObject problema = (JSONObject) this.problemas.get(i);
			if (problema.get("nombre").equals(nombre)) {
				String prob = problema.get("nombre")+"\n"+problema.get("maxmov")+"\n"+problema.get("FEN")+"\n"+problema.get("color")+
						"\n"+problema.get("valid")+"\n"+problema.get("vecesJugado")+"\n"+problema.get("autor")+"\n"+problema.get("dificultad");
				return prob;
			}
		}
		return null;
	}
	
	/**
	 * Elimina la informacion de un problema de la base de datos
	 * @param nombre String con el nombre del problema a eliminar
	 */
	public void deleteProblema(String nombre) {
		for (int i = 0; i < this.problemas.size(); ++i) {
			JSONObject problema = (JSONObject) this.problemas.get(i);
			if (problema.get("nombre").equals(nombre)) {
				this.problemas.remove(i);
				this.guardarFichero();
			}
		}
	}
	
	/**
	 * Obtiene la informacion de los problemas que pertenezcan la persona especificada
	 * @param nombrePersona String con el nombre de la persona
	 * @return List<String> donde cada elemento contiene la informacion de un problema en el siguiente formato:
	 * nombreproblema + "\n" + maxmov + "\n" + FEN + "\n" + colorAGanar + "\n" + valido + "\n" + veces jugado + "\n" + autor + "\n" + dificultad
	 */
	public List<String> getMisProblemas(String nombrePersona) {
		List<String> probs = new ArrayList<String>();
		for (int i = 0; i < this.problemas.size(); ++i) {
			JSONObject problema = (JSONObject) this.problemas.get(i);
			if (problema.get("autor").equals(nombrePersona)) {
				probs.add(this.getProblema((String) problema.get("nombre")));
			}
		}
		return probs;
	}
	
	/**
	 * Obtiene la informacion de los problemas jugables de todo el sistema
	 * @return List<Srting> en donde cada elemento contiene la informacion de un problema en el siguiente formato:
	 * nombreproblema + "\n" + maxmov + "\n" + FEN + "\n" + colorAGanar + "\n" + valido + "\n" + veces jugado + "\n" + autor + "\n" + dificultad
	 */
	public List<String> getProblemasJugables() {
		List<String> probs = new ArrayList<String>();
		for (int i = 0; i < this.problemas.size(); ++i) {
			JSONObject problema = (JSONObject) this.problemas.get(i);
			if ((boolean) problema.get("valid") == true) {
				probs.add(this.getProblema((String) problema.get("nombre")));
			}
		}
		return probs;
	}
	
	/**
	 * Modifica la informamcion de un problema de la base de datos para incrementar en uno las veces jugadas
	 * @param nombreProblema String del nombre del problema a incrementar
	 */
	public void incrementarVecesJugado(String nombreProblema) {
		for (int i = 0; i < this.problemas.size(); ++i) {
			JSONObject problema = (JSONObject) this.problemas.get(i);
			if (problema.get("nombre").equals(nombreProblema)) {
				// Increase & save
				Long valor;
				if (problema.get("vecesJugado") instanceof Long) valor = (Long) problema.get("vecesJugado");
				else valor = new Long((int) problema.get("vecesJugado"));
				valor++;
				problema.remove("vecesJugado");
				problema.put("vecesJugado", valor);
				this.guardarFichero();
			}
		}
	}
	
}
