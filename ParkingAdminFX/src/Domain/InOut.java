package Domain;
import java.time.Duration;
import java.time.LocalDateTime;

public class InOut {
	//Attributes
	private static int contadorIDs = 0;
	private final int id;
	private Encargado encargado;
	private Cliente cliente;
	private Sucursal sucursal;
	private LocalDateTime in;
	private LocalDateTime out;
	private Factura facturacion;
	
	//Constructor
	public InOut(Cliente cliente, Sucursal sucursal, Encargado encargado) {
		this.id = ++contadorIDs;
		this.cliente = cliente;
		this.sucursal = sucursal;
		this.encargado = encargado;
		this.in = LocalDateTime.now();
		this.facturacion = null;
	}
	
	public void registrarOut() {
		this.out = LocalDateTime.now();
		double horasPermanencia = calcularPermanencia();
		double montoFacturacion = calcularFacturacion(horasPermanencia);
		this.facturacion = new Factura(id, horasPermanencia,montoFacturacion);
	}
	
	public double calcularPermanencia() {
		Duration duracion = Duration.between(in, out);
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
	

	public LocalDateTime getIn() {
		return in;
	}

	public void setIn(LocalDateTime in) {
		this.in = in;
	}

	public LocalDateTime getOut() {
		return out;
	}

	public void setOut(LocalDateTime out) {
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
