package gestorDeLibros;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class ImprimirEnArchivo {
	
	public static void actualizarArchivo(String ruta, ArrayList<Libro> vector) throws FileNotFoundException
	{
		PrintStream salida = new PrintStream(ruta); //(58)
		/*int n = vector.size();
		for (int i=0; i<n; i++) //(59)
			imprimirEnArchivo.funcion(vector.get(i), salida); //(60)*/
		for(Libro l : vector)
		{
			salida.println(l.toString());
		}
		
		salida.close();//(61)
	}

}
