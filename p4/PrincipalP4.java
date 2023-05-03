package MetodologiaProg.p4;

import java.util.*;
import java.io.*;

/*********************************************************************
 *
 * Class Name: PrincipalP4.java
 * Author/s name: Pablo Carbayo, Sergio Cornejo
 * Class description: Clase que calcula a partir de un archivo .csv en el que se
 * contien informacion de vacas, cuales son las
 * mejores combinaciones que pruducen mas leche en un espacio limitado
 *
 **********************************************************************
 */

public class PrincipalP4 {

    private static File archivo = new File("C:/Users/sergi/Desktop/Universidad/VisualStudio/vacas.csv");
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String args[]) throws FileNotFoundException {
        ArrayList<Vaca> listaVacas = LeerArchivo.lecturaArchivo(archivo);
        menu(listaVacas);

        /*
         * ArrayList<int[]> listaSolucionesFinales = new ArrayList<>();
         * int lotes = backtracking22(0, new int[listaVacas.size()], listaVacas, 100, 0,
         * 1500,
         * listaSolucionesFinales, 0);
         * System.out.println("Lotes totales: " + lotes);
         */
    }

    /*********************************************************************
     *
     * Method name: backtracking1
     * Description of the Method: Metodo que sera el encargado de hacer backtracking
     * hasta encontrar la primera solucion
     * que sea valida. Una vez la encunetre la mostrará por pantalla
     * Calling arguments:
     * -int k: La etapa en la cual asignamos una vaca o no (Segun cumpla el test de
     * fracaso) a nuestro vector de soluciones
     * -int[] soluciones: Vector donde se iran guardando nuestras soluciones
     * parciales. En este caso nuestra unica solucion
     * -ArrayList<Vaca>: Lista que contiene todas las vacas con sus caratceristicas
     * -double lecheMinima: La leche que sera requisito para que un lote de vacas
     * sea adecuado
     * -int espacio: El espacio maximo que pueden ocupar nuestro conjunto de vacas
     * -boolean solucionEncontrada: Variable booleanan con la cual se para la
     * tecnica de backtracking una vez hemos encontrado la primeras solucion
     * Return value: boolean
     *
     *********************************************************************/

    private static boolean backtracking1(int k, int[] soluciones, ArrayList<Vaca> listaVacas, double lecheMinima,
            double lecheParcial, int espacio, boolean solucionEncontrada) {
        if (!solucionEncontrada && k == listaVacas.size()) { // Ya se ha recorrido toda la lista de vacas y no hemos
                                                             // encontrado solucion
            if (lecheParcial >= lecheMinima) { // Test de solucion (Superamos la leche minima que se nos exige)
                System.out.println("Primera solucion encontrada:\n");
                for (int i = 0; i < soluciones.length; i++) {
                    if (soluciones[i] == 1) { // Si es una solucion, mostramos las caracteristicas de la vaca
                        System.out.println(listaVacas.get(i).toString());
                    }
                }
                System.out.println();
                return true; // Cambiamos a true para indicar que se encontró una solución
            }
        } else if (!solucionEncontrada) { // Solo seguimos buscando si no se ha encontrado la solución
            if (espacio - listaVacas.get(k).getEspacio() >= 0) { // Test de fracaso (Cabe en el espacio que nos queda
                                                                 // restante)
                soluciones[k] = 1; // Marcamos la vaca como solucion y llamos al metodo con el vector de
                                   // soluciones, el espacio y la leche actualizado
                solucionEncontrada = backtracking1(k + 1, soluciones, listaVacas, lecheMinima,
                        lecheParcial + listaVacas.get(k).getLeche(),
                        (espacio - listaVacas.get(k).getEspacio()), solucionEncontrada);
            }
            soluciones[k] = 0; // Si no es solucion la marcamos como 0 y llamamos al metodo sin actualizar las
                               // variables de leche y espacio peor sumando en uno la etapa
            solucionEncontrada = backtracking1(k + 1, soluciones, listaVacas, lecheMinima, lecheParcial, espacio,
                    solucionEncontrada);
        }
        return solucionEncontrada;
    }

    /*********************************************************************
     *
     * Method name: backtracking2
     * Description of the Method: Metodo que sera el encargado de hacer backtracking
     * para encontrar todas las soluciones que son validas. Segun las va encontrando
     * las va metiendo en una lista de soluciones y a su vez va actualizando el
     * parametro que se le
     * pasa para averiguar el numero total de soluciones posibles
     * Calling arguments:
     * -int k: La etapa en la cual asignamos una vaca o no (Segun cumpla el test de
     * fracaso) a nuestro vector de soluciones
     * -int[] soluciones: Vector donde se iran guardando nuestras
     * solucionesparciales.
     * -ArrayList<Vaca>: Lista que contiene todas las vacas con sus caratceristicas
     * -double lecheMinima: La leche que sera requisito para que los lotes de vacas
     * sean validos
     * -int espacio: El espacio maximo que pueden ocupar nuestros lotes de vacas
     * -ArrayList<int []> listaSoluciones: Lista donde se guardaron todos los
     * vectores que son solucion
     * -int numeroCombinaciones: El numero de soluciones que encontrará el metodo,
     * se irá
     * pasando como argumento en cada llamada para que se vaya actualizando
     * Return value: void
     *
     *********************************************************************/

    private static int backtracking2(int k, int[] soluciones, ArrayList<Vaca> listaVacas, double lecheMinima,
            double lecheParcial, int espacio, ArrayList<int[]> listaSoluciones, int numeroCombinaciones) {
        if (k == listaVacas.size()) { // Ya se ha recorrido toda la lista de vacas
            if (lecheParcial >= lecheMinima) { // Test de solucion (Superamos la leche minima que se nos exige)
                int[] copia = new int[soluciones.length];
                System.arraycopy(soluciones, 0, copia, 0, soluciones.length);
                listaSoluciones.add(copia);
                numeroCombinaciones++; // Aumentamos el numero de soluciones encontradas que llevamos
            }
        } else {
            if (espacio - listaVacas.get(k).getEspacio() >= 0) { /* Test de fracaso (Si superamos el espacio, no es una
                                                                  solucion valida, con lo que no cogemos esta vaca como solucion)*/
                soluciones[k] = 1; // Marcamos como solucion si no superamos el espacio y seguimos comprobando
                numeroCombinaciones = backtracking2(k + 1, soluciones, listaVacas, lecheMinima,
                        lecheParcial + listaVacas.get(k).getLeche(),
                        (espacio - listaVacas.get(k).getEspacio()), listaSoluciones, numeroCombinaciones);
            }
            // Si superamos el espacio, no marcamos esa vaca como solucion y seguimos buscando combinaciones.
            soluciones[k] = 0; /* Si la vaca de la etapa K no puede ser solucion, se marca como no solucion y
                               llamamos al metodo de forma recursiva sin actualizar ningun parametro */
            numeroCombinaciones = backtracking2(k + 1, soluciones, listaVacas, lecheMinima, lecheParcial, espacio,
                    listaSoluciones, numeroCombinaciones);
        }
        return numeroCombinaciones;
    }

    /*********************************************************************
     *
     * Method name: apartadoB
     * Description of the Method: Metodo que será el encargado de llamar al metodo
     * de backtracking2 y una vez se tienen todoas las soluciones
     * las mostrará por pantlla una a uno, diciendo al final el numero total de
     * soluciones encontradas a traves de la variable de "soluciones"
     * Calling arguments:
     * -ArrayList<Vaca> lista de vacas que contiene todas las vacas.
     * -double lecheMinima: La leche minima que queremos que nuestros lotes de vacas
     * produzcan
     * -int espacio: El espacio que deben ocupar como maximo nuestro lote de vacas
     * Return value: void
     *
     *********************************************************************/

    private static void apartadoB(ArrayList<Vaca> listaVacas, double lecheMinima, int espacio) {
        ArrayList<int[]> listaSolucionesFinales = new ArrayList<>();

        int soluciones = backtracking2(0, new int[listaVacas.size()], listaVacas, lecheMinima, 0, espacio,
                listaSolucionesFinales, 0);

        for (int i = 0; i < listaSolucionesFinales.size(); i++) {
            int solucionParcial[] = listaSolucionesFinales.get(i);
            System.out.println("\nLote de vacas " + (i + 1));
            for (int j = 0; j < solucionParcial.length; j++) {
                if (solucionParcial[j] == 1) {
                    System.out.println(listaVacas.get(j).toString());
                }
            }
        }
        System.out.println("\n---Hay un total de " + soluciones
                + " lotes diferentes de vacas que se pueden comprar.---");
    }

    /*********************************************************************
     *
     * Method name: backtracking3
     * Description of the Method: Metodo que sera el encargado de hacer backtracking
     * para encontrar la mejor solucion valida. Ira comparando segun
     * encuentra soluciones y se quedará con la mejor. Este metodo no hara un uso
     * excesivo de memoria
     * ya que no guarda todas las soluciones, solo guarda una, la cual se
     * reemplazará por otra
     * (Si se encuntra una mejor)
     * Calling arguments:
     * -int k: La etapa en la cual asignamos una vaca o no (Segun cumpla el test de
     * fracaso) a nuestro vector de soluciones
     * -int[] soluciones: Vector donde se iran guardando nuestras
     * solucionesparciales.
     * -ArrayList<Vaca>: Lista que contiene todas las vacas con sus caratceristicas
     * -int espacio: El espacio maximo que pueden ocupar nuestros lotes de vacas
     * -int[] mejorSolucion: Vector que se ira comprando con las soluciones que se
     * van encontrando y en la que se guardara la mejor solucion que se encuentre
     * ContenedorLeche contenedorLeche: Objeto que tiene como atributo la
     * lecheMaxima, la cual se ira usando para ir comparando si la leche que se
     * obtiene
     * en la etapa actual es mayor que la de la etapa anterioir, y asi poder
     * quedarnos con la solucion que nos porporciona mas leche
     * Return value: void
     *
     *********************************************************************/

    private static void backtracking3(int k, int[] soluciones, ArrayList<Vaca> listaVacas, int espacio,
            int[] mejorSolucion, ContenedorLeche contenedorLeche) {
        if (k == listaVacas.size()) { // Ya se ha recorrido toda la lista de vacas
            int leche = 0; // Leche que se producirá en esa solucion
            for (int i = 0; i < soluciones.length; i++) {
                if (soluciones[i] == 1) {
                    leche += listaVacas.get(i).getLeche(); // Actualizamos la leche segun vamos recorriendo las vacas
                }
            }
            if (leche > contenedorLeche.mejorLeche) { /* Comprobamos si la leche que acabamos de actualiar es mejor que 
                la que es mejor hasta ese momento. Si es mejor, se actualiza elatributo donde secontiene la leche mejor y 
                cambiamos la solucion que teniamoscomo mejor por la que acabamos de encontrar */
                contenedorLeche.mejorLeche = leche;
                for (int i = 0; i < soluciones.length; i++) {
                    mejorSolucion[i] = soluciones[i];
                }
            }
        } else { // Si todavia no hemos llegado al final de la lista de vacas
            if (espacio - listaVacas.get(k).getEspacio() >= 0) { /*Si la vaca de la etapa K cabe en el espacio quetenemos restante la 
                marcamos como solucion yllamamos de forma recursiva al metodo actualizandolos parametros pertinentes */
                soluciones[k] = 1;
                backtracking3(k + 1, soluciones, listaVacas, (espacio - listaVacas.get(k).getEspacio()), mejorSolucion,
                        contenedorLeche);
            }
            soluciones[k] = 0; /*Si la vaca de la etapa K no puede ser solucion, se marca como no solucion y llamamos al metodo de forma 
            recursivain actualizar ningun parametro*/
            backtracking3(k + 1, soluciones, listaVacas, espacio, mejorSolucion, contenedorLeche);
        }
    }

    /*********************************************************************
     *
     * Method name: apartadoC
     * Description of the Method: Metodo que será el encargado de llamar al metodo
     * de backtracking3, y una vez se obtenga la mejor solucion, la
     * mostrara por pantalla junto al total de leche producida y el espacio ocupado
     * por ese lote de vacas
     * Calling arguments:
     * -ArrayList<Vaca> lista de vacas que contiene todas las vacas.
     * -int espacio: El espacio que deben ocupar como maximo nuestro lote de vacas
     * Return value: void
     *
     *********************************************************************/

    private static void apartadoC(ArrayList<Vaca> listaVacas, int espacio) {
        int[] mejorSolucion = new int[listaVacas.size()];
        backtracking3(0, new int[listaVacas.size()], listaVacas, espacio, mejorSolucion, new ContenedorLeche());
        System.out.println("La mejor combinacion es:");
        double mejorLeche = 0;
        int mejorEspacio = 0;
        for (int i = 0; i < mejorSolucion.length; i++) {
            if (mejorSolucion[i] == 1) {
                System.out.print("Vaca: " + listaVacas.get(i).getId() + " ");
                mejorLeche += listaVacas.get(i).getLeche();
                mejorEspacio += listaVacas.get(i).getEspacio();
            }
        }
        System.out.println("con " + String.format("%.1f", mejorLeche) + " litros en " + mejorEspacio + " hectareas.\n");
    }

    /*********************************************************************
     *
     * Method name: menu
     * Description of the Method: Metodo que sera el encargado de pedirle al usuario
     * los datos que quiere para la realizacion del problema y cual de los puntos
     * quiere que se resuelva
     * Calling arguments:
     * ArrayList<Vaca> listaVacas: Lista que contendra todas las vacas que se van a
     * usar para resolver los problemas
     * Return value: void
     *
     *********************************************************************/

    private static void menu(ArrayList<Vaca> listaVacas) {
        boolean noEnd = true;

        while (noEnd) {
            System.out.println("\nBienvenido al gestor de ganaderías. ¿Qué desea hacer?");
            System.out.println("1.Combinacion para dar la leche minima\n2.Combinaciones para dar la leche minima\n" +
                    "3.Mejor combinacion para dar la leche minima\n4.Salir");
            int opcion = teclado.nextInt();
            while (opcion < 1 || opcion > 4) {
                System.out.println("Valor introducido erroneo. Vuelva a elegir una opcion");
                opcion = teclado.nextInt();
            }

            switch (opcion) {
                case 1:
                    System.out.println(
                            "Se dara la primera combiancion de vacas que den el minimo de leche requerido respetando el espacio");
                    double lecheMinima1 = lecheMinima();
                    int espacioMaximo1 = espacioDisponible();
                    backtracking1(0, new int[listaVacas.size()], listaVacas, lecheMinima1, 0, espacioMaximo1, false);
                    break;
                case 2:
                    System.out.println(
                            "Se daran todas las combinaciones posibles para dar la leche minima requerida respetando el espacio");
                    double lecheMinima2 = lecheMinima();
                    int espacioMaximo2 = espacioDisponible();
                    apartadoB(listaVacas, lecheMinima2, espacioMaximo2);
                    break;

                case 3:
                    System.out.println("Metodo mejorador para culcular la leche maximo segun el espacio.");
                    int espacioMaximo3 = espacioDisponible();
                    apartadoC(listaVacas, espacioMaximo3);
                    break;

                case 4:
                    System.out.println("¡Hasta la proxima!");
                    noEnd = false;
                    break;

            }
        }
    }

    /*********************************************************************
     *
     * Method name: espacioDiponible
     * Description of the Method: Metodo que sera el encargado de pedirle al usuario
     * el espacio que quiere que haya disponible
     * Return value: int - Que sera el espacio que quiere el usuario
     *
     *********************************************************************/

    private static int espacioDisponible() {
        System.out.println("Introduce el espacio de la granja");
        int espacio = teclado.nextInt();
        while (espacio < 0) {
            System.out.println("Valor introducido erroneo. Vuelve a introducir el espacio de la finca.");
            espacio = teclado.nextInt();
        }
        System.out.println("El espacio será de " + espacio + " hectareas\n");
        return espacio;
    }

    /*********************************************************************
     *
     * Method name: lecheMinima
     * Description of the Method: Metodo que sera el encargado de pedirle al usuario
     * la leche minima que quiere que se produzca
     * Return value: double - La leche minima que queire que se produzca
     *
     *********************************************************************/

    private static double lecheMinima() {
        System.out.println("Introduce el minimo de litros que quieres obtener");
        double litros = teclado.nextDouble();
        while (litros < 0) {
            System.out.println("Valor introducido erroneo. Vuelve a introducir la cantidad de comida");
            litros = teclado.nextDouble();
        }
        System.out.println("La leche minima sera de " + litros + " litros");
        return litros;
    }
}
