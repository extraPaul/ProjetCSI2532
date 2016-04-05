
package control;

import model.connection.DataAccess;
import model.dbbeans.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Control extends HttpServlet
{
    private DataAccess db;

    
    private void processAction(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException
    {
            HttpSession s = request.getSession(true);
            String Medecin_ID = (String)request.getParameter("txtName");

            // CONNEXION
            db= new DataAccess();
            db.openConnection();

            //MedecinExiste
            //Medecin medecin = new Medecin();
            //boolean exist = medecin.existsMedecin(Medecin_ID, db);
            
            Medecin medecin = findMedecin(Medecin_ID, db);

            if (exist)
            {    
            	//À changer
            	medecin.setIDM(Medecin_ID);
            	medecin.setNom(Medecin_ID);
                medecin.getNom();
                medecin.getPrenom();
                medecin.setDataAccess(db);
                s.setAttribute("Medecin", medecin);
                
              ///SESION
                s.setAttribute("key","000");
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

    
    public void destroy()
    {       
        super.destroy();
    }
}