package model.dbbeans;
import java.sql.*;

import model.connection.DataAccess;

public class Medecin {
    private Statement st;
    private ResultSet rs;
    private String nom;
    private String prenom;
    private String idM;
    
    public Medecin() {
    }
    
    public Medecin(String idm, String nom, String prenom) {
    	this.idM = idm;
    	this.nom = nom;
    	this.prenom = prenom;
    }
    
    public String getNom()
    {
        return nom;
    }
    public void setNom(String nom)
    {
    	this.nom = nom;
    }
    public String getPrenom()
    {
        return prenom;
    }
    
    public void setPrenom(String Prenom)
    {
    	this.prenom = Prenom;
    }
    public String getIDM()
    {
        return idM;
    }
    public void setIDM(String IDM)
    {
    	this.idM = IDM;
    }
    
    public Medecin findMedecin(String idmed, DataAccess db) {
        Connection connection = db.getConnection();
        Medecin med = null;
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT * FROM CabinetDB.medecin, CabinetDB.personne WHERE idp = idm AND idm = '".concat(idmed).concat("'"));
            if(rs.next())
            	med = new Medecin(idmed, rs.getString("nom"), rs.getString("prenom"));
            
            rs.close();
            st.close();
            }catch(Exception e){
                System.out.println("Cant read from medecin table");
                System.out.println(e);
            }
            return med;
    }
    
    public String getNom(String idmed, DataAccess db) {
        String temp;
        String nom ="";
        Connection connection = db.getConnection();

        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT IDM FROM CabinetDB.medecin WHERE IDM = idmed");
            while (rs.next()){
                temp = rs.getString("nom");
                temp = temp.trim();
                if (temp.compareTo(nom.trim())==0)
                   nom = rs.getString("nom");
            }
            rs.close();
            st.close();
            }catch(Exception e){
                System.out.println("Cant read from medecin table");
            }
            return nom;
    }
}
