package Domain;
import java.util.ArrayList;
import java.util.List;

public class Sucursal {
	private static int contadorIds = 0;
	public int id;
	private String direccion;
	private double valorXhora;
	private List<Encargado> listaEncargados;
	private List<InOut> listaInOut;
	
	public Sucursal(String direccion, double valorXhora) {
		this.id = ++contadorIds;
		this.direccion = direccion;
		this.valorXhora = valorXhora;
		this.listaInOut = new ArrayList<>();
		this.listaEncargados = new ArrayList<>();
	}

	public Sucursal(int id2, String direccion2, double valorXhora2, List<Encargado> listaEncargados2, List<InOut> listaInOut2) {
		this.id = id2;
		this.direccion = direccion2;
		this.valorXhora = valorXhora2;
		this.listaEncargados = listaEncargados2;
		this.listaInOut = listaInOut2;

	}

	public int getId() {
		return id;
	}

	public String getDireccion() {
		return direccion;
	}


	public double getValorXhora() {
		return valorXhora;
	}

	public void setValorXhora(double valorXhora) {
		this.valorXhora = valorXhora;
	}

	public List<InOut> getListaInOut() {
		return listaInOut;
	}

	public void agregarInOut(InOut eventoInOut) {
		listaInOut.add(eventoInOut);
	}

	public List<Encargado> getListaEncargados(){
		return listaEncargados;
	}
	
	public void setListaEncargados(List<Encargado> listaEncargados) {
		this.listaEncargados = listaEncargados;
	}
	
	public void agregarEncargado (Encargado encargado) {
		listaEncargados.add(encargado);
	}

	public void setListaInOut(ArrayList<InOut> listaInOut) {
		this.listaInOut = listaInOut;
	}
	
	
	
	
	
	

	
}