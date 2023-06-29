package Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import Domain.Sucursal;

public class GsonService implements I_GsonService{
	private Gson gson;
	
	//contructor
	public GsonService() {
		this.gson = new Gson();
	}

	@Override
	public JsonElement convertirJavaToJson(Sucursal sucursal) {
		return gson.toJsonTree(sucursal);
	}


	@Override
	public void guardarJsonEnData(JsonElement jsonElement) {
		//crear instancia del PaqData//DataClass.guardarJson(jsonElement);
	}

	@Override
	public Sucursal convertirJsonToJava(JsonElement jsonElement) {
		Sucursal sucursal = gson.fromJson(jsonElement, Sucursal.class);
		return sucursal;
	}


	/* En el contructor de la clase se crea una instancia de la clase Gson 
	 * y se le asigna la variable gson que actuara como intermediario en 
	 * la serializacion y deserializacion de objetos Java a Json y viceversa.
	 * El parametro jsonElement es un objeto del tipo JsonElement que
	 * representa un elemento Json generico en un estructura Json,
	 * este jsonElement contiene contiene los datos Json que seran almacenados,
	 * o deseralizados para la construccion de un objeto Sucursal.
	 */
	
}
