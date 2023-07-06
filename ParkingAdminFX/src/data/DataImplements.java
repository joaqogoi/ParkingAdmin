package data;
import java.io.*;
import java.util.*;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Domain.Sucursal;
import Exception.*;


public class DataImplements implements I_Data{
	
	//Nombre del archivo:: "parking.json"
	//Metodo Crear Archivo: crea el archivo principal al inicar el programa por primera vez
	@Override
	public void crearArchivo(JsonElement jsonElement) throws DataAccessException {
		 if (!existeArchivo("parking.json")) {
	            actualizarArchivo("parking.json", jsonElement);
	        } else {
	            throw new DataAccessException("El archivo ya existe.");
	        }
	}
	
	//Metodo Exists: Comprueba si el archivo ya existe en la base de datos.
	@Override
	public boolean existeArchivo(String fileName) throws DataAccessException {
		  File archivo = new File("parking.json");
	        return archivo.exists() && archivo.isFile();
	}
	
	//Metodo ListarSucursal: recorre el arreglo de sucursales dentro del archivo JsonElement.
	@Override
	public List<JsonElement> listarSucursal(JsonElement jsonElement) throws DataReadingException {
		 List<JsonElement> sucursalesEncontradas = new ArrayList<>();

		    if (jsonElement.isJsonArray()) {
		        JsonArray jsonArray = jsonElement.getAsJsonArray();

		        for (JsonElement elemento : jsonArray) {
		            if (elemento.isJsonObject()) {
		                sucursalesEncontradas.add(elemento);
		            }
		        }
		    }

		    return sucursalesEncontradas;
	}
	
	//Metodo escribirSucursal: se encarga de escribir los datos dentro del archivo.
	@Override
	public void escribirSucursal(JsonElement jsonElement, String fileName) throws DataWritingException {
		// TODO Auto-generated method stub
		
	}

	//Metodo actualizarBase: almacena los datos sin sobreescribir lo anterior.
	@Override
	public void actualizarArchivo(String string, JsonElement jsonElement) throws DataWritingException {
		try (FileWriter fileWriter = new FileWriter("parking.json", true);
		         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		         PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
		        Gson gson = new GsonBuilder().setPrettyPrinting().create();
		        gson.toJson(jsonElement, printWriter);
		        printWriter.println(); // Agrega una línea en blanco después de cada elemento para separarlos
		    } catch (IOException e) {
		        throw new DataWritingException("Error al escribir en el archivo.");
		    }
	}

	
	

}
