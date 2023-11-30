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

    private String codigo_proveer;
    private String cod_proveedor_proveer;
    private String vin_proveer;
    
    public Proveer(String codigo, String codigoProveedor, String vin){
    
        setCodigo_proveer(codigo);
        setcod_proveedor_proveer(codigoProveedor);
        setvin_proveer(vin);
    
    }

    public String getCodigo_proveer() {
        return codigo_proveer;
    }

    public void setCodigo_proveer(String codigo_proveer) {
        this.codigo_proveer = codigo_proveer;
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
