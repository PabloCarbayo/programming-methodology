package p2;

import java.io.*;
import java.util.*;


/*********************************************************************
 *
 * Class Name: ProgramaPrincipal Author/s name: Sergio Cornejo y Pablo Carbayo
 * Release/Creation date: 6/3 Class version: 1.0 Class description: Clase donde
 * podremos calcular los determinados vehículos que pueden terminar un viaje
 * comprobando los litros de combustible que ha gastado durante el recorrido
 *
 **********************************************************************
 */

public class ProgramaPrincipal {

	// Variables locales
	private static Scanner teclado = new Scanner(System.in);
	private static File archivo = new File("cars_dataset.csv");

	/*********************************************************************
	 *
	 * Method name: puntosInteres
	 *
	 *
	 * Description of the Method: Metodo que crea un vector de doubles para
	 * describir las distancias entre los diferentes puntos de interes del viaje a
	 * realizar
	 *
	 * Calling arguments: Numero de puntos de interes que tiene el viaje
	 *
	 * Return value: double[] array
	 *
	 *
	 *********************************************************************/
	public static double[] puntosInteres(int n) {
		double[] array= new double[n];
		Random random = new Random();
		System.out.println("Distancia máxima entre POI:");
		int max=teclado.nextInt();
		for (int i = 0; i < n; i++) {
			double aleatorio =random.nextDouble(max);
			array[i]=aleatorio;
		}
		for(double num : array) {
			System.out.println("Distancia: "+num);
		}
		return array;
	}

	/*********************************************************************
	 *
	 * Method name: calcularViaje
	 *
	 *
	 * Description of the Method: Metodo que se utiliza para organizar los coches
	 * en funcion de si acaba o no el viaje
	 *
	 * Calling arguments: ArrayList<Coche> coches, double[] poi,
	 * ArrayList<Coche> cochesAcaban, ArrayList<Coche> cochesNoAcaban
	 *
	 *
	 *********************************************************************/

	public static void calcularViaje(ArrayList<Coche> coches, double[] poi, ArrayList<Coche> cochesAcaban,
			ArrayList<Coche> cochesNoAcaban) {

		for(Coche coche : coches) {
			Coche aux=dyv(coche,poi);
			if(aux.getAcaba()) {
				cochesAcaban.add(aux);
			}
			else {
				cochesNoAcaban.add(aux);
			}
		}
	}

	/*********************************************************************
	 *
	 * Method name:dyv
	 *
	 *
	 * Description of the Method: Metodo en el que se compara el gasto calculado
	 * en el metodo dyvGasto con la capacidad del depósito del coche para indicar
	 * si el coche acaba o no el trayecto
	 *
	 * Calling arguments: Coche coche, double[] poi
	 * 
	 * Return value: Objeto coche en el que se cambia el valor tipo boolean
	 * si acaba el trayecto o no
	 *
	 *
	 *********************************************************************/
	private static Coche dyv(Coche coche, double[] poi) {
	    double gasto = dyvGasto(coche, poi, 0, poi.length-1);
	    coche.setGastoTotal(gasto);
	    if(coche.getCapacidadDeposito()<gasto) {
	        coche.setAcaba(false);
	    } else {
	        coche.setAcaba(true);
	    }
	    return coche;
	}

	/*********************************************************************
	 *
	 * Method name:dyvGasto
	 *
	 *
	 * Description of the Method: Metodo en el que se calcula el gasto de combustible
	 * del coche metido por parámetro, siguiendo una estrategia divide y vencerás´
	 *
	 * Calling arguments: Coche coche, double[] poi, int inicio, int fin
	 * 
	 * Return value: Valor tipo double el cual hace referencia a los litros 
	 * gastados en total durante el viaje.
	 *
	 *
	 *********************************************************************/
	private static double dyvGasto(Coche coche, double[] poi, int inicio, int fin) {
	    if (inicio == fin) {
	        double distancia = poi[inicio];
	        return distancia / coche.getConsumoMedio();
	    }
	    int mitad = (inicio + fin) / 2;
	    double g1 = dyvGasto(coche, poi, inicio, mitad);
	    double g2 = dyvGasto(coche, poi, mitad+1, fin);
	    return g1 + g2;
	}


	/*********************************************************************
	 *
	 * Method name: ordenarMenorMayor
	 *
	 *
	 * Description of the Method: Metodo que llama al metodo de ordenacion quicksort
	 * controlando que la lista llamada no este vacia
	 *
	 * Calling arguments: ArrayList<Coche> cochesAcaban
	 *
	 * Return value: lista de coches que acaban el viaje ordenada de menos litros
	 * gastados a mas
	 *
	 *
	 *********************************************************************/

	public static void ordenarMenorMayor(ArrayList<Coche> cochesAcaban) {
		if (cochesAcaban == null || cochesAcaban.size() == 0) {
			return;
		}
		quicksort(cochesAcaban, 0, cochesAcaban.size() - 1);
	}

