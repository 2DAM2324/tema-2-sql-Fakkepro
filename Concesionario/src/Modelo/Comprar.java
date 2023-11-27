/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 *
 * @author adrian
 */
public class Comprar implements Serializable{

    private String matricula_comprar;
    private LocalDateTime FechayHora;
    private String dni_comprar;
    private int vin_comprar;
    
    public Comprar(String matricula, LocalDateTime fechayhora, String dni, int vin){
    
        setmatricula_comprar(matricula);
        setFechayHora(fechayhora);
        setdni_comprar(dni);
        setvin_comprar(vin);
    
    }
    
    public Comprar(){

    }

    public void setmatricula_comprar(String matricula){
    
        this.matricula_comprar = matricula;
    
    }
    
    public String getmatricula_comprar(){
    
        return this.matricula_comprar;
        
    }

    public LocalDateTime getFechayHora() {
        
        return FechayHora;
        
    }

    public void setFechayHora(LocalDateTime FechayHora) {
        
        this.FechayHora = FechayHora;
        
    }
    
    
    public void setdni_comprar(String dni){
    
        this.dni_comprar = dni;
    
    }
    
    public String getdni_comprar(){
    
        return this.dni_comprar;
        
    }
    
    public void setvin_comprar(int vin){
    
        this.vin_comprar = vin;
    
    }
    
    public int getvin_comprar(){
    
        return this.vin_comprar;
        
    }
    
}
