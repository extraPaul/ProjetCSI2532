
package control;

import model.connection.DataAccess;
import model.dbbeans.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class Control extends HttpServlet
{
    private DataAccess db;
    private HttpSession s;

    
    private void processLogIn(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException
    {
    	s = request.getSession(true);
        String medecin_ID = (String)request.getParameter("username");

        // CONNEXION
        db = new DataAccess();
        db.openConnection();
        
        Medecin medecin = new Medecin().findMedecin(medecin_ID, db);
        medecin.findAttributes(medecin_ID, db);

        if (medecin != null)
        {    
         
         
            s.setAttribute("Medecin", medecin);
            
            ///SESION
            s.setAttribute("key", medecin_ID);
            s.setMaxInactiveInterval(1000);

            
            s.setAttribute("dataaccess",db);
            s.setAttribute("db",db);

            db.closeConsult();
            
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/Accueil.jsp");
            rd.forward(request,response);
        }
            else{
            	RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/Connexion.jsp");
                rd.forward(request,response);
          
            }
            
        }
    
    private void processActionPatient(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException
    {
    	String idmed = request.getParameter("drInput");
    	Medecin med = (Medecin) s.getAttribute("Medecin");
        med.getPatientsString(idmed, db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r2 = this.getServletContext().getRequestDispatcher("/PatientList.jsp");
        r2.forward(request,response);
    }
    
    private void processActionMyPatient(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException
    {
    	Medecin med = (Medecin) s.getAttribute("Medecin");
        med.getPatientsString(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/PatientList.jsp");
        r.forward(request,response);
    }
    
    private void processActionMyConsultation(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException
    {
        Medecin med = (Medecin) s.getAttribute("Medecin");
        med.getConsultationsString(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r2 = this.getServletContext().getRequestDispatcher("/ConsultList.jsp");
        r2.forward(request,response);
    }
    
    private void processActionConsultation(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException
    {
    	String idmed = request.getParameter("drConsultInput");
    	Medecin med = (Medecin) s.getAttribute("Medecin");
    	med.getConsultationsString(idmed, db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r2 = this.getServletContext().getRequestDispatcher("/ConsultList.jsp");
        r2.forward(request,response);
    }
    
    private void processActionMyMedecin(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException
    {
        Medecin med = (Medecin) s.getAttribute("Medecin");
        med.getMedecinsString(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r2 = this.getServletContext().getRequestDispatcher("/MedecinList.jsp");
        r2.forward(request,response);
    }
    
    private void processActionOpenMedecin(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException
    {
        Medecin med = (Medecin) s.getAttribute("Medecin");
        med.findAttributes(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r2 = this.getServletContext().getRequestDispatcher("/modMedInfoPerso.jsp");
        r2.forward(request,response);
    }
    
    private void deletePatient(HttpServletRequest request,HttpServletResponse response, String idPat) throws ServletException, IOException{
    	Medecin med = (Medecin) s.getAttribute("Medecin");
    	med.deletePatient(idPat, db);
    	med.getPatientsString(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/PatientList.jsp");
        r.forward(request,response);
    }
    
    private void openPatientPrescriptions(HttpServletRequest request,HttpServletResponse response, String idPat) throws ServletException, IOException{
    	Patient pat = new Patient();
    	pat.findAttributes(idPat, db);
    	pat.getPrescriptionsExamen(db);
    	pat.getPrescriptionsMed(db);
    	s.setAttribute("Patient", pat);
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/ViewPrescriptions.jsp");
        r.forward(request,response);
    }

    private void modifyPatient(HttpServletRequest request,HttpServletResponse response, String idPat) throws ServletException, IOException{
    	Patient pat = new Patient();
    	pat.findAttributes(idPat, db);
    	s.setAttribute("Patient", pat);
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/ModPatient.jsp");
        r.forward(request,response);
    }
    
    private void addPatient(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	Medecin med = (Medecin) s.getAttribute("Medecin");
    	
    	Connection connection = db.getConnection();
        String id = "P000";        

        try {
        	Statement st = connection.createStatement();

        	ResultSet rs  = st.executeQuery("SELECT max(idpat) as id FROM CabinetDB.Patient");
            rs.next();
            
            String max_id = rs.getString(1);
            id = "P" + Integer.toString(Integer.parseInt(max_id.substring(1)) + 1);
    		while(id.length() < 4){
    			id = "P0" + id.substring(1);
    		}
            
            st.executeUpdate("INSERT INTO CabinetDB.Personne VALUES ('"+id+"','"
            		+ (String)request.getParameter("AddPatNom") + "', '"
            		+ (String)request.getParameter("AddPatPrenom") + "', '"
            		+ (String)request.getParameter("AddPatNum") + "', '"
            		+ (String)request.getParameter("AddPatRue") + "', '"
            		+ (String)request.getParameter("AddPatVille") + "', '"
            		+ (String)request.getParameter("AddPatNumTel") + "');");
            
            st.executeUpdate("INSERT INTO CabinetDB.Patient VALUES ('"+id+"','"
            		+ (String)request.getParameter("AddPatNas") + "', '"
            		+ (String)request.getParameter("AddPatDateN") + "', '"
            		+ (String)request.getParameter("AddPatSexe") + "', '"
            		+  med.getIDM() + "');");

            rs.close();
            st.close();
        }catch(Exception e){
            System.out.println("Cant insert into patient table");
            System.out.println(e);
        }
    	
    	
        med.getPatientsString(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/PatientList.jsp");
        r.forward(request,response);
    }
    
    private void addPatientForm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/AddPat.jsp");
        r.forward(request,response);
    }
    
    private void modifyPatient(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	Medecin med = (Medecin) s.getAttribute("Medecin");
    	Patient pat = (Patient) s.getAttribute("Patient");
    	
    	Connection connection = db.getConnection();       

        try {
        	Statement st = connection.createStatement();
            
    		st.executeUpdate("UPDATE CabinetDB.Personne"
    				+ " SET nom = '" + (String)request.getParameter("ModPatNom")
    				+ "', prenom = '" + (String)request.getParameter("ModPatPrenom")
    				+ "', num = '" + (String)request.getParameter("ModPatNum")
    				+ "', rue = '" + (String)request.getParameter("ModPatRue")
    				+ "', ville = '" + (String)request.getParameter("ModPatVille")
    				+ "', numTelephone = '" + (String)request.getParameter("ModPatNumTel")
    				+ "' WHERE Personne.idP = '" + pat.getIdP() + "';");
    		
    		st.executeUpdate("UPDATE CabinetDB.Patient"
    				+ " SET sexe = '" + (String)request.getParameter("ModPatSexe")
    				+ "' WHERE Patient.idPat = '" + pat.getIdP() + "';");

            //rs.close();
            st.close();
        }catch(Exception e){
            System.out.println("Cant insert into Personne table");
            System.out.println(e);
        }
    	
    	
        med.getPatientsString(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/PatientList.jsp");
        r.forward(request,response);
    }
    
    private void modifyConsultation(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	Medecin med = (Medecin) s.getAttribute("Medecin");
    	Consultation cons = (Consultation) s.getAttribute("Consultation");
    	
    	Connection connection = db.getConnection();       

        try {
        	Statement st = connection.createStatement();
            
    		st.executeUpdate("UPDATE CabinetDB.Consultation"
    				+ " SET datec = '" + (String)request.getParameter("ModConsultDate")
    				+ "', heure = '" + (String)request.getParameter("ModConsultTemps")
    				+ "', duree = '" + (String)request.getParameter("ModConsultDuree")
    				+ "', raison = '" + (String)request.getParameter("ModConsultRaison")
    				+ "' WHERE Consultation.idCons = '" + cons.getIdCons() + "';");

            st.close();
        }catch(Exception e){
            System.out.println("Cant insert into Personne table");
            System.out.println(e);
        }
    	
    	
        med.getConsultationsString(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/ConsultList.jsp");
        r.forward(request,response);
    }
    
    private void modifyMedecin(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	Medecin med = (Medecin) s.getAttribute("Medecin");
    	
    	Connection connection = db.getConnection();       

        try {
        	Statement st = connection.createStatement();
            
    		st.executeUpdate("UPDATE CabinetDB.Personne"
    				+ " SET nom = '" + (String)request.getParameter("ModMedNom")
    				+ "', prenom = '" + (String)request.getParameter("ModMedPrenom")
    				+ "', num = '" + (String)request.getParameter("ModMedNum")
    				+ "', rue = '" + (String)request.getParameter("ModMedRue")
    				+ "', ville = '" + (String)request.getParameter("ModMedVille")
    				+ "', numTelephone = '" + (String)request.getParameter("ModMedNumTel")
    				+ "' WHERE Personne.idP = '" + med.getIDM() + "';");
    		
    		st.executeUpdate("UPDATE CabinetDB.Medecin"
    				+ " SET spécialite = '" + (String)request.getParameter("ModMedSpe")
    				+ "' WHERE Medecin.idM = '" + med.getIDM() + "';");

            st.close();
        }catch(Exception e){
            System.out.println("Cant insert into Personne table");
            System.out.println(e);
        }
    	
    	med.findAttributes(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/Accueil.jsp");
        r.forward(request,response);
    }
    
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
    	if (request.getParameter("logIn") != null) {
    	    processLogIn(request,response);
    	}
    	else if (request.getParameter("drInputSubmit") != null){
            processActionPatient(request,response); 
        }
    	else if (request.getParameter("openPatients") != null){
            processActionMyPatient(request,response); 
        }
    	else if (request.getParameter("openConsultation") != null){
    		processActionMyConsultation(request,response); 
        } else if (request.getParameter("openMedecin") != null){
            processActionMyMedecin(request,response);   		
    	} else if (request.getParameter("drConsultInput") != null){
            processActionConsultation(request,response);   		
    	} else if (request.getParameter("ModMedSubmit") != null){
    		modifyMedecin(request,response);   		
    	} else if (request.getParameter("openInfo") != null){
    		processActionOpenMedecin(request,response);
    	} else if (request.getParameter("auOptPatGo") != null){
    		String action = (String)request.getParameter("patAuxSelect");
    		String idPat = action.split(" ")[0];
    		
    		switch(action.split(" ")[1]){
    		case "patVoirPrescription":
    			openPatientPrescriptions(request,response,idPat);
    			break;
    		case "modifierPatinfo":
    			modifyPatient(request,response,idPat);
    			break;
			case "supprimerPatList":
				deletePatient(request,response,idPat);
				break;
    		}
    	} else if (request.getParameter("AddPatSubmit") != null){
    		addPatient(request,response); 
        } else if (request.getParameter("AddPatList") != null){
    		addPatientForm(request,response); 
        } else if (request.getParameter("ModPatSubmit") != null){
    		modifyPatient(request,response);
        } else if (request.getParameter("auOptConsultGo") != null){
    		String action = (String)request.getParameter("consAuxSelect");
    		String idCons = action.split(" ")[0];
    		
    		Consultation cons = new Consultation();
    		cons.findAttributesConsultation(idCons, db);;
    		s.setAttribute("Consultation", cons);
    		RequestDispatcher r;
    		switch(action.split(" ")[1]){
    		case "consultAddPrescriptionMed":
    			r = this.getServletContext().getRequestDispatcher("/AddPresMed.jsp");
    	        r.forward(request,response);
    			break;
    		case "consultAddPrescriptionExam":
    			r = this.getServletContext().getRequestDispatcher("/AddPresExam.jsp");
    	        r.forward(request,response);
    			break;
			case "modifierConsultinfo":
		    	r = this.getServletContext().getRequestDispatcher("/ModConsult.jsp");
		        r.forward(request,response);
				break;
			case "supprimerConsultList":
				Connection connection = db.getConnection();   

		        try {
		            Statement st = connection.createStatement();
		            
		            st.executeUpdate("DELETE FROM CabinetDB.Consultation WHERE Consultation.idcons = '" + idCons + "';");
		            st.close();
		        }catch(Exception e){
		            System.out.println("Cant delete from Consultation table");
		            System.out.println(e);
		        }
		        processActionMyConsultation(request, response);
				break;
    		}
    	} else if (request.getParameter("AddPresExamSubmit") != null){
    		Consultation cons = (Consultation) s.getAttribute("Consultation");
    		Medecin med = (Medecin) s.getAttribute("Medecin");
    		med.createPresExam(cons, request.getParameter("AddPresExamNom"), db);
    		RequestDispatcher r2 = this.getServletContext().getRequestDispatcher("/ConsultList.jsp");
            r2.forward(request,response);
        } else if (request.getParameter("AddPresMedSubmit") != null){
    		Consultation cons = (Consultation) s.getAttribute("Consultation");
    		Medecin med = (Medecin) s.getAttribute("Medecin");
    		med.createPresMed(cons, request.getParameter("AddPresMedDuree"), request.getParameter("AddPresMedIdMed"), db);
    		RequestDispatcher r2 = this.getServletContext().getRequestDispatcher("/ConsultList.jsp");
            r2.forward(request,response);
        } else if (request.getParameter("ModConsultSubmit") != null){
    		modifyConsultation(request,response);
        }
    	
    }
    
    public void destroy()
    {       
        super.destroy();
    }
}