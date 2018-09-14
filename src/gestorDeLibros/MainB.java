package gestorDeLibros;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class MainB {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Gestor g = new Gestor();
		g.configurarSalida();
		g.cargarArchivo("Ruta del archivo");
		g.menuPrincipal();
	}
}
