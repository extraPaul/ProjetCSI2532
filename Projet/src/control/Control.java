
package control;

import model.connection.DataAccess;
import model.dbbeans.*;

import java.io.*;
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
    	RequestDispatcher r2 = this.getServletContext().getRequestDispatcher("/PatientList.jsp");
        r2.forward(request,response);
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