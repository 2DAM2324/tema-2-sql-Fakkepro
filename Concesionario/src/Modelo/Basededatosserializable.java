/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author adrian
 */
public class Basededatosserializable {
    
    private String archivoClientes;
    private String archivoCoches;
    private String archivoRevisores;
    private String archivoProveedores;
    private String archivoCompras;
    private String archivoRevisiones;
    private String archivoProvisiones;
    
    public Basededatosserializable(){
    
        archivoClientes = "clientes";
        archivoCoches = "coches";
        archivoRevisores = "revisores";
        archivoProveedores = "proveedores";
        archivoCompras = "compras";
        archivoRevisiones = "revisiones";
        archivoProvisiones = "provisiones";
    
    }
    
    public void serializacionClientes(ArrayList<Cliente> lista_clientes){
    
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(archivoClientes)))
        {
            outputStream.writeObject(lista_clientes);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Cliente> deserializacionCliente(){
    
        ArrayList<Cliente> lista_clientes = new ArrayList<>();
        
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(archivoClientes)))
        {
            lista_clientes = (ArrayList<Cliente>) inputStream.readObject();
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    return lista_clientes;
    }
    
    public void serializacionCoche(ArrayList<Coche> lista_coches){
    
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(archivoCoches)))
        {
            outputStream.writeObject(lista_coches);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Coche> deserializacionCoches(){
    
        ArrayList<Coche> lista_coches = new ArrayList<>();
        
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(archivoCoches)))
        {
            lista_coches = (ArrayList<Coche>) inputStream.readObject();
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return lista_coches;
    }
    
    public void serializacionRevisores(ArrayList<Revisores> lista_revisores){
    
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(archivoRevisores)))
        {
            outputStream.writeObject(lista_revisores);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Revisores> deserializacionRevisores(){
        
        ArrayList<Revisores> lista_revisores = new ArrayList<>();
    
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(archivoRevisores)))
        {
            lista_revisores = (ArrayList<Revisores>) inputStream.readObject();
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return lista_revisores;
    }
    
    public void serializacionProveedores(ArrayList<Proveedor> lista_proveedores){
    
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(archivoProveedores)))
        {
            outputStream.writeObject(lista_proveedores);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Proveedor> deserializacionProveedores( ){
    
        ArrayList<Proveedor> lista_proveedores = new ArrayList<>();
        
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(archivoProveedores)))
        {
            lista_proveedores = (ArrayList<Proveedor>) inputStream.readObject();
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return lista_proveedores;
    }
    
    public void serializacionCompras(ArrayList<Comprar> lista_compras){
    
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(archivoCompras)))
        {
            outputStream.writeObject(lista_compras);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Comprar> deserializacionCompras(){
    
        ArrayList<Comprar> lista_compras = new ArrayList<>();
        
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(archivoCompras)))
        {
            lista_compras = (ArrayList<Comprar>) inputStream.readObject();
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return lista_compras;
    }
    
    public void serializacionRevisiones(ArrayList<Revisar> lista_revisiones){
    
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(archivoRevisiones)))
        {
            outputStream.writeObject(lista_revisiones);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Revisar> deserializacionRevisiones( ){
    
        ArrayList<Revisar> lista_revisiones = new ArrayList<>();
        
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(archivoRevisiones)))
        {
            lista_revisiones = (ArrayList<Revisar>) inputStream.readObject();
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return lista_revisiones;
    }
    
    public void serializacionProvisiones(ArrayList<Proveer> lista_provisiones){
    
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(archivoProvisiones)))
        {
            outputStream.writeObject(lista_provisiones);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Proveer> deserializacionProvsiones( ){
    
        ArrayList<Proveer> lista_provisiones = new ArrayList<>();
        
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(archivoProvisiones)))
        {
            lista_provisiones = (ArrayList<Proveer>) inputStream.readObject();
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return lista_provisiones;
    }
    
}
