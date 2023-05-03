package p3;

import java.util.ArrayList;

public class QuickSortVacas {


/*********************************************************************
	 *
	 * Method name: quicksortLecheEspacio
	 *
	 *
	 * Description of the Method: Metodo de ordenamiento quicksort que ordena
     * la lista de vacas de mayor a menor ratio LecheProducida/EspacioOcupado
	 *
     * Calling arguments: Lista de vacas, posición de inicio y posición final
     * 
	 *
	 *
	 *********************************************************************/

    public static void quicksortLecheEspacio(ArrayList<Vaca> listaVacas, int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particionarLecheEspacio(listaVacas, inicio, fin);
            quicksortLecheEspacio(listaVacas, inicio, indicePivote - 1);
            quicksortLecheEspacio(listaVacas, indicePivote + 1, fin);
        }
    }

        	/*********************************************************************
	 *
	 * Method name: particionarLecheEspacio
	 *
	 *
	 * Description of the Method: Metodo auxiliar del quicksort en el que se 
     * compara los ratios LecheProducida/EspacioOcupado de las vacas a comparar
     * que nos permite ordenar de mayor a menor en función de este ratio
	 *
     * Calling arguments: Lista de vacas, posición de inicio y posición final
     * 
	 * Return value: int
	 *
	 *
	 *********************************************************************/

    private static int particionarLecheEspacio(ArrayList<Vaca> listaVacas, int inicio, int fin) {
        Vaca pivote = listaVacas.get(inicio);
        int i = inicio + 1;
        int j = fin;
        while (i <= j) {
            if ((listaVacas.get(i).getLeche()/listaVacas.get(i).getEspacio()) > (pivote.getLeche()/pivote.getEspacio())) {
                i++;
            } else if ((listaVacas.get(j).getLeche()/listaVacas.get(j).getEspacio()) <= ((pivote.getLeche()/pivote.getEspacio()))) {
                j--;
            } else {
                intercambiar(listaVacas, i, j);
            }
        }
        intercambiar(listaVacas, inicio, j);
        return j;
    }

/*********************************************************************
	 *
	 * Method name: quicksortComidaEspacio
	 *
	 *
	 * Description of the Method: Metodo de ordenamiento quicksort que ordena
     * la lista de vacas de menor a mayor ratio ComidaConsumida/EspacioOcupado
	 *
     * Calling arguments: Lista de vacas, posición de inicio y posición final
     * 
	 *
	 *
	 *********************************************************************/

    public static void quicksortComidaEspacio(ArrayList<Vaca> listaVacas, int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particionarComidaEspacio(listaVacas, inicio, fin);
            quicksortComidaEspacio(listaVacas, inicio, indicePivote - 1);
            quicksortComidaEspacio(listaVacas, indicePivote + 1, fin);
        }
    }


    	/*********************************************************************
	 *
	 * Method name: particionarComidaEspacio
	 *
	 *
	 * Description of the Method: Metodo auxiliar del quicksort en el que se 
     * compara los ratios ComidaConsumida/EspacioOcupado de las vacas a comparar
     * que nos permite ordenar de menor a mayor en función de este ratio
	 *
     * Calling arguments: Lista de vacas, posición de inicio y posición final
     * 
	 * Return value: int
	 *
	 *
	 *********************************************************************/

    private static int particionarComidaEspacio(ArrayList<Vaca> listaVacas, int inicio, int fin) {
        Vaca pivote = listaVacas.get(inicio);
        int i = inicio + 1;
        int j = fin;
        while (i <= j) {
            if ((listaVacas.get(i).getComida()/listaVacas.get(i).getEspacio()) < (pivote.getComida()/pivote.getEspacio())) {
                i++;
            } else if ((listaVacas.get(j).getComida()/listaVacas.get(j).getEspacio()) >= ((pivote.getComida()/pivote.getEspacio()))) {
                j--;
            } else {
                intercambiar(listaVacas, i, j);
            }
        }
        intercambiar(listaVacas, inicio, j);
        return j;
    }
    

	/*********************************************************************
	 *
	 * Method name: intercambiar
	 *
	 *
	 * Description of the Method: Método para cambiar una vaca de la lista por otra
	 *
	 * Calling arguments: Lista de vacas, posicion i, posicion j
	 *
	 *
	 *
	 *********************************************************************/

    private static void intercambiar(ArrayList<Vaca> listaVacas, int i, int j) {
        Vaca temp = listaVacas.get(i);
        listaVacas.set(i, listaVacas.get(j));
        listaVacas.set(j, temp);
    }
}
