package p1;

import java.util.*;
import java.io.*;

public class ProgramaPrincipal {
	final static Scanner Teclado = new Scanner(System.in);
	final static File archivoMs=new File("tiempos_ms.csv");
	final static File archivoNs=new File("tiempos_ns.csv");
	
	/**********************************************************************
	 * Method name: rutaSalida
	 *
	 * Description of the Method: Método que añade la cadena "_g" al nombre
	 * de la imagen metida por parámetro para diferenciar la imagen generada en
	 * blanco y negro de la imagen original.
	 * 
	 *********************************************************************/

	public static String rutaSalidaBN(String rutaEntrada) {
		String[] partes=rutaEntrada.split("\\.");
		partes[0]=partes[0]+"_g";
		String rutaFinal=partes[0]+"."+partes[1];
		System.out.println(rutaFinal);
		return rutaFinal;
	}
	
	public static void escribirResultado(File archivo, long tiempo) {
		try {
			FileWriter writer=new FileWriter(archivo,true);
			writer.append(Long.toString(tiempo));
			writer.append(",");
			writer.close();
		}catch (IOException e) {
			System.out.println("Archivo no encontrado");
		}
	}
	
	public static String rutaSalidaOrdenada(String rutaEntrada) {
		String[] partes=rutaEntrada.split("\\.");
		partes[0]=partes[0]+"_gOrdenada";
		String rutaFinal=partes[0]+"."+partes[1];
		System.out.println(rutaFinal);
		return rutaFinal;
	}
	/**********************************************************************
	 * Method name: generarImagenBN
	 *
	 * Description of the Method: Método que permite generar una copia en escala
	 * de grises de la imagen que deseemos introduciendo por teclado el nombre de la imagen 
	 * y su extension. Como resultado devuelve el nombre de la imagen con "_g" al final del mismo
	 * 
	 *********************************************************************/

	public static void generarImagenBN(int opcion) throws IOException, InterruptedException {
		long inicio,fin,tiempoTotal;
		System.out.println("Metodo GenerarImagenGrises");
		System.out.println("Dime el nombre completo de la imagen de entrada: ");
		String URLE = Teclado.next();
		String URLS=rutaSalidaBN(URLE);
		if(opcion==0) {
			inicio=System.currentTimeMillis();
			Auxiliar.GenerarImagenGrises(URLE,URLS);
			fin=System.currentTimeMillis();
			tiempoTotal=fin-inicio;
			System.out.println("Tiempo transcurrido: "+ tiempoTotal+ "ms");
			escribirResultado(archivoMs, tiempoTotal);
		}
		else if(opcion==1) {
			inicio=System.nanoTime();
			Auxiliar.GenerarImagenGrises(URLE,URLS);
			fin=System.nanoTime();
			tiempoTotal=fin-inicio;
			System.out.println("Tiempo transcurrido: "+ tiempoTotal+ "ns");
			escribirResultado(archivoNs, tiempoTotal);
		}


	}

	/**********************************************************************
	 * Method name: generarHistograma
	 *
	 * Description of the Method: Método que genera el histograma de la imagen que
	 * se introduzca por teclado su nombre y extensión
	 * 
	 *********************************************************************/
	public static void generarHistograma(int opcion) throws IOException, InterruptedException {
		long inicio,fin,tiempoTotal;
		System.out.println("Metodo HistogramaImagen");
		System.out.println("Dime el nombre completo de la imagen de entrada:");
		String URL = Teclado.next();
		
		if(opcion==0) {
			inicio=System.currentTimeMillis();
			Auxiliar.HistogramaImagen(URL);
			fin=System.currentTimeMillis();
			tiempoTotal=fin-inicio;
			System.out.println("Tiempo transcurrido: "+ tiempoTotal+ "ms");
			escribirResultado(archivoMs, tiempoTotal);
		}
		else if(opcion==1) {
			inicio=System.nanoTime();
			Auxiliar.HistogramaImagen(URL);
			fin=System.nanoTime();
			tiempoTotal=fin-inicio;
			System.out.println("Tiempo transcurrido: "+ tiempoTotal+ "ns");
			escribirResultado(archivoNs, tiempoTotal);
		}
	}

