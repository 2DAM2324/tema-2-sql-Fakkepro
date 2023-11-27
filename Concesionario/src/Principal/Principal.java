/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Interfaz.Ventana1;
import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author adrian
 */
public class Principal {
    
    public static void main(String[] args) {
        
        //prueba de commit
        
        Ventana1 ventana = null;
        
        try{
            
            ventana = new Ventana1();
            ventana.setVisible(true);
            
        }catch(IOException ex){
            
            ex.printStackTrace();
            
        }catch(ClassNotFoundException ex){
            
            ex.printStackTrace();
            
        }catch(SAXException ex){
            
            ex.printStackTrace();
            
        }
        
    } 
    
}
