package model.dbbeans;

public class Prescription {
	private String idPres;
	private String idCons;
	
	public Prescription(String idPres, String idCons){
		this.idPres = idPres;
		this.idCons = idCons;
	}

	public String getIdCons() {
		return idCons;
	}

	public String getIdPres() {
		return idPres;
	}
}
