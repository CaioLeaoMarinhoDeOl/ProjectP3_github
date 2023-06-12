/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author caio
 */
public class ConnectionFactory {
    public Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/silhuettahost","silhuetta","08112017");
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }
}