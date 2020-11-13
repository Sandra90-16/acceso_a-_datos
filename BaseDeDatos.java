package AccesoFicherosAleatorios;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class BaseDeDatos {
	
	Scanner entrada = new Scanner(System.in);
	
	File fichero; 
	
	public void crearFichero() {
		
		System.out.println("Introduzca el nombre del archivo: ");
		String sFichero;
		sFichero = entrada.nextLine();
		
		this.fichero = new File(sFichero);
		if (fichero.exists()) {
			 System.out.println("El fichero " + sFichero + " existe");
		}
		
		else {
			  System.out.println("El fichero no existe");
		}
			
		try {
					
			if (fichero.createNewFile())
			  System.out.println("El fichero se ha creado correctamente");
		}
		
		catch (IOException ioe) {
		 
			  ioe.printStackTrace();
		}
	}
	
	public void abrirFichero() {
		
		Scanner entrada = new Scanner (System.in);
		System.out.println("Introduzca el nombre del fichero que quiera abrir: ");
		String nombreFichero = entrada.nextLine();
		try  
		{  
		//crear un objeto de file con el argumento introducido
		this.fichero  = new File(nombreFichero);   
		if(!Desktop.isDesktopSupported())//comprueba si el teclado soporta la plataforma o no.
		{  
		System.out.println("not supported");  
		return;  
		}  
		Desktop desktop = Desktop.getDesktop();  
		if(fichero.exists()) {        //comprobar si el fichero existe o no
		desktop.open(fichero);              //abre el fichero específico
		} else { //lista lo que hay en el directorio
			File directorio = new File(".");
			  if(directorio.exists()) {
				  System.out.println("estos son los archivos que hay en el directorio: ");
		        if(directorio.isDirectory()) {
		            File[] archivos = directorio.listFiles();
		            
		            for(int i=0; i<archivos.length; i++) {		
		         	    if (!archivos[i].isDirectory()) { 
		         	    	System.out.println(archivos[i].getName());
		         	    }
		             }
		          }
			   }
		   }
		}
		catch(Exception e)  {  
			e.printStackTrace();  
		}
	}
	
	public void listarContenido() throws IOException {
	        String cadena;
	        FileReader f = new FileReader(fichero);
	        BufferedReader b = new BufferedReader(f);
	        while((cadena = b.readLine())!=null) {
	            System.out.println(cadena);
	        }
	        b.close();
	 }
	
	
	public void mostrarRegistro() {
		 try {
	            //crear un objeto de InputStream
	            FileInputStream fi = new FileInputStream(fichero);
	            ObjectInputStream oi = new ObjectInputStream(fi);

	            // leer objetos que están el fichero
	            Registro rg1 = (Registro) oi.readObject();
	            if(!rg1.borrado) { //muestro el registro si no está borrado
	            	System.out.println(rg1.toString());
	            } else {
	            	System.out.println("El registro no existe");
	            }
	            

	            oi.close();
	            fi.close();

	        } catch (FileNotFoundException e) {
	            System.out.println("File not found");
	        } catch (IOException e) {
	            System.out.println("Error initializing stream");
	        } catch (ClassNotFoundException e) {
	         
	            e.printStackTrace();
	        }

	    }
		
	
	public void anadirRegistro() {
		
		Integer referencia;
		
		do {
			System.out.println("Introduzca una referencia: ");
			referencia = entrada.nextInt();
			
			if(referencia != 6) {
				System.out.println("La referencia debe contener 6 dígitos");
			}
			
		}while(referencia.toString().length() != 6);
			
		String descripcion;
		do {
			System.out.println("Introduzca una descripción: ");
			descripcion = entrada.next();
		
			if(descripcion.length() > 60) {
				System.out.println("La descripción debe contener como máximo 60 caracteres");
			}
		}while(descripcion.length() > 60);
	
		System.out.println("Introduzca un precio: ");
		entrada.useLocale(Locale.US);
		float precio = 0.0f;
		precio = entrada.nextFloat();
		
		
		Registro registro1 = new Registro();
		registro1.setReferencia(referencia);
		registro1.setDescripcion(descripcion);
		registro1.setPrecio(precio);
		registro1.setBorrado(false);
		
		Registro registro2 = new Registro(referencia, descripcion, precio, false);
		
		
		OutputStream fileOutput;
		try {
			fileOutput = new FileOutputStream(fichero);
			OutputStream buffer = new BufferedOutputStream(fileOutput);
	        ObjectOutput output = new ObjectOutputStream(buffer);
	        
	        output.writeObject(registro2);//aquí se escriben los registros en el fichero
	        System.out.println("Registro creado");
	        output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modificarRegistro() throws IOException, ClassNotFoundException {
		System.out.println("Escriba una referencia: ");
		int referencia;
		referencia = entrada.nextInt();
		  FileInputStream fi = new FileInputStream(fichero);
          ObjectInputStream oi = new ObjectInputStream(fi);

          // leer objetos
          Registro rg1 = (Registro) oi.readObject();

          oi.close();
          fi.close();
          if(rg1.getReferencia() == referencia && !rg1.borrado) {
        	  System.out.println("la referencia existe");
        	  System.out.println(rg1.toString());
        	  System.out.println("¿Qué campo quiere modificar? Precio o Descripción");
        	  String opcion = entrada.next();
        	  if(opcion.equalsIgnoreCase("precio")) {
        		  // pido el precio y lo guardo en una variable
        		  System.out.println("Introduzca el precio nuevo: ");
        		  float precio = entrada.nextFloat();
        		  rg1.setPrecio(precio);
        		  // seteo el precio al objeto de registro
        	  } else if (opcion.equalsIgnoreCase("descripcion")) {
    		  	// pido la descripción y la guardo en una variable
        		  System.out.println("Introduzca la nueva descripción: ");
        		  String descripcion = entrada.next();
        		  rg1.setDescripcion(descripcion);
        		  // seteo la descripcion al objeto de registro
        	  }

        	  // escribo el registro en el fichero
    	   System.out.println("Registro modificado" + rg1.toString());
        	OutputStream fileOutput;
        	fileOutput = new FileOutputStream(fichero);
  			OutputStream buffer = new BufferedOutputStream(fileOutput);
  	        ObjectOutput output = new ObjectOutputStream(buffer);
  	       output.writeObject(rg1);
  	       output.close();
        	  
          }else {
        	  System.out.println("la referencia no existe");
          }
	}
	
	public void eliminarRegistro() throws IOException, ClassNotFoundException{
		System.out.println("Escriba una referencia: ");
		int referencia;
		referencia = entrada.nextInt();
		  FileInputStream fi = new FileInputStream(fichero);
          ObjectInputStream oi = new ObjectInputStream(fi);

          Registro rg1 = (Registro) oi.readObject();

          oi.close();
          fi.close();
          if(rg1.getReferencia() == referencia && !rg1.borrado) {
        	  System.out.println("la referencia existe");
        	  System.out.println("Vas a borrar el registro" + rg1.toString());
        	  
        	rg1.setBorrado(true);
        	
          // escribo el registro en el fichero
        	OutputStream fileOutput;
        	fileOutput = new FileOutputStream(fichero);
  			OutputStream buffer = new BufferedOutputStream(fileOutput);
  	        ObjectOutput output = new ObjectOutputStream(buffer);
  	       output.writeObject(rg1); // escribir en el fichero el nuevo objeto con borrado = true

  	     System.out.println("Registro borrado " + rg1.toString());
  	       output.close();
        	  
          }else {
        	  System.out.println("la referencia no existe");
          }
		}
	
	public void compactarFichero() throws IOException, ClassNotFoundException {
		//crear un fichero temporal
		File temporal = new File("copia");
		
		//copiar registros no marcados como borrados
		  FileInputStream fi = new FileInputStream(fichero);
          ObjectInputStream oi = new ObjectInputStream(fi);

          // leer objetos que están el fichero
          Registro rg1 = (Registro) oi.readObject();
          if(!rg1.borrado) { //muestro el registro si no está borrado	
      		OutputStream fileOutput;
        	  try {
      			fileOutput = new FileOutputStream(fichero);
      			OutputStream buffer = new BufferedOutputStream(fileOutput);
      	        ObjectOutput output = new ObjectOutputStream(buffer);
      	        
      	        output.writeObject(rg1);//aquí se escriben los registros en el fichero
      	        output.close();
      		} catch (FileNotFoundException e) {	
      			e.printStackTrace();
      		} catch (IOException e) {
      			e.printStackTrace();
      		}
        fichero.delete();
        temporal.renameTo(fichero);
        System.out.println("Fichero compactado");
         }
	}
}
