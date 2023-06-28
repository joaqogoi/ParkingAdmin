package Domain;
public class Vehiculo {
	
	//atributos
	private double id;
	private String mod;
	
	//constructor
	public Vehiculo(double id, String mod) {
		super();
		this.id = id;
		this.mod = mod;
	}
	
	//getters y setters
	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", mod=" + mod + "]";
	}
	
	//metodos
	
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Vehiculo other = (Vehiculo) obj;
		return Double.compare(other.id, id) == 0;
	}
	
	/*Este metodo es utilizado para determinar si dos obj Vehiculo
	 * son conciderados iguales, en primera instancia se realiza una
	 * verificacion rapida para determinar si los obj son identicos
	 * en memoria utilizando el operador "==", luego se asegura que
	 * el obj pasado como argumento no sea nulo y sea concretamente
	 * una instancia de la clase Vehiculo ya que esta composicion
	 * de id,nombre esta presente en varias clases, una vez corroborado
	 * esto se castea el obj Vehiculo en la variable other y se compara
	 * el atributo id. 
	 * Este metodo esta pensando para ser invocado desde clases externas
	 * y evitar la presencia de Vehiculos repetidos en listas de objetos  
	 * */
}