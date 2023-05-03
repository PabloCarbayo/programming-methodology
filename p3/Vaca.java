package p3;

/*********************************************************************
*
* Class Name: Vaca
* Author/s name: Pablo Carbayo y Sergio Cornejo
* Release/Creation date: 10/3
* Class version: 1.0
* Class description: Clase para definir objetos del tipo vaca según su
* id, espacio ocupado, comida consumida, leche producida y ratio Leche/Comida
*
**********************************************************************
*/


public class Vaca {
	//Variables globales
	private int id,espacio;
	private double comida, leche;
	//Getters y Setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEspacio() {
		return espacio;
	}
	public void setEspacio(int espacio) {
		this.espacio = espacio;
	}
	public double getComida() {
		return comida;
	}
	public void setComida(double comida) {
		this.comida = comida;
	}
	
	
	public double getLeche() {
		return leche;
	}
	public void setLeche(double leche) {
		this.leche = leche;
	}
	
	//Constructor
	public Vaca(int id, int espacio, double comida, double leche) {
		super();
		this.id = id;
		this.espacio = espacio;
		this.comida = comida;
		this.leche = leche;
	}
	
	//ToString
	public String toString() {
		return "Vaca [id=" + id + ", espacio=" + espacio + ", comida=" + comida + " leche=" + leche+"]";
	}
	
	
	
}
