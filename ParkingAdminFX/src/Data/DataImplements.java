package Data;
import java.io.*;

import java.util.*;
import com.google.gson.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import Exception.*;


public class DataImplements implements I_Data{
	
	//Metodo Crear Archivo: crea el archivo principal al inicar el programa por primera vez
	@Override
	public void crearArchivo() throws DataAccessException {
	    String nombreArchivo = "baseDeDatos.json";
	    
	    if (!existeArchivo(nombreArchivo)) {
	        try (FileWriter fileWriter = new FileWriter(nombreArchivo)) {
	            // Puedes agregar contenido inicial al archivo si lo deseas
	            fileWriter.write("[]");
	        } catch (IOException e) {
	            throw new DataAccessException("Error al crear el archivo.");
	        }
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
	public List<JsonElement> toList() throws DataAccessException {
		List<JsonElement> sucursalesEncontradas = new ArrayList<>();
		String nombreDelArchivo = "baseDeDatos.json";
		
		if(!existeArchivo(nombreDelArchivo)) {
			try {
				crearArchivo();
			} catch (DataAccessException e) {
				throw new DataReadingException("Error al crear el archivo");
			}
		} else {
		try (FileReader fileReader = new FileReader(nombreDelArchivo);
		         BufferedReader bufferedReader = new BufferedReader(fileReader)) {
		        JsonElement fileData = JsonParser.parseReader(bufferedReader);

		        if (fileData.isJsonArray()) {
		            JsonArray jsonArray = fileData.getAsJsonArray();

		            for (JsonElement elemento : jsonArray) {
		                if (elemento.isJsonObject()) {
		                    sucursalesEncontradas.add(elemento);
		                    System.out.println(elemento.toString());
		                } else {
		                	System.err.println("Error al cargar el arreglo del archivo Json");
		                	}
		            	}
		        	} else {
		        		System.err.println("La base de datos no contiene un arreglo de sucursales");
		        	}
		    } catch (IOException e) {
		        throw new DataReadingException("Error al leer el archivo.");
		    }

		}
		return sucursalesEncontradas;
	}
	

	@Override
	public void actualizarArchivo(JsonElement jsonElement) throws DataWritingExcepction {
		try (FileReader fileReader = new FileReader("baseDeDatos.json");
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			
			JsonElement fileData = JsonParser.parseReader(bufferedReader);
			
			if(fileData.isJsonArray()) {
				JsonArray jsonArray = fileData.getAsJsonArray();
				
				//verificar si el obj ya existe en el jsonArray
				int idSucursalNueva = jsonElement.getAsJsonObject().get("id").getAsInt();
				boolean sucursalExistente = false;
				for (int i = 0; i < jsonArray.size(); i++) {
					JsonElement elemento = jsonArray.get(i);
					int idSucursalExistente = elemento.getAsJsonObject().get("id").getAsInt();
					if (idSucursalExistente == idSucursalNueva) {
						//se rempleza el elemento existente con el mismo id
						jsonArray.set(i, jsonElement);
						sucursalExistente = true;
						break;
					}
				}
				//si no existe lo agrega al jsonArray
				if (!sucursalExistente) {
					jsonArray.add(jsonElement);
				}
				
				try (FileWriter fileWriter = new FileWriter("baseDeDatos.json");
						BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
						PrintWriter printWriter = new PrintWriter(bufferedWriter)){
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					gson.toJson(jsonArray, printWriter);
				} catch (IOException e) {
					throw new DataWritingExcepction("Error al escribir el archivo");
				}
			} else {
				System.out.println("La base de datos no contiene un arreglo de sucursales");
			}
		} catch (IOException e) {
			throw new DataWritingExcepction("Error al leer el archivo");
		}
		
	}

	
	}



