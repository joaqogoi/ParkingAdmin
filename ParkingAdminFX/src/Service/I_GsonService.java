package Service;

import java.util.List;

import com.google.gson.JsonElement;

import Domain.Sucursal;
import Exception.DataAccessException;
import Exception.DataReadingException;
import Exception.DataWritingExcepction;

public interface I_GsonService {
	public JsonElement convertirJavaToJson(Sucursal sucursal);
	public Sucursal convertirJsonToJava(JsonElement jsonElement);
	public List<Sucursal> convertirLista() throws DataReadingException, DataAccessException;
	public void guardarEnData(JsonElement jsonElement) throws DataWritingExcepction;
}
