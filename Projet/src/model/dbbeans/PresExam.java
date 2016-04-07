package model.dbbeans;

public class PresExam extends Prescription {
	
	private String nom;

	public PresExam(String idPres, String idCons, String nom) {
		super(idPres, idCons);
		this.nom = nom;
	}
	
	public String getNom(){
		return nom;
	}
	

}
