package p2;

import java.io.*;
import java.util.*;
/*********************************************************************
*
* Class Name: LecturaFichero
* Author/s name: Pablo Carbayo y Sergio Cornejo
* Release/Creation date: 5/3
* Class version: 1.0
* Class description: Clase que nos permitirá leer un archivo csv
* con los vehículos y sus características
*
**********************************************************************
*/

public class LecturaFichero {

	/*********************************************************************
	*
	* Method name: listaCocches
	*
	*
	* Description of the Method: Método que nos permite leer el archivo
	* con la base de datos de coches y devolverlo en forma de arraylist del tipo
	* Coche
	*
	* Calling arguments: Archivo csv de vehiculos
	*
	* Return value: ArrayList<Coche>
	*
	* Required Files: cars_dataset.csv
	*
	* List of Checked Exceptions and an indication of when each exception 
	* is thrown: FileNotFoundException
	*
	*********************************************************************/
	
	public static ArrayList<Coche> listaCoches(File archivo) throws FileNotFoundException{
		ArrayList<Coche> lista = new ArrayList<Coche>();
		FileReader fr = new FileReader(archivo);
		try (BufferedReader br = new BufferedReader(fr)){
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] elementos = linea.split("\\s*,\\s*");
				String nombre = elementos[0]; 
				TipoCombustible combustible =TipoCombustible.valueOf(elementos[1]); 
				double asientos = Double.parseDouble(elementos[2]);
				TipoTransmision transmision = TipoTransmision.valueOf(elementos[3]);
				double deposito = Double.parseDouble(elementos[4]);
				double autonomia = Double.parseDouble(elementos[5]); 
				Coche coche = new Coche(nombre,combustible,transmision,asientos,deposito,autonomia);
				lista.add(coche);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
