package Service;

import Domain.*;

import java.util.List;
import java.util.ArrayList;
import com.google.gson.JsonElement;

public class SucursalService {
	private GsonService gsonService;
	private Sucursal sucursalActiva;
	private Encargado encargadoActivo;
	
	public SucursalService(GsonService gsonService) {
		//instancia del paq Gson para la implementacion de sus metodos. 
		this.gsonService = gsonService;
	}
	
	//Metodos para manipular la sucursalActiva:
	public void crearSucursal(String direccion, double valorXhora) {
		sucursalActiva = new Sucursal(direccion, valorXhora);
	}
	
	public void cargarSucursal(int idSucursal) {
		GsonService gsonService = new GsonService();
		List<Sucursal> listaSucursales = gsonService.convertirLista("baseDeDatos.Json");
		//recorremos la lista de sucursales de la base de datos y seleccionamos la sucursalActiva
		for (Sucursal sucursal : listaSucursales) {
			if(sucursal.getId() == idSucursal) {
				
				//guardamos el valor del id para el ultimo evento registrado
				int ultimoRegistro = 0;
				List<InOut> listaDeRegistros = sucursal.getListaInOut();
				for (InOut registro : listaDeRegistros) {
					if (registro.getId() > ultimoRegistro) {
						ultimoRegistro = registro.getId();
					}
				}
				
				sucursalActiva = sucursal;
				//limpiamos la lista de eventos InOut para comenzar el turno
				sucursalActiva.getListaInOut().clear();
				InOut.setContadorIDs(ultimoRegistro);
				break;
			}
		}
	}
	
	public void guardarSucursal() {
		GsonService gsonService = new GsonService();
		List<Sucursal> listaSucursales = gsonService.convertirLista("baseDeDatos.json");
		
		//busqueda de sucursalActiva por id
		Sucursal sucursalActualizada = null;
		for (Sucursal sucursal : listaSucursales) {
			if (sucursal.getId() == sucursalActiva.getId()) {
				//actualizacion de la lista de eventosInOut
				sucursal.getListaInOut().addAll(sucursalActiva.getListaInOut());
				sucursalActualizada = sucursal;
				break;
			}
		}
		if (sucursalActualizada != null) {
			//convetir sucursal a Json
			JsonElement jsonElement = gsonService.convertirJavaToJson(sucursalActualizada);
			//guarda Json en la base de datos
			instanciaDeData.guardarEnData(jsonElement);
		} else {
			System.out.println("No se encuentra sucursal activa");
		}
	}
	
	//Creacion de listas para el usuario
	public List<String> obtenerNombresEncargados(){
		List<String> listaEncargados = new ArrayList<>();
		List<Encargado> encargados = sucursalActiva.getListaEncargados();
		for (Encargado encargado : encargados) {
			String nombreEncargado = encargado.getName();
			listaEncargados.add(nombreEncargado);
		}
		return listaEncargados;
	}
	
	
	//Metodos para manipular la lista de encargados:
	public void crearEncargado(double id, String nombre) {
	     Encargado encargado = new Encargado(id, nombre);
	     sucursalActiva.getListaEncargados().add(encargado);
	}
	
	public void eliminarEncargado(double id) {
		List<Encargado> encargados = sucursalActiva.getListaEncargados();
		encargados.removeIf(encargado -> encargado.getId() == id);
	}
	
	public void establecerEncargadoActivo (double idEncargadoAct) {
		List<Encargado> listaEncargados = sucursalActiva.getListaEncargados();
		for (Encargado encargado : listaEncargados) {
			if (encargado.getId() == idEncargadoAct) {
				encargadoActivo = encargado;
			}
		}
		System.out.println("No se encontro ningun encargado con este dni");
	}
	
	public Encargado obtenerEncargadoActivo() {
		return encargadoActivo;
	}
	
	public List<Encargado> obtenerListaEncargados(){
		return sucursalActiva.getListaEncargados();
	}
	
	//Metodos para manipular los eventos InOut:
	public List<String> agregarEventoInOut(double idCliente, String nombreCliente, String patente, String mod) {
		List<String> datosEvento = new ArrayList<>();
		if (sucursalActiva != null && encargadoActivo != null) {
			Cliente cliente = new Cliente(idCliente, nombreCliente);
			cliente.agregarVehiculo(patente, mod);
			
			InOut eventoInOut = new InOut(cliente, sucursalActiva, encargadoActivo);
			sucursalActiva.agregarInOut(eventoInOut);
			datosEvento = obtenerDatosEvento(eventoInOut);
		} else {
			System.out.println("No se encuentra un encargado o sucursal activos");
		}
		return datosEvento;
	}
	
	public List<String> obtenerDatosEvento(InOut eventoInOut){
		List<String> datosEvento = new ArrayList<>();
		
		String encargadoActivo = eventoInOut.getEncargado().getName();
		String idEvento = String.valueOf(eventoInOut.getId());
		String horarioIngreso = eventoInOut.getIn().toString();
		String nombreCliente = eventoInOut.getCliente().getName();
		String patenteVehiculo = eventoInOut.getCliente().getVehiculo().getPatente();
		String modVehiculo = eventoInOut.getCliente().getVehiculo().getMod();
		
		datosEvento.add(encargadoActivo);
		datosEvento.add(idEvento);
		datosEvento.add(horarioIngreso);
		datosEvento.add(nombreCliente);
		datosEvento.add(patenteVehiculo);
		datosEvento.add(modVehiculo);
		
		return datosEvento;
	}
	
	public void registrarSalida(double idEvento) {
		if(sucursalActiva != null) {
			List<InOut> eventosInOut = sucursalActiva.getListaInOut();
			for(InOut evento : eventosInOut) {
				if(evento.getId() == idEvento) {
					evento.registrarOut();
					System.out.println("Se ha registrado la salida del ingreso con el id:" + idEvento);
					return; //este metodo no recibe ningun retorno pero el return indica la finalizacion del metodo
				}
			}
			System.out.println("No se encontro un ingreso con este ID");
		} else {
			System.out.println("No se puede registrar una salida por que no existe una sucursal activa.");
		}
	}
	
	public List<InOut> obtenerEventosSinRegistroDeSalida(){
		List<InOut> eventosSinSalida = new ArrayList<>();
		if (sucursalActiva != null) {
			List<InOut> eventosInOut = sucursalActiva.getListaInOut();
			for (InOut evento : eventosInOut) {
				if (evento.getOut() == null) {
					eventosSinSalida.add(evento);
				}
			}
		}
		return eventosSinSalida;
	}
	
}
