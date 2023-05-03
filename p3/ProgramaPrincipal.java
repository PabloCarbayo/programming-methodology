package p3;

import java.util.*;
import java.io.*;;

/*********************************************************************
 *
 * Class Name: ProgramaPrincipal
 * Author/s name: Sergio Cornejo y Pablo Carbayo
 * Release/Creation date: 10/4
 * Class version: 1.0
 * Class description: Clase donde
 * podremos optar por maximizar la producción de leche o minimizar el consumo
 * de comida de las vacas a la hora de crear una granja
 **********************************************************************
 */

public class ProgramaPrincipal {
	// Variables globales
	private static File archivo = new File("vacas.csv");
	private static Scanner teclado = new Scanner(System.in);

	/*********************************************************************
	 *
	 * Method name: espacioDisponible
	 *
	 *
	 * Description of the Method: Metodo que nos permite introducir
	 * por teclado el espacio en hectáreas de la granja
	 *
	 * Return value: int
	 *
	 *
	 *********************************************************************/
	private static int espacioDisponible() {

		System.out.println("Introduce el espacio de la finca (en hectareas)\n");
		int espacio = teclado.nextInt();

		// Bucle para volver a meter el espacio de la finca siempre que el valor
		// introducido sea negativo
		while (espacio <= 0) {
			System.out.println("Valor introducido erroneo. Vuelve a introducir espacio disponible\n");
			espacio = teclado.nextInt();
		}

		System.out.println("El espacio será de " + espacio + " hectareas\n");
		return espacio;
	}

	/*********************************************************************
	 *
	 * Method name: maximizarLecheProducida
	 *
	 *
	 * Description of the Method: Método con el cual, dada la lista de vacas
	 * se calculará el ratio Leche/Comida de cada vaca, se ordenarán de mayor
	 * a menor según su ratio y se elegirán las vacas que mayor ratio tengan,
	 * siempre y cuando el espacio disponible en la finca sea suficiente.
	 *
	 * Calling arguments: Lista de vacas y la cantidad de vacas a comprar
	 *
	 *
	 *
	 *********************************************************************/

	public static void maximizarLecheProducida(ArrayList<Vaca> vacas) {

		// Inicializamos las variables locales
		int espacio = espacioDisponible();
		ArrayList<Vaca> solucion = new ArrayList<Vaca>();
		int contador = 0; // Contador con el que seleccionaremos la vaca
		double totalLecheProducida = 0;
		double totalComidaConsumida = 0;

		// Ordenamos la lista de vacas descendentemente en función de del ratio Leche
		// producida/Espacio ocupado
		QuickSortVacas.quicksortLecheEspacio(vacas,0, vacas.size()-1);

		// Comprobamos que el espacio de la vaca elegida no supera el espacio restante
		while (espacio - vacas.get(contador).getEspacio() >= 0) {
			espacio -= vacas.get(contador).getEspacio();
			totalLecheProducida += vacas.get(contador).getLeche();
			totalComidaConsumida += vacas.get(contador).getComida();
			solucion.add(vacas.get(contador));
			contador++;
		}

		System.out.println("Se han podido comprar " + solucion.size() +
				" vacas\nque producen un total de " + totalLecheProducida + " litros de leche consumiendo un total de "
				+ totalComidaConsumida + " kg de comida");
		System.out.println("El espacio restante es de " + espacio + " hectareas");
		System.out.println("Se han comprado las siguientes vacas");

		// Bucle para poder imprimir las vacas que forman la solución
		for (Vaca vaca : solucion) {
			System.out.println("Vaca " + vaca.getId() + " leche producida " + vaca.getLeche());
		}

	}

	/*********************************************************************
	 *
	 * Method name: minimizarComida
	 *
	 *
	 * Description of the Method: Método con el cual, dada la lista de vacas
	 * se ordenarán de menor a mayor según su consumo de comida
	 * y se elegirán las vacas que menor cantidad de comida consuman
	 * siempre y cuando el espacio disponible en la finca y la cantidad de comida
	 * disponible sean suficiente.
	 *
	 * Calling arguments: Lista de vacas y la cantidad de vacas a comprar
	 *
	 *
	 *
	 *********************************************************************/

