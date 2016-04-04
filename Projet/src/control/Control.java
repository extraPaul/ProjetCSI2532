
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
            String artist_name=(String)request.getParameter("rdArtist");

            // CONNEXION
            db= new DataAccess();
            db.openConnection();

            //MedecinExiste
            Medecin Medecin = new Medecin();
            boolean exist = Medecin.existsMedecin(Medecin_ID, db);

            if (exist)
            {    
            	Medecin.setIDM(Medecin_ID);
            	Medecin.setNom(Medecin_ID);
                Medecin.getNom();
                Medecin.getPrenom();
                s.setAttribute("PrenomNom", Medecin );
            }




            //LIKE ARTIST
            LikeArtistBean likeartistbean = new LikeArtistBean();
            
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