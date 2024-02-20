/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author admin
 */
public class DBContext {
    protected Connection connect;
    
    private final String serverName = "localhost";
    private final String dbName = "checkdb";
    private final String portName = "1433";
    private final String userID = "sa";
    private final String password = "Hahien123";
    
    
    public DBContext(){
        try {
            
            String url = "jdbc:sqlserver://" + serverName + ":" + portName + ";databaseName="+ dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection(url, userID, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