	/**********************************************************************
	 * Method name: imprimirHistograma
	 *
	 * Description of the Method: Método para imprimir el histograma que previamente hemos 
	 * generado y así poder visualizarlo.
	 * 
	 *********************************************************************/
	public static void imprimirHistograma(int opcion) throws IOException, InterruptedException {
		long inicio,fin,tiempoTotal;
		System.out.println("Metodo ImprimeHistograma");
		System.out.println("Dime el nombre completo de la imagen de entrada:");
		String URL = Teclado.next();
		int[] Histograma = Auxiliar.HistogramaImagen(URL);
		
		if(opcion==0) {
			inicio=System.currentTimeMillis();
			Auxiliar.ImprimeHistograma(Histograma);
			fin=System.currentTimeMillis();
			tiempoTotal=fin-inicio;
			System.out.println("Tiempo transcurrido: "+ tiempoTotal+ "ms");
			escribirResultado(archivoMs, tiempoTotal);
		}
		else if(opcion==1) {
			inicio=System.nanoTime();
			Auxiliar.ImprimeHistograma(Histograma);
			fin=System.nanoTime();
			tiempoTotal=fin-inicio;
			System.out.println("Tiempo transcurrido: "+ tiempoTotal+ "ns");
			escribirResultado(archivoNs, tiempoTotal);
		}
	}

	/**********************************************************************
	 * Method name: generarImagenOrdenada
	 *
	 * Description of the Method: Método para crear una imagen en blanco y negro
	 * pero con la escala de grises ordenada mediante el algoritmo elegido.
	 * 
	 *********************************************************************/
	public static void generarImagenOrdenada(int opcion) throws IOException, InterruptedException {
		long inicio,fin,tiempoTotal;
		System.out.println("Metodo GenerarImagenOrdenandoColumna");
		System.out.println("Dime que metodo quieres usar para ordenar las colummnas.\n0.Burbuja\n1.Quicksort.");
		int algoritmo = Teclado.nextInt();
		System.out.println("Dime el nombre completo de la imagen de entrada:");
		String URLE = Teclado.next();
		String URLS = rutaSalidaOrdenada(URLE);
		
		if(opcion==0) {
			inicio=System.currentTimeMillis();
			Auxiliar.GenerarImagenOrdenandoColumnas(URLE, URLS, algoritmo);
			fin=System.currentTimeMillis();
			tiempoTotal=fin-inicio;
			System.out.println("Tiempo transcurrido: "+ tiempoTotal+ "ms");
			escribirResultado(archivoMs, tiempoTotal);
		}
		else if(opcion==1) {
			inicio=System.nanoTime();
			Auxiliar.GenerarImagenOrdenandoColumnas(URLE, URLS, algoritmo);
			fin=System.nanoTime();
			tiempoTotal=fin-inicio;
			System.out.println("Tiempo transcurrido: "+ tiempoTotal+ "ns");
			escribirResultado(archivoNs, tiempoTotal);
		}
	}

	public static int benchmark() {
		int opcion=0;
		System.out.println("Introduce 0 si quiere medir el tiempo en milisegundos o 1 si lo quiere medir en nanosegundos");
		opcion=Teclado.nextInt();
		while(opcion<0 || opcion>1) {
			System.out.println("Error. Vuelva a seleccionar la opcion");
			opcion=Teclado.nextInt();
		}
		return opcion;
	}


	public static void menu(int benchmark) throws IOException, InterruptedException {
		boolean noEnd = true;
		int metodo = 0;
		int opcion=benchmark;
		while (noEnd) {
			System.out.println("Dime el metodo que quieres invocar:"
					+ "\n0.Salir\n1.GenerarImagenGrises\n2.HistogramaImagen\n3.ImprimeHistograma\n4.GenerarImagenOrdenandoColumna");
			metodo = Teclado.nextInt();
			switch (metodo) {
			case 0:
				noEnd = false;
				System.out.println("FIN DEL PROGRAMA");
				break;
			case 1:
				generarImagenBN(opcion);

				break;
			case 2:
				generarHistograma(opcion);
				break;
			case 3:
				imprimirHistograma(opcion);
				break;
			case 4:
				generarImagenOrdenada(opcion);
				break;
			default:
				System.out.println("Opcion incorrecta, eliga una opcion dentro del intervalo [0-4]");
			}
		}


	}

	/**********************************************************************
	 * Method name: main
	 *
	 * Description of the Method: Metodo que sera el mÃ©todo principal de ejecutar
	 * el programa usando metodos auxiliares para la ejecuacion del mismo. El main
	 * se basa en un bloque principal swtich que esta dentro de un bucle while que
	 * se encarga de pedir al usuario constantemente el metodo que quiere invocar
	 * hasta que quiera salir del progranma
	 * 
	 *********************************************************************/

	public static void main(String[] args) throws IOException, InterruptedException {
		menu(benchmark());
	}
}
