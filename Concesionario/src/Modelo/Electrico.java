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
public class Electrico extends Coche implements Serializable{

    private double bateria_electrico;
    
    public Electrico(double bateria, int vin, String marca, String modelo, float precio, String color){
    
        super(vin, marca, modelo, precio, color);
        setbateria_electrico(bateria);
    
    }

    public void setbateria_electrico(double bateria){

        this.bateria_electrico = bateria;

    }

    public double getbateria_electrico(){

        return this.bateria_electrico;

    }
    
}
