package model.dbbeans;

import java.sql.*;
import java.util.ArrayList;

import model.connection.DataAccess;

public class Medecin {
    private Statement st;
    private ResultSet rs;
    private Connection connection;
    private String nom;
    private String prenom;
    private String idM;
    private String patients = "";
    
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
            rs  = st.executeQuery("SELECT * FROM CabinetDB.medecin, CabinetDB.personne WHERE idp = idm AND idm = '"+idmed+"'");
            
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
    
    public ArrayList<Patient> getPatients(DataAccess db) {
        connection = db.getConnection();
        ArrayList<Patient> patient = new ArrayList<Patient>();
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT  * FROM CabinetDB.personne,CabinetDB.patient WHERE Personne.IDP = Patient.IDPat AND Patient.IDM = '"+this.idM+"'");

            while(rs.next())
            {
                patient.add(new Patient(rs.getString("IDPat"),rs.getString("nom"), rs.getString("prenom"),rs.getInt("Num"),rs.getString("Rue"),rs.getString("Ville"),rs.getString("numTelephone"),rs.getString("NAS"),rs.getDate("dateNaissance"),rs.getString("sexe")));         	
            }
            rs.close();
            st.close();
            }catch(Exception e){
                System.out.println("Cant read from Personne or Patient table");
                System.out.println(e);
            }
        return patient;
    }
    
    public String getPatientsString(DataAccess db) {
        connection = db.getConnection();
        
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT  * FROM CabinetDB.personne,CabinetDB.patient WHERE Personne.IDP = Patient.IDPat AND Patient.IDM = '"+this.idM+"'");
        }catch(Exception e){
            System.out.println("Cant read from Personne or Patient table");
            System.out.println(e);
        }
        
        try{
            while (rs.next())
            {
                patients+="<tr><tr><td>"
                               + rs.getString("IDPat")
                               + "</td><td>"
                               + rs.getString("nom")
                               +"</td></tr>"
                               + rs.getString("prenom")
                               +"</td></tr>"
                               + rs.getInt("Num")
                               +"</td></tr>"
                               + rs.getString("Rue")
                               +"</td></tr>"
                               + rs.getString("Ville")
                               +"</td></tr>"
                               + rs.getString("numTelephone")
                               +"</td></tr>"
                               + rs.getString("NAS")
                               +"</td></tr>"
                               + rs.getDate("dateNaissance")
                               +"</td></tr>"
                               + rs.getString("sexe")
                               +"</td></tr>";
            }
        }catch(Exception e){
            System.out.println("Error creating table "+e);
        }
        return patients;
    }
    
    
    public ArrayList<Consultation> getConsultations(DataAccess db) {
        connection = db.getConnection();
        ArrayList<Consultation> cons = new ArrayList<Consultation>();
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT  * FROM CabinetDB.Personne, CabinetDB.Consultation WHERE Consultation.IDM = Personne.IDMAND Personne.IDM = '"+this.idM+"'");
            
            while(rs.next()){
            	cons.add(new Consultation(rs.getString("idcons"), rs.getDate("datec"), rs.getTime("heure"), rs.getTime("duree"), rs.getString("raison")));
            }
            
            rs.close();
            st.close();
            }catch(Exception e){
                System.out.println("Cant read from medecin table");
                System.out.println(e);
            }
            return cons;
    }
    
    public ArrayList<Prescription> getPrescriptions(DataAccess db) {
        Connection connection = db.getConnection();
        ArrayList<Prescription> pres = new ArrayList<Prescription>();
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT  * FROM CabinetDB.prescription p, CabinetDB.presexamen pE, CabinetDB.consultation c WHERE p.consultation = c.idcons"
            		+ "AND p.idpres = pE.idprese AND c.medecin = '"+this.idM+"'");
            while(rs.next()){
            	pres.add(new PresExam(rs.getString("idprese"), rs.getString("idcons"), rs.getString("nom")));
            }
            
            rs  = st.executeQuery("SELECT  * FROM CabinetDB.prescription p, CabinetDB.presmedicament pM, CabinetDB.consultation c WHERE p.consultation = c.idcons"
            		+ "AND p.idpres = pM.idpresm AND c.medecin = '"+this.idM+"'");
            while(rs.next()){
            	pres.add(new PresMed(rs.getString("idpresm"), rs.getString("idcons"), rs.getDate("duréevalidite"), rs.getString("idmed")));
            }
            rs.close();
            st.close();
            }catch(Exception e){
                System.out.println("Cant read from Personne or Patient table");
                System.out.println(e);
            }
        return pres;
    }
    
    public String createPresExam(Consultation cons, String nom, DataAccess db){
    	connection = db.getConnection();
        String id = "E000";        

        try {
            st = connection.createStatement();

            rs  = st.executeQuery("SELECT max(idprese) as id FROM CabinetDB.PresExamen");
            rs.next();
            
            String max_id = rs.getString(1);
            id = "E" + Integer.toString(Integer.parseInt(max_id.substring(1)) + 1);
    		while(id.length() < 4){
    			id = "E0" + id.substring(1);
    		}
            

            System.out.println("ID: "+ id);
            st.executeUpdate("INSERT INTO CabinetDB.Prescription VALUES ('"+id+"','" + cons.getIdCons() + "')");
            st.executeUpdate("INSERT INTO CabinetDB.PresExamen VALUES ('"+id+"','" + nom + "')");

            rs.close();
            st.close();
        }catch(Exception e){
            System.out.println("Cant insert into customer table");
        }
        
        return id;
    }
    
    //Duré de validité de PresMed devrait peut-être être une date de début et de fin? 
    public String createPresMed(Consultation cons, Date date, String idMedicament, DataAccess db){
    	connection = db.getConnection();
        String id = "S000";        

        try {
            st = connection.createStatement();

            rs  = st.executeQuery("SELECT max(idpresm) as id FROM CabinetDB.PresMedicament");
            rs.next();
            
            String max_id = rs.getString(1);
            id = "S" + Integer.toString(Integer.parseInt(max_id.substring(1)) + 1);
    		while(id.length() < 4){
    			id = "S0" + id.substring(1);
    		}
            

            System.out.println("ID: "+ id);
            st.executeUpdate("INSERT INTO CabinetDB.Prescription VALUES ('"+id+"','" + cons.getIdCons() + "')");
            st.executeUpdate("INSERT INTO CabinetDB.PresMedicament VALUES ('"+id+"',DATE'" + date.toString() + "','"+idMedicament+"')");

            rs.close();
            st.close();
        }catch(Exception e){
            System.out.println("Cant insert into customer table");
        }
        
        return id;
    }
    
    //This could potentially become a "search through all".
    //En se moment it only looks throught attribues of personne and pathologie(name).
    public ArrayList<Patient> searchPatients(String search, DataAccess db) {
        connection = db.getConnection();
        ArrayList<Patient> patient = new ArrayList<Patient>();
        try{
            st = connection.createStatement();
            //Check attribues again...
            rs  = st.executeQuery("SELECT  p.IDPat,p.nom, p.prenom,p.Num,p.Rue,p.Ville,p.numTelephone,p.NAS,p.dateNaissance,p.sexe"
            		+ "FROM CabinetDB.personne p,CabinetDB.patient, CabinetDB.pathologie, CabinetDB.dureepatho"
            		+ "WHERE p.IDP = Patient.IDPat AND Patient.IDM = '"+this.idM+"' AND dureepatho.idpat = p.IDP AND pathologie.idpatho = dureepatho.idpatho"
            		+ "AND MATCH (p.IDPat,p.nom, p.prenom,p.Num,p.Rue,p.Ville,p.numTelephone,p.NAS,p.dateNaissance,pathologie.nom)"
            		+ "AGAINST ('" + search + "' IN BOOLEAN MODE);");

            while(rs.next())
            {
                patient.add(new Patient(rs.getString("IDPat"),rs.getString("nom"), rs.getString("prenom"),rs.getInt("Num"),rs.getString("Rue"),rs.getString("Ville"),rs.getString("numTelephone"),rs.getString("NAS"),rs.getDate("dateNaissance"),rs.getString("sexe")));         	
            }
            rs.close();
            st.close();
            }catch(Exception e){
                System.out.println("Cant read from Personne or Patient table");
                System.out.println(e);
            }
        return patient;
    }
    
}
