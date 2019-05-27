package data;

import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class DBRanking {

	private File fichero;
	JSONArray rankings;
	JSONParser parser = new JSONParser();
	private static DBRanking single_instance = null;

	public static DBRanking getInstance() {  // DONE
		if (single_instance == null) 
			single_instance = new DBRanking(); 
		return single_instance; 
	}
	
	private DBRanking() { // DONE
		this.cargarFichero();
		System.out.println("Rankings loaded: " + this.rankings.toJSONString());
	}
	
	/**
	 * Carga el fichero DBRanking.txt al estado interno del objeto en formato JSON
	 */
	private void cargarFichero() { // DONE
		try {
			this.fichero = new File("DBRanking.txt");
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
					this.rankings = new JSONArray();
					this.guardarFichero();
				}
				else {
					Object obj = parser.parse(content);
					this.rankings = (JSONArray) obj;
				}
				
			} else { // FICHERO NO EXISTE
				this.fichero.createNewFile();
		        this.rankings = new JSONArray();
		        this.guardarFichero();
			}
			
		} catch(Exception e) {
			System.out.println("Error cargando fichero DBRanking.txt");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Guarda el estado interno del objeto en formato JSON al fichero DBRanking.txt
	 */
	private void guardarFichero() { // DONE
		try {			
			String fileContent = this.rankings.toJSONString();
			BufferedWriter writer = new BufferedWriter(new FileWriter("DBRanking.txt"));
			writer.write(fileContent);
			writer.close();
		} catch (Exception err) {
			System.out.println("Error saving file DBRanking.txt");
		}
	}
	
	/**
	 * Consulta si existe cierto ranking en la base de datos
	 * @param nombreProblema String con el nombre del problema
	 * @return Truen en caso de que exista. False en caso contrario.
	 */
	public boolean existsRanking(String nombreProblema) { // DONE
		for (int i = 0; i < this.rankings.size(); ++i) {
			JSONObject ranking = (JSONObject) this.rankings.get(i);
			if (ranking.get("nombreProblema").equals(nombreProblema)) {
				return true;
			}		
		}
		return false;
	}
	
	/**
	 * Guarda la informacion de un nuevo ranking en la base de datos
	 * @param dataRanking String con la informacion del ranking con el siguiente formato:
	 * nombrePersona1 + "?" + puntuacionPersona1 + "\n" + ...
	 * @param nombreProblema String indicando el nombre del problema que implementa
	 */
	public void createRanking(String dataRanking, String nombreProblema) {
		String[] rankingData = dataRanking.split("\n");
		JSONObject ranking = new JSONObject();
		JSONArray list = new JSONArray();
		ranking.put("nombreProblema", nombreProblema);
		for (String line : rankingData) {
			String[] info = line.split("\\?");
			JSONObject listElem = new JSONObject();
			listElem.put("nombre", info[0]);
			listElem.put("puntuacion", info[1]);
			list.add(listElem);
		}
		ranking.put("list", list);
		this.rankings.add(ranking);
		this.guardarFichero();
	}
	
	/**
	 * Actualiza la informacion de un ranking existente
	 * @param dataRanking String con la nueva informacion del ranking en formato:
	 * nombrePersona1 + "?" + puntuacionPersona1 + "\n" + ...
	 * @param nombreProblema String indicando el nombre de problema que implementa
	 */
	public void updateRanking(String dataRanking, String nombreProblema) {
		for (int i = 0; i < this.rankings.size(); ++i) {
			JSONObject problema = (JSONObject) this.rankings.get(i);
			if (problema.get("nombreProblema").equals(nombreProblema)) {
				this.rankings.remove(problema);
				this.createRanking(dataRanking, nombreProblema);
			}
		}
	}
	
	/**
	 * Obtiene la informacion de un ranking para determinado problema
	 * @param nombreProblema String indicando el nombre del problema
	 * @return String con la informacion del ranking en el siguiente formato:
	 * nombrePersona1 + "?" + puntuacionPersona1 + "\n" + ...
	 */
	public String getRankingProblema(String nombreProblema) { // DONE
		String out = "";
		for (int i = 0; i < this.rankings.size(); ++i) {
			JSONObject ranking = (JSONObject) this.rankings.get(i);
			if (ranking.get("nombreProblema").equals(nombreProblema)) {
				JSONArray list = (JSONArray) ranking.get("list");
				for (int j = 0; j < list.size(); ++j) {
					JSONObject linea = (JSONObject) list.get(j);
					out += linea.get("nombre")+"?"+linea.get("puntuacion")+"\n";
				}
				return out;
			}
		}
		return null;
	}
	
}

/*
STRING RESPONSE FORMAT:
usuario1?putuacion1\nusuario2?putuacion2\n
*/