package Domain;
public class Vehiculo {
	
	//atributos
	private String patente;
	private String mod;
	
	//constructor
	public Vehiculo(String patente, String mod) {
		super();
		this.patente = patente;
		this.mod = mod;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

	@Override
	public String toString() {
		return "Vehiculo [patente=" + patente + ", mod=" + mod + "]";
	}
	
	
	
}