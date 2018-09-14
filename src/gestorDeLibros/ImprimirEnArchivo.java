package gestorDeLibros;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class ImprimirEnArchivo {
	
	public static void actualizarArchivo(String ruta, ArrayList<Libro> vector) throws FileNotFoundException
	{
		PrintStream salida = new PrintStream(ruta);
		for(Libro l : vector)
			salida.println(l.toString());
		
		salida.close();
	}
}