	/*********************************************************************
	 *
	 * Method name: quicksort
	 *
	 *
	 * Description of the Method: Metodo de ordenacion quicksort
	 *
	 * Calling arguments: ArrayList<Coche> cochesAcaban, int inicio, int fin
	 *
	 * Return value: lista de coches ordenada de menor a mayor segun los litros
	 * gastados durante el viaje
	 *
	 *
	 *********************************************************************/

	public static void quicksort(ArrayList<Coche> cochesAcaban, int inicio, int fin) {
		int i = inicio;
		int j = fin;
		int numeroPivote = inicio + (fin - inicio) / 2;
		double gastoTotalPivote = cochesAcaban.get(numeroPivote).getGastoTotal();

		while (i <= j) {
			while (cochesAcaban.get(i).getGastoTotal() < gastoTotalPivote) {
				i++;
			}
			while (cochesAcaban.get(j).getGastoTotal() > gastoTotalPivote) {
				j--;
			}
			if (i <= j) {
				Coche temp = cochesAcaban.get(i);
				cochesAcaban.set(i, cochesAcaban.get(j));
				cochesAcaban.set(j, temp);
				i++;
				j--;
			}
		}
		if (inicio < j) {
			quicksort(cochesAcaban, inicio, j);
		}
		if (i < fin) {
			quicksort(cochesAcaban, i, fin);
		}
	}

	/*********************************************************************
	 *
	 * Method name: menu
	 *
	 * Description of the Method: Metodo que devuelve un String mostrando las
	 * opciones del men�
	 *
	 * Return value: String que muestra los datos del men�
	 *
	 *********************************************************************/

	public static String menu() {
		return "Dime lo que quieres hacer:\n1.Mostrar vehiculos que acaban el trayecto de forma ordenada."
				+ "\n2.Mostrar vehiculos que no acaban el trayecto de forma ordenada.\n3.Salir";
	}

	/*********************************************************************
	 *
	 * Method name: pedirNumero
	 *
	 * Description of the Method: Metodo que pide un numero al usuario y lo devuelve
	 *
	 * Return value: Devuelve un valor de tipo int que se le ha pedido al usuario
	 * por teclado
	 *
	 *********************************************************************/

	public static int pedirNumero(String s) {
		System.out.println(s);
		int numero = teclado.nextInt();
		return numero;
	}

	/*********************************************************************
	 *
	 * Method name: bucleSwitch
	 *
	 * Description of the Method: Metodo que entra en un bucle While que muestra
	 * constantemente un men� para elegir lo que se quiere hacer hasta que el
	 * usuario elija la opcion 3
	 *
	 *
	 * Calling arguments: boolean seguir, ArrayList<Coche> cochesAcaban,
	 * ArrayList<Coches> cochesNoAcaban
	 *
	 *********************************************************************/

	public static void bucleSwitch(boolean seguir, ArrayList<Coche> cochesAcaban, ArrayList<Coche> cochesNoAcaban) {
		while (seguir) {
			int numero = pedirNumero(menu());
			switch (numero) {

			case 1:
				for (Coche coche : cochesAcaban) {
					System.out.println(
							"Modelo: " + coche.getModelo() + " | Capacidad dep�sito: " + coche.getCapacidadDeposito()
							+ " | Consumo total: " + coche.getGastoTotal() + " litros (Termina el trayecto)");
				}
				break;
			case 2:
				for (Coche coche : cochesNoAcaban) {
					System.out.println("Modelo: " + coche.getModelo() + " | Capacidad dep�sito: "
							+ coche.getCapacidadDeposito() + " | Consumo total: " + coche.getGastoTotal()
							+ " litros (No termina el trayecto)");
				}
				break;
			case 3:
				seguir = false;
				System.out.println("---Saliendo del programa---");
				break;
			default:
				System.out.println("Elige un intervalo pertenenciante a [1-3]");
			}
		}
	}

	/*********************************************************************
	 *
	 * Method name: main
	 *
	 * Description of the Method: Metodo que permite ejecutar el programa
	 *
	 * List of Checked Exceptions and an indication of when each exception is
	 * thrown. FileNotFoundException
	 *
	 *********************************************************************/

	public static void main(String[] args) throws FileNotFoundException {

		boolean seguir = true;
		ArrayList<Coche> cochesAcaban = new ArrayList<Coche>();
		ArrayList<Coche> cochesNoAcaban = new ArrayList<Coche>();

		System.out.println("Introduce por teclado el n�mero de puntos de interes que quiere visitar");
		int n = teclado.nextInt();
		System.out.print("Distancia entre los puntos de interes: ");
		calcularViaje(LecturaFichero.listaCoches(archivo), puntosInteres(n), cochesAcaban, cochesNoAcaban);
		ordenarMenorMayor(cochesAcaban);
		ordenarMenorMayor(cochesNoAcaban);
		bucleSwitch(seguir, cochesAcaban, cochesNoAcaban);
	}







}
