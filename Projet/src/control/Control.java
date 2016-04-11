
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

        if (medecin != null)
        {    
         //Get patient string should maybe only be done when needed?
         System.out.println();
         //System.out.println(medecin.getPatientsString(medecin_ID, db));
         
         
            s.setAttribute("Medecin", medecin);
            //For testing
            ArrayList<Patient> patients = medecin.getPatients(db);
            for(int i = 0; i < patients.size(); i++){
             System.out.println(patients.get(i).toString());
            }
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
    
    private void deletePatient(HttpServletRequest request,HttpServletResponse response, String idPat) throws ServletException, IOException{
    	Medecin med = (Medecin) s.getAttribute("Medecin");
    	med.deletePasient(idPat, db);
    	med.getPatientsString(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/PatientList.jsp");
        r.forward(request,response);
    }
    
    private void openPatientPrescriptions(HttpServletRequest request,HttpServletResponse response, String idPat) throws ServletException, IOException{
    	Patient pat = new Patient();
    	pat.setIdP(idPat);
    	pat.getPrescriptionsExam();
    	pat.getPrescriptionsMed();
    	s.setAttribute("Patient", pat);
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/JSP_forms/ViewPrescriptions.jsp");
        r.forward(request,response);
    }

    private void modifyPatient(HttpServletRequest request,HttpServletResponse response, String idPat) throws ServletException, IOException{
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/JSP_forms/ModPatient.jsp");
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
            

            System.out.println("ID: "+ id);
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
            System.out.println("Cant insert into customer table");
        }
    	
    	
        med.getPatientsString(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/PatientList.jsp");
        r.forward(request,response);
    }
    
    private void addPatientForm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	RequestDispatcher r = this.getServletContext().getRequestDispatcher("/JSP_forms/AddPat.jsp");
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
        }
    	else if (request.getParameter("auOptPatGo") != null){
    		String action = (String)request.getParameter("patAuxSelect");
    		String idPat = action.split(" ")[0];
    		
    		System.out.println(action);
    		
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
        }
    	
    }
    
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        Medecin med = (Medecin) s.getAttribute("Medecin");
        med.getPatientsString(med.getIDM(), db);
        s.setAttribute("Medecin", med);
    }
    
    public void destroy()
    {       
        super.destroy();
    }
}