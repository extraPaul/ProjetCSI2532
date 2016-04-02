package model.dbbeans;
import java.sql.*;

import model.connection.DataAccess;

public class Medecin {
    private Connection connection;
    private Statement st;
    private ResultSet rs;
    private String nom;
    private String Prenom;
    private int custid;
    
    public Medecin() {
    }
    
    public String getNom()
    {
        return nom;
    }
    public String getPrenom()
    {
        return nom;
    }
    
    public int existsCustomer(String name, DataAccess db) {
        int id = -1;
        String temp;
        connection = db.getConnection();

        try{
            st = connection.createStatement();
            rs  = st.executeQuery("SELECT * FROM lab9.customer");
            while (rs.next())
            {
                temp = rs.getString("name");
                temp = temp.trim();
                if (temp.compareTo(name.trim())==0)
                    id = rs.getInt("custid");
            }
            rs.close();
            st.close();
            }catch(Exception e){
                System.out.println("Cant read from customers table");
            }
            return id;
    }
    
}
