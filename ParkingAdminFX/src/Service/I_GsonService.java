package Service;

import com.google.gson.Gson;
import Domain.Sucursal;

public interface I_GsonService {
	public Gson convertirJavaToJson(Sucursal sucursal);
	public Sucursal convertirJsonToJava(Gson gson);
}
