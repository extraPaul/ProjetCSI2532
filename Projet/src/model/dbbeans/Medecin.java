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
    private int num;
    private String rue;
    private String ville;    
    private String numTelephone; 
    private String idM;
    private String info;
    private String Specialite;
    private String SpecialiteInfo;
    private String medecins;
    private String InfoMed;
    
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
    public String getInfo(){
    	return info;
    }
    
    public Medecin findMedecin(String idmed, DataAccess db) {
        Connection connection = db.getConnection();
        Medecin med = null;
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT * FROM CabinetDB.medecin, CabinetDB.personne WHERE idp = idm AND idm = '"+idmed+"';");
            
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
    
    public void findAttributes(String idmed, DataAccess db) {
        Connection connection = db.getConnection();
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT * FROM CabinetDB.medecin, CabinetDB.personne WHERE idp = idm AND idm = '"+idmed+"';");
            
            if(rs.next()){
            	this.idM = idmed;
            	this.nom = rs.getString("nom");
            	this.prenom = rs.getString("prenom");
            	this.num = rs.getInt("Num");
            	this.rue = rs.getString("Rue");
            	this.ville = rs.getString("Ville");
            	this.numTelephone = rs.getString("numTelephone");
            	this.SpecialiteInfo = rs.getString("Spécialite");
            	
            }
            
            rs.close();
            st.close();
            
            }catch(Exception e){
                System.out.println("Cant read from patient table");
                System.out.println(e);
            }
    }
    
    public ArrayList<Patient> getPatients(DataAccess db) {
        connection = db.getConnection();
        ArrayList<Patient> patient = new ArrayList<Patient>();
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT  * FROM CabinetDB.personne,CabinetDB.patient WHERE Personne.IDP = Patient.IDPat AND Patient.IDM = '"+this.idM+"';");

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
    
    public String getPatientsString(String idmed, DataAccess db) {
        connection = db.getConnection();
        info = "";
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT  * FROM CabinetDB.personne,CabinetDB.patient WHERE Personne.IDP = Patient.IDPat AND Patient.IDM = '"+idmed+"';");
        }catch(Exception e){
            System.out.println("Cant read from Personne or Patient table");
            System.out.println(e);
        }
        
        try{
            while (rs.next())
            {
            	info+="<tr><td>"
                        + rs.getString("IDPat")
                        + "</td><td>"
                        + rs.getString("nom")
                        +"</td><td>"
                        + rs.getString("prenom")
                        +"</td><td>"
                        + rs.getInt("Num")
                        +"</td><td>"
                        + rs.getString("Rue")
                        +"</td><td>"
                        + rs.getString("Ville")
                        +"</td><td>"
                        + rs.getString("numTelephone")
                        +"</td><td>"
                        + rs.getString("NAS")
                        +"</td><td>"
                        + rs.getDate("dateNaissance")
                        +"</td><td>"
                        + rs.getString("sexe")
                        +"</td><td>"
                        + "<form action='Control' method='POST'>"
                        			+  "<select name='patAuxSelect'> "
                        			+ 			"<option value='"+ rs.getString("IDPat")+" patVoirPrescription'>Voir prescriptions</option>"
                        			+  			"<option value='"+ rs.getString("IDPat")+" modifierPatinfo'>Modifier</option>"
                        			+  			"<option value='"+ rs.getString("IDPat")+" supprimerPatList'>Supprimer</option></select>"
                        			+  			"<input type='submit' name='auOptPatGo' value='Go'></form>"
                        + "</td></tr>";
            }
        }catch(Exception e){
            System.out.println("Error creating table "+e);
        }
        return info;
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
    
    //En théorie on pourrait conbinné cette fonction et getConsultations.
    public String getConsultationsString(String idmed,DataAccess db) {
        connection = db.getConnection();
        info ="";
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT Consultation.*, Personne.nom FROM CabinetDB.Consultation, CabinetDB.Patient, CabinetDB.Personne WHERE Patient.IDpat = Consultation.Patient AND Patient.IDpat = Personne.IDP AND Consultation.medecin = '"+idmed+"' ORDER BY Consultation.idcons;");
            while(rs.next()){
            	info+="<tr><td>"
                        + rs.getString("idcons")
                        + "</td><td>"
                        + rs.getString("datec")
                        +"</td><td>"
                        + rs.getString("heure")
                        +"</td><td>"
                        + rs.getDate("duree")
                        +"</td><td>"
                        + rs.getString("raison")
                        +"</td><td>"
                        + rs.getString("nom")
                        +"</td><td>"
                        +"<form action='Control' type='POST'>"
                    	+  "<select name='consAuxSelect'> <option  name='"+ rs.getString("IDPat")+" consultAddPrescriptionMed'>Ajouter prescription medicament</option>"
                    	+  "<option  name='"+ rs.getString("idcons")+" consultAddPrescriptionExam'>Ajouter prescription examen</option>"
                    	+  "<option name='"+ rs.getString("idcons")+" modifierConsultinfo'>Modifier</option>"
                    	+  "<option name='"+ rs.getString("idcons")+" supprimerConsultList'>Supprimer</option></select>"
                    	+  "<input type='submit' name='auOptConsultGo' value='Go'></form>"
                        + "</td></tr>";
            }
            rs.close();
            st.close();
            }catch(Exception e){
                System.out.println("Cant read from medecin table");
                System.out.println(e);
            }
            return info;
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
            st.executeUpdate("INSERT INTO CabinetDB.Prescription VALUES ('"+id+"','" + cons.getIdCons() + "');");
            st.executeUpdate("INSERT INTO CabinetDB.PresExamen VALUES ('"+id+"','" + nom + "');");

            rs.close();
            st.close();
        }catch(Exception e){
            System.out.println("Cant insert into customer table");
        }
        
        return id;
    }
    
    //Duré de validité de PresMed devrait peut-être être une date de début et de fin? 
    public String createPresMed(Consultation cons, String date, String idMedicament, DataAccess db){
    	connection = db.getConnection();
        String id = "S000";        

        try {
            st = connection.createStatement();

            rs  = st.executeQuery("SELECT max(idpresm) as id FROM CabinetDB.PresMedicament;");
            rs.next();
            
            String max_id = rs.getString(1);
            id = "S" + Integer.toString(Integer.parseInt(max_id.substring(1)) + 1);
    		while(id.length() < 4){
    			id = "S0" + id.substring(1);
    		}
            

            System.out.println("ID: "+ id);
            st.executeUpdate("INSERT INTO CabinetDB.Prescription VALUES ('"+id+"','" + cons.getIdCons() + "');");
            st.executeUpdate("INSERT INTO CabinetDB.PresMedicament VALUES ('"+id+"',DATE'" + date + "','"+idMedicament+"');");

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
    
    public void deletePasient(String idPat, DataAccess db){
    	connection = db.getConnection();   

        try {
            st = connection.createStatement();
            
            st.executeUpdate("DELETE FROM CabinetDB.Personne WHERE Personne.idp = '" + idPat + "';");

            rs.close();
            st.close();
        }catch(Exception e){
            System.out.println("Cant delete from customer table");
        }
        
    }
    
    public void changeDate(String idCons, Date date, DataAccess db) {
    	connection = db.getConnection();
        try{
            st = connection.createStatement();
            st.executeUpdate("UPDATE CabinetDB.Consultation "
            		+ "SET Consultation.datec = " + date.toString()
            		+ " WHERE Consultation.idcons = '"+idCons+"';");
            
            rs.close();
            st.close();
            }catch(Exception e){
                System.out.println("Cant read from medecin table");
                System.out.println(e);
            }
    }
    
    public String getMedecinsString(String idmed, DataAccess db) {
        connection = db.getConnection();
        medecins = "";
        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT  * FROM CabinetDB.Medecin,CabinetDB.personne WHERE Personne.IDP = Medecin.IDM AND Medecin.IDM <> '"+idmed+"' ORDER BY nom;");
        }catch(Exception e){
            System.out.println("Cant read from Medecin");
            System.out.println(e);
        }
        
        try{
            while (rs.next())
            {
            	medecins+="<tr><td>"
                        + rs.getString("nom")
                        +"</td><td>"
                        + rs.getString("prenom")
                        +"</td><td>"
                        + rs.getInt("Num")
                        +"</td><td>"
                        + rs.getString("Rue")
                        +"</td><td>"
                        + rs.getString("Ville")
                        +"</td><td>"
                        + rs.getString("numTelephone")
                        +"</td><td>"
                        + rs.getString("spécialite");

            }
        }catch(Exception e){
            System.out.println("Error creating table "+e);
        }
        return medecins;
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

	public String getSpecialite() {
		return Specialite;
	}

	public void setSpecialite(String specialite) {
		Specialite = specialite;
	}

	public String getSpecialiteInfo() {
		return SpecialiteInfo;
	}

	public void setSpecialiteInfo(String specialiteInfo) {
		SpecialiteInfo = specialiteInfo;
	}

	public String getMedecins() {
		return medecins;
	}

	public void setMedecins(String medecins) {
		this.medecins = medecins;
	}

	public String getInfoMed() {
		return InfoMed;
	}

	public void setInfoMed(String infoMed) {
		InfoMed = infoMed;
	}
    
}
