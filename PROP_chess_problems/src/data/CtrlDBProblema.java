package data;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class CtrlDBProblema {

	private File fichero;
	JSONArray problemas;
	JSONParser parser = new JSONParser();
	private static CtrlDBProblema single_instance = null;

	public static CtrlDBProblema getInstance() {  // DONE
		if (single_instance == null) 
			single_instance = new CtrlDBProblema(); 
		return single_instance; 
	}
	
	public CtrlDBProblema() { // DONE
		this.cargarFichero();
		System.out.println("Actual content: " + this.problemas.toJSONString());
	}
	
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
				System.out.println("Content readed: " + content);
				
				// Parsing to JSON
				if (content.isEmpty()) {
					this.problemas = new JSONArray();
					// Insertar problemas DEFAULT
					this.createProblema("Problema1\n2\n8/8/8/8/3p4/5K2/3kNN2/2R5\nfalse\ntrue\n0\nDEFAULT");
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
		        this.createProblema("Problema1\n2\n8/8/8/8/3p4/5K2/3kNN2/2R5\nfalse\ntrue\n0\nDEFAULT");
		        this.guardarFichero();
			}
			
		} catch(Exception e) {
			System.out.println("Error cargando fichero DBPersonas.txt");
			e.printStackTrace();
		}
		
	}
	
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
		problema.put("nombre", nombre);
		problema.put("maxmov", maxmov);
		problema.put("FEN", FEN);
		problema.put("color", color);
		problema.put("valid", valid);
		problema.put("vecesJugado", vecesJugado);
		problema.put("autor", autor);
		this.problemas.add(problema);
		this.guardarFichero();
	}
	
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
	
	public boolean existsProblema(String nombre) {
		for (int i = 0; i < this.problemas.size(); ++i) {
			JSONObject problema = (JSONObject) this.problemas.get(i);
			if (problema.get("nombre").equals(nombre)) return true;
		}
		return false;
	}
	
	public String getProblema(String nombre) {
		for (int i = 0; i < this.problemas.size(); ++i) {
			JSONObject problema = (JSONObject) this.problemas.get(i);
			if (problema.get("nombre").equals(nombre)) {
				String prob = problema.get("nombre")+"\n"+problema.get("maxmov")+"\n"+problema.get("FEN")+"\n"+problema.get("color")+
						"\n"+problema.get("valid")+"\n"+problema.get("vecesJugado")+"\n"+problema.get("autor");
				return prob;
			}
		}
		return null;
	}
	
	public void deleteProblema(String nombre) {
		for (int i = 0; i < this.problemas.size(); ++i) {
			JSONObject problema = (JSONObject) this.problemas.get(i);
			if (problema.get("nombre").equals(nombre)) {
				this.problemas.remove(i);
				this.guardarFichero();
			}
		}
	}
	
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
