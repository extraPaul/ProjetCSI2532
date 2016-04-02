package model.dbbeans;

import java.sql.*;

import model.connection.DataAccess;


public class LikeArtistBean {
    private Connection connection;
    private Statement st;
    private DataAccess dataaccess;
    private ResultSet rs;
    private String likeArtistList="";


    public LikeArtistBean() {
    }

    public void setDataAccess(DataAccess db)
    {
        dataaccess = db;
    }
    
    /**
     * 
     * @param custid
     * @param aname
     * @param db
     * @return
     */
    
    public boolean existsLikeArtist(int custid, String aname, DataAccess db) {
        boolean exists = false;
        // A COMPLETER
        connection = db.getConnection();
        return(exists);
    }

     /**
      * 
      * @param custid
      * @param aname
      * @param db
      */
    public void insertLikeArtist(int custid, String aname, DataAccess db)
    {
       //A COMPLETER
    }
   
/**
 * 
 * @return
 */
    public String getLikeArtistList()
    {
        connection = dataaccess.getConnection();
        String cname;
        String aname;

        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT c.name,la.aname FROM lab9.customer c, lab9.likeartist la WHERE c.custid = la.custid");
        } catch(Exception e){
            System.out.println("Cant read likeartist table");
        }
        try{
            while (rs.next())
            {
                cname=rs.getString("name");
                aname=rs.getString("aname");
                likeArtistList+="<tr><tr><td>"
                               + cname
                               + "</td><td>"
                               + aname
                               +"</td></tr>";
            }
        }catch(Exception e){
            System.out.println("Error creating table "+e);
        }
        return likeArtistList;
    }

}
