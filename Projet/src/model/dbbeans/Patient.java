package model.dbbeans;

import java.sql.*;
import java.util.ArrayList;

import model.connection.DataAccess;

public class Patient {
	    private Statement st;
	    private ResultSet rs;
	    private String nom;
	    private String prenom;
	    private int num;
	    private String rue;
	    private String ville;
	    private String numTelephone;
	    private String idP;
	    private String nas;  
	    private Date dateNaissance;
	    private String sexe;
	    
	    public Patient(){
	    	
	    }
	    public Patient(String idp, String nom, String prenom,int num, String rue, String ville,String numTelephone, String nas,Date dateNaissance,String sexe) {
	    	this.idP = idp;
	    	this.nom = nom;
	    	this.prenom = prenom;
	    	this.nom = nom;
	    	this.num = num;
	    	this.rue = rue;
	    	this.ville = ville;
	    	this.nas = nas;
	    	this.dateNaissance = dateNaissance;
	    	this.sexe = sexe;
	    }
	    public ArrayList<Patient> findPatient(String idmed, DataAccess db) {
	    String temp;
        String nom ="";
        Connection connection = db.getConnection();
        ArrayList<Patient> patient = new ArrayList<Patient>();
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT  * FROM Personne,Patient WHERE Patient.IDM = ' AND Personne.IDP = Patient.IDPat;".concat(idmed.concat("'")));

            while(rs.next())
            {
                patient.add(new Patient(rs.getString("IDPat"),rs.getString("nom"), rs.getString("prenom"),rs.getInt("Num"),rs.getString("Rue"),rs.getString("Ville"),rs.getString("numTelephone"),rs.getString("NAS"),rs.getDate("dateNaissance"),rs.getString("sexe")));         	
            }
            rs.close();
            st.close();
            }catch(Exception e){
                System.out.println("Cant read from Personne or Patient table");
            }
            return patient;
    
	    }
		public Statement getSt() {
			return st;
		}
		public void setSt(Statement st) {
			this.st = st;
		}
		public ResultSet getRs() {
			return rs;
		}
		public void setRs(ResultSet rs) {
			this.rs = rs;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public String getRue() {
			return rue;
		}
		public void setRue(String rue) {
			this.rue = rue;
		}
		public String getVille() {
			return ville;
		}
		public void setVille(String ville) {
			this.ville = ville;
		}
		public String getNumTelephone() {
			return numTelephone;
		}
		public void setNumTelephone(String numTelephone) {
			this.numTelephone = numTelephone;
		}
		public String getIdP() {
			return idP;
		}
		public void setIdP(String idP) {
			this.idP = idP;
		}
		public String getNas() {
			return nas;
		}
		public void setNas(String nas) {
			this.nas = nas;
		}
		public Date getDateNaissance() {
			return dateNaissance;
		}
		public void setDateNaissance(Date dateNaissance) {
			this.dateNaissance = dateNaissance;
		}
		public String getSexe() {
			return sexe;
		}
		public void setSexe(String sexe) {
			this.sexe = sexe;
		}
}
