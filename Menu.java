package AccesoFicherosAleatorios;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
	
		//este es el menú para arrancar la aplicación.
		BaseDeDatos fichero = new BaseDeDatos();
		mostrarMenu(fichero);
		
	}
	public static void mostrarMenu(BaseDeDatos fichero) throws IOException, ClassNotFoundException {
		Scanner entrada= new Scanner(System.in);

		int opcion=1;
		boolean salir = false;
		
		do{
			
			System.out.println("1 Fichero Nuevo");
			System.out.println("2 Abrir fichero");
			System.out.println("3 Listar contenido del fichero");
			System.out.println("4 Visualizar registro");
			System.out.println("5 Añadir registro");
			System.out.println("6 Modificar registro");
			System.out.println("7 Eliminar registro");
			System.out.println("8 Compactar fichero");
			System.out.println("9 Salir");
				
			System.out.println("Introduzca la operación a realizar:");
			boolean entre1y9;
			do
			{
				opcion=entrada.nextInt();
				entre1y9=(opcion>=1 && opcion<=9);
				if(!entre1y9) 
				{
					System.out.println("Error!, la opción elegida no se encuentra entre 1 y 9");
				}
			}while(!entre1y9);
			
			salir = (opcion==9);
			
			if(!salir) 
			{
				switch(opcion)
				{
					case 1:
					fichero.crearFichero();
						break;
					case 2:
					fichero.abrirFichero();
						break;
					case 3: 
					fichero.listarContenido();
						break;
					case 4: 
					fichero.mostrarRegistro();
						break;
					case 5:
					fichero.anadirRegistro();
					    break;
					case 6:
					fichero.modificarRegistro();
						break;
					case 7:
					fichero.eliminarRegistro();
						break;
					case 8:
					fichero.compactarFichero();
						break;	
					case 9:
					System.out.println("9");
						break;
				}	
			}
			
			
			if(salir) 
			{		
				System.out.println("Hasta pronto.");
			}else
				System.out.println(""  );
				
			}while(!salir);
		}
	}
