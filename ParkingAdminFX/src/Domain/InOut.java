package Domain;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InOut {
	//Attributes
	private static int contadorIDs = 0;
	private int id;
	private Encargado encargado;
	private Cliente cliente;
	private Sucursal sucursal;
	private String in;
	private String out;
	private Factura facturacion;
	
	//Constructor
	public InOut(Cliente cliente, Sucursal sucursal, Encargado encargado) {
		this.id = ++contadorIDs;
		this.cliente = cliente;
		this.sucursal = sucursal;
		this.encargado = encargado;
		this.in = LocalDateTime.now().toString();
		this.facturacion = null;
	}
	
	public InOut(int id2, String in2, String out2, Cliente cliente2, Factura facturacion2) {
		this.id = id2;
		this.in = in2;
		this.out = out2;
		this.cliente = cliente2;
		this.facturacion = facturacion2;
	}

	public void registrarOut() {
		this.out = LocalDateTime.now().toString();
		double horasPermanencia = calcularPermanencia();
		double montoFacturacion = calcularFacturacion(horasPermanencia);
		this.facturacion = new Factura(id, horasPermanencia,montoFacturacion);
	}
	
	public double calcularPermanencia() {
		LocalDateTime inDateTime = LocalDateTime.parse(in, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime outDateTime = LocalDateTime.parse(out, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Duration duracion = Duration.between(inDateTime, outDateTime);
		long segundos = duracion.getSeconds();
		double horas = segundos / 3600.0;
		return horas;
	}
	
	public double calcularFacturacion(double horasPermanencia) {
		double valorXhora = sucursal.getValorXhora();
		return horasPermanencia * valorXhora;
	}
	
	//preguntar en clase como afecteria el cambiar el metodo getValorXhora a static
	//rodearlo con un trycatch no es simplemente una manera de emascarar el problema?
	

	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Factura getFacturacion() {
		return facturacion;
	}

	public void setFacturacion(Factura facturacion) {
		this.facturacion = facturacion;
	}

	public int getId() {
		return id;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public Encargado getEncargado() {
		return encargado;
	}

	public static int getContadorIDs() {
		return contadorIDs;
	}

	public static void setContadorIDs(int contadorIDs) {
		InOut.contadorIDs = contadorIDs;
	}
	
	
}
