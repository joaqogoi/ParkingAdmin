package Service;

import java.util.List;

import com.google.gson.JsonElement;

import Domain.Sucursal;
import Exception.DataReadingException;

public interface I_GsonService {
	public JsonElement convertirJavaToJson(Sucursal sucursal);
	public Sucursal convertirJsonToJava(JsonElement jsonElement);
	public List<Sucursal> convertirLista() throws DataReadingException;
}
