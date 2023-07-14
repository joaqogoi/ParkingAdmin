package Data;
import java.util.List;
import com.google.gson.JsonElement;
import Exception.*;

interface I_Data {
	
	    void crearArchivo() throws DataAccessException;
		boolean existeArchivo(String fileName) throws DataAccessException;
		void actualizarArchivo(JsonElement jsonElement) throws DataWritingExcepction;
		List<JsonElement> toList() throws DataReadingException, DataAccessException;
		

	}


