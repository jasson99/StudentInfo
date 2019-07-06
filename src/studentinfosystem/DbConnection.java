/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfosystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jasmine
 */
public class DbConnection {
    private Connection myConn;
    
    public Connection DbConnect () throws ClassNotFoundException, SQLException{
        try{
              Class.forName("com.mysql.jdbc.Driver");
              myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sis","jasmine","jasmine");
        }catch(SQLException ex){
            System.out.println("Error: "+ex);
        }
        return myConn;
    }
}
