/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_billing.v3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Shree
 */
public class Database {
    public Database() throws SQLException{
        
    }
 public Connection conn() throws SQLException, ClassNotFoundException{

java.sql.Connection con;
Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3307/e_billing","root","admin");
return(con);

}
 
}
