package p3;


import java.io.*;
import java.util.*;

/*********************************************************************
*
* Class Name: LecturaArchivo
* Author/s name: Pablo Carbayo y Sergio Cornejo
* Release/Creation date: 10/3
* Class version: 1.0
* Class description: Clase que nos permitirá leer un archivo csv
* con las vacas y sus características
*
**********************************************************************
*/

public class LeerArchivo {

	/*********************************************************************
	*
	* Method name: listaVacas
	*
	*
	* Description of the Method: Método que nos permite leer el archivo
	* con la base de datos de vacas y devolverlo en forma de arraylist del tipo
	* Vaca
	*
	* Calling arguments: Archivo csv de vacas
	*
	* Return value: ArrayList<Vacas>
	*
	* Required Files: vacas.csv
	*
	* List of Checked Exceptions and an indication of when each exception 
	* is thrown: FileNotFoundException (cuando el archivo no es encontrado)
	*
	*********************************************************************/

	public static ArrayList<Vaca> lecturaArchivo(File archivo) throws FileNotFoundException{
		ArrayList<Vaca> lista = new ArrayList<Vaca>();
		FileReader fr = new FileReader(archivo);
		try (BufferedReader br = new BufferedReader(fr)){
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] elementos = linea.split(" ");
				int id= Integer.parseInt(elementos[0]); 
				int espacio = Integer.parseInt(elementos[1]);
				double comida = Double.parseDouble(elementos[2]);
				double leche = Double.parseDouble(elementos[3]);
				Vaca vaca = new Vaca(id,espacio,comida,leche);
				lista.add(vaca);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
