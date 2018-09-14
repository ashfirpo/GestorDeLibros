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
	ArrayList<Libro> vector = new ArrayList<Libro>();
	Scanner teclado = new Scanner(System.in, "CP850");
	PrintStream out = new PrintStream(System.out, true);
	
	public void configurarSalida() throws UnsupportedEncodingException
	{
		if(!System.getProperties().get("os.name").equals("Linux") && System.console()!=null)
			out = new PrintStream(System.out, true, "CP850");
	}
	
	public void menuPrincipal() throws FileNotFoundException
	{
		do
		{
			out.println("MEN\u00DA");
			out.println("1.- Altas");
			out.println("2.- Consultas");
			out.println("3.- Actualizaciones");
			out.println("4.- Bajas");
			out.println("5.- Ordenar registros");
			out.println("6.- Listar registros");
			out.println("7.- Salir");
			
			validarOpcion("Seleccione una opci\u00F3n", 1, 7); //Valida que la opción seleccionada esté en el rango de 1 a 7
			
			out.println();
			if (noHayRegistros())
			{
				//pausar("No hay registros.\n"); //(21) pausar????
				continue;
			}
			
			verificarOpcion(); //Se fija qué opción fue seleccionada

		}while(opcion !=7);
		
		ImprimirEnArchivo.actualizarArchivo("Ruta del archivo", vector); //Imprime los registros en un archivo
	}
	
	
	public void cargarArchivo(String ruta) throws FileNotFoundException
	{
		Scanner entrada = new Scanner(new FileReader(ruta)); //Lee el archivo
		while (entrada.hasNextLine())
		{
			String[] campos = entrada.nextLine().split("\t"); //Separa los campos por tab
			Libro libro = new Libro();
			libro.setISBN(campos[0]);
			libro.setTitulo(campos[1]);
			libro.setAutor(campos[2]);
			libro.setEditorial(campos[3]);
			libro.setEdicion(Integer.parseInt(campos[4]));
			libro.setAnno_de_publicacion(Integer.parseInt(campos[5]));
			vector.add(libro);
		}
		entrada.close();
		
		libro = new Libro(); //Limpio la variable libro
	}
	
	public void validarOpcion(String mensaje, int inf, int sup)
	{
		do
		{
			opcion = leer_entero(mensaje);   //Ver de sacar la asignacion aca y dejarlo solo dentro de la funcion
			if(opcion<inf || opcion>sup)
				out.println("Opci\u00F3nn no v\u00E1lida.");
		} while(opcion<inf || opcion>sup);
	}
	
	public int leer_entero(String mensaje)
	{
		int opcion=0;
		out.println(mensaje);
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
		out.println(mensaje);
		try
		{
			return teclado.nextLine(); //Optimizar
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
		if (dato!=null)
		{
			out.println();
			//imprimir.funcion(dato, contador); No se si esto es imprimir en pantalla o que
		}
	}
	
	public void verificarOpcion()
	{
		if(opcion<5) //Busca el ISBN para ver si ya existe el registro de ese libro
		{
			libro.setISBN(leer_cadena("Ingrese el ISBN del libro"));
			obtenerDato(vector.indexOf(libro));  //Si existe, va a buscarlo a la lista
			
			if(opcion>=2 && dato==null)
			{
				out.println("Registro no encontrado.");
				return;
			}
		}
		else
			abmLibros();
	}
	
	public void abmLibros()
	{
		switch (opcion)
		{
			case 1: //Da de alta un nuevo libro
				altaLibro();
				break;
				
			case 3:
				modificarLibro();
				break;
				
			case 4: //Eliminar un libro
				vector.remove(dato);
				out.println("Registro borrado correctamente.");
				break;
				
			case 5: //Ordena los libros por ISBN
				Collections.sort(vector, new Libro());
				out.println("Registros ordenados correctamente.");
				break;
				
			case 6://Contar total de registros + Listar registros
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
		vector.add(libro); //Agrega el libro a la lista
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
		validarOpcion("Seleccione un n\u00FAmero de campo a modificar", 1, 5); //Valida que la opción esté en el rango de 1 a 5
		
		switch (subopcion)
		{
			case 1:
				dato.setTitulo(leer_cadena("Ingrese el nuevo titulo"));
				break;
			case 2:
				dato.setAutor(leer_cadena("Ingrese el nuevo autor"));
				break;
			case 3:
				dato.setEditorial(leer_cadena("Ingrese el nuevo editorial"));
				break;
			case 4:
				dato.setEdicion(leer_entero("Ingrese el nuevo edicion"));
				break;
			case 5:
				dato.setAnno_de_publicacion(leer_entero("Ingrese el nuevo anno de publicacion"));
				break;
		}
		out.println("\nRegistro actualizado correctamente.");
	}
	
	public void listarLibros()
	{
		for(Libro l : vector) //Imprime todos los libros por pantalla
			System.out.println(l);
		
		out.println("Cantidad total de registros: " + vector.size());
	}

}
