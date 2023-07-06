package Exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class DataAccessException extends IOException {


	public DataAccessException (String mensaje) {
		super(mensaje);
	}
}