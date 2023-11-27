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
public class Gasolina extends Coche implements Serializable{

    private int deposito_gasolina;

    public Gasolina(int deposito_gasolina, int vin, String marca, String modelo, float precio, String color) {
        super(vin, marca, modelo, precio, color);
        this.deposito_gasolina = deposito_gasolina;
    }

    public int getdeposito_gasolina() {
        return deposito_gasolina;
    }

    public void setdeposito_gasolina(int deposito_gasolina) {
        this.deposito_gasolina = deposito_gasolina;
    }
    
}
