package MetodologiaProg.p4;

public class Vaca {
	
	private int id,espacio;
	private double comida, leche, ratioLecheComida;
	
	public double getRatioLecheComida(){
		return ratioLecheComida;
	}

	public void setRatioLecheComida(double ratioLecheComida){
		this.ratioLecheComida=ratioLecheComida;
	}
	
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
	public Vaca(int id, int espacio, double comida, double leche) {
		super();
		this.id = id;
		this.espacio = espacio;
		this.comida = comida;
		this.leche = leche;
	}
	
	
	public String toString() {
		return "Vaca [id=" + id + ", espacio=" + espacio + ", comida=" + comida + " leche=" + leche
				+ "]";
	}
}
