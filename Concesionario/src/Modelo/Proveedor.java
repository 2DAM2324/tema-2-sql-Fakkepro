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
public class Proveedor implements Serializable{
    
    private String cod_proveedor;
    private String nombre_provedor;
    
    public Proveedor(String codigo, String nombre){
    
        setcod_proveedor(codigo);
        setnombre_provedor(nombre);
    
    }

    public void setcod_proveedor(String codigo){

        this.cod_proveedor = codigo;

    }

    public String getcod_proveedor(){

        return this.cod_proveedor;

    }
    
    public void setnombre_provedor(String nombre){

        this.nombre_provedor = nombre;

    }

    public String getnombre_provedor(){

        return this.nombre_provedor;

    }
    
}
