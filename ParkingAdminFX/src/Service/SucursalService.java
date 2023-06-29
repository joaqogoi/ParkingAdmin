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
		this.gsonService = gsonService;
	}
	
	//Metodos para manipular la sucursalActiva:
	public void crearSucursal(String direccion, double valorXhora) {
		sucursalActiva = new Sucursal(direccion, valorXhora);
	}
	
	public void cargarSucursal(JsonElement jsonElement) {
		sucursalActiva = gsonService.convertirJsonToJava(jsonElement);
	}
	
	public void guardarSucursal() {
		List<InOut> eventosSinSalida = sucursalActiva.getListaInOut();
		for(InOut evento : eventosSinSalida) {
			if (evento.getOut() == null) {
				System.out.println("Existen ingresos que no registran salida no se puede grabar la lista en la base de datos mientras existan ingresos sin finalizar");
				return;
			}
		}
		JsonElement jsonElement = gsonService.convertirJavaToJson(sucursalActiva);
		gsonService.guardarJsonEnData(jsonElement);
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
	public void agregarEventoInOut(double idCliente, String nombreCliente, double patenteVehiculo, String mod) {
		if (sucursalActiva != null && encargadoActivo != null) {
			Cliente cliente = new Cliente(idCliente, nombreCliente);
			cliente.agregarVehiculo(patenteVehiculo, mod);
			
			InOut eventoInOut = new InOut(cliente, sucursalActiva, encargadoActivo);
			sucursalActiva.agregarInOut(eventoInOut);
		} else {
			System.out.println("No se puede agregar evento InOut");
		}
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