	private static void minimizarComida(ArrayList<Vaca> vacas) {
		System.out.println(
				"POSIBLE SOLUCIÓN\nPara minimizar el gasto de comida se compran 0 vacas ya que el gasto es 0, por lo tanto es mínimo.");
		System.out.println("\nPero en este caso, minimizaremos el gasto de comida hasta llenar el espacio de la finca");
		// Inicializamos las variables locales
		int espacio = espacioDisponible();
		ArrayList<Vaca> solucion = new ArrayList<Vaca>();
		double totalLecheProducida = 0;
		double totalComidaConsumida = 0;
		int contador = 0; // Contador con el que seleccionaremos la vaca
		// Ordenamos la lista de vacas ascendentemente en función del ratio Comida
		// consumida/Espacio ocupado
		QuickSortVacas.quicksortComidaEspacio(vacas, 0, vacas.size()-1);
		// Comprobamos que el espacio de la vaca elegida no supera el espacio restante
		while (espacio - vacas.get(contador).getEspacio() >= 0) {
			espacio -= vacas.get(contador).getEspacio();
			totalLecheProducida += vacas.get(contador).getLeche();
			totalComidaConsumida += vacas.get(contador).getComida();
			solucion.add(vacas.get(contador));
			contador++;
		}

		System.out.println("Se han podido comprar " + solucion.size() +
				" vacas\nque producen un total de " + totalLecheProducida + " litros de leche consumiendo un total de "
				+ totalComidaConsumida + " kg de comida");
		System.out.println("El espacio restante es de " + espacio + " hectareas");
		System.out.println("Se han comprado las siguientes vacas");
		// Bucle para poder imprimir las vacas que forman la solución
		for (Vaca vaca : solucion) {
			System.out.println("Vaca " + vaca.getId() + " comida consumida " + vaca.getLeche());
		}

	}

	/*********************************************************************
	 *
	 * Method name: menu
	 *
	 * Description of the Method: Metodo del tipo menu para elegir entre las
	 * distintas
	 * opciones: maximizar produccion de leche, minimizar consumo de comida o salir
	 * del programa
	 *
	 * List of Checked Exceptions and an indication of when each exception is
	 * thrown: FileNotFoundException (cuando el fichero de vacas no es encontrado)
	 *
	 * Calling arguments: Lista de vacas
	 * 
	 *********************************************************************/

	private static void menu(ArrayList<Vaca> listaVacas) throws FileNotFoundException {
		boolean noEnd = true;
		// Bucle para poder seleccionar las distintas opciones siempre y cuando noEnd
		// sea true
		while (noEnd) {
			System.out.println("\nBienvenido al gestor de ganaderías. ¿Qué desea hacer?\n");
			System.out.println("1.Maximizar la producción de leche\n2.Minimizar el consumo de comida\n3.Salir");
			int opcion = teclado.nextInt();
			while (opcion < 1 || opcion > 3) {
				System.out.println("Valor introducido erroneo. Vuelva a elegir una opcion");
				opcion = teclado.nextInt();
			}

			switch (opcion) {
				case 1:
					System.out.println("\nSe maximizará la produccion de leche\n");
					maximizarLecheProducida(listaVacas);
					break;
				case 2:
					System.out.println("\nSe minimizará el consumo de comida\n");
					minimizarComida(listaVacas);
					break;

				case 3:
					System.out.println("\n¡Hasta la proxima!\n");
					noEnd = false;
					break;

			}
		}

	}

	/*********************************************************************
	 *
	 * Method name: main
	 *
	 * Description of the Method: Metodo que permite la ejecucion del programa
	 *
	 * List of Checked Exceptions and an indication of when each exception is
	 * thrown: FileNotFoundException (cuando el fichero de vacas no es encontrado)
	 *
	 * 
	 *********************************************************************/

	public static void main(String[] args) throws FileNotFoundException {
		menu(LeerArchivo.lecturaArchivo(archivo));
	}

}
