package Data;
import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import Domain.Sucursal;
import Exception.*;
import Service.*;


public class DataImplements implements I_Data{
	
	//Metodo Crear Archivo: crea el archivo principal al inicar el programa por primera vez
	@Override
	public void crearArchivo(JsonElement jsonElement) throws DataAccessException {
		 if (!existeArchivo("baseDeDatos.json")) {
	            actualizarArchivo(jsonElement);
	        } else {
	            throw new DataAccessException("El archivo ya existe.");
	        }
	}
	
	//Metodo Exists: Comprueba si el archivo ya existe en la base de datos.
	@Override
	public boolean existeArchivo(String fileName) throws DataAccessException {
		  File archivo = new File("baseDeDatos.json");
	        return archivo.exists() && archivo.isFile();
	}
	
	//Metodo ListarSucursal: recorre el arreglo de sucursales dentro del archivo JsonElement.
	@Override
	public List<JsonElement> toList() throws DataReadingException {
		List<JsonElement> sucursalesEncontradas = new ArrayList<>();
		 try (FileReader fileReader = new FileReader("baseDeDatos.json");
		         BufferedReader bufferedReader = new BufferedReader(fileReader)) {
		        JsonElement fileData = JsonParser.parseReader(bufferedReader);

		        if (fileData.isJsonArray()) {
		            JsonArray jsonArray = fileData.getAsJsonArray();

		            for (JsonElement elemento : jsonArray) {
		                if (elemento.isJsonObject()) {
		                    sucursalesEncontradas.add(elemento);
		                }
		            }
		        }
		    } catch (IOException e) {
		        throw new DataReadingException("Error al leer el archivo.");
		    }

		    return sucursalesEncontradas;
	}
	

	//Metodo actualizarBase: almacena los datos sin sobreescribir lo anterior.
	@Override
	public void actualizarArchivo(JsonElement jsonElement) throws DataWritingExcepction {
		try (FileWriter fileWriter = new FileWriter("baseDeDatos.json", true);
		         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		         PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
		        Gson gson = new GsonBuilder().setPrettyPrinting().create();
		        gson.toJson(jsonElement, printWriter);
		        printWriter.println(); // Agrega una línea en blanco después de cada elemento para separarlos
		    } catch (IOException e) {
		        throw new DataWritingExcepction("Error al escribir en el archivo.");
		    }
	}

	
	}



