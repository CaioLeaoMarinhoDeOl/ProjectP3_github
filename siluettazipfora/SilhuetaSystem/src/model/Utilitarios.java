/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author caio
 */
public class Utilitarios {
    
    public void limpaTela(JPanel container){
            
        Component components[] = container.getComponents();
            for(Component component: components){
                if(component instanceof JTextField){
                    ((JTextField)component).setText(null);
            }
        }        
    }        
}
