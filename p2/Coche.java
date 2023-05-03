package p2;


/*********************************************************************
*
* Class Name: Coche
* Author/s name: Pablo Carbayo y Sergio Cornejo
* Release/Creation date: 5/3
* Class version: 1.0
* Class description: Clase para definir objetos del tipo coche según su
* modelo, tipo de combustible, tipo de transmisión, capacidad del depósito
* consumo medio y número de asientos.
*
**********************************************************************
*/
public class Coche {
	private String modelo;
	private TipoCombustible tipoCombustible;
	private TipoTransmision tipoTransmision;
	private Boolean acaba=false;
	private double capacidadDeposito,consumoMedio,numAsinetos,gastoTotal;
	
	//Getters y setters
	public double getGastoTotal() {
		return gastoTotal;
	}
	public void setGastoTotal(double gastoTotal) {
		this.gastoTotal = gastoTotal;
	}
	public Boolean getAcaba() {
		return acaba;
	}
	public void setAcaba(Boolean acaba) {
		this.acaba = acaba;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public TipoCombustible getTipoCombustible() {
		return tipoCombustible;
	}
	public void setTipoCombustible(TipoCombustible tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}
	public TipoTransmision getTipoTransmision() {
		return tipoTransmision;
	}
	public void setTipoTransmision(TipoTransmision tipoTransmision) {
		this.tipoTransmision = tipoTransmision;
	}
	public double getNumAsinetos() {
		return numAsinetos;
	}
	public void setNumAsinetos(double numAsinetos) {
		this.numAsinetos = numAsinetos;
	}
	public double getCapacidadDeposito() {
		return capacidadDeposito;
	}
	public void setCapacidadDeposito(double capacidadDeposito) {
		this.capacidadDeposito = capacidadDeposito;
	}
	public double getConsumoMedio() {
		return consumoMedio;
	}
	public void setConsumoMedio(double consumoMedio) {
		this.consumoMedio = consumoMedio;
	}
	
	//Constructor
	public Coche(String modelo, TipoCombustible tipoCombustible, TipoTransmision tipoTransmision, double numAsinetos,
			double capacidadDeposito, double consumoMedio) {
		super();
		this.modelo = modelo;
		this.tipoCombustible = tipoCombustible;
		this.tipoTransmision = tipoTransmision;
		this.numAsinetos = numAsinetos;
		this.capacidadDeposito = capacidadDeposito;
		this.consumoMedio = consumoMedio;
	}
	
	//ToString
	public String toString() {
		return "Coche [modelo=" + modelo + ", tipoCombustible=" + tipoCombustible + ", tipoTransmision="
				+ tipoTransmision + ", capacidadDeposito=" + capacidadDeposito + ", consumoMedio=" + consumoMedio
				+ ", numAsinetos=" + numAsinetos + "]";
	}
	
}
