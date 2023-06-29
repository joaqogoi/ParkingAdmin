package Service;

import com.google.gson.JsonElement;

import Domain.Sucursal;

public interface I_GsonService {
	public JsonElement convertirJavaToJson(Sucursal sucursal);
	public Sucursal convertirJsonToJava(JsonElement jsonElement);
	public void guardarJsonEnData(JsonElement jsonElement);
}
