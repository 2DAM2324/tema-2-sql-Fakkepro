/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

/**
 *
 * @author adrian
 */
public class Cliente implements Serializable{

    private String dni_cliente;
    private String nombre_cliente;
    private String direccion_cliente;
    private String ciudad_cliente;
    private String numero_telefono_cliente;
    
    public Cliente(String dni, String nombre, String direccion, String ciudad, String numero_telefono){
    
        setdni_cliente(dni);
        setnombre_cliente(nombre);
        setdireccion_cliente(direccion);
        setciudad_cliente(ciudad);
        setnumero_telefono_cliente(numero_telefono);
    
    }

    public void setdni_cliente(String dni){

        this.dni_cliente = dni;

    }

    public String getdni_cliente(){

        return this.dni_cliente;

    }

    public void setnombre_cliente(String nombre){

        this.nombre_cliente = nombre;

    }

    public String getnombre_cliente(){

        return this.nombre_cliente;

    }

    public void setdireccion_cliente(String direccion){

        this.direccion_cliente = direccion;

    }

    public String getdireccion_cliente(){

        return this.direccion_cliente;

    }

    public void setciudad_cliente(String ciudad){

        this.ciudad_cliente = ciudad;

    }

    public String getciudad_cliente(){

        return this.ciudad_cliente;

    }

    public void setnumero_telefono_cliente(String numero){

        this.numero_telefono_cliente = numero;

    }

    public String getnumero_telefono_cliente(){

        return this.numero_telefono_cliente;

    }
}
