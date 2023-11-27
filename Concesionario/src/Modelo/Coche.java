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
public class Coche implements Serializable{

    protected int vin_coche;
    protected String marca_coche;
    protected String modelo_coche;
    protected float precio_coche;
    protected String color_coche;
    
    public Coche(int vin, String marca, String modelo, float precio, String color){
    
        setvin_coche(vin);
        setmarca_coche(marca);
        setmodelo_coche(modelo);
        setprecio_coche(precio);
        setcolor_coche(color);
    
    }

    public void setvin_coche(int vin){

        this.vin_coche = vin;

    }

    public int getvin_coche(){

        return this.vin_coche;

    }

    public void setmarca_coche(String marca){

        this.marca_coche = marca;

    }

    public String getmarca_coche(){

        return this.marca_coche;

    }

    public void setmodelo_coche(String modelo){

        this.modelo_coche = modelo;

    }

    public String getmodelo_coche(){

        return this.modelo_coche;

    }

    public void setcolor_coche(String color){

        this.color_coche = color;

    }

    public String getcolor_coche(){

        return this.color_coche;

    }

    public void setprecio_coche(float precio){

        this.precio_coche = precio;

    }

    public float getprecio_coche(){

        return this.precio_coche;

    }

}
