package Service;

import java.util.List;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import Domain.*;
import Exception.DataAccessException;
import Exception.DataWritingExcepction;
import Data.*;

public class GsonService implements I_GsonService{
	//instancia del paquete Gson para la implementacion de sus metodos
	public GsonService() {
		new Gson();
	}

	//metodos para la manipulacion entre el paqService y el paqData
	
	@Override
	public JsonElement convertirJavaToJson(Sucursal sucursal) {
		JsonObject jsonObject = new JsonObject();
		
		jsonObject.addProperty("id",sucursal.getId());
		jsonObject.addProperty("direccion", sucursal.getDireccion());
		jsonObject.addProperty("valorXhora", sucursal.getValorXhora());
		
		//convertir lista de encargados a JsonArray
		JsonArray encargadosArray = new JsonArray();
		for (Encargado encargado : sucursal.getListaEncargados()) {
			JsonObject encargadoJson = new JsonObject();
			encargadoJson.addProperty("id", encargado.getId());
			encargadoJson.addProperty("name", encargado.getName());
			encargadosArray.add(encargadoJson);
		}
		//incorporacion de la lista de encargados a la sucursalJson
		jsonObject.add("listaEncargados",encargadosArray);
		
		//convertir lista de eventosInOut en JsonArray
		JsonArray inOutArray = new JsonArray();
		for(InOut eventoInOut : sucursal.getListaInOut()) {
			JsonObject eventoInOutJson = new JsonObject();
			eventoInOutJson.addProperty("id", eventoInOut.getId());
			eventoInOutJson.addProperty("in", eventoInOut.getIn().toString());
			eventoInOutJson.addProperty("out", eventoInOut.getOut().toString());
			
			//objeto Cliente a JsonObject
			JsonObject clienteJson = new JsonObject();
			clienteJson.addProperty("id", eventoInOut.getCliente().getId());
			clienteJson.addProperty("name", eventoInOut.getCliente().getName());
			
			//objeto vehiculo a JsonObject
			JsonObject vehiculoJson = new JsonObject();
			vehiculoJson.addProperty("patente",eventoInOut.getCliente().getVehiculo().getPatente());
			vehiculoJson.addProperty("mod", eventoInOut.getCliente().getVehiculo().getMod());
			
			//incorporacion de los datos del cliente al evento
			clienteJson.add("vehiculo", vehiculoJson);
			eventoInOutJson.add("cliente", clienteJson);
			
			//factura a JsonObject
			JsonObject facturaJson = new JsonObject();
			facturaJson.addProperty("id", eventoInOut.getFacturacion().getId());
			facturaJson.addProperty("time", eventoInOut.getFacturacion().getTime());
			facturaJson.addProperty("monto", eventoInOut.getFacturacion().getMonto());
			
			eventoInOutJson.add("facturacion", facturaJson);
			inOutArray.add(eventoInOutJson);	
		}
		
		//incorporacion del arreglo de evento InOut a la sucursal
		jsonObject.add("listaInOut", inOutArray);
		return jsonObject;
	}
	
	@Override
	public Sucursal convertirJsonToJava(JsonElement jsonElement) {
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		int id = jsonObject.get("id").getAsInt();
		String direccion = jsonObject.get("direccion").getAsString();
		double valorXhora = jsonObject.get("valorXhora").getAsDouble();
		
		List<Encargado> listaEncargados = convetirJsonArrayToEncargados(jsonObject.getAsJsonArray("listaEncargados"));
		
		List<InOut> listaInOut;
		JsonArray jsonArray = jsonObject.getAsJsonArray("listaInOut");
		if (jsonArray != null && jsonArray.size() > 0) {
			listaInOut = convetirJsonArrayToInOut(jsonArray);
		} else {
			listaInOut = new ArrayList<>();
		}
		
		Sucursal sucursal = new Sucursal(id, direccion,valorXhora, listaEncargados,listaInOut);
		return sucursal;
	}
	
	private List<Encargado> convetirJsonArrayToEncargados(JsonArray jsonArray){
		List<Encargado> listaEncargados = new ArrayList<>();
		for (JsonElement jsonElement : jsonArray) {
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			double id = jsonObject.get("id").getAsDouble();
			String name = jsonObject.get("name").getAsString();
			Encargado encargado = new Encargado(id, name);
			listaEncargados.add(encargado);
		}
		
		return listaEncargados;	
	}
	
