package data;

import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class DBPersona {

	private File fichero;
	JSONArray personas;
	JSONParser parser = new JSONParser();
	private static DBPersona single_instance = null;

	public static DBPersona getInstance() {  // DONE
		if (single_instance == null) 
			single_instance = new DBPersona(); 
		return single_instance; 
	}
	
	private DBPersona() { // DONE
		this.cargarFichero();
		System.out.println("Personas loaded: " + this.personas.toJSONString());
	}
	
	/**
	 * Carga el fichero DBPersonas.txt al estado interno del objeto en formato JSON
	 */
	private void cargarFichero() { // DONE
		try {
			this.fichero = new File("DBPersonas.txt");
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
					this.personas = new JSONArray();
					this.guardarFichero();
				}
				else {
					Object obj = parser.parse(content);
					this.personas = (JSONArray) obj;
				}
				
			} else { // FICHERO NO EXISTE
				this.fichero.createNewFile();
		        this.personas = new JSONArray();
		        this.guardarFichero();
			}
			
		} catch(Exception e) {
			System.out.println("Error cargando fichero DBPersonas.txt");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Guarda el estado interno del objeto en formato JSON al fichero DBPersonas.txt
	 */
	private void guardarFichero() { // DONE
		try {			
			String fileContent = this.personas.toJSONString();
			BufferedWriter writer = new BufferedWriter(new FileWriter("DBPersonas.txt"));
			writer.write(fileContent);
			writer.close();
		} catch (Exception err) {
			System.out.println("Error saving file DBPersonas.txt");
		}
	}
	
	/**
	 * Guarda una persona nueva en la base de datos y guarda el estado
	 * @param nombre String con el nombre de la persona
	 * @param contrasena String con la contrasena de la persona
	 */
	public void createPersona(String nombre, String contrasena) {
		JSONObject persona = new JSONObject();
		persona.put("nombre", nombre);
		persona.put("contrasena", contrasena);
		this.personas.add(persona);
		this.guardarFichero();
	}
	
	/**
	 * Consulta si el la base de datos existe cierta persona
	 * @param nombre String del nombre de la persona a consultar
	 * @return Ture en caso de que la persona exista. False en caso contrario.
	 */
	public boolean existsPersona(String nombre) { // DONE
		for (int i = 0; i < this.personas.size(); ++i) {
			JSONObject persona = (JSONObject) this.personas.get(i);
			if (persona.get("nombre").equals(nombre)) return true;
		}
		return false;
	}
	
	/**
	 * Consulta si un par usuario y contrasena son correctos
	 * @param nombre String indicando el nombre de la persona
	 * @param contrasena String indicando la contrasena de la persona
	 * @return
	 */
	public boolean checkLogIn(String nombre, String contrasena) { // DONE
		for (int i = 0; i < this.personas.size(); ++i) {
			JSONObject persona = (JSONObject) this.personas.get(i);
			if (persona.get("nombre").equals(nombre) && persona.get("contrasena").equals(contrasena)) return true;
		}
		return false;
	}
	
}
