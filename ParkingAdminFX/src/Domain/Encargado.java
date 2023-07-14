package Domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Encargado extends UsuarioABS{

	public Encargado(double id, String name) {
		super(id, name);
		
	}
	
	//Metodos heredados y sobreescritos
	
	public double facturacion(List<InOut> listaInOut) {
		double facturacionXdia = 0.0;
		LocalDate fechaActual = LocalDate.now();
		
		for (InOut eventoInOut : listaInOut) {
			String out = eventoInOut.getOut();
			LocalDateTime outDateTime = LocalDateTime.parse(out, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			LocalDate fechaOut = outDateTime.toLocalDate();
			
			if(fechaOut.equals(fechaActual) && eventoInOut.getFacturacion() != null) {
				facturacionXdia += eventoInOut.getFacturacion().getMonto();
			}
		}
		return facturacionXdia;
	}

	/*El metodo facturacion itera sobre una lista de obj InOut corroborando las fechas
	 * en que son registrados dichos eventos, al encontrar concidencias con la fecha actual
	 * va almacenando el valor acumulado de la variable monto de cada factura para obtener
	 * el valor de la facturacion total por dia de todos los eventos InOut registrados en 
	 * una fecha determinada.  
	 * */
}
