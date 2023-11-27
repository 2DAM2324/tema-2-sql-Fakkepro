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
public class Revisar implements Serializable{

    private String cod_revision_revisar;
    private int vin_revisar;
    private String cod_revisor_revisar;
    
    public Revisar(String codigo_revision, int vin, String codigo_revisor){
    
        setcod_revision_revisar(codigo_revision);
        setvin_revisar(vin);
        setcod_revisor_revisar(codigo_revisor);
    
    }

    public void setcod_revision_revisar(String codigo){

        this.cod_revision_revisar = codigo;

    }

    public String getcod_revision_revisar(){

        return this.cod_revision_revisar;

    }

    public void setcod_revisor_revisar(String codigo){

        this.cod_revisor_revisar = codigo;

    }

    public String getcod_revisor_revisar(){

        return this.cod_revisor_revisar;

    }

    public void setvin_revisar(int vin){

        this.vin_revisar = vin;

    }

    public int getvin_revisar(){

        return this.vin_revisar;

    }
    
}
