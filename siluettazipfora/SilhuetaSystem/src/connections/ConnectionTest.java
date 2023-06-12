/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;

import javax.swing.JOptionPane;

/**
 *
 * @author caio
 */
public class ConnectionTest {
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            
            JOptionPane.showMessageDialog(null, "Conectado com SUCESSO!");
        } catch (Exception erro) {
            
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }
}
