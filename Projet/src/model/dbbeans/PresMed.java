package model.dbbeans;

import java.sql.Date;

public class PresMed extends Prescription {
	
	private Date duree;
	private String idMed;

	public PresMed(String idPres, String idCons, Date duree, String idMed) {
		super(idPres, idCons);
		this.duree = duree;
		this.idMed = idMed;
	}

	public Date getDuree() {
		return duree;
	}

	public void setDuree(Date duree) {
		this.duree = duree;
	}

	public String getIdMed() {
		return idMed;
	}

	public void setIdMed(String idMed) {
		this.idMed = idMed;
	}

}
