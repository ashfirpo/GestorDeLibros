package gestorDeLibros;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Gestor {
	
	int opcion, subopcion;
	Libro libro, dato;
	ArrayList<Libro> vector = new ArrayList<Libro>(); //(1)
	Scanner teclado = new Scanner(System.in, "CP850"); //(2)
	PrintStream out = new PrintStream(System.out, true); //Agregada
	
	public void configurarSalida() throws UnsupportedEncodingException
	{
		if(!System.getProperties().get("os.name").equals("Linux") && System.console()!=null) //(1)(2)
			out = new PrintStream(System.out, true, "CP850"); //(3)	
	}
	
	public void menuPrincipal() throws FileNotFoundException
	{
		do
		{
			out.println("MEN\u00DA");//(9)
			out.println("1.- Altas");
			out.println("2.- Consultas");
			out.println("3.- Actualizaciones");
			out.println("4.- Bajas");
			out.println("5.- Ordenar registros");
			out.println("6.- Listar registros");
			out.println("7.- Salir");
			
			validarOpcion("Seleccione una opci\u00F3n", 1, 7);
			
			out.println(); //(17)
			if (noHayRegistros())//(18)
			{
				//pausar("No hay registros.\n"); //(21) pausar????
				continue;
			}
			
			verificarOpcion();

		}while(opcion !=7); //(57)
		
		ImprimirEnArchivo.actualizarArchivo("Ruta del archivo", vector);
	}
	
	
	public void cargarArchivo(String ruta) throws FileNotFoundException
	{
		Scanner entrada = new Scanner(new FileReader(ruta));
		while (entrada.hasNextLine()) //(5)
		{
			String[] campos = entrada.nextLine().split("\t"); //(6)
			Libro libro = new Libro();
			libro.setISBN(campos[0]);
			libro.setTitulo(campos[1]);
			libro.setAutor(campos[2]);
			libro.setEditorial(campos[3]);
			libro.setEdicion(Integer.parseInt(campos[4]));
			libro.setAnno_de_publicacion(Integer.parseInt(campos[5]));
			vector.add(libro);
		}
		entrada.close();//(7)
		
		libro = new Libro();
	}
	
	public void validarOpcion(String mensaje, int inf, int sup)
	{
		do //(10)
		{
			opcion = leer_entero(mensaje);//(11)   //Ver de sacar la asignacion aca y dejarlo solo dentro de la funcion
			if(opcion<inf || opcion>sup) //(12)(13)
				out.println("Opci\u00F3nn no v\u00E1lida."); //(14)
		} while(opcion<inf || opcion>sup); //(15)(16)
	}
	
	public int leer_entero(String mensaje)
	{
		int opcion=0;
		out.println(mensaje);//(1)
		try
		{
			opcion = teclado.nextInt();
			//int opcion = Integer.parseInt(teclado.nextLine());
		}
		catch(Exception e)
		{}
		
		return opcion;
	}
	
	public String leer_cadena(String mensaje)
	{
		out.println(mensaje);//(1)
		try
		{
			return teclado.nextLine();
		}
		catch(Exception e)
		{}
		return "";
	}
	
	public boolean noHayRegistros()
	{
		return vector.isEmpty() && opcion!=1 && opcion!=7;
	}
	
	public void obtenerDato(int index)
	{
		dato = index<0 ? null : vector.get(index);
		if (dato!=null) //(24)
		{
			out.println(); //(25)
			//imprimir.funcion(dato, contador); No se si esto es imprimir en pantalla o que
		}
	}
	
	public void verificarOpcion()
	{
		if(opcion<5) //(22)
		{
			libro.setISBN(leer_cadena("Ingrese el ISBN del libro")); //(23)
			obtenerDato(vector.indexOf(libro));
			
			if(opcion>=2 && dato==null)
			{
				out.println("Registro no encontrado.");
				return;
			}
		}
//		if (opcion>=2 && opcion<=4 && dato==null) //(29)(30)(31)
//		{
//			out.println("\nRegistro no encontrado."); // (32)
//			return;
//		}
		else
			abmLibros();
	}
	
	public void abmLibros()
	{
		switch (opcion) //(33)
		{
			case 1: //(34)
				altaLibro();
				break;
				
			case 3: //(35)
				modificarLibro();
				break;
				
			case 4: //(36)
				vector.remove(dato);
				out.println("Registro borrado correctamente.");
				break;
				
			case 5: //(37)
				Collections.sort(vector, new Libro()); //()
				out.println("Registros ordenados correctamente.");
				break;
				
			case 6: //(38)
				///Contar total de registros + Listar registros
				listarLibros();
				break;
		}
	}
	
	public void altaLibro()
	{
		if(dato!=null)
		{
			out.println("El registro ya existe.");
			return;
		}
		libro.setTitulo(leer_cadena("Ingrese el titulo"));
		libro.setAutor(leer_cadena("Ingrese el autor"));
		libro.setEditorial(leer_cadena("Ingrese el editorial"));
		libro.setEdicion(leer_entero("Ingrese el edicion"));
		libro.setAnno_de_publicacion(leer_entero("Ingrese el anno de publicacion"));
		vector.add(libro);
		libro = new Libro();
		out.println("\nRegistro agregado correctamente.");
	}
	
	public void modificarLibro()
	{
		out.println("Men\u00FA de modificaci\u00F3n de campos");
		out.println("1.- Titulo");
		out.println("2.- Autor");
		out.println("3.- Editorial");
		out.println("4.- Edicion");
		out.println("5.- Año de publicacion");
//		do //(36)
//		{
//			subopcion = leer_entero("Seleccione un n\u00FAmero de campo a modificar");
//			if (subopcion<1 || subopcion>5) //(37)(38)
//				System.out.println("Opci\u00F3n no v\u00E1lida."); //(39)
//		} while(subopcion<1 || subopcion>5); //(40)(41)
		
		validarOpcion("Seleccione un n\u00FAmero de campo a modificar", 1, 5);
		
		switch (subopcion) //(42)
		{
			case 1: //(43)
				dato.setTitulo(leer_cadena("Ingrese el nuevo titulo"));
				break;
			case 2: //(44)
				dato.setAutor(leer_cadena("Ingrese el nuevo autor"));
				break;
			case 3: //(45)
				dato.setEditorial(leer_cadena("Ingrese el nuevo editorial"));
				break;
			case 4: //(46)
				dato.setEdicion(leer_entero("Ingrese el nuevo edicion"));
				break;
			case 5: //(47)
				dato.setAnno_de_publicacion(leer_entero("Ingrese el nuevo anno de publicacion"));
				break;
		}
		out.println("\nRegistro actualizado correctamente."); //(48)
	}
	
	public void listarLibros()
	{
		for(Libro l : vector)
			System.out.println(l);
		
		out.println("Cantidad total de registros: " + vector.size());
	}

}
