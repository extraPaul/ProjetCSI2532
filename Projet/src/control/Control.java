
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

    
    private void processAction(HttpServletRequest request,HttpServletResponse response) 
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


            

            
      /*      if (!likeartistbean.existsLikeArtist(custid, artist_name, db)){
                likeartistbean.insertLikeArtist(custid, artist_name, db);
            }

            likeartistbean.setDataAccess(db);
            
            s.setAttribute("likeartistbean", likeartistbean );
            s.setAttribute("dataaccess",db);
            s.setAttribute("db",db);


            ///SESION
            s.setAttribute("key","000");
            s.setMaxInactiveInterval(1000);


            db.closeConsult();


            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/menu.jsp");
            rd.forward(request,response);*/
 }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        processAction(request,response);
    }
    
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        Medecin med = (Medecin) s.getAttribute("Medecin");
        med.getPatientsString(db);
        s.setAttribute("Medecin", med);
    }
    
    public void destroy()
    {       
        super.destroy();
    }
}