/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.amenbts.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author marcmartinez
 */
public class MySQLConnection {
     public static Connection get(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=&password=");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/amenbts?user=root&password=");
        }catch(Exception ex){
            System.err.print("Error: "+ex.getMessage());
        }
        return connection;
    }
}
