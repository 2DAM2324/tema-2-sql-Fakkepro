/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author adrian
 */
public class Proveer implements Serializable{

    private String cod_proveedor_proveer;
    private String vin_proveer;
    
    public Proveer(String codigo, String vin){
    
        setcod_proveedor_proveer(codigo);
        setvin_proveer(vin);
    
    }

    public void setcod_proveedor_proveer(String codigo){
    
        this.cod_proveedor_proveer = codigo;
    
    }
    
    public String getcod_proveedor_proveer(){
    
        return this.cod_proveedor_proveer;
        
    }
    
    public void setvin_proveer(String vin){
    
        this.vin_proveer = vin;
    
    }
    
    public String getvin_proveer(){
    
        return this.vin_proveer;
        
    }
    
}
