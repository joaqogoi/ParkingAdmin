package Domain;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends UsuarioABS{
	
	//atributos propios de la clase hija
	private List<Vehiculo> vehiculos;

	//contructor
	public Cliente(double id, String name) {
		super(id, name);
		vehiculos = new ArrayList<>();	
	}
	
	//metodos propios
	
	public void agregarVehiculo(double id, String mod) {
		Vehiculo nuevoVehiculo = new Vehiculo(id, mod);
		if(!vehiculos.contains(nuevoVehiculo)) {
			vehiculos.add(nuevoVehiculo);
		}
	}
	
	/* El metodo agregar vehiculo crea una nueva instancia de la clase Vehiculo
	 * invocando al contructor con los parametros id y mod, el obj resultante
	 * se almacena en la variable nuevo vehiculo, luego se verifica si la lista
	 * de vehiculos YA contiene el nuevo vehiculo mediante el metodo contains()
	 * de la clase List, que invoca al metodo equals() de los obj vehiculo.
	 * */
	
	public void eliminarVehiculo(Vehiculo vehiculo) {
		vehiculos.remove(vehiculo);
	}
	
	public List<Vehiculo> getVehiculos(){
		return vehiculos;
	}
	
	
	//metodo heredado y sobreescrito
	@Override
	public double facturacion(List<InOut> listaInOut) {
		double facturacionXcliente = 0.0;
		for (InOut eventoInOut : listaInOut) {
			if(eventoInOut.getCliente().equals(this) && eventoInOut.getFacturacion() != null) {
				facturacionXcliente += eventoInOut.getFacturacion().getMonto();
			}
		}
		return facturacionXcliente;
	}
	
	/* El metodo facturacion calcula la facturacion total de un cliente en base a una lista
	 * de eventos InOut, se itera sobre cada objeto InOut en la listaInOut, se verifica si
	 * el cliente asociado al eventoInOut es igual al cliente actual mediante el metodo
	 * "equals(this)", ademas se verifica el estado de el evento mediante la instancia de facturacion
	 * no nula "eventoInOut.getFacturacion() != null" para asegurarse de que el eventoInOut tiene una
	 * facturacion asociada.
	 * */
	

}