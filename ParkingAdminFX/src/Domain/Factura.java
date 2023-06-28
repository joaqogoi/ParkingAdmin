package Domain;

public class Factura {
	private double id;
	private double time;
	private double monto;
	
	public Factura(double id, double time, double monto) {
		super();
		this.id = id;
		this.time = time;
		this.monto = monto;
	}
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	
	

}