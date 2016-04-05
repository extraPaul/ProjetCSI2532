package model.dbbeans;
import java.sql.*;

import model.connection.DataAccess;

public class Medecin {
    private Connection connection;
    private Statement st;
    private ResultSet rs;
    private String nom;
    private String Prenom;
    private String IDM;
    
    public Medecin() {
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
        return Prenom;
    }
    
    public void setPrenom(String Prenom)
    {
    	this.Prenom = Prenom;
    }
    public String getIDM()
    {
        return IDM;
    }
    public void setIDM(String IDM)
    {
    	this.IDM = IDM;
    }
    
    public Medecin findMedecin(String idmed, DataAccess db) {
        Boolean MedExist = false;
        String temp;
        connection = db.getConnection();
        Medecin med = new Medecin();
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT * FROM CabinetDB.medecin WHERE IDM = ".concat(idmed));
            /*while (rs.next()){
                temp = rs.getString("idm");
                temp = temp.trim();
                if (temp.compareTo(idmed.trim())==0)
                   MedExist = true;
            }*/
            
            //Créer un nouveau médecin.
            
            rs.close();
            st.close();
            }catch(Exception e){
                System.out.println("Cant read from medecin table");
            }
            return med;
    }
    
    public String getNom(String idmed, DataAccess db) {
        String temp;
        String nom ="";
        connection = db.getConnection();

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
