package model.dbbeans;

import java.sql.*;
import model.connection.DataAccess;

public class Consultation {
	
	private Statement st;
	private ResultSet rs;
	private String idCons;
	private Date date;
	private Time time;
	private Time duree;
	private String raison;
	
	public Consultation(){
		
	}
	
	public Consultation(String idCons, Date date, Time time, Time duree, String raison){
		this.setIdCons(idCons);
		this.setDate(date);
		this.setTime(time);
		this.setDuree(duree);
		this.setRaison(raison);
		
	}

	public String getIdCons() {
		return idCons;
	}

	public void setIdCons(String idCons) {
		this.idCons = idCons;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Time getDuree() {
		return duree;
	}

	public void setDuree(Time duree) {
		this.duree = duree;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}

}
