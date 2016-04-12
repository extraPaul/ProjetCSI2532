package model.dbbeans;

import java.sql.*;
import model.connection.DataAccess;

public class Consultation {
	
	private Statement st;
	private ResultSet rs;
	private String idCons;
	private Date date;
	private Time heure;
	private Time duree;
	private String raison;
	
	public Consultation(){
		
	}
	
	public Consultation(String idCons, Date date, Time time, Time duree, String raison){
		this.setIdCons(idCons);
		this.setDate(date);
		this.setHeure(time);
		this.setDuree(duree);
		this.setRaison(raison);
		
	}
	
	public void findAttributesConsultation(String idcons, DataAccess db) {
        Connection connection = db.getConnection();
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT * FROM CabinetDB.consultation WHERE idcons = '"+idcons+"';");
            
            if(rs.next()){
            	this.idCons = idcons;
            	this.date = rs.getDate("datec");
            	this.heure = rs.getTime("heure");
            	this.duree = rs.getTime("duree");
            	this.raison = rs.getString("raison");        	
            }
            
            rs.close();
            st.close();
            
            }catch(Exception e){
                System.out.println("Cant read from patient table");
                System.out.println(e);
            }
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

	public Time getHeure() {
		return heure;
	}

	public void setHeure(Time time) {
		this.heure = time;
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