	private List<InOut> convetirJsonArrayToInOut(JsonArray jsonArray){
		List<InOut> listaInOut = new ArrayList<>();
		for (JsonElement jsonElement : jsonArray) {
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			int id = jsonObject.get("id").getAsInt();
			String in = jsonObject.get("in").getAsString();
			String out = jsonObject.get("out").getAsString();
			
			JsonObject clienteJson = jsonObject.getAsJsonObject("cliente");
			double clienteId = clienteJson.get("id").getAsDouble();
			String clienteName = clienteJson.get("name").getAsString();
			
			JsonObject vehiculoJson = jsonObject.getAsJsonObject("vehiculo");
			String patente = vehiculoJson.get("patente").getAsString();
			String mod = vehiculoJson.get("mod").getAsString();
			Vehiculo vehiculo = new Vehiculo(patente, mod);
			Cliente cliente = new Cliente(clienteId, clienteName);
			cliente.setVehiculo(vehiculo);
			
			JsonObject facturaJson = jsonObject.getAsJsonObject("facturacion");
			int facturaId = facturaJson.get("id").getAsInt();
			double facturaTime = facturaJson.get("time").getAsDouble();
			double facturaMonto = facturaJson.get("monto").getAsDouble();
			Factura facturacion = new Factura(facturaId, facturaTime, facturaMonto);
			
			InOut eventoInOut = new InOut(id, in, out,cliente, facturacion);
			listaInOut.add(eventoInOut);
		}
		return listaInOut;
	}	
	

	@Override
	public List<Sucursal> convertirLista() throws DataAccessException {
		DataImplements instanciaData = new DataImplements();
		List<JsonElement> listaJsonElement = new ArrayList<>();
		try {
			listaJsonElement = instanciaData.toList();
		} catch (Exception e) {
			throw new DataAccessException("Error al convertir la lista");
		}
		
		List<Sucursal> listaSucursales = new ArrayList<>();
		for(JsonElement jsonElement : listaJsonElement) {
			Sucursal sucursal = convertirJsonToJava(jsonElement);
			listaSucursales.add(sucursal);
		}
		return listaSucursales;
	}

	@Override
	public void guardarEnData(JsonElement jsonElement) throws DataWritingExcepction {
		DataImplements instanciaData = new DataImplements();
		instanciaData.actualizarArchivo(jsonElement);
	}


	//convetirLista:
	/* En este metodo se recorre el arreglo de JsonElements proporcionado por
	 * el metodo toList() del paqData y se convierte cada elemento en un obj
	 * de tipo Sucursal utilizando el metodo convertirJsonToJava, luego se 
	 * agrega cada elemento en una List<Sucursal> listaSucursales con los que
	 * se pueden las operacion correspondientes.*/

	
	//convertirJsonToJava:
	/*Este metedo toma un obj Json de la base de datos, lo deserealiza mediente 
	 * la implementacion del metodo fromJson de la libreria Gson de google y lo
	 * serealiza en un obj de tipo Sucursal para poder ser utilizado como un obj 
	 * Java permitiendo su manipulacion por parte del sistema durante la ejecucion
	 * del programa, es importante aclarar que en este metodo no es necesario la 
	 * estructuracion adicional del objeto Sucursal ya que Gson se encarga de
	 * analizar la estructura del Json y mapear los valores correspondientes a los
	 * campos del objeto sucursal SIEMPRE Y CUANDO la estructura del Json coincida
	 * con la estructura de la clase Sucursal y los nombres de los campos sean compatibles*/
	
	//convertirJavaToJson:
	/*En la ultima modificacion del metodo convertirJavaToJson()
	* se desarrollo la serealizacion y jerarquizacion de la estructura
	* del objeto Sucursal de la manera en que sera almacenado dentro del
	* archivo Json encargado de la persistencia de los datos.
	* Se crea un JsonObject y se le van agregando las propiedades de la clase
	* sucursal con su estructura y jerarquia correspondientes*/


	//Implementacion del paq Gson:
	/* En el contructor de la clase se crea una instancia de la clase Gson 
	 * y se le asigna la variable gson que actuara como intermediario en 
	 * la serializacion y deserializacion de objetos Java a Json y viceversa.
	 * El parametro jsonElement es un objeto del tipo JsonElement que
	 * representa un elemento Json generico en un estructura Json,
	 * este jsonElement contiene los datos Json que seran almacenados,
	 * o deseralizados para la construccion de un objeto Sucursal.
	 */
	
}
