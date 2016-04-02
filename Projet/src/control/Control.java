
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
            String customer_name=(String)request.getParameter("txtName");
            String artist_name=(String)request.getParameter("rdArtist");

            // CONNEXION
            db= new DataAccess();
            db.openConnection();

            //CUSTOMER
            CustomerBean customerbean = new CustomerBean();
            int custid = customerbean.existsCustomer(customer_name, db);

            if (custid == -1)
            {
                custid = customerbean.insertCustomer(customer_name, db);
            }

            customerbean.setName(customer_name);
            customerbean.setCustid(custid);

            s.setAttribute("customerbean", customerbean );


            //LIKE ARTIST
            LikeArtistBean likeartistbean = new LikeArtistBean();
            
            if (!likeartistbean.existsLikeArtist(custid, artist_name, db)){
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
            rd.forward(request,response);
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