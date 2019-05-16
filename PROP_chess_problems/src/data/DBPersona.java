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
	
	public DBPersona() { // DONE
		this.cargarFichero();
		System.out.println("Actual content: " + this.personas.toJSONString());
	}
	
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
				System.out.println("Content readed: " + content);
				
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
	
	public void createPersona(String nombre, String contrasena) {
		JSONObject persona = new JSONObject();
		persona.put("nombre", nombre);
		persona.put("contrasena", contrasena);
		this.personas.add(persona);
		this.guardarFichero();
	}
	
	public boolean existsPersona(String nombre) { // DONE
		for (int i = 0; i < this.personas.size(); ++i) {
			JSONObject persona = (JSONObject) this.personas.get(i);
			if (persona.get("nombre").equals(nombre)) return true;
		}
		return false;
	}
	
	public boolean checkLogIn(String nombre, String contrasena) { // DONE
		for (int i = 0; i < this.personas.size(); ++i) {
			JSONObject persona = (JSONObject) this.personas.get(i);
			if (persona.get("nombre").equals(nombre) && persona.get("contrasena").equals(contrasena)) return true;
		}
		return false;
	}
	
}
