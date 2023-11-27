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
public class Revisores implements Serializable{

    private String cod_revisor_revisores;
    private String nombre_revisores;
    
    public Revisores(String codigo_revisor, String nombre_revisor){
    
        setcod_revisor_revisores(codigo_revisor);
        setNombre_revisores(nombre_revisor);
    
    }

    public void setcod_revisor_revisores(String codigo){

        this.cod_revisor_revisores = codigo;

    }

    public String getcod_revisor_revisores(){

        return this.cod_revisor_revisores;

    }

    public String getNombre_revisores() {
        
        return nombre_revisores;
        
    }

    public void setNombre_revisores(String nombre_revisores) {
        
        this.nombre_revisores = nombre_revisores;
        
    }
    
}
