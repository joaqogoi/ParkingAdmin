package data;
import java.util.List;
import com.google.gson.JsonElement;
import Exception.*;

interface I_Data {
	
	    void crearArchivo(JsonElement jsonElement) throws DataAccessException;
		List <JsonElement> listarSucursal(JsonElement jsonElement) throws DataReadingException;
		void escribirSucursal(JsonElement jsonElement, String fileName) throws DataWritingException;
		boolean existeArchivo(String fileName) throws DataAccessException;
		void actualizarArchivo(String string, JsonElement jsonElement) throws DataWritingException;
		

	}


