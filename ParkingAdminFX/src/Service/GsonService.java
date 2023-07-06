package Service;

import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import Domain.Encargado;
import Domain.InOut;
import Domain.Sucursal;

public class GsonService implements I_GsonService{
	private Gson gson;
	
	//contructor
	//instancia del paquete Gson para la implementacion de sus metodos
	public GsonService() {
		this.gson = new Gson();
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
		Sucursal sucursal = gson.fromJson(jsonElement, Sucursal.class);
		return sucursal;
	}

	@Override
	public List<Sucursal> convertirLista(String nombreDelArchivo) {
		List<JsonElement> listaJsonElement = instanciaData.toList(nombreDelArchivo);
		List<Sucursal> listaSucursales = new ArrayList<>();
		
		for(JsonElement jsonElement : listaJsonElement) {
			Sucursal sucursal = convertirJsonToJava(jsonElement);
			listaSucursales.add(sucursal);
		}
		return listaSucursales;
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
